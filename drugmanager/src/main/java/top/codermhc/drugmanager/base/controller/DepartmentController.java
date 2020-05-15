package top.codermhc.drugmanager.base.controller;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import top.codermhc.drugmanager.base.service.DepartmentService;
import top.codermhc.drugmanager.base.entity.Department;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ye Minghui
 */
@Slf4j
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Resource(name = "departmentServiceImpl")
    public DepartmentService departmentService;

}

