package common.quartz.task001;

import org.junit.Test;
import org.quartz.CalendarIntervalScheduleBuilder;
import org.quartz.CalendarIntervalTrigger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.spi.MutableTrigger;

import java.util.Date;

/**
 * QuartzJob Tester.
 * https://www.w3cschool.cn/quartz_doc/quartz_doc-2put2clm.html 英翻译中 教程
 * 该类包含一些常见调度规则配置案例；作为一个教程配置
 * @author <PengRong>
 * @version 1.0
 */
public class QuartzJobTest {
    /**
     * Method: execute(JobExecutionContext jobExecutionContext)
     * 周期执行，每隔固定时间调度器调度Job执行
     */
    @Test
    public void testMutableTrigger() throws Exception {
        //调度器工厂
        SchedulerFactory factory = new StdSchedulerFactory();
        // Job调度器
        Scheduler scheduler = factory.getScheduler();
        //job 任务的唯一标识
        JobKey key = new JobKey("job-quartz-task-001", "vincent");
        JobDataMap map = new JobDataMap();
        map.put("name", "vincent");
        map.put("copyright", "PLCC");
        map.put("key1", "123456");
        map.put("key2", "sdfds");
        //创建 jobDetail 实例，绑定Job实例； 真正执行的任务并不是Job接口的实例，而是用反射的方式实例化的一个JobDetail实例
        JobDetail detail = JobBuilder.newJob(QuartzJob.class).
                withIdentity(key).
                setJobData(map).
                build();
        //triggerKey 唯一标识触发器的
        TriggerKey triggerKey = new TriggerKey("trigger-quartz-task-001", "vincent");
        //设置触发器的时刻安排表;  SimpleScheduleBuilder
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.
                simpleSchedule().
                withIntervalInSeconds(1).// 间隔一秒调度一次
                withRepeatCount(8);//设置调度次数
        // TriggerBuilder创建 MutableTrigger futureDate
//SimpleTrigger主要用于一次性执行的Job（只在某个特定的时间点执行一次），
// 或者Job在特定的时间点执行，重复执行N次，每次执行间隔T个时间单位。
        MutableTrigger trigger = (MutableTrigger) TriggerBuilder.newTrigger().
                withDescription("MutalbeTrigger instance").
                withIdentity(triggerKey).
                startAt(DateBuilder.evenMinuteDate(new Date())). //DateBuilder.evenMinuteDate(new Date())  设置启动日期时间为启动时间的下一分钟。
                withSchedule(scheduleBuilder).
                build();
        scheduler.scheduleJob(detail, trigger);
        scheduler.start();
        Thread.sleep(100000);
    }

    @Test
    public void testDate() {
        Date date = DateBuilder.evenMinuteDate(new Date(2018, 1, 10, 22, 20, 10));
        System.out.println(date.getMinutes());
    }

