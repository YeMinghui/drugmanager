package top.codermhc.drugmanager.service;

import top.codermhc.drugmanager.entity.User;

/*
 * @author Ye Minghui
 */
public interface UserService {

    /**
     * 根据id查用户信息
     *
     * @param id 用户id
     * @return 用户
     */
    User findUserById(long id);

}
