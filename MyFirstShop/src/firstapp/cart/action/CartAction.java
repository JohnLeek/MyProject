package firstapp.cart.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import firstapp.cart.vo.Cart;
import firstapp.cart.vo.CartItem;
import firstapp.product.service.ProductService;
import firstapp.product.vo.Product;

public class CartAction extends ActionSupport{
	
	//����pid  count
	private Integer pid;
	private Integer count;
	//ע�뵽��Ʒ ��service
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
		//��ӵ�������
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		Product product = productService.findByPid(pid);
		cartItem.setProduct(product);
		//��ӵ����ﳵ  ���ﳵ ��ŵ�session���
		Cart cart = getCart();//�˷����Ǵ�session�л��һ��cart����
		cart.addCart(cartItem);
		return "addCart";
	}
	//��չ��ﳵ�ķ���
	public String clearCart(){
		Cart cart = getCart();
		cart.cleanCart();
		return "clearCart";
	}
	//ɾ�����ﳵ��һ��ķ���
	public String removeCart(){
		Cart cart = getCart();
		cart.removeCart(pid);
		return "removeCart";
	}
	//��ת���ҵĹ��ﳵ
	public String myCart(){
		return "myCart";
	}
	
	//��session�л�ù��ﳵ
	private Cart getCart(){
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null){
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
}
