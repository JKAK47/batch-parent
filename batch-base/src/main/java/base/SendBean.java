package base;

/**
 * 发送 邮件，短信等消息的基础Bean
 *
 * @ClassName: SendBean
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author PengRong
 * @date 2017年12月2日 上午10:40:02
 *
 */
public class SendBean {

	public SendBean() {
		// TODO Auto-generated constructor stub
	}

	/** 发送者 */
	private String sender;
	/** 接受者 */
	private String receiver;
	/** 主题 */
	private String subject;
	/** 内容 */
	private String content;

	public String getSender() {
		return this.sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
