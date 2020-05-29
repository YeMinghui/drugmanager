package top.codermhc.drugmanager.base.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.codermhc.drugmanager.base.service.DepartmentService;
import top.codermhc.drugmanager.base.entity.Department;

/**
 * @author Ye Minghui
 */
@RestController
public class DepartmentController {

    @Resource(name = "departmentServiceImpl")
    private DepartmentService departmentService;

    @GetMapping("/department")
    public Department get(@RequestParam("id") Long id) {
        return departmentService.getById(id);
    }

    @PostMapping("/department")
    public Department post(@Validated @RequestBody Department department) {
        departmentService.save(department);
        return department;
    }

    @RequestMapping(value = "/department", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public boolean modify(@Validated @RequestBody Department department) {
        return departmentService.updateById(department);
    }

    @DeleteMapping("/department")
    public boolean delete(@RequestParam("id") Long id) {
        return departmentService.removeById(id);
    }

    @GetMapping(value = "/department/page")
    public Object page(
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        return departmentService.page(new Page<>(page, limit));
    }

    @GetMapping(value = "/department/list")
    public Object list(@RequestParam("ids") List<Long> ids) {
        return departmentService.listByIds(ids);
    }

    @DeleteMapping(value = "/department/list")
    public boolean deleteAll(@RequestParam("ids") List<Long> ids) {
        return departmentService.removeByIds(ids);
    }

}
