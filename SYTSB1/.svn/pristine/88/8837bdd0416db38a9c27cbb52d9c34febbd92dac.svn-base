package com.lsts.filter;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;

public class PrintFilter extends OncePerRequestFilter {  	

    
    @Override  
    protected void doFilterInternal(HttpServletRequest request,  
            HttpServletResponse response, FilterChain filterChain)  
            throws ServletException, IOException {  
        String validPwd = request.getParameter("validPwd");
        // 请求的URI(如果被过滤器过滤了，则把这个原始请求的uri带会页面，在页面登录之后才好继续跳转)
        String uri = request.getRequestURI();
        // 请求参数中包含validPwd时才进行过滤  
        if (StringUtil.isNotEmpty(validPwd)) {  
            // 是否过滤  
            boolean doFilter = true;  
            if (doFilter) {  
                // 执行过滤  
                // 从session中获取验第二次验证信息  
                Object obj = request.getSession().getAttribute("SecondPwd");  
                if (null == obj) {  
                	/*CurrentSessionUser user = SecurityUtil.getSecurityUser(); 	// 获取当前用户登录信息
                	String user_name = user.getName();
                	if("孙宇艺".equals(user_name.trim())){
                		// 直接进入目标页面，不进行独立密码验证页面跳转
                		filterChain.doFilter(request, response);
                	}else{*/
                		// 如果session中不存在第二次验证信息，则跳转到第二次验证页面  
                        System.out.println("===========OMG,这咋就进来了呢.呢.呢呢呢============");
                        request.setAttribute("origin_uri", uri);
                        request.getRequestDispatcher("/app/flow/export/validate_print_pwd.jsp").forward(request,response);
                	//}
                } else {  
                    //session中存在第二次验证信息,则该干啥干啥。
                    filterChain.doFilter(request, response);  
                }  
            } else {  
                // 如果不执行过滤，请你主动过滤本消息。
                filterChain.doFilter(request, response);  
            }  
        } else {  
            // 如果请求参数里面不包含validPwd，各回各家，各找各妈。
            filterChain.doFilter(request, response);  
        }  
    } 
    
}
