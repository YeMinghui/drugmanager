package top.codermhc.drugmanager.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.codermhc.drugmanager.base.entity.User;
import top.codermhc.drugmanager.base.entity.UserAuthentication;
import top.codermhc.drugmanager.base.service.DepartmentService;
import top.codermhc.drugmanager.base.service.RoleService;
import top.codermhc.drugmanager.base.service.UserAuthenticationService;
import top.codermhc.drugmanager.base.service.UserService;
import top.codermhc.drugmanager.service.UserVOService;
import top.codermhc.drugmanager.vo.UserVO;

@Service
public class UserVOServiceImpl implements UserVOService {

    public static final String USERVO_CACHE_PREFIX = "uservo:";

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Resource(name = "userAuthenticationServiceImpl")
    private UserAuthenticationService userAuthenticationService;

    @Resource(name = "departmentServiceImpl")
    private DepartmentService departmentService;

    @Resource(name = "roleServiceImpl")
    private RoleService roleService;

    /**
     * 查询所有User 对应的 UserVO
     *
     * @return all user's vo
     */
    @Override
    public List<UserVO> list() {
        List<User> list = userService.list();
        return list(list);
    }

    /**
     * 根据user查询UserVO
     *
     * @param users user list
     * @return list of users' uservo
     */
    @Override
    public List<UserVO> list(List<User> users) {
        List<UserVO> vos = new ArrayList<>();
        for (User user : users) {
            vos.add(getById(user.getId()));
        }
        return vos;
    }

    /**
     * 分页查询User对应的UserVO
     *
     * @param page 分页对象
     * @return 对应页里的数据
     */
    @Override
    public List<UserVO> page(IPage<User> page) {
        List<User> list = userService.page(page).getRecords();
        return list(list);
    }

    @Resource(name = "myRedisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 根据User id，查询userVO
     *
     * @param id user id
     * @return vo
     */
    @Override
    public UserVO getById(Long id) {
        BoundValueOperations<String, Object> ops = redisTemplate
            .boundValueOps(USERVO_CACHE_PREFIX + id);
        UserVO o = (UserVO) ops.get();
        if (o != null) {
            ops.expire(60, TimeUnit.MINUTES);
            return o;
        }
        User user = userService.getById(id);
        if (user != null) {
            UserVO userVO = new UserVO();
            userVO.setUser(user);
            userVO.setUserAuthentication(userAuthenticationService
                .getOne(Wrappers.<UserAuthentication>lambdaQuery().eq(UserAuthentication::getUserId, user.getId())));
            userVO.setDepartment(departmentService.getById(user.getDepartmentId()));
            userVO.setRole(roleService.getById(userVO.getUserAuthentication().getRoleId()));
            ops.set(userVO,60,TimeUnit.MINUTES);
            return userVO;
        }
        return null;
    }


    /**
     * 清除对应用户缓存
     *
     * @param id user id
     */
    @Override
    public void evict(Long id) {
        redisTemplate.expire(USERVO_CACHE_PREFIX + id, 0, TimeUnit.MILLISECONDS);
    }
}
