package org.vincent;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertTrue;

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
    public AppTest( String testName )
    {
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
