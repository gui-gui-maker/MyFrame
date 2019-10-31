package com.edu.security;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class ShiroConfig {
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(org.apache.shiro.mgt.SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/favicon.ico", "anon");
		filterChainDefinitionMap.put("/**", "authc");
		// authc表示需要验证身份才能访问，还有一些比如anon表示不需要验证身份就能访问等。
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/index");
		// shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		// 这里设置403并不会起作用，参考http://www.jianshu.com/p/e03f5b54838c
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	

	@Bean
	public MyShiroRealm myShiroRealm() {
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		// 设置解密规则
		return myShiroRealm;
	}
	
	//SecurityManager 是 Shiro 架构的核心，通过它来链接Realm和用户(文档中称之为Subject.)
	@Bean
	public org.apache.shiro.mgt.SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());
		// 将Realm注入到SecurityManager中。
		return securityManager;
	}

	//因为我们的密码是加过密的，所以，如果要Shiro验证用户身份的话，
	//需要告诉它我们用的是md5加密的，并且是加密了两次。
	//同时我们在自己的Realm中也通过SimpleAuthenticationInfo返回了加密时使用的盐。
	//这样Shiro就能顺利的解密密码并验证用户名和密码是否正确了。
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");
		// 散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashIterations(2);
		// 散列的次数，比如散列两次，相当于 md5(md5(""));
		return hashedCredentialsMatcher;
	}
}
