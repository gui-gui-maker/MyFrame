package com.lsts.finance.web;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.finance.bean.CwBankDetail;
import com.lsts.finance.bean.CwBankFefund;
import com.lsts.finance.service.CwBankDetailManager;
import com.lsts.finance.service.CwBankFefundManager;
import com.lsts.log.service.SysLogService;


@Controller
@RequestMapping("bank/fefund")
public class CwBankFefundAction extends SpringSupportAction<CwBankFefund, CwBankFefundManager> {

    @Autowired
    private CwBankFefundManager  cwBankFefundManager;
	@Autowired
	private CwBankDetailManager cwBankDetailManager;
	@Autowired
	private SysLogService logService;
	
    @RequestMapping(value = "saveBank")
    @ResponseBody
    public HashMap<String, Object> saveBank(HttpServletRequest request,@RequestBody CwBankFefund cwBankFefund){
    	CurrentSessionUser user = SecurityUtil.getSecurityUser();
    	String id = request.getParameter("FkId");
    	cwBankFefundManager.saveBank(request, cwBankFefund, user,id);
    	return JsonWrapper.successWrapper(cwBankFefund);
    	
    }
    /**
     * 保存备注
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "saveRemark")
	@ResponseBody
	public HashMap<String, Object> saveRemark(HttpServletRequest request) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
    	String id = request.getParameter("FkId");
    	String remark = URLDecoder.decode(request.getParameter("remark"),"utf-8");
    	CwBankDetail cwBankDetail = cwBankDetailManager.get(id);
    	String temp = cwBankDetail.getRemrk();
		cwBankDetail.setRemrk(remark);
		try {
			cwBankDetailManager.save(cwBankDetail);
			//记录备注修改日志
			logService.setLogs(cwBankDetail.getId(), "修改银行转账数据备注", "修改前备注内容："+temp+",修改后备注内容："+cwBankDetail.getRemrk(), user.getId(), user.getName(),
					new Date(), request.getRemoteAddr());
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败，请重试！");
		}
		return JsonWrapper.successWrapper(cwBankDetail);
	}
    /**
     * 撤销退款申请
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "revokeApply")
	@ResponseBody
	public HashMap<String, Object> revokeApply(HttpServletRequest request) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
    	String id = request.getParameter("id");
    	if(StringUtil.isNotEmpty(id)) {
    		CwBankFefund cwBankFefund = cwBankFefundManager.get(id);
    		cwBankFefund.setDataStatus("98");//已撤销
    		try {
    			cwBankFefundManager.save(cwBankFefund);
    			//记录撤销银行转账退款申请日志
    			logService.setLogs(cwBankFefund.getId(), "撤销银行转账退款申请", "撤销银行转账退款申请", user.getId(), user.getName(),
    					new Date(), request.getRemoteAddr());
    			hashMap.put("success", true);
        		hashMap.put("msg", "操作成功！");
    		} catch (Exception e) {
    			e.printStackTrace();
    			hashMap.put("success", false);
        		hashMap.put("msg", "操作失败！");
    		}
    	}else {
    		hashMap.put("success", false);
    		hashMap.put("msg", "获取业务信息失败！");
    	}
    	return hashMap;
	}
    /**
     * 撤销退款审核
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "revokeChecked")
	@ResponseBody
	public HashMap<String, Object> revokeChecked(HttpServletRequest request) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
    	String id = request.getParameter("id");
    	if(StringUtil.isNotEmpty(id)) {
    		CwBankFefund cwBankFefund = cwBankFefundManager.get(id);
    		cwBankFefund.setCheckOpId(null);
    		cwBankFefund.setCheckOpName(null);
    		cwBankFefund.setCheckOpTime(null);
    		cwBankFefund.setDataStatus("0");//待审核
    		try {
    			cwBankFefundManager.save(cwBankFefund);
    			//记录撤销银行转账退款审核日志
    			logService.setLogs(cwBankFefund.getId(), "撤销银行转账退款审核", "撤销银行转账退款审核", user.getId(), user.getName(),
    					new Date(), request.getRemoteAddr());
    			hashMap.put("success", true);
        		hashMap.put("msg", "操作成功！");
    		} catch (Exception e) {
    			e.printStackTrace();
    			hashMap.put("success", false);
        		hashMap.put("msg", "操作失败！");
    		}
    	}else {
    		hashMap.put("success", false);
    		hashMap.put("msg", "获取业务信息失败！");
    	}
    	return hashMap;
	}
    /**
     * 审核退款申请
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "checkBankFefund")
	@ResponseBody
	public HashMap<String, Object> checkBankFefund(HttpServletRequest request) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
    	String id = request.getParameter("id");
    	String result = request.getParameter("result");
    	if(StringUtil.isNotEmpty(id)) {
    		CwBankFefund cwBankFefund = cwBankFefundManager.get(id);
    		cwBankFefund.setCheckOpId(user.getId());
    		cwBankFefund.setCheckOpName(user.getName());
    		cwBankFefund.setCheckOpTime(new Date());
    		if("1".equals(result)) {
    			cwBankFefund.setDataStatus("1");//审核通过，待确认
    		}else if("0".equals(result)){
    			cwBankFefund.setDataStatus("3");//审核不通过
    		}
    		try {
    			cwBankFefundManager.save(cwBankFefund);
    			//记录审核银行转账退款申请日志
    			logService.setLogs(cwBankFefund.getId(), "审核银行转账退款申请", "审核银行转账退款申请", user.getId(), user.getName(),
    					new Date(), request.getRemoteAddr());
    			hashMap.put("success", true);
        		hashMap.put("msg", "操作成功！");
    		} catch (Exception e) {
    			e.printStackTrace();
    			hashMap.put("success", false);
        		hashMap.put("msg", "操作失败！");
    		}
    	}else {
    		hashMap.put("success", false);
    		hashMap.put("msg", "获取业务信息失败！");
    	}
    	return hashMap;
	}
    /**
     * 退款确认
     * @param request
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unlikely-arg-type")
	@RequestMapping(value = "confirm")
	@ResponseBody
	public HashMap<String, Object> confirm(HttpServletRequest request) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
    	String id = request.getParameter("id");
    	if(StringUtil.isNotEmpty(id)) {
    		CwBankFefund cwBankFefund = cwBankFefundManager.get(id);
    		cwBankFefund.setConfirmOpId(user.getId());
    		cwBankFefund.setCheckOpName(user.getName());
    		cwBankFefund.setCheckOpTime(new Date());
    		cwBankFefund.setDataStatus("2");//已确认
    		try {
    			cwBankFefundManager.save(cwBankFefund);
    			if(cwBankFefund.getRearAmount()!=null||!"".equals(cwBankFefund.getRearAmount())){
    				   CwBankDetail cwBankDetail = cwBankDetailManager.get(cwBankFefund.getFkBankDetailId());
    				   cwBankDetail.setRestMoney(cwBankFefund.getRearAmount());
    				   cwBankDetailManager.save(cwBankDetail);
    			   }
    			//记录银行转账退款确认日志
    			logService.setLogs(cwBankFefund.getId(), "银行转账退款确认", "银行转账退款确认", user.getId(), user.getName(),
    					new Date(), request.getRemoteAddr());
    			hashMap.put("success", true);
        		hashMap.put("msg", "操作成功！");
    		} catch (Exception e) {
    			e.printStackTrace();
    			hashMap.put("success", false);
        		hashMap.put("msg", "操作失败！");
    		}
    	}else {
    		hashMap.put("success", false);
    		hashMap.put("msg", "获取业务信息失败！");
    	}
    	return hashMap;
	}
    /**
     * 查询未确认、未撤销、未删除的退款申请
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "queryBankFefund")
	@ResponseBody
	public HashMap<String, Object> queryBankFefund(HttpServletRequest request) throws Exception {
    	HashMap<String,Object> hashMap = new HashMap<String,Object>();
    	String FkId = request.getParameter("FkId");
    	List<CwBankFefund> list = new ArrayList<CwBankFefund>();
    	if(StringUtil.isNotEmpty(FkId)) {
    		try {
    			list = cwBankFefundManager.queryBankFefund(request, FkId);
    			hashMap.put("success", true);
    			hashMap.put("list", list);
    		} catch (Exception e) {
    			e.printStackTrace();
    			hashMap.put("success", false);
    			hashMap.put("msg", "获取未确认退款申请信息失败！");
    		}
    	}else {
    		hashMap.put("success", false);
			hashMap.put("msg", "无法获取未确认退款申请信息！");
    	}
    	return hashMap;
	}
    /**
     * 查询流程步骤
     * @param request
     * @return
     * @throws Exception
     */
 	@RequestMapping(value = "getFlowStep")
 	@ResponseBody
 	public ModelAndView getFlowStep(HttpServletRequest request)
 			throws Exception {

 		Map<String, Object> map = new HashMap<String, Object>();

 		map = cwBankFefundManager.getFlowStep(request.getParameter("id"));

 		ModelAndView mav = new ModelAndView("app/finance/flow_card", map);

 		return mav;

 	}
}