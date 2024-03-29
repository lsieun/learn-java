package cn.itcast.mail;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeMessage.RecipientType;

import org.junit.Test;

/**
 * 带图片资源的邮件 + 附件
 * @author AdminTH
 *
 */
public class Mail_3_imgAndAtta {

	@Test
	public void testMail() throws Exception {
	
		// 设置连接邮件服务器的参数
		Properties prop = new Properties();
		prop.put("mail.transport.protocol", "smtp");	// 指定协议
		prop.put("mail.smtp.host", "localhost");		// 主机   stmp.qq.com
		prop.put("mail.smtp.port", 25);					// 端口
		prop.put("mail.smtp.auth", "true");				// 用户密码认证
		prop.put("mail.debug", "true");					// 调试模式
		
		//1. 创建会话
		Session session = Session.getDefaultInstance(prop);
		//2. 创建邮件对象
		MimeMessage message = new MimeMessage(session);
		//3. 设置邮件参数
		message.setSender(new InternetAddress("zhangsan@itcast.com"));
		message.setRecipient(RecipientType.TO, new InternetAddress("lisi@itcast.com"));
		message.setSubject("带图片的邮件");
		message.setSentDate(new Date());
		//message.setText("<a href='#'>百度<a/>");
		
		/*************设置总邮件块***************/
		MimeMultipart all = new MimeMultipart("mixed");
		// 左（内容 + 图片资源），  右 （附件）
		MimeBodyPart left = new MimeBodyPart();
		MimeBodyPart right = new MimeBodyPart();
		// 设置
		all.addBodyPart(left);
		all.addBodyPart(right);
		//把总邮件快, 设置到邮件体中
		message.setContent(all);
		
		// 设置 总邮件块的右侧，  即附件
		String path_ = this.getClass().getResource("a.docx").getPath();
		File file_ = new File(path_);
		DataSource file_ds = new FileDataSource(file_);
		DataHandler file_handler = new DataHandler(file_ds);
		right.setDataHandler(file_handler);
		right.setFileName("a.docx");  // 附件中显示的文件名
		
		
		/**************1. 邮件设置图片资源********************/
		// 1.1 创建负责复杂邮件体
		MimeMultipart mul = new MimeMultipart("related");
		// 邮件体related  = 内容 +  资源
		MimeBodyPart content = new MimeBodyPart(); // 内容
		MimeBodyPart resource = new MimeBodyPart();  // 资源
		// 把内容资源设置到复杂邮件中
		mul.addBodyPart(content);
		mul.addBodyPart(resource);
		//message.setContent(mul);
		left.setContent(mul);		
		
		
		//---- 设置邮件资源------
		String path = this.getClass().getResource("1.jpg").getPath();
		
		File file = new File(path);
		DataSource ds = new FileDataSource(file); 
		DataHandler handler = new DataHandler(ds);
		resource.setDataHandler(handler);  // 设置数据资源
		resource.setContentID("1.jpg");  // 图片资源在邮件中的名称
		
		//---- 设置邮件内容------
		content.setContent("<img src='cid:1.jpg' />正文邮件，你看图片！", "text/html;charset=UTF-8");
		
		
		
		message.saveChanges(); // 保存邮件
		//4. 邮件发送
		Transport trans = session.getTransport();
		trans.connect("zhangsan", "888"); // 指定连接发件人的用户密码
		trans.sendMessage(message, message.getAllRecipients());
		trans.close();// 断开连接
	}

}














