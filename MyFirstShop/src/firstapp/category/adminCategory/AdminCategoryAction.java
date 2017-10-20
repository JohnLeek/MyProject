package firstapp.category.adminCategory;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import firstapp.category.service.CategoryService;
import firstapp.category.vo.Category;

//��̨һ����������action			
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{

	private Category category = new Category();
	private CategoryService categoryService;
	
	public void setCategotyService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	public Category getModel() {
		return category;
	}

		//��ѯ���е�һ������ķ���
	public String findAll(){
		//����service��ѯ���е�һ������
		List<Category> cList = categoryService.findAll();
		//��ʾ��ҳ����
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}
	//���һ������ķ���
	public String save(){
		//����service����
		categoryService.save(category);
		//ҳ����ת
		return "saveSuccess";
	}
	//ɾ��һ�ڷ���
	public String delete(){
		//ģ����������cid��ɾ��һ������ͬʱɾ���������࣬Ҫ�Ȳ�ѯ��ɾ��
		category = categoryService.findByCid(category.getCid());
		//ɾ��
		categoryService.delete(category);
		return "deleteSuccess";
	}
	//�༭һ������
	public String edit(){
		//�����Ȳ�ѯ��Ȼ�����޸�
		category = categoryService.findByCid(category.getCid());
		return "editSuccess";
	}
	//�޸�
	public String update(){
		categoryService.update(category);
		return "updateSuccess";
	}
	
	
	
	
	
}
