package org.batch.db.mysql.mybatis;

import common.utils.LoggerFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;

/**
 * Package: org.batch.db.mysql.mybatis
 * Description： 基于mybatis ORM 访问数据库 基础Dao 提供日志打印，以及读取MybatisConfig配置文件并获取SqlSession
 * Author: PengRong
 * Date: Created in 2017/12/17 22:46
 * Company: PLCC
 * Copyright: Copyright (c) 2017
 * Version: 1.0
 * Modified By:
 * Created by PengRong on 2017/12/17.
 */

public interface BaseDao {
		// 公共的日志记录器和配置文件配置在接口中。
		public Logger logger = LoggerFactory.getLogger();;
		public String MybatisConfig = "mysql/mybatis/MybatisConfig.xml";

		// 打开一个Session
		default public SqlSession getSession() throws IOException {
				InputStream inputStream;
				inputStream = Resources.getResourceAsStream(BaseDao.MybatisConfig);

				return new SqlSessionFactoryBuilder().build(inputStream).openSession();
		}

		// 关闭一个Session
		default public void closeSession(SqlSession session) {
				if (session != null) {
						session.close();
				}
		}

		// 获取一个日志记录器
		default public Logger getLogger() {
				return BaseDao.logger;
		}
}
