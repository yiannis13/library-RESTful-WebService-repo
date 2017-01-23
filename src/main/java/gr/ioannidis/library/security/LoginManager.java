package gr.ioannidis.library.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gr.ioannidis.library.security.db.controller.IUserPasswordRepository;
import gr.ioannidis.library.security.db.controller.IUserRepository;
import gr.ioannidis.library.security.db.controller.IUserTokenRepository;
import gr.ioannidis.library.security.db.model.User;
import gr.ioannidis.library.security.db.model.UserPassword;
import gr.ioannidis.library.security.db.model.UserToken;
import gr.ioannidis.library.security.model.LoginResult;


@Service
public class LoginManager implements ILoginManager {
	
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private IUserPasswordRepository userPasswordRepository;
	@Autowired
	private IPasswordProtector passwordProtector;
	@Autowired
	private IUserTokenRepository userTokenRepository;
	@Autowired
	private IUserTokenHandler userTokenHandler;
	
	

	@Override
	public LoginResult loginUser(String username, String password) {
		// check the inputs
		if ((username == null) || (password == null)) {
			return LoginResult.failedLogin("username and/or password did not provided");
		}
		// check the username
		User user = userRepository.findByUsername(username);
		if (user == null) {
			return LoginResult.failedLogin("No user with that username");
		}
		// check the password
		UserPassword pass = userPasswordRepository.findByUserUsername(username);
		if (pass == null) {
			return LoginResult.failedLogin("User login is not allowed");
		}
		// check if the 2 passwords match   
		if (!pass.getPassword().equals(passwordProtector.protect(password))) {
			return LoginResult.failedLogin("Your password is not correct");
		}
		// check if the user is already logged in, and if his token has not expired
		// If yes, return that tokenValue, but refresh each time
		UserToken userToken = userTokenRepository.findByUserUsername(username);
		if ((userToken != null) && (!InternalUtilities.hasExpired(userToken.getValidUntil()))) {
			userTokenHandler.refresh(userToken);
			return LoginResult.successfulLogin(userToken.getTokenValue());
		}
		userToken= userTokenHandler.create(username);
		return LoginResult.successfulLogin(userToken.getTokenValue());
	}

	@Override
	public boolean isValidToken(String token) {
		UserToken tokenValue = userTokenRepository.findByTokenValue(token);
		if ((tokenValue != null) && (!InternalUtilities.hasExpired(tokenValue.getValidUntil()))) {
			return true;
		}
		return false;
	}
	
}
