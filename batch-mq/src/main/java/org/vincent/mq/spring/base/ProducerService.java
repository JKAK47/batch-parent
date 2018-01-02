package org.vincent.mq.spring.base;

import javax.jms.Destination;

/**
 * batch-parent.spring.base <br/>
 * Created by PengRong on 2018/1/2. <br/>
 * Spring + ActiveMQ集成后，生产者接口
 * @author PengRong <br/>
 * @Description TODO(${END})
 * @ClassName: ${CLASS}
 * @since 2018-01-02 13:30 <br/>
 */
public interface ProducerService {
    public void sendMessage(Destination destination, final String message);
}
