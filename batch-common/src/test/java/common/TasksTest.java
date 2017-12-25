package common;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tasks Tester.
 *
 * @author <PengRong>
 * @version 1.0
 */
public class TasksTest extends BaseTest {

		@Before
		public void before() throws Exception {

		}

		@After
		public void after() throws Exception {
		}

		/**
		 * Method: CachedThreadPoolTask(Runnable task)
		 * 测试可以缓存很大量线程的线程池，线程空闲一秒即会销毁。
		 */
		@Test
		public void testCachedThreadPoolTask() throws Exception {
				logger.info("start testCachedThreadPoolTask");
				Tasks.CachedThreadPoolTask(() -> logger.debug("CachedThreadPoolTask running"));
				Thread.sleep(1000);
				logger.info("stop testCachedThreadPoolTask");
		}

		/**
		 * Method: ScheduledThreadPoolFixedRateTask(Runnable task)
		 * 测试执行周期性任务的线程池
		 */
		@Test
		public void testScheduledThreadPoolFixedRateTaskTask() throws Exception {
				logger.info("testScheduledThreadPoolFixedRateTaskTask running");
				final int[] i = {0};
				Tasks.ScheduledThreadPoolFixedRateTask(() -> logger.debug("ScheduledThreadPoolFixedRateTask runnig +" + (i[0]++)));
				Thread.sleep(10000);
				logger.info("testScheduledThreadPoolFixedRateTaskTask stop");
		}

		/**
		 * Method: ScheduledThreadPoolFixedRateTask(Runnable task, long initialDelay, long period, TimeUnit unit)
		 * 测试周期性调用执行任务线程池（ScheduledThreadPoolFixedRateTask 接口）
		 */
		@Test
		public void testScheduledThreadPoolFixedRateTaskForTaskInitialDelayPeriodUnit() throws Exception {
				logger.info("start ScheduledThreadPoolFixedRateTask");
				Tasks.ScheduledThreadPoolFixedRateTask(() -> logger.info("initialDelay = 0S, period = 3S, ScheduledThreadPoolFixedRateTask Running.."), 0, 3, TimeUnit.SECONDS);
				Thread.sleep(10000);
				logger.info("stop ScheduledThreadPoolFixedRateTask");
		}

		/**
		 * Method: FixedThreadPoolTask(Runnable task)
		 */
		@Test
		public void testFixedThreadPoolTask() throws Exception {
				logger.info("start FixedThreadPoolTask");
				Tasks.FixedThreadPoolTask(() -> logger.info("FixedThreadPoolTask running"));
				Thread.sleep(100);
		}

} 
