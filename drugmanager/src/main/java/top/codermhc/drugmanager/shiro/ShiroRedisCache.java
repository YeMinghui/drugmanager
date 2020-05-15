package top.codermhc.drugmanager.shiro;


import java.util.Collection;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;

@Slf4j
public class ShiroRedisCache<K, V> implements Cache<K, V> {

    private Object hashKey(K k) {
        return k;
    }

    private String cacheKey;
    private RedisTemplate<String,Object> redisTemplate;

    public ShiroRedisCache(String cacheKey, RedisTemplate<String,Object> redisTemplate) {
        this.cacheKey = cacheKey;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public V get(K k) throws CacheException {
        log.debug("shiro cache. get key={}", k);
        BoundHashOperations<String,K,V> hash = redisTemplate.boundHashOps(cacheKey);
        Object key = hashKey(k);
        V v = hash.get(key);
        if (v == null) {
            log.warn("shiro cache {} key={} doesn't exist.", cacheKey, k);
        } else {
            log.debug("shiro cache {} got key={}, value={}.", cacheKey, k, v);
        }
        return v;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        log.debug("shiro cache {} put key={}, value={}", cacheKey, k, v);
        BoundHashOperations<String,K,V> hash = redisTemplate.boundHashOps(cacheKey);
        Object key = hashKey(k);
        hash.put((K) key, v);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        log.debug("shiro cache {} remove key={}", cacheKey, k);
        BoundHashOperations<String, K, V> hash = redisTemplate.boundHashOps(cacheKey);
        Object key = hashKey(k);
        V v = hash.get(key);
        hash.delete(key);
        return v;
    }

    @Override
    public void clear() throws CacheException {
        log.debug("shiro cache {} clear.", cacheKey);
        redisTemplate.delete(cacheKey);
    }

    @Override
    public int size() {
        int size = redisTemplate.boundHashOps(cacheKey).size().intValue();
        log.debug("shiro cache {} count size={}", cacheKey, size);
        return size;
    }

    @Override
    public Set<K> keys() {
        log.debug("shiro cache {} get keys.", cacheKey);
        BoundHashOperations<String, K, V> hash = redisTemplate.boundHashOps(cacheKey);
        return hash.keys();
    }

    @Override
    public Collection<V> values() {
        log.debug("shiro cache {} get values.", cacheKey);
        BoundHashOperations<String, K, V> hash = redisTemplate.boundHashOps(cacheKey);
        return hash.values();
    }
}