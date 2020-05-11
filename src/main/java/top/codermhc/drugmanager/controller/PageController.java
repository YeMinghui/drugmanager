package top.codermhc.drugmanager.controller;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.codermhc.drugmanager.entity.User;
import top.codermhc.drugmanager.entity.UserAuthentication;
import top.codermhc.drugmanager.service.UserService;

/**
 * @author Ye Minghui
 */
@Slf4j
@Controller
public class PageController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public User login(@RequestParam("workId") String workId, @RequestParam("password") String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(workId, password);
        subject.login(token);
        UserAuthentication authentication =
            // (UserAuthentication) subject.getPrincipal();
        new Gson().fromJson(new Gson().toJson(subject.getPrincipal()),UserAuthentication.class);
        return userService.findUserById(authentication.getUserId());
    }

    @GetMapping("/")
    public String index() {
        return "forward:/index.html";
    }

}
