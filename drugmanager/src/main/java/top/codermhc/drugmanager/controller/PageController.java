package top.codermhc.drugmanager.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.codermhc.drugmanager.base.entity.UserAuthentication;
import top.codermhc.drugmanager.base.service.UserService;

/**
 * @author Ye Minghui
 */
@Slf4j
@Controller
public class PageController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam("workId") String workId, @RequestParam("password") String password, @RequestParam(value = "rememberMe", defaultValue = "false") boolean rememberMe) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(workId, password);
        token.setRememberMe(rememberMe);
        subject.login(token);
        return "redirect:/index.html";
    }

    @GetMapping({"/", "/index.html"})
    public String index(Model model) {
        Subject subject = SecurityUtils.getSubject();
        UserAuthentication authentication =
             (UserAuthentication) subject.getPrincipal();
        model.addAttribute("user", userService.getById(authentication.getUserId()));
        return "index";
    }

    @GetMapping("/admin.html")
    public String admin() {
        return "/admin";
    }

    @RequestMapping("/forbid")
    public void forbid() {
        throw new AuthorizationException("No Authorization.");
    }

    @RequestMapping("/login.html")
    public String page_login() {
        return "/login";
    }

    @RequestMapping("/forgot.html")
    public String forgot() {
        return "forgot";
    }

    @RequestMapping("/home.html")
    public String home(Model model) {
        Subject subject = SecurityUtils.getSubject();
        UserAuthentication authentication =
             (UserAuthentication) subject.getPrincipal();
        model.addAttribute("user", userService.getById(authentication.getUserId()));
        model.addAttribute("shiro_session", subject.getSession());
        return "home";
    }


    @RequestMapping("/{page}")
    public String page(@PathVariable String page, Model model) {
        Subject subject = SecurityUtils.getSubject();
        UserAuthentication authentication =
             (UserAuthentication) subject.getPrincipal();
        model.addAttribute("user", userService.getById(authentication.getUserId()));
        return page;
    }

    @RequestMapping("{pre}/{page}")
    public String multilevelPage(@PathVariable String pre, @PathVariable String page, Model model) {
        Subject subject = SecurityUtils.getSubject();
        UserAuthentication authentication =
             (UserAuthentication) subject.getPrincipal();
        model.addAttribute("user", userService.getById(authentication.getUserId()));
        return pre + "/" + page;
    }

}
