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
import top.codermhc.drugmanager.base.service.ManufacturerService;
import top.codermhc.drugmanager.base.entity.Manufacturer;

/**
 * @author Ye Minghui
 */
@RestController
public class ManufacturerController {

    @Resource(name = "manufacturerServiceImpl")
    private ManufacturerService manufacturerService;

    @GetMapping("/manufacturer")
    public Manufacturer get(@RequestParam("id") Long id) {
        return manufacturerService.getById(id);
    }

    @PostMapping("/manufacturer")
    public Manufacturer post(@Validated @RequestBody Manufacturer manufacturer) {
        manufacturerService.save(manufacturer);
        return manufacturer;
    }

    @RequestMapping(value = "/manufacturer", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public boolean modify(@Validated @RequestBody Manufacturer manufacturer) {
        return manufacturerService.updateById(manufacturer);
    }

    @DeleteMapping("/manufacturer")
    public boolean delete(@RequestParam("id") Long id) {
        return manufacturerService.removeById(id);
    }

    @GetMapping(value = "/manufacturer/page")
    public Object page(
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        return manufacturerService.page(new Page<>(page, limit));
    }

    @GetMapping(value = "/manufacturer/list")
    public Object list(@RequestParam("ids") List<Long> ids) {
        return manufacturerService.listByIds(ids);
    }

    @DeleteMapping(value = "/manufacturer/list")
    public boolean deleteAll(@RequestParam("ids") List<Long> ids) {
        return manufacturerService.removeByIds(ids);
    }

}
