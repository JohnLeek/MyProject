package firstapp.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//发送邮件
public class MailUitls {
	//to 收件人 code 激活码
	public static void sendMail(String to,String code){
		//获得链接对象
		Properties props = new Properties();
		props.setProperty("mail.host", "localhost");
		//获得session对象
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication("service@shop.com","111");
			}
			
			
		});
		//创建邮件对象
		Message message = new MimeMessage(session);
		
		try {
			//设置发件人
			message.setFrom(new InternetAddress("service@shop.com"));
			//设置收件人
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			//设置标题
			message.setSubject("来自购物商城的激活邮件");
			//设置邮件的正文
			message.setContent("<h1>点击下面链接激活</h1><h3><a href='http://221.192.237.29:8080/MyFirstShop/user_active.action?code="+code+"'>http://221.192.237.29:8080/MyFirstShop/user_active.action?code="+code+"</a></h3>", "text/html;charset=UTF-8");
			//发送
			Transport.send(message);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
