package gr.ioannidis.library.security.db.controller;

import org.springframework.data.repository.CrudRepository;
import gr.ioannidis.library.security.db.model.UserPassword;


public interface IUserPasswordRepository extends CrudRepository<UserPassword, Integer> {
	
	public UserPassword findByUserUsername(String username);
}
