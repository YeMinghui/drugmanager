package top.codermhc.drugmanager.base.controller;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import top.codermhc.drugmanager.base.service.SupplierService;
import top.codermhc.drugmanager.base.entity.Supplier;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ye Minghui
 */
@Slf4j
@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Resource(name = "supplierServiceImpl")
    public SupplierService supplierService;

}

