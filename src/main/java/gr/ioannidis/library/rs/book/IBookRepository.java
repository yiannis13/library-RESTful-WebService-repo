package gr.ioannidis.library.rs.book;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface IBookRepository extends CrudRepository<Book, String> {
	
	// Spring Data JPA will implement this method for me!
	public List<Book> findByCategoryGenre(String genre);
}
