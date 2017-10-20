package firstapp.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart implements Serializable{

	//������ 
	private Map<Integer,CartItem> map = new LinkedHashMap<Integer, CartItem>();
	//�ܽ��
	private double total;
	
	//��mapת���ɵ��еļ���
	public Collection<CartItem> getCartItems(){
		
		return map.values();
	}
	
	
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	//���ﳵ�Ĺ���   ���    �Ƴ�  ���
			//��ӵ����ﳵ
		public void addCart(CartItem cartItem){
			//�ж��Ƿ��и���Ʒ �� �����۸�仯  û����ӵ����ﳵ��
			Integer pid = cartItem.getProduct().getPid();
			
			if(map.containsKey(pid)){
				//����
				CartItem _cartItem = map.get(pid); //��ù��ﳵ��ԭ������Ʒ��
				_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
			}
			else{
				//������
				map.put(pid, cartItem);
			}
			//���úϼƵ�ֵ
			total += cartItem.getSubtotal();
		}
		//�Ƴ�
		public void removeCart(Integer pid){
			//�Ƴ�
			CartItem cartItem = map.remove(pid);
			//�۸�仯 ��ȥ�Ƴ��ļ۸�
			total -= cartItem.getSubtotal();
		}
		//���
		public void cleanCart(){
			map.clear();//��չ��ﳵ
			total = 0;//�ܼ�Ϊ0
		}
	
}
