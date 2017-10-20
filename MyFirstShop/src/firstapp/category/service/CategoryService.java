package firstapp.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import firstapp.category.dao.CategoryDao;
import firstapp.category.vo.Category;

@Transactional
public class CategoryService {

	//×¢Èëdao
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	public void save(Category category) {

		categoryDao.save(category);
	}

	public Category findByCid(Integer cid) {
		return categoryDao.findByCid(cid);
	}

	public void delete(Category category) {

		categoryDao.delete(category);
	}

	public void update(Category category) {

		categoryDao.update(category);
	}

	
}
