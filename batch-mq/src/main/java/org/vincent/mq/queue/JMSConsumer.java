package org.vincent.mq.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * batch-parent.org.vincent.mq <br/>
 * Created by PengRong on 2017/12/29. <br/>
 *
 * @author PengRong <br/>
 * @Description 基于ActiveMQ 消息队列 实现的消息消费者 简单测试实例;对于点对点的消息队列，可以先启动生产者，然后启动消费者(${END})
 * @ClassName: ${CLASS}
 * @since 2017-12-29 17:15 <br/>
 */
public class JMSConsumer implements Runnable, ExceptionListener {

		@Override
		public void run() {

				ConnectionFactory connectionFactory;//连接工厂
				//队列连接
				Connection connection = null;

				Session session = null; //会话 接受或者发送消息的线程
				Destination destination;//消息的目的地

				MessageConsumer messageConsumer;//消息的消费者

				//第一步，实例化连接工厂
				connectionFactory = new ActiveMQConnectionFactory(MqConfigConstants.USERNAME, MqConfigConstants.PASSWORD, MqConfigConstants.BROKEURL);

				try {
						//第二步，通过连接工厂获取连接
						connection = connectionFactory.createConnection();
						//启动连接
						connection.start();
						connection.setExceptionListener(this);
						//第三步，创建session，读取消息不开启事务
						session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
						//第四步，创建一个连接HelloWorld的消息队列，接受者消息队列名称 必须和发送者的的消息队列名字一致；如果不一致那么消费者将一直阻塞。
						destination = session.createQueue(MqConfigConstants.QueueName);
						//第五步，创建消息消费者
						messageConsumer = session.createConsumer(destination);

						while (true) {
								Message message = messageConsumer.receive(100000);
								if (message instanceof TextMessage) {
										if (message != null) {
												//通知消息队列，消费者已消费了这条消息，可以从消息队列把这条消息删除；防止多个消息接受者接受到同一个消息。
												message.acknowledge();
												System.out.println("收到的消息:" + ((TextMessage) message).getText());
										} else {
												break;
										}
								} else {
										System.out.println("Received Message: " + message);
								}
						}
				} catch (JMSException e) {
						e.printStackTrace();
				} finally {
						if (session != null) {
								try {
										session.close();
								} catch (JMSException e) {
										e.printStackTrace();
								}
						}
						if (connection != null) {
								try {
										connection.close();
								} catch (JMSException e) {
										e.printStackTrace();
								}
						}
				}

		}

		/**
		 * 消费者遇到异常后回调的方法
		 * @param exception
		 */
		@Override
		public void onException(JMSException exception) {
				System.out.println("JMS Exception occured.  Shutting down client. Exception Msg:" + exception.getMessage());
		}
}