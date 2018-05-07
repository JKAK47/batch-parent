package org.vincent.mq.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.vincent.mq.queue.MqConfigConstants;

/**
 * @Package: org.vincent.mq.topic <br/>
 * @Description： Topic 类型消息系统，消息生产者 <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2017/12/31 17:25 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2017 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2017/12/31. <br/>
 */
public class MyTopicMessageProducer implements Runnable {
		@Autowired
		MqConfigConstants mqConfigConstants;
		private ConnectionFactory factory;

		/**
		 * When an object implementing interface <code>Runnable</code> is used
		 * to create a thread, starting the thread causes the object's
		 * <code>run</code> method to be called in that separately executing
		 * thread.
		 * <p>
		 * The general contract of the method <code>run</code> is that it may
		 * take any action whatsoever.
		 *
		 * @see Thread#run()
		 */
		@Override
		public void run() {
				System.out.println("topic 消息类型开始产生消息 " + "生产者: " + Thread.currentThread().getName());
				try {
						/**第一步 创建连接工厂*/
						factory = new ActiveMQConnectionFactory(MqConfigConstants.USERNAME, MqConfigConstants.PASSWORD, MqConfigConstants.BROKEURL);
						/**第二步 创建JMS 连接*/
						Connection connection = factory.createConnection();
						connection.start();
						/** 创建Session,开启事务 */
						Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
						/** 创建topic,Topic是 Destination接口的子接口*/
						Topic topic = session.createTopic(mqConfigConstants.TopicName);
						/** 创建生产者producer */
						MessageProducer producer = session.createProducer(topic);

						/** 设置消息不需要持久化 */
						producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
						//发送文本消息
						while (true) {
								Message message = createMessage(session,"vincent",27,"江西省赣州市");
								producer.send(message);
								session.commit();//开启事务必须需要提交，消费者才能获取到
								System.out.println("发送消息： " +message.toString() );
								Thread.sleep(200);
						}
				} catch (JMSException e) {
						e.printStackTrace();
				} catch (InterruptedException e) {
						e.printStackTrace();
				}
		}

		/**
		 * 构建消息发出者发出的消息实体
		 *
		 * @return
		 * @param session
		 */
		private Message createMessage(Session session,String name,int age,String address) throws JMSException {
				MapMessage message=session.createMapMessage();
				message.setString("name",name);
				message.setString("address",address);
				message.setInt("age",age);
				return message;
		}
}
