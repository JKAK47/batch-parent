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
	 * @Title: sendMail
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param emailSendBean
	 *            邮件发送bean
	 * @throws EmailException
	 *             设定文件
	 * @return void 返回类型
	 * @throws
	 */
	void sendMail(EmailSendBean emailSendBean) throws EmailException;
}
