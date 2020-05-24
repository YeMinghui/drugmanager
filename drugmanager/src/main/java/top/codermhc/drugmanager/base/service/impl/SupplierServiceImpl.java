package top.codermhc.drugmanager.base.service.impl;

import top.codermhc.drugmanager.base.entity.Supplier;
import top.codermhc.drugmanager.base.mapper.SupplierMapper;
import top.codermhc.drugmanager.base.service.SupplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @author Ye Minghui
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements SupplierService {

}
