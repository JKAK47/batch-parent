package base.MutiThread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MutiThreadDemo {

	/**
	 * 通过线程池创建固定个线程的线程池，线程池包含三个线程，还有一个Main线程
	 *
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@SuppressWarnings("unused")
	private static void testFixedThreadPool() throws InterruptedException,
			ExecutionException {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(Runtime
				.getRuntime().availableProcessors() * 2);
		for (int i = 0; i < 100; i++) {
			final int index = i;
			fixedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println("threadName: "
								+ Thread.currentThread().getName() + "\t"
								+ index);
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		System.out.println("当前核心数："
				+ Runtime.getRuntime().availableProcessors());
		/**
		 * 指示关闭线程池了，不在接收新的任务提交
		 */
		fixedThreadPool.shutdown();
		/**
		 * awaitTermination 函数会在调用shutdown函数之后调用才有效，这个函数的功能是等待一定时间。
		 * 在调用shutdown函数后 要么阻塞到所有线程完成，要么就是达到了等待时间。才会执行下面的语句
		 */
		fixedThreadPool.awaitTermination(20, TimeUnit.SECONDS);
	}

	public static void main(String[] args) throws ExecutionException,
			InterruptedException {
		MutiThreadDemo.testFixedThreadPool();
		// MutiThreadDemo.testCachedThreadPool();
		System.out.println("zhongg");
		MutiThreadDemo demo=new  MutiThreadDemo();
		String sql=demo.getStatement("insertAA");
		System.out.println(sql);
		
	}
	
	/**
	 * 在数据库中自动获取到单表的Mapper命令空间
	 * @param sqlId
	 * @return
	 */
	public   String getStatement(String sqlId) {
		String name = this.getClass().getName();
		StringBuffer sb = new StringBuffer();
		sb.append(name).append(".").append(sqlId);
		String statement = sb.toString();

		return statement;
	}

	/**
	 * newCachedThreadPool创建一个可缓存的线程池。
	 * 1).工作线程(线程池中的线程)的创建数量几乎没有限制(其实也有限制的,数目为Interger. MAX_VALUE),
	 * 这样可灵活的往线程池中添加线程。 2).如果长时间没有往线程池中提交任务，即如果工作线程空闲了指定的时间(默认为1分钟)，则该工作线程将自动终止。
	 * 终止后，如果你又提交了新的任务，则线程池重新创建一个工作线程。
	 */
	private static void testCachedThreadPool() throws InterruptedException,
			ExecutionException {
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

		for (int i = 0; i < 100; i++) {
			final int index = i;
			Thread.sleep(index * 10);
			cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println("threadName: "
							+ Thread.currentThread().getName() + "\t" + index);
				}
			});
		}
	}

	public static void testScheduledThreadPool() {
		ScheduledExecutorService scheduledThreadPool = Executors
				.newScheduledThreadPool(5);
		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out
						.println("delay 1 seconds, and excute every 3 seconds");
			}
		}, 1, 3, TimeUnit.SECONDS);
	}
}
