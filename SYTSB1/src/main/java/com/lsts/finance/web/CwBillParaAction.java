package com.lsts.finance.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.lsts.finance.bean.CwBillPara;
import com.lsts.finance.service.CwBillParaManager;

@Controller
@RequestMapping("cwBillParaAction")
public class CwBillParaAction extends SpringSupportAction<CwBillPara, CwBillParaManager>{
	
	@Autowired
	private CwBillParaManager cwBillParaManager;
	
	
	/**
	 * 删除申请附表信息信息
	 */
	@RequestMapping(value = "del")
	@ResponseBody
	public HashMap<String, Object> del(String id,
			HttpServletRequest request) throws Exception {
		try {
			cwBillParaManager.del(id);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败！");
		}
	}
	/**
	 * 删除申请附表号段信息
	 */
	@RequestMapping(value = "delReturn")
	@ResponseBody
	public HashMap<String, Object> delReturn(String id,
			HttpServletRequest request) throws Exception {
		try {
			cwBillParaManager.delReturn(id);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败！");
		}
	}

}
