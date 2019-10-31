package com.lsts.report.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.khnt.base.Factory;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.exception.KhntException;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.security.CurrentSessionUser;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;
import com.lsts.employee.service.EmployeesService;
import com.lsts.log.service.SysLogService;
import com.lsts.report.bean.ReportError;
import com.lsts.report.bean.ReportErrorDTO;
import com.lsts.report.dao.ReportErrorDao;
import com.lsts.report.service.ReportErrorService;
import com.scts.car.dao.CarApplyDao;

/**
 * 不符合报告控制器
 * 
 * @ClassName ReportErrorAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-11-04 14:16:00
 */
@Controller
@RequestMapping("report/error")
public class ReportErrorAction extends
		SpringSupportAction<ReportError, ReportErrorService> {

	@Autowired
	private ReportErrorService reportErrorService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private SysLogService logService;
	@Autowired
	private AttachmentManager attachmentManager;
	@Autowired
	private EmployeesService employeesService;
	@Autowired
	private ReportErrorDao reportErrorDao;
	@Autowired
	private CarApplyDao carApplyDao;
	
	/**
	 * 保存
	 * 
	 * @param request
	 * @param reportError
	 * @throws Exception
	 */
	@RequestMapping(value = "saveBasic")
	@ResponseBody
	public HashMap<String, Object> saveBasic(HttpServletRequest request, @RequestBody ReportError reportError) throws Exception {
		try {
			return reportErrorService.saveBasic(
					reportError, request);
		} catch (Exception e) {
			log.debug(e.toString());
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败！");
		}
	}
	
	// 获取不符合报告信息
	@RequestMapping(value = "getDetail")
	@ResponseBody
	public HashMap<String, Object> getDetail(HttpServletRequest request)
			throws Exception {
		String id = request.getParameter("id");
		try {
			return reportErrorService.getDetail(id);
		} catch (Exception e) {
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("读取不符合报告信息失败！");
		}
	}
	
	/**
	 * 质量部审核不符合报告
	 * 
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "qua_check")
	@ResponseBody
	public HashMap<String, Object> qua_check(@RequestBody ReportErrorDTO entity, HttpServletRequest request) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		CurrentSessionUser curUser = this.getCurrentUser(); 	// 获取当前用户登录信息
		try {
			String check_result = entity.getCheck_result();		// 审核结果
			String check_remark = entity.getCheck_remark();		// 审核备注（审核不通过时填写）
			
			String check_result_txt = ""; 
			String[] ids = entity.getId().split(",");
			for(int i=0; i<ids.length; i++){
				ReportError reportError = reportErrorService.get(ids[i]);
				reportError.setCheck_result(check_result);
				reportError.setCheck_remark(check_remark);
				reportError.setCheck_user_id(curUser.getId());		// 审核人ID
				reportError.setCheck_user_name(curUser.getName());	// 审核人姓名
				reportError.setCheck_date(new Date());				// 审核时间
				
				// 审核结果（1：审核通过 2：审核不通过）
				if ("1".equals(check_result)) {
					check_result_txt = "审核通过";
					// 审核通过时，系统将自动发送微信和短信给责任人
					// 根据责任人ID获取责任人手机号码
					
					/*	
					因为调用微信短信接口不稳定、发送速度影响
					2015-12-17修改，将原直接在后台程序中调用接口改为前台通过ajax请求调用接口		
					Employee employee = employeesService.get(reportError.getError_user_id());
					String destNumber = employee.getMobileTel();
					String content =  Constant.getReportErrorNoticeContent(
							reportError.getSn(), DateUtil.getDateTime(Constant.defaultDatePattern, reportError.getSolve_end_date()));
					// 发送短信
					String send_mo_status = messageService.sendMoMsg(request, id, content, destNumber);	
					reportError.setSend_msg_status(send_mo_status.trim());
					// 发送微信
					String send_wx_status = messageService.sendWxMsg(request, id, content, destNumber);	
					reportError.setSend_wx_status(send_wx_status.trim());
					if("0".equals(send_mo_status.trim()) || "0".equals(send_wx_status.trim())){
						reportError.setReceive_user_id(reportError.getError_user_id());		// 接收人ID
						reportError.setReceive_user_name(reportError.getError_user_name());	// 接收人姓名
						reportError.setReceive_date(new Date());							// 接收日期
					}
					*/
					reportError.setData_status("03");	// 数据状态（03：质量部审核通过，检验员待处理）	
				}else{
					check_result_txt = "审核不通过";
					check_remark = "，" + check_remark;
					reportError.setData_status("02");	// 数据状态（02：质量部审核未通过，流程结束）	
				}
				reportErrorService.save(reportError);	// 1、质量部审核不符合报告
				
				// 2、写入日志
				logService.setLogs(ids[i], "质量部审核不符合报告", check_result_txt+"，"+check_remark,
						curUser.getId(), curUser.getName(), new Date(), request
								.getRemoteAddr());
			}
			
			
			
			
			wrapper.put("success", true);
			wrapper.put("data", entity.getId());
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
			wrapper.put("msg", "质量部审核不符合报告保存失败，请重试！");
		}
		return wrapper;
	}
	
	/**
	 * 不符合项目为“其他”时，检验员纠正处理
	 * 
	 * @param request
	 * @param reportErrorRecord
	 * @throws Exception
	 */
	@RequestMapping(value = "deal")
	@ResponseBody
	public HashMap<String, Object> deal(HttpServletRequest request, String id, String deal_remark) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		CurrentSessionUser curUser = this.getCurrentUser(); 	// 获取当前用户登录信息
		try {
			// 见证照片
			String uploadFiles = request.getParameter("uploadFiles");
			
			// 1、保存检验员纠正信息
			ReportError reportError = reportErrorService.get(id);
			reportError.setDeal_user_id(curUser.getId());		// 检验员ID
			reportError.setDeal_user_name(curUser.getName());	// 检验员姓名
			reportError.setDeal_date(new Date());				// 检验员纠正时间
			reportError.setDeal_remark(deal_remark);			// 检验员备注处理结果
			reportError.setData_status("04");					// 数据状态（04：检验员已纠正，部门负责人待确认）	
			reportErrorService.save(reportError);		
			
			// 2、检验员纠正的见证照片
			if (StringUtil.isNotEmpty(uploadFiles)) {
				String[] files = uploadFiles.replaceAll("/^,/", "").split(",");
				for (String file : files) {
					if (StringUtil.isNotEmpty(file)) {
						attachmentManager.setAttachmentBusinessId(file,
								id); 
					}
				}
			}
			
			// 3、写入日志
			logService.setLogs(id, "检验员纠正处理", "检验员纠正处理",
					curUser.getId(), curUser.getName(), new Date(), request
							.getRemoteAddr());
			
			
			// 4、发送消息提醒部门负责人确认
			try {
				sendMsg(request, reportError);
			} catch (Exception e) {
				e.printStackTrace();
				KhntException kh = new KhntException(e);
				log.error(kh.getMessage());
			}
			
			wrapper.put("success", true);
			wrapper.put("data", reportError);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
			wrapper.put("msg", "检验员纠正处理保存失败，请重试！");
		}
		return wrapper;
	}
	
	/**
	 * 责任部门负责人确认
	 * 
	 * @param request
	 * @param reportErrorRecord
	 * @throws Exception
	 */
	@RequestMapping(value = "app_dep_finish")
	@ResponseBody
	public HashMap<String, Object> app_dep_finish(HttpServletRequest request, String id, String dep_head_remark) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		CurrentSessionUser curUser = this.getCurrentUser(); 	// 获取当前用户登录信息
		try {
			// 1、责任部门负责人确认纠正完成情况
			ReportError reportError = reportErrorService.get(id);
			reportError.setDep_head_id(curUser.getId());		// 部门负责人ID
			reportError.setDep_head_name(curUser.getName());	// 部门负责人姓名
			reportError.setDep_head_check_date(new Date());		// 部门负责人审核时间
			reportError.setDep_head_remark(dep_head_remark);	// 部门负责人备注完成情况
			reportError.setData_status("05");		// 数据状态（05：责任部门负责人已确认，质量部待确认）	
			reportErrorService.save(reportError);	
			// 2、写入日志
			logService.setLogs(id, "确认不符合报告情况", "责任部门负责人确认不符合报告完成情况",
					curUser.getId(), curUser.getName(), new Date(), request
							.getRemoteAddr());
			
			wrapper.put("success", true);
			wrapper.put("data", reportError);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
			wrapper.put("msg", "责任部门负责人确认不符合报告完成情况失败，请重试！");
		}
		return wrapper;
	}

	/**
	 * 质量部确认整改
	 * 
	 * @param request
	 * @param reportErrorRecord
	 * @throws Exception
	 */
	@RequestMapping(value = "qua_head_check")
	@ResponseBody
	public HashMap<String, Object> qua_head_check(HttpServletRequest request, String id, String qua_end_result) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		CurrentSessionUser curUser = this.getCurrentUser(); 	// 获取当前用户登录信息
		try {
			ReportError reportError = reportErrorService.get(id);
			reportError.setQua_end_result(qua_end_result);
			reportError.setQua_head_id(curUser.getId());
			reportError.setQua_head_name(curUser.getName());
			reportError.setQua_head_check_date(new Date());
			reportError.setData_status("06");	// 数据状态（06：质量部确认已整改）	
			reportErrorService.save(reportError);	// 1、保存不符合报告主表
			// 2、写入日志
			logService.setLogs(id, "质量部确认整改", qua_end_result,
					curUser.getId(), curUser.getName(), new Date(), request
							.getRemoteAddr());
			
			wrapper.put("success", true);
			wrapper.put("data", reportError);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
			wrapper.put("msg", "质量部确认整改，保存失败，请重试！");
		}
		return wrapper;
	}
	
	/**
	 * 消息提醒推送
	 * 
	 * @param request
	 *            -- 请求对象
	 * @param reportError
	 *            -- 不符合报告对象           
	 * @return
	 * @author GaoYa
	 * @date 2018-07-24 上午11:06:00
	 */
	@SuppressWarnings("unchecked")
	private void sendMsg(HttpServletRequest request, ReportError reportError) {
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("errorOp", reportError.getDeal_user_name());
		map1.put("errorSn", reportError.getSn());
		/*String wx_check_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb0f376eb09e64dd3"
				+ "&redirect_uri=http://kh.scsei.org.cn/car/apply/weixinUserInfo.do?businessId=" + carApply.getId()
				+ "&businessStatus=" + carApply.getData_status() + "&response_type=code&scope=snsapi_base&state=STATE";
		HashMap<String, Object> map2 = new HashMap<String, Object>();
		map2.put("url", wx_check_url);*/
		try {
			String msg_code = "reportError_confirm";
			// 获取发送内容
			String content = Constant.getReportErrorWxTips(reportError.getDeal_user_name(), reportError.getSn());
			String mobile = "";

			List<Map<String, Object>> leaders = carApplyDao.queryDepLeaders();
			for (Iterator iterator = leaders.iterator(); iterator.hasNext();) {
				Map<String, Object> dataMap = (Map<String, Object>) iterator.next();
				String org_id = dataMap.get("org_id").toString();
				if (org_id.equals(reportError.getError_dep_id())) {
					mobile = dataMap.get("mobile_tel").toString();
					break;
				}
			}
			if (StringUtil.isEmpty(mobile)) {
				String[] emp_ids = null;
				if ("100025".equals(reportError.getError_dep_id())) {
					// 财务部
					String emp_id = Factory.getSysPara().getProperty("CAR_APPLY_CW_EMP_ID");
					if (StringUtil.isNotEmpty(emp_id)) {
						emp_ids = emp_id.split(",");
					} else {
						emp_ids = Constant.CAR_APPLY_CW_EMP_ID.split(",");
					}
				} else if ("100030".equals(reportError.getError_dep_id())) {
					// 科管部
					String emp_id = Factory.getSysPara().getProperty("CAR_APPLY_KG_EMP_ID");
					if (StringUtil.isNotEmpty(emp_id)) {
						emp_ids = emp_id.split(",");
					} else {
						emp_ids = Constant.CAR_APPLY_KG_EMP_ID.split(",");
					}
				}
				for (int j = 0; j < emp_ids.length; j++) {
					Employee employee = employeesService.get(emp_ids[j]);
					// 获取发送目标号码
					if (StringUtil.isEmpty(mobile)) {
						mobile = employee.getMobileTel();
					}
				}
			}
			if(StringUtil.isNotEmpty(mobile)) {
				// 发送即时短信和微信提醒（发送类型以系统配置为准）
				messageService.sendMassageByConfig(request, reportError.getId(), mobile, content, msg_code,
						null, map1, null, null, Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
	}
	
	// 获取报告信息
	@RequestMapping(value = "queryReportInfos")
	@ResponseBody
	public HashMap<String, Object> getReportInfos(HttpServletRequest request)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String ids = request.getParameter("ids");
		try {
			map.put("list", reportErrorService.queryReportInfos(ids));
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("读取报告信息失败！");
		}
		return map;
	}
	
	// 删除不符合报告信息
	@RequestMapping(value = "del")
	@ResponseBody
	public HashMap<String, Object> del(HttpServletRequest request, String ids)
			throws Exception {
		String[] idArr = ids.split(",");
		for (int i = 0; i < idArr.length; i++) {
			reportErrorService.del(request, idArr[i]);
		}
		return JsonWrapper.successWrapper(ids);
	}
	
	/**
	 * 发短信
	 * 
	 * @param request
	 * @param id -- 不符合报告业务id
	 * @throws Exception
	 * 
	 * @author GaoYa
	 * @date 2015-12-17 14:16:00
	 */
	@RequestMapping(value = "sendMoMsg")
	@ResponseBody
	public HashMap<String, Object> sendMoMsg(HttpServletRequest request, String ids)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			//CurrentSessionUser curUser = this.getCurrentUser(); 	// 获取当前用户登录信息
			String[] id_list = ids.split(",");
			for (int i = 0; i < id_list.length; i++) {
				ReportError reportError = reportErrorService.get(id_list[i]);
				String[] userId = reportError.getError_user_id().split(",");
				for(String userIds:userId){  //循环人员ID
					//获取电话
					//Employee employee = employeesService.get(userIds);
					String user=reportErrorDao.gettol(userIds).toString();
					String destNumber = Pattern.compile("\\b([\\w\\W])\\b").matcher(user.toString()
							.substring(1,user.toString().length()-1)).replaceAll("'$1'");
					String content =  Constant.getReportErrorNoticeContent(
							reportError.getSn(), DateUtil.getDateTime(Constant.defaultDatePattern, reportError.getSolve_end_date()));
					String send_msg_status = messageService.sendMoMsg(request, id_list[i], content, destNumber);
					if("0".equals(send_msg_status)){	// 0：发送成功
						reportError.setReceive_user_id(reportError.getError_user_id());		// 接收人ID
						reportError.setReceive_user_name(reportError.getError_user_name());	// 接收人姓名
						reportError.setReceive_date(new Date());							// 接收日期
						reportError.setSend_msg_status(send_msg_status);
						reportErrorService.save(reportError);	// 1、保存不符合报告主表
						// 2、写入日志
						/*logService.setLogs(id, "发送短信", "质量部发送短信给不符合报告责任人",
							curUser.getId(), curUser.getName(), new Date(), request
									.getRemoteAddr());*/
						map.put("success", true);
					}else{
						return JsonWrapper.failureWrapperMsg("短信发送失败！错误代码："+send_msg_status);
					}
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("短信发送失败！");
		}
		return map;
	}

	/**
	 * 发微信
	 * 
	 * @param request
	 * @param id -- 不符合报告业务id
	 * @throws Exception
	 * 
	 * @author GaoYa
	 * @date 2015-12-17 13:10:00
	 */
	@RequestMapping(value = "sendWxMsg")
	@ResponseBody
	public HashMap<String, Object> sendWxMsg(HttpServletRequest request, String ids)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			//CurrentSessionUser curUser = this.getCurrentUser(); 	// 获取当前用户登录信息
			String[] id_list = ids.split(",");
			for (int i = 0; i < id_list.length; i++) {
				ReportError reportError = reportErrorService.get(id_list[i]);
				String[] userId = reportError.getError_user_id().split(",");
				for(String userIds:userId){  //循环人员ID
					String user=reportErrorDao.gettol(userIds).toString();
					String destNumber = Pattern.compile("\\b([\\w\\W])\\b").matcher(user.toString()
							.substring(1,user.toString().length()-1)).replaceAll("'$1'");
					String content =  Constant.getReportErrorNoticeContent(
							reportError.getSn(), DateUtil.getDateTime(Constant.defaultDatePattern, reportError.getSolve_end_date()));
					String send_wx_status = messageService.sendWxMsg(request, id_list[i], Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD, content, destNumber);
					if("0".equals(send_wx_status)){	// 0：发送成功
						reportError.setReceive_user_id(reportError.getError_user_id());		// 接收人ID
						reportError.setReceive_user_name(reportError.getError_user_name());	// 接收人姓名
						reportError.setReceive_date(new Date());							// 接收日期
						reportError.setSend_wx_status(send_wx_status);
						reportErrorService.save(reportError);	// 1、保存不符合报告主表
						// 2、写入日志
						/*logService.setLogs(id, "发送微信", "质量部重发送微信给不符合报告责任人",
							curUser.getId(), curUser.getName(), new Date(), request
									.getRemoteAddr());*/
						map.put("success", true);
					}else{
						return JsonWrapper.failureWrapperMsg("微信发送失败！错误代码："+send_wx_status);
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("微信发送失败！");
		}
		return map;
	}
	
	/**
	 * 发微信和短信
	 * 
	 * @param request
	 * @param id -- 不符合报告业务id
	 * @throws Exception
	 * 
	 * @author GaoYa
	 * @date 2015-12-17 17:50:00
	 */
	@RequestMapping(value = "sendMsgs")
	@ResponseBody
	public HashMap<String, Object> sendMoMsgs(HttpServletRequest request, String ids, String send_msg_type)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			
			String[] id_list = ids.split(",");
			for (int i = 0; i < id_list.length; i++) {
				ReportError reportError = reportErrorService.get(id_list[i]);
				
				//Employee employee = employeesService.get(reportError.getError_user_id());
				// 获取发送目标号码
				//String destNumber = employee.getMobileTel();
				
				String[] userId = reportError.getError_user_id().split(",");
				for(String userIds:userId){  //循环人员ID
					//获取电话
					//Employee employee = employeesService.get(userIds);
					String user=reportErrorDao.gettol(userIds).toString();
					String destNumber = Pattern.compile("\\b([\\w\\W])\\b").matcher(user.toString()
							.substring(1,user.toString().length()-1)).replaceAll("'$1'");
					
					// 获取发送内容
					String content =  Constant.getReportErrorNoticeContent(
							reportError.getSn(), DateUtil.getDateTime(Constant.defaultDatePattern, reportError.getSolve_end_date()));
					// 发送信息类型（1：微信 2：短信 3：微信和短信）
					if(StringUtil.isEmpty(send_msg_type)){
						send_msg_type = "1";	// 默认发送微信
					}
					
					String send_wx_status = "";
					String send_mo_status = "";
					if("1".equals(send_msg_type)){
						// 发送微信
						send_wx_status = messageService.sendWxMsg(request, id_list[i], Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD, content, destNumber);
					}else if("2".equals(send_msg_type)){
						// 发送短信
						send_mo_status = messageService.sendMoMsg(request, id_list[i], content, destNumber);	
					}else if("3".equals(send_msg_type)){
						// 发送微信
						send_wx_status = messageService.sendWxMsg(request, id_list[i], Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD, content, destNumber);	
						// 发送短信
						send_mo_status = messageService.sendMoMsg(request, id_list[i], content, destNumber);	
					}
					reportError.setSend_wx_status(send_wx_status.trim());	// 微信发送状态
					reportError.setSend_msg_status(send_mo_status.trim());	// 短信发送状态
					
					if("0".equals(send_mo_status.trim()) || "0".equals(send_wx_status.trim())){
						reportError.setReceive_user_id(reportError.getError_user_id());		// 接收人ID
						reportError.setReceive_user_name(reportError.getError_user_name());	// 接收人姓名
						reportError.setReceive_date(new Date());							// 接收日期
						reportErrorService.save(reportError);	// 1、保存不符合报告主表
						map.put("success", true);
						map.put("msg", "审核成功！");
					}else{
						if(!"4".equals(send_msg_type)){
							return JsonWrapper.failureWrapperMsg("发送失败！微信错误代码："+send_wx_status+"，短信错误代码："+send_mo_status);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("消息发送失败！");
		}
		return map;
	}
	
	// 查询流程步骤信息
	@RequestMapping(value = "getFlowStep")
	@ResponseBody
	public ModelAndView getFlowStep(HttpServletRequest request)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map = reportErrorService
				.getFlowStep(request.getParameter("id"));
		ModelAndView mav = new ModelAndView("app/report/flow_card", map);
		return mav;
	}
	

//		@RequestMapping(value = "getemployeeid")
//		@ResponseBody
//		public Map<String, Object> getemployeeid(String  userId)
//				throws Exception {
//			Map<String, Object> map = new HashMap<String, Object>();
//			String[] Id = userId.split(",");
//			for(String userIds:Id){  //循环人员ID
//				
//				List employeeid=reportErrorDao.getemployeeid(userIds);
//				map.put("employee",employeeid);
//			}
//			return map;
//		}
}
