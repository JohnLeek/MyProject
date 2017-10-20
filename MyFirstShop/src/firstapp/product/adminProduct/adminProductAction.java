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
	//ע��product��service
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
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
	//��ҳ��ѯ���е���Ʒ
	public String findAllByPage(){
		PageBean<Product> pageBean = productService.findAllByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAllByPage";
	}
	//��ת�����ҳ��
	//ע���������
	private CategorySecondService categorySecondService; 
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	public String addPage(){
		//����������м��������ѯ��������  ��ע��
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPageSuccess";
	}
	
	//��̨�����Ʒ����ķ��� һ���ϴ���ƷͼƬ
	private File upload;//�ϴ����ļ�
	private String uploadFileName;//�ϴ����ļ���
	private String uploadContextType;//�ϴ��ļ���MIME��ʽ�������ļ��ĸ�ʽ  �ṩ����������set����
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
			//����ļ��ϴ���·��
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			//����һ���ļ�
			File diskFile = new File(realPath+"//"+uploadFileName);
			//�ļ��ϴ�
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products"+"/"+uploadFileName);
		}
		productService.save(product);
		return "saveSuccess";
	}
	
	//��̨ɾ����Ʒ
	public String delete(){
		//�Ȳ�ѯ��Ʒ
		product = productService.findByPid(product.getPid());
		
		String path = product.getImage();
		if(path != null){
			//ɾ���ϴ���ͼƬ
			String realPath = ServletActionContext.getServletContext().getRealPath("/"+path);
			File file = new File(realPath);
			file.delete();
			//�ϴ�
		}
		productService.delete(product);
		return "deleteSuccess";
	}
	
	//�޸���Ʒ  ��ѯ�������� ����      ��Ʒ
	public String edit(){
		//����pid��ѯ��Ʒ
		product = productService.findByPid(product.getPid());
		//��ѯ���еĶ�������
		List<CategorySecond> csList = categorySecondService.findAll();
		//����ֵջ
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "editSuccess";
	}
	//�޸�
	public String update() throws IOException{
		product.setPdate(new Date());
		//��ɶ��ϴ�ͼƬ�Ĳ��� ɾ��   �ϴ�
		if(upload != null){
			//ɾ��
			String path = product.getImage();
			File file = new File(ServletActionContext.getServletContext().getRealPath("/"+path));
			file.delete();
			//�޸��ϴ���ͼƬ
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			//������վ�Ĵ����ļ�
			File diskFile = new File(realPath+"//"+uploadFileName);
			//���ϴ����ļ����Ƶ���վ������
			FileUtils.copyFile(upload, diskFile);
			//���浽���ݿ�
			product.setImage("products"+"/"+uploadFileName);
		}
		productService.update(product);
		return "updateSuccess";
	}
	
	
	
	
	
	
}
