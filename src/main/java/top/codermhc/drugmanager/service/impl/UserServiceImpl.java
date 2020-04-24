package top.codermhc.drugmanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.codermhc.drugmanager.entity.User;
import top.codermhc.drugmanager.mapper.UserMapper;
import top.codermhc.drugmanager.service.UserService;

/**
 * @author Ye Minghui
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 根据id查用户信息
     *
     * @param id 用户id
     * @return 用户
     */
    @Override
    @Cacheable(cacheNames = "user", key = "#id")
    public User findUserById(long id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
