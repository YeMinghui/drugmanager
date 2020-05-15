package top.codermhc.drugmanager.base.controller;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import top.codermhc.drugmanager.base.service.MedicalCaseService;
import top.codermhc.drugmanager.base.entity.MedicalCase;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ye Minghui
 */
@Slf4j
@RestController
@RequestMapping("/medicalCase")
public class MedicalCaseController {

    @Resource(name = "medicalCaseServiceImpl")
    public MedicalCaseService medicalCaseService;

}

