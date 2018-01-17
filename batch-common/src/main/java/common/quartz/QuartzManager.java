package common.quartz;

import java.util.Date;
import java.util.Objects;
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

/**
 * batch-parent.common.quartz <br/>
 * Created by PengRong on 2018/1/9. <br/>
 * Quartz框架是一个全功能、开源的任务调度服务，可以集成几乎任何的java应用程序；
 * Quartz核心的概念：scheduler任务调度、
 * Job任务（开发者只要实现此接口，实现execute方法即可。把我们想做的事情，在execute中执行即可）、
 * Trigger触发器（执行任务的时间规则，SimpleTrigger，CronTrigger）、
 * JobDetail任务细节
 * <p>
 * http://www.quartz-scheduler.org/documentation/quartz-2.2.x/tutorials/tutorial-lesson-01.html
 * 本类是一个对Quartz调度的的管理类。
 *
 * @author PengRong <br/>
 * @Description TODO(${END})
 * @ClassName: ${CLASS}
 * @since 2018-01-09 18:58 <br/>
 */
public class QuartzManager {

		private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
		private static String JOB_GROUP_NAME = "JOBGROUP_NAME_Vincent";
		private static String JOB_NAME = "JOB_NAME_Vincent";
		private static String TRIGGER_GROUP_NAME = "TRIGGERGROUP_NAME_Vincent";
		private static String TRIGGER_NAME = "TRIGGER_NAME_Vincent";
		private static Scheduler sched = null;

		// 实例化调度器
		static {
				try {
						sched = StdSchedulerFactory.getDefaultScheduler();
				} catch (SchedulerException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
				}
		}

