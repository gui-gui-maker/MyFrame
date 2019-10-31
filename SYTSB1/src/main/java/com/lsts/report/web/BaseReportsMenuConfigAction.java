package com.lsts.report.web;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.khnt.core.crud.web.SpringSupportAction;
import com.lsts.report.bean.BaseReportsMenuConfig;
import com.lsts.report.service.BaseReportsMenuConfigService;

@Controller
@RequestMapping("baseReportsMenuConfigAction")
public class BaseReportsMenuConfigAction extends SpringSupportAction<BaseReportsMenuConfig, BaseReportsMenuConfigService> {

	@Autowired
	private BaseReportsMenuConfigService reportsMenuConfigService;
	
	/**
	 * 查看报告目录配置信息
	 * author pingZhou
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getReportMenuConfig")
	public HashMap<String, Object> getReportMenuConfig(String id){
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			List<BaseReportsMenuConfig> reportCertList = reportsMenuConfigService.getReportMenuConfig(id);
			map.put("datalist", reportCertList);
			map.put("success", true);
		} catch (Exception e) {
			
			map.put("msg", "查询失败");
			map.put("success", false);
		}
		
		return map;
	}
	
	/**
	 * 查询模板的目录
	 * author pingZhou
	 * @param rtCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getRecordModelDir")
	public HashMap<String, Object> getRecordModelDir(String rtCode){
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			JSONArray menus = reportsMenuConfigService.getRecordModelDir(rtCode);
			map.put("menus", menus);
			map.put("success", true);
		} catch (Exception e) {
			
			map.put("msg", "查询失败");
			map.put("success", false);
		}
		
		return map;
	}
	
	
}
