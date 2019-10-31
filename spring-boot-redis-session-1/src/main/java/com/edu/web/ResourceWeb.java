package com.edu.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.anno.AuthToken;
import com.edu.bean.Resource;
import com.edu.bean.Role;
import com.edu.bean.User;
import com.edu.service.impl.RedisService;
import com.edu.service.impl.ResourceService;
@Controller
@RequestMapping("resourceWeb")
public class ResourceWeb {
	@Autowired
	ResourceService resourceService;
	
	@Autowired
	private RedisService redisService;
	
	@RequestMapping(value="toResource")
	public String toResource(){
		return "sys-resource";
	}
	
	@RequestMapping(value="findResource")
	@ResponseBody
	@AuthToken
	public HashMap<String,Object> resource(HttpServletRequest request) throws Exception{
		HashMap<String,Object> wapper =new HashMap<String,Object>();
		User user = (User) redisService.get(request.getSession().getId());
		List<Resource> hasResources = new ArrayList<Resource>();
		Set<Role> roles = user.getRoles();
		for (Role role : roles) {
			hasResources.addAll(role.getResources());
		}
		List<Resource> list = resourceService.findByRank(1);
		for(Resource r : list) {
			mark(hasResources,r);
		}
		wapper.put("success",true);
		wapper.put("data", list);
		
		return wapper;
	}
	private void mark(List<Resource> list,Resource r) {
		for (Resource resource : list) {
			if(r.getName().contentEquals(resource.getName())) {
				r.setHasAuth(true);
				break;
			}
		}
		if(r.getChildren()!=null && r.getChildren().size()>0) {
			for(Resource child : r.getChildren()) {
				mark(list,child);
			}
		}
	}
	@RequestMapping(value="queryForTree")
	@ResponseBody
	public HashMap<String,Object> queryForTree(){
		HashMap<String,Object> wapper =new HashMap<String,Object>();
		List<Resource> list = null;
		try {
			list = resourceService.queryForTree();
			wapper.put("success",true);
			wapper.put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			wapper.put("success",false);
			wapper.put("msg", e.getMessage());
		}
		
		return wapper;
	}

}
