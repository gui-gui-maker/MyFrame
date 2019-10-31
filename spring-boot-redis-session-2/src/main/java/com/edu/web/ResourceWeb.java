package com.edu.web;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.bean.Resource;
import com.edu.service.impl.ResourceService;
@Controller
@RequestMapping("sr")
public class ResourceWeb {
	@Autowired
	ResourceService sResourceService;
	
	@RequestMapping(value="toResource")
	public String toResource(){
		return "sys-resource";
	}
	
	@RequestMapping(value="findResource")
	@ResponseBody
	public HashMap<String,Object> resource(){
		HashMap<String,Object> wapper =new HashMap<String,Object>();
		List<Resource> list = null;
		try {
			list = sResourceService.findByRank(1);
			wapper.put("success",true);
			wapper.put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			wapper.put("success",false);
			wapper.put("msg", e.getMessage());
		}
		
		return wapper;
	}
	@RequestMapping(value="queryForTree")
	@ResponseBody
	public HashMap<String,Object> queryForTree(){
		HashMap<String,Object> wapper =new HashMap<String,Object>();
		List<Resource> list = null;
		try {
			list = sResourceService.queryForTree();
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
