package com.fwxm.contract.web;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fwxm.contract.bean.ContractEvaluate;
import com.fwxm.contract.service.ContractEvaluateService;
import com.khnt.core.crud.web.SpringSupportAction;

@Controller
@RequestMapping("contractEvaluateAction")
public class ContractEvaluateAction extends SpringSupportAction<ContractEvaluate, ContractEvaluateService> {

	@Autowired
	private ContractEvaluateService contractEvaluateService;

	@Override
	public HashMap<String, Object> delete(String ids) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			contractEvaluateService.delete(ids);
			map.put("success", true);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "删除失败！");
		}
		return map;
	}
	
	
	
}
