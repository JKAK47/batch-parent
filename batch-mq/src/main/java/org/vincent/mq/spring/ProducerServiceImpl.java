package org.vincent.mq.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.vincent.mq.spring.base.ProducerService;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * batch-parent.spring <br/>
 * Created by PengRong on 2018/1/2. <br/>
 *
 * @author PengRong <br/>
 * @Description TODO(${END})
 * @ClassName: ${CLASS}
 * @since 2018-01-02 13:30 <br/>
 */
@Component("producerServiceImpl")
public class ProducerServiceImpl implements ProducerService {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(Destination destination, final String message) {
        System.out.println("---------------生产者发送消息-----------------");
        System.out.println("---------------生产者发了一个消息：" + message);
        //发送消息到 destination
       // jmsTemplate.send(destination, session -> session.createTextMessage(message));
        // destination 通过注入到jmstemplate
        jmsTemplate.send(session -> session.createTextMessage(message));

    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    @Resource
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
}
