package top.codermhc.drugmanager.base.controller;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import top.codermhc.drugmanager.base.service.UserService;
import top.codermhc.drugmanager.base.entity.User;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ye Minghui
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource(name = "userServiceImpl")
    public UserService userService;

}

