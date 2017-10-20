package firstapp.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import firstapp.product.vo.Product;
import firstapp.utils.PageHibernateCallback;

public class ProductDao extends HibernateDaoSupport{

	public List<Product> findHot() {
		//分页查询 离线条件写法
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//限制条件 hot=1
		criteria.add(Restrictions.eq("is_hot", 1));
		//倒序输出
		criteria.addOrder(Order.desc("pdate"));
		//查询
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}

	public List<Product> findNew() {
		//分页查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//倒序查询
		criteria.addOrder(Order.desc("pdate"));
		//查询
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria,0,10);
		return list;
	}
	//查询商品详情
	public Product findByPid(Integer pid) {
		//查询
		return this.getHibernateTemplate().get(Product.class, pid);
	}
	//根据一级分类的id来查询包含的商品个数
	public int findCountCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql,cid);
		if(list != null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//多表查询 关键字是外键 hql是实体类的属性
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	public int findCountCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql,csid);
		if(list != null && list.size()>0){
			return  list.get(0).intValue();
		}
		return 0;
	}
	//左侧二级分类查询 右侧的商品信息 带分页
	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
	
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	public int findCount() {
		String hql = "select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Product> findAllByPage(int begin, int limit) {
		String hql = "from Product order by pdate desc";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, null, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	//保存后台商品
	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}
	//删除后台的商品
	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
	}
	//修改商品
	public void update(Product product) {
		this.getHibernateTemplate().update(product);
	}

	

}
