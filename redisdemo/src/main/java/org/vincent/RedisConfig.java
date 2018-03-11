package org.vincent;

/**
 * @Package: org.vincent  Redis 配置类<br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/3/10 15:33 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/3/10. <br/>
 */

public class RedisConfig {
		private   int maxTotal;
		private   int maxIdle;
		private    int maxWait;
		private    boolean testOnBorrow;
		private    boolean testOnReturn;
		private    String RedisIp;
		private    int RedisPort;

		@Override
		public String toString() {
				return "RedisConfig{" +
								"maxTotal=" + maxTotal +
								", maxIdle=" + maxIdle +
								", maxWait=" + maxWait +
								", testOnBorrow=" + testOnBorrow +
								", testOnReturn=" + testOnReturn +
								", RedisIp='" + RedisIp + '\'' +
								", RedisPort=" + RedisPort +
								'}';
		}

		public  String getRedisIp() {
				return RedisIp;
		}
		public  void setRedisIp(String redisIp) {
				RedisIp = redisIp;
		}
		public  int getRedisPort() {
				return RedisPort;
		}
		public   void setRedisPort(int redisPort) {
				RedisPort = redisPort;
		}
		public RedisConfig(){
		}
		public   int getMaxTotal() {
				return maxTotal;
		}
		public   void setMaxTotal(int maxTotal) {
				this.maxTotal = maxTotal;
		}

		public   int getMaxIdle() {
				return maxIdle;
		}

		public   void setMaxIdle(int maxIdle) {
				this.maxIdle = maxIdle;
		}

		public   int getMaxWait() {
				return maxWait;
		}

		public   void setMaxWait(int maxWait) {
				this.maxWait = maxWait;
		}

		public   boolean isTestOnBorrow() {
				return testOnBorrow;
		}

		public   void setTestOnBorrow(boolean testOnBorrow) {
				this.testOnBorrow = testOnBorrow;
		}

		public   boolean isTestOnReturn() {
				return testOnReturn;
		}

		public   void setTestOnReturn(boolean testOnReturn) {
				this.testOnReturn = testOnReturn;
		}

}
