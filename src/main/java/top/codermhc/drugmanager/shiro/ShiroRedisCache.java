package top.codermhc.drugmanager.shiro;


import java.util.Collection;
import java.util.Set;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;

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
        BoundHashOperations<String,K,V> hash = redisTemplate.boundHashOps(cacheKey);
        Object key = hashKey(k);
        return hash.get(key);
    }

    @Override
    public V put(K k, V v) throws CacheException {
        BoundHashOperations<String,K,V> hash = redisTemplate.boundHashOps(cacheKey);
        Object key = hashKey(k);
        hash.put((K) key, v);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        BoundHashOperations<String, K, V> hash = redisTemplate.boundHashOps(cacheKey);
        Object key = hashKey(k);
        V v = hash.get(key);
        hash.delete(key);
        return v;
    }

    @Override
    public void clear() throws CacheException {
        redisTemplate.delete(cacheKey);
    }

    @Override
    public int size() {
        return redisTemplate.boundHashOps(cacheKey).size().intValue();
    }

    @Override
    public Set<K> keys() {
        BoundHashOperations<String, K, V> hash = redisTemplate.boundHashOps(cacheKey);
        return hash.keys();
    }

    @Override
    public Collection<V> values() {
        BoundHashOperations<String, K, V> hash = redisTemplate.boundHashOps(cacheKey);
        return hash.values();
    }
}