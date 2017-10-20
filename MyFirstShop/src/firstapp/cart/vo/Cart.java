package firstapp.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart implements Serializable{

	//购物项 
	private Map<Integer,CartItem> map = new LinkedHashMap<Integer, CartItem>();
	//总金额
	private double total;
	
	//将map转化成单列的集合
	public Collection<CartItem> getCartItems(){
		
		return map.values();
	}
	
	
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	//购物车的功能   添加    移除  清空
			//添加到购物车
		public void addCart(CartItem cartItem){
			//判断是否含有该商品 有 数量价格变化  没有添加到购物车中
			Integer pid = cartItem.getProduct().getPid();
			
			if(map.containsKey(pid)){
				//含有
				CartItem _cartItem = map.get(pid); //获得购物车中原来的商品项
				_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
			}
			else{
				//不含有
				map.put(pid, cartItem);
			}
			//设置合计的值
			total += cartItem.getSubtotal();
		}
		//移除
		public void removeCart(Integer pid){
			//移除
			CartItem cartItem = map.remove(pid);
			//价格变化 减去移除的价格
			total -= cartItem.getSubtotal();
		}
		//清空
		public void cleanCart(){
			map.clear();//清空购物车
			total = 0;//总计为0
		}
	
}
