package org.vincent.mq.topic;

import common.SimpleCacheThreadPool;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * MyTopicMessageConsumer Tester.
 *
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
