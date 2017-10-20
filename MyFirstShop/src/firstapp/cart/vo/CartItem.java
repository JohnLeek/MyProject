package firstapp.cart.vo;

import firstapp.product.vo.Product;

public class CartItem {
	
	private Product product;//商品的实例，购物车中的一项
	private int count;//购买的商品的总数
	private double subtotal;//一条商品的总价

	public Product getProduct() {
		return product;
	}
	public double getSubtotal() {
		return count * product.getShop_price();
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	
}
