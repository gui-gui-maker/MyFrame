package com.lsts.mobileapp.input.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.lsts.mobileapp.input.bean.InspectionNotice;
import com.lsts.mobileapp.input.service.InspectionNoticeService;

@Controller
@RequestMapping("inspectionNoticeAction")
public class InspectionNoticeAction extends SpringSupportAction<InspectionNotice,InspectionNoticeService> {

	@Autowired
	InspectionNoticeService inspectionNoticeService;
	
	@RequestMapping("getByInspectInfoId")
	@ResponseBody
	public HashMap<String, Object> getByInspectInfoId(String infoId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			
			InspectionNotice  notice = inspectionNoticeService.getByInspectInfoId(infoId);
			
			map.put("data", notice);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "查询失败！");
		}
		
		return map;
	}

	@RequestMapping("saveAndSub")
	@ResponseBody
	public HashMap<String, Object> saveAndSub(HttpServletRequest request, InspectionNotice entity) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			
			inspectionNoticeService.saveAndSub(request,entity);
			
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "查询失败！");
		}
		
		return map;
	}
	
	@RequestMapping("callInput")
	@ResponseBody
	public HashMap<String, Object> callInput(HttpServletRequest request, String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			
			inspectionNoticeService.callInput(request,id);
			
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			if("没有整改完成确认！".equals(e.getMessage())) {
				map.put("msg", "没有整改完成确认！");
			}else if("没有检验意见通知书！".equals(e.getMessage())) {
				map.put("msg","没有检验意见通知书！");
			}  else {
				map.put("msg", "查询整改情况失败！");
			}
			
		}
		
		return map;
	}

	/**
	 * 检验意见通知书反馈
	 * @param id
	 * @param feed_that
	 * @param feedback_file_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "feedback")
	public HashMap<String,Object> feedback(String id,String feed_that,String feedback_file_id,String feedback_file_name){
		HashMap<String,Object> map = new HashMap<String, Object>();
		try {
			map = inspectionNoticeService.feedback(id,feed_that,feedback_file_id,feedback_file_name);
		}catch (Exception e){
			map.put("success",false);
			return map;
		}
		return map;
	}

	/**
	 * 通过检验反馈
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "through")
	@ResponseBody
	public HashMap<String,Object> through(String id){
		HashMap<String,Object> map = new HashMap<String, Object>();
		try {
			map = inspectionNoticeService.through(id);
		}catch (Exception e){
			map.put("success",false);
			return map;
		}
		return map;
	}
}
