package org.vincent.mq.activemq.queue;

import org.apache.activemq.ActiveMQConnection;

/**
 * @Package: org.vincent.mq.queue <br/>
 * @Description： ActiveMQ 定义的一些常量 <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2017/12/31 17:06 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2017 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2017/12/31. <br/>
 */

public class MqConfigConstants {
		/** 默认连接用户名 */
		public static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
		/** 默认连接密码 */
		public static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
		/** 默认连接地址 */
		public static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
		/** 外网IP 运行消息代理服务器，消息中介 监听 61616 */
		public static final String BROKEURL_ALI = "tcp://112.74.63.132:61616";

		/**发送的消息数量*/
		public static final int SENDNUM = 10;
		/** 主题名字 */
		public  static final  String TopicName="Activemq.topic.Vincent.helloword.01";
		/** 队列名字 */
		public static final String QueueName="Activemq.queue.vincent.helloworld.00" ;
}
