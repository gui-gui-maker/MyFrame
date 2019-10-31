package com.lsts.report.web;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("report/nk")
public class ReportNkAction {
	
	
	
	
	@RequestMapping(value = "su")
	@ResponseBody
	public HashMap<String, Object> su(String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (id.isEmpty()){
			map.put("success", false);
			map.put("msg", "所选业务ID为空！");
		} else {
			
		}
		return map;
	}
}
