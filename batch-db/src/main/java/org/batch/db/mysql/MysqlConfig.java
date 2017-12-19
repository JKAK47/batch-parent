package org.batch.db.mysql;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Mysql Setting 配置信息，通过Spring 注入 获取数据库配置信息
 */
@Component("mysqlConfig")
public class MysqlConfig {

	private String driver;
	private String url;
	private String username;
	private String password;
	/**
	 * Spring 注入属性可以注入数字
	 */
	@Value("${mysql.mysql.jdbc.initPoolSize}")
	private int initPoolSize;

	@Override
	public String toString() {
		return "MysqlConfig [driver=" + this.driver + ", url=" + this.url
				+ ", username=" + this.username + ", password=" + this.password
				+ "]";
	}

	public MysqlConfig() {
	}

	public String getDriver() {
		return this.driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
