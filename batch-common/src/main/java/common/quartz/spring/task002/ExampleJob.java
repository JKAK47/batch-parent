package common.quartz.spring.task002;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * batch-parent.common.quartz.spring.task002 <br/>
 * Created by PengRong on 2018/1/12. <br/>
 * Spring + quartz 集成后 任务作业Bean ：MethodInvokingJobDetailFactoryBean 的作业类。不用继承任何类和接口
 * @author PengRong <br/>
 * @Description TODO(${END})
 * @ClassName: ${CLASS}
 * @since 2018-01-12 16:36 <br/>
 */
@Component("exampleJob")
public class ExampleJob {
    public void execute(){
        System.out.println("现在是"+new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date())+"\tI am called by MethodInvokingJobDetailFactoryBean using SimpleTriggerFactoryBean");
    }
}
