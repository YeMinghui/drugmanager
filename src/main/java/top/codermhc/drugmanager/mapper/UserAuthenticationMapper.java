package top.codermhc.drugmanager.mapper;

import tk.mybatis.mapper.common.Mapper;
import org.springframework.stereotype.Repository;
import top.codermhc.drugmanager.entity.UserAuthentication;

/**
 * @author Ye Minghui
 */
@Repository
public interface UserAuthenticationMapper extends Mapper<UserAuthentication> {

}
