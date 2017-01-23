package gr.ioannidis.library.rs.category;

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
import gr.ioannidis.library.rs.exception.BadRequestException;
import gr.ioannidis.library.rs.exception.ResourceNotFoundException;


@RestController
public class CategoryRestController {

	@Autowired
	private CategoryService categoryService;


	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getAllCategories(@RequestHeader("Authorization") String token) {
		return new ResponseEntity<List<Category>>(categoryService.getCategories(), HttpStatus.OK);
	}

	@PostMapping("/categories")
	public ResponseEntity<Category> addCategory(@RequestHeader("Authorization") String token, @RequestBody Category category, UriComponentsBuilder ucb) {
		if ((category.getGenre() == null) || (category.getDescription() == null)) {
			throw new BadRequestException("The category object was not properly sent");
		}
		categoryService.addCategory(category);
		URI uri = ucb.path("/categories/" + category.getGenre()).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);
		return new ResponseEntity<Category>(category, headers, HttpStatus.CREATED);
	}

	@GetMapping("/categories/{genre}")
	public ResponseEntity<Category> getCategory(@RequestHeader("Authorization") String token, @PathVariable String genre) {
		Category category = categoryService.getCategory(genre);
		if (category == null) {
			throw new ResourceNotFoundException("The category was not found");
		}
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}

	@PutMapping("/categories/{genre}")
	public ResponseEntity<?> updateCategory(@RequestHeader("Authorization") String token, @PathVariable String genre, @RequestBody Category category) {
		if ((category.getGenre() == null) || (category.getDescription() == null)) {
			throw new BadRequestException("The category object was not properly sent");
		}
		categoryService.updateCategory(category);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/categories/{genre}")
	public ResponseEntity<?> deleteCategory(@RequestHeader("Authorization") String token, @PathVariable String genre) {
		if (categoryService.getCategory(genre) == null) {
			throw new ResourceNotFoundException("The category was not found");
		}
		categoryService.deleteCategory(genre);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
