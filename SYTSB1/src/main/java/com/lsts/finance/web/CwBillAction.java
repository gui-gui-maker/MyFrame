package com.lsts.finance.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.bean.BaseEntity;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.lsts.finance.bean.CwBill;
import com.lsts.finance.service.CwBillManager;

@Controller
@RequestMapping("cwBillAction")
public class CwBillAction extends SpringSupportAction<CwBill, CwBillManager>{
	
	@Autowired
	private CwBillManager cwBillManager;
	
	/**
	 * 保存申请信息
	 */
	@RequestMapping(value = "saveBasic")
	@ResponseBody
	public HashMap<String, Object> saveBasic(@RequestBody CwBill cwBill,
			HttpServletRequest request) throws Exception {
		try {
			cwBillManager.saveBasic(cwBill);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败！");
		}
	}
	/**
	 * 删除申请信息
	 */
	@RequestMapping(value = "del")
	@ResponseBody
	public HashMap<String, Object> del(String ids,
			HttpServletRequest request) throws Exception {
		try {
			cwBillManager.del(ids);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败！");
		}
	}
	/**
	 * 提交申请申请信息
	 */
	@RequestMapping(value = "subAudit")
	@ResponseBody
	public HashMap<String, Object> subAudit(String ids,String op_id,String op,
			HttpServletRequest request) throws Exception {
		try {
			cwBillManager.subAudit(ids,op_id,op);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败！");
		}
	}
	/**
	 * 获取退回申请信息
	 */
	@RequestMapping(value = "getReturnDetail")
	@ResponseBody
	public HashMap<String, Object> getReturnDetail(String id,String step,
			HttpServletRequest request) throws Exception {
		try {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map = cwBillManager.getReturnDetail(id,step);
			map.put("success", true);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败！");
		}
	}
	/**
	 * 保存退回申请信息
	 */
	@RequestMapping(value = "saveReturnDetail")
	@ResponseBody
	public HashMap<String, Object> saveReturnDetail(@RequestBody CwBill cwBill,
			HttpServletRequest request) throws Exception {
		try {
			cwBillManager.saveReturnDetail(cwBill);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败！");
		}
	}
	/**
	 * 提交退回申请信息
	 */
	@RequestMapping(value = "subRerurnApply")
	@ResponseBody
	public HashMap<String, Object> subRerurnApply(String ids,
			HttpServletRequest request) throws Exception {
		try {
			cwBillManager.subRerurnApply(ids);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败！");
		}
	}
	/**
	 * 保存接受信息
	 */
	@RequestMapping(value = "saveReceivedDetail")
	@ResponseBody
	public HashMap<String, Object> saveReceivedDetail(@RequestBody CwBill cwBill,
			HttpServletRequest request) throws Exception {
		try {
			cwBillManager.saveReceivedDetail(cwBill);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败！");
		}
	}
}
