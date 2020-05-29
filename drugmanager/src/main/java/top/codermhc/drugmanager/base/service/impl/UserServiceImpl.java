package top.codermhc.drugmanager.base.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.codermhc.drugmanager.base.entity.User;
import top.codermhc.drugmanager.base.entity.UserAuthentication;
import top.codermhc.drugmanager.base.mapper.UserAuthenticationMapper;
import top.codermhc.drugmanager.base.mapper.UserMapper;
import top.codermhc.drugmanager.base.service.UserService;
import top.codermhc.drugmanager.utils.PasswordHash;
import top.codermhc.drugmanager.utils.SaltGenerator;
import top.codermhc.drugmanager.utils.UserFlag;

/**
 * @author Ye Minghui
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource(name = "userAuthenticationMapper")
    private UserAuthenticationMapper userAuthenticationMapper;
    /**
     * 录入新用户，并设置默认密码
     *
     * @param user user with no password
     * @return true for success add
     */
    @Override
    public boolean addUser(User user) {
        user.setStatus(UserFlag.enable(0,UserFlag.ENABLED));
        save(user);
        UserAuthentication authentication = new UserAuthentication();
        authentication.setUserId(user.getId());
        authentication.setWorkId(user.getWorkId());
        String password = user.getIdentity().substring(12);
        String salt = SaltGenerator.generate();
        password = PasswordHash.hash(password, salt);
        authentication.setPassword(password);
        authentication.setSalt(salt);
        userAuthenticationMapper.insert(authentication);
        return true;
    }

    /**
     * 根据user id删除用户
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteUserById(Long id) {
        removeById(id);
        userAuthenticationMapper
            .delete(Wrappers.<UserAuthentication>lambdaQuery().eq(UserAuthentication::getUserId, id));
        return true;
    }

    /**
     * 根据id集合删除对应用户
     *
     * @param ids
     * @return
     */
    @Override
    public boolean deleteUsersByIds(List<Long> ids) {
        removeByIds(ids);
        userAuthenticationMapper
            .delete(Wrappers.<UserAuthentication>lambdaQuery().in(UserAuthentication::getUserId, ids));
        return true;
    }
}
