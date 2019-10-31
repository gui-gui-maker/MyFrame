package com.lsts.finance.web;




import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.finance.bean.CwInvoiceF;
import com.lsts.finance.bean.CwInvoiceLead;
import com.lsts.finance.service.CwInvoiceFManager;


@Controller
@RequestMapping("cwInvoicef/reg")
public class CwInvoiceFAction extends SpringSupportAction<CwInvoiceF, CwInvoiceFManager> {

	@Autowired
	private CwInvoiceFManager cwInvoiceFManager;
   /**
    * 保存发票信息
    */
	@RequestMapping(value = "saveF")
	@ResponseBody
	public HashMap<String, Object> saveF(HttpServletRequest request,@RequestBody CwInvoiceF cwInvoiceF) throws Exception{
		//HashMap<String, Object> map = new HashMap<String, Object>();
		try{
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			cwInvoiceFManager.saveF(request,cwInvoiceF,user);
		} catch (Exception e){
			e.printStackTrace();
		}
		return JsonWrapper.successWrapper();
	}

	
	
	
}