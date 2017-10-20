package firstapp.user.service;

import firstapp.user.dao.UserDao;
import firstapp.user.vo.User;
import firstapp.utils.MailUitls;
import firstapp.utils.UUIDUtils;

public class UserService {
	
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	//ajax 异步校验
	public User findByUserName(String username) {
		
		return userDao.findByUserName(username);
	}
	//注册的方法
	public void save(User user) {

		//设置激活状态
		user.setState(0);
		//设置激活码
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		//调用dao层去储存注册的数据
		userDao.save(user);
		MailUitls.sendMail(user.getEmail(), code);
	}
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}
		//修改用户状态
	public void update(User existUser) {
		 userDao.update(existUser);
	}
	public User login(User user) {
		return userDao.login(user);
	}
	
}
