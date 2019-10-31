package com.lsts.approve.web;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.lsts.approve.bean.CertificateRule;
import com.lsts.approve.service.CertificateRuleService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("certificateRuleAction")
public class CertificateRuleAction extends SpringSupportAction<CertificateRule, CertificateRuleService>{
	@Autowired
	private CertificateRuleService certificateRuleService;
	
	
	@RequestMapping("details")
	@ResponseBody
	public HashMap<String,Object> details(String device,String dept,String report) throws Exception {
		return certificateRuleService.detail(device,dept,report);
	}

	@RequestMapping("saves")
	@ResponseBody
	public HashMap<String,Object> saves(HttpServletRequest request,@RequestBody CertificateRule certificateRule) throws Exception {
		CertificateRule rule = null;
		try{
			rule = certificateRuleService.saves(request,certificateRule);
		}catch(Exception e){
			e.printStackTrace();
			return JsonWrapper.failureWrapper("保存失败，请重试");
		}
		return JsonWrapper.successWrapper(rule);
	}
	@RequestMapping("getUsers")
	@ResponseBody
	public HashMap<String,Object> getUsers() throws Exception {
		
		return certificateRuleService.getUsers();
	}
	/**
	 * 移动端获取用户数据列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getUsersData")
	@ResponseBody
	public Map<String, Object> getUsersData() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {		
			map.put("usersData", certificateRuleService.getUsersData());
			map.put("success", true);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "获取列表信息失败！");
		}
		return map;
	}
	/**
	 * 移动端获取设备类别树
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getTreeData")
	@ResponseBody
	public Map<String, Object> getTreeData() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {		
			map.put("treeData", certificateRuleService.getTreeData());
			map.put("success", true);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "获取设备类别失败！");
		}
		return map;
	}
	/**
	 * 移动端保存签字规则数据
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("savesByFlat")
	@ResponseBody
	public HashMap<String,Object> savesByFlat(HttpServletRequest request) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String version = request.getParameter("ismobilenew");
			String dataStr = request.getParameter("data");
			if("1".equals(version)){
				if(dataStr==null){
					map.put("success", false);
					map.put("msg", "参数为空！");
				}else{
					
					JSONObject object = JSONObject.fromString(dataStr);
					map = certificateRuleService.savesByFlat(object,map,request);
				}
			}else{
				map.put("success", false);
				map.put("msg", "请升级APP至最新版本!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "网络异常，数据保存失败！");
		}
		return map;
	}

}
