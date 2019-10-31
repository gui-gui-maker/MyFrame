package com.lsts.finance.web;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.bpm.core.service.ActivityManager;
import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.finance.bean.CwBorrowMoney;
import com.lsts.finance.bean.CwRefundment;
import com.lsts.finance.bean.CwYijian;
import com.lsts.finance.service.CwBorrowMoneyManager;
import com.lsts.finance.service.CwRefundmentManager;
import com.lsts.finance.service.CwYijianManager;
import com.lsts.inspection.service.InspectionService;


@Controller
@RequestMapping("money/borrow")
public class CwBorrowMoneyAction extends SpringSupportAction<CwBorrowMoney, CwBorrowMoneyManager> {

    @Autowired
    private CwBorrowMoneyManager  cwBorrowMoneyManager;

	@Autowired
	private FlowExtManager flowExtManager;
	
	@Autowired
	private ActivityManager activityManager;
	
	@Autowired
	private InspectionService inspectionService;
	@Autowired
    private CwYijianManager  cwYijianManager;
	/**
	 * 财务审核通过借款
	 * */
	@RequestMapping(value = "cwshtg")
	@ResponseBody
	public HashMap<String, Object> cwshtg(String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
   		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		if (id.isEmpty()){
			map.put("success", false);
			map.put("msg", "所选业务ID为空！");
		} else {
			CwBorrowMoney cwBorrowMoney = cwBorrowMoneyManager.get(id);
			if(cwBorrowMoney==null){
				map.put("success", false);
				map.put("msg", "数据获取失败！");
			} else {
				cwBorrowMoney.setStatus(CwBorrowMoneyManager.CW_CSTG);//已提交
	   			cwBorrowMoney.setRepayment_status(CwBorrowMoneyManager.CW_JK_REGISTER);//未还款
				cwBorrowMoney.setHandleId(user.getId());//审批人id
				cwBorrowMoney.setHandleName(user.getName());
				cwBorrowMoney.setHandleTime(new Date());
				cwBorrowMoney.setHandleOpinion("借款审批通过");
				cwBorrowMoneyManager.save(cwBorrowMoney);
				//意见
		   		CwYijian cwYijian=new CwYijian();
		   		cwYijian.setAuditMan(user.getName());
		   		cwYijian.setAuditManId(user.getId());
		   		cwYijian.setAuditTime(new Date());
		   		cwYijian.setFileId(id);
		   		cwYijian.setAuditOpinion("通过");
	   			cwYijian.setAuditResult("审批通过");
	   	   		cwYijianManager.save(cwYijian);

				map.put("success", true);
			}
		}
		return map;
	}
	/**
	 * 财务审核通过还款
	 * */
	@RequestMapping(value = "cwhkshtg")
	@ResponseBody
	public HashMap<String, Object> cwhkshtg(String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
   		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		if (id.isEmpty()){
			map.put("success", false);
			map.put("msg", "所选业务ID为空！");
		} else {
			CwBorrowMoney cwBorrowMoney = cwBorrowMoneyManager.get(id);
			if(cwBorrowMoney==null){
				map.put("success", false);
				map.put("msg", "数据获取失败！");
			} else {
				cwBorrowMoney.setRepayment_status(CwBorrowMoneyManager.CW_HK_NO);//已提交
				cwBorrowMoney.setHandleIdh(user.getId());//审批人id
				cwBorrowMoney.setHandleNameh(user.getName());
				cwBorrowMoney.setHandleTimeh(new Date());
				cwBorrowMoney.setHandleOpinionh("还款审批通过");
				cwBorrowMoneyManager.save(cwBorrowMoney);
				//意见
		   		CwYijian cwYijian=new CwYijian();
		   		cwYijian.setAuditMan(user.getName());
		   		cwYijian.setAuditManId(user.getId());
		   		cwYijian.setAuditTime(new Date());
		   		cwYijian.setFileId(id);
		   		cwYijian.setAuditOpinion("通过");
	   			cwYijian.setAuditResult("审批通过");
	   	   		cwYijianManager.save(cwYijian);

				map.put("success", true);
			}
		}
		return map;
	}
	/**
	 * 单据作废
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "submitzf1")
	@ResponseBody
	public HashMap<String, Object> submitzf1(String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (id.isEmpty()){
			map.put("success", false);
			map.put("msg", "所选业务ID为空！");
		} else {
			CwBorrowMoney cwBorrowMoney = cwBorrowMoneyManager.get(id);
			if(cwBorrowMoney==null){
				map.put("success", false);
				map.put("msg", "数据获取失败！");
			} else {
				cwBorrowMoney.setStatus(CwBorrowMoneyManager.CW_DJZF);//已作废
				cwBorrowMoney.setRepayment_status(CwBorrowMoneyManager.CW_DJZF);
				cwBorrowMoney.setAbolishTime(new Date());
				if(cwBorrowMoney.getHandleTime()!=null){
					boolean bj=isSameDate(new Date(),cwBorrowMoney.getHandleTime());
					System.out.println(bj);
					if(bj){
						cwBorrowMoney.setAbolish("1");
					}else{
						cwBorrowMoney.setAbolish("2");
					}
				}
				cwBorrowMoneyManager.save(cwBorrowMoney);
				
				map.put("success", true);
			}
		}
		return map;
	}
	private static boolean isSameDate(Date date1, Date date2) {
	       Calendar cal1 = Calendar.getInstance();
	       cal1.setTime(date1);

	       Calendar cal2 = Calendar.getInstance();
	       cal2.setTime(date2);

	       boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
	               .get(Calendar.YEAR);
	       boolean isSameMonth = isSameYear
	               && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
	       boolean isSameDate = isSameMonth
	               && cal1.get(Calendar.DAY_OF_MONTH) == cal2
	                       .get(Calendar.DAY_OF_MONTH);

	       return isSameDate;
	   }
	/**提交单据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "submit")
	@ResponseBody
	public HashMap<String, Object> submit(String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
   		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		if (id.isEmpty()){
			map.put("success", false);
			map.put("msg", "所选业务ID为空！");
		} else {
			CwBorrowMoney cwBorrowMoney = cwBorrowMoneyManager.get(id);
			if(cwBorrowMoney==null){
				map.put("success", false);
				map.put("msg", "数据获取失败！");
			} else {
				cwBorrowMoney.setStatus(CwBorrowMoneyManager.CW_SUBMIT);//已提交
				cwBorrowMoneyManager.save(cwBorrowMoney);
				
				map.put("success", true);
			}
		}
		return map;
	}
	/**提交还款单据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "submit1")
	@ResponseBody
	public HashMap<String, Object> submit1(String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (id.isEmpty()){
			map.put("success", false);
			map.put("msg", "所选业务ID为空！");
		} else {
			CwBorrowMoney cwBorrowMoney = cwBorrowMoneyManager.get(id);
			if(cwBorrowMoney==null){
				map.put("success", false);
				map.put("msg", "数据获取失败！");
			} else {
				cwBorrowMoney.setRepayment_status(CwBorrowMoneyManager.CW_SUBMIT);//已提交
				cwBorrowMoneyManager.save(cwBorrowMoney);
				
				map.put("success", true);
			}
		}
		return map;
	}
	/**还款单据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "huansubmit")
	@ResponseBody
	public HashMap<String, Object> huansubmit(HttpServletRequest request,String repayment_man) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		request.setCharacterEncoding("UTF-8");
		String id=request.getParameter("id").toString();
		String check=request.getParameter("check").toString();
//		String repayment_man_id=request.getParameter("borrowerId").toString();
//		String repayment_man=request.getParameter("repayment_man");
//		String repayment_time=request.getParameter("repayment_time").toString();
		if (id.isEmpty()){
			map.put("success", false);
			map.put("msg", "所选业务ID为空！");
		} else {
			CwBorrowMoney cwBorrowMoney = cwBorrowMoneyManager.get(id);
			if(cwBorrowMoney==null){
				map.put("success", false);
				map.put("msg", "数据获取失败！");
			} else {
				CurrentSessionUser user = SecurityUtil.getSecurityUser();
				//取消确认				
				if(check.equals("0")){
					cwBorrowMoney.setRepayment_man(null);
					cwBorrowMoney.setRepayment_man_id(null);
//					cwBorrowMoney.setRepayment_time(sdf.parse(repayment_time));
					cwBorrowMoney.setRepayment_time(null);
					cwBorrowMoney.setHandleIdh(null);//还款处理人ID
					cwBorrowMoney.setHandleNameh(null);//还款处理人姓名
					cwBorrowMoney.setHandleTimeh(null);//还款处理时间
					//获取当前登录人姓名
					/*cwBorrowMoney.setRepayment_status(CwBorrowMoneyManager.CW_HK_YES);//已还款
	*/				cwBorrowMoney.setStatus(CwBorrowMoneyManager.CW_HK_NO);//未还款				
				}else if(check.equals("1")){
					cwBorrowMoney.setRepayment_man(cwBorrowMoney.getBorrower());
					cwBorrowMoney.setRepayment_man_id(cwBorrowMoney.getBorrowerId());
//					cwBorrowMoney.setRepayment_time(sdf.parse(repayment_time));
					cwBorrowMoney.setRepayment_time(new Date());
					cwBorrowMoney.setHandleIdh(user.getId());//还款处理人ID
					cwBorrowMoney.setHandleNameh(user.getName());//还款处理人姓名
					cwBorrowMoney.setHandleTimeh(new Date());//还款处理时间
					//获取当前登录人姓名
					/*cwBorrowMoney.setRepayment_status(CwBorrowMoneyManager.CW_HK_YES);//已还款
	*/				cwBorrowMoney.setStatus(CwBorrowMoneyManager.CW_HK_YES);//已还款
				}
				cwBorrowMoneyManager.save(cwBorrowMoney);
				
				map.put("success", true);
			}
		}
		return map;
	}
    /**
	 * 添加
	 * @param response
     * @throws Exception 
	 */
    @Override
	public synchronized HashMap<String, Object> save(HttpServletRequest request,@RequestBody CwBorrowMoney cwMoney) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(cwMoney.getId()==null || cwMoney.getId().equals("")){
			cwBorrowMoneyManager.saveTask1(cwMoney,user);
			map.put("success", true);
			map.put("msg", "数据保存成功！");
		}else{
			//CwBorrowMoney cwMoney1=cwBorrowMoneyManager.get(cwMoney.getId());
			if(cwMoney.getStatus().equals(CwBorrowMoneyManager.CW_CSTG)){
				map.put("msg", "此条数据已通过不可修改！");
				map.put("success", false);
	  		}else{
	  			cwBorrowMoneyManager.saveTask1(cwMoney,user);
				map.put("success", true);
				map.put("msg", "数据保存成功！");
			}
		}
		return map;
		

	}

	/**
	 * 详情
	 */
	@RequestMapping(value = "detail1")
	@ResponseBody
	public HashMap<String, Object> detail1(HttpServletRequest request, String id)
			throws Exception {
		return super.detail(request, id);
	}
	
	/**
	 * 删除
	 * */
	@Override
	public HashMap<String, Object> delete(String ids) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		CwBorrowMoney cwMoney=cwBorrowMoneyManager.get(ids);
  		if(cwMoney.getStatus().equals(CwBorrowMoneyManager.CW_CSTG)){
			map.put("msg", "此条数据已通过不可删除！");
			map.put("success", false);
  		}else{
  			cwBorrowMoneyManager.delete(ids);
			map.put("msg", "数据删除成功！");
			map.put("success", true);
		}
		return map;
		//return JsonWrapper.successWrapper();
	}
	
	

	/**提交流程并改变状态
	 * @param id
	 * @param flowId
	 * @param typeCode
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "subFolw")
	@ResponseBody
	public HashMap<String, Object> subFolw(ServletRequest request,String id, String flowId,
			String typeCode, String status,String activityId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		//流程传参
		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "借款填报- "+request.getParameter("borrower"));
		map.put(FlowExtWorktaskParam.FLOW_DEFINITION_ID, flowId);
		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
		
		
		if (StringUtil.isEmpty(id)) {
			return JsonWrapper.failureWrapper("参数错误！");
		} else {
			// 启动流程
			if (StringUtil.isNotEmpty(flowId)) {
				cwBorrowMoneyManager.doStartPress(map);
				//改变状态
				CwBorrowMoney cwMoney=cwBorrowMoneyManager.get(id);
				//cwMoney.setStatus(CwBorrowMoneyManager.CW_JK_AUDIT);
				cwBorrowMoneyManager.save(cwMoney);
			} else {
				return JsonWrapper.failureWrapper("流程ID为空！");
			}
			
			return JsonWrapper.successWrapper(id);
		}
		
	}
	/**审批流程并改变状态
	 * @param id
	 * @param flowId
	 * @param typeCode
	 * @param status
	 * @param httpServletRequest 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "cwtj")
	@ResponseBody
	public HashMap<String, Object> cwtj(String areaFlag,String id,
			String typeCode, String status,String activityId,ServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "借款填报");
		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
		
		if (StringUtil.isEmpty(id)) {
			return JsonWrapper.failureWrapper("参数错误！");
		} else {
			// 审批流程
			if (StringUtil.isNotEmpty(activityId)) {
				if(areaFlag.equals("1") || areaFlag.equals("2") || areaFlag.equals("3")){
					flowExtManager.submitActivity(map);
					CwBorrowMoney cwMoney=cwBorrowMoneyManager.get(id);
					//cwMoney.setStatus(CwBorrowMoneyManager.CW_JK_AUDIT);
					cwBorrowMoneyManager.save(cwMoney);
				}else if(areaFlag.equals("4")){
					CwBorrowMoney cwMoney1=cwBorrowMoneyManager.get(id);
					//cwMoney1.setStatus(CwBorrowMoneyManager.CW_JK_PASS);
					cwBorrowMoneyManager.save(cwMoney1);
					flowExtManager.submitActivity(map);
				}else{
					CwBorrowMoney cwMoney1=cwBorrowMoneyManager.get(id);
					cwMoney1.setStatus(CwBorrowMoneyManager.CW_JK_NO_PASS);
					cwBorrowMoneyManager.save(cwMoney1);
				}
			} else {
				return JsonWrapper.failureWrapper("流程ID为空！");
			}
			
			return JsonWrapper.successWrapper(id);
		}
		
	}
	/**退回审批流程并改变状态
	 * @param id
	 * @param flowId
	 * @param typeCode
	 * @param status
	 * @return
	 * @throws Exception
	 * 已登记，已提交，审核中，审核通过，审核不通过
	 */
	@RequestMapping(value = "cwth")
	@ResponseBody
	public HashMap<String, Object> cwth(String areaFlag,String id,
			String typeCode, String status,String activityId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "借款填报");
		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
		
		CwBorrowMoney cwMoney=cwBorrowMoneyManager.get(id);
		cwMoney.setStatus(CwBorrowMoneyManager.CW_JK_NO_PASS);
		cwBorrowMoneyManager.save(cwMoney);
		
		if (StringUtil.isEmpty(id)) {
			return JsonWrapper.failureWrapper("参数错误！");
		} else {
			// 退回流程
			if (StringUtil.isNotEmpty(activityId)) {
				flowExtManager.returnedActivity(map);
			} else {
				return JsonWrapper.failureWrapper("流程ID为空！");
			}
			return JsonWrapper.successWrapper(id);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getBeanMap")
	public String getBeanMap(String id,HttpServletRequest request) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		CwBorrowMoney bean = cwBorrowMoneyManager.get(id);
		map = bean.beanToMap();
		request.setAttribute("infoMap",map);
		request.setAttribute("templetName","hkd");
		return "app/finance/cw_borrow_print";
	}
	
	
	/**
	 * 打印借款单
	 * 
	 * @param request
	 * @param id --
	 *            借款单id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "printCwBorrow")
	public String printCwBorrow(HttpServletRequest request, String id) throws Exception {
		CwBorrowMoney cwMoney = cwBorrowMoneyManager.get(id);
		Map<String,Object> infoMap = new HashMap<String,Object>();
		infoMap = inspectionService.beanToMap(cwMoney);
		request.setAttribute("infoMap", infoMap);
		request.setAttribute("templetName", "CW_JKD");
		return "app/finance/cw_borrow_print";
	}
	
//	/**初审
//	 * @param id
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "chu")
//	@ResponseBody
//	public HashMap<String, Object> chu(String id,String c) throws Exception {
//		HashMap<String, Object> map = new HashMap<String, Object>();
//   	CurrentSessionUser user = SecurityUtil.getSecurityUser();
//   	String d="2";
//		if (id.isEmpty()){
//			map.put("success", false);
//			map.put("msg", "所选业务ID为空！");
//		} else {
//			CwBorrowMoney cwMoney=cwBorrowMoneyManager.get(id);
//			if(cwMoney==null){
//				map.put("success", false);
//				map.put("msg", "数据获取失败！");
//			} else {
//				cwMoney.setHandleName(user.getName());
//				cwMoney.setHandleId(user.getId());
//				cwMoney.setHandleTime(new Date());
//				//设置初审
//		   		if(c.equals(d)){
//		   			cwMoney.setStatus(CwBorrowMoneyManager.CW_JK_NO_PASS);//no已初审
//		   		}else{
//		   			cwMoney.setStatus(CwBorrowMoneyManager.CW_CSTG);//已初审
//		   		}
//				cwBorrowMoneyManager.save(cwMoney);
//				map.put("success", true);
//			}
//		}
//		return map;
//	}
//	 /**
//  	 * 通过的意见
//  	 * @param response
//    * @throws Exception 
//  	 */
//   @RequestMapping(value = "saveyj")
//	@ResponseBody
//  	public HashMap<String, Object> saveyj(HttpServletRequest request,CwBorrowMoney cwMoney) throws Exception {
//  		//获取当前登录人姓名
//		CurrentSessionUser user = SecurityUtil.getSecurityUser();
//  		return super.save(request, cwMoney);
//
//  	}
	/**借款确认
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "jkcheck")
	@ResponseBody
	public HashMap<String, Object> jkcheck(HttpServletRequest request,String repayment_man) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		request.setCharacterEncoding("UTF-8");
		String id=request.getParameter("id").toString();
		String check=request.getParameter("check").toString();
//		String repayment_man_id=request.getParameter("borrowerId").toString();
//		String repayment_man=request.getParameter("repayment_man");
//		String repayment_time=request.getParameter("repayment_time").toString();
		if (id.isEmpty()){
			map.put("success", false);
			map.put("msg", "所选业务ID为空！");
		} else {
			CwBorrowMoney cwBorrowMoney = cwBorrowMoneyManager.get(id);
			if(cwBorrowMoney==null){
				map.put("success", false);
				map.put("msg", "数据获取失败！");
			} else {
				CurrentSessionUser user = SecurityUtil.getSecurityUser();
				//取消确认				
				if(check.equals("0")){
					cwBorrowMoney.setHandleIdj(null);//还款处理人ID
					cwBorrowMoney.setHandleNamej(null);//还款处理人姓名
					cwBorrowMoney.setHandleTimej(null);//还款处理时间
					cwBorrowMoney.setStatus(CwBorrowMoneyManager.CW_CSTG);//反写状态为审核通过				
				}else if(check.equals("1")){
					cwBorrowMoney.setHandleIdj(user.getId());//还款处理人ID
					cwBorrowMoney.setHandleNamej(user.getName());//还款处理人姓名
					cwBorrowMoney.setHandleTimej(new Date());//还款处理时间
					cwBorrowMoney.setStatus(CwBorrowMoneyManager.CW_HK_NO);//未还款
				}
				cwBorrowMoneyManager.save(cwBorrowMoney);
				
				map.put("success", true);
			}
		}
		return map;
	}
	/**
	 * 借款批量确认
	 * @param request
	 * @param repayment_man
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "jkchecks")
	@ResponseBody
	public HashMap<String, Object> jkchecks(HttpServletRequest request,String repayment_man) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		String ids=request.getParameter("ids").toString();
		for(String id:ids.split(",")){
			CwBorrowMoney cwBorrowMoney = cwBorrowMoneyManager.get(id);
			cwBorrowMoney.setHandleIdj(user.getId());//还款处理人ID
			cwBorrowMoney.setHandleNamej(user.getName());//还款处理人姓名
			cwBorrowMoney.setHandleTimej(new Date());//还款处理时间
			cwBorrowMoney.setStatus(CwBorrowMoneyManager.CW_HK_NO);//未还款	
			cwBorrowMoneyManager.save(cwBorrowMoney);
		}
		return JsonWrapper.successWrapper(ids);
	}
	/**
	 * 还款批量确认
	 * @param request
	 * @param repayment_man
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "huansubmits")
	@ResponseBody
	public HashMap<String, Object> huansubmits(HttpServletRequest request,String repayment_man) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		String ids=request.getParameter("ids").toString();
		for(String id:ids.split(",")){
			CwBorrowMoney cwBorrowMoney = cwBorrowMoneyManager.get(id);
			cwBorrowMoney.setRepayment_man(cwBorrowMoney.getBorrower());
			cwBorrowMoney.setRepayment_man_id(cwBorrowMoney.getBorrowerId());
//			cwBorrowMoney.setRepayment_time(sdf.parse(repayment_time));
			cwBorrowMoney.setRepayment_time(new Date());
			cwBorrowMoney.setHandleIdh(user.getId());//还款处理人ID
			cwBorrowMoney.setHandleNameh(user.getName());//还款处理人姓名
			cwBorrowMoney.setHandleTimeh(new Date());//还款处理时间
			cwBorrowMoney.setStatus(CwBorrowMoneyManager.CW_HK_YES);//已还款
			cwBorrowMoneyManager.save(cwBorrowMoney);
		}
		return JsonWrapper.successWrapper(ids);
	}
}