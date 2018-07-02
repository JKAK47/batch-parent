package org.vincent;

import java.util.ArrayList;
import java.util.Collections;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    @Test
    public void  AppTest( String testName )
    {
        ArrayList<String> list=new ArrayList<>();
        list.removeAll(Collections.singleton(null));
        Jedistemplate.execute((RedisCallback<Boolean>) connection -> {
            byte [] key = "tempkey".getBytes();
            byte[] value = "tempvalue".getBytes();
            connection.set(key, value);
            return true;
        });
    }


}