		/**
		 * 添加一个在指定时间运行的任务
		 *
		 * @param jobName        任务名
		 * @param cls            Job 类
		 * @param cronExpression cron表达式，描述任务启动时间
		 */
		public static void addCronJob(String jobName, Class cls, String cronExpression) {
				try {
						addCronJob(jobName, JOB_GROUP_NAME, TRIGGER_NAME, TRIGGER_GROUP_NAME, cls, null, cronExpression);
				} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException(e);
				}
		}

		/**
		 * 这个接口用于加入在指定时间调度执行任务,任务启动后立即启动 </br>
		 *
		 * @param jobName          任务Job名字</br>
		 * @param jobGroupName     任务Job组名</br>
		 * @param triggerName      触发器名字</br>
		 * @param triggerGroupName 触发器组名</br>
		 * @param jobClass         任务clasa</br>
		 * @param jobdatamap       传递给job的参数</br>
		 * @param cronExpression   Cron 表达式 参考官方文档</br>
		 */
		public static void addCronJob(String jobName, String jobGroupName,
		                              String triggerName, String triggerGroupName, Class jobClass, JobDataMap jobdatamap,
		                              String cronExpression) {
				try {
						if (jobClass == null) {
								throw new IllegalArgumentException("jobClass 参数不能为空.");
						}
						// 任务名，任务组，任务执行类
						JobBuilder jobBuilder = JobBuilder.newJob()
										.withIdentity(jobName == null ? JOB_NAME : jobName, jobGroupName == null ? JOB_GROUP_NAME : jobGroupName)
										.ofType(jobClass);
						if (Objects.nonNull(jobdatamap)) {
								jobBuilder.setJobData(jobdatamap);
						}
						// CronTrigger 触发器
						CronTrigger trigger = TriggerBuilder.newTrigger()
										.withIdentity(triggerName == null ? TRIGGER_NAME : triggerName, triggerGroupName == null ? TRIGGER_GROUP_NAME : triggerGroupName)
										.startAt(DateBuilder.futureDate(0, DateBuilder.IntervalUnit.MINUTE))
										.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
										.build();
						sched.scheduleJob(jobBuilder.build(), trigger);
						// 启动调度器
						if (!sched.isShutdown()) {
								sched.start();
						}
				} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException(e);
				}
		}

		/**
		 * 改任务提交一个周期性任务，可以自定义SimpleScheduleBuilder
		 *
		 * @param jobName          任务名
		 * @param jobGroupName     任务组名
		 * @param triggerName      触发器名
		 * @param triggerGroupName 触发器组名
		 * @param jobClass         任务类class
		 * @param map              JobDataMap，提交给job 的数据
		 * @param startDate        任务启动时间，不提供为new Date()
		 * @param scheduleBuilder  触发器调度Builder，指定了任务调度安排。
		 */
		public static void addSimpleJob(String jobName, String jobGroupName,
		                                String triggerName, String triggerGroupName, Class jobClass,
		                                JobDataMap map, Date startDate, SimpleScheduleBuilder scheduleBuilder) {
				try {
						if (jobClass == null) {
								throw new IllegalArgumentException("jobClass 参数不能为空.");
						}
						// 任务名，任务组，任务执行类
						JobBuilder jobBuilder = JobBuilder.newJob()
										.withIdentity(jobName == null ? JOB_NAME : jobName, jobGroupName == null ? JOB_GROUP_NAME : jobGroupName)
										.ofType(jobClass);
						if (Objects.nonNull(map)) {
								jobBuilder.setJobData(map);
						}
						// 执行任务间隔
						SimpleScheduleBuilder builder = scheduleBuilder;
						if (Objects.isNull(builder)) {
								builder = SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(24).repeatForever();
						}
						// SimpleTrigger 触发器
						SimpleTrigger trigger = TriggerBuilder.newTrigger()
										.withIdentity(triggerName == null ? TRIGGER_NAME : triggerName, triggerGroupName == null ? TRIGGER_GROUP_NAME : triggerGroupName)
										.startAt(startDate == null ? new Date() : startDate)
										.withSchedule(builder)
										.build();
						sched.scheduleJob(jobBuilder.build(), trigger);
						// 启动调度器
						if (!sched.isShutdown()) {
								sched.start();
						}
				} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("jobNmae= " + jobName + ", jobgroup=" + jobGroupName, e);
				}
		}

		/**
		 * 调度一个定时周期执行任务简化版
		 *
		 * @param jobName          任务名
		 * @param jobGroupName     任务组名
		 * @param triggerName      触发器名
		 * @param triggerGroupName 触发器组名
		 * @param jobClass         任务类class
		 * @param startDate        任务启动时间，不提供为new Date()
		 */
		public void addSimpleJob(String jobName, String jobGroupName,
		                         String triggerName, String triggerGroupName, Class jobClass,
		                         Date startDate) {
				addSimpleJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, null, startDate, null);
		}

		/**
		 * 提交一个基于CalendarIntervalTrigger触发器的周期性任务
		 *
		 * @param jobName          任务名
		 * @param jobGroupName     任务组名
		 * @param triggerName      触发器名
		 * @param triggerGroupName 触发器组名
		 * @param jobClass         任务类class
		 * @param map              JobDataMap，提交给job 的数据
		 * @param startDate        任务启动时间，不提供为new Date()
		 * @param scheduleBuilder  触发器调度Builder，指定了任务调度安排（CalendarIntervalScheduleBuilder）根据任务日历调度触发器,如果为赋值则以一天为间隔。
		 */
		public static void addCalendarJob(String jobName, String jobGroupName,
		                                  String triggerName, String triggerGroupName, Class jobClass,
		                                  JobDataMap map, Date startDate, CalendarIntervalScheduleBuilder scheduleBuilder) {
				try {
						if (jobClass == null) {
								throw new IllegalArgumentException("jobClass 参数不能为空.");
						}
						// 任务名，任务组，任务执行类
						JobBuilder jobBuilder = JobBuilder.newJob()
										.withIdentity(jobName == null ? JOB_NAME : jobName, jobGroupName == null ? JOB_GROUP_NAME : jobGroupName)
										.ofType(jobClass);
						if (Objects.nonNull(map)) {
								jobBuilder.setJobData(map);
						}
						// 执行任务间隔
						CalendarIntervalScheduleBuilder builder = scheduleBuilder;
						if (Objects.isNull(builder)) {
								builder = CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInDays(1);
						}
						// SimpleTrigger 触发器
						CalendarIntervalTrigger trigger = TriggerBuilder.newTrigger()
										.withIdentity(triggerName == null ? TRIGGER_NAME : triggerName, triggerGroupName == null ? TRIGGER_GROUP_NAME : triggerGroupName)
										.startAt(startDate == null ? new Date() : startDate)
										.withSchedule(builder)
										.build();
						sched.scheduleJob(jobBuilder.build(), trigger);
						// 启动调度器
						if (!sched.isShutdown()) {
								sched.start();
						}
				} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("jobNmae= " + jobName + ", jobgroup=" + jobGroupName, e);
				}
		}

		/**
		 * 提交一个基于CalendarIntervalTrigger触发器的周期性任务，间隔为一天调用一次。
		 *
		 * @param jobName          任务名
		 * @param jobGroupName     任务组名
		 * @param triggerName      触发器名
		 * @param triggerGroupName 触发器组名
		 * @param jobClass         任务类class
		 * @param startDate        任务启动时间，不提供为new Date()
		 */
		public static void addCalendarJob(String jobName, String jobGroupName,
		                                  String triggerName, String triggerGroupName, Class jobClass,
		                                  Date startDate) {
				addCalendarJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, null, startDate, null);
		}


		/**
		 * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
		 *
		 * @param jobName        任务名
		 * @param cronExpression cron表达式规则
		 */
		public static void modifyJobTime(String jobName, String triggerName, String cronExpression) {
				try {

						CronTrigger trigger = (CronTrigger) sched.getTrigger(new TriggerKey(triggerName, TRIGGER_GROUP_NAME));
						if (trigger == null) {
								return;
						}
						String oldTime = trigger.getCronExpression();
						if (!oldTime.equalsIgnoreCase(cronExpression)) {
								JobDetail jobDetail = sched.getJobDetail(new JobKey(jobName, JOB_GROUP_NAME));
								Class objJobClass = jobDetail.getJobClass();
								removeJob(jobName, triggerName);
								addCronJob(jobName, objJobClass, cronExpression);
						}
				} catch (Exception e) {
						throw new RuntimeException(e);
				}
		}

		/**
		 * @param jobName
		 * @param triggerName
		 * @Description: 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
		 * @Title: QuartzManager.java
		 * @Copyright: Copyright (c) 2014
		 * @author Comsys-LZP
		 * @date 2014-6-26 下午03:49:51
		 * @version V2.0
		 */
		public static void removeJob(String jobName, String triggerName) {
				removeJob(jobName, JOB_GROUP_NAME, triggerName, TRIGGER_GROUP_NAME);
		}

		/**
		 * @param jobName
		 * @param jobGroupName
		 * @param triggerName
		 * @param triggerGroupName
		 */
		public static void removeJob(String jobName, String jobGroupName,
		                             String triggerName, String triggerGroupName) {
				try {
						// 停止触发器
						sched.pauseTrigger(new TriggerKey(triggerName, triggerGroupName));
						// 移除触发器
						sched.unscheduleJob(new TriggerKey(triggerName, triggerGroupName));
						// 删除任务
						sched.deleteJob(new JobKey(jobName, jobGroupName));
				} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException(e);
				}
		}

		/**
		 * @Description:启动所有定时任务
		 * @Title: QuartzManager.java
		 * @Copyright: Copyright (c) 2014
		 * @author Comsys-LZP
		 * @date 2014-6-26 下午03:50:18
		 * @version V2.0
		 */
		public static void startJobs() {
				try {
						if (sched.isShutdown()) {
								sched.start();
						}
				} catch (Exception e) {
						throw new RuntimeException(e);
				}
		}

		/**
		 * @Description:关闭所有定时任务
		 * @Title: QuartzManager.java
		 * @Copyright: Copyright (c) 2014
		 * @author Comsys-LZP
		 * @date 2014-6-26 下午03:50:26
		 * @version V2.0
		 */
		public static void shutdownJobs() {
				try {
						if (!sched.isShutdown()) {
								sched.shutdown();
						}
				} catch (Exception e) {
						throw new RuntimeException(e);
				}
		}
}
