package org.eureka.feign.web;

import org.eureka.feign.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@Autowired
	ApiService apiService;
	
	@RequestMapping
	public String test(){
		return apiService.index();
    }
}
