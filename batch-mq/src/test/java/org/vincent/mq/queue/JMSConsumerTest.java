package org.vincent.mq.queue;

import common.SimpleFixedThreadPool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JMSConsumer Tester.
 *  后启动消费者消费消息
 * @author <Authors name>
 * @version 1.0
 * @since <pre>01/02/2018</pre>
 */
public class JMSConsumerTest {

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
        pool.addTask(new JMSConsumer());
        Thread.sleep(100000);
    }


} 
