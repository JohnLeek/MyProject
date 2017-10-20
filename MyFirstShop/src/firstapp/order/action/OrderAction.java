package firstapp.order.action;



import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import firstapp.cart.vo.Cart;
import firstapp.cart.vo.CartItem;
import firstapp.order.service.OrderService;
import firstapp.order.vo.Order;
import firstapp.order.vo.OrderItem;
import firstapp.user.vo.User;
import firstapp.utils.PageBean;
import firstapp.utils.PaymentUtil;

public class OrderAction extends ActionSupport implements ModelDriven<Order>{

	private Order order = new Order();
	private OrderService orderService;
	private Integer page;
	//接收银行通道的编号
	private String pd_FrpId;
	//接收支付后的 参数
	private String r6_Order;
	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}
	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}
	private String r3_Amt;
	
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	@Override
	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}

	public String saveOrder(){
		//1保存数据到数据库
		//补全订单的数据
		
		//添加购物车中的信息到订单 购物车存放到session中 直接获取就行
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null){
			this.addActionMessage("亲您还没有购物，先去逛逛吧");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		order.setState(1); //1 未付款  2 付款了未发货 3  发货没有确认收货 4 交易完成 
		order.setOrdertime(new Date());
		//将用户放入到订单中
				User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
				if(existUser == null){
					this.addActionMessage("亲您还没有登录，请先去登录");
					return "login";
				}
		//设置的订单项   里边包含了购物项的数量 总计
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			//将订单项放入订单中
			order.getOrderItems().add(orderItem);
			
		}
		order.setUser(existUser);
		orderService.save(order);
		//将数据显示到前台页面  通过值栈的方式  由于实现了模型驱动那么order就在栈顶
		//保存完毕清空购物车
		cart.cleanCart();
		return "saveOrder";
	}
	//根据用id查询订单
	public String findByUid(){
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		Integer uid = existUser.getUid();
		PageBean<Order>pageBean = orderService.findByUid(uid,page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUidSuccess";
	}
	//根据订单的编号查询订单  oid存放在模型驱动里边
	public String findByOid(){
		order = orderService.findByOid(order.getOid());
		return "findByOidSuccess";
	}
	//付款的方法
	public String payOrder() throws IOException{
		//根据订单的编号先修改订单
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		//修改订单
		orderService.update(currOrder);
		//付款的参数
		String p0_Cmd = "Buy"; 
		String p1_MerId = "10001126856";
		String p2_Order = order.getOid().toString();
		String p3_Amt = "0.01"; 
		String p4_Cur = "CNY"; 
		String p5_Pid = ""; 
		String p6_Pcat = ""; 
		String p7_Pdesc = ""; 
		String p8_Url = "http://localhost:8080/MyFirstShop/order_callBack.action"; 
		String p9_SAF = ""; 
		String pa_MP = ""; 
		String pd_FrpId = this.pd_FrpId;
		String pr_NeedResponse = "1"; 
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue); 
		
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		//付款
		ServletActionContext.getResponse().sendRedirect(sb.toString());
		return NONE;
	}
	//支付成功后的重定向的方法
	public String callBack(){
		// 修改订单的状态
		Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		currOrder.setState(2);
		orderService.update(currOrder);
		//显示支付成功后的信息
		this.addActionMessage("付款成功订单编号为："+r6_Order+"金额："+r3_Amt);
		return "msg";
	}
	
	//用户修改订单的方法
	public String updateState(){
		//先根据oid查询
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(4);
		//修改
		orderService.update(currOrder);;
		return "updateStateSuccess";
	}
	
	
	
	
}
