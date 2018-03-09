package org.vincent.rediscache.service;

import java.util.List;

/**
 * batch-parent.org.vincent.rediscache.service <br/>
 * Created by PengRong on 2018/3/9. <br/>
 *
 * @author PengRong <br/>
 * @Description TODO(${END})
 * @ClassName: ${CLASS}
 * @since 2018-03-09 10:03 <br/>
 */
public interface CacheService {
    public void addMessage(String user,String message);
    public List<String> listMessages(String user);

}
