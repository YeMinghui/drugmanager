package top.codermhc.drugmanager.base.mapper;

import java.util.List;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;
import top.codermhc.drugmanager.base.entity.Inventory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author Ye Minghui
 */
@Repository
@CacheNamespace
public interface InventoryMapper extends BaseMapper<Inventory> {

	@Select("select * from inventory where exp_date < curdate()")
	List<Inventory> outDates();

}
