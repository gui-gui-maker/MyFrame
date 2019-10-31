package com.khnt.rtbox.config.web;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.rtbox.config.bean.BaseConfig;
import com.khnt.rtbox.config.service.BaseConfigService;

@Controller
@RequestMapping("baseConfigAction")
public class BaseConfigAction extends SpringSupportAction<BaseConfig, BaseConfigService> {

	@Autowired
	private BaseConfigService baseConfigService;
	
	@RequestMapping("getCodeTable")
	@ResponseBody
	public HashMap<String, Object> getCodeTable(String name){
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		try {
			List<HashMap<String,String>> list = baseConfigService.getCodeTable(name);
			map.put("data", list);
			map.put("success", true);
			
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "查询失败！");
		}
		
		return map;
	}
	
	@RequestMapping("getCodeTableValues")
	@ResponseBody
	public HashMap<String, Object> getCodeTableValues(String code){
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		try {
			List<HashMap<String,String>> list = baseConfigService.getCodeTableValues(code);
			map.put("data", list);
			map.put("success", true);
			
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "查询失败！");
		}
		
		return map;
	}
	@RequestMapping("getCodeBySql")
	@ResponseBody
	public HashMap<String, Object> getCodeBySql(String sql){
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		try {
			List<HashMap<String, String>> list = baseConfigService.getCodeBySql(sql);
			map.put("data", list);
			map.put("success", true);
			
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		
		return map;
	}
}
