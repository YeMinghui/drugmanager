package top.codermhc.drugmanager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.codermhc.drugmanager.back.dto.UserDTO;
import top.codermhc.drugmanager.back.entity.Department;
import top.codermhc.drugmanager.back.entity.Role;
import top.codermhc.drugmanager.back.entity.User;
import top.codermhc.drugmanager.back.entity.UserAuthentication;
import top.codermhc.drugmanager.mapper.UserAuthenticationMapper;
import top.codermhc.drugmanager.mapper.UserMapper;
import top.codermhc.drugmanager.service.DepartmentService;
import top.codermhc.drugmanager.service.RoleService;
import top.codermhc.drugmanager.service.UserService;
import top.codermhc.drugmanager.back.utils.PasswordHash;
import top.codermhc.drugmanager.back.utils.SaltGenerator;
import top.codermhc.drugmanager.back.utils.UserStatus;

/**
 * @author Ye Minghui
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
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
     * @param identity 身份证号
     * @return 用户
     */
    @Override
    public User findUserByIdentity(String identity) {
        User user = new User();
        user.setIdentity(identity);
        return userMapper.selectOne(user);
    }

    /**
     * 添加用户, 并生成默认登录信息, 使用身份证后六位作为密码(身份证号非空)
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
        String password = user.getIdentity().substring(12);
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

    /**
     * 查询所有的用户
     *
     * @return 用户列表
     */
    @Override
    public List<User> findAll() {
        BoundHashOperations<String, Long, User> users = redisTemplate.boundHashOps("users");
        if (users.size().longValue() > 0) {
            return users.values();
        }
        List<User> list = userMapper.selectAll();
        Map<Long, User> map = list.stream().collect(Collectors.toMap(User::getId, x -> x));
        users.putAll(map);
        users.expire(7, TimeUnit.DAYS);
        return list;
    }

    @Resource
    private DepartmentService departmentService;
    @Resource
    private RoleService roleService;
    /**
     * 查询所有用户的所有信息
     *
     * @return List<UserDTO>
     */
    @Override
    public List<UserDTO> findAllCascade() {
        BoundHashOperations<String, Long, UserDTO> hash = redisTemplate.boundHashOps("userdtos");
        if (hash.size().longValue() > 0) {
            return hash.values();
        }
        List<UserDTO> dtos = new ArrayList<>();
        List<User> all = findAll();
        for (User user : all) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setDepartmentId(user.getDepartmentId());
            userDTO.setWorkId(user.getWorkId());
            userDTO.setName(user.getName());
            userDTO.setIdentity(user.getIdentity());
            userDTO.setTitle(user.getTitle());
            userDTO.setEmail(user.getEmail());
            userDTO.setPhone(user.getPhone());
            Department department = departmentService.findById(user.getDepartmentId());
            userDTO.setDepartment(department);
            UserAuthentication authentication = findUserAuthenticationByWorkId(userDTO.getWorkId());
            userDTO.setAuthentication(authentication);
            Role role = roleService.findRoleById(authentication.getRoleId());
            userDTO.setRole(role);
            dtos.add(userDTO);
        }

        Map<Long, UserDTO> collect = dtos.stream().collect(Collectors.toMap(dto -> dto.getId(), dto -> dto));
        hash.putAll(collect);
        return dtos;
    }
}
