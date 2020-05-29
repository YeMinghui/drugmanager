package top.codermhc.drugmanager.base.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.codermhc.drugmanager.base.service.InventoryService;
import top.codermhc.drugmanager.base.entity.Inventory;

/**
 * @author Ye Minghui
 */
@RestController
public class InventoryController {

    @Resource(name = "inventoryServiceImpl")
    private InventoryService inventoryService;

    @GetMapping("/inventory")
    public Inventory get(@RequestParam("id") Long id) {
        return inventoryService.getById(id);
    }

    @PostMapping("/inventory")
    public Inventory post(@Validated @RequestBody Inventory inventory) {
        inventoryService.save(inventory);
        return inventory;
    }

    @RequestMapping(value = "/inventory", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public boolean modify(@Validated @RequestBody Inventory inventory) {
        return inventoryService.updateById(inventory);
    }

    @DeleteMapping("/inventory")
    public boolean delete(@RequestParam("id") Long id) {
        return inventoryService.removeById(id);
    }

    @GetMapping(value = "/inventory/page")
    public Object page(
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        return inventoryService.page(new Page<>(page, limit));
    }

    @GetMapping(value = "/inventory/list")
    public Object list(@RequestParam("ids") List<Long> ids) {
        return inventoryService.listByIds(ids);
    }

    @DeleteMapping(value = "/inventory/list")
    public boolean deleteAll(@RequestParam("ids") List<Long> ids) {
        return inventoryService.removeByIds(ids);
    }

}
