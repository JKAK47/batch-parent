package base.email.exception;

/**
 * 邮件发送 异常类
 *
 * @ClassName: EmailException</br>
 * @Description: TODO(这里用一句话描述这个类的作用)</br>
 * @author PengRong</br>
 * @date 2017年11月26日 下午11:18:18</br>
 *
 */
public class EmailException extends Exception {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;

	public EmailException(Throwable e) {
		super(e);
	}

	public EmailException(String msg, Throwable e) {
		super(msg, e);
	}

}
