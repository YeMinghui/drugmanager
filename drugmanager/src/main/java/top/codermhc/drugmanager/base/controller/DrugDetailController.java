package top.codermhc.drugmanager.base.controller;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import top.codermhc.drugmanager.base.service.DrugDetailService;
import top.codermhc.drugmanager.base.entity.DrugDetail;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ye Minghui
 */
@Slf4j
@RestController
@RequestMapping("/drugDetail")
public class DrugDetailController {

    @Resource(name = "drugDetailServiceImpl")
    public DrugDetailService drugDetailService;

}

