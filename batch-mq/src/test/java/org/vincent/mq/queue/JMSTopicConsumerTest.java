package org.vincent.mq.queue;

import common.SimpleFixedThreadPool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** 
* JMSTopicConsumer Tester. 
*  ActiveMQ 基于topic 的消息 (发布/订阅类型)
* @author <PengRong> 
* @since  
* @version 1.0 
*/ 
public class JMSTopicConsumerTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: run() 
* 
*/ 
@Test
public void testRun() throws Exception {
		SimpleFixedThreadPool pool=new SimpleFixedThreadPool();
		pool.addTask(new JMSTopicConsumer());
		Thread.sleep(1000000);
} 


} 
