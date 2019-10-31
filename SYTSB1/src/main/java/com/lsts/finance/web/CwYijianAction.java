package com.lsts.finance.web;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.finance.bean.Clfbxd;
import com.lsts.finance.bean.CwBorrowMoney;
import com.lsts.finance.bean.CwDrawmoney;
import com.lsts.finance.bean.CwYijian;
import com.lsts.finance.bean.Fybxd;
import com.lsts.finance.bean.Pxfbxd;
import com.lsts.finance.service.ClfbxdManager;
import com.lsts.finance.service.CwBorrowMoneyManager;
import com.lsts.finance.service.CwDrawmoneyManager;
import com.lsts.finance.service.CwYijianManager;
import com.lsts.finance.service.FybxdService;
import com.lsts.finance.service.PxfbxdManager;


@Controller
@RequestMapping("cw/yijian")
public class CwYijianAction extends SpringSupportAction<CwYijian, CwYijianManager> {

    @Autowired
    private CwYijianManager  cwYijianManager;
    @Autowired
    private CwBorrowMoneyManager  cwBorrowMoneyManager;
    @Autowired
    private CwDrawmoneyManager cwDrawmoneyManager;
    @Autowired
	private FybxdService fybxdService;
    @Autowired
	private ClfbxdManager clfbxdManager;
    @Autowired
	private PxfbxdManager pxfbxdManager;
	
    /*@Autowired
	private Tjy2FcpManager tjy2FcpManager;*/
   
    
    /**
   	 * 添加
   	 * @param response
        * @throws Exception 
   	 */
    @RequestMapping(value = "save2")
   	@ResponseBody
   	public HashMap<String, Object> save2(String id) throws Exception {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	CurrentSessionUser user = SecurityUtil.getSecurityUser();
   		Clfbxd clfbxd=clfbxdManager.get(id);
   		
   		System.out.println(clfbxd.getClCcid()+"______"+clfbxd.getClCcr());
   
   		String[] user_name = clfbxd.getClCcr().split(","); 
   		String[] user_id   = clfbxd.getClCcid().split(",");  
   		System.out.println(user_name.length);
   			int size = user_name.length;
   	for(int i=0;i<user_name.length;i++){
   		System.out.println(user_name[i]);
   		System.out.println(user_id[i]);
   		String ids = cwYijianManager.getmid(user_id[i]);
   		if(ids==null){
   			continue;
   		}
   		BigDecimal size1 = new BigDecimal(size);
   		/*tjy2FcpManager.saveclfbxd(clfbxd,ids,user_name[i],size1);*/
   	}
   		
   		CwYijian cwYijian=new CwYijian();
   		//获取当前登录人姓名
   		cwYijian.setAuditMan(user.getName());
   		cwYijian.setAuditManId(user.getId());
   		cwYijian.setAuditTime(new Date());
   		cwYijian.setAuditOpinion("通过");
   		cwYijian.setAuditResult("审批通过");
   		cwYijian.setFileId(id);
   		/*if(cwYijian.getAuditResult().equals("审批通过")){*/
   			clfbxd.setStatue(CwBorrowMoneyManager.CW_CSTG);//已初审
   			clfbxd.setHandleTime(new Date());
   			clfbxd.setHandleId(user.getId());
   			clfbxd.setHandleName(user.getName());
   			clfbxdManager.save(clfbxd);
   		/*}else{
   			clfbxd.setStatue(CwBorrowMoneyManager.CW_JK_NO_PASS);
   			clfbxdManager.save(clfbxd);
   		}*/
   		//改变状态
   			cwYijianManager.save(cwYijian);
   			map.put("success", true);
   		return map;

   	}
    
