package org.vincent.order;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * @Package: org.vincent.order <br/>
 * @Description： 基于redis template 封装的 redis dao层， 对Order 类型 实例进行增删查操作. <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/3/11 1:37 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/3/11. <br/>
 */
@Component("orderDao")
public class OrderDao {
		@Autowired
		private RedisTemplate<String,Order> redisTemplate;
		private final static String keyPrefix="Order";

		/**
		 * 给存储的键值对 设置一个前缀，这样在redismanager 管理软件中就有等级目录划分了。
		 * @param key
		 * @return
		 */
		public  String getKey(String key){
				return  OrderDao.keyPrefix+":"+key;
		}
		/**
		 * 保存一个对象到Redis
		 * @param order
		 * @return
		 */
		public  boolean save(Order order){

				ValueOperations<String,Order> valueOperations=redisTemplate.opsForValue();

				/** 设置key， value如果key不存在将插入键值对 k-v， k为字符串形式插入。 */
				boolean result=valueOperations.setIfAbsent(getKey(order.getId()),order);
				return  result;
		}

		public Order read(String id){
				if (StringUtils.isBlank(id) && "NULL".equalsIgnoreCase(id)){
					return null;
				}
				ValueOperations<String,Order> valueOperations= redisTemplate.opsForValue();
				Order order = valueOperations.get(getKey(id));
				return order;
		}
		public void  delete(String id){
				if (StringUtils.isBlank(id) && "NULL".equalsIgnoreCase(id)){
						return ;
				}
				ValueOperations<String,Order> valueOperations=redisTemplate.opsForValue();
				RedisOperations<String,Order> redisOperations = valueOperations.getOperations();
				redisOperations.delete(getKey(id));
		}
}
