package org.vincent.mq.spring;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.vincent.mq.spring.base.ProducerService;
import javax.jms.Destination;
/**
 * ProducerServiceImpl Tester.
 * Spring + ActiveMQ 集成测试
 * @author <Authors name>
 * @version 1.0
 * @since <pre>01/02/2018</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/Application.xml"})
public class ProducerServiceImplTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private ProducerService producerService;
    @Autowired
    @Qualifier("queueDestination")
    private Destination destination;
    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: sendMessage(Destination destination, final String message)
     */
    @Test
    public void testSendMessage() throws Exception {
        for (int i=0; i<2; i++) {
            producerService.sendMessage(destination, "你好，消费者！这是消息，我是生产者：" + (i+1));
        }
    }


} 
