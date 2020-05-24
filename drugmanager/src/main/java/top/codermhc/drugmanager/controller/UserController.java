package top.codermhc.drugmanager.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sun.org.apache.bcel.internal.generic.FSUB;
import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import top.codermhc.drugmanager.base.entity.User;
import top.codermhc.drugmanager.base.entity.UserAuthentication;
import top.codermhc.drugmanager.base.service.UserAuthenticationService;
import top.codermhc.drugmanager.base.service.UserService;
import top.codermhc.drugmanager.exception.PasswordException;
import top.codermhc.drugmanager.exception.UserExistException;
import top.codermhc.drugmanager.service.UserVOService;
import top.codermhc.drugmanager.utils.PasswordHash;
import top.codermhc.drugmanager.utils.SaltGenerator;
import top.codermhc.drugmanager.vo.UserVO;

/**
 * @author Ye Minghui
 */
@Controller
public class UserController extends BaseController {

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Resource(name = "userAuthenticationServiceImpl")
    private UserAuthenticationService userAuthenticationService;

    @Resource(name = "userVOServiceImpl")
    private UserVOService userVOService;

    /**
     * 添加职员, 需要管理员权限
     *
     * @param user 要添加的用户信息
     * @return 重定向到主页
     */
    @PostMapping("/user")
    @RequiresRoles("admin")
    public String addUser(@Valid @ModelAttribute User user, @RequestParam("roleId") Integer roleId) {
        String identity = user.getIdentity();
        User one = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getIdentity, identity));
        if (one != null) {
            throw new UserExistException();
        }
        userService.addUser(user, roleId);
        return "redirect:/admin/user.html";
    }

    /**
     * 删除用户，单个或批量操作。需要admin权限。
     *
     * @param ids id 列表
     * @param id  id
     * @return 重定向到主页
     */
    @DeleteMapping("/user")
    @RequiresRoles("admin")
    public String deleteUser(@RequestBody(required = false) List<Integer> ids,
        @RequestParam(value = "id", required = false) Integer id) {
        if (id != null) {
            userService.removeById(id);
            userAuthenticationService
                .remove(Wrappers.<UserAuthentication>lambdaQuery().eq(UserAuthentication::getUserId, id));
        }
        if (ids != null && !ids.isEmpty()) {
            userService.removeByIds(ids);
            for (Integer integer : ids) {
                userAuthenticationService
                    .remove(Wrappers.<UserAuthentication>lambdaQuery().eq(UserAuthentication::getUserId, integer));
            }
        }
        return "redirect:/admin/user.html";
    }

    /**
     * 修改用户信息，管理员可以操作所有用户，用户可以操作自己。
     *
     * @param user   修改的数据
     * @param status 用户状态
     * @return 重定向页面
     */
    @PatchMapping("/user")
    public String modifyUser(@ModelAttribute @Valid User user,
        @RequestParam(value = "status", required = false) Integer status
        , @RequestParam(value = "roleId", required = false) Integer roleId) {
        UserAuthentication auth = authentication();
        if (ROLE_ADMIN_ID.equals(auth.getRoleId())) {
            if (user.getId() != null) {
                userService.updateById(user);
                if (status != null || roleId != null) {
                    UserAuthentication authentication = new UserAuthentication();
                    authentication.setStatus(status);
                    authentication.setRoleId(roleId);
                    userAuthenticationService.update(authentication,
                        Wrappers.<UserAuthentication>lambdaUpdate().eq(UserAuthentication::getUserId, user.getId()));
                }
                userVOService.evict(user.getId());
            } else {
                throw new HttpClientErrorException(HttpStatus.NOT_MODIFIED, "未修改");
            }
        } else if (auth.getUserId().equals(user.getId())) {
            // 用户只能修改邮箱和电话
//            userService.updateById(user);
            userService.update(user,
                Wrappers.<User>lambdaUpdate().eq(User::getId, user.getId()).set(User::getEmail, user.getEmail())
                    .set(User::getPhone, user.getPhone()));
            userVOService.evict(user.getId());
        } else {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "没有修改用户的权利");
        }
        return "redirect:/index.html";
    }

    /**
     * 修改密码
     * @param old 旧密码
     * @param thenew 新密码
     * @return 重定向到首页
     */
    @PostMapping("/changePass")
    @RequiresUser
    public String changPass(@RequestParam("old") String old, @RequestParam("thenew") String thenew) {
        UserAuthentication authentication = authentication();
        String password = authentication.getPassword();
        String hash = PasswordHash.hash(old, authentication.getSalt());
        if (!password.equals(hash)) {
            throw new PasswordException();
        }
        // 改密时换盐
        authentication.setSalt(SaltGenerator.generate());
        String hashedPass = PasswordHash.hash(thenew, authentication.getSalt());
        authentication.setPassword(hashedPass);
        userAuthenticationService.updateById(authentication);
        doLogout();
        return "redirect:/index.html";
    }
}
