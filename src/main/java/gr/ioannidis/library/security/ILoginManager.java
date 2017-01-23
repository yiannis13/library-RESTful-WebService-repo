package gr.ioannidis.library.security;

import gr.ioannidis.library.security.model.LoginResult;

public interface ILoginManager {

	LoginResult loginUser(String username, String password);
	
	boolean isValidToken(String token);
}
