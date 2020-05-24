package top.codermhc.drugmanager.shiro;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import javax.annotation.Resource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import top.codermhc.drugmanager.base.entity.Role;
import top.codermhc.drugmanager.base.entity.UserAuthentication;
import top.codermhc.drugmanager.base.service.RoleService;
import top.codermhc.drugmanager.base.service.UserAuthenticationService;
import top.codermhc.drugmanager.base.service.UserService;
import top.codermhc.drugmanager.utils.UserFlag;

/**
 * 自定义Shiro Realm, 使用workId和Password作为登录的凭证.
 *
 * @author Ye Minghui
 */
public class CustomRealm extends AuthorizingRealm {

    @Resource(name = "userServiceImpl")
    @Lazy
    private UserService userService;

    @Resource(name = "userAuthenticationServiceImpl")
    @Lazy
    private UserAuthenticationService userAuthenticationService;

    @Resource(name = "roleServiceImpl")
    @Lazy
    private RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserAuthentication authentication = (UserAuthentication) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Role role = roleService.getById(authentication.getRoleId());
        authorizationInfo.addRoles(Collections.singleton(role.getName()));
        authorizationInfo.addStringPermission(role.getPerms());
        return authorizationInfo;
    }

    /**
     * 获取认证信息
     *
     * @param authenticationToken 用户携带凭证
     * @return 认证信息
     * @throws AuthenticationException 用户名或密码错误时抛出认证异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
        throws AuthenticationException {
        String workId = (String) authenticationToken.getPrincipal();
        Object credentials = authenticationToken.getCredentials();
        if (workId == null) {
            throw new UnknownAccountException();
        }
        if (credentials == null) {
            throw new IncorrectCredentialsException();
        }
        UserAuthentication authentication = userAuthenticationService
            .getOne(Wrappers.<UserAuthentication>lambdaQuery().eq(UserAuthentication::getWorkId, workId));

        if (authentication == null) {
            throw new UnknownAccountException();
        }
        authentication.setLastLoginTime(LocalDateTime.now());
        userAuthenticationService.update(
            Wrappers.<UserAuthentication>lambdaUpdate().eq(UserAuthentication::getId, authentication.getId())
                .set(UserAuthentication::getLastLoginTime, authentication.getLastLoginTime()));
        return new SimpleAuthenticationInfo(authentication, authentication.getPassword(),
            new CustomByteSource(authentication.getSalt()), getName());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }
}
