package firstapp.product.adminProduct;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import firstapp.categorysecond.service.CategorySecondService;
import firstapp.categorysecond.vo.CategorySecond;
import firstapp.product.service.ProductService;
import firstapp.product.vo.Product;
import firstapp.utils.PageBean;

public class adminProductAction extends ActionSupport implements ModelDriven<Product>{

	private Product product = new Product();
	//注入product的service
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
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
	//分页查询所有的商品
	public String findAllByPage(){
		PageBean<Product> pageBean = productService.findAllByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAllByPage";
	}
	//跳转到添加页面
	//注入二级分类
	private CategorySecondService categorySecondService; 
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	public String addPage(){
		//与二级分类有级联，需查询二级分类  先注入
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPageSuccess";
	}
	
	//后台添加商品保存的方法 一级上传商品图片
	private File upload;//上传的文件
	private String uploadFileName;//上传的文件名
	private String uploadContextType;//上传文件的MIME格式，就是文件的格式  提供三个参数的set方法
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}
	
	public String save() throws IOException{
		product.setPdate(new Date());
		if(upload != null){
			//获得文件上传的路径
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			//创建一个文件
			File diskFile = new File(realPath+"//"+uploadFileName);
			//文件上传
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products"+"/"+uploadFileName);
		}
		productService.save(product);
		return "saveSuccess";
	}
	
	//后台删除商品
	public String delete(){
		//先查询商品
		product = productService.findByPid(product.getPid());
		
		String path = product.getImage();
		if(path != null){
			//删除上传的图片
			String realPath = ServletActionContext.getServletContext().getRealPath("/"+path);
			File file = new File(realPath);
			file.delete();
			//上传
		}
		productService.delete(product);
		return "deleteSuccess";
	}
	
	//修改商品  查询二级分类 集合      商品
	public String edit(){
		//根据pid查询商品
		product = productService.findByPid(product.getPid());
		//查询所有的二级分类
		List<CategorySecond> csList = categorySecondService.findAll();
		//存入值栈
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "editSuccess";
	}
	//修改
	public String update() throws IOException{
		product.setPdate(new Date());
		//完成对上传图片的操作 删除   上传
		if(upload != null){
			//删除
			String path = product.getImage();
			File file = new File(ServletActionContext.getServletContext().getRealPath("/"+path));
			file.delete();
			//修改上传的图片
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			//创建网站的磁盘文件
			File diskFile = new File(realPath+"//"+uploadFileName);
			//将上传的文件复制到网站磁盘中
			FileUtils.copyFile(upload, diskFile);
			//保存到数据库
			product.setImage("products"+"/"+uploadFileName);
		}
		productService.update(product);
		return "updateSuccess";
	}
	
	
	
	
	
	
}
