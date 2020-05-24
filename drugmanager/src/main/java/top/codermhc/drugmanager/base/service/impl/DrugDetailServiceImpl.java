package top.codermhc.drugmanager.base.service.impl;

import top.codermhc.drugmanager.base.entity.DrugDetail;
import top.codermhc.drugmanager.base.mapper.DrugDetailMapper;
import top.codermhc.drugmanager.base.service.DrugDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @author Ye Minghui
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class DrugDetailServiceImpl extends ServiceImpl<DrugDetailMapper, DrugDetail> implements DrugDetailService {

}
