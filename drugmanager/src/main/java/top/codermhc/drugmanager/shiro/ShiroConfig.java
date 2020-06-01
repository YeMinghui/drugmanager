package top.codermhc.drugmanager.shiro;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.Filter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.codermhc.drugmanager.utils.JSON;
import top.codermhc.drugmanager.utils.PropertiesParser;

/**
 * @author Ye Minghui
 */
@Slf4j
@Configuration
public class ShiroConfig {

    @Bean
    CustomRealm customRealm() {
        CustomRealm customRealm = new CustomRealm();
        customRealm.setName("custom.realm");
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        customRealm.setAuthenticationCachingEnabled(true);
        customRealm.setAuthenticationCacheName(customRealm.getName() + ".authc");
        customRealm.setAuthorizationCachingEnabled(true);
        customRealm.setAuthorizationCacheName(customRealm.getName() + ".authz");
        return customRealm;
    }

    @Bean
    DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(customRealm());
        manager.setCacheManager(cacheManager());
        manager.setSessionManager(sessionManager());
//        manager.setRememberMeManager(cookieRememberMeManager());
        return manager;
    }

    @Bean
    ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        // 所有访问走ajax请求
        // 登录url不用认证
        chainDefinition.addPathDefinition("/login", "ajax,anon");
        chainDefinition.addPathDefinition("/logout","ajax,logout");
        // 从文件中读取规则
        new PropertiesParser().parse("shiro-rules.properties").forEach(
            (key, value) -> chainDefinition.addPathDefinition(key,"ajax,".concat(value))
        );
        // 默认所有url要认证
        chainDefinition.addPathDefinition("/**", "ajax,authc");
        log.debug("shiro-rules: {}", JSON.string(chainDefinition.getFilterChainMap()));
        return chainDefinition;
    }

    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new RestShiroFilterFactoryBean();
        Map<String, Filter> map = new HashMap<>();
        map.put("ajax", new AjaxFilter());
        map.put("authc", new CustomAuthenticationFilter());
        map.put("perms", new RestPermissionAuthorizationFilter());
        CustomLogoutFilter customLogoutFilter = new CustomLogoutFilter();
        customLogoutFilter.setPostOnlyLogout(true);
        map.put("logout", customLogoutFilter);
        shiroFilterFactoryBean.setFilters(map);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition().getFilterChainMap());
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        return shiroFilterFactoryBean;
    }

    @Bean
    HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("SHA-256");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    @Bean
    CustomCacheManager cacheManager() {
        return new CustomCacheManager();
    }

    @Bean
    SessionManager sessionManager() {
        DefaultWebSessionManager manager = new CustomSessionManager();
        manager.setGlobalSessionTimeout(1000 * 60 * 60);
        return manager;
    }
//
//    @Bean("cookieRememberMeManager")
//    RememberMeManager cookieRememberMeManager() {
//        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
//        cookieRememberMeManager.setCookie(rememberMeCookie());
//        return cookieRememberMeManager;
//    }
//
//    @Bean
//    Cookie rememberMeCookie() {
//        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
//        simpleCookie.setPath("/");
//        simpleCookie.setHttpOnly(true);
//        simpleCookie.setSecure(false);
//        // 7 days.
//        simpleCookie.setMaxAge(60*60*24*7);
//        return simpleCookie;
//    }

}
