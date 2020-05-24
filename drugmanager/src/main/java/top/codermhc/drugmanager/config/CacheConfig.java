package top.codermhc.drugmanager.config;

import java.time.Duration;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author Ye Minghui
 */
@EnableCaching
@Configuration
public class CacheConfig {

    @Bean(name = "myRedisCacheManager")
    RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory){
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
            .computePrefixWith(cacheName -> cacheName + ":")
            .serializeKeysWith(SerializationPair.fromSerializer(RedisSerializer.string()))
            .serializeValuesWith(SerializationPair.fromSerializer(gsonRedisSerializer()))
            .entryTtl(Duration.ofDays(1));
        HashMap<String, RedisCacheConfiguration> map = new HashMap<>();
        map.put("user", defaultCacheConfig.entryTtl(Duration.ofMinutes(100)));
        return RedisCacheManager.builder(redisConnectionFactory)
            .cacheDefaults(defaultCacheConfig).withInitialCacheConfigurations(map).build();
    }

    @Bean(name = "myRedisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setDefaultSerializer(gsonRedisSerializer());
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    GsonRedisSerializer<Object> gsonRedisSerializer() {
        return new GsonRedisSerializer<>();
    }

}