    /**
     * 在测试案例启动后一分钟后触发器启动，任务指定时间点执行，到指定时间点（00 01 00）调度
     *
     * @throws SchedulerException
     * @throws InterruptedException
     */
    @Test
    public void testCornTrigger() throws SchedulerException, InterruptedException {
        //调度器工厂
        SchedulerFactory factory = new StdSchedulerFactory();
        // Job调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //job 任务的唯一标识
        JobKey key = new JobKey("job-quartz-task-001", "vincent");
        // 设置 JobDataMap，可以携带一些执行定时任务的参数，带到job 执行的方法execute方法里面。
        JobDataMap map = new JobDataMap();
        map.put("name", "vincent");
        map.put("copyright", "PLCC");
        map.put("key1", "123456");
        map.put("key2", "sdfds");

        //创建 jobDetail 实例，绑定Job实例
        JobDetail detail = JobBuilder.newJob(QuartzJob.class).
                withIdentity(key).
                withDescription("CornTrigger Tester").
                setJobData(map).
                build();
        //triggerKey 唯一标识触发器的
        TriggerKey triggerKey = new TriggerKey("trigger-cron-quartz-task-001", "vincent");
        // 定义调度触发规则，每天23：25分调度运行 QuartzJob 作业；
        // CronScheduleBuilder 定义Quartz 的时间规则
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 01 00 * * ? *");
        //定义触发器规则
        //CronTrigger在基于日历的调度上非常有用，如“每个星期五的正午”，或者“每月的第十天的上午10:15”等
        CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger().
                withIdentity(triggerKey).
                withSchedule(cronScheduleBuilder).
                startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.MINUTE)). //设置在执行时间后一分钟启动任务
                build();
        //将任务和触发器注册到调度器中
        scheduler.scheduleJob(detail, trigger);
        //启动调度器
        scheduler.start();
        Thread.sleep(1000000);
        scheduler.shutdown();
    }

    /**
     * Trigger That Executes Every Day
     * 测试启动后一分钟启动触发器；Using CronTrigger 设置每天 11点触发任务调度执行；
     */
    @Test
    public void testExecutesEveryDayCronTrigger() throws SchedulerException, InterruptedException {
        // Job调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //job 任务的唯一标识
        JobKey key = new JobKey("job-quartz-task-001", "vincent");
        // 设置 JobDataMap，可以携带一些执行定时任务的参数，带到job 执行的方法execute方法里面。
        JobDataMap map = new JobDataMap();
        map.put("name", "vincent");
        map.put("copyright", "PLCC");
        map.put("key1", "123456");
        map.put("key2", "sdfds");

        //创建 jobDetail 实例，绑定Job实例
        JobDetail detail = JobBuilder.newJob(QuartzJob.class).
                withIdentity(key).
                withDescription("CornTrigger Tester").
                setJobData(map).
                build();
        //triggerKey 唯一标识触发器的
        TriggerKey triggerKey = new TriggerKey("trigger-cron-quartz-task-001", "vincent");
        // 定义调度触发规则，每天23：25分调度运行 QuartzJob 作业；
        // CronScheduleBuilder 定义Quartz 的时间规则,在每天的 11点开跑 Using CronTrigger
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.dailyAtHourAndMinute(11, 0);
        //定义触发器规则
        //CronTrigger在基于日历的调度上非常有用，如“每个星期五的正午”，或者“每月的第十天的上午10:15”等
        CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger().
                withIdentity(triggerKey).
                withSchedule(cronScheduleBuilder).
                startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.MINUTE)). //设置在执行时间后一分钟启动并调度任务。但是如果调度指定了调度时间这个设置没有用
                build();
        //将任务和触发器注册到调度器中
        scheduler.scheduleJob(detail, trigger);
        //启动调度器
        scheduler.start();
        Thread.sleep(1000000);
        scheduler.shutdown();
    }

    /**
     * @throws SchedulerException
     * @throws InterruptedException
     * 在每天固定时间出发，Using SimpleTrigger 设置 启动后开跑，但是第二次开跑间距为 24小时（一天）(startAt 设置有效)
     */

    @Test
    public void testExecutesEveryDaySimpleTrigger() throws SchedulerException, InterruptedException {
        // Job调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //job 任务的唯一标识
        JobKey key = new JobKey("job-quartz-task-001", "vincent");
        // 设置 JobDataMap，可以携带一些执行定时任务的参数，带到job 执行的方法execute方法里面。
        JobDataMap map = new JobDataMap();
        map.put("name", "vincent");
        map.put("copyright", "PLCC");
        map.put("key1", "123456");
        map.put("key2", "sdfds");

        //创建 jobDetail 实例，绑定Job实例
        JobDetail detail = JobBuilder.newJob(QuartzJob.class).
                withIdentity(key).
                withDescription("SimpleTrigger Tester").
                setJobData(map).
                build();
        //triggerKey 唯一标识触发器的
        TriggerKey triggerKey = new TriggerKey("trigger-cron-quartz-task-001", "vincent");
        // 定义调度触发规则，每天23：25分调度运行 QuartzJob 作业；
        // CronScheduleBuilder 定义Quartz 的时间规则,在每天的 11点开跑 Using SimpleTrigger
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(24).repeatForever();
        //定义触发器规则
        //CronTrigger在基于日历的调度上非常有用，如“每个星期五的正午”，或者“每月的第十天的上午10:15”等
        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().
                withIdentity(triggerKey).
                withSchedule(simpleScheduleBuilder).
                startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.MINUTE)). //设置在一分钟后启动
                build();
        //将任务和触发器注册到调度器中
        scheduler.scheduleJob(detail, trigger);
        //启动调度器
        scheduler.start();
        Thread.sleep(1000000);
        scheduler.shutdown();
    }

    /**
     * 基于 CalendarIntervalTrigger 触发器创建基于时间的周期性调用；改案例是在测试启动后十秒每隔五秒调用一次。(startAt 设置有效)
     * @throws SchedulerException
     * @throws InterruptedException
     */
    @Test
    public void testExecutesEveryDayCalendarIntervalTrigger() throws SchedulerException, InterruptedException {
        // Job调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //job 任务的唯一标识
        JobKey key = new JobKey("job-quartz-task-001", "vincent");
        // 设置 JobDataMap，可以携带一些执行定时任务的参数，带到job 执行的方法execute方法里面。
        JobDataMap map = new JobDataMap();
        map.put("name", "vincent");
        map.put("copyright", "PLCC");
        map.put("key1", "123456");
        map.put("key2", "sdfds");

        //创建 jobDetail 实例，绑定Job实例
        JobDetail detail = JobBuilder.newJob(QuartzJob.class).
                withIdentity(key).
                withDescription("SimpleTrigger Tester").
                setJobData(map).
                build();
        //triggerKey 唯一标识触发器的
        TriggerKey triggerKey = new TriggerKey("trigger-cron-quartz-task-001", "vincent");
        // 定义调度触发规则，每天23：25分调度运行 QuartzJob 作业；
        // Using CalendarIntervalTrigger 定义Quartz 的时间规则,
        CalendarIntervalScheduleBuilder calendarIntervalScheduleBuilder = CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInSeconds(5);
        //定义触发器规则
        // CalendarIntervalTrigger 在基于日历的调度上非常有用，如“每个星期五的正午”，或者“每月的第十天的上午10:15”等
        CalendarIntervalTrigger trigger =  TriggerBuilder.newTrigger().
                withIdentity(triggerKey).
                withSchedule(calendarIntervalScheduleBuilder).
                startAt(DateBuilder.futureDate(10, DateBuilder.IntervalUnit.SECOND)). //设置在一分钟后启动
                build();
        //将任务和触发器注册到调度器中
        scheduler.scheduleJob(detail, trigger);
        //启动调度器
        scheduler.start();
        Thread.sleep(1000000);
        scheduler.shutdown();
    }

    /**
     * 设置一个调度案例，在测试案例启动后一分钟调度任务一次，然后按照每两天的调度间隔进行调度 (startAt 设置有效)
     * @throws SchedulerException
     * @throws InterruptedException
     */
    @Test
    public  void  test2DayInvoke() throws SchedulerException, InterruptedException {
        // Job调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //job 任务的唯一标识
        JobKey key = new JobKey("job-quartz-task-001", "vincent");
        // 设置 JobDataMap，可以携带一些执行定时任务的参数，带到job 执行的方法execute方法里面。
        JobDataMap map = new JobDataMap();
        map.put("name", "vincent");
        map.put("copyright", "PLCC");
        map.put("key1", "123456");
        map.put("key2", "sdfds");

        //创建 jobDetail 实例，绑定Job实例
        JobDetail detail = JobBuilder.newJob(QuartzJob.class).
                withIdentity(key).
                withDescription("SimpleTrigger Tester").
                setJobData(map).
                build();
        //triggerKey 唯一标识触发器的
        TriggerKey triggerKey = new TriggerKey("trigger-cron-quartz-task-001", "vincent");
        // 定义调度触发规则，每天23：25分调度运行 QuartzJob 作业；
        // Using CalendarIntervalTrigger 定义Quartz 的时间规则,
        CalendarIntervalScheduleBuilder calendarIntervalScheduleBuilder = CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInDays(2);
        //定义触发器规则
        // CalendarIntervalTrigger 在基于日历的调度上非常有用，如“每个星期五的正午”，或者“每月的第十天的上午10:15”等
        CalendarIntervalTrigger trigger =  TriggerBuilder.newTrigger().
                withIdentity(triggerKey).
                withSchedule(calendarIntervalScheduleBuilder).
                startAt(DateBuilder.futureDate(10, DateBuilder.IntervalUnit.SECOND)). //设置在10秒钟后启动
                build();
        //将任务和触发器注册到调度器中
        scheduler.scheduleJob(detail, trigger);
        //启动调度器
        scheduler.start();
        Thread.sleep(1000000);
        scheduler.shutdown();
    }

    /**
     * 设置 在每周三15 点调度任务（CronTrigger 调度器）（CronTrigger 触发器下 ，startAt 设置无效。因为指定了跑的时间）
     * @throws SchedulerException
     * @throws InterruptedException
     */
    @Test
    public void testWeekInvoke() throws SchedulerException, InterruptedException {
        // Job调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //job 任务的唯一标识
        JobKey key = new JobKey("job-quartz-task-001", "vincent");
        // 设置 JobDataMap，可以携带一些执行定时任务的参数，带到job 执行的方法execute方法里面。
        JobDataMap map = new JobDataMap();
        map.put("name", "vincent");
        map.put("copyright", "PLCC");
        map.put("key1", "123456");
        map.put("key2", "sdfds");

        //创建 jobDetail 实例，绑定Job实例
        JobDetail detail = JobBuilder.newJob(QuartzJob.class).
                withIdentity(key).
                withDescription("SimpleTrigger Tester").
                setJobData(map).
                build();
        //triggerKey 唯一标识触发器的
        TriggerKey triggerKey = new TriggerKey("trigger-cron-quartz-task-001", "vincent");
        // 定义调度触发规则，每天23：25分调度运行 QuartzJob 作业；
        // Using CronScheduleBuilder 定义Quartz 的时间规则,每周三下午 15 点跑
        CronScheduleBuilder builder=CronScheduleBuilder.weeklyOnDayAndHourAndMinute(DateBuilder.WEDNESDAY,15,0);
        //定义触发器规则
        // CalendarIntervalTrigger 在基于日历的调度上非常有用，如“每个星期五的正午”，或者“每月的第十天的上午10:15”等
        CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger().
                withIdentity(triggerKey).
                withSchedule(builder).
                startAt(DateBuilder.futureDate(10, DateBuilder.IntervalUnit.SECOND)). //设置在启动后10秒后调用一次
                build();
        //将任务和触发器注册到调度器中
        scheduler.scheduleJob(detail, trigger);
        //启动调度器
        scheduler.start();
        Thread.sleep(1000000);
        scheduler.shutdown();
    }


    /**
     * 设置一个触发器，他是每隔三天在下午 20180112T15:47:50点触发; 并能监听scheduler
     */
    @Test
    public  void test3Day15Time() throws SchedulerException, InterruptedException {
        // Job调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //job 任务的唯一标识
        JobKey key = new JobKey("job-quartz-task-001", "vincent");
        // 设置 JobDataMap，可以携带一些执行定时任务的参数，带到job 执行的方法execute方法里面。
        JobDataMap map = new JobDataMap();
        map.put("name", "vincent");
        map.put("copyright", "PLCC");
        map.put("key1", "123456");
        map.put("key2", "sdfds");

        //创建 jobDetail 实例，绑定Job实例
        JobDetail detail = JobBuilder.newJob(QuartzJob.class).
                withIdentity(key).
                withDescription("SimpleTrigger Tester").
                setJobData(map).
                build();
        //triggerKey 唯一标识触发器的
        TriggerKey triggerKey = new TriggerKey("trigger-cron-quartz-task-001", "vincent");
        // 定义调度触发规则，每天23：25分调度运行 QuartzJob 作业；
        // Using CronScheduleBuilder 定义Quartz 的时间规则,每隔三天调用一次。
        CalendarIntervalScheduleBuilder builder=CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInDays(3);
        //定义触发器规则
        // CalendarIntervalTrigger 在基于日历的调度上非常有用，如“每个星期五的正午”，或者“每月的第十天的上午10:15”等
        CalendarIntervalTrigger trigger = (CalendarIntervalTrigger) TriggerBuilder.newTrigger().
                withIdentity(triggerKey).
                withSchedule(builder).
                startAt(DateBuilder.dateOf(15,47,50,12,1,2018)). //设置在启动后10秒后调用一次
                build();
        //将任务和触发器注册到调度器中
        scheduler.scheduleJob(detail, trigger);
        //加入监听Scheduler
        scheduler.getListenerManager().addSchedulerListener(new MyOtherSchedulerListener());
        //启动调度器
        scheduler.start();
        Thread.sleep(1000000);
        scheduler.shutdown();
    }


} 
