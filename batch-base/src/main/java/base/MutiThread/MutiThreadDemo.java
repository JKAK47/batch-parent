package base.MutiThread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MutiThreadDemo {

	/**
	 * 通过线程池创建固定个线程的线程池，线程池包含三个线程，还有一个Main线程
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	private static void testFixedThreadPool() throws InterruptedException, ExecutionException {  
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);  
        for (int i = 0; i < 10; i++) {  
            final int index = i;  
            fixedThreadPool.execute(new Runnable() {  
                public void run() {  
                    try {  
                        System.out.println("threadName: "+Thread.currentThread().getName()+"\t"+index);  
                        Thread.sleep(2000);  
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    }  
                }  
            });  
        }  
        System.out.println("当前线程数：" + Runtime.getRuntime().availableProcessors()); 
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
  
    public static void main(String[] args) throws ExecutionException, InterruptedException {  
        //testFixedThreadPool();  
    	testCachedThreadPool();
        System.out.println("zhongg");
    } 
    
    /**
     * newCachedThreadPool创建一个可缓存的线程池。
     * 1).工作线程的创建数量几乎没有限制(其实也有限制的,数目为Interger. MAX_VALUE), 这样可灵活的往线程池中添加线程。 
        2).如果长时间没有往线程池中提交任务，即如果工作线程空闲了指定的时间(默认为1分钟)，则该工作线程将自动终止。终止后，如果你又提交了新的任务，则线程池重新创建一个工作线程。
     */
    private static void testCachedThreadPool() throws InterruptedException, ExecutionException {  
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();  
        for (int i = 0; i < 100; i++) {  
            final int index = i;  
            Thread.sleep(index * 10);  
            cachedThreadPool.execute(new Runnable() {  
                public void run() {  
                    System.out.println("threadName: "+Thread.currentThread().getName()+"\t"+index);  
                }  
            });  
        }  
    } 
}
