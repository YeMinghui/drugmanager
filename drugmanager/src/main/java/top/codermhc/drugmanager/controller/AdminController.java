package top.codermhc.drugmanager.controller;

import java.util.List;
import javax.annotation.Resource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import top.codermhc.drugmanager.base.entity.User;
import top.codermhc.drugmanager.base.entity.UserAuthentication;
import top.codermhc.drugmanager.base.service.DepartmentService;
import top.codermhc.drugmanager.base.service.UserService;

/**
 * @author Ye Minghui
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource(name = "userServiceImpl")
    UserService userService;

    @Resource(name = "departmentServiceImpl")
    DepartmentService departmentService;

    @RequestMapping("/adduser.html")
    public String addUser(Model model) {
        Subject subject = SecurityUtils.getSubject();
        UserAuthentication authentication =
             (UserAuthentication) subject.getPrincipal();
        model.addAttribute("user", userService.getById(authentication.getUserId()));
        model.addAttribute("role", "admin");
        model.addAttribute("departs", departmentService.list());
        return "/admin/adduser";
    }

    @RequestMapping("/moduser.html")
    public String modUser(Model model) {
        UserAuthentication authentication = (UserAuthentication) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user", userService.getById(authentication.getUserId()));
        return "/admin/moduser";
    }

    @RequestMapping("/listuser.html")
    public String listUser(Model model) {
        UserAuthentication authentication = (UserAuthentication) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user", userService.getById(authentication.getUserId()));
        List<User> users = userService.list();

        model.addAttribute("users", users);
        return "/admin/listuser";
    }

}
