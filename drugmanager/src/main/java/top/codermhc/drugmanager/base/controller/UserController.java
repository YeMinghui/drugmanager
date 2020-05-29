package top.codermhc.drugmanager.base.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import top.codermhc.drugmanager.base.entity.Department;
import top.codermhc.drugmanager.base.entity.User;
import top.codermhc.drugmanager.base.service.DepartmentService;
import top.codermhc.drugmanager.base.service.UserService;
import top.codermhc.drugmanager.controller.BaseController;

/**
 * @author Ye Minghui
 */
@RestController
@Slf4j
public class UserController extends BaseController {

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Resource(name = "departmentServiceImpl")
    private DepartmentService departmentService;

    @GetMapping("/user")
    public User get(@RequestParam("id") Long id) {
        // 普通用户只能获取自己的信息
        if (!isAdmin() && !authentication().getUserId().equals(id)) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "没有权限");
        }
        return userService.getById(id);
    }

    @PostMapping("/user")
    public User post(@Validated @RequestBody User user, @RequestParam("roleId") Integer roleId) {
        userService.addUser(user, roleId);
        return user;
    }

    @RequestMapping(value = "/user", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public boolean modify(@Validated @RequestBody User user) {
        return userService.updateById(user);
    }

    @DeleteMapping("/user")
    public boolean delete(@RequestParam("id") Long id) {
        return userService.removeById(id);
    }

    @GetMapping(value = "/user/page")
    public Object page(
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        return userService.page(new Page<>(page, limit));
    }

    @GetMapping(value = "/user/list")
    public Object list(@RequestParam("ids") List<Long> ids) {
        return userService.listByIds(ids);
    }

    @DeleteMapping(value = "/user/list")
    public boolean deleteAll(@RequestParam("ids") List<Long> ids) {
        return userService.removeByIds(ids);
    }

    @PostMapping(value = "/user/search")
    public Object search(@RequestParam("type") String type, @RequestParam("value") String value) {
        if (Strings.isBlank(value)) {
            return null;
        }
        SFunction<User, ?> function;
        switch (type) {
            case "name":
                function = User::getName;
                break;
            case "identity":
                function = User::getIdentity;
                break;
            case "workId":
                function = User::getWorkId;
                break;
            case "phone":
                function = User::getPhone;
                break;
            case "title":
                function = User::getTitle;
                break;
            case "department":
                List<Long> collect = departmentService
                    .list(Wrappers.<Department>lambdaQuery().like(Department::getName, value)).stream()
                    .map(Department::getId).collect(
                        Collectors.toList());
                return userService.list(Wrappers.<User>lambdaQuery().in(User::getDepartmentId, collect));
            default:
                return null;
        }
        return userService.list(Wrappers.<User>lambdaQuery().like(function, value.trim()));
    }

}
