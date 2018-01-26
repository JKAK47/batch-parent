package common;

/**
 * batch-parent.common <br/>
 * Created by PengRong on 2018/1/26. <br/>
 *
 * @author PengRong <br/>
 * @Description TODO(${END})
 * @ClassName: ${CLASS}
 * @since 2018-01-26 14:09 <br/>
 */

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 单例模式实现，静态私有类实现。
 * @author  pengrong
 *
 */
public class Singleton {
    private Singleton() {
    }
    public static Singleton getInstance() {
        return Holder.instance;
    }
    private static class Holder{
        private volatile static Singleton instance = new Singleton();
    }
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService= Executors.newCachedThreadPool();
        for (int i = 0; i < 10000; i++) {
            Future<Integer> future=executorService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return Singleton.getInstance().hashCode();
                }
            });
            System.out.println("第 "+(i+1)+" 个"+future.get());;
        }
        executorService.shutdown();
    }
}