package top.codermhc.drugmanager.base.service.impl;

import top.codermhc.drugmanager.base.entity.Prescription;
import top.codermhc.drugmanager.base.mapper.PrescriptionMapper;
import top.codermhc.drugmanager.base.service.PrescriptionService;
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
public class PrescriptionServiceImpl extends ServiceImpl<PrescriptionMapper, Prescription> implements PrescriptionService {

    @Autowired
    private PrescriptionMapper prescriptionMapper;

}
