package common.utils;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;

import java.net.URL;

/**
 * log4j + slf4j 统一 日志接口
 *
 * @author PengRong
 * @ClassName: LoggerFactory
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017年11月23日 下午9:13:07
 */
public class LoggerFactory {

		private static final Logger logger = org.slf4j.LoggerFactory
						.getLogger(LoggerFactory.class);

		private LoggerFactory() {
		}

		public static Logger getLogger() {
				// 读取batch-common 模块下main/java 目录下resources 的log4j.properties文件; 注意使用的文件路径：/log4j.properties
				URL url = LoggerFactory.class.getResource("/log4j.properties");
				PropertyConfigurator.configure(url.getPath());
				return LoggerFactory.logger;
		}

}
