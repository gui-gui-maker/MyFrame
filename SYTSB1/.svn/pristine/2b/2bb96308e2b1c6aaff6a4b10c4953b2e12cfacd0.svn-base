package com.lsts.mobileapp.audit.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.utils.StringUtil;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.mobileapp.audit.service.AuditService;
import com.lsts.mobileapp.input.bean.RecordModelDir;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Controller
@RequestMapping("auditAction")
public class AuditAction extends SpringSupportAction<InspectionInfo, AuditService>{
	
	@Autowired
	AuditService auditService;
	
	// 保存报告审核
	@RequestMapping(value = "pass")
	@ResponseBody
	public Map<String, Object> pass(HttpServletRequest request,String formObject,String businessArray) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			JSONObject formData = JSONObject.fromString(formObject);
			JSONArray businesses = JSONArray.fromString(businessArray);
				
			List<InspectionInfo> list = auditService.pass(request,businesses,formData);
			map.put("data",list);
			map.put("success",true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg",e.getMessage());
			map.put("success",true);
		}
		return map;
	}
	@RequestMapping(value = "failed")
	@ResponseBody
	public Map<String, Object> failed(HttpServletRequest request,String formObject,String businessArray) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			JSONObject formData = JSONObject.fromString(formObject);
			JSONArray businesses = JSONArray.fromString(businessArray);
			
			List<InspectionInfo> list = auditService.auditBack(request,businesses, formData);
			
			map.put("data",list);
			map.put("success",true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg",e.getMessage());
			map.put("success",true);
		}
		return map;
	}
	
	@RequestMapping(value="getAuditMission")
	@ResponseBody
	public HashMap<String, Object> getAuditMission() throws Exception{
		HashMap<String, Object> map = new HashMap<String,Object>();
		try {
			List<Object[]> list = auditService.getAuditMission();
			map.put("success", true);
			map.put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", true);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	@RequestMapping(value="loadReportDir")
	@ResponseBody
	public HashMap<String, Object> loadReportDir(String id, String code) throws Exception{
		HashMap<String, Object> map = new HashMap<String,Object>();
		try {
			if(code==null||StringUtil.isEmpty(code)||"undefined".equals(code)) {
				code = auditService.getRtcode(id);
			}
			String dir = auditService.loadReportDir(id,code);
			JSONArray dirs = JSONArray.fromString(dir);
			List<RecordModelDir> list = new ArrayList<RecordModelDir>();
			list = getlistByJson(list,dirs.getJSONObject(0),0,id);
			map.put("data", list);
			map.put("code", code);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	private List<RecordModelDir> getlistByJson(List<RecordModelDir> list, JSONObject jsonObject,Integer n, String reportId) {
		if(!"root".equals(jsonObject.getString("code"))) {
			RecordModelDir dir = new RecordModelDir();
			
			dir.setFkModelId(reportId);
			dir.setOrders(n+1);
			dir.setPageCode(jsonObject.getString("code"));
			dir.setPageName(jsonObject.getString("name"));
			String pageNum = jsonObject.getString("code").split("__")[0];
			dir.setPagePath("index"+pageNum+".jsp");
			
			list.add(dir);
		}
		if(jsonObject.has("children")&&jsonObject.get("children")!=null) {
			JSONArray children = jsonObject.getJSONArray("children");
			for (int i = 0; i < children.length(); i++) {
				getlistByJson(list,children.getJSONObject(i),n++,reportId);
			}
		}else {
			n++;
		}
		
		return list;
	}
	
	
}
