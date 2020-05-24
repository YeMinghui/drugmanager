package top.codermhc.drugmanager.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import org.springframework.cache.annotation.CacheEvict;
import top.codermhc.drugmanager.base.entity.User;
import top.codermhc.drugmanager.vo.UserVO;

public interface UserVOService {

    /**
     * 查询所有User 对应的 UserVO
     *
     * @return all user's vo
     */
    List<UserVO> list();

    /**
     * 根据user查询UserVO
     *
     * @param users user list
     * @return list of users' uservo
     */
    List<UserVO> list(List<User> users);

    /**
     * 分页查询User对应的UserVO
     *
     * @param page 分页对象
     * @return 对应页里的数据
     */
    List<UserVO> page(IPage<User> page);

    /**
     * 根据User id，查询userVO
     *
     * @param id user id
     * @return vo
     */
    UserVO getById(Long id);

    /**
     * 清除对应用户缓存
     *
     * @param id user id
     */
    void evict(Long id);
}
