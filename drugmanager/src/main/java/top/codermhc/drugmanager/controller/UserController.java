package top.codermhc.drugmanager.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.codermhc.drugmanager.base.entity.User;
import top.codermhc.drugmanager.base.service.UserService;
import top.codermhc.drugmanager.exception.UserExistException;

/**
 * @author Ye Minghui
 */
//@Controller
//@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 添加职员, 需要管理员权限
     *
     * @param user 药添加的用户信息
     * @return 操作结果
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addUser(@Valid @ModelAttribute User user) {
        String identity = user.getIdentity();
        User one = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getIdentity, identity));
        if (one != null) {
            throw new UserExistException();
        }
        userService.save(user);

        HashMap<String, Object> map = new HashMap<>();
        map.put("user",user);
        return ResponseEntity.ok(map);
    }

}
