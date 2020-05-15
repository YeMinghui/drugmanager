package top.codermhc.drugmanager.base.service.impl;

import top.codermhc.drugmanager.base.entity.InventoryChange;
import top.codermhc.drugmanager.base.mapper.InventoryChangeMapper;
import top.codermhc.drugmanager.base.service.InventoryChangeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @author Ye Minghui
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class InventoryChangeServiceImpl extends ServiceImpl<InventoryChangeMapper, InventoryChange> implements InventoryChangeService {

    @Autowired
    private InventoryChangeMapper inventoryChangeMapper;

}
