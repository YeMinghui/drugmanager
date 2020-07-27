package top.codermhc.drugmanager.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.codermhc.drugmanager.base.entity.Inventory;
import top.codermhc.drugmanager.base.mapper.InventoryMapper;
import top.codermhc.drugmanager.base.service.InventoryService;

/**
 * @author Ye Minghui
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements InventoryService {

	@Resource
	private InventoryMapper inventoryMapper;

	@Resource(name = "myRedisTemplate")
	private RedisTemplate template;

	public static final String outDates = "outDateDrugs";

	@Scheduled(cron = "0 0 0 * * ? *")
	public void outDates() {
		List<Inventory> inventories = inventoryMapper.outDates();
		template.opsForList().rightPushAll(outDates, inventories);
	}

	public boolean hasOutDates() {
		return template.opsForList().size(outDates) != 0;
	}

	public List<Inventory> getOutDates() {
		BoundListOperations operations = template.boundListOps(outDates);
		List<Inventory> list = new ArrayList<>(operations.size().intValue());
		while (operations.size() > 0) {
			list.add((Inventory) operations.leftPop());
		}
		return list;
	}

}
