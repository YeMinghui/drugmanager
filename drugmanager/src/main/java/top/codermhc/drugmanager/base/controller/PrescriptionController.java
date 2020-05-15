package top.codermhc.drugmanager.base.controller;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import top.codermhc.drugmanager.base.service.PrescriptionService;
import top.codermhc.drugmanager.base.entity.Prescription;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ye Minghui
 */
@Slf4j
@RestController
@RequestMapping("/prescription")
public class PrescriptionController {

    @Resource(name = "prescriptionServiceImpl")
    public PrescriptionService prescriptionService;

}

