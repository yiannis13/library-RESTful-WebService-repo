package gr.ioannidis.library.rs.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import gr.ioannidis.library.rs.exception.AccessNotAllowedException;
import gr.ioannidis.library.security.ILoginManager;
import gr.ioannidis.library.security.db.model.User;
import gr.ioannidis.library.security.model.LoginResult;


@RestController
public class LoginRestController {
	
	@Autowired
	private ILoginManager loginManager;
	
	
	@PostMapping("/login")
	public ResponseEntity<LoginResult> login(@RequestBody User user) {
		LoginResult loginResult = loginManager.loginUser(user.getUsername(), user.getPassword());
		if (loginResult.isError()) {
			throw new AccessNotAllowedException(loginResult.getErrorMessage());
		}
		HttpHeaders header = new HttpHeaders();
		header.set("Authorization", loginResult.getToken());
		return new ResponseEntity<>(header, HttpStatus.CREATED);
	}
	

}
