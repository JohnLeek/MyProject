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
	//注入order的service
	private OrderService orderService;
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	@Override
	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}
	//查询所有的订单  带带分页的
	//接收page
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	public String findAllByPage(){
		PageBean<Order> pageBean = orderService.findAllByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAllByPage";
	}
	//	ajax 根据oid 查询订单项
	public String findOrderItem(){
		//查询
		List<OrderItem> list = orderService.findOrderItem(order.getOid());
		//显示到页面上
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}
	
	//修改订单状态
	public String updateState(){
		//1根据订单id先查询
		Order currOrder = orderService.findByOid(order.getOid());
		//2修改
		currOrder.setState(3);
		orderService.updateState(currOrder);
		//3跳转页面
		return "updateStateSuccess";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
