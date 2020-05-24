package top.codermhc.drugmanager.base.service.impl;

import top.codermhc.drugmanager.base.entity.Manufacturer;
import top.codermhc.drugmanager.base.mapper.ManufacturerMapper;
import top.codermhc.drugmanager.base.service.ManufacturerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @author Ye Minghui
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class ManufacturerServiceImpl extends ServiceImpl<ManufacturerMapper, Manufacturer> implements ManufacturerService {

}
