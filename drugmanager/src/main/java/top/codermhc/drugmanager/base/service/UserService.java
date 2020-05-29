package top.codermhc.drugmanager.base.service;

import top.codermhc.drugmanager.base.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Ye Minghui
 */
public interface UserService extends IService<User> {

    /**
     * 录入新用户，并设置默认密码
     *
     * @param user user with no password
     * @return true for success add
     */
    boolean addUser(User user);

}
