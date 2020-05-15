package top.codermhc.drugmanager.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.session.mgt.AbstractSessionManager;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author Ye Minghui
 */
@Configuration
public class ShiroConfig {

    @Bean
    CustomRealm customRealm() {
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        customRealm.setAuthenticationCachingEnabled(true);
        customRealm.setAuthorizationCachingEnabled(true);
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
        chainDefinition.addPathDefinition("/logout", "anon");
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
    ShiroCacheManager cacheManager() {
        return new ShiroCacheManager();
    }

    @Bean(name = "shiroRedisTemplate")
    RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        redisTemplate.setDefaultSerializer(RedisSerializer.java());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Value("${shiro.sessionManager.sessionIdCookieEnabled:true}") boolean sessionIdCookieEnable;
    @Value("${shiro.sessionManager.sessionIdUrlRewritingEnabled:false}") boolean sessionIdUrlRewritingEnable;
    @Bean
    SessionManager sessionManager() {
        DefaultWebSessionManager manager = new CustomSessionManager();
        manager.setSessionFactory(sessionFactory());
        manager.setSessionDAO(sessionDAO());
        manager.setGlobalSessionTimeout(AbstractSessionManager.DEFAULT_GLOBAL_SESSION_TIMEOUT);
        manager.setSessionValidationSchedulerEnabled(true);
        manager.setSessionIdCookie(simpleCookie());
        manager.setSessionIdCookieEnabled(sessionIdCookieEnable);
        manager.setSessionIdUrlRewritingEnabled(sessionIdUrlRewritingEnable);
        return manager;
    }

    @Bean(name = "sessionFactory")
    SessionFactory sessionFactory() {
        return new ShiroSessionFactory();
    }

    @SuppressWarnings("unchecked")
    @Bean
    SessionDAO sessionDAO() {
        return new ShiroSessionDAO(cacheManager().getCache("session"));
    }

    @Bean
    Cookie simpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("session");
        simpleCookie.setPath("/");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setSecure(false);
        simpleCookie.setMaxAge(60*60);
        return simpleCookie;
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
