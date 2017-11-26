package com.lsieun.javamail;

import org.junit.Test;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class App_SendMail {
    @Test
    public void testSend()throws Exception{
        //0. 邮件参数
        Properties prop = new Properties();
        prop.put("mail.transport.protocol", "smtp");	// 指定协议
        //prop.put("mail.smtp.host", "localhost");		// 主机   stmp.qq.com
        prop.put("mail.smtp.host", "192.168.80.128");	// 我把邮件服务器放到了192.168.80.128上
        prop.put("mail.smtp.port", 25);					// 端口
        prop.put("mail.smtp.auth", "true");				// 用户密码认证
        prop.put("mail.debug", "true");					// 调试模式

        //1. 创建一个邮件的会话
        Session session = Session.getDefaultInstance(prop);
        //2. 创建邮件体对象 (整封邮件对象)
        MimeMessage message = new MimeMessage(session);
        //3. 设置邮件体参数:
        //3.1 标题
        message.setSubject("我的第一封邮件");
        //3.2 邮件发送时间
        message.setSentDate(new Date());
        //3.3 发件人
        message.setSender(new InternetAddress("zhangsan@lsieun.com"));
        //3.4 接收人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("lisi@lsieun.com"));
        //3.5内容
        message.setText("你好，已经发送成功！  正文....");  // 简单纯文本邮件
        message.saveChanges();   // 保存邮件(可选)

        //4. 发送
        Transport trans = session.getTransport();
        trans.connect("zhangsan", "123456");
        // 发送邮件
        trans.sendMessage(message, message.getAllRecipients());
        trans.close();

    }
}
