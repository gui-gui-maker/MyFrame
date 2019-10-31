package com.edu.web;



import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.anno.AuthToken;
import com.edu.bean.University0;
import com.edu.bean.User;
import com.edu.service.UniversityService;
import com.edu.service.UserService;
import com.edu.service.impl.RedisService;

@Controller
public class LoginWeb {
	@Autowired
	private UserService userService;
	@Autowired
	private UniversityService universityService;
	@Autowired
	private RedisService redisService;
	 
	@RequestMapping(value = "/toLogin")
    public String tologin (HttpServletRequest request){
    	return "login";
    }
	
	@PostMapping(value = "/login")
    public String login (HttpServletRequest request,HttpServletResponse response,
    		String userName,String password,String rememberMe) throws Exception{
		
		 User user = userService.findByUserName(userName);
		
	     if (user!=null && user.getPassword().equals(password)){
	         //将角色存入redis ? 设置失效时间20分钟
	         redisService.set(request.getSession().getId(), user,12000l);
	         
	         if("on".equals(rememberMe)) {
	        	 Cookie cookie = new Cookie("logininfo",userName+"-"+password+"-"+rememberMe);
	        	 cookie.setMaxAge(3600*24*30);
	        	 cookie.setPath("/");
	        	 response.addCookie(cookie);
	         }else {
	        	 Cookie killMyCookie = new Cookie("logininfo", null);
	        	 killMyCookie.setMaxAge(0);
	        	 killMyCookie.setPath("/");
	        	 response.addCookie(killMyCookie);
	         }
	     }
         return "redirect:index";
    }
	
	@GetMapping(value = "/index")
	@AuthToken
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/loginout")
    public String loginout (HttpServletRequest request){
    	//获取id
        String id = request.getSession().getId();
        //redis 中移除
        redisService.remove(id);
        return "login";
    }
	
	@GetMapping("/toProfile")
	@AuthToken
	public String toProfile() {
		return "profile";
	}
	
	@GetMapping("/toNoAuth")
	public String toNoAuth() {
		return "noAuth";
	}
	
	
}
