package com.lsts.approve.web;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.lsts.approve.bean.SysRecord;
import com.lsts.approve.bean.SysRecordSearch;
import com.lsts.approve.service.SysRecordService;

@Controller
@RequestMapping("sysRecordAction")
public class SysRecordAction extends SpringSupportAction<SysRecord,  SysRecordService>{
	@Autowired
	private SysRecordService sysRecordService;
	
	@RequestMapping("getRecords")
	@ResponseBody
	public HashMap<String, Object> getRecords(HttpServletRequest request) throws Exception{
		HashMap<String, Object> map = new HashMap<String , Object>();
		try{
			int page = Integer.parseInt(request.getParameter("page"));
			int pageSize = Integer.parseInt(request.getParameter("pagesize"));
			map = sysRecordService.getRecords(page,pageSize);
		}catch(Exception e){
			e.printStackTrace();
			map.put("Rows", null);
			map.put("Total", 0);
		}
		return map;
	}
	@RequestMapping("searchRecords")
	@ResponseBody
	public HashMap<String, Object> searchRecords(HttpServletRequest request,@RequestBody SysRecordSearch filter) throws Exception{
		HashMap<String, Object> map = new HashMap<String , Object>();
		try{
			map = sysRecordService.searchRecords(filter);
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("msg","获取数据失败！");
		}
		return map;
	}
	@RequestMapping("detail2")
	@ResponseBody
	public HashMap<String, Object> detail2(HttpServletRequest request, String id) throws Exception {
		HashMap<String, Object> map = super.detail(request, id);
		return map;
	}
	
	
	
}
