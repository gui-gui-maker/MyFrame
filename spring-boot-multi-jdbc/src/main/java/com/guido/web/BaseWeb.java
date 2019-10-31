package com.guido.web;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseWeb {
	
	@GetMapping("/index")
	public String index(Map<String, Object> model) {
		model.put("time", new Date());
		model.put("message", "hello world");
		return "index";
	}
	@GetMapping("/tables")
	public String tables() {
		
		return "tables";
	}
	@GetMapping("/jqgrid")
	public String jqgrid() {
		
		return "jqgrid";
	}
}
