package firstapp.order.adminOrder;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import firstapp.order.service.OrderService;
import firstapp.order.vo.Order;
import firstapp.order.vo.OrderItem;
import firstapp.utils.PageBean;

public class adminOrderAction extends ActionSupport implements ModelDriven<Order>{

	private Order order = new Order();
	//ע��order��service
	private OrderService orderService;
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	@Override
	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}
	//��ѯ���еĶ���  ������ҳ��
	//����page
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	public String findAllByPage(){
		PageBean<Order> pageBean = orderService.findAllByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAllByPage";
	}
	//	ajax ����oid ��ѯ������
	public String findOrderItem(){
		//��ѯ
		List<OrderItem> list = orderService.findOrderItem(order.getOid());
		//��ʾ��ҳ����
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}
	
	//�޸Ķ���״̬
	public String updateState(){
		//1���ݶ���id�Ȳ�ѯ
		Order currOrder = orderService.findByOid(order.getOid());
		//2�޸�
		currOrder.setState(3);
		orderService.updateState(currOrder);
		//3��תҳ��
		return "updateStateSuccess";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
