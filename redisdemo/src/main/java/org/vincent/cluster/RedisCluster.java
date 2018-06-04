package org.vincent.cluster;

import java.util.HashSet;
import java.util.Set;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Package: org.vincent <br/>
 * @Description： redis 集群简单连通性测试，连接集群只需要这个方法 <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/6/3 1:44 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/6/3. <br/>
 */

public class RedisCluster {
		public static void main(String[] args){
				Set<HostAndPort> jedisConfig=new HashSet<>();
				jedisConfig.add(new HostAndPort("192.168.0.5",8001));
				jedisConfig.add(new HostAndPort("192.168.0.5",8002));
				jedisConfig.add(new HostAndPort("192.168.0.5",8003));
				jedisConfig.add(new HostAndPort("192.168.0.5",8004));
				jedisConfig.add(new HostAndPort("192.168.0.5",8005));
				jedisConfig.add(new HostAndPort("192.168.0.5",8006));
				JedisPoolConfig config=new JedisPoolConfig();
				config.setMaxTotal(100);
				config.setMaxIdle(10);
				config.setTestOnBorrow(true);
				JedisCluster cluster=new JedisCluster(jedisConfig,6000,10,config);
				System.out.println(cluster.set("student","aaaa"));
				System.out.println(cluster.set("age","18"));
				System.out.println(cluster.get("student"));
				System.out.println(cluster.get("age"));
		}
}
