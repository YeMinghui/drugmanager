package top.codermhc.drugmanager.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.codermhc.drugmanager.base.entity.UserAuthentication;
import top.codermhc.drugmanager.base.service.RoleService;
import top.codermhc.drugmanager.base.service.UserService;
import top.codermhc.drugmanager.exception.UserDisabledException;
import top.codermhc.drugmanager.exception.UserLockedException;
import top.codermhc.drugmanager.utils.ResponseData;
import top.codermhc.drugmanager.utils.UserFlag;

/**
 * @author Ye Minghui
 */
@Slf4j
@RestController
public class PageController extends BaseController {

    @Resource(name = "userServiceImpl")
    UserService userService;

    @Resource(name = "roleServiceImpl")
    RoleService roleService;
//
//    @RequestMapping("/login.html")
//    public String page_login() {
//        return "/login";
//    }
    @RequestMapping("/error")
    public void error() {
    }

    @PostMapping("/login")
    public ResponseData login(@RequestParam("workId") String workId, @RequestParam("password") String password,
        @RequestParam(value = "rememberMe", defaultValue = "false") boolean rememberMe) {
        Subject subject = SecurityUtils.getSubject();
        Map<String, Object> data = new HashMap<>();
        if (subject.isAuthenticated()) {
            data.put("token", subject.getSession().getId());
            data.put("id", authentication().getUserId());
            return new ResponseData(HttpStatus.CREATED, "已登录", data);
        }
        UsernamePasswordToken token = new UsernamePasswordToken(workId, password);
        token.setRememberMe(rememberMe);
        subject.login(token);
        UserAuthentication principal = authentication();
        int status = userService.getById(principal.getUserId()).getStatus();
        if (UserFlag.isEnabled(status, UserFlag.DISABLED)) {
            log.info("login user workId {}, DISABLED", workId);
            doLogout();
            throw new UserDisabledException();
        }
        if (UserFlag.isEnabled(status, UserFlag.LOCKED)) {
            log.info("login user workId {}, LOCKED", workId);
            doLogout();
            throw new UserLockedException();
        }
        log.info("login user workId {}, LOGGED", workId);
        data.put("token", subject.getSession().getId());
        data.put("id", principal.getUserId());
        return new ResponseData(HttpStatus.OK, "success", data);
    }

    @GetMapping("/logout")
    public String logout() {
        UserAuthentication authentication = authentication();
        doLogout();
        log.info("logout user workId {}, LOGOUT", authentication.getWorkId());
        return "redirect:/index.html";
    }
//
//    @RequestMapping(value = {"/", "/index.html"})
//    public String index(Model model) {
//        if (!SecurityUtils.getSubject().isAuthenticated()) {
//            return "redirect:/login.html";
//        }
//        setLoginInfo(model);
//        return "/index";
//    }
//
//    @RequestMapping("/forbid")
//    public void forbid() {
//        throw new AuthorizationException("No Authorization.");
//    }
//
//
//    @RequestMapping("/forgot.html")
//    public String forgot() {
//        return "forgot";
//    }
//
//    @GetMapping("/changePassword.html")
//    public String changPassword(Model model) {
//        setLoginInfo(model);
//        return "/changePassword";
//    }
//
//    @GetMapping("/userinfo.html")
//    public String userinfo(Model model) {
//        setLoginInfo(model);
//        return "/userinfo";
//    }

}
