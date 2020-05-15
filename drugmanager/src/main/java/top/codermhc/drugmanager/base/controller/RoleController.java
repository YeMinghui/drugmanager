package top.codermhc.drugmanager.base.controller;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import top.codermhc.drugmanager.base.service.RoleService;
import top.codermhc.drugmanager.base.entity.Role;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ye Minghui
 */
@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource(name = "roleServiceImpl")
    public RoleService roleService;

}

