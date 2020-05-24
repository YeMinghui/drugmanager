package top.codermhc.drugmanager.base.mapper;

import org.apache.ibatis.annotations.CacheNamespace;
import top.codermhc.drugmanager.base.entity.Manufacturer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author Ye Minghui
 */
@Repository
@CacheNamespace
public interface ManufacturerMapper extends BaseMapper<Manufacturer> {

}
