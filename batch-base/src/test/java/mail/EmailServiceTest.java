package mail;

import javax.mail.Message;
import javax.mail.MessagingException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import base.email.EmailService;
import base.email.exception.EmailException;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;

/**
 *
 * @ClassName: EmailServiceTest
 * @Description: TODO( EmailService 邮件接口方法测试，使用GreenMail 作为邮件测试框架)
 * @author PengRong
 * @date 2017年11月27日 上午1:13:20
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 标注spring注入文件
@ContextConfiguration(locations = "classpath:META-INF/Spring/email-test.xml")
public class EmailServiceTest extends AbstractJUnit4SpringContextTests {

	private GreenMail greenMail;

	@Autowired
	private EmailService emailService;
	@Value("${email.username}")
	private String userName;

	@Value("${email.password}")
	private String userPassword;

	@Before
	public void setUp() throws Exception {
		// 基于SMTP协议初始化greemmail 邮件服务器并创建用户最后启动邮件服务器
		this.greenMail = new GreenMail(ServerSetup.SMTP);
		this.greenMail.setUser(this.userName, this.userPassword);
		this.greenMail.start();
	}

	@After
	public void tearDown() throws Exception {
		this.greenMail.stop();// 关闭服务器
	}

	@Test
	public void test() throws EmailException, InterruptedException,
			MessagingException {
		// 发送邮件，设置主题，内容
		String subject = "pop music";
		String htmltext = "<h1>Test H1</h1>";
		this.emailService.sendMail("test2@pengrong.com", subject, htmltext);
		// 接受一封邮件
		this.greenMail.waitForIncomingEmail(2000, 1);
		// 获取邮件内容
		Message[] messages = this.greenMail.getReceivedMessages();
		// 对比刚才发送邮件的字段内容
		Assert.assertEquals(1, messages.length);
		Assert.assertEquals(subject, messages[0].getSubject());
		Assert.assertEquals(htmltext, GreenMailUtil.getBody(messages[0]).trim());

	}

}
