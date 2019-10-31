package com.lsts.mobileapp.login.web;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.lsts.mobileapp.login.bean.Online;
import com.lsts.mobileapp.login.service.OnlineService;

@Controller
@RequestMapping("onlineAction")
public class OnlineAction extends SpringSupportAction<Online, OnlineService>{

	@Autowired
	OnlineService onlineService;
	
	@RequestMapping(value="online")
	@ResponseBody
	public HashMap<String, Object> online(String userId,String clientId,String orgId) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			onlineService.online(userId,clientId,orgId);
			map.put("success", true);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		
		return map;
	}
	@RequestMapping(value="offline")
	@ResponseBody
	public HashMap<String, Object> offline(String userId,String clientId,String orgId) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			onlineService.offline(userId,clientId,orgId);
			map.put("success", true);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		
		return map;
	}
	
}
