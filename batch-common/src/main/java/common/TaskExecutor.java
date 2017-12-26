package common;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @Package: common <br/>
 * @Description： 线程池任务执行接口 <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2017/12/25 12:27 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2017 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2017/12/25. <br/>
 */
public interface TaskExecutor {
    /**
     * 添加一个任务，任务无返回值
     *
     * @param task
     */
    void addTask(Runnable task);

    /**
     * 添加一个具有一个返回值的任务，返回值类型为Future类型
     *
     * @param task
     * @return
     */
    <T> Future<T> addTask(Callable<T> task);

}
