package top.codermhc.drugmanager.base.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
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
import top.codermhc.drugmanager.base.service.DrugDetailService;

/**
 * @author Ye Minghui
 */
@RestController
public class DrugDetailController {

    @Resource(name = "drugDetailServiceImpl")
    private DrugDetailService drugDetailService;

    @GetMapping("/drugDetail")
    public DrugDetail get(@RequestParam("id") Long id) {
        return drugDetailService.getById(id);
    }

    @PostMapping("/drugDetail")
    public DrugDetail post(@Validated @RequestBody DrugDetail drugDetail) {
        drugDetailService.save(drugDetail);
        return drugDetail;
    }

    @RequestMapping(value = "/drugDetail", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public boolean modify(@Validated @RequestBody DrugDetail drugDetail) {
        return drugDetailService.updateById(drugDetail);
    }

    @DeleteMapping("/drugDetail")
    public boolean delete(@RequestParam("id") Long id) {
        return drugDetailService.removeById(id);
    }

    @GetMapping(value = "/drugDetail/page")
    public Object page(
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        return drugDetailService.page(new Page<>(page, limit));
    }

    @PostMapping(value = "/drugDetail/list")
    public Object list(@RequestBody List<Long> ids) {
        return drugDetailService.listByIds(ids);
    }

    @DeleteMapping(value = "/drugDetail/list")
    public boolean deleteAll(@RequestBody List<Long> ids) {
        return drugDetailService.removeByIds(ids);
    }

    @PostMapping(value = "/drugDetail/search")
    public Object search(@RequestParam("type") String type, @RequestParam("value") String value) {
        if (Strings.isBlank(value)) {
            return null;
        }
        SFunction<DrugDetail, ?> function;
        switch (type) {
            case "name":
                function = DrugDetail::getDrugName;
                break;
            case "genericId":
                return drugDetailService.list(Wrappers.<DrugDetail>lambdaQuery().likeRight(DrugDetail::getGenericId, value.trim()));
            case "ingredients":
                function = DrugDetail::getIngredients;
                break;
            case "indications":
                function = DrugDetail::getIndications;
                break;
            case "approvalNumber":
                function = DrugDetail::getApprovalNumber;
                break;
            case "medicareClassify":
                function = DrugDetail::getMedicareClassify;
                break;
            default:
                return null;
        }
        return drugDetailService.list(Wrappers.<DrugDetail>lambdaQuery().like(function, value.trim()));
    }

}
