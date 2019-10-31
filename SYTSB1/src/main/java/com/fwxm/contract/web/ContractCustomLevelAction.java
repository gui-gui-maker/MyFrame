package com.fwxm.contract.web;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fwxm.contract.bean.ContractCustomLevel;
import com.fwxm.contract.service.ContractCustomLevelService;
import com.khnt.core.crud.web.SpringSupportAction;

@Controller
@RequestMapping("contractCustomLevelAction")
public class ContractCustomLevelAction extends SpringSupportAction<ContractCustomLevel, ContractCustomLevelService> {

	@Autowired
	private ContractCustomLevelService customLevelService;
	
	@Override
	public HashMap<String, Object> delete(String ids) throws Exception {
		 HashMap<String, Object>  map = new HashMap<String, Object>();
		try {
			customLevelService.del(ids);
			map.put("success", true);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "删除失败！");
		}
		
		return map;
	}
	
}
