package top.codermhc.drugmanager.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.codermhc.drugmanager.base.entity.UserAuthentication;
import top.codermhc.drugmanager.base.service.RoleService;
import top.codermhc.drugmanager.base.service.UserAuthenticationService;
import top.codermhc.drugmanager.base.service.UserService;
import top.codermhc.drugmanager.exception.PasswordException;
import top.codermhc.drugmanager.exception.UserDisabledException;
import top.codermhc.drugmanager.exception.UserLockedException;
import top.codermhc.drugmanager.utils.PasswordHash;
import top.codermhc.drugmanager.utils.ResponseData;
import top.codermhc.drugmanager.utils.SaltGenerator;
import top.codermhc.drugmanager.utils.UserFlag;

/**
 * @author Ye Minghui
 */
@Slf4j
@RestController
public class PageController extends BaseController {

    @PostMapping("/login")
    public ResponseData login(@RequestParam("workId") String workId, @RequestParam("password") String password
//        , @RequestParam(value = "rememberMe", defaultValue = "false") boolean rememberMe
    ) {
        Subject subject = subject();
        Map<String, Object> data = new HashMap<>();
        if (subject.isAuthenticated()) {
            data.put("token", subject.getSession().getId());
            data.put("id", user().getId());
            return new ResponseData(HttpStatus.CREATED, "已登录", data);
        }
        UsernamePasswordToken token = new UsernamePasswordToken(workId, password);
//        token.setRememberMe(rememberMe);
        subject.login(token);
        int status = user().getStatus();
        if (UserFlag.isEnabled(status, UserFlag.DISABLED)) {
            log.warn("login user workId {}, DISABLED", workId);
            doLogout();
            throw new UserDisabledException();
        }
        if (UserFlag.isEnabled(status, UserFlag.LOCKED)) {
            log.warn("login user workId {}, LOCKED", workId);
            doLogout();
            throw new UserLockedException();
        }
        log.debug("login user workId {}, LOGGED", workId);
        data.put("token", subject.getSession().getId());
        data.put("id", user().getId());
        return new ResponseData(HttpStatus.OK, "success", data);
    }

//    @PostMapping("/logout")
//    public boolean logout() {
//        doLogout();
//        log.debug("logout user workId {}, LOGOUT", user().getWorkId());
//        return true;
//    }

    @Resource(name = "userAuthenticationServiceImpl")
    UserAuthenticationService userAuthenticationService;

    @PutMapping("/password")
    public boolean changePassword(@ModelAttribute Map<String, String> map) {
        String oldpwd = map.get("oldpwd").trim();
        String newpwd = map.get("newpwd").trim();
        UserAuthentication one = userAuthenticationService
            .getOne(Wrappers.<UserAuthentication>lambdaQuery().eq(UserAuthentication::getUserId, user().getId()));
        if (!one.getPassword().equals(PasswordHash.hash(oldpwd,one.getSalt()))) {
            throw new PasswordException();
        }
        // 改密时换盐
        one.setSalt(SaltGenerator.generate());
        String hashedPass = PasswordHash.hash(newpwd, one.getSalt());
        one.setPassword(hashedPass);
        userAuthenticationService.updateById(one);
        doLogout();
        return false;
    }

}
