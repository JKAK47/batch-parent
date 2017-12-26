package common;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * batch-parent.common <br/>
 * Created by PengRong on 2017/12/26. <br/>
 *
 * @author PengRong <br/>
 *
 * @Description  带有cache 功能的线程池,线程池最大线程数为Integer.MAX_VALUE，核心线程数为 0；线程空闲时间 1Min，线程工作队列是SynchronousQueue
 * 一个不存储元素的阻塞队列，每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态。这个线程池的特点是 应对短期突发性简单性任务具有优势。
 * @ClassName: ${CLASS}
 * @since 2017-12-26 13:13 <br/>
 */
@Component("simpleCacheThreadPool")
public class SimpleCacheThreadPool implements TaskExecutor{
    private ExecutorService executor= Executors.newCachedThreadPool(new ThreadFactoryBuilder().setNameFormat("simpleCacheThreadPool-%d: ").build());
    @Override
    public void addTask(Runnable task) {
        executor.execute(task);
    }

    @Override
    public <T> Future<T> addTask(Callable<T> task) {
       Future<T> future= executor.submit(task);
        return future;
    }

    /**
     *  关闭线程池
     */
    public void  shutdown(){
        executor.shutdown();
    }
}
