package firstapp.categorysecond.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import firstapp.categorysecond.dao.CategorySecondDao;
import firstapp.categorysecond.vo.CategorySecond;
import firstapp.utils.PageBean;
@Transactional
public class CategorySecondService {

	private CategorySecondDao categorySecondDao;

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

		//带分页的查询
	public PageBean<CategorySecond> findAllByPage(Integer page) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		//当前的页数
		pageBean.setPage(page);
		//每页显示的记录数
		int limit = 10;
		pageBean.setLimit(limit);
		//总的记录数
		int totalCount = categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		//总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}
		else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		int begin = (page - 1)*limit;
		//每页显示的数据集合
		List<CategorySecond> list = categorySecondDao.fingAllByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
		//保存添加的二级分类
	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
		
	}
	//先查询再删除二级分类
	public CategorySecond findByCsid(Integer csid) {

		return categorySecondDao.findByCsid(csid);
	}
	//删除二级分类
	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
	}
	//修改的方法
	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}

	public List<CategorySecond> findAll() {
		return categorySecondDao.findAll();
	}

	
}
