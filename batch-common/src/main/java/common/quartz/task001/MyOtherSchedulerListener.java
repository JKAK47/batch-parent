package common.quartz.task001;

import org.quartz.Trigger;
import org.quartz.listeners.SchedulerListenerSupport;

/**
 * batch-parent.common.quartz.task001 <br/>
 * Created by PengRong on 2018/1/12. <br/>
 * 监听Scheduler 监听器
 * @author PengRong <br/>
 * @Description TODO(${END})
 * @ClassName: ${CLASS}
 * @since 2018-01-12 15:56 <br/>
 */
public class MyOtherSchedulerListener  extends SchedulerListenerSupport {

    @Override
    public void schedulerStarted() {
        super.schedulerStarted();
        System.out.println("schedulerStarted");
    }

    @Override
    public void schedulerStarting() {
        super.schedulerStarting();
        System.out.println("schedulerStarting");
    }

    @Override
    public void schedulerShutdown() {
        super.schedulerShutdown();
        System.out.println("schedulerShutdown");
    }

    @Override
    public void jobScheduled(Trigger trigger) {
        super.jobScheduled(trigger);
        System.out.println("jobScheduled" + trigger.getKey().getName());
    }
}
