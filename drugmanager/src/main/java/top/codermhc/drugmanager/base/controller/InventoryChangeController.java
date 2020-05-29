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
import top.codermhc.drugmanager.base.service.InventoryChangeService;
import top.codermhc.drugmanager.base.entity.InventoryChange;

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

    @GetMapping(value = "/inventoryChange/list")
    public Object list(@RequestParam("ids") List<Long> ids) {
        return inventoryChangeService.listByIds(ids);
    }

    @DeleteMapping(value = "/inventoryChange/list")
    public boolean deleteAll(@RequestParam("ids") List<Long> ids) {
        return inventoryChangeService.removeByIds(ids);
    }

}
