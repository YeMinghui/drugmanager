package top.codermhc.drugmanager.base.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.time.LocalDateTime;
import java.util.Collections;
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
import top.codermhc.drugmanager.base.entity.InventoryChange;
import top.codermhc.drugmanager.base.service.DrugDetailService;
import top.codermhc.drugmanager.base.service.InventoryChangeService;
import top.codermhc.drugmanager.base.service.InventoryService;
import top.codermhc.drugmanager.controller.BaseController;

/**
 * @author Ye Minghui
 */
@RestController
public class InventoryController extends BaseController {

    @Resource(name = "inventoryServiceImpl")
    private InventoryService inventoryService;

    @Resource(name = "inventoryChangeServiceImpl")
    private InventoryChangeService inventoryChangeService;

    @GetMapping("/inventory")
    public Inventory get(@RequestParam("id") Long id) {
        return inventoryService.getById(id);
    }

    @PostMapping("/inventory")
    public Inventory post(@Validated @RequestBody Inventory inventory) {
        inventoryService.save(inventory);
        logInventoryChange(inventory.getId(),"ruku");
        return inventory;
    }

    @RequestMapping(value = "/inventory", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public boolean modify(@Validated @RequestBody Inventory inventory) {
        return inventoryService.updateById(inventory);
    }

    @DeleteMapping("/inventory")
    public boolean delete(@RequestParam("id") Long id) {
        boolean b = inventoryService.removeById(id);
        logInventoryChange(id,"chuku");
        return b;
    }

    private void logInventoryChange(Long id, String opr) {
        Inventory byId = inventoryService.getById(id);
        InventoryChange inventoryChange = new InventoryChange();
        inventoryChange.setOperateMethod(opr);
        inventoryChange.setAmountDelta(byId.getAmount());
        inventoryChange.setInventoryId(id);
        inventoryChange.setOperateTime(LocalDateTime.now());
        inventoryChange.setOperatorId(user().getId());
        inventoryChangeService.save(inventoryChange);
    }

    @GetMapping(value = "/inventory/page")
    public Object page(
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        return inventoryService.page(new Page<>(page, limit));
    }

    @PostMapping(value = "/inventory/list")
    public Object list(@RequestBody List<Long> ids) {
        return inventoryService.listByIds(ids);
    }

    @DeleteMapping(value = "/inventory/list")
    public boolean deleteAll(@RequestBody List<Long> ids) {
        boolean b = inventoryService.removeByIds(ids);
        for (Long id : ids) {
            logInventoryChange(id,"chuku");
        }
        return b;
    }

    @PostMapping(value = "/inventory/search")
    public Object search(@RequestParam("type") String type, @RequestParam("value") String value) {
        if (Strings.isBlank(value)) {
            return null;
        }
        List<Long> ids;
        switch (type) {
            case "drugId":
                return inventoryService.list(Wrappers.<Inventory>lambdaQuery().like(Inventory::getDrugId, value.trim()));
            case "drugName":
                ids = drugDetailService
                    .list(Wrappers.<DrugDetail>lambdaQuery().like(DrugDetail::getDrugName, value.trim())).stream()
                    .map(DrugDetail::getId).collect(
                        Collectors.toList());
                return inventoryService.list(Wrappers.<Inventory>lambdaQuery().in(Inventory::getDrugId, ids));
            case "genericId":
                ids = drugDetailService.list(
                    Wrappers.<DrugDetail>lambdaQuery().likeRight(DrugDetail::getGenericId, value.trim())).stream()
                    .map(DrugDetail::getId).collect(Collectors.toList());
                return inventoryService.list(Wrappers.<Inventory>lambdaQuery().in(Inventory::getDrugId, ids));
            default:
                return null;
        }
    }

    @Resource(name = "drugDetailServiceImpl")
    private DrugDetailService drugDetailService;

    @GetMapping("/inventory/outdates")
	public Object getOutDates() {
        return inventoryService.hasOutDates() ? inventoryService.getOutDates() : Collections.EMPTY_LIST;
    }

}
