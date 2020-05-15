package top.codermhc.drugmanager.base.controller;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import top.codermhc.drugmanager.base.service.InventoryChangeService;
import top.codermhc.drugmanager.base.entity.InventoryChange;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ye Minghui
 */
@Slf4j
@RestController
@RequestMapping("/inventoryChange")
public class InventoryChangeController {

    @Resource(name = "inventoryChangeServiceImpl")
    public InventoryChangeService inventoryChangeService;

}

