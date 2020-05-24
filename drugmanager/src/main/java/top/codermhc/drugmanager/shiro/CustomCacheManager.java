package top.codermhc.drugmanager.shiro;

import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Resource;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@SuppressWarnings({"rawtypes","unchecked"})
@Component
public class CustomCacheManager implements CacheManager {

    private final String PREFIX = "shiro:";
    private final ConcurrentHashMap<String, Cache> caches = new ConcurrentHashMap<>();

    @Resource(name = "myRedisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        Cache<K,V> cache = caches.get(name);
        if (cache == null) {
            cache = new ShiroRedisCache<>(PREFIX.concat(name), redisTemplate);
            caches.put(name, cache);
        }
        return cache;
    }

}
