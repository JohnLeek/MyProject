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
	//ע��service
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	//����cid
	private Integer cid;
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	//����csid
	private Integer csid;
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	//ע��һ�������service
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//����ҳ��
	private int page;
	public void setPage(int page) {
		this.page = page;
	}
	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	//��ҳ��Ʒ�������ѯ����
	public String findByPid(){
		//ģ����������ֱ�Ӵ���ҳ����ȥ    
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}
	//����menu������cid��ѯ��ർ���� ��session����У�һ���������hbm�ļ�Ҳ����������û��дд���ұߵģ��Ҳ���Ʒ��Ϣ
	public String findByCid(){
		PageBean<Product> pageBean = productService.findByPageCid(cid,page );
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	
	//�����������ѯ �Ҳ����Ʒ��Ϣ ����ҳ
	public String findByCsid(){
		PageBean<Product> pageBean = productService.findByPageCsid(csid,page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
	
	
	
	
	
	
	
	
	
	
	
}
