package firstapp.category.adminCategory;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import firstapp.category.service.CategoryService;
import firstapp.category.vo.Category;

//后台一级分类管理的action			
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

		//查询所有的一级分类的方法
	public String findAll(){
		//调用service查询所有的一级分类
		List<Category> cList = categoryService.findAll();
		//显示到页面上
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}
	//添加一级分类的方法
	public String save(){
		//调用service保存
		categoryService.save(category);
		//页面跳转
		return "saveSuccess";
	}
	//删除一节分类
	public String delete(){
		//模型驱动接收cid，删除一级分类同时删除二级分类，要先查询再删除
		category = categoryService.findByCid(category.getCid());
		//删除
		categoryService.delete(category);
		return "deleteSuccess";
	}
	//编辑一级分类
	public String edit(){
		//还是先查询，然后再修改
		category = categoryService.findByCid(category.getCid());
		return "editSuccess";
	}
	//修改
	public String update(){
		categoryService.update(category);
		return "updateSuccess";
	}
	
	
	
	
	
}
