package com.lsts.mobileapp.company.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khnt.core.crud.web.SpringSupportAction;
import com.lsts.mobileapp.company.service.EnterService;
import com.lsts.org.bean.EnterInfo;

@Controller
@RequestMapping("appenterAction")
public class AppEnterAction extends SpringSupportAction<EnterInfo, EnterService>{

	@Autowired
	private EnterService enterService;

	
}
