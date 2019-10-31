package com.edu.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthToken {
	/**
	 * 访问所需的身份，默认为空，为登录即可访问，可以多个定义
	 * 
	 * @return
	 * @data 2018年12月19日
	 * @version v1.0.0.0
	 */
	String[] hasRole() default {};
	String[] hasAuth() default {};
	boolean profile() default false; 
	
}
