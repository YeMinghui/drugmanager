package top.codermhc.drugmanager.base.controller;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import top.codermhc.drugmanager.base.service.PrescriptionDetailService;
import top.codermhc.drugmanager.base.entity.PrescriptionDetail;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ye Minghui
 */
@Slf4j
@RestController
@RequestMapping("/prescriptionDetail")
public class PrescriptionDetailController {

    @Resource(name = "prescriptionDetailServiceImpl")
    public PrescriptionDetailService prescriptionDetailService;

}

