package org.vincent.mq.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
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
public class JMSTopicConsumer implements Runnable{

    @Override
    public void run() {

        ConnectionFactory connectionFactory;//连接工厂
        //队列连接
        Connection connection = null;

        Session session;//会话 接受或者发送消息的线程
        Destination destination;//消息的目的地

        MessageConsumer messageConsumer;//消息的消费者

        //第一步，实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(MqConfigConstants.USERNAME, MqConfigConstants.PASSWORD, MqConfigConstants.BROKEURL);

        try {
            //第二步，通过连接工厂获取连接
            connection = connectionFactory.createConnection();
            //启动连接
            connection.start();
            //第三步，创建session，读取消息不开启事务
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //第四步，创建一个Topic
            destination = session.createTopic(MqConfigConstants.TopicName);
            //第五步，创建消息消费者
            messageConsumer = session.createConsumer(destination);
            while (true) {
                TextMessage textMessage = (TextMessage) messageConsumer.receive(100000);
                if (textMessage != null) {
                    textMessage.acknowledge();
                    System.out.println("收到的消息:" + textMessage.getText());
                } else {
                    break;
                }
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}