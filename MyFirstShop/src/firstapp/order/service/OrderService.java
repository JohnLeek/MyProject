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
	//���涩��
	public void save(Order order) {
		orderDao.save(order);
	}
	public PageBean<Order> findByUid(Integer uid, Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//����ÿҳ�ļ�¼��
		Integer limit = 5;
		pageBean.setLimit(limit);
		//�����ܼ�¼��
		Integer totalCount = null;
		totalCount = orderDao.findByCountUid(uid);
		pageBean.setTotalCount(totalCount);
		//������ҳ��
		Integer totalPage = null;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}
		else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//����ÿҳ��ʾ�����ݼ���
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
		//ҳ��
		pageBean.setPage(page);
		//ÿҳ��ʾ�ļ�¼��
		int limit = 10;
		pageBean.setLimit(limit);
		//�ܼ�¼��
		int totalCount = 0;
		totalCount = orderDao.findByCount();
		pageBean.setTotalCount(totalCount);
		//��ҳ��
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit; 
		}
		else{
			totalPage = totalCount /limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//��ʼ
		int begin = (page-1)*limit;
		//order����
		List<Order> list = orderDao.findAllByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	
	//���ݶ���id��ѯ������
	public List<OrderItem> findOrderItem(Integer oid) {
		return orderDao.findOrderItem(oid);
	}
	//�޸Ķ���״̬
	public void updateState(Order order) {
		orderDao.updateState(order);
	}
}
