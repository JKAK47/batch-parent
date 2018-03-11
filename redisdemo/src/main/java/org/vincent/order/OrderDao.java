package org.vincent.order;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * @Package: org.vincent.order <br/>
 * @Description： TODO <br/>
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

		/**
		 * 保存一个对象到Redis
		 * @param order
		 * @return
		 */
		public  boolean save(Order order){
				ValueOperations<String,Order> valueOperations=redisTemplate.opsForValue();
				boolean result=valueOperations.setIfAbsent(order.getId(),order);
				return  result;
		}

		public Order read(String id){
				if (StringUtils.isBlank(id) && "NULL".equalsIgnoreCase(id)){
					return null;
				}
				ValueOperations<String,Order> valueOperations= redisTemplate.opsForValue();
				Order order = valueOperations.get(id);
				return order;
		}
		public void  delete(String id){
				if (StringUtils.isBlank(id) && "NULL".equalsIgnoreCase(id)){
						return ;
				}
				ValueOperations<String,Order> valueOperations=redisTemplate.opsForValue();
				RedisOperations<String,Order> redisOperations = valueOperations.getOperations();
				redisOperations.delete(id);
		}
}
