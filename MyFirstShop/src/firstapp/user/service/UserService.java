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
	//ajax �첽У��
	public User findByUserName(String username) {
		
		return userDao.findByUserName(username);
	}
	//ע��ķ���
	public void save(User user) {

		//���ü���״̬
		user.setState(0);
		//���ü�����
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		//����dao��ȥ����ע�������
		userDao.save(user);
		MailUitls.sendMail(user.getEmail(), code);
	}
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}
		//�޸��û�״̬
	public void update(User existUser) {
		 userDao.update(existUser);
	}
	public User login(User user) {
		return userDao.login(user);
	}
	
}
