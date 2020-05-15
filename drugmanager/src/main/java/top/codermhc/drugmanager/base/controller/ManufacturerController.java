package top.codermhc.drugmanager.base.controller;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import top.codermhc.drugmanager.base.service.ManufacturerService;
import top.codermhc.drugmanager.base.entity.Manufacturer;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ye Minghui
 */
@Slf4j
@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {

    @Resource(name = "manufacturerServiceImpl")
    public ManufacturerService manufacturerService;

}

