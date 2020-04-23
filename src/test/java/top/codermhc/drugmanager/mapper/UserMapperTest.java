package top.codermhc.drugmanager.mapper;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;
import top.codermhc.drugmanager.entity.User;
import top.codermhc.drugmanager.utils.JSON;

/**
 * @author Ye Minghui
 * @date 2020/3/28 下午1:09
 */
@Slf4j
@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void insertTest() {
        User user = new User();
        user.setName("MHC");
        user.setEmail("MH_C_Y@163.com");
        Assertions.assertEquals(1,userMapper.insertSelective(user));
        log.info("{}", JSON.stringExcludeNull(user));
    }

    @Test
    void testPrimaryKey() {
        log.info("{}", JSON.stringExcludeNull(userMapper.selectByPrimaryKey(1)));
    }

    @Test
    void testByName() {
        final Example example = Example.builder(User.class)
            .select("id", "name")
            .andWhere(Sqls.custom().andEqualTo("name", "MHC"))
            .build();
        final List<User> users = userMapper.selectByExample(example);
        log.info("{}", JSON.stringExcludeNull(users.get(0)));
    }

}