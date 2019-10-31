package com.lsts.inspection.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.lsts.inspection.bean.ReportBHGRecord;
import com.lsts.inspection.service.ReportBHGRecordService;

/** 
 * @author 作者 PingZhou
 * @JDK 1.6
 * @version 创建时间：2016年1月19日 下午2:08:22 
 * 类说明 
 */
@Controller
@RequestMapping("reportBHGRecordAction")
public class ReportBHGRecordAction extends SpringSupportAction<ReportBHGRecord, ReportBHGRecordService> {
	@Autowired
	private ReportBHGRecordService reportBHGRecordService;
	
	
	/**
	 * 保存修改问题来源
	 * @param request
	 * @param bhg_name
	 * @param bhg_value
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addErrorResource")
	@ResponseBody
	public HashMap<String, Object> addErrorResource( HttpServletRequest request,String bhg_name,String bhg_value)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		try {
			String id = request.getParameter("id");
			reportBHGRecordService.addErrorResource(id,bhg_name,bhg_value);
			
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
			
					 
		return map;
		
		}
	
	/**
	 * 根据业务信息id查询问题来源
	 * @param request
	 * @param bhg_name
	 * @param bhg_value
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "queryResourceByInfoid")
	@ResponseBody
	public HashMap<String, Object> queryResourceByInfoid( HttpServletRequest request,String id)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		try {

			List<ReportBHGRecord> list = reportBHGRecordService.queryResourceByInfoid(id);
			map.put("gridList", list);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
			
					 
		return map;
		
		}
	
	/**
	 * 删除问题来源
	 * @param request
	 * @param bhg_name
	 * @param bhg_value
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "del")
	@ResponseBody
	public HashMap<String, Object> del( HttpServletRequest request,String ids)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		try {

			reportBHGRecordService.del(ids);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
			
					 
		return map;
		
		}
	
	
}
