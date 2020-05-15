package top.codermhc.drugmanager.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.codermhc.drugmanager.base.entity.User;
import top.codermhc.drugmanager.base.service.UserService;
import top.codermhc.drugmanager.utils.JSON;

/**
 * @author Ye Minghui
 */
@Slf4j
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void findUserById() {
        User user = userService.getById(1);
        log.info("{}", JSON.stringExcludeNull(user));
    }

    @Test
    void findUserByWorkId() {
        User user = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getWorkId, "1"));
        log.info("{}", JSON.stringExcludeNull(user));
    }

    @Test
    void addUser() {
        User user = new User();
        user.setIdentity("510704199704022836");
        user.setWorkId("123");
        userService.save(user);
        log.info("{}", JSON.stringExcludeNull(user));
    }

}