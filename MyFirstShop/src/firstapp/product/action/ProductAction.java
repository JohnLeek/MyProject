package firstapp.product.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import firstapp.category.service.CategoryService;
import firstapp.product.service.ProductService;
import firstapp.product.vo.Product;
import firstapp.utils.PageBean;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{

	private Product product = new Product();
	//注入service
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	//接收cid
	private Integer cid;
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	//接收csid
	private Integer csid;
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	//注入一级分类的service
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//接收页数
	private int page;
	public void setPage(int page) {
		this.page = page;
	}
	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	//首页商品的详情查询方法
	public String findByPid(){
		//模型驱动可以直接带到页面上去    
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}
	//根据menu传来的cid查询左侧导航栏 （session里边有，一二级分类的hbm文件也配置了这里没有写写了右边的）右侧商品信息
	public String findByCid(){
		PageBean<Product> pageBean = productService.findByPageCid(cid,page );
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	
	//左侧二级分类查询 右侧的商品信息 带分页
	public String findByCsid(){
		PageBean<Product> pageBean = productService.findByPageCsid(csid,page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
	
	
	
	
	
	
	
	
	
	
	
}
