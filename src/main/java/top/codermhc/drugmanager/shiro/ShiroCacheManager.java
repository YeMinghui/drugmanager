package top.codermhc.drugmanager.shiro;

import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Resource;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;

public class ShiroCacheManager implements CacheManager {

    private String prefix = "shiro:";
    private final ConcurrentHashMap<String, Cache> caches = new ConcurrentHashMap<>();

    @Resource(name = "shiroRedisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        Cache cache = caches.get(name);
        if (cache == null) {
            cache = new ShiroRedisCache<>(prefix + name, redisTemplate);
            caches.put(name, cache);
        }
        return cache;
    }

}
