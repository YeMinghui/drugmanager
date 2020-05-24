package top.codermhc.drugmanager.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.codermhc.drugmanager.base.entity.Department;
import top.codermhc.drugmanager.base.mapper.DepartmentMapper;
import top.codermhc.drugmanager.base.service.DepartmentService;
/**
 * @author Ye Minghui
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

}
