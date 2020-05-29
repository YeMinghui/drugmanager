package top.codermhc.drugmanager.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import top.codermhc.drugmanager.base.entity.Department;
import top.codermhc.drugmanager.base.entity.Role;
import top.codermhc.drugmanager.base.service.DepartmentService;
import top.codermhc.drugmanager.base.service.RoleService;
import top.codermhc.drugmanager.base.service.UserService;
import top.codermhc.drugmanager.service.UserVOService;
import top.codermhc.drugmanager.utils.UserFlag;
import top.codermhc.drugmanager.vo.UserVO;

/**
 * @author Ye Minghui
 */
@RequiresRoles("admin")
@RestController
public class AdminController extends BaseController{

    @Resource(name = "userVOServiceImpl")
    private UserVOService userVOService;

    @Resource(name = "userServiceImpl")
    UserService userService;

    @Resource(name = "departmentServiceImpl")
    DepartmentService departmentService;

    @Resource(name = "roleServiceImpl")
    RoleService roleService;

    @RequestMapping("/admin")
    public String admin() {
        return "redirect:/";
    }

    @RequestMapping("/admin/user.html")
    public String user(@RequestParam(value = "m", defaultValue = "list") String func, Model model,
        @RequestParam(value = "id", required = false) Long id,
        @RequestParam(value = "current", defaultValue = "1") Integer current,
        @RequestParam(value = "size", defaultValue = "10") Integer size) {
        setLoginInfo(model);
        validFunc(func, model);
        if (func.equals("add")) {
            // add user. need department and role to choose.
            List<Department> departments = departmentService.list();
            model.addAttribute("departs", departments);
            List<Role> roles = roleService.list();
            model.addAttribute("roles", roles);
        } else if (func.equals("modify") || func.equals("del") || func.equals("info")) {
            UserVO userVO = userVOService.getById(id);
            if (userVO == null) {
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "用户不存在");
            }
            model.addAttribute("userVO", userVO);
            List<Department> departments = departmentService.list();
            model.addAttribute("departs", departments);
            List<Role> roles = roleService.list();
            model.addAttribute("roles", roles);
            setUserFlag(model);
        } else if (func.equals("list")) {
            setPage(model, current, size, userService.count());
            List<UserVO> users = userVOService.page(new Page<>(current, size));
            model.addAttribute("users", users);
        }
        return "/admin/user";
    }

    /**
     * 设置用户状态选择栏
     *
     * @param model Model
     */
    private void setUserFlag(Model model) {
        Map<String, Integer> status = new HashMap<>();
        status.put("启用", UserFlag.ENABLED.mask);
        status.put("禁用", UserFlag.DISABLED.mask);
        status.put("锁定", UserFlag.LOCKED.mask);
        model.addAttribute("status", status);
    }

    @RequestMapping("/admin/role.html")
    public String role(@RequestParam(value = "m", defaultValue = "list") String func, Model model,
        @RequestParam(value = "id", required = false) Long id,
        @RequestParam(value = "current", defaultValue = "1") Integer current,
        @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {
        setLoginInfo(model);
        validFunc(func, model);
        if (func.equals("add")) {
            // add role. do nothing
        } else if (func.equals("modify") || func.equals("del") || func.equals("info")) {
            Role role = roleService.getById(id);
            if (role == null) {
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "角色不存在");
            }
            model.addAttribute("role", role);
        } else if (func.equals("list")) {
            setPage(model, current, size, roleService.count());
            List<Role> roles = roleService.page(new Page<>(current, size)).getRecords();
            model.addAttribute("roles", roles);
        }
        return "/admin/role";
    }


}
