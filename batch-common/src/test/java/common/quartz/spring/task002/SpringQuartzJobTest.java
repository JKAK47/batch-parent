package common.quartz.spring.task002; 

import common.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** 
* SpringQuartzJob Tester.
* 
* @author <PengRong> 
* @since  
* @version 1.0 
*/ 
public class SpringQuartzJobTest extends BaseTest{

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: execute() 
* 
*/ 
@Test
public void testExecute() throws Exception {
		 System.out.println("Spring 和 Quartz 框架集成 测试.");
		 Thread.sleep(1000000);
}


} 