    /**
   	 * 添加
   	 * @param response
        * @throws Exception 
   	 */
    @RequestMapping(value = "save4")
   	@ResponseBody
   	public HashMap<String, Object> save4(String id) throws Exception {
    	HashMap<String, Object> map = new HashMap<String, Object>();
   		CurrentSessionUser user = SecurityUtil.getSecurityUser();
   		Pxfbxd Pxfbxd=pxfbxdManager.get(id);
   		System.out.println(Pxfbxd.getClCcid()+"______"+Pxfbxd.getClCcr());
   	   
   		String[] user_name = Pxfbxd.getClCcr().split(","); 
   		String[] user_id   = Pxfbxd.getClCcid().split(",");  
   		System.out.println(user_name.length);
   		int size = user_name.length;
   	for(int i=0;i<user_name.length;i++){
   		System.out.println(user_name[i]);
   		System.out.println(user_id[i]);
   		String ids = cwYijianManager.getmid(user_id[i]);
   		if(ids==null){
   			continue;
   		}
   		BigDecimal size1 = new BigDecimal(size);
   		/*tjy2FcpManager.savepxfbxd(Pxfbxd,ids,user_name[i],size1);*/
   	}
   		
   		
   		CwYijian cwYijian = new CwYijian();
   		//获取当前登录人姓名
   		cwYijian.setAuditMan(user.getName());
   		cwYijian.setAuditManId(user.getId());
   		cwYijian.setAuditTime(new Date());
   		cwYijian.setFileId(id);
   		cwYijian.setAuditOpinion("通过");
   		cwYijian.setAuditResult("审批通过");
   		/*if(cwYijian.getAuditResult().equals("审批通过")){*/
   		Pxfbxd.setStatue(CwBorrowMoneyManager.CW_CSTG);//已初审
   		Pxfbxd.setHandleTime(new Date());
   		Pxfbxd.setHandleId(user.getId());
   		Pxfbxd.setHandleName(user.getName());
   			pxfbxdManager.save(Pxfbxd);
   		/*}else{
   			clfbxd.setStatue(CwBorrowMoneyManager.CW_JK_NO_PASS);
   			pxfbxdManager.save(clfbxd);
   		}*/
   		//改变状态
   		cwYijianManager.save(cwYijian);
			map.put("success", true);
		return map;
   	}
    
