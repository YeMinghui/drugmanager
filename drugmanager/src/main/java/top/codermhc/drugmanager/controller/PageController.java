package top.codermhc.drugmanager.controller;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.codermhc.drugmanager.base.entity.UserAuthentication;
import top.codermhc.drugmanager.base.service.RoleService;
import top.codermhc.drugmanager.base.service.UserService;
import top.codermhc.drugmanager.exception.UserDisabledException;
import top.codermhc.drugmanager.exception.UserLockedException;
import top.codermhc.drugmanager.utils.UserFlag;

/**
 * @author Ye Minghui
 */
@Slf4j
@Controller
public class PageController extends BaseController {

    @Resource(name = "userServiceImpl")
    UserService userService;

    @Resource(name = "roleServiceImpl")
    RoleService roleService;

    @RequestMapping("/login.html")
    public String page_login() {
        return "/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("workId") String workId, @RequestParam("password") String password,
        @RequestParam(value = "rememberMe", defaultValue = "false") boolean rememberMe) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return "redirect:/index.html";
        }
        UsernamePasswordToken token = new UsernamePasswordToken(workId, password);
        token.setRememberMe(rememberMe);
        subject.login(token);
        UserAuthentication principal = authentication();
        int status = principal.getStatus();
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
        return "redirect:/index.html";
    }

    @GetMapping("/logout")
    public String logout() {
        UserAuthentication authentication = authentication();
        doLogout();
        log.info("logout user workId {}, LOGOUT", authentication.getWorkId());
        return "redirect:/index.html";
    }

    @RequestMapping(value = {"/", "/index.html"})
    public String index(Model model) {
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/login.html";
        }
        setLoginInfo(model);
        return "/index";
    }

    @RequestMapping("/forbid")
    public void forbid() {
        throw new AuthorizationException("No Authorization.");
    }


    @RequestMapping("/forgot.html")
    public String forgot() {
        return "forgot";
    }

    @GetMapping("/changePassword.html")
    public String changPassword(Model model) {
        setLoginInfo(model);
        return "/changePassword";
    }

    @GetMapping("/userinfo.html")
    public String userinfo(Model model) {
        setLoginInfo(model);
        return "/userinfo";
    }

}
