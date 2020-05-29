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
import top.codermhc.drugmanager.base.service.PatientService;
import top.codermhc.drugmanager.base.entity.Patient;

/**
 * @author Ye Minghui
 */
@RestController
public class PatientController {

    @Resource(name = "patientServiceImpl")
    private PatientService patientService;

    @GetMapping("/patient")
    public Patient get(@RequestParam("id") Long id) {
        return patientService.getById(id);
    }

    @PostMapping("/patient")
    public Patient post(@Validated @RequestBody Patient patient) {
        patientService.save(patient);
        return patient;
    }

    @RequestMapping(value = "/patient", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public boolean modify(@Validated @RequestBody Patient patient) {
        return patientService.updateById(patient);
    }

    @DeleteMapping("/patient")
    public boolean delete(@RequestParam("id") Long id) {
        return patientService.removeById(id);
    }

    @GetMapping(value = "/patient/page")
    public Object page(
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        return patientService.page(new Page<>(page, limit));
    }

    @GetMapping(value = "/patient/list")
    public Object list(@RequestParam("ids") List<Long> ids) {
        return patientService.listByIds(ids);
    }

    @DeleteMapping(value = "/patient/list")
    public boolean deleteAll(@RequestParam("ids") List<Long> ids) {
        return patientService.removeByIds(ids);
    }

}
