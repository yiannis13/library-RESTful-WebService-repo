package gr.ioannidis.library.rs.book;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import gr.ioannidis.library.rs.category.Category;
import gr.ioannidis.library.rs.exception.BadRequestException;
import gr.ioannidis.library.rs.exception.ResourceNotFoundException;

@RestController
public class BookRestController {

	@Autowired
	private BookService bookService;

	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBooks(@RequestHeader("Authorization") String token) { // it's necessary!!!
		List<Book> books = bookService.getBooks();
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}

	@GetMapping(value = "categories/{genre}/books")
	public ResponseEntity<List<Book>> getAllBooksByGenre(@RequestHeader("Authorization") String token, @PathVariable String genre) {
		List<Book> books = bookService.getBooksByCategoryGenre(genre);
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}

	@PostMapping("categories/{genre}/books")
	public ResponseEntity<Book> addBook(@RequestHeader("Authorization") String token, @PathVariable String genre, @RequestBody Book book, UriComponentsBuilder ucb) {
		if ((book.getIsbn() == null) || (book.getTitle() == null) || (book.getDescription() == null)) {
			throw new BadRequestException("The book object was not properly sent");
		}
		// The user does not need to pass category information within the json request.
		// We are creating this information here (only the id is needed)
		book.setCategory(new Category(genre, ""));
		bookService.addBook(book);
		book = bookService.getBook(book.getIsbn());
		HttpHeaders headers = new HttpHeaders();
		// headers.add("myHeaderName", "myHeaderValue");
		URI uri = ucb.path("categories/genre/books/" + book.getIsbn()).build().toUri();
		headers.setLocation(uri);
		return new ResponseEntity<Book>(book, headers, HttpStatus.CREATED);
	}

	@GetMapping("categories/{genre}/books/{isbn}")
	public ResponseEntity<Book> getBook(@RequestHeader("Authorization") String token, @PathVariable String isbn) {
		Book book = bookService.getBook(isbn);
		if (book == null) {
			throw new ResourceNotFoundException("The book was not found");
		}
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}

	@PutMapping("/categories/{genre}/books/{isbn}")
	public ResponseEntity<?> updateBook(@RequestHeader("Authorization") String token, @PathVariable String genre, @RequestBody Book book) {
		if ((book.getIsbn() == null) || (book.getTitle() == null) || (book.getDescription() == null)) {
			throw new BadRequestException("The book object was not properly sent");
		}
		book.setCategory(new Category(genre, ""));
		// I don't need to grab the PathVariable "isbn". Spring Framework has
		// the intelligence to know what book to update
		bookService.updateBook(book);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/categories/{genre}/books/{isbn}")
	public ResponseEntity<?> deleteBook(@RequestHeader("Authorization") String token, @PathVariable String isbn) {
		if (bookService.getBook(isbn) == null) {
			throw new ResourceNotFoundException("The book you want to delete was not found");
		}
		bookService.deleteBook(isbn);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
