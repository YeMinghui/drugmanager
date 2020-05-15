package top.codermhc.drugmanager.base.service.impl;

import top.codermhc.drugmanager.base.entity.PrescriptionDetail;
import top.codermhc.drugmanager.base.mapper.PrescriptionDetailMapper;
import top.codermhc.drugmanager.base.service.PrescriptionDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @author Ye Minghui
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class PrescriptionDetailServiceImpl extends ServiceImpl<PrescriptionDetailMapper, PrescriptionDetail> implements PrescriptionDetailService {

    @Autowired
    private PrescriptionDetailMapper prescriptionDetailMapper;

}
