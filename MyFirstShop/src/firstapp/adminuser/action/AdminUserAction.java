package firstapp.adminuser.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import firstapp.adminuser.service.AdminUserService;
import firstapp.adminuser.vo.AdminUser;

public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{
	
	private AdminUserService adminUserService;//ע��Service
	//����ǰ̨���˺�����
//	private String username;
//	private String password;
//	public void setPassword(String password) {
//		this.password = password;
//	}
	private AdminUser adminUser = new AdminUser();
	@Override
	public AdminUser getModel() {
		return adminUser;
	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	//����Ա��¼�ķ���
	public String login(){
		
		AdminUser existAdminUser = adminUserService.login(adminUser);
		if(existAdminUser == null){
			this.addActionError("�˺Ż���������");
			return "loginFail";
		}
		else{
			ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
			return "loginSuccess";
		}
		
	}

	
	

}
