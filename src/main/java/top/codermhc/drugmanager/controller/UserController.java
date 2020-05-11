package top.codermhc.drugmanager.controller;

import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import top.codermhc.drugmanager.entity.User;
import top.codermhc.drugmanager.exception.UserExistException;
import top.codermhc.drugmanager.service.UserService;

/**
 * @author Ye Minghui
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 添加职员, 需要管理员权限
     *
     * @param user 药添加的用户信息
     * @return 操作结果
     */
    @PostMapping("/user")
    public ResponseEntity<Map<String, Object>> addUser(@Valid @RequestBody User user) {
        String identify = user.getIdentify();
        if (userService.findUserByIdentify(identify) != null) {
            throw new UserExistException();
        }
        userService.addUser(user);

        HashMap<String, Object> map = new HashMap<>();
        map.put("user",user);
        return ResponseEntity.ok(map);
    }

}
