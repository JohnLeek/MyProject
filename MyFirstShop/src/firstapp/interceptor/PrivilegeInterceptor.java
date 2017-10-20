package firstapp.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import firstapp.adminuser.vo.AdminUser;

//��̨Ȩ�޵�������
public class PrivilegeInterceptor extends MethodFilterInterceptor{

	@Override
	//���صķ���
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		//����seesion�б�����û���Ϣ�����ж�
		AdminUser adminUser = (AdminUser) ServletActionContext.getRequest().getAttribute("existAdminUser");
		if(adminUser == null){
			//û�е�¼
			ActionSupport actionSupport= (ActionSupport) actionInvocation.getAction();
			actionSupport.addActionError("δ��¼û��Ȩ�޷���");
			return "loginFail";
		}
		else{
			//��¼��
			return actionInvocation.invoke();
		}
		
	}

	
}
