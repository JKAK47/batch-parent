package common.quartz; 

import common.BaseTest;
import common.quartz.task001.QuartzJob;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/** 
* QuartzManager Tester. 
* Quartz Manager 管理器测试类
* @author <PengRong> 
* @since  
* @version 1.0 
*/ 
public class QuartzManagerTest extends BaseTest{

@BeforeClass
public static void before() throws Exception {
		QuartzManager.startJobs();
} 

@AfterClass
public static void after() throws Exception {
		QuartzManager.shutdownJobs();
} 

/** 
* 
* Method: addCronJob(String jobName, Class cls, String cronExpression) 
* 
*/ 
@Test
public void testAddCronJobForJobNameClsCronExpression() throws Exception {
		QuartzManager.addCronJob("cron-job-demo-1", QuartzJob.class,"0/15 50 22 * * ?");
		Thread.sleep(100000);
}

/** 
* 
* Method: addCronJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class jobClass, String cronExpression) 
* 
*/ 
@Test
public void testAddCronJobForJobNameJobGroupNameTriggerNameTriggerGroupNameJobClassCronExpression() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: addSimpleJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class jobClass, JobDataMap map, Date startDate, SimpleScheduleBuilder scheduleBuilder) 
* 
*/ 
@Test
public void testAddSimpleJobForJobNameJobGroupNameTriggerNameTriggerGroupNameJobClassMapStartDateScheduleBuilder() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: addSimpleJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class jobClass, Date startDate) 
* 
*/ 
@Test
public void testAddSimpleJobForJobNameJobGroupNameTriggerNameTriggerGroupNameJobClassStartDate() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: addCalendarJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class jobClass, JobDataMap map, Date startDate, CalendarIntervalScheduleBuilder scheduleBuilder) 
* 
*/ 
@Test
public void testAddCalendarJobForJobNameJobGroupNameTriggerNameTriggerGroupNameJobClassMapStartDateScheduleBuilder() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: addCalendarJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class jobClass, Date startDate) 
* 
*/ 
@Test
public void testAddCalendarJobForJobNameJobGroupNameTriggerNameTriggerGroupNameJobClassStartDate() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: modifyJobTime(String jobName, String triggerName, String cronExpression) 
* 
*/ 
@Test
public void testModifyJobTime() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: removeJob(String jobName, String triggerName) 
* 
*/ 
@Test
public void testRemoveJobForJobNameTriggerName() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) 
* 
*/ 
@Test
public void testRemoveJobForJobNameJobGroupNameTriggerNameTriggerGroupName() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: startJobs() 
* 
*/ 
@Test
public void testStartJobs() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: shutdownJobs() 
* 
*/ 
@Test
public void testShutdownJobs() throws Exception { 
//TODO: Test goes here... 
} 


} 
