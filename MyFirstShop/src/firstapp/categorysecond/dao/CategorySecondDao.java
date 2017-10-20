package firstapp.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import firstapp.categorysecond.vo.CategorySecond;
import firstapp.utils.PageHibernateCallback;

public class CategorySecondDao extends HibernateDaoSupport{

	//二级分类的总个数
	public int findCount() {
		String hql = "select count(*) from CategorySecond";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//二级分类带分页的查询
	public List<CategorySecond> fingAllByPage(int begin, int limit) {
		String hql = "from CategorySecond order by csid desc";
		List<CategorySecond> list = this.getHibernateTemplate().execute(new PageHibernateCallback<CategorySecond>(hql, null, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	//保存添加的二级分类
	public void save(CategorySecond categorySecond) {

		this.getHibernateTemplate().save(categorySecond);
	}
	//先查询再删除二级分类
	public CategorySecond findByCsid(Integer csid) {

		return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}
	//删除二级分类
	public void delete(CategorySecond categorySecond) {
		this.getHibernateTemplate().delete(categorySecond);
		
	}
	//修改二级分类
	public void update(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);
	}
	//查询所有的二级分类
	public List<CategorySecond> findAll() {
		String hql = "from CategorySecond";
		return this.getHibernateTemplate().find(hql);
	}

}
