package common;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @Package: common <br/>
 * @Description： 任务执行接口 <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2017/12/25 12:27 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2017 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2017/12/25. <br/>
 */
public interface TaskExecutor {
		void  addTask(Runnable task);
		Future<?>  addTask(Callable<?> task);

}
