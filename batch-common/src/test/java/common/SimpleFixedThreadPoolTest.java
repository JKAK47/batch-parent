package common;

import java.util.Date;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * SimpleFixedThreadPool Tester.
 *
 * @author <PengRong>
 * @version 1.0
 */

public class SimpleFixedThreadPoolTest extends BaseTest {
		@Autowired
		private SimpleFixedThreadPool simpleFixedThreadPool;

		@BeforeClass
		public static void before() throws Exception {
		}

		@AfterClass
		public static void after() throws Exception {

		}

		/**
		 * Method: addTask(Runnable task)
		 */
		@Test
		public void testAddTask() throws Exception {
				final int[] j = {0};
				for (int i = 0; i < 100; i++) {
						simpleFixedThreadPool.addTask(new Runnable() {
								@Override
								public void run() {
										try {
												Thread.sleep(10);
												logger.debug(new Date() + "\t time =" + System.currentTimeMillis() + Thread.currentThread().getName() + "\t" + (j[0]++));
										} catch (InterruptedException e) {
												e.printStackTrace();
										}
								}
						});
				}
				simpleFixedThreadPool.shutdown();
				Thread.sleep(10000);
		}

		/**
		 * Method: shutdown()
		 */
		@Test
		public void testShutdown() throws Exception {
//TODO: Test goes here... 
		}


} 
