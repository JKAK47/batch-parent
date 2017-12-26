package common;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * SimpleScheduledThreadPool Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>12/26/2017</pre>
 */
public class SimpleScheduledThreadPoolTest extends BaseTest{
    @Autowired
    private SimpleScheduledThreadPool simpleScheduledThreadPool;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * 周期性执行任务的线程池 执行 10秒好 关闭线程池退出
     * Method: addTask(Runnable task)
     */
    @Test
    public void testAddTaskTask() throws Exception {
            simpleScheduledThreadPool.addTask(new Runnable() {
                @Override
                public void run() {
                    System.out.println("threadName = "+Thread.currentThread().getName()+" ,time :>> "+(System.currentTimeMillis()/1000));
                }
            });
            Thread.sleep(10000);
            simpleScheduledThreadPool.shutdown();

    }

    /**
     * Method: addTask(Callable task)
     */
    @Test
    public void testAddTaskTaskCallable() throws Exception {
        Future<String> future = simpleScheduledThreadPool.addTask(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Callable--threadName = "+Thread.currentThread().getName()+" ,time :>> "+(System.currentTimeMillis()/1000);
            }
        });

        Thread.sleep(1000);
        System.out.println(future.get());
        simpleScheduledThreadPool.shutdown();
    }


} 
