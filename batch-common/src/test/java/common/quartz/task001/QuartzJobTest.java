package common.quartz.task001;

import java.util.Date;
import org.junit.Test;
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
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.spi.MutableTrigger;

/**
 * QuartzJob Tester.
 * https://www.w3cschool.cn/quartz_doc/quartz_doc-2put2clm.html 英翻译中 教程
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
				JobDataMap map=new JobDataMap();
				map.put("name","vincent");
				map.put("copyright","PLCC");
				map.put("key1","123456");
				map.put("key2","sdfds");
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
								startAt(DateBuilder.evenMinuteDate(new Date()) ). //DateBuilder.evenMinuteDate(new Date())  设置日期为给定日期的下一分钟。
								withSchedule(scheduleBuilder).
								build();
				scheduler.scheduleJob(detail, trigger);
				scheduler.start();
				Thread.sleep(100000);
		}

		@Test
		public  void testDate(){
				Date date=DateBuilder.evenMinuteDate(new Date(2018,1,10,22,20,10));
				System.out.println(date.getMinutes());
		}
		/**
		 * 指定时间点执行，到指定时间点调度
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
				JobDataMap map=new JobDataMap();
				map.put("name","vincent");
				map.put("copyright","PLCC");
				map.put("key1","123456");
				map.put("key2","sdfds");

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
								startAt(DateBuilder.futureDate(1,DateBuilder.IntervalUnit.MINUTE)). //设置在执行时间后一分钟启动任务
								build();
				//将任务和触发器注册到调度器中
				scheduler.scheduleJob(detail, trigger);
				//启动调度器
				scheduler.start();
				Thread.sleep(1000000);
				scheduler.shutdown();
		}
} 
