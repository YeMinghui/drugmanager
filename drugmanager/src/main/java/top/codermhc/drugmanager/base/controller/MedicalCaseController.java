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
import top.codermhc.drugmanager.base.service.MedicalCaseService;
import top.codermhc.drugmanager.base.entity.MedicalCase;

/**
 * @author Ye Minghui
 */
@RestController
public class MedicalCaseController {

    @Resource(name = "medicalCaseServiceImpl")
    private MedicalCaseService medicalCaseService;

    @GetMapping("/medicalCase")
    public MedicalCase get(@RequestParam("id") Long id) {
        return medicalCaseService.getById(id);
    }

    @PostMapping("/medicalCase")
    public MedicalCase post(@Validated @RequestBody MedicalCase medicalCase) {
        medicalCaseService.save(medicalCase);
        return medicalCase;
    }

    @RequestMapping(value = "/medicalCase", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public boolean modify(@Validated @RequestBody MedicalCase medicalCase) {
        return medicalCaseService.updateById(medicalCase);
    }

    @DeleteMapping("/medicalCase")
    public boolean delete(@RequestParam("id") Long id) {
        return medicalCaseService.removeById(id);
    }

    @GetMapping(value = "/medicalCase/page")
    public Object page(
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        return medicalCaseService.page(new Page<>(page, limit));
    }

    @PostMapping(value = "/medicalCase/list")
    public Object list(@RequestBody List<Long> ids) {
        return medicalCaseService.listByIds(ids);
    }

    @DeleteMapping(value = "/medicalCase/list")
    public boolean deleteAll(@RequestBody List<Long> ids) {
        return medicalCaseService.removeByIds(ids);
    }

}
