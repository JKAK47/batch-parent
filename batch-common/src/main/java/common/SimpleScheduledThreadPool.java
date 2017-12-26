package common;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * batch-parent.common <br/>
 * Created by PengRong on 2017/12/26. <br/>
 *
 * @author PengRong <br/>
 * @Description 周期性执行任务的简单封装实现
 * @ClassName: ${CLASS}
 * @since 2017-12-26 16:10 <br/>
 */
@Component("simpleScheduledThreadPool")
public class SimpleScheduledThreadPool implements TaskExecutor {
    private ScheduledExecutorService executor= Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()*2,
            new ThreadFactoryBuilder().setNameFormat("simpleScheduledThreadPool-%d: ").build());
    List<Future<?>> lists=new ArrayList<>(16);
    /**
     * 添加一个 延迟一秒后，一秒为间隔的执行task 任务
     * @param task
     */
    @Override
    public void addTask(Runnable task) {
        // 每次任务执行完后延迟一秒执行
        ScheduledFuture future=executor.scheduleWithFixedDelay(task,1,1, TimeUnit.SECONDS);
        lists.add(future);
    }

    /**
     *
     * @param task
     * @param <T>
     * @return
     */
    @Override
    public <T> Future<T> addTask(Callable<T> task) {
        ScheduledFuture<T> future =  executor.schedule(task,1,TimeUnit.SECONDS);
        lists.add(future);
        return future;
    }

    /**
     *  关闭线程池
     */
    public void  shutdown(){
        // 关闭所有定时任务
        for (Future<?> future:lists){
            future.cancel(true);
        }
        executor.shutdown();
    }
}
