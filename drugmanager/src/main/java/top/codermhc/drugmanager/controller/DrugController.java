package top.codermhc.drugmanager.controller;

import javax.annotation.Resource;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.codermhc.drugmanager.base.entity.DrugDetail;
import top.codermhc.drugmanager.base.service.DrugDetailService;

/**
 * 药品基本信息管理
 * 采用 RESTful 接口实现
 *
 * @author Ye Minghui
 */
@RestController
@RequestMapping("/drug")
public class DrugController extends BaseController{

    @Resource(name = "drugDetailServiceImpl")
    private DrugDetailService drugDetailService;

    @RequiresPermissions({"drug:get", "drug:*"})
    @GetMapping("/{id}")
    public DrugDetail info(@PathVariable("id") Long id) {
        return drugDetailService.getById(id);
    }

    @PostMapping("/")
    public String add() {
        return "";
    }

    @PatchMapping("/")
    public String modify() {
        return "";
    }

    @DeleteMapping("/")
    public String delete() {
        return "";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hellowoer";
    }

}
