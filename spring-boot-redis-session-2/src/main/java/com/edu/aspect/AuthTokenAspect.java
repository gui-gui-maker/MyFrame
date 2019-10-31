package com.edu.aspect;


import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.edu.anno.AuthToken;
import com.edu.bean.Role;
import com.edu.bean.User;
import com.edu.service.impl.RedisService;

@Aspect
@Component
public class AuthTokenAspect {
	
	@Autowired
	private RedisService redisService;
	
	/**
	 * Spring中使用@Pointcut注解来定义方法切入点
	 * @Pointcut 用来定义切点，针对方法  @Aspect 用来定义切面，针对类
	 * 后面的增强均是围绕此切入点来完成的
	 * 此处仅配置被我们刚才定义的注解：AuthToken修饰的方法即可
	 * 
	 */
	@Pointcut("@annotation(authToken)")
	public void doAuthToken(AuthToken authToken) {
	}

	/**
	 * 此处我使用环绕增强，在方法执行之前或者执行之后均会执行。
	 */
	@Around("doAuthToken(authToken)")
	public Object deBefore(ProceedingJoinPoint pjp, AuthToken authToken) throws Throwable {
		
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		
		System.out.println(request.getRequestURL().toString());
		
		User user = (User) redisService.get(request.getSession().getId());
		
		if(null==user) {
			return "redirect:toLogin";
		}
		// 获取访问该方法所需的role_name信息
		String[] role_name = authToken.hasRole();
		if (role_name == null || role_name.length == 0 || "admin".equals(user.getUserName())) {
			// 登录即可访问。
			return pjp.proceed();
			
		}else {
			//需要访问权限
			Object object = pjp.proceed();
			// 需要验证身份
			for (String one_role : role_name) {
				
				for (Role role : user.getRoles()) {
					//Role role = (Role) JSONObject.toBean(JSONObject.fromObject(obj), Role.class);
					// 身份匹配成功
					if(role.getCode().equals(one_role)) {
						if(null != user.getContacts() && user.getContacts().size()>0) {
							return object;
						}else {
							
							return "redirect:toProfile";
						}
					}
				}
			}
			return "redirect:toNoAuth";
		}
	}

}
