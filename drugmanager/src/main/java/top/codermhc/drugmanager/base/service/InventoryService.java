package top.codermhc.drugmanager.base.service;

import java.util.List;
import top.codermhc.drugmanager.base.entity.Inventory;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Ye Minghui
 */
public interface InventoryService extends IService<Inventory> {

	void outDates();

	boolean hasOutDates();

	List<Inventory> getOutDates();

}
