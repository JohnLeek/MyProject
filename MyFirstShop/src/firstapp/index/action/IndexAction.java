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

	//��ҳһ������Ĳ�ѯ ��Ҫ��ע��һ�������CategoryService
	private CategoryService categoryService;
	//ע����Ʒ��service
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//������ҳ�ķ���
	public String execute(){
		//��ѯһ������
		List<Category> cList = categoryService.findAll(); 
		//����session�ķ�Χ
		ActionContext.getContext().getSession().put("cList", cList);
		//��ѯ������Ʒ       ����ֵջ
		List<Product> hList = productService.findHot();
		ActionContext.getContext().getValueStack().set("hList", hList);
		//��ѯ������Ʒ ����ֵջ
		List<Product> nList = productService.findNew();
		ActionContext.getContext().getValueStack().set("nList", nList);
		return "index";
	}
	
	
}
