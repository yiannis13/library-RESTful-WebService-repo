package gr.ioannidis.library.rs.book;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	
	@Autowired
	private IBookRepository bookRepository;

	public List<Book> getBooks() {
		List<Book> books = new ArrayList<>();
		bookRepository.findAll().forEach(b -> books.add(b));
		return books;
	}
	
	
	public List<Book> getBooksByCategoryGenre(String genre) {
		return bookRepository.findByCategoryGenre(genre);
	}
	
	public Book getBook(String isbn) {
		return bookRepository.findOne(isbn);
	}
	
	public void addBook(Book book) {
		bookRepository.save(book);
	}
	
	public void updateBook(Book book) {
		bookRepository.save(book);
	}
	
	public void deleteBook(String isbn) {
		bookRepository.delete(isbn);
	} 
	
}
