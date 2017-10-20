package firstapp.index.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import firstapp.category.service.CategoryService;
import firstapp.category.vo.Category;
import firstapp.product.service.ProductService;
import firstapp.product.vo.Product;

public class IndexAction extends ActionSupport{

	//首页一级分类的查询 需要先注入一级分类的CategoryService
	private CategoryService categoryService;
	//注入商品的service
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//访问首页的方法
	public String execute(){
		//查询一级分类
		List<Category> cList = categoryService.findAll(); 
		//存入session的范围
		ActionContext.getContext().getSession().put("cList", cList);
		//查询热门商品       放入值栈
		List<Product> hList = productService.findHot();
		ActionContext.getContext().getValueStack().set("hList", hList);
		//查询最新商品 放入值栈
		List<Product> nList = productService.findNew();
		ActionContext.getContext().getValueStack().set("nList", nList);
		return "index";
	}
	
	
}
