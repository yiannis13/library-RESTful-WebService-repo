package gr.ioannidis.library.security.db.controller;

import org.springframework.data.repository.CrudRepository;
import gr.ioannidis.library.security.db.model.User;


public interface IUserRepository extends CrudRepository<User, Integer> {
	
	public User findByUsername(String username);
}
