package top.codermhc.drugmanager.mapper;

import tk.mybatis.mapper.common.Mapper;
import org.springframework.stereotype.Repository;
import top.codermhc.drugmanager.entity.User;

/**
 * @author Ye Minghui
 * @date 2020/04/21 下午10:43
 */
@Repository
public interface UserMapper extends Mapper<User> {

}
