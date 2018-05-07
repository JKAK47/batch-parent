package org.vincent.mq.queue;

import common.SimpleFixedThreadPool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** 
* JMSTopicProducer Tester. 
* 
* @author <PengRong> 
* @since  
* @version 1.0 
*/ 
public class JMSTopicProducerTest { 

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
		for (int i=0; i<10;i++ ){
				pool.addTask(new JMSTopicProducer());
		}
		Thread.sleep(10000);
} 


/** 
* 
* Method: sendMessage(Session session, MessageProducer messageProducer) 
* 
*/ 
@Test
public void testSendMessage() throws Exception { 
//TODO: Test goes here... 

} 

} 
