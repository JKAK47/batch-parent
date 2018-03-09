package org.vincent.rediscache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * batch-parent.org.vincent.rediscache <br/>
 * Created by PengRong on 2018/3/9. <br/>
 * https://dzone.com/articles/integrate-redis-to-your-spring-project
 * @author PengRong <br/>
 * @Description TODO(${END})
 * @ClassName: ${CLASS}
 * @since 2018-03-09 9:49 <br/>
 */
@Configuration
@EnableCaching
public class RedisConfig  extends CachingConfigurerSupport {

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setUsePool(true);
        return jedisConnectionFactory;
    }
    @Bean
    public RedisSerializer redisStringSerializer() {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        return stringRedisSerializer;
    }
    @Bean(name="redisTemplate")
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf, RedisSerializer redisSerializer) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
        redisTemplate.setConnectionFactory(cf);
        redisTemplate.setDefaultSerializer(redisSerializer);
        return redisTemplate;
    }
    @Override
    @Bean
    public CacheManager cacheManager() {
        //new RedisCacheManager(redisTemplate(redisConnectionFactory(),redisStringSerializer()));
        return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory()).build();
    }
}
