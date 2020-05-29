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
import top.codermhc.drugmanager.base.service.RoleService;
import top.codermhc.drugmanager.base.entity.Role;
import top.codermhc.drugmanager.controller.BaseController;

/**
 * @author Ye Minghui
 */
@RestController
public class RoleController extends BaseController {

    @Resource(name = "roleServiceImpl")
    private RoleService roleService;

    @GetMapping("/role")
    public Role get(@RequestParam(value = "id", required = false) Long id) {
        if (id == null) {
            return roleService.getById(user().getRoleId());
        }
        return roleService.getById(id);
    }
    @PostMapping("/role")
    public Role post(@Validated @RequestBody Role role) {
        roleService.save(role);
        return role;
    }

    @RequestMapping(value = "/role", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public boolean modify(@Validated @RequestBody Role role) {
        return roleService.updateById(role);
    }

    @DeleteMapping("/role")
    public boolean delete(@RequestParam("id") Long id) {
        return roleService.removeById(id);
    }

    @GetMapping(value = "/role/page")
    public Object page(
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        return roleService.page(new Page<>(page, limit));
    }

    @GetMapping(value = "/role/list")
    public Object list(@RequestParam("ids") List<Long> ids) {
        return roleService.listByIds(ids);
    }

    @DeleteMapping(value = "/role/list")
    public boolean deleteAll(@RequestParam("ids") List<Long> ids) {
        return roleService.removeByIds(ids);
    }

}
