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
import top.codermhc.drugmanager.base.service.UserAuthenticationService;
import top.codermhc.drugmanager.base.entity.UserAuthentication;

/**
 * @author Ye Minghui
 */
@RestController
public class UserAuthenticationController {

    @Resource(name = "userAuthenticationServiceImpl")
    private UserAuthenticationService userAuthenticationService;

    @GetMapping("/userAuthentication")
    public UserAuthentication get(@RequestParam("id") Long id) {
        return userAuthenticationService.getById(id);
    }

    @PostMapping("/userAuthentication")
    public UserAuthentication post(@Validated @RequestBody UserAuthentication userAuthentication) {
        userAuthenticationService.save(userAuthentication);
        return userAuthentication;
    }

    @RequestMapping(value = "/userAuthentication", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public boolean modify(@Validated @RequestBody UserAuthentication userAuthentication) {
        return userAuthenticationService.updateById(userAuthentication);
    }

    @DeleteMapping("/userAuthentication")
    public boolean delete(@RequestParam("id") Long id) {
        return userAuthenticationService.removeById(id);
    }

    @GetMapping(value = "/userAuthentication/page")
    public Object page(
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        return userAuthenticationService.page(new Page<>(page, limit));
    }

    @PostMapping(value = "/userAuthentication/list")
    public Object list(@RequestBody List<Long> ids) {
        return userAuthenticationService.listByIds(ids);
    }

    @DeleteMapping(value = "/userAuthentication/list")
    public boolean deleteAll(@RequestBody List<Long> ids) {
        return userAuthenticationService.removeByIds(ids);
    }

}
