package gr.ioannidis.library.security.db.controller;

import org.springframework.data.repository.CrudRepository;
import gr.ioannidis.library.security.db.model.UserToken;


public interface IUserTokenRepository extends CrudRepository<UserToken, Integer> {

	public UserToken findByUserUsername(String username);
	
	public UserToken findByTokenValue(String tokenValue);
}
