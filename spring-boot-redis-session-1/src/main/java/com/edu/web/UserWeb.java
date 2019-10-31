package com.edu.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.anno.AuthToken;
import com.edu.bean.Profile;
import com.edu.bean.User;
import com.edu.hb.repository.UserDao;
import com.edu.service.ProfileService;
import com.edu.service.impl.RedisService;
import com.edu.util.BusinessUtil;

import javax.servlet.http.HttpServletRequest;

@Controller()
@RequestMapping("userWeb")
public class UserWeb {

    @Autowired
    private UserDao userDao;
    
    @Autowired
	private RedisService redisService;
    @Autowired
    private ProfileService profileService;

    @RequestMapping(value = "/userList")
    @AuthToken(hasRole = { "admin", "Administrator" })
    public String userList (HttpServletRequest request){
        
        return "userList";
    }

    @RequestMapping(value = "/userListData")
    @ResponseBody
    public Object getSession (HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("result", 1);
        map.put("data", userDao.findAll());
        return map;
    }
    @RequestMapping(value = "/saveProfile")
    @ResponseBody
    @AuthToken(hasRole= {"user"})
    public Map<String, Object> saveProfile (HttpServletRequest request,Profile profile) throws Exception{
    	Map<String, Object> map = new HashMap<>();
    	User user = (User) redisService.get(request.getSession().getId());
    	profile.setUser(user);
    	//检查资料是否完善
		boolean b = BusinessUtil.checkProfile(profile);
		if(b) {
			profile.setStatus("1");
		}else {
			profile.setStatus("0");
		}
    	profileService.save(profile);
    	user.getProfiles().clear();
    	user.getProfiles().add(profile);
    	
    	redisService.set(request.getSession().getId(),user,12000l);
    	map.put("success", true);
    	map.put("data", profile);
    	return map;
    }

}