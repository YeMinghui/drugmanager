package top.codermhc.drugmanager.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.ServletContainerSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ye Minghui
 */
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
        manager.setRememberMeManager(cookieRememberMeManager());
        return manager;
    }

    @Bean
    ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/login.html", "anon");
        chainDefinition.addPathDefinition("/login", "anon");
        chainDefinition.addPathDefinition("/forgot.html","anon");
        chainDefinition.addPathDefinition("/forgot","anon");
        chainDefinition.addPathDefinition("/static/**", "anon");
        chainDefinition.addPathDefinition("/**", "user");
        return chainDefinition;
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

//    @Value("${shiro.sessionManager.sessionIdCookieEnabled:true}") boolean sessionIdCookieEnable;
//    @Value("${shiro.sessionManager.sessionIdUrlRewritingEnabled:false}") boolean sessionIdUrlRewritingEnable;
    @Bean
    SessionManager sessionManager() {
        WebSessionManager manager = new ServletContainerSessionManager();
//        SessionManager manager = new DefaultWebSessionManager();
//        manager.setSessionFactory(sessionFactory());
//        manager.setSessionDAO(sessionDAO());
//        manager.setGlobalSessionTimeout(AbstractSessionManager.DEFAULT_GLOBAL_SESSION_TIMEOUT);
//        manager.setSessionValidationSchedulerEnabled(true);
//        manager.setSessionIdCookie(simpleCookie());
//        manager.setSessionIdCookieEnabled(sessionIdCookieEnable);
//        manager.setSessionIdUrlRewritingEnabled(sessionIdUrlRewritingEnable);
        return manager;
    }

    @Bean("cookieRememberMeManager")
    RememberMeManager cookieRememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        return cookieRememberMeManager;
    }

    @Bean
    Cookie rememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setPath("/");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setSecure(false);
        // 7 days.
        simpleCookie.setMaxAge(60*60*24*7);
        return simpleCookie;
    }

}
