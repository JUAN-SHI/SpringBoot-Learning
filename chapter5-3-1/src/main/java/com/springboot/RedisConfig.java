package com.springboot;

import com.springboot.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by shijuan on 2018/3/14.
 */

@Configuration
public class RedisConfig {

//    @Bean
//    JedisConnectionFactory  jedisConnectionFactory(){
//        return  new JedisConnectionFactory();
//    }


    @Bean
    public RedisTemplate<String,User> redisTemplate(RedisConnectionFactory factory){
        //定义RedisTemlpate,并设置连接工厂
        RedisTemplate<String,User> template=new RedisTemplate<String,User>();
        template.setConnectionFactory(factory);
        //设置序列化器
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return  template;
    }
}
