package top.codermhc.drugmanager.base.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.apache.logging.log4j.util.Strings;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.codermhc.drugmanager.base.entity.DrugDetail;
import top.codermhc.drugmanager.base.entity.Inventory;
import top.codermhc.drugmanager.base.service.DrugDetailService;
import top.codermhc.drugmanager.base.service.InventoryChangeService;
import top.codermhc.drugmanager.base.entity.InventoryChange;
import top.codermhc.drugmanager.base.service.InventoryService;

/**
 * @author Ye Minghui
 */
@RestController
public class InventoryChangeController {

    @Resource(name = "inventoryChangeServiceImpl")
    private InventoryChangeService inventoryChangeService;

    @GetMapping("/inventoryChange")
    public InventoryChange get(@RequestParam("id") Long id) {
        return inventoryChangeService.getById(id);
    }

    @PostMapping("/inventoryChange")
    public InventoryChange post(@Validated @RequestBody InventoryChange inventoryChange) {
        inventoryChangeService.save(inventoryChange);
        return inventoryChange;
    }

    @RequestMapping(value = "/inventoryChange", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public boolean modify(@Validated @RequestBody InventoryChange inventoryChange) {
        return inventoryChangeService.updateById(inventoryChange);
    }

    @DeleteMapping("/inventoryChange")
    public boolean delete(@RequestParam("id") Long id) {
        return inventoryChangeService.removeById(id);
    }

    @GetMapping(value = "/inventoryChange/page")
    public Object page(
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        return inventoryChangeService.page(new Page<>(page, limit));
    }

    @PostMapping(value = "/inventoryChange/list")
    public Object list(@RequestBody List<Long> ids) {
        return inventoryChangeService.listByIds(ids);
    }

    @DeleteMapping(value = "/inventoryChange/list")
    public boolean deleteAll(@RequestBody List<Long> ids) {
        return inventoryChangeService.removeByIds(ids);
    }

    @PostMapping(value = "/inventoryChange/search")
    public Object search(@RequestParam("type") String type, @RequestParam("value") String value) {
        if (Strings.isBlank(value)) {
            return null;
        }
        List<Long> ids;
        switch (type) {
            case "drugName":
                ids = drugDetailService
                    .list(Wrappers.<DrugDetail>lambdaQuery().like(DrugDetail::getDrugName, value.trim())).stream()
                    .map(DrugDetail::getId).collect(
                        Collectors.toList());
                ids = inventoryService
                    .list(Wrappers.<Inventory>lambdaQuery().in(Inventory::getDrugId, ids)).stream()
                    .map(Inventory::getId).collect(Collectors.toList());
                return inventoryChangeService
                    .list(Wrappers.<InventoryChange>lambdaQuery().in(InventoryChange::getInventoryId, ids));
            case "genericId":
                ids = drugDetailService.list(
                    Wrappers.<DrugDetail>lambdaQuery().likeRight(DrugDetail::getGenericId, value.trim())).stream()
                    .map(DrugDetail::getId).collect(Collectors.toList());
                ids = inventoryService.list(Wrappers.<Inventory>lambdaQuery().in(Inventory::getDrugId, ids)).stream()
                    .map(Inventory::getId).collect(
                        Collectors.toList());
                return inventoryChangeService
                    .list(Wrappers.<InventoryChange>lambdaQuery().in(InventoryChange::getInventoryId, ids));
            case "opId":
                return inventoryChangeService.list(
                    Wrappers.<InventoryChange>lambdaQuery().likeRight(InventoryChange::getOperatorId, value.trim()));
            default:
                return null;
        }
    }

    @Resource(name = "drugDetailServiceImpl")
    private DrugDetailService drugDetailService;

    @Resource(name = "inventoryServiceImpl")
    private InventoryService inventoryService;

}
