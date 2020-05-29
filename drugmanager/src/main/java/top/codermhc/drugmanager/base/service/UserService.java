package top.codermhc.drugmanager.base.service;

import java.util.List;
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

    /**
     * 根据user id删除用户
     *
     * @param id
     * @return
     */
    boolean deleteUserById(Long id);

    /**
     * 根据id集合删除对应用户
     *
     * @param ids
     * @return
     */
    boolean deleteUsersByIds(List<Long> ids);
}
