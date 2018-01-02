package org.vincent.mq.topic;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * @Package: org.vincent.mq.topic <br/>
 * @Description： 基于Topic类型消息系统 消息消费者的消息监听器实现， <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/1/1 19:02 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2017 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/1/1. <br/>
 * 教程： http://blog.csdn.net/u013760858/article/details/49638061；
 *      http://blog.csdn.net/j253507692/article/details/78861428
 */

public class MqListener implements MessageListener {
		@Override
		public void onMessage(Message message) {
				try {
						MapMessage mapMessage= (MapMessage) message;
						if (mapMessage!=null){
								System.out.println("name: "+mapMessage.getString("name")+"\tage: "+mapMessage.getInt("agt")+"\taddress"+mapMessage.getString("address"));
								mapMessage.acknowledge();//topic 类型消息系统，确认消费消息
						}
				}catch (Exception e){
						e.printStackTrace();
				}
		}
}
