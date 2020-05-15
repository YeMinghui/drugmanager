package top.codermhc.drugmanager.service;

import java.util.List;
import top.codermhc.drugmanager.back.dto.UserDTO;
import top.codermhc.drugmanager.back.entity.User;
import top.codermhc.drugmanager.back.entity.UserAuthentication;

/**
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

    /**
     * 根据工号查用户
     *
     * @param workId 工号
     * @return 用户
     */
    User findUserByWorkId(String workId);

    /**
     * 根据身份证号查询用户
     *
     * @param identity 身份证号
     * @return 用户
     */
    User findUserByIdentity(String identity);

    /**
     * 添加用户, 并生成默认登陆信息, 使用身份证后六位作为密码(身份证号非空)
     *
     * @param user 用户
     */
    void addUser(User user);

    /**
     * 通过工号查询用户认证信息
     *
     * @param workId 工号
     */
    UserAuthentication findUserAuthenticationByWorkId(String workId);

    /**
     * 查询所有的用户
     *
     * @return 用户列表
     */
    List<User> findAll();

    /**
     * 查询所有用户的所有信息
     *
     * @return List<UserDTO>
     */
    List<UserDTO> findAllCascade();

}
