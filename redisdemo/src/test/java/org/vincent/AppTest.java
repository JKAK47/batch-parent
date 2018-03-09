package org.vincent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit test for simple App.
 * http://www.cnblogs.com/luochengqiuse/p/4638988.html
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/Application.xml")
public class AppTest 
    extends AbstractJUnit4SpringContextTests
{

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String, String> Jedistemplate;

   /* @Autowired
    @Qualifier("redisTemplate")
    private ValueOperations<String,Object> vOps;*/
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    @Test
    public void  AppTest( String testName )
    {
        Jedistemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                byte [] key = "tempkey".getBytes();
                byte[] value = "tempvalue".getBytes();
                connection.set(key, value);
                return true;
            }
        });
    }


}
