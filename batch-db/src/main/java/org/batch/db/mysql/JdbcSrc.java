package org.batch.db.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.batch.db.mysql.bean.Product;
import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import common.utils.LoggerFactory;

/**
 * Package: org.batch.db.excel Description：
 * 使用原始方式jdbc方式创建jdbc应用访问单表（基于mysql+mybatis）;只需要DataSource 对象。 Author: PengRong
 * Date: Created in 2017/12/13 0:40 Company: PLCC Copyright: Copyright (c) 2017
 * Version: 1.0 Modified By: Created by PengRong on 2017/12/13.
 */

public class JdbcSrc {

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger();
		String path = "/mysql/spring/Application.xml";
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Product product = new Product();
		logger.info("start");
		// 第一步 获取Spring IOC容器
		ApplicationContext context = new ClassPathXmlApplicationContext(path);
		// 第二步 获取DataSource JavaBean实例
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		System.out.println(dataSource);// 能输出信息说明实现了mysql数据库连接
		@SuppressWarnings("unused")
		MysqlConfig config = (MysqlConfig) context.getBean("mysqlConfig");
		try {
			// 第三步 从DataSource实例获取一个sql 连接
			connection = dataSource.getConnection();
			// 第四步 获取Statement实例，用于执行Sql语句
			statement = connection.createStatement();
			// 第五步 通过Statement实例执行sql语句，获取查询结果
			resultSet = statement.executeQuery("select * from products ");
			// 第六步 从结果集中获取数据
			while (resultSet.next()) {
				product.setProductId(resultSet.getString("prod_id"));
				product.setProductDsc(resultSet.getString("prod_desc"));
				product.setProductName(resultSet.getString("prod_name"));
				product.setProductPrice(resultSet.getDouble("prod_price"));
				product.setVendId(resultSet.getInt("vend_id"));
				System.out.println(product.toString());
			}
			logger.info("stop");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 首先释放结果集
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// 关闭sql语句执行实例statement
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// 关闭sql 连接connection
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
