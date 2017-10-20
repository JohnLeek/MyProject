package firstapp.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import firstapp.user.service.UserService;
import firstapp.user.vo.User;
//使用模型驱动
public class UserAction extends ActionSupport implements ModelDriven<User>{

	public String registPage(){
		
		return "registPage";
	}
	//接收验证码
	private String checkcode;
	
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private User user  = new User();

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	//ajax 异步校验
	public String findByName() throws IOException{
		User existUser = userService.findByUserName(user.getUsername());
		//向页面输出
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		
		if(existUser != null){
			response.getWriter().println("<font color='red'>用户名已存在</font>");
		}
		else{
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}
		return NONE;
	}
	
	//后台校验的方法
	public String regist(){
		String checkcode1 = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(!checkcode1.equalsIgnoreCase(checkcode)){
			this.addActionError("验证码有错请重新输入");
			return "checkFail";
		}
		userService.save(user);
		return NONE;
	}
	
	//根据激活码查询用户
	public String active(){
		User existUser = userService.findByCode(user.getCode());
		if(existUser == null){
			this.addActionMessage("激活失败");
		}
		else{
			//激活用户
			existUser.setCode(null);
			existUser.setState(1);
			//修改用户状态
			userService.update(existUser);
			this.addActionMessage("激活成功，请去登录");
		}
		return "msg";
	}
	//跳转到登登录界面
	public String loginPage(){
		return "loginPage";
	}
	//登录的方法
	public String login(){
		User existUser = userService.login(user);
		if(existUser != null){
			//存入session
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			return "loginSuccess";
		}
		else{
			this.addActionError("登录信息有误");
			return LOGIN;
		}
	}
	//退出的方法
	public String quit(){
		
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
	
	
	
	
}
