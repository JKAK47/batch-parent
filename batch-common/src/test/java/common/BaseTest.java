package common;

import common.utils.LoggerFactory;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Package: common <br/>
 * @Description： 测试基础类 <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2017/12/25 11:16 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2017 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2017/12/25. <br/>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/Application.xml"})
public class BaseTest extends AbstractJUnit4SpringContextTests {
		Logger logger= LoggerFactory.getLogger();
}
