package top.codermhc.drugmanager.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.ResultExtractor;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.config.JdbcNamespaceHandler;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import top.codermhc.drugmanager.base.entity.User;
import top.codermhc.drugmanager.base.entity.UserAuthentication;
import top.codermhc.drugmanager.base.service.UserAuthenticationService;
import top.codermhc.drugmanager.base.service.UserService;
import top.codermhc.drugmanager.shiro.CustomByteSource;
import top.codermhc.drugmanager.utils.JSON;

/**
 * @author Ye Minghui
 */
@Slf4j
@SpringBootTest
class UserServiceTest {

    @Resource(name = "userServiceImpl")
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

    @Resource(name = "userAuthenticationServiceImpl")
    UserAuthenticationService userAuthenticationService;

    @Resource(name = "myGson")
    Gson gson;

    @Test
    void gson() {
        UserAuthentication authentication = userAuthenticationService.getById(1);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(authentication,
            authentication.getPassword(),
            new CustomByteSource(authentication.getSalt()), "customRealm");
        String s = gson.toJson(info);
        System.out.println(s);
        SimpleAuthenticationInfo x = gson.fromJson(s, new TypeToken<SimpleAuthenticationInfo>(){}.getType());
        System.out.println(gson.toJson(x));
    }

}