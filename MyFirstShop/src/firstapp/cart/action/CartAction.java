package firstapp.cart.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import firstapp.cart.vo.Cart;
import firstapp.cart.vo.CartItem;
import firstapp.product.service.ProductService;
import firstapp.product.vo.Product;

public class CartAction extends ActionSupport{
	
	//接收pid  count
	private Integer pid;
	private Integer count;
	//注入到商品 的service
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String addCart(){
		//添加到购物项
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		Product product = productService.findByPid(pid);
		cartItem.setProduct(product);
		//添加到购物车  购物车 存放到session里边
		Cart cart = getCart();//此方法是从session中获得一个cart对象
		cart.addCart(cartItem);
		return "addCart";
	}
	//清空购物车的方法
	public String clearCart(){
		Cart cart = getCart();
		cart.cleanCart();
		return "clearCart";
	}
	//删除购物车中一项的方法
	public String removeCart(){
		Cart cart = getCart();
		cart.removeCart(pid);
		return "removeCart";
	}
	//跳转到我的购物车
	public String myCart(){
		return "myCart";
	}
	
	//从session中获得购物车
	private Cart getCart(){
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null){
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
}
