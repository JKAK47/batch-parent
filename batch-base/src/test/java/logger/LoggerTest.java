package logger;

import org.slf4j.Logger;

import common.utils.LoggerFactory;

/**
 * 测试batch-common 模块中的日志服务能不能被batch-base 模版引用使用，pom添加对common 模块的依赖。
 *
 * @ClassName: LoggerTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author PengRong
 * @date 2017年11月26日 下午11:02:18
 *
 */
public class LoggerTest {

	public LoggerTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 测试后发现可以使用到batch-common 提供的日志服务，只需要添加对common模块的依赖， 不需要再次添加对日志库依赖
	 * 
	 * @Title: main
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param args 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger();
		logger.info("batch-base info");
		logger.warn("batch-base warn");
		logger.debug("batch-base warn");
		logger.error("batch-base warn");
	}
}
