package common.utils;

import org.apache.log4j.Logger;

/**
 * log4j 获取接口
 * 
 * @ClassName: LoggerFactory
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author PengRong
 * @date 2017年11月23日 下午9:13:07
 *
 */
public class LoggerFactory {

	private LoggerFactory() {
	}

	public Logger getLogger() {
		return Logger.getLogger(LoggerFactory.class);
	}
}
