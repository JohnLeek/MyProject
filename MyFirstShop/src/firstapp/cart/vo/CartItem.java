package firstapp.cart.vo;

import firstapp.product.vo.Product;

public class CartItem {
	
	private Product product;//��Ʒ��ʵ�������ﳵ�е�һ��
	private int count;//�������Ʒ������
	private double subtotal;//һ����Ʒ���ܼ�

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
