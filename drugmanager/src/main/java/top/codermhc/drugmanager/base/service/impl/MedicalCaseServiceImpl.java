package top.codermhc.drugmanager.base.service.impl;

import top.codermhc.drugmanager.base.entity.MedicalCase;
import top.codermhc.drugmanager.base.mapper.MedicalCaseMapper;
import top.codermhc.drugmanager.base.service.MedicalCaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @author Ye Minghui
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class MedicalCaseServiceImpl extends ServiceImpl<MedicalCaseMapper, MedicalCase> implements MedicalCaseService {

}
