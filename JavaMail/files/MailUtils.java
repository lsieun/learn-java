package cn.itcast.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.junit.Test;

public class MailUtils {
	private static Properties prop;
	static {
		// 设置连接邮件服务器的参数
		prop = new Properties();
		prop.put("mail.transport.protocol", "smtp");	// 指定协议
		prop.put("mail.smtp.host", "localhost");		// 主机   stmp.qq.com
		prop.put("mail.smtp.port", 25);					// 端口
		prop.put("mail.smtp.auth", "true");				// 用户密码认证
		prop.put("mail.debug", "true");					// 调试模式
	}

	/**
	 * 发送邮件
	 * @param email		收件人地址
	 * @param content	邮件内容
	 * @throws Exception
	 */
	public static void sendMail(String email, String content) throws Exception {
	
		//1. 创建邮件会话对象
		Session session = Session.getDefaultInstance(prop);
		
		//2. 创建邮件对象
		MimeMessage message = new MimeMessage(session);
		//3. 设置邮件对象参数: 发件人、收件人、主题、内容、发送时间
		message.setSender(new InternetAddress("zhangsan@itcast.com"));
		
		message.setRecipient(RecipientType.TO, new InternetAddress(email)); // 【参数1】
		
		message.setSubject("我的第一封邮件2！");
		
		message.setText(content);// 【参数2】
		
		message.setSentDate(new Date());
		message.saveChanges(); // 保存邮件
		
		//4. 邮件发送
		Transport trans = session.getTransport();
		trans.connect("zhangsan", "888"); // 指定连接发件人的用户密码
		trans.sendMessage(message, message.getAllRecipients());
		trans.close();// 断开连接
	}

}
