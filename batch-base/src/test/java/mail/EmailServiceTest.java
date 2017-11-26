package mail;

import javax.mail.Message;
import javax.mail.MessagingException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import base.email.EmailService;
import base.email.exception.EmailException;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/Spring/email-test.xml")
public class EmailServiceTest extends AbstractJUnit4SpringContextTests {

	private GreenMail greenMail;

	@Autowired
	private EmailService emailService;

	@Before
	public void setUp() throws Exception {
		this.greenMail = new GreenMail(ServerSetup.SMTP);
		this.greenMail.setUser("test@pengrong.com", "123456");
	}

	@After
	public void tearDown() throws Exception {
		this.greenMail.stop();
	}

	@Test
	public void test() throws EmailException, InterruptedException,
			MessagingException {
		String subject = " pop music";
		String htmltext = "<h1>Test H1</h1>";
		this.emailService.sendMail("test2@pengrong.com", subject, htmltext);
		this.greenMail.waitForIncomingEmail(2000, 1);
		Message[] messages = this.greenMail.getReceivedMessages();
		Assert.assertEquals(1, messages.length);
		Assert.assertEquals(subject, messages[0].getSubject());
		Assert.assertEquals(htmltext, GreenMailUtil.getBody(messages[0]).trim());

	}

}
