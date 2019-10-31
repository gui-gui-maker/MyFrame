package com.lsts.finance.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import com.lsts.finance.bean.CwDrawmoney;
import com.lsts.finance.bean.CwRefundment;
import com.lsts.finance.bean.CwYijian;
import com.lsts.finance.service.CwDrawmoneyManager;
import com.lsts.finance.service.CwRefundmentManager;
import com.lsts.finance.service.CwYijianManager;


@Controller
@RequestMapping("cwRefundmentAction")
public class CwRefundmentAction extends SpringSupportAction<CwRefundment, CwRefundmentManager> {


	@Autowired
    private CwRefundmentManager  cwRefundmentManager;
   
    @Autowired
	private FlowExtManager flowExtManager;
	
	@Autowired
	private ActivityManager activityManager;
  
    @Autowired
    private CwYijianManager cwYijianManager;
	
	
	
	/**
	 * 财务初审通过
	 * @param id
	 * @param c
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "audit")
	@ResponseBody
	public HashMap<String, Object> audit(String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
   		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		if (id.isEmpty()){
			map.put("success", false);
			map.put("msg", "所选业务ID为空！");
		} else {
			CwRefundment cwRefundment = cwRefundmentManager.get(id);
			if(cwRefundment==null){
				map.put("success", false);
				map.put("msg", "数据获取失败！");
			} else {
				cwRefundment.setStatus(CwRefundmentManager.CW_CSTG);//已提交
				cwRefundment.setHandleId(user.getId()); 
				cwRefundment.setHandleName(user.getName());
				cwRefundment.setHandleTime(new Date());
				cwRefundmentManager.save(cwRefundment);
				
				CwYijian cwYijian=new CwYijian();
		   		cwYijian.setAuditMan(user.getName());  //获得审核人
		   		cwYijian.setAuditManId(user.getId()); //获得审核 人ID
		   		cwYijian.setAuditTime(new Date());  //获得审核时间
		   		cwYijian.setFileId(id);				//外键ID
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
			@RequestMapping(value = "submitzf")
			@ResponseBody
			public HashMap<String, Object> submitzf(String id) throws Exception {
				HashMap<String, Object> map = new HashMap<String, Object>();
				if (id.isEmpty()){
					map.put("success", false);
					map.put("msg", "所选业务ID为空！");
				} else {
					CwRefundment cwRefundment = cwRefundmentManager.get(id);
					if(cwRefundment==null){
						map.put("success", false);
						map.put("msg", "数据获取失败！");
					} else {
						cwRefundment.setStatus(CwRefundmentManager.CW_DJZF);//已作废
						cwRefundment.setAbolish_time(new Date());
						if(cwRefundment.getHandleTime()!=null){
							boolean bj=isSameDate(new Date(),cwRefundment.getHandleTime());
							System.out.println(bj);
							if(bj){
								cwRefundment.setAbolish("1");
							}else{
								cwRefundment.setAbolish("2");
							}
						}
						cwRefundmentManager.save(cwRefundment);
						
						map.put("success", true);
					}
				}
				return map;
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
	public HashMap<String, Object> subFolw(String id, String flowId,
			String activityId,String typeCode, String status) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
		map.put(FlowExtWorktaskParam.FLOW_DEFINITION_ID, flowId);
		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
		map.put(FlowExtWorktaskParam.SERVICE_TITLE,"领款填报");
		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
		
		CwRefundment cwRefundment = cwRefundmentManager.get(id);
		cwRefundment.setStatus(CwRefundmentManager.CW_TK_AUDIT);
		cwRefundmentManager.save(cwRefundment);

		if (StringUtil.isEmpty(id)) {
			return JsonWrapper.failureWrapper("参数错误！");
		} else {
			// 启动流程
			if (StringUtil.isNotEmpty(flowId)) {
				cwRefundmentManager.doStartProess(map);

			} else {
				return JsonWrapper.failureWrapper("流程ID为空！");
			}

			return JsonWrapper.successWrapper(id);
		}

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
		if (id.isEmpty()){
			map.put("success", false);
			map.put("msg", "所选业务ID为空！");
		} else {
			CwRefundment cwRefundment = cwRefundmentManager.get(id);
			if(cwRefundment==null){
				map.put("success", false);
				map.put("msg", "数据获取失败！");
			} else {
				cwRefundment.setStatus(CwRefundmentManager.CW_SUBMIT);//已提交
				cwRefundmentManager.save(cwRefundment);
				
				map.put("success", true);
			}
		}
		return map;
	}
		
	/**
	 * 审批流程并改变状态
	 * @param id
	 * @param flowId
	 * @param activityId
	 * @param typeCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "lktj")
	@ResponseBody
	public HashMap<String, Object> lktj(String areaFlag,String id, String flowId,
			String activityId,String typeCode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
		map.put(FlowExtWorktaskParam.SERVICE_TITLE,"领款填报");
		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);

		CwRefundment cwRefundment = cwRefundmentManager.get(id);
		cwRefundment.setStatus(CwRefundmentManager.CW_TK_AUDIT);
		cwRefundmentManager.save(cwRefundment);
		
		if(StringUtil.isEmpty(id)){
			return JsonWrapper.failureWrapper("参数错误！");
		}else{
			// 审批流程
			if (StringUtil.isNotEmpty(activityId)) {
				if(areaFlag.equals("1") || areaFlag.equals("2") || areaFlag.equals("3")){
					flowExtManager.submitActivity(map);
				}else if(areaFlag.equals("4")){
					CwRefundment cwRefundment2=cwRefundmentManager.get(id);
					cwRefundment2.setStatus(CwRefundmentManager.CW_TK_PASS);
					cwRefundmentManager.save(cwRefundment2);
					flowExtManager.submitActivity(map);
				}else{
					CwRefundment cwRefundment2=cwRefundmentManager.get(id);
					cwRefundment2.setStatus(CwRefundmentManager.CW_TK_NO_PASS);
					cwRefundmentManager.save(cwRefundment2);
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
	@RequestMapping(value = "lkth")
	@ResponseBody
	public HashMap<String, Object> lkth(String areaFlag,String id,
			String typeCode, String status,String activityId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "领款填报");
		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);

		CwRefundment cwRefundment=cwRefundmentManager.get(id);
		cwRefundment.setStatus(CwRefundmentManager.CW_TK_NO_PASS);
		cwRefundmentManager.save(cwRefundment);
		
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
	  
    /**
     * 获取当前用户信息
     */
	@RequestMapping(value = "savetk")
	@ResponseBody
    public synchronized HashMap<String,Object> savetk(HttpServletRequest request,@RequestBody CwRefundment cwRefundment) throws Exception{
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(cwRefundment.getId() == null || cwRefundment.getId().equals("")){
			cwRefundmentManager.savetk(cwRefundment, user);
			map.put("success", true);
			map.put("msg", "数据保存成功！");
		}else{
			if (cwRefundment.getStatus().equals(CwRefundmentManager.CW_CSTG)) {
				map.put("msg", "此条数据已通过不可修改！");
				map.put("success", false);
			}else{
				cwRefundmentManager.savetk(cwRefundment, user);
				map.put("success", true);
				map.put("msg", "数据保存成功！");
			}
		}
		return map;
    }
    
	
		/**
		 * 删除
		 * */
		@RequestMapping(value = "getdelete")
		@ResponseBody
		public HashMap<String, Object> getdelete(String ids,CwRefundment cwRefundment) throws Exception {
			cwRefundmentManager.delete(ids,cwRefundment);
			return JsonWrapper.successWrapper();
		}
		
		
		
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "getBeanMap")
		public String getBeanMap(String id,HttpServletRequest request) throws Exception {
			Map<String, String> map = new HashMap<String, String>();
			CwRefundment bean = cwRefundmentManager.get(id);
			map = bean.beanToMap();
			request.setAttribute("infoMap",map);
			request.setAttribute("templetName","lkd");
			return "app/finance/cw_borrow_print";
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
		/**
		 * 确认退款
		 * @param request
		 * @param repayment_man
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "tuisubmit")
		@ResponseBody
		public HashMap<String, Object> tuisubmit(HttpServletRequest request,String repayment_man) throws Exception {
			HashMap<String, Object> map = new HashMap<String, Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			request.setCharacterEncoding("UTF-8");
			String id=request.getParameter("id").toString();
			String check=request.getParameter("check").toString();
//			String repayment_man_id=request.getParameter("borrowerId").toString();
//			String repayment_man=request.getParameter("repayment_man");
//			String repayment_time=request.getParameter("repayment_time").toString();
			if (id.isEmpty()){
				map.put("success", false);
				map.put("msg", "所选业务ID为空！");
			} else {
				CwRefundment cwRefundment = cwRefundmentManager.get(id);
				if(cwRefundment==null){
					map.put("success", false);
					map.put("msg", "数据获取失败！");
				} else {
					CurrentSessionUser user = SecurityUtil.getSecurityUser();
					//取消确认				
					if(check.equals("0")){
						cwRefundment.setDmQrId(null);//确认处理人ID
						cwRefundment.setDmQrName(null);//确认处理人姓名
						cwRefundment.setDmQrDate(null);//确认处理时间
						cwRefundment.setStatus(CwRefundmentManager.CW_CSTG);//反写回审批通过状态			
					}else if(check.equals("1")){
						cwRefundment.setDmQrId(user.getId());//确认处理人ID
						cwRefundment.setDmQrName(user.getName());//确认处理人姓名
						cwRefundment.setDmQrDate(new Date());//确认处理时间
						cwRefundment.setStatus(CwRefundmentManager.CW_YTK_YES);//已退款	
					}
					cwRefundmentManager.save(cwRefundment);
					
					map.put("success", true);
				}
			}
			return map;
		}
		/**
		 * 退款批量确认
		 * @param request
		 * @param repayment_man
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "tuisubmits")
		@ResponseBody
		public HashMap<String, Object> tuisubmits(HttpServletRequest request,String repayment_man) throws Exception {
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			String ids=request.getParameter("ids").toString();
			for(String id:ids.split(",")){
				CwRefundment cwRefundment = cwRefundmentManager.get(id);
				cwRefundment.setDmQrId(user.getId());//确认处理人ID
				cwRefundment.setDmQrName(user.getName());//确认处理人姓名
				cwRefundment.setDmQrDate(new Date());//确认处理时间
				cwRefundment.setStatus(CwRefundmentManager.CW_YTK_YES);//已退款		
				cwRefundmentManager.save(cwRefundment);
			}
			return JsonWrapper.successWrapper(ids);
		}
}