package top.codermhc.drugmanager.base.mapper;

import org.apache.ibatis.annotations.CacheNamespace;
import top.codermhc.drugmanager.base.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author Ye Minghui
 */
@Repository
@CacheNamespace
public interface DepartmentMapper extends BaseMapper<Department> {

}
