package com.khnt.bpm.ext.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.bpm.ext.bean.FlowServiceConfig;
import com.khnt.bpm.ext.service.FlowServiceConfigManager;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;

/**
 * 业务流程配置Controller
 * 
 * @author hansel 2013-02-22
 * 
 */
@Controller
@RequestMapping("/bpm/serviceConfig/")
public class FlowServiceConfigAction extends SpringSupportAction<FlowServiceConfig, FlowServiceConfigManager> {
	@Autowired
	private FlowServiceConfigManager flowServiceConfigManager;

	@RequestMapping("getFlowServiceConfig")
	@ResponseBody
	public Map<String, Object> getFlowServiceConfig(String serviceCode, String orgId) throws Exception {
		return JsonWrapper.successWrapper(this.flowServiceConfigManager.queryServiceConfig(serviceCode, orgId));
	}
}
