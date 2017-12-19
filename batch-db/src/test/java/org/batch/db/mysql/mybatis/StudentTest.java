package org.batch.db.mysql.mybatis;

import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



/**
 * batch-parent.org.batch.db.mysql.mybatis <br/>
 * Created by PengRong on 2017/12/18. <br/>
 *
 * @author PengRong <br/>
 * @Description 基于Spring-test 的测试类写法模版
 * @ClassName: ${CLASS}
 * @since 2017-12-18 16:18 <br/>
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:mysql/spring/Application.xml"})
public class StudentTest extends AbstractJUnit4SpringContextTests {

    protected ApplicationContext getContext() {
        return applicationContext;
    }
}
