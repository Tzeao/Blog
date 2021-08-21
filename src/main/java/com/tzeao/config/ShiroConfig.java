package com.tzeao.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 君子慎独
 */
@Configuration
public class ShiroConfig {

    /**
     * 3.ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        //        绑定Manager,设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        Map<String, String> linkedMap = new LinkedHashMap<>();
//         编写   ,路径为controller里面的路径
        linkedMap.put("/", "anon");
        linkedMap.put("/tags", "anon");
        linkedMap.put("/types", "anon");
        linkedMap.put("/about", "anon");
        linkedMap.put("/index", "anon");
        linkedMap.put("/archives", "anon");
        linkedMap.put("/blog", "anon");
        linkedMap.put("/admin/blogs", "authc");
        linkedMap.put("/admin", "anon");
        linkedMap.put("/admin/login", "anon");
        linkedMap.put("/admin/blogs-input", "authc");
        linkedMap.put("/admin/logout", "authc");
        linkedMap.put("/admin/types", "authc");
        linkedMap.put("/admin/tags", "authc");
        linkedMap.put("/admin/types/**", "authc");
        linkedMap.put("/admin/tags/**", "authc");
        bean.setFilterChainDefinitionMap(linkedMap);
//      没有权限时会跳转 登录页面
        bean.setLoginUrl("/admin");
        return bean;
    }

    //    2.DefaultWebSecurityManager

    /**
     * a@Qualifier绑定下面创建的realm方法
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();

        //        关联realm
        manager.setRealm(userRealm);
        return manager;
    }

    /**
     * 1.创建realm对象，需要自定义
     */
    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        return hashedCredentialsMatcher;
    }
}