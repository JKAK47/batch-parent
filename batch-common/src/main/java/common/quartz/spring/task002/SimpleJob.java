package common.quartz.spring.task002;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * batch-parent.common.quartz.spring <br/>
 * Created by PengRong on 2018/1/12. <br/>
 * Spring + Quartz 集成后，使用JobDetailFactoryBean来管理作业任务时，我们的作业任务实现类需要继承QuartzJobBean类，并覆盖其executeInternal方法。
 * 这个Quartz 作业Bean 有点不友好
 * @author PengRong <br/>
 * @Description TODO(${END})
 * @ClassName: ${CLASS}
 * @since 2018-01-12 16:19 <br/>
 */
//@Component("simpleJob") Quartz 调度任务通过class 反射调用的。

/**
 * 如果你需要持久化 JobDataMap里面的数据你就需要PersistJobDataAfterExecution注解Job；
 * 如果多个触发器调度Scheduling同一个job，为了避免竞争冲突。使用DisallowConcurrentExecution 注解Job
  */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class SimpleJob extends QuartzJobBean {
    public static final String COUNT = "key3";
    private AnotherBean anotherBean;

    public void setAnotherBean(AnotherBean anotherBean) {
        this.anotherBean = anotherBean;
    }

    /**
     * SimpleJob 任务Job 类
     * @param context
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        JobKey key = context.getJobDetail().getKey();
        //job 唯一标识符
        System.out.println("时间是"+new SimpleDateFormat("yyyy-MM-dd- HH-时-mm-分-ss-秒-").format(new Date())+"key: "+key);
        JobDataMap map = context.getJobDetail().getJobDataMap();
        String name = map.getString("name");
        int count=map.getInt(COUNT);
        System.out.print("quartzJob task Spring....." + name+"\tkey: "+key+"\t "+count++);
        map.put(COUNT,count);
        anotherBean.printAnotherMessage();
    }
}
