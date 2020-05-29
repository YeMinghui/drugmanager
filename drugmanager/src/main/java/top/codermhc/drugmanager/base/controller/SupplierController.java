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
import top.codermhc.drugmanager.base.service.SupplierService;
import top.codermhc.drugmanager.base.entity.Supplier;

/**
 * @author Ye Minghui
 */
@RestController
public class SupplierController {

    @Resource(name = "supplierServiceImpl")
    private SupplierService supplierService;

    @GetMapping("/supplier")
    public Supplier get(@RequestParam("id") Long id) {
        return supplierService.getById(id);
    }

    @PostMapping("/supplier")
    public Supplier post(@Validated @RequestBody Supplier supplier) {
        supplierService.save(supplier);
        return supplier;
    }

    @RequestMapping(value = "/supplier", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public boolean modify(@Validated @RequestBody Supplier supplier) {
        return supplierService.updateById(supplier);
    }

    @DeleteMapping("/supplier")
    public boolean delete(@RequestParam("id") Long id) {
        return supplierService.removeById(id);
    }

    @GetMapping(value = "/supplier/page")
    public Object page(
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        return supplierService.page(new Page<>(page, limit));
    }

    @GetMapping(value = "/supplier/list")
    public Object list(@RequestParam("ids") List<Long> ids) {
        return supplierService.listByIds(ids);
    }

    @DeleteMapping(value = "/supplier/list")
    public boolean deleteAll(@RequestParam("ids") List<Long> ids) {
        return supplierService.removeByIds(ids);
    }

}
