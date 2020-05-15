package top.codermhc.drugmanager.base.controller;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import top.codermhc.drugmanager.base.service.InventoryService;
import top.codermhc.drugmanager.base.entity.Inventory;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ye Minghui
 */
@Slf4j
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Resource(name = "inventoryServiceImpl")
    public InventoryService inventoryService;

}

