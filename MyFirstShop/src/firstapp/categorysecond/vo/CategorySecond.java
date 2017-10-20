package firstapp.categorysecond.vo;

import java.util.HashSet;
import java.util.Set;

import firstapp.category.vo.Category;
import firstapp.product.vo.Product;

public class CategorySecond {

	private Integer csid;
	private String csname;
	//一级分类的外键   这里是实体类
	private Category category;
	//商品的集合
	private Set<Product> products = new HashSet<Product>();
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public String getCsname() {
		return csname;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}
	
}
