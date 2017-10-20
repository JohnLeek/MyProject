package firstapp.adminuser.service;

import org.springframework.transaction.annotation.Transactional;

import firstapp.adminuser.dao.AdminUserDao;
import firstapp.adminuser.vo.AdminUser;
@Transactional
public class AdminUserService {
	private AdminUserDao adminUserDao;//×¢Èëdao
	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}
	public AdminUser login(AdminUser adminUser) {
		return adminUserDao.login(adminUser);
	}

	

	
}
