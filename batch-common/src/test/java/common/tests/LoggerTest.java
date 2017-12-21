package common.tests;

import common.utils.LoggerFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

import java.util.Date;

/**
 * 对batch-common 模块的日志服务进行测试
 *
 * @author PengRong
 * @ClassName: LoggerTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017年11月26日 下午11:12:48
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

		/**
		 * 测试基于 slf4j 接口库+ log4j 日志具体实现库 实现的 应用日志统一答应方案
		 *
		 * @return void 返回类型
		 * @throws
		 * @Title: test
		 * @Description: TODO(这里用一句话描述这个方法的作用)
		 */
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
