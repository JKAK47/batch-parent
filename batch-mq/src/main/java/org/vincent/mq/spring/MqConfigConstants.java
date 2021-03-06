package org.vincent.mq.spring;

import org.apache.activemq.ActiveMQConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Package: org.vincent.mq.queue <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2017/12/31 17:06 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2017 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2017/12/31. <br/>
 */
@Component("mqConfigConstants")
public class MqConfigConstants {
		/** 默认连接用户名 */
		public static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
		/** 默认连接密码 */
		public static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
		/** 默认连接地址 */
		public static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
		/**发送的消息数量*/
		public static final int SENDNUM = 10;
		/** 队列名字 */
		@Value("${topicName}")
		public   final  String TopicName="topic.Vincent";
		/** 主题名字 */
		@Value("${queueName}")
		public   String QueueName="quenu.vincent" ;

}
