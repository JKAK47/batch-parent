package org.vincent.rediscache;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.vincent.rediscache.service.CacheService;

import javax.annotation.Resource;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

/**
 * batch-parent.org.vincent.rediscache <br/>
 * Created by PengRong on 2018/3/9. <br/>
 *https://dzone.com/articles/integrate-redis-to-your-spring-project
 * @author PengRong <br/>
 * @Description TODO(${END})
 * @ClassName: ${CLASS}
 * @since 2018-03-09 10:04 <br/>
 */
public class CacheRedisImpl  implements CacheService{

    @Resource(name = "redisTemplate")
    private ListOperations<String, String> messageList;
    @Resource(name = "redisTemplate")
    private RedisOperations<String,String> latestMessageExpiration;
    @Override
    public void addMessage(String user, String message) {
        messageList.leftPush(user,message);
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        Date date = Date.from(zonedDateTime.plus(1, ChronoUnit.MINUTES).toInstant());
        latestMessageExpiration.expireAt(user,date);
    }

    @Override
    public List<String> listMessages(String user) {
        return messageList.range(user,0,-1);
    }
}
