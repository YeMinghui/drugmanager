package top.codermhc.drugmanager.shiro;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.data.redis.core.RedisTemplate;
import top.codermhc.drugmanager.base.entity.User;
import top.codermhc.drugmanager.base.entity.UserAuthentication;

@Slf4j
@SuppressWarnings({"unchecked"})
public class ShiroRedisCache<K, V> implements Cache<K, V> {

    private String wrapKey(String key) {
        return cacheName.concat(":").concat(key);
    }

    private String key(K k) {
        // AuthenticationToken 缓存，k 为 Principal
        if (k instanceof String) {
            return (String) k;
        }
        // AuthorizationInfo 缓存，k 为 PrincipalCollection
        if (k instanceof PrincipalCollection) {
            String key = "";
            Object o = ((PrincipalCollection) k).getPrimaryPrincipal();
            if (o instanceof UserAuthentication) {
                key = ((UserAuthentication) o).getWorkId();
            } else if (o instanceof User) {
                key = ((User) o).getWorkId();
            } else {
                key = k.toString();
            }
            return key;
        }

        // 注销时会获取PrincipalCollection中的第一个
        if (k instanceof UserAuthentication) {
            return ((UserAuthentication) k).getWorkId();
        }

        if (k instanceof User) {
            return ((User) k).getWorkId();
        }
        // 默认当作 String
        return (String) k;
    }

    private final String cacheName;
    private final RedisTemplate<String, Object> redisTemplate;

    public ShiroRedisCache(String cacheName, RedisTemplate<String,Object> redisTemplate) {
        this.cacheName = cacheName;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public V get(K k) throws CacheException {
        String key = key(k);
        String fullKey = wrapKey(key);
        log.debug("GET cache name {}, key {}.", cacheName, key);
        V v = (V) redisTemplate.boundValueOps(fullKey).get();
        if (v == null) {
            log.warn("[FAILED] GET cache name {}, key {}, doesn't exist.", cacheName, key);
        } else {
            redisTemplate.expire(key, 1, TimeUnit.HOURS);
            log.trace("[SUCCESS] GET cache name {}, key {}, value {}.", cacheName, key, v);
        }
        return v;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        String key = key(k);
        String fullKey = wrapKey(key);
        log.debug("PUT cache name {}, key {}, value {}.", cacheName, key, v);
        redisTemplate.boundValueOps(fullKey).set(v,1,TimeUnit.HOURS);
        log.trace("[SUCCESS] PUT cache name {}, key {}, value {}.", cacheName, key, v);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        String key = key(k);
        String fullKey = wrapKey(key);
        log.debug("DELETE cache name {}, key {}.", cacheName, key);
        V v = (V) redisTemplate.boundValueOps(fullKey).get();
        if (v == null) {
            log.warn("[FAILED] DELETE cache name {}, key {}.", cacheName, key);
        } else {
            redisTemplate.delete(fullKey);
            log.trace("[SUCCESS] DELETE cache name {}, key {}.", cacheName, key);
        }
        return v;
    }

    @Override
    public void clear() throws CacheException {
        log.debug("CLEAR cache name {}.", cacheName);
        redisTemplate.delete(wrapKey("*"));
        log.trace("[SUCCESS] CLEAR cache name {}.", cacheName);
    }

    @Override
    public int size() {
        log.debug("SIZE cache name {}.", cacheName);
        int size = listKeys().size();
        log.trace("[SUCCESS] SIZE cache name {}, size {}.", cacheName, size);
        return size;
    }

    @Override
    public Set<K> keys() {
        log.debug("KEYS cache name {}.",cacheName);
        Set<K> ks = listKeys();
        log.trace("[SUCCESS] KEYS cache name {}, keys {}.",cacheName, ks);
        return ks;
    }

    @Override
    public Collection<V> values() {
        log.debug("VALUES cache name {}.",cacheName);
        Collection<V> list = (Collection<V>) redisTemplate.opsForValue().multiGet((Collection<String>) listKeys());
        log.trace("[SUCCESS] VALUES cache name {}, values {}.", cacheName, list);
        return list;
    }

    private Set<K> listKeys() {
        return (Set<K>) redisTemplate.keys(wrapKey("*"));
    }
}