package org.vincent.mq.spring.base;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * batch-parent.spring.base <br/>
 * Created by PengRong on 2018/1/2. <br/>
 * Spring + ActiveMQ 整合后消息监听器
 * @author PengRong <br/>
 * @Description TODO(${END})
 * @ClassName: ${CLASS}
 * @since 2018-01-02 13:48 <br/>
 */
@Component("consumerMessageListener")
public class ConsumerMessageListener implements MessageListener {

    public void onMessage(Message message) {
        //这里我们知道生产者发送的就是一个纯文本消息，所以这里可以直接进行强制转换
        TextMessage textMsg = (TextMessage) message;
        System.out.println("消息监听器接收到一个纯文本消息。");
        try {
            System.out.println("消息内容是：" + textMsg.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
