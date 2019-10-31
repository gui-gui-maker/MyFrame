package com.scts.cspace.space.web;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.scts.cspace.space.bean.TjyptSpaceExpand;
import com.scts.cspace.space.service.TjyptSpaceExpandService;

@Controller
@RequestMapping("tjyptSpaceExpandAction")
public class TjyptSpaceExpandAction extends SpringSupportAction<TjyptSpaceExpand, TjyptSpaceExpandService> {

	@Autowired
	private TjyptSpaceExpandService spaceExpandService;
	
	/**
	 * 扩容申请
	 *@author PingZhou
	 *@param rj 申请容积
	 *@param desc 申请说明
	 *@return
	 */
	@ResponseBody
	@RequestMapping("applyExpand")
	public HashMap<String, Object> applyExpand(String rj,String desc){
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		try {
			spaceExpandService.applyExpand(rj,desc);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		
		return map;
	}
	
	/**
	 * 审核扩容申请
	 *@author PingZhou
	 *@param audit_conclusion 审核结论
	 *@param audit_date
	 *@param audit_expand
	 *@param audit_desc
	 *@return
	 */
	@ResponseBody
	@RequestMapping("auditExpand")
	public HashMap<String, Object> auditExpand(String id,String audit_conclusion,String audit_date,String audit_expand,String audit_desc){
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		try {
			spaceExpandService.auditExpand(id,audit_conclusion,audit_date,audit_expand,audit_desc);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		
		return map;
	}
}
