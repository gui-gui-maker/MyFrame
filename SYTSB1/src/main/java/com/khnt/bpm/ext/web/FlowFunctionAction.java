package com.khnt.bpm.ext.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khnt.bpm.ext.bean.FlowFunction;
import com.khnt.bpm.ext.service.FlowFunctionManager;
import com.khnt.core.crud.web.SpringSupportAction;

/**
 * 流程功能管理
 * 
 */
@Controller
@RequestMapping("/bpm/flowFunction/")
public class FlowFunctionAction extends SpringSupportAction<FlowFunction, FlowFunctionManager> {

	@Autowired
	private FlowFunctionManager flowFunctionManager;
}
