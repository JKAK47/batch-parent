package org.vincent;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.vincent.order.Order;
import org.vincent.order.OrderDao;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Redis Tester. 简单通过Jedis 连接Redis 服务。
 * [Redis--普通工程集成redis](https://my.oschina.net/u/2312022/blog/741502?utm_medium=referral)
 *
 * @author <PengRong>
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/Application.xml"})
public class RedisTest extends AbstractJUnit4SpringContextTests {

		@Autowired
		private JedisPool jedisPool;
		@Autowired
		private RedisConfig redisConfig;


		/**
		 * 使用spring + redis 集成
		 */
		@Autowired
		private OrderDao orderDao;

		/**
		 * 测试Redis配置类是否能自动注入
		 */
		@Test
		public void testRedisConfig() {
				System.out.println("");
				System.out.println(jedisPool.getResource().ping());
				System.out.println(redisConfig);
		}

		/**
		 * 通过jedis 访问redis 数据库
		 */
		@Test
		public void testJedis() {
				Jedis jedis = jedisPool.getResource();
				//testString(jedis);
				testMap(jedis);
				//testList(jedis);
				//testSet(jedis);
				//testzadd(jedis);
		}

		/**
		 * 用于有序字符串集合（zadd 命令）
		 * @param jedis
		 */
		public void testzadd(Jedis jedis) {
			jedis.del("zadd-demo");
			jedis.zadd("zadd-demo",0,"java");
			jedis.zadd("zadd-demo",1,"C++");
			jedis.zadd("zadd-demo",2,"PHP");
			jedis.zadd("zadd-demo",3,"C#");
			Set<String> ValueSet = jedis.zrangeByScore("zadd-demo",0,10);
			double score = jedis.zscore("zadd-demo","PHP");//获取一个元素成员对应的Score.
			jedis.zincrby("zadd-demo",1,"PHP");//尝试 给有序字符串集合，成员对应的score添加指定分值。
			/** 删除有序集合中元素*/
			jedis.zrem("zadd-demo","java");
			System.out.println(ValueSet);
		}

		/**
		 * jedis操作Set（字符串集合, 元素无序，元素不可重复）
		 */

		public void testSet(Jedis jedis) {
				jedis.del("user");
				//添加
				jedis.sadd("user", "chenhaoxiang");
				jedis.sadd("user", "hu");
				jedis.sadd("user", "chen");
				jedis.sadd("user", "xiyu");
				jedis.sadd("user", "chx");
				jedis.sadd("user", "are");
				jedis.sadd("user", "are");
				//移除user集合中的元素are
				jedis.srem("user", "are");
				System.out.println("user中的value:" + jedis.smembers("user"));//获取所有加入user的value
				System.out.println("chx是否是user中的元素:" + jedis.sismember("user", "chx"));//判断chx是否是user集合中的元素
				System.out.println("集合中的一个随机元素:" + jedis.srandmember("user"));//返回集合中的一个随机元素
				System.out.println("user中元素的个数:" + jedis.scard("user"));
				jedis.del("user");
		}

		/**
		 * jedis操作List（字符串列表, 元素可以重复）
		 */

		public void testList(Jedis jedis) {
				//移除javaFramwork所所有内容
				jedis.del("javaFramwork");
				//从左边插入数据
				jedis.lpush("javaFramework", "spring");
				jedis.lpush("javaFramework", "springMVC");
				jedis.lpush("javaFramework", "mybatis");
				jedis.lpush("javaFramework", "mybatis");
				jedis.lpop("javaFramework");// 从列表左侧弹出元素。
				jedis.lindex("javaFramework",1);// 使用lindex 命令获取到指定索引下标的值。
				//jedis.llen获取长度，-1表示取得所有
				System.out.println("长度:" + jedis.llen("javaFramework"));
				//取出所有数据,jedis.lrange是按范围取出
				//第一个是key，第二个是起始位置，第三个是结束位置
				System.out.println("javaFramework:" + jedis.lrange("javaFramework", 0, -1));

				jedis.del("javaFramework");
				System.out.println("删除后长度:" + jedis.llen("javaFramework"));
				System.out.println(jedis.lrange("javaFramework", 0, -1));
		}

		/**
		 * 测试Hash 类型数据； key 为一个字符串类型； value 部分为一个map类型数据
		 *
		 * @param jedis
		 */
		public void testMap(Jedis jedis) {
				//添加数据
				Map<String, String> map = new HashMap<String, String>();
				map.put("name", "chx");
				map.put("age", "100");
				map.put("email", "***@outlook.com");
				/** Hash 添加多个键值对 */
				jedis.hmset("user", map);
				/** hash 添加一个键值对 */
				jedis.hset("user","company","PCLC");
				//取出user中的name，结果是一个泛型的List
				//第一个参数是存入redis中map对象的key，后面跟的是放入map数据中的的key，后面的key是可变参数
				List<String> list = jedis.hmget("user", "name", "age", "email");
				System.out.println(list);
				jedis.hincrBy("user","age",30);
				/** 给散列存储的键值对中值进行自增操作 */
				System.out.println("age = "+jedis.hget("user","age"));
				//删除map中的某个键值对
				jedis.hdel("user", "age");
				System.out.println("age:" + jedis.hmget("user", "age")); //因为删除了，所以返回的是null
				System.out.println("user的键中存放的值的个数:" + jedis.hlen("user")); //返回key为user的map数据中存放的键值对个数2
				System.out.println("是否存在key为user的记录:" + jedis.exists("user"));//Redis 是否存在key为user的记录 返回true
				System.out.println("user对象中的所有key:" + jedis.hkeys("user"));//返回user对象对应map中所有key
				System.out.println("user对象中的所有value:" + jedis.hvals("user"));//返回key = user 中 value 对象中的所有value

				//拿到所有 key，再通过迭代器得到map中每个键对应的值
				Iterator<String> iterator = jedis.hkeys("user").iterator();
				while (iterator.hasNext()) {
						String key = iterator.next();
						System.out.println(key + ":" + jedis.hmget("user", key));
				}
				/** 删除 Redis 中 key = user 的元素， */
				jedis.del("user");
				System.out.println("删除后是否存在key为user的记录:" + jedis.exists("user"));//是否存在key为user的记录
		}

		/**
		 * 测试字符串类型数据
		 *
		 * @param jedis
		 */
		public void testString(Jedis jedis) {
				jedis.set("num",String.valueOf(20));
				//添加String数据
				jedis.set("name", "chx"); //key为name放入value值为chx
				System.out.println("拼接前:" + jedis.get("name"));//读取key为name的值

				//向key为name的String 值后面加上数据 ---拼接
				jedis.append("name", " is my name;");
				System.out.println("拼接后:" + jedis.get("name"));

				//删除某个键值对
				jedis.del("name");
				System.out.println("删除key = name 后:" + jedis.get("name"));

				//设置多个字符串键值对,mset 如果遇到key已经存在，那么将用新的值替换现有旧的值。
				jedis.mset("name", "chenhaoxiang", "age", "20", "email", "chxpostbox@outlook.com");
				jedis.incr("age");//用于将键的整数值递增1。如果键不存在，则在执行操作之前将其设置为0。 如果键包含错误类型的值或包含无法表示为整数的字符串，则会返回错误。此操作限于64位有符号整数。
				System.out.println(jedis.get("name") + " " + jedis.get("age") + " " + jedis.get("email"));
		}

		/**
		 * 通过Redis Dao 对Redis数据库进行增删改查
		 */
		@Test
		public void saveTest() {
				Order order = new Order();
				order.setId("3");
				order.setPrice(40);
				boolean cc = orderDao.save(order);
				System.out.println(cc);
				Order order2 = orderDao.read("3");
				System.out.println(order2);
		}

		@Test
		public void deleteTest() {
				orderDao.delete("3");
		}


} 
