package com.edu.web;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.anno.AuthToken;
import com.edu.bean.Profile;
import com.edu.service.ProfileService;
import com.edu.bean.Role;
import com.edu.bean.User;
import com.edu.service.UserService;
import com.edu.service.impl.RedisService;
import com.edu.util.BusinessUtil;

@Controller
public class LoginWeb {
	@Autowired
	private UserService userService;

	@Autowired
	private RedisService redisService;
	
	@Autowired
	ProfileService profileService;
	 
	@GetMapping(value = {"/toLogin","/"})
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
	         request.getSession().setAttribute("username", user.getUsername());
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
	     Set<Role> roles = user.getRoles();
	     for(Role role : roles) {
	    	 if(role.getRole().equals("user")){
	    		 return "redirect:toProfile";
	    	 }
	     }
	     return "redirect:index";
    }
	
	@GetMapping(value = "/index")
	@AuthToken(hasRole= {"admin"})
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/loginout")
    public String loginout (HttpServletRequest request){
    	//获取id
        String id = request.getSession().getId();
        //redis 中移除
        redisService.remove(id);
        request.getSession().removeAttribute("username");
        return "login";
    }
	
	@GetMapping("/toProfile")
	@AuthToken(hasRole= {"user"})
	public String toProfile(HttpServletRequest request) throws Exception {
		User user = (User) redisService.get(request.getSession().getId());
		if(user.getProfiles().iterator().hasNext()) {
			
			request.setAttribute("profile",user.getProfiles().iterator().next());
		}else {
			Profile profile = new Profile();
			profile.setUser(user);
			//检查资料是否完善
			boolean b = BusinessUtil.checkProfile(profile);
			if(b) {
				profile.setStatus("1");
			}else {
				profile.setStatus("0");
			}
			profileService.save(profile); 
			request.setAttribute("profile",profile);
			Set<Profile> profiles = new HashSet<Profile>();
			//刷新缓存中的user
			profiles.add(profile);
			user.setProfiles(profiles);
			redisService.set(request.getSession().getId(), user,12000l);
		}
		return "profile";
	}
	
	@GetMapping("/toNoAuth")
	public String toNoAuth() {
		return "noAuth";
	}
	
	
}