    @RequestMapping(value = "save1")
   	@ResponseBody
   	public HashMap<String, Object> save1(String id) throws Exception {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	CurrentSessionUser user = SecurityUtil.getSecurityUser();
    	CwYijian cwYijian = new CwYijian();
    	Fybxd fybxd=fybxdService.get(id);
    	System.out.println(fybxd.getUserId());
    	String ids = cwYijianManager.getmid(fybxd.getUserId());
    	if(ids!=null){
    	/*tjy2FcpManager.savefybxd(fybxd,ids);*/
    	}
   		//获取当前登录人姓名
   		cwYijian.setAuditMan(user.getName());
   		cwYijian.setAuditManId(user.getId());
   		cwYijian.setAuditTime(new Date());
   		cwYijian.setFileId(id);
   		cwYijian.setAuditOpinion("通过");
   		cwYijian.setAuditResult("审批通过");
   	/*	if(cwYijian.getAuditResult().equals("审批通过")){*/
   			fybxd.setStatus(CwBorrowMoneyManager.CW_CSTG);//已初审
   			fybxd.setHandleTime(new Date());
   			fybxd.setHandleId(user.getId());
   			fybxd.setHandleName(user.getName());
   			fybxdService.save(fybxd);
   	/*	}else{
   			fybxd.setStatus(CwBorrowMoneyManager.CW_JK_NO_PASS);
   			fybxdService.save(fybxd);
   	}	*/
   		//改变状态
   		cwYijianManager.save(cwYijian);
		map.put("success", true);
	return map;

   	}
    /**
     * 领款单审核
     * @param request
     * @param cwYijian
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "save3")
   	@ResponseBody
	public HashMap<String, Object> save3(String fileId,String auditOpinion,String a) throws Exception {
   		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
   		CwDrawmoney cwDrawmoney=cwDrawmoneyManager.get(fileId);
   		CwYijian cwYijian=new CwYijian();
   		//获取当前登录人姓名
   		cwYijian.setAuditMan(user.getName());
   		cwYijian.setAuditManId(user.getId());
   		cwYijian.setAuditTime(new Date());
   		cwYijian.setAuditOpinion(auditOpinion);
   		cwYijian.setFileId(fileId);
   		if(a.equals("1")){
   			cwYijian.setAuditResult("审批通过");
   			cwDrawmoney.setStatus(CwDrawmoneyManager.CW_CSTG);//已初审
   			cwDrawmoneyManager.save(cwDrawmoney);
   			map.put("success", true);
   		}else{
   			cwYijian.setAuditResult("审批未通过");
   			cwDrawmoney.setStatus(CwDrawmoneyManager.CW_LK_NO_PASS);
   			cwDrawmoneyManager.save(cwDrawmoney);
   			map.put("success", true);
   		}
   		//改变状态
   		cwYijianManager.save(cwYijian);
   		return map;
   	}
    
    /**
     * 借款
     * */
    @RequestMapping(value = "save11")
   	@ResponseBody
   	public HashMap<String, Object> save11(String fileId,String auditOpinion,String a) throws Exception {
   		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
   		CwBorrowMoney cwBorrowMoney=cwBorrowMoneyManager.get(fileId);
   		CwYijian cwYijian=new CwYijian();
   		//获取当前登录人姓名
   		cwYijian.setAuditMan(user.getName());
   		cwYijian.setAuditManId(user.getId());
   		cwYijian.setAuditTime(new Date());
   		cwYijian.setAuditOpinion(auditOpinion);
   		cwYijian.setFileId(fileId);
   		if(a.equals("1")){
   			cwYijian.setAuditResult("审批通过");
   			cwBorrowMoney.setStatus(CwBorrowMoneyManager.CW_CSTG);//已初审
   			cwBorrowMoney.setRepayment_status(CwBorrowMoneyManager.CW_JK_REGISTER);//未还款
   			cwBorrowMoneyManager.save(cwBorrowMoney);
   			map.put("success", true);
   		}else{
   			cwYijian.setAuditResult("审批未通过");
   			cwBorrowMoney.setStatus(CwBorrowMoneyManager.CW_JK_NO_PASS);
   			cwBorrowMoneyManager.save(cwBorrowMoney);
   			map.put("success", true);
   		}
   		//改变状态
   		cwYijianManager.save(cwYijian);
   		return map;
   	}
    /**
     * 还款
     * */
    @RequestMapping(value = "saveh")
   	@ResponseBody
   	public HashMap<String, Object> saveh(String fileId,String auditOpinion,String a) throws Exception {
   		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
   		CwBorrowMoney cwBorrowMoney=cwBorrowMoneyManager.get(fileId);
   		CwYijian cwYijian=new CwYijian();
   		//获取当前登录人姓名
   		cwYijian.setAuditMan(user.getName());
   		cwYijian.setAuditManId(user.getId());
   		cwYijian.setAuditTime(new Date());
   		cwYijian.setAuditOpinion(auditOpinion);
   		cwYijian.setFileId(fileId);
   		if(a.equals("1")){
   			cwYijian.setAuditResult("审批通过");
   			cwBorrowMoney.setRepayment_status(CwBorrowMoneyManager.CW_HK_NO);//已初审
   			cwBorrowMoneyManager.save(cwBorrowMoney);
   			map.put("success", true);
   		}else{
   			cwYijian.setAuditResult("审批未通过");
   			cwBorrowMoney.setRepayment_status(CwBorrowMoneyManager.CW_JK_NO_PASS);
   			cwBorrowMoneyManager.save(cwBorrowMoney);
   			map.put("success", true);
   		}
   		//改变状态
   		cwYijianManager.save(cwYijian);
   		return map;
   	}
	
}