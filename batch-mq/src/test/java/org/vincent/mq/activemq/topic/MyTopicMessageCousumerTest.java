package org.vincent.mq.activemq.topic;

import common.SimpleCacheThreadPool;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * MyTopicMessageConsumer Tester.
 * 基于Topic 模式的消费者测试类，先生产者启动，阻塞等待生产者生产消息。
 * @author <PengRong>
 * @version 1.0
 */
public class MyTopicMessageCousumerTest {

		@BeforeClass
		public static void before() throws Exception {
		}

		@AfterClass
		public static void after() throws Exception {
		}

		/**
		 * Method: run()
		 */
		@Test
		public void testTopic() throws Exception {
				MyTopicMessageConsumer consumer = new MyTopicMessageConsumer();
				SimpleCacheThreadPool pool = new SimpleCacheThreadPool();
				for (int i = 0; i < 3; i++) {
						pool.addTask(consumer);
				}
				Thread.sleep(100000);

		}


} 
