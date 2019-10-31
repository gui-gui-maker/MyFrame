package com.khnt.rtbox.config.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.rtbox.config.bean.RtRule;
import com.khnt.rtbox.config.service.RtRuleManager;

/**
 * <p>
 * 类说明
 * </p>
 * 
 * @ClassName Rule
 * @JDK 1.5
 * @author CODER_V3.0
 * @date 2016-03-22 09:51:04
 */
@Controller
@RequestMapping("com/rt/rule")
public class RtRuleAction extends SpringSupportAction<RtRule, RtRuleManager> {

	@Autowired
	private RtRuleManager rtRuleManager;

}
