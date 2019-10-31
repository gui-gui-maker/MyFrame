package com.lsts.expert.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.lsts.expert.bean.ExpertRecord;
import com.lsts.expert.service.ExpertRecordService;

@Controller
@RequestMapping("expertRecordAction")
public class ExpertRecordAction extends SpringSupportAction<ExpertRecord, ExpertRecordService> {

	
	@Autowired
	private ExpertRecordService expertRecordService;
	
	@RequestMapping(value="getExpByType")
	@ResponseBody
	public HashMap<String, Object> getExpByType(String id) throws Exception{
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			List<Object[]> list = expertRecordService.getExpByType(id);
			map.put("success", true);
			map.put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "获取专家信息失败！");
		}
		return map;
	}
	
	/**
	 * 机选专家
	 * author pingZhou
	 * @param request
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("randomExport")
	public HashMap<String, Object> randomExport(HttpServletRequest request,String id){
		
		 HashMap<String, Object> map = new HashMap<String, Object>();
		 
		 try {
			
			 map = expertRecordService.randomExport(id,map);
			 
			 map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "机选专家失败！");
		}
		 
		 return map;
		
	}
	
	
}
