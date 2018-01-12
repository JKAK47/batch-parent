package common.quartz.task001;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.springframework.stereotype.Component;

/**
 * @Package: common.quartz。QuartzJob <br/>
 * @Description： Quartz 任务调度程序一个 Job，实现Job 接口 <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/1/9 20:52 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2017 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/1/9. <br/>
 */
@Component("quartzJob")
public class QuartzJob implements Job {
		/**
		 * execute 是被调度器scheduler 到时间点调度的方法。
		 *
		 * @param jobExecutionContext
		 * @throws JobExecutionException
		 */
		@Override
		public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
				JobKey key = jobExecutionContext.getJobDetail().getKey();
				jobExecutionContext.getMergedJobDataMap();
				//job 唯一标识符
				System.out.println(key);
				JobDataMap map = jobExecutionContext.getJobDetail().getJobDataMap();
				String name = map.getString("name");
				System.out.println("quartzJob task 001....." + name);
		}
}
