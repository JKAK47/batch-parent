package base.email;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import base.email.exception.EmailException;

/**
 * @ClassName: EmailServiceImpl
 * @Description: TODO(邮件发送实现类)
 * @author PengRong
 * @date 2017年11月26日 下午11:22:01
 *
 */

public class EmailServiceImpl implements EmailService {
	private JavaMailSender sender;
	private String systemEmail;

	public JavaMailSender getSender() {
		return this.sender;
	}

	public void setSender(JavaMailSender sender) {
		this.sender = sender;
	}

	public String getSystemEmail() {
		return this.systemEmail;
	}

	public void setSystemEmail(String systemEmail) {
		this.systemEmail = systemEmail;
	}

	@Override
	public void sendMail(String to, String subject, String htmlText)
			throws EmailException {
		// TODO Auto-generated method stub
		try {
			// MimeMessage 表示一封邮件
			MimeMessage email = this.sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(email);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(htmlText, true);
			this.sender.send(email);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
