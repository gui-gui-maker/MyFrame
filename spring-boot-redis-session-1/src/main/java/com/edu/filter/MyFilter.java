package com.edu.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.edu.bean.Profile;
import com.edu.bean.Role;
import com.edu.bean.User;
import com.edu.service.ProfileService;
import com.edu.service.impl.RedisService;

import java.io.IOException;
import java.util.Set;

public class MyFilter implements Filter {

	private RedisService redisService;
	
	private ProfileService profileService;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		 ServletContext servletContext = filterConfig.getServletContext();
		 ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		 redisService = ctx.getBean("redisService",RedisService.class);
		 profileService = ctx.getBean("profileService",ProfileService.class);

	}

	@Override
	public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) srequest;
		HttpServletResponse response = (HttpServletResponse) sresponse;
		System.out.println("this is MyFilter,url :"+request.getRequestURI());
		String id = request.getSession().getId();
		User user = (User)redisService.get(id);
		Set<Role> roles = user.getRoles();
		for (Role role : roles) {
			if(role.getRole().equals("user")) {
				//判断是否访问profile,
				if("/planWeb/viewUniversityPlans".equals(request.getRequestURI())) {
					//否则判断资料是否完成
					try {
						Profile profile  = profileService.findByUser(user);
						if("0".equals(profile.getStatus())) {
							response.sendRedirect(request.getContextPath()+"/toProfile");
							return;
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			}
		}
		filterChain.doFilter(srequest, sresponse);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

}