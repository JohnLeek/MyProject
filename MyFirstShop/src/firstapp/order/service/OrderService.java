package firstapp.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import firstapp.order.dao.OrderDao;
import firstapp.order.vo.Order;
import firstapp.order.vo.OrderItem;
import firstapp.utils.PageBean;

@Transactional
public class OrderService {
 
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	//保存订单
	public void save(Order order) {
		orderDao.save(order);
	}
	public PageBean<Order> findByUid(Integer uid, Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页的记录数
		Integer limit = 5;
		pageBean.setLimit(limit);
		//设置总记录数
		Integer totalCount = null;
		totalCount = orderDao.findByCountUid(uid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		Integer totalPage = null;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}
		else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//设置每页显示的数据集合
		Integer begin = (page - 1)*limit;
		List<Order> list = orderDao.findByUid(uid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}
	public void update(Order currOrder) {
		orderDao.update(currOrder);
		
	}
	public PageBean<Order> findAllByPage(Integer page) {
		PageBean<Order> pageBean = new PageBean();
		//页数
		pageBean.setPage(page);
		//每页显示的记录数
		int limit = 10;
		pageBean.setLimit(limit);
		//总记录数
		int totalCount = 0;
		totalCount = orderDao.findByCount();
		pageBean.setTotalCount(totalCount);
		//总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit; 
		}
		else{
			totalPage = totalCount /limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//起始
		int begin = (page-1)*limit;
		//order集合
		List<Order> list = orderDao.findAllByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	
	//根据订单id查询订单项
	public List<OrderItem> findOrderItem(Integer oid) {
		return orderDao.findOrderItem(oid);
	}
	//修改订单状态
	public void updateState(Order order) {
		orderDao.updateState(order);
	}
}
