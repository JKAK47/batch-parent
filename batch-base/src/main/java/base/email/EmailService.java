package base.email;

import base.email.exception.EmailException;

/**
 * 邮件发送接口
 *
 * @ClassName: EmailService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author PengRong
 * @date 2017年11月26日 下午11:18:46
 *
 */
public interface EmailService {
	/**
	 *
	 * @Title: sendMail </br>
	 * @Description: TODO(发送邮件接口方法) </br>
	 * @param to
	 *            发送目的地址</br>
	 * @param subject
	 *            邮件主题</br>
	 * @param htmlText
	 *            邮件内容</br>
	 * @throws EmailException
	 *             设定文件 </br>
	 * @return void 返回类型 </br>
	 */
	void sendMail(String to, String subject, String htmlText)
			throws EmailException;
}
