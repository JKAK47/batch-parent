package common.quartz.spring.task002;

import org.springframework.stereotype.Component;

/**
 * @Package: common.quartz.spring.task002 <br/>
 * @Description： 这个类设置成被SimpleJob 引用从而被调用的Job。 <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/1/14 12:40 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2017 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/1/14. <br/>
 */
@Component("anotherBean")
public class AnotherBean {
		public void printAnotherMessage(){
				System.out.println("I am called by Quartz jobBean using CronTriggerFactoryBean");
		}
}
