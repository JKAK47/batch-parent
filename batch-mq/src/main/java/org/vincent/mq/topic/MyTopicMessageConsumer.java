package org.vincent.mq.topic;

import common.utils.LoggerFactory;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Topic;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.vincent.mq.queue.MqConfigConstants;


/**
 * @Package: org.vincent.mq.topic <br/>
 * @Description： Topic 消息系统 消费者 <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/1/1 17:21 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2017 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/1/1. <br/>
 */

public class MyTopicMessageConsumer implements Runnable {

		private Logger logger = LoggerFactory.getLogger();
		@Autowired
		MqConfigConstants mqConfigConstants;
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
				try {
						logger.info("消費者(" + Thread.currentThread().getName() + ")開始接受信息： ");
						//创建连接工厂类
						ConnectionFactory factory = new ActiveMQConnectionFactory(MqConfigConstants.USERNAME, MqConfigConstants.PASSWORD, MqConfigConstants.BROKEURL);
						// 创建JMS连接实例，并启动连接
						Connection connection = factory.createConnection();
						connection.start();
						//创建Session 对象
						Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
						//创建主题，消费者消费主题 必须和消息产生者产生的主题一致。
						Topic topic = session.createTopic(mqConfigConstants.TopicName);
						// 创建消费者
						MessageConsumer consumer = session.createConsumer(topic);
						// 消费者 异步消息生产者生成的消息
						consumer.setMessageListener(new MqListener());
				} catch (JMSException e) {
						e.printStackTrace();
				}
		}
}
