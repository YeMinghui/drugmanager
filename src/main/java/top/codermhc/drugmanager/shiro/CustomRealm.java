package top.codermhc.drugmanager.shiro;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import top.codermhc.drugmanager.entity.UserAuthentication;
import top.codermhc.drugmanager.service.UserService;

/**
 * 自定义Shiro Realm, 使用workId和Password作为登陆的凭证.
 *
 * @author Ye Minghui
 */
public class CustomRealm extends AuthorizingRealm {

    {setName("customRealm");}

    @Autowired
    @Lazy
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
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
        UserAuthentication authentication = userService.findUserAuthenticationByWorkId(workId);

        if (authentication == null) {
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(authentication, authentication.getPassword(), new CustomByteSource(authentication.getSalt()), getName());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }
}
