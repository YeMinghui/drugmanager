package top.codermhc.drugmanager.base.service.impl;

import top.codermhc.drugmanager.base.entity.Role;
import top.codermhc.drugmanager.base.mapper.RoleMapper;
import top.codermhc.drugmanager.base.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @author Ye Minghui
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
