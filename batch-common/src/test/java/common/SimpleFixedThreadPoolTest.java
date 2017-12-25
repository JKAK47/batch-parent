package common;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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

    @Test
    public void testAddTaskCallable() throws ExecutionException, InterruptedException {
        Future<String> future=null;
        for (int i = 0; i < 20; i++) {
            future= (Future<String>) simpleFixedThreadPool.addTask((Callable<String>) () -> new Random().nextInt()+"\t Vincent");
            // 获取结果并打印
            System.out.println(future.get());
        }



    }

    /**
     * Method: shutdown()
     */
    @Test
    public void testShutdown() throws Exception {

    }


} 
