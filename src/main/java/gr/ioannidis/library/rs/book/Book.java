package gr.ioannidis.library.rs.book;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import gr.ioannidis.library.rs.category.Category;

@Entity
public class Book {
	
	@Id
	private String isbn;
	private String title;
	private String description;
	@ManyToOne
	private Category category;
	
	public Book() {
		
	}

	public Book(String isbn, String title, String description) {
		this.isbn = isbn;
		this.title = title;
		this.description = description;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	
}
