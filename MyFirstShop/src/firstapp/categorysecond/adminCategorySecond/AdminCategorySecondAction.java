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
	//接收页数page
	private Integer page;
	//注入service
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
	//带分页查询二级分类的方法
	public String findAllByPage(){
		PageBean<CategorySecond> pageBean = categorySecondService.findAllByPage(page);
		//存入值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAllByPage";
	}
	//二级分类添加  注入一级分类的service
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public String addPage(){
		//先要查询一级分类  还要注入一级分类的service
		//查询一级分类
		List<Category> cList = categoryService.findAll();
		//带到前端页面  存入值栈
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPageSuccess";
	}
	//添加二级分类保存到数据库
	public String save(){
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}
	//有关联的表进行操作要先查询
	//二级分类删除的方法
	public String delete(){
		//删除二级分类如果有级联的一级分类需要先查询配置hibernate的cascade属性
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}
	//先查询再修改 编辑二级分类的方法
	public String edit(){
		//先查询二级分类的对象
		categorySecond  = categorySecondService.findByCsid(categorySecond.getCsid());
		//再查询一级分类
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
	}
	public String update(){
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
