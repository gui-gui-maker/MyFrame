package com.khnt.rtbox.config.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rtbox.config.bean.SpecialCharacter;
import com.khnt.rtbox.config.service.SpecialCharacterService;

@Controller
@RequestMapping("specialCharacterAction")
public class SpecialCharacterAction extends SpringSupportAction<SpecialCharacter, SpecialCharacterService>{
	@Autowired
	private SpecialCharacterService specialCharacterService;
	
	@RequestMapping(value = "getAllData")
	@ResponseBody
	public HashMap<String, Object> getAllData(HttpServletRequest request) {
		try {
			List<SpecialCharacter> characters = specialCharacterService.getAllData();
			return JsonWrapper.successWrapper(characters);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapper();
		}
	}
}
