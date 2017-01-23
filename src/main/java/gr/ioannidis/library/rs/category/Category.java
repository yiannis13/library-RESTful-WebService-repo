package gr.ioannidis.library.rs.category;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {
	
	@Id
	private String genre;
	private String description;
	
	public Category() {
		
	}

	public Category(String genre, String description) {
		this.genre = genre;
		this.description = description;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
}
