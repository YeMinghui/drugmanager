package top.codermhc.drugmanager.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import top.codermhc.drugmanager.base.entity.Role;
import top.codermhc.drugmanager.base.service.RoleService;
import top.codermhc.drugmanager.exception.RoleAdminException;

@Controller
@RequiresRoles("admin")
public class RoleController extends BaseController{

    @Resource(name = "roleServiceImpl")
    private RoleService roleService;

    /**
     * 删除角色，单个或批量操作。需要admin权限。
     * admin 不应被修改。
     *
     * @param ids id 列表
     * @param id  id
     * @return 重定向到主页
     */
    @DeleteMapping("/role")
    public String deleteRole(@RequestBody(required = false) List<Integer> ids,
        @RequestParam(value = "id", required = false) Integer id) {
        if (id != null) {
            if (ROLE_ADMIN_ID.equals(id)) {
                throw new RoleAdminException("admin 角色不可删除");
            }
            roleService.removeById(id);
        }
        if (ids != null && !ids.isEmpty()) {
            ids.removeIf(ROLE_ADMIN_ID::equals);
            roleService.removeByIds(ids);
        }
        return "redirect:/admin/role.html";
    }

    /**
     * 添加角色
     * @param role 添加的角色
     * @return 重定向到管理页面
     */
    @PostMapping("/role")
    public String addRole(@Valid @ModelAttribute Role role) {
        if (roleService.getOne(Wrappers.<Role>lambdaQuery().eq(Role::getName, role.getName())) != null) {
            throw new RoleAdminException("role " + role.getName() + " 已存在");
        }
        roleService.save(role);
        return "redirect:/admin/role.html";
    }

    /**
     * 修改角色
     * @param role data to modify
     * @return 重定向到管理页面
     */
    @PatchMapping("/role")
    public String modifyRole(@ModelAttribute @Valid Role role) {
        if (ROLE_ADMIN_ID.equals(role.getId())) {
            throw new RoleAdminException("role admin 不可修改");
        }
        roleService.updateById(role);
        return "redirect:/admin/role.html";
    }

}
