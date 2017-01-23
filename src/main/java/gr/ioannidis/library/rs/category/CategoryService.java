package gr.ioannidis.library.rs.category;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
	
	@Autowired
	private ICategoryRepository categoryRepository;

	public List<Category> getCategories() {
		List<Category> categories = new ArrayList<>();
		categoryRepository.findAll().forEach(c -> categories.add(c));
		return categories;
	}
	
	public Category getCategory(String genre) {
		return categoryRepository.findOne(genre);
	}
	
	public void addCategory(Category category) {
		categoryRepository.save(category);
	}
	
	public void updateCategory(Category category) {
		categoryRepository.save(category);
	}
	
	public void deleteCategory(String genre) {
		categoryRepository.delete(genre);
	} 
	
}
