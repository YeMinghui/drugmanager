package top.codermhc.drugmanager.base.service.impl;

import top.codermhc.drugmanager.base.entity.Patient;
import top.codermhc.drugmanager.base.mapper.PatientMapper;
import top.codermhc.drugmanager.base.service.PatientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @author Ye Minghui
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient> implements PatientService {

}
