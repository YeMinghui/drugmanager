package top.codermhc.drugmanager.base.controller;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import top.codermhc.drugmanager.base.service.PatientService;
import top.codermhc.drugmanager.base.entity.Patient;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ye Minghui
 */
@Slf4j
@RestController
@RequestMapping("/patient")
public class PatientController {

    @Resource(name = "patientServiceImpl")
    public PatientService patientService;

}

