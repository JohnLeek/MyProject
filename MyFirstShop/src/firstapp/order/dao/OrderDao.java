package firstapp.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import firstapp.order.vo.Order;
import firstapp.order.vo.OrderItem;
import firstapp.utils.PageHibernateCallback;

public class OrderDao extends HibernateDaoSupport{

	//保存订单
	public void save(Order order) {
		this.getHibernateTemplate().save(order);
	}
	//查询用户总订单的方法
	public Integer findByCountUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, uid);
		if(list != null && list.size()>0){
			return list.get(0).intValue();
		}
		return null;
	}
	//订单的分页查询
	public List<Order> findByUid(Integer uid, Integer begin, Integer limit) {
		String hql = "from Order o where o.user.uid = ? order by o.ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, new Object[] { uid },
						begin, limit));
		if(list != null && list.size()>0){
			return list;
		}
		return null;
	}
	public Order findByOid(Integer oid) {
		return this.getHibernateTemplate().get(Order.class, oid);
	}
	public void update(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);
	}
	
	//后台查询总的订单数
	public int findByCount() {
		String hql = "select count(*) from Order";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//分页查询
	public List<Order> findAllByPage(int begin, int limit) {
		String hql = "from Order order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, null, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	//订单id查询订单项
	public List<OrderItem> findOrderItem(Integer oid) {
		String hql = "from OrderItem oi where oi.order.oid = ?";
		List<OrderItem> list = this.getHibernateTemplate().find(hql, oid);
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	//修改订单状态
	public void updateState(Order order) {
		this.getHibernateTemplate().update(order);
	}

}
