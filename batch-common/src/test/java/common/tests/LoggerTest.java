package common.tests;

import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import common.utils.LoggerFactory;

/**
 * 对batch-common 模块的日志服务进行测试
 * 
 * @ClassName: LoggerTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author PengRong
 * @date 2017年11月26日 下午11:12:48
 *
 */
public class LoggerTest {

	@Before
	public void setUp() throws Exception {
		System.out.println("setUp");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("tearDown");
	}

	@Test
	public void test() {
		Logger logger = LoggerFactory.getLogger();
		logger.debug("debug msg" + new Date());
		logger.info("debug msg" + new Date());
		logger.error("debug msg" + new Date());
		logger.trace("debug msg" + new Date());
		logger.warn("debug msg" + new Date());
	}
}
