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
		//��ҳ��ѯ ��������д��
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//�������� hot=1
		criteria.add(Restrictions.eq("is_hot", 1));
		//�������
		criteria.addOrder(Order.desc("pdate"));
		//��ѯ
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}

	public List<Product> findNew() {
		//��ҳ��ѯ
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//�����ѯ
		criteria.addOrder(Order.desc("pdate"));
		//��ѯ
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria,0,10);
		return list;
	}
	//��ѯ��Ʒ����
	public Product findByPid(Integer pid) {
		//��ѯ
		return this.getHibernateTemplate().get(Product.class, pid);
	}
	//����һ�������id����ѯ��������Ʒ����
	public int findCountCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql,cid);
		if(list != null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//����ѯ �ؼ�������� hql��ʵ���������
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
	//�����������ѯ �Ҳ����Ʒ��Ϣ ����ҳ
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
	//�����̨��Ʒ
	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}
	//ɾ����̨����Ʒ
	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
	}
	//�޸���Ʒ
	public void update(Product product) {
		this.getHibernateTemplate().update(product);
	}

	

}
