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

//�����ʼ�
public class MailUitls {
	//to �ռ��� code ������
	public static void sendMail(String to,String code){
		//������Ӷ���
		Properties props = new Properties();
		props.setProperty("mail.host", "localhost");
		//���session����
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication("service@shop.com","111");
			}
			
			
		});
		//�����ʼ�����
		Message message = new MimeMessage(session);
		
		try {
			//���÷�����
			message.setFrom(new InternetAddress("service@shop.com"));
			//�����ռ���
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			//���ñ���
			message.setSubject("���Թ����̳ǵļ����ʼ�");
			//�����ʼ�������
			message.setContent("<h1>����������Ӽ���</h1><h3><a href='http://221.192.237.29:8080/MyFirstShop/user_active.action?code="+code+"'>http://221.192.237.29:8080/MyFirstShop/user_active.action?code="+code+"</a></h3>", "text/html;charset=UTF-8");
			//����
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
