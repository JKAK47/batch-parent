package common.utils;

import java.net.URL;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;

/**
 * log4j + slf4j 统一 日志接口
 *
 * @ClassName: LoggerFactory
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author PengRong
 * @date 2017年11月23日 下午9:13:07
 *
 */
public class LoggerFactory {

	private static final Logger logger = org.slf4j.LoggerFactory
			.getLogger(LoggerFactory.class);

	private LoggerFactory() {
	}

	public static Logger getLogger() {
		URL url = LoggerFactory.class.getResource("/log4j.properties");
		PropertyConfigurator.configure(url.getPath());
		return LoggerFactory.logger;
	}

}
