package top.codermhc.drugmanager.mapper;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.codermhc.drugmanager.base.entity.User;
import top.codermhc.drugmanager.base.mapper.UserMapper;
import top.codermhc.drugmanager.utils.JSON;

/**
 * @author Ye Minghui
 */
@Slf4j
@SpringBootTest
class UserMapperTest {

    @Resource
    UserMapper userMapper;

    @Test
    void insertTest() {
        User user = new User();
        user.setName("MHC");
        user.setEmail("MH_C_Y@163.com");
        Assertions.assertEquals(1, userMapper.insert(user));
        log.info("{}", JSON.stringExcludeNull(user));
    }

    @Test
    void testPrimaryKey() {
        log.info("{}", JSON.stringExcludeNull(userMapper.selectById(1)));
    }

}