package firstapp.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import firstapp.user.service.UserService;
import firstapp.user.vo.User;
//ʹ��ģ������
public class UserAction extends ActionSupport implements ModelDriven<User>{

	public String registPage(){
		
		return "registPage";
	}
	//������֤��
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
	
	//ajax �첽У��
	public String findByName() throws IOException{
		User existUser = userService.findByUserName(user.getUsername());
		//��ҳ�����
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		
		if(existUser != null){
			response.getWriter().println("<font color='red'>�û����Ѵ���</font>");
		}
		else{
			response.getWriter().println("<font color='green'>�û�������ʹ��</font>");
		}
		return NONE;
	}
	
	//��̨У��ķ���
	public String regist(){
		String checkcode1 = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(!checkcode1.equalsIgnoreCase(checkcode)){
			this.addActionError("��֤���д�����������");
			return "checkFail";
		}
		userService.save(user);
		return NONE;
	}
	
	//���ݼ������ѯ�û�
	public String active(){
		User existUser = userService.findByCode(user.getCode());
		if(existUser == null){
			this.addActionMessage("����ʧ��");
		}
		else{
			//�����û�
			existUser.setCode(null);
			existUser.setState(1);
			//�޸��û�״̬
			userService.update(existUser);
			this.addActionMessage("����ɹ�����ȥ��¼");
		}
		return "msg";
	}
	//��ת���ǵ�¼����
	public String loginPage(){
		return "loginPage";
	}
	//��¼�ķ���
	public String login(){
		User existUser = userService.login(user);
		if(existUser != null){
			//����session
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			return "loginSuccess";
		}
		else{
			this.addActionError("��¼��Ϣ����");
			return LOGIN;
		}
	}
	//�˳��ķ���
	public String quit(){
		
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
	
	
	
	
}
