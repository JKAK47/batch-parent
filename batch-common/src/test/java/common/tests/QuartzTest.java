package common.tests;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

/**
 * batch-parent.common.tests <br/>
 * Created by PengRong on 2018/1/9. <br/>
 * Quartz 调度程序 测试用例
 * 下面的代码获取Quartz调度程序的实例，启动它，然后关闭它：
 * @author PengRong <br/>
 * @Description TODO(${END})
 * @ClassName: ${CLASS}
 * @since 2018-01-09 14:17 <br/>
 *
    // define the job and tie it to our HelloJob class
    JobDetail job = newJob(HelloJob.class)
    .withIdentity("job1", "group1")
    .build();

    // Trigger the job to run now, and then repeat every 40 seconds
    Trigger trigger = newTrigger()
    .withIdentity("trigger1", "group1")
    .startNow()
    .withSchedule(simpleSchedule()
    .withIntervalInSeconds(40)
    .repeatForever())
    .build();

    // Tell quartz to schedule the job using our trigger
    scheduler.scheduleJob(job, trigger);
 */
public class QuartzTest {
    public static void main(String[] args) {

        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // and start it off
            scheduler.start();

            scheduler.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}
