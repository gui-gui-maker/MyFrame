package com.lsts.finance.web;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.lsts.finance.bean.CwInvoiceLead;
import com.lsts.finance.dao.CwInvoiceFDao;
import com.lsts.finance.service.CwInvoiceFManager;
import com.lsts.finance.service.CwInvoiceLeadManager;
import com.lsts.log.bean.SysLog;


/**
 * 发票管理
 * @author lenovo
 *
 */
@Controller
@RequestMapping("cwInvoiceLead/lead")
public class CwInvoiceLeadAction extends SpringSupportAction<CwInvoiceLead, CwInvoiceLeadManager> {
	
	@Autowired
	CwInvoiceLeadManager cwInvoiceLeadManager;
	@Autowired
	CwInvoiceFManager cwInvoiceFManager;
	@Autowired
	CwInvoiceFDao cwInvoiceFDao;
	
	/**
	 * 发票作废
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "cancel")
	@ResponseBody
	public Map<String, Object> Cancel(HttpServletRequest request) throws Exception{
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		String ids = request.getParameter("ids");
		cwInvoiceFManager.Cancel(ids,request,user);
		return JsonWrapper.successWrapper(ids);
	}
			/*HashMap<String, Object> map = new HashMap<String, Object>();
			String[] ids = id.split(",");
			if (ids !=null || !ids.equals("")){
				map.put("success", false);
				map.put("msg", "所选业务ID为空！");
			} else {
				CwInvoiceF cwInvoiceF = cwInvoiceFManager.get(id);
				if(cwInvoiceF==null){
					map.put("success", false);
					map.put("msg", "数据获取失败！");
				} else {
					cwInvoiceF.setStatus(CwInvoiceFManager.CW_FPGL_ZF);//已作废
					cwInvoiceFManager.save(cwInvoiceF);
					map.put("success", true);
				}
			}
			return map;
		}*/

	
	   /**
	    * 保存发票信息
	    */
	@RequestMapping(value = "saveLy")
	@ResponseBody
    public HashMap<String, Object> saveLy(HttpServletRequest request,@RequestBody CwInvoiceLead cwInvoiceLead) throws Exception{
		//HashMap<String, Object> map = new HashMap<String, Object>();
		try{
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			String[] ids=request.getParameter("ids").toString().split(",");
			cwInvoiceLeadManager.saveLy(cwInvoiceLead,ids,user,request);
		} catch (Exception e){
			e.printStackTrace();
		}
		//map.put("success", true);
		return JsonWrapper.successWrapper();
	}
	
	@RequestMapping(value = "refund")
	@ResponseBody
	public HashMap<String, Object> refund(CwInvoiceLead cwInvoiceLead){
		//HashMap<String, Object> map = new HashMap<String, Object>();
		
		cwInvoiceLeadManager.refund(cwInvoiceLead);
		
		//map.put("success", true);
		return JsonWrapper.successWrapper();
		
	}
	@RequestMapping(value = "queryRecord")
	@ResponseBody
	public HashMap<String, Object> queryRecord(String cId){
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		List<SysLog> list = cwInvoiceLeadManager.queryRecord(cId);
		wrapper.put("success", true);
		wrapper.put("list", list);
		return wrapper;
	}
}
