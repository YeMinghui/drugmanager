package top.codermhc.drugmanager.base.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
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
import top.codermhc.drugmanager.base.entity.Department;
import top.codermhc.drugmanager.base.entity.User;
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

    @PostMapping(value = "/manufacturer/list")
    public Object list(@RequestBody List<Long> ids) {
        return manufacturerService.listByIds(ids);
    }

    @DeleteMapping(value = "/manufacturer/list")
    public boolean deleteAll(@RequestBody List<Long> ids) {
        return manufacturerService.removeByIds(ids);
    }

    @PostMapping(value = "/manufacturer/search")
    public Object search(@RequestParam("type") String type, @RequestParam("value") String value) {
        if (Strings.isBlank(value)) {
            return null;
        }
        SFunction<Manufacturer, ?> function;
        switch (type) {
            case "name":
                function = Manufacturer::getName;
            case "phone":
                function = Manufacturer::getPhone;
                break;
            case "address":
                function = Manufacturer::getAddress;
                break;
            case "postcode":
                function = Manufacturer::getPostcode;
                break;
            default:
                return null;
        }
        return manufacturerService.list(Wrappers.<Manufacturer>lambdaQuery().like(function, value.trim()));
    }

}
