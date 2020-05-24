package top.codermhc.drugmanager.base.service.impl;

import top.codermhc.drugmanager.base.entity.UserAuthentication;
import top.codermhc.drugmanager.base.mapper.UserAuthenticationMapper;
import top.codermhc.drugmanager.base.service.UserAuthenticationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @author Ye Minghui
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserAuthenticationServiceImpl extends ServiceImpl<UserAuthenticationMapper, UserAuthentication> implements UserAuthenticationService {

}
