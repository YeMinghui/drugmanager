package top.codermhc.drugmanager.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.codermhc.drugmanager.entity.User;
import top.codermhc.drugmanager.utils.JSON;

/*
 * @author Ye Minghui
 */
@Slf4j
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void findUserById() {
        User user = userService.findUserById(1);
        log.info("{}", JSON.stringExcludeNull(user));
    }
}