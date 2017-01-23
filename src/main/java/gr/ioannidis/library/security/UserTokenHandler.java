package gr.ioannidis.library.security;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gr.ioannidis.library.security.db.controller.IUserPasswordRepository;
import gr.ioannidis.library.security.db.controller.IUserRepository;
import gr.ioannidis.library.security.db.controller.IUserTokenRepository;
import gr.ioannidis.library.security.db.model.User;
import gr.ioannidis.library.security.db.model.UserPassword;
import gr.ioannidis.library.security.db.model.UserToken;


@Service
public class UserTokenHandler implements IUserTokenHandler {

	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private IUserPasswordRepository userPasswordRepository;
	@Autowired
	private IPasswordProtector passwordProtector;
	@Autowired
	private IUserTokenRepository userTokenRepository;

	
	@Override
	public UserToken create(String username) {
		// check the input
		if (username == null) {
			return null;
		}
		// check if there is a user with this username
		User user = userRepository.findByUsername(username);
		if (user == null) {
			return null;
		}
		// check if there is a password associated with that username
		UserPassword password = userPasswordRepository.findByUserUsername(username);
		if (password == null) {
			return null;
		}
		// create the userToken 
		UserToken userToken = new UserToken();
		String tokenValue = passwordProtector.protect(username + password);
		userToken.setTokenValue(tokenValue);
		userToken.setUser(user);
		Date validUntil = new Date();
		validUntil.setTime(validUntil.getTime() + (long) 1000 * 60 * 60 * 48); // valid for 2 days
		userToken.setValidUntil(validUntil);
		//save the userToken object in DB
		userTokenRepository.save(userToken);

		return userToken;
	}

	@Override
	public void refresh(UserToken token) {
		Date validUntil = token.getValidUntil();
		if (validUntil != null) {
			long time = validUntil.getTime() + (1000 * 60 * 60 * 48); // make it valid for another 2 days
			validUntil.setTime(time);
			token.setValidUntil(validUntil);
			userTokenRepository.save(token);
		}
	}

}
