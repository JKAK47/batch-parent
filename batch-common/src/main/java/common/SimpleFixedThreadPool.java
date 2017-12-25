package common;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.springframework.stereotype.Component;

/**
 * @Package: common <br/>
 * @Description： 基于 FixedThreadPool 线程池的简单封装，FixedThreadPool 线程池的核心线程数和最大线程数是一样的可以确认任务消费安装FIFO原则 <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2017/12/25 12:25 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2017 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2017/12/25. <br/>
 */
@Component("simpleFixedThreadPool")
public class SimpleFixedThreadPool implements  TaskExecutor{
		private static ExecutorService executor= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2,
						new ThreadFactoryBuilder().setNameFormat("SimpleFixedThreadPool- %d").build());

		/**
		 * 提交任务,任务执行后无返回值
		 * @param task
		 */
		@Override
		public void addTask(Runnable task) {
			executor.execute(task);
		}


		@Override
		public Future<?> addTask(Callable<?> task) {
			Future<?> future=executor.submit(task);
				return future;
		}

		/**
		 *  关闭线程池
		 */
		public void  shutdown(){
				executor.shutdown();
		}
}
