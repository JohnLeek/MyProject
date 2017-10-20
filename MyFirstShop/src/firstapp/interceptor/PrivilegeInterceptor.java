package firstapp.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import firstapp.adminuser.vo.AdminUser;

//后台权限的拦截器
public class PrivilegeInterceptor extends MethodFilterInterceptor{

	@Override
	//拦截的方法
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		//根据seesion中保存的用户信息进行判断
		AdminUser adminUser = (AdminUser) ServletActionContext.getRequest().getAttribute("existAdminUser");
		if(adminUser == null){
			//没有登录
			ActionSupport actionSupport= (ActionSupport) actionInvocation.getAction();
			actionSupport.addActionError("未登录没有权限访问");
			return "loginFail";
		}
		else{
			//登录的
			return actionInvocation.invoke();
		}
		
	}

	
}
