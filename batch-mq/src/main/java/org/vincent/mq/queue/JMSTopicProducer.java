package org.vincent.mq.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * batch-parent.org.vincent.mq <br/>
 * Created by PengRong on 2017/12/29. <br/>
 *  http://blog.csdn.net/caiwenfeng_for_23/article/details/8606538
 * @author PengRong <br/>
 * @Description 基于ActiveMQ 消息队列 实现的消息提供者 简单测试实例，向消息中介系统发送消息 (${END})
 * @ClassName: ${CLASS}
 * @since 2017-12-29 14:17 <br/>
 */

public class JMSTopicProducer implements  Runnable{
    /**
     * 发送文本消息
     * @param session
     * @param messageProducer  消息生产者
     * @throws Exception
     */
    private  void sendMessage(Session session,MessageProducer messageProducer) throws Exception{
        for (int i = 0; i < MqConfigConstants.SENDNUM; i++) {
            //创建一条文本消息
            TextMessage message = session.createTextMessage("ActiveMQ 发送消息" +i);
            System.out.println("发送消息：Activemq 发送消息" + i);
            //通过消息生产者发出消息
            messageProducer.send(message);
        }
    }

    @Override
    public void run() {
        //连接工厂
        ConnectionFactory connectionFactory;
        //连接
        Connection connection = null;
        //会话 接受或者发送消息的线程
        Session session;
        //消息的目的地
        Destination destination;
        //消息生产者
        MessageProducer messageProducer;
        //实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(MqConfigConstants.USERNAME, MqConfigConstants.PASSWORD, MqConfigConstants.BROKEURL);
        try {
            //通过连接工厂获取连接
            connection = connectionFactory.createConnection();
            //启动连接
            connection.start();
            //创建session，开启事务
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            //创建一个Topic,
            destination = session.createTopic(MqConfigConstants.TopicName);
            //创建消息生产者
            messageProducer = session.createProducer(destination);
            // 设置消息不需要持久化
            //// 发送消息。non-persistent 默认异步发送；persistent 默认同步发送(同步发送会阻塞生产者
            messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            //发送消息
            sendMessage(session, messageProducer);
            session.commit();// 如果 session 开启了事务，那么发送消息后需要提交事务
            System.out.println("P2P生产者: "+Thread.currentThread().getName()+" 消息产生完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(connection != null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}