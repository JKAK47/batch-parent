package org.vincent.mq.queue;

import common.SimpleFixedThreadPool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JMSProducer Tester.
 *   先启动点对点消息队列的生产者，生产消息提交到队列；然后启动消费者消费消息。
 * @author <Authors name>
 * @version 1.0
 * @since <pre>01/02/2018</pre>
 */
public class JMSProducerTest {


    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: run()
     */
    @Test
    public void testRun() throws Exception {
        SimpleFixedThreadPool pool=new SimpleFixedThreadPool();
        for (int i=0; i<10;i++ ){
            pool.addTask(new JMSProducer());
        }
        Thread.sleep(10000);
    }


} 
