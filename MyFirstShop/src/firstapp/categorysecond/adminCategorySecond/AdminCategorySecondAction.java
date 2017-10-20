package firstapp.categorysecond.adminCategorySecond;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import firstapp.category.service.CategoryService;
import firstapp.category.vo.Category;
import firstapp.categorysecond.service.CategorySecondService;
import firstapp.categorysecond.vo.CategorySecond;
import firstapp.utils.PageBean;

public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{

	private CategorySecond categorySecond = new CategorySecond();
	//����ҳ��page
	private Integer page;
	//ע��service
	private CategorySecondService categorySecondService;
	
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	@Override
	public CategorySecond getModel() {
		// TODO Auto-generated method stub
		return categorySecond;
	}
	//����ҳ��ѯ��������ķ���
	public String findAllByPage(){
		PageBean<CategorySecond> pageBean = categorySecondService.findAllByPage(page);
		//����ֵջ
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAllByPage";
	}
	//�����������  ע��һ�������service
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public String addPage(){
		//��Ҫ��ѯһ������  ��Ҫע��һ�������service
		//��ѯһ������
		List<Category> cList = categoryService.findAll();
		//����ǰ��ҳ��  ����ֵջ
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPageSuccess";
	}
	//��Ӷ������ౣ�浽���ݿ�
	public String save(){
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}
	//�й����ı���в���Ҫ�Ȳ�ѯ
	//��������ɾ���ķ���
	public String delete(){
		//ɾ��������������м�����һ��������Ҫ�Ȳ�ѯ����hibernate��cascade����
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}
	//�Ȳ�ѯ���޸� �༭��������ķ���
	public String edit(){
		//�Ȳ�ѯ��������Ķ���
		categorySecond  = categorySecondService.findByCsid(categorySecond.getCsid());
		//�ٲ�ѯһ������
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
	}
	public String update(){
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
