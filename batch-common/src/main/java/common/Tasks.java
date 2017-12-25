package common;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Package: common <br/>
 * @Description： Java1.5后引入的Executor框架的最大优点是把任务的提交和执行解耦，只需把Task描述清楚，然后提交即可。
 * 至于这个Task是怎么被执行的，被谁执行的，什么时候执行的，就全部交给线程池管理。 <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2017/12/24 18:51 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2017 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2017/12/24. <br/>
 */

public class Tasks {
		public static void main(String[] args) throws ExecutionException, InterruptedException {
				//构建一个线程工厂
				ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
				int number = Runtime.getRuntime().availableProcessors();
				/**
				 * 构建一个最低为number 个线程的 线程池，最多3*number 个线程的线程池；
				 * 线程池空闲60毫秒将销毁，使用基于链接的队列；线程池满了之后的策略是中止请求
				 *
				 */
				ExecutorService service = new ThreadPoolExecutor(number, 3 * number, 60L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), factory, new ThreadPoolExecutor.AbortPolicy());
				Future<String> future = service.submit(new Task());
				String result = future.get();
				System.out.println(result);


				// MutiThreadDemo.testCachedThreadPool();
				System.out.println("zhongg");
		}

		private static class Task implements Callable<String> {
				/**
				 * Computes a result, or throws an exception if unable to do so.
				 *
				 * @return computed result
				 * @throws Exception if unable to compute a result
				 */
				@Override
				public String call() throws Exception {
						TimeUnit.SECONDS.sleep(10);
						return "China";
				}
		}

		/**
		 *  newCachedThreadPool创建一个可缓存的线程池。</br>
		 * 1).工作线程(线程池中的线程)的创建数量几乎没有限制(数目为Interger. MAX_VALUE),
		 * 这样可灵活的往线程池中添加线程。 </br>
		 * 2).如果长时间没有往线程池中提交任务，即如果工作线程空闲了指定的时间(为1分钟)，则该工作线程将自动终止。</br>
		 * 终止后，如果你又提交了新的任务，则线程池重新创建一个工作线程。</br>
		 * 3)该线程池使用的是不存储元素的阻塞队列（SynchronousQuene），每插入一个任务必须有另一个线程调用消费这个任务。</br>
		 * 4) 该方法接受Runnable 类型任务，无返回值。
		 * @param task
		 * @throws InterruptedException
		 * @throws ExecutionException
		 */
		public static void CachedThreadPoolTask(Runnable task) throws InterruptedException,
						ExecutionException {
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(task);
		}

		/**
		 *
		 * 创建一个启动线程池后延迟一秒，每两次成功执行间隔三秒执行一次的定期任务
		 * @param task
		 */
		public static void ScheduledThreadPoolFixedRateTask(Runnable task) {
				ScheduledThreadPoolFixedRateTask(task,1,3,TimeUnit.SECONDS);
		}

		/**
		 * 创建一个启动后延迟initialDelay秒，每两次成功执行间隔period秒；定期执行任务线程池
		 * @param task
		 * @param initialDelay
		 * @param period
		 * @param unit
		 */
		public static void ScheduledThreadPoolFixedRateTask(Runnable task,long initialDelay,long period,TimeUnit unit) {
				ScheduledExecutorService scheduledThreadPool = Executors
								.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
				scheduledThreadPool.scheduleAtFixedRate(task, initialDelay, period, unit);
		}

		/**
		 *
		 * @param task
		 * @throws InterruptedException
		 * @throws ExecutionException
		 */
		@SuppressWarnings("unused")
		public static void FixedThreadPoolTask(Runnable task) throws InterruptedException,
						ExecutionException {
				ExecutorService fixedThreadPool = Executors.newFixedThreadPool(Runtime
								.getRuntime().availableProcessors() * 2);
						fixedThreadPool.execute(task);
				/**
				 * 指示关闭线程池了，不在接收新的任务提交
				 */
				//fixedThreadPool.shutdown();
				/**
				 * awaitTermination 函数会在调用shutdown函数之后调用才有效，这个函数的功能是等待一定时间。
				 * 在调用shutdown函数后 要么阻塞到所有线程完成，要么就是达到了等待时间。
				 */
			 //fixedThreadPool.awaitTermination(20, TimeUnit.SECONDS);
		}
}
