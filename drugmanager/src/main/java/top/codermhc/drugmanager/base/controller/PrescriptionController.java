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
import top.codermhc.drugmanager.base.service.PrescriptionService;
import top.codermhc.drugmanager.base.entity.Prescription;

/**
 * @author Ye Minghui
 */
@RestController
public class PrescriptionController {

    @Resource(name = "prescriptionServiceImpl")
    private PrescriptionService prescriptionService;

    @GetMapping("/prescription")
    public Prescription get(@RequestParam("id") Long id) {
        return prescriptionService.getById(id);
    }

    @PostMapping("/prescription")
    public Prescription post(@Validated @RequestBody Prescription prescription) {
        prescriptionService.save(prescription);
        return prescription;
    }

    @RequestMapping(value = "/prescription", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public boolean modify(@Validated @RequestBody Prescription prescription) {
        return prescriptionService.updateById(prescription);
    }

    @DeleteMapping("/prescription")
    public boolean delete(@RequestParam("id") Long id) {
        return prescriptionService.removeById(id);
    }

    @GetMapping(value = "/prescription/page")
    public Object page(
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        return prescriptionService.page(new Page<>(page, limit));
    }

    @GetMapping(value = "/prescription/list")
    public Object list(@RequestParam("ids") List<Long> ids) {
        return prescriptionService.listByIds(ids);
    }

    @DeleteMapping(value = "/prescription/list")
    public boolean deleteAll(@RequestParam("ids") List<Long> ids) {
        return prescriptionService.removeByIds(ids);
    }

}
