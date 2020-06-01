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
import top.codermhc.drugmanager.base.service.PrescriptionDetailService;
import top.codermhc.drugmanager.base.entity.PrescriptionDetail;

/**
 * @author Ye Minghui
 */
@RestController
public class PrescriptionDetailController {

    @Resource(name = "prescriptionDetailServiceImpl")
    private PrescriptionDetailService prescriptionDetailService;

    @GetMapping("/prescriptionDetail")
    public PrescriptionDetail get(@RequestParam("id") Long id) {
        return prescriptionDetailService.getById(id);
    }

    @PostMapping("/prescriptionDetail")
    public PrescriptionDetail post(@Validated @RequestBody PrescriptionDetail prescriptionDetail) {
        prescriptionDetailService.save(prescriptionDetail);
        return prescriptionDetail;
    }

    @RequestMapping(value = "/prescriptionDetail", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public boolean modify(@Validated @RequestBody PrescriptionDetail prescriptionDetail) {
        return prescriptionDetailService.updateById(prescriptionDetail);
    }

    @DeleteMapping("/prescriptionDetail")
    public boolean delete(@RequestParam("id") Long id) {
        return prescriptionDetailService.removeById(id);
    }

    @GetMapping(value = "/prescriptionDetail/page")
    public Object page(
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        return prescriptionDetailService.page(new Page<>(page, limit));
    }

    @PostMapping(value = "/prescriptionDetail/list")
    public Object list(@RequestBody List<Long> ids) {
        return prescriptionDetailService.listByIds(ids);
    }

    @DeleteMapping(value = "/prescriptionDetail/list")
    public boolean deleteAll(@RequestBody List<Long> ids) {
        return prescriptionDetailService.removeByIds(ids);
    }

}
