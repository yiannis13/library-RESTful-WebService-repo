package gr.ioannidis.library.security;

import gr.ioannidis.library.security.db.model.UserToken;


public interface IUserTokenHandler {

	UserToken create(String username);
	
	void refresh(UserToken token);
}
