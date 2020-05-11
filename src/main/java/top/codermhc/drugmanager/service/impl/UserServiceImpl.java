package top.codermhc.drugmanager.service.impl;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.codermhc.drugmanager.entity.User;
import top.codermhc.drugmanager.entity.UserAuthentication;
import top.codermhc.drugmanager.mapper.UserAuthenticationMapper;
import top.codermhc.drugmanager.mapper.UserMapper;
import top.codermhc.drugmanager.service.UserService;
import top.codermhc.drugmanager.utils.PasswordHash;
import top.codermhc.drugmanager.utils.SaltGenerator;
import top.codermhc.drugmanager.utils.UserStatus;

/**
 * @author Ye Minghui
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserAuthenticationMapper userAuthenticationMapper;

    @Resource(name = "cacheRedisTemplate")
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

    /**
     * 根据工号查用户
     *
     * @param workId 工号
     * @return 用户
     */
    @Override
    @Cacheable(cacheNames = "user", key = "#workId")
    public User findUserByWorkId(String workId) {
        User user = new User();
        user.setWorkId(workId);
        user = userMapper.selectOne(user);
        return user;
    }

    /**
     * 根据身份证号查询用户
     *
     * @param identify 身份证号
     * @return 用户
     */
    @Override
    public User findUserByIdentify(String identify) {
        User user = new User();
        user.setIdentify(identify);
        return userMapper.selectOne(user);
    }

    /**
     * 添加用户, 并生成默认登陆信息, 使用身份证后六位作为密码(身份证号非空)
     *
     * @param user 用户
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void addUser(User user) {
        userMapper.insert(user);
        UserAuthentication authentication = new UserAuthentication();
        authentication.setUserId(user.getId());
        authentication.setWorkId(user.getWorkId());
        authentication.setStatus(UserStatus.ENABLE | UserStatus.LOCKED);
        String password = user.getIdentify().substring(12);
        String salt = SaltGenerator.generate();
        password = PasswordHash.hash(password, salt);
        authentication.setPassword(password);
        authentication.setSalt(salt);
        userAuthenticationMapper.insert(authentication);
    }

    /**
     * 通过工号查询用户认证信息
     *
     * @param workId 工号
     */
    @Override
    public UserAuthentication findUserAuthenticationByWorkId(String workId) {
        UserAuthentication record = new UserAuthentication();
        record.setWorkId(workId);
        return userAuthenticationMapper.selectOne(record);
    }
}
