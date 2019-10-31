package com.fwxm.scientific.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fwxm.scientific.bean.KYTaskBook;

public class KYTaskBookAction {
	

	@RequestMapping(value = "saveEntity")
	@ResponseBody
	public HashMap<String, Object> saveEntity(HttpServletRequest request,HttpServletResponse response,KYTaskBook entity){
		HashMap<String, Object> map=new HashMap<String, Object>();
		try {
			
			
			map.put("success", true);
//			map.put("data", )
		} catch (Exception e) {
			map.put("success", false);
			map.put("data", "科研项目管理-任务书保存失败！");
			System.err.println(e);
		}
		return map;
	}
}
