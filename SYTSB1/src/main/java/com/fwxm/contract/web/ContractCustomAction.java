package com.fwxm.contract.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fwxm.contract.bean.ContractCustom;
import com.fwxm.contract.service.ContractCustomService;
import com.khnt.core.crud.web.SpringSupportAction;

@Controller
@RequestMapping("contractCustomAction")
public class ContractCustomAction extends SpringSupportAction<ContractCustom, ContractCustomService> {

	@Autowired
	private ContractCustomService customService;

	@ResponseBody
	@RequestMapping("saveBasic")
	public HashMap<String, Object> saveBasic(HttpServletRequest request, ContractCustom entity) throws Exception {
		// TODO Auto-generated method stub
		return super.save(request, entity);
	}

	@Override
	public HashMap<String, Object> delete(String ids) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			
			customService.del(ids);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		
		return map;
	}
	
	
	
}
