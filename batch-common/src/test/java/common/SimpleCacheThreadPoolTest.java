package common;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * SimpleCacheThreadPool Tester.
 *
 * @author <PengRong>
 * @version 1.0
 * @since <pre>12/26/2017</pre>
 */
public class SimpleCacheThreadPoolTest extends BaseTest {

    @Autowired
    private  SimpleCacheThreadPool simpleCacheThreadPool;
    @BeforeClass
    public static void before() throws Exception {
        System.out.println("before Tester begin");

    }

    @AfterClass
    public static void after() throws Exception {
        System.out.println("after Tester stop");
    }


    /**
     * Method: addTask(Runnable task)
     */
    @Test
    public void testAddTask() throws Exception {
        final int[] j = {0};
        long time=System.currentTimeMillis();
        logger.info("begin time= "+(time)/1000);
        for (int i = 0; i < 90; i++) {
            simpleCacheThreadPool.addTask(() -> {
                try {
                    Thread.sleep(10);
                    logger.debug(new Date() + "\t time =" + System.currentTimeMillis() + Thread.currentThread().getName() + "\t" + (j[0]++));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        simpleCacheThreadPool.shutdown();
        System.out.println("stop time = "+(System.currentTimeMillis()-time)/1000);
        Thread.sleep(10000);
    }

    @Test
    public void testAddTaskCallable() throws ExecutionException, InterruptedException {
        Future<String> future=null;
        List<Future<String>> lists=new ArrayList<>(200);
        // 提交任务并获取到能拿到结果的类示例 Future
        for (int i = 0; i < 200; i++) {
            future= (Future<String>) simpleCacheThreadPool.addTask((Callable<String>) () -> "Thread "+Thread.currentThread().getName()+"\t"+new Random().nextInt()+"\t Vincent");
            lists.add(future);
        }
        // 遍历Future类的List 列表；获取结果并打印
        System.out.println("System: >>> "+future.get());
        for (Future<String> fu:lists){
            logger.info(fu.get());
        }
    }

    /**
     * Method: shutdown()
     */
    @Test
    public void testShutdown() throws Exception {
        System.out.println(String.format("%,.2f",  1224.344));
    }


} 
