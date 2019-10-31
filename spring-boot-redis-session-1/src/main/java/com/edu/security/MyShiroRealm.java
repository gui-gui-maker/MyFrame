package com.edu.security;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.edu.bean.Auth;
import com.edu.bean.Role;
import com.edu.bean.User;
import com.edu.service.UserService;

public class MyShiroRealm extends AuthorizingRealm { 
	@Resource 
	private UserService userService; 
	@Override 
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("开始身份验证"); 
		String username = (String) token.getPrincipal(); 
		//获取用户名，默认和login.html中的username对应。 
		User user = userService.findByUserName(username); 
		if (user == null) { 
			//没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常 
			return null;
		} 
		//验证通过返回一个封装了用户信息的AuthenticationInfo实例即可。 
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, //用户信息 
				user.getPassword(), //密码 
				getName() //realm name
				);
		authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt())); 
		//设置盐 
		return authenticationInfo;
	}
	//当访问到页面的时候，链接配置了相应的权限或者shiro标签才会执行此方法否则不会执行
	@Override 
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("开始权限配置"); 
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		User user = (User) principals.getPrimaryPrincipal(); 
		for (Role role: user.getRoles()) {
			authorizationInfo.addRole(role.getRole()); 
			for (Auth p: role.getAuths()) {
				authorizationInfo.addStringPermission(p.getName());}} 
		return authorizationInfo;
	} 
}

