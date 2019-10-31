package com.lsts.finance.web;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.lsts.finance.bean.CwBankAccount;
import com.lsts.finance.service.CwBankAccountService;

@Controller
@RequestMapping("cwBankAccountAction")
public class CwBankAccountAction extends SpringSupportAction<CwBankAccount,CwBankAccountService>{

	@Autowired
	private CwBankAccountService cwBankAccountService;
	
	@RequestMapping(value="deletes")
	@ResponseBody
	public HashMap<String, Object> deletes(String ids) throws Exception{
		HashMap<String, Object> map = new HashMap<String,Object>();
		try {
			cwBankAccountService.deletes(ids);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
			
		}
		return map;
	}
	
}
