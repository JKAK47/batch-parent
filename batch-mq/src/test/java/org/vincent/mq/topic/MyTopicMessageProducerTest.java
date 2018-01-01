package org.vincent.mq.topic;

import common.SimpleFixedThreadPool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** 
* MyTopicMessageProducer Tester. 
* 
* @author <PengRong> 
* @since  
* @version 1.0 
*/ 
public class MyTopicMessageProducerTest { 

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
		MyTopicMessageProducer producer = new MyTopicMessageProducer();
		SimpleFixedThreadPool threadPool = new SimpleFixedThreadPool();
		for (int i = 0; i < 1; i++) {
				threadPool.addTask(producer);
		}
		Thread.sleep(1000000);
}


} 
