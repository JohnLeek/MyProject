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

		//����ҳ�Ĳ�ѯ
	public PageBean<CategorySecond> findAllByPage(Integer page) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		//��ǰ��ҳ��
		pageBean.setPage(page);
		//ÿҳ��ʾ�ļ�¼��
		int limit = 10;
		pageBean.setLimit(limit);
		//�ܵļ�¼��
		int totalCount = categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		//��ҳ��
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}
		else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		int begin = (page - 1)*limit;
		//ÿҳ��ʾ�����ݼ���
		List<CategorySecond> list = categorySecondDao.fingAllByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
		//������ӵĶ�������
	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
		
	}
	//�Ȳ�ѯ��ɾ����������
	public CategorySecond findByCsid(Integer csid) {

		return categorySecondDao.findByCsid(csid);
	}
	//ɾ����������
	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
	}
	//�޸ĵķ���
	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}

	public List<CategorySecond> findAll() {
		return categorySecondDao.findAll();
	}

	
}
