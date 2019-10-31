package com.scts.maintenance.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.Org;
import com.khnt.rbac.impl.manager.OrgManagerImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;
import com.lsts.employee.service.EmployeesService;
import com.lsts.log.bean.SysLog;
import com.lsts.log.service.SysLogService;
import com.lsts.report.dao.ReportErrorDao;
import com.scts.maintenance.bean.MaintenanceInfo;
import com.scts.maintenance.bean.MaintenanceInfoDTO;
import com.scts.maintenance.dao.MaintenanceInfoDao;
import com.scts.task.bean.FunctionTaskInfo;
import com.scts.task.dao.FunctionTaskInfoDao;

import util.TS_Util;

/**
 * 系统更新维护日志（系统建设台账）明细业务逻辑对象
 * @ClassName MaintenanceInfoService
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-09-09 上午11:43:00
 */
@Service("maintenanceInfoService")
public class MaintenanceInfoService extends
		EntityManageImpl<MaintenanceInfo, MaintenanceInfoDao> {
	@Autowired
	private MaintenanceInfoDao maintenanceInfoDao;
	@Autowired
	private SysLogService logService;
	@Autowired
	private AttachmentManager attachmentManager;
	@Autowired
	private OrgManagerImpl orgManager;
	@Autowired
	private EmployeesService employeesService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private FunctionTaskInfoDao functionTaskInfoDao;
	@Autowired
	private ReportErrorDao reportErrorDao;
	
	//点击修改
	public  HashMap<String, Object> maintenanceEdit(HttpServletRequest request, MaintenanceInfo maintenanceInfo, String uploadFiles,String type,String send_msg_type) throws Exception{
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
		// 1、保存台账信息
		if (StringUtil.isNotEmpty(maintenanceInfo.getOrg_id())) {
			Org org = orgManager.get(maintenanceInfo.getOrg_id());
			maintenanceInfo.setOrg_name(org.getOrgName());
		}else{
			maintenanceInfo.setOrg_id(user.getDepartment().getId());
			maintenanceInfo.setOrg_name(user.getDepartment().getOrgName());
		}	
		
		if (StringUtil.isNotEmpty(maintenanceInfo.getAdvance_user_name())) {	// 报告人,保存的是employee_id
			String user_id = employeesService.getEmployee(maintenanceInfo.getOrg_id(), maintenanceInfo.getAdvance_user_name());
			if(StringUtil.isNotEmpty(user_id)){
				maintenanceInfo.setAdvance_user_id(user_id);
			}
		}
		if (maintenanceInfo.getAdvance_date() == null) {
			maintenanceInfo.setAdvance_date(new Date());	// 报告日期
		}
		
		if (StringUtil.isNotEmpty(maintenanceInfo.getCreate_user_name())) {	// 记录人，保存的是user_id
			String user_id = employeesService.getUserID(maintenanceInfo.getCreate_user_name());
			if(StringUtil.isNotEmpty(user_id)){
				maintenanceInfo.setCreate_user_id(user_id);
			}
		}else{
			maintenanceInfo.setCreate_user_id(user.getId()); 
			maintenanceInfo.setCreate_user_name(user.getName());
		}
		if (maintenanceInfo.getCreate_date() == null) {
			maintenanceInfo.setCreate_date(new Date());	// 记录日期
		}
		
		if (StringUtil.isNotEmpty(maintenanceInfo.getReceive_user_name())) {	// 受理人,保存的是user_id
			String user_id = employeesService.getUserID(maintenanceInfo.getReceive_user_name());
			if(StringUtil.isNotEmpty(user_id)){
				maintenanceInfo.setReceive_user_id(user_id);
			}
		}else{
			maintenanceInfo.setReceive_user_id(user.getId()); 
			maintenanceInfo.setReceive_user_name(user.getName());
		}
		if (maintenanceInfo.getReceive_date() == null) {
			maintenanceInfo.setReceive_date(new Date());	// 受理日期
		}
		
		if(StringUtil.isEmpty(maintenanceInfo.getSn())){
			String pre_sn = TS_Util.getCurYearMonth();
			maintenanceInfo.setSn(pre_sn + maintenanceInfoDao.queryNextSn(pre_sn));
		}
		
		maintenanceInfo.setData_status(type);	// 1：已受理 2：已论证 3：已完成
		//点击修改--改变状态需要再次论证
		if("1".equals(type)){
			//论证值赋空
			maintenanceInfo.setProve_user_id(null);
			maintenanceInfo.setProve_user_name(null);
			maintenanceInfo.setProve_date(null);
			maintenanceInfo.setProve_type(null);
			maintenanceInfo.setProve_remark(null);
			//FunctionTaskInfo bean=functionTaskInfoDao.get(maintenanceInfo.getFk_func_task_id());
			maintenanceInfo.setExpect_finish_date(null);//预计完成日期;
			

			//完成情况赋空
			maintenanceInfo.setDevelop_user_name(null);
			maintenanceInfo.setDevelop_start_date(null);
			maintenanceInfo.setDevelop_end_date(null);
			maintenanceInfo.setTest_user_name(null);
			maintenanceInfo.setTest_date(null);
			maintenanceInfo.setDevelop_desc(null);
			maintenanceInfo.setWrite_user_name(null);
			maintenanceInfo.setWrite_date(null);
		}
		if("2".equals(type)){
			//完成情况赋空
			maintenanceInfo.setDevelop_user_name(null);
			maintenanceInfo.setDevelop_start_date(null);
			maintenanceInfo.setDevelop_end_date(null);
			maintenanceInfo.setTest_user_name(null);
			maintenanceInfo.setTest_date(null);
			maintenanceInfo.setDevelop_desc(null);
			maintenanceInfo.setWrite_user_name(null);
			maintenanceInfo.setWrite_date(null);
		}
		maintenanceInfo.setMdy_user_id(user.getId()); 		// 最后修改人ID
		maintenanceInfo.setMdy_user_name(user.getName()); 	// 最后修改人姓名
		maintenanceInfo.setMdy_date(new Date()); 				// 最后修改时间
		maintenanceInfoDao.save(maintenanceInfo); 
		
		// 2、记录日志
		logService.setLogs(maintenanceInfo.getId(), "业务受理", "系统建设台账业务受理", user.getId(), user
				.getName(), new Date(), request.getRemoteAddr());
		
		// 3、保存台账附件
		if (StringUtil.isNotEmpty(uploadFiles)) {
			String[] files = uploadFiles.replaceAll("/^,/", "").split(",");
			for (String file : files) {
				if (StringUtil.isNotEmpty(file)) {
					attachmentManager.setAttachmentBusinessId(file,
							maintenanceInfo.getId(), "task"); 
				}
			}
		}
		
		//4、论证、完成发送消息
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("advanceUser", maintenanceInfo.getAdvance_user_name());
		map1.put("proDesc", "“"+(maintenanceInfo.getPro_desc().length()>128?maintenanceInfo.getPro_desc().substring(0,128)+"......":maintenanceInfo.getPro_desc())+"”");
		if(maintenanceInfo.getExpect_finish_date()!=null){
			map1.put("finishDate", DateUtil.format(maintenanceInfo.getExpect_finish_date(), "yyyy-MM-dd"));
		}
		map1.put("remark", maintenanceInfo.getProve_remark());
		if("2".equals(type)){
			//4.1论证发送消息
			if (StringUtil.isNotEmpty(maintenanceInfo.getAdvance_user_id()) && !"0".equals(send_msg_type)) {
				//发送论证提醒消息给报告人（send_msg_type 0：不发送任何消息提醒）
				String[] userId = maintenanceInfo.getAdvance_user_id().split(",");
				for(String userIds:userId){  //循环人员ID
					// 获取手机号码
					Employee employee = employeesService.get(userIds);
					if(employee!=null) {
						String mobile = employee.getMobileTel();
						if (StringUtil.isNotEmpty(mobile)) {
							if (mobile.length() == 11) {
								// 获取发送内容
								String content =  Constant.getMaintenanceProveContent(maintenanceInfo);
								// 发送信息类型（1：微信 2：短信 3：微信和短信）
								if(StringUtil.isEmpty(send_msg_type)){
									send_msg_type = "1";	// 默认发送微信
								}
								String code = "maintenanceProve1";
								if("0".equals(maintenanceInfo.getProve_type())){
									code = "maintenanceProve1";
								}else {
									code = "maintenanceProve2";
								}
								
								if("1".equals(send_msg_type)){
									messageService.sendMassageByConfig(request,maintenanceInfo.getId(), mobile,content, code, null, map1, null,
											"1",Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD);
								}else if("2".equals(send_msg_type)){
									messageService.sendMassageByConfig(request,maintenanceInfo.getId(), mobile,content, code, null, map1, null,
											"0",Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD);	
								}else if("3".equals(send_msg_type)){
									messageService.sendMassageByConfig(request,maintenanceInfo.getId(), mobile,content, code, null, map1, null,
											"2",Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD);
								}
							}else{
								return JsonWrapper.failureWrapperMsg("发送失败：手机号码不正确，请核实！");
							}
						}
					}
				}
			}
		}else if("3".equals(type)) {
			//4.2.1完成发送消息--有任务书时标记关联的任务书状态
			if (StringUtil.isNotEmpty(maintenanceInfo.getFk_func_task_id())) {
				FunctionTaskInfo functionTaskInfo = functionTaskInfoDao.get(maintenanceInfo.getFk_func_task_id());
				if(functionTaskInfo != null){
					if(!"99".equals(functionTaskInfo.getData_status())){
						// 5.1、保存更新任务
						functionTaskInfo.setData_status("4"); // 4：任务开发完成
						functionTaskInfo.setMdy_user_id(user.getId()); // 最后修改人ID
						functionTaskInfo.setMdy_user_name(user.getName()); // 最后修改人姓名
						functionTaskInfo.setMdy_date(
								DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", DateUtil.getCurrentDateTime())); // 最后修改时间
						functionTaskInfoDao.save(functionTaskInfo);

						// 5.2、记录系统建设台账完成日志
						logService.setLogs(functionTaskInfo.getId(), "完成任务书", "完成软件功能开发/修改任务书", user.getId(),
								user.getName(), new Date(), request.getRemoteAddr());

						// send_msg_type 0：不发送任何消息提醒
						if(!"0".equals(send_msg_type)){
							// 5.3、发送提醒消息给任务下达人
							if (StringUtil.isNotEmpty(functionTaskInfo.getCreate_user_id())) {
								Employee employee = employeesService.get(functionTaskInfo.getCreate_user_id());
								if (employee != null) {
									String mobile = employee.getMobileTel();
									if (StringUtil.isNotEmpty(mobile)) {
										if (mobile.length() == 11) {
											map1.put("advanceUser", maintenanceInfo.getCreate_user_name());
											// 获取发送内容
											String content = Constant.getMaintenanceNoticeContent(maintenanceInfo, "2", functionTaskInfo.getCreate_user_name());
											if ("1".equals(send_msg_type)) {
												messageService.sendMassageByConfig(request,employee.getId(), mobile,content, "maintenance2", null, map1, null,
														"1",Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD);
											} else if ("2".equals(send_msg_type)) {
												messageService.sendMassageByConfig(request,employee.getId(), mobile,content, "maintenance2", null, map1, null,
														"0",Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD);			
											} else if ("3".equals(send_msg_type)) {
												messageService.sendMassageByConfig(request,employee.getId(), mobile,content, "maintenance2", null, map1, null,
														"2",Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD);
											}
										} else {
											return JsonWrapper.failureWrapperMsg("发送失败：手机号码不正确，请核实！");
										}
									}
								}
							}
						}
					}
				}
			}else if(StringUtil.isEmpty(maintenanceInfo.getFk_func_task_id())) {//4.2.2完成发送消息--无任务书时只发送消息
				if (StringUtil.isNotEmpty(maintenanceInfo.getAdvance_user_id()) && !"0".equals(send_msg_type)) {
					//发送完成提醒消息给报告人（send_msg_type 0：不发送任何消息提醒）
					String[] userId = maintenanceInfo.getAdvance_user_id().split(",");
					for(String userIds:userId){  //循环人员ID
						// 获取手机号码
						Employee employee = employeesService.get(userIds);
						if(employee!=null) {
							String mobile = employee.getMobileTel();
							if (StringUtil.isNotEmpty(mobile)) {
								if (mobile.length() == 11) {
									// 获取发送内容
									String content =  Constant.getMaintenanceProveContent(maintenanceInfo);
									// 发送信息类型（1：微信 2：短信 3：微信和短信）
									if(StringUtil.isEmpty(send_msg_type)){
										send_msg_type = "1";	// 默认发送微信
									}
									String code = "maintenance2";
									if("1".equals(send_msg_type)){
										messageService.sendMassageByConfig(request,maintenanceInfo.getId(), mobile,content, code, null, map1, null,
												"1",Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD);
									}else if("2".equals(send_msg_type)){
										messageService.sendMassageByConfig(request,maintenanceInfo.getId(), mobile,content, code, null, map1, null,
												"0",Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD);	
									}else if("3".equals(send_msg_type)){
										messageService.sendMassageByConfig(request,maintenanceInfo.getId(), mobile,content, code, null, map1, null,
												"2",Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD);
									}
								}else{
									return JsonWrapper.failureWrapperMsg("发送失败：手机号码不正确，请核实！");
								}
							}
						}
					}
				}
			}
		}
		map.put("success", true);
		return map;
		
	}
	// 带附件保存
	public void saveInfo(HttpServletRequest request, MaintenanceInfo info, String uploadFiles) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		// 1、保存台账信息
		if (StringUtil.isNotEmpty(info.getOrg_id())) {
			Org org = orgManager.get(info.getOrg_id());
			info.setOrg_name(org.getOrgName());
		}else{
			info.setOrg_id(user.getDepartment().getId());
			info.setOrg_name(user.getDepartment().getOrgName());
		}	
		
		if (StringUtil.isNotEmpty(info.getAdvance_user_name())) {	// 报告人
			String user_id = employeesService.getEmployee(info.getOrg_id(), info.getAdvance_user_name());
			if(StringUtil.isNotEmpty(user_id)){
				info.setAdvance_user_id(user_id);
			}
		}
		if (info.getAdvance_date() == null) {
			info.setAdvance_date(new Date());	// 报告日期
		}
		
		if (StringUtil.isNotEmpty(info.getCreate_user_name())) {	// 记录人
			String user_id = employeesService.getUserID(info.getCreate_user_name());
			if(StringUtil.isNotEmpty(user_id)){
				info.setCreate_user_id(user_id);
			}
		}else{
			info.setCreate_user_id(user.getId()); 
			info.setCreate_user_name(user.getName());
		}
		if (info.getCreate_date() == null) {
			info.setCreate_date(new Date());	// 记录日期
		}
		
		if (StringUtil.isNotEmpty(info.getReceive_user_name())) {	// 受理人
			String user_id = employeesService.getUserID(info.getReceive_user_name());
			if(StringUtil.isNotEmpty(user_id)){
				info.setReceive_user_id(user_id);
			}
		}else{
			info.setReceive_user_id(user.getId()); 
			info.setReceive_user_name(user.getName());
		}
		if (info.getReceive_date() == null) {
			info.setReceive_date(new Date());	// 受理日期
		}
		
		if(StringUtil.isEmpty(info.getSn())){
			String pre_sn = TS_Util.getCurYearMonth();
			info.setSn(pre_sn + maintenanceInfoDao.queryNextSn(pre_sn));
		}
		
		// 保存既已受理
		info.setData_status("1");	// 1：已受理
		maintenanceInfoDao.save(info); 
		
		// 2、记录日志
		logService.setLogs(info.getId(), "业务受理", "系统建设台账业务受理", user.getId(), user
				.getName(), new Date(), request.getRemoteAddr());
		
		// 3、保存台账附件
		if (StringUtil.isNotEmpty(uploadFiles)) {
			String[] files = uploadFiles.replaceAll("/^,/", "").split(",");
			for (String file : files) {
				if (StringUtil.isNotEmpty(file)) {
					attachmentManager.setAttachmentBusinessId(file,
							info.getId(), "task"); 
				}
			}
		}
	}

	// 删除
	public void del(HttpServletRequest request, String id) {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		try {
			if (StringUtil.isNotEmpty(id)) {
				// 1、删除维护日志（系统建设台账）明细表（TZSB_MAINTENANCE_INFO）
				maintenanceInfoDao
							.createSQLQuery(
									"update TZSB_MAINTENANCE_INFO set data_status='99' where id = ? ",
									id).executeUpdate();
				// 2、写入日志
				logService.setLogs(id, "删除业务明细", "系统建设台账，删除业务明细",
							user.getId(), user.getName(), new Date(), request
									.getRemoteAddr());
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
	}
	
	// 受理需求
	public HashMap<String, Object> receives(HttpServletRequest request, MaintenanceInfoDTO entity)
		throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object>  map = new HashMap<String, Object>();	
		String ids = request.getParameter("ids");
		try{
				String temp[] = ids.split(",");
				for(int i=0;i<temp.length;i++){
					MaintenanceInfo maintenanceInfo =  maintenanceInfoDao.get(temp[i]);
					maintenanceInfo.setReceive_user_name(entity.getReceive_user_name());
					if(entity.getReceive_date() == null){
						maintenanceInfo.setReceive_date(new Date());
					}else{
						maintenanceInfo.setReceive_date(entity.getReceive_date());
					}
					// 数据状态（数据字典MAINTENANCE_DATA_STATUS，0：未受理 1：已受理 2：开发中 3：已完成 4：已发布 99：已删除）
					maintenanceInfo.setData_status("1");
					maintenanceInfo.setMdy_user_id(user.getId());		// 最后修改人ID
					maintenanceInfo.setMdy_user_name(user.getName());	// 最后修改人姓名
					maintenanceInfo.setMdy_date(new Date());			// 最后修改时间
					maintenanceInfoDao.save(maintenanceInfo);
					
					logService.setLogs(maintenanceInfo.getId(), "业务受理", "系统建设台账，业务受理", user.getId(), user
							.getName(), new Date(), request.getRemoteAddr());
				}
			map.put("success", true);
		 }catch(Exception e){
			 e.printStackTrace();
			 map.put("success", false);
			 map.put("msg", "请求超时，请稍后再试！");
		 }
		return map;
	}
	
	// 标记开发情况
	public HashMap<String, Object> develops(HttpServletRequest request, MaintenanceInfoDTO entity)
		throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object>  map = new HashMap<String, Object>();	
		String ids = request.getParameter("ids");
		try{
				String temp[] = ids.split(",");
				for(int i=0;i<temp.length;i++){
					MaintenanceInfo maintenanceInfo =  maintenanceInfoDao.get(temp[i]);
					maintenanceInfo.setDevelop_user_name(entity.getDevelop_user_name());
					if(entity.getDevelop_start_date() == null){
						maintenanceInfo.setDevelop_start_date(new Date());
					}else{
						maintenanceInfo.setDevelop_start_date(entity.getDevelop_start_date());
					}
					// 数据状态（数据字典MAINTENANCE_DATA_STATUS，0：未受理 1：已受理 2：开发中 3：已完成 4：已发布 99：已删除）
					maintenanceInfo.setData_status("2");
					maintenanceInfo.setMdy_user_id(user.getId());		// 最后修改人ID
					maintenanceInfo.setMdy_user_name(user.getName());	// 最后修改人姓名
					maintenanceInfo.setMdy_date(new Date());			// 最后修改时间
					maintenanceInfoDao.save(maintenanceInfo);
					
					logService.setLogs(maintenanceInfo.getId(), "标记开发", "系统建设台账，标记业务功能开发情况", user.getId(), user
							.getName(), new Date(), request.getRemoteAddr());
				}
			map.put("success", true);
		 }catch(Exception e){
			 e.printStackTrace();
			 map.put("success", false);
			 map.put("msg", "请求超时，请稍后再试！");
		 }
		return map;
	}
	
	// 标记完成
	public HashMap<String, Object> finishs(HttpServletRequest request, MaintenanceInfoDTO entity, String send_msg_type)
		throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object>  map = new HashMap<String, Object>();
		String ids = request.getParameter("ids");
		try{
				String temp[] = ids.split(",");
				for(int i=0;i<temp.length;i++){
					// 1、保存
					MaintenanceInfo maintenanceInfo =  maintenanceInfoDao.get(temp[i]);
					
					if(entity.getDevelop_end_date() == null){
						maintenanceInfo.setDevelop_end_date(new Date());
					}else{
						maintenanceInfo.setDevelop_end_date(entity.getDevelop_end_date());
					}
					maintenanceInfo.setDevelop_desc(entity.getDevelop_desc());	// 开发完成情况描述
					// 数据状态（数据字典MAINTENANCE_DATA_STATUS，0：未受理 1：已受理 2：开发中 3：已完成 4：已发布 99：已删除）
					maintenanceInfo.setData_status("3");
					maintenanceInfo.setMdy_user_id(user.getId());		// 最后修改人ID
					maintenanceInfo.setMdy_user_name(user.getName());	// 最后修改人姓名
					maintenanceInfo.setMdy_date(new Date());			// 最后修改时间
					maintenanceInfoDao.save(maintenanceInfo);
					

					HashMap<String, Object> map1 = new HashMap<String, Object>();
					map1.put("advanceUser", maintenanceInfo.getAdvance_user_name());
					map1.put("proDesc", "“"+(maintenanceInfo.getPro_desc().length()>128?maintenanceInfo.getPro_desc().substring(0,128)+"......":maintenanceInfo.getPro_desc())+"”");
					map1.put("finishDate", DateUtil.format(maintenanceInfo.getExpect_finish_date(), "yyyy-MM-dd"));
					map1.put("remark", maintenanceInfo.getProve_remark());
					
					
					// 2、发送提醒消息给报告人
					if(StringUtil.isNotEmpty(maintenanceInfo.getAdvance_user_id()) && !"0".equals(send_msg_type)){
						Employee employee = employeesService.get(maintenanceInfo.getAdvance_user_id());
						if(employee!=null){
							String mobile = employee.getMobileTel();
							if(StringUtil.isNotEmpty(mobile)){
								if(mobile.length()==11){
									// 获取发送内容
									String content =  Constant.getMaintenanceNoticeContent(maintenanceInfo, "1", "");
									map1.put("advanceUser", maintenanceInfo.getAdvance_user_name());
									
									
									if("1".equals(send_msg_type)){
										
										//发送消息
										messageService.sendMassageByConfig(request,employee.getId(), mobile,content, "maintenance1", null, map1, null,
												"1",Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD);
										
										/*// 发送微信
										messageService.sendWxMsg(request, employee.getId(), Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD, content, mobile);
									*/
									}else if("2".equals(send_msg_type)){
										
										//发送消息
										messageService.sendMassageByConfig(request,employee.getId(), mobile,content, "maintenance1", null, map1, null,
												"0",Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD);
										
									/*	// 发送短信
										messageService.sendMoMsg(request, employee.getId(), content, mobile);*/	
									}else if("3".equals(send_msg_type)){
										
										//发送消息
										messageService.sendMassageByConfig(request,employee.getId(), mobile,content, "maintenance1", null, map1, null,
												"2",Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD);
										
									/*	// 发送微信
										messageService.sendWxMsg(request, employee.getId(), Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD, content, mobile);	
										// 发送短信
										messageService.sendMoMsg(request, employee.getId(), content, mobile);*/	
									}
								}else{
									return JsonWrapper.failureWrapperMsg("发送失败：手机号码不正确，请核实！");
								}
							}else{
								return JsonWrapper.failureWrapperMsg("发送失败：系统暂未设置手机号码！请联系系统管理员！");
							}
						}
					}
					
					// 3、记录系统建设台账完成日志
					logService.setLogs(maintenanceInfo.getId(), "标记完成", "系统建设台账，标记业务功能完成情况", user.getId(), user
							.getName(), new Date(), request.getRemoteAddr());
					
					// 4、标记关联的任务书状态
					if(StringUtil.isNotEmpty(maintenanceInfo.getFk_func_task_id())){
						// 4.1、保存更新任务
						FunctionTaskInfo functionTaskInfo = functionTaskInfoDao.get(maintenanceInfo.getFk_func_task_id());
						functionTaskInfo.setData_status("4"); 	// 4：任务开发完成
						functionTaskInfo.setMdy_user_id(user.getId()); 		// 最后修改人ID
						functionTaskInfo.setMdy_user_name(user.getName()); 	// 最后修改人姓名
						functionTaskInfo.setMdy_date(
								DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", DateUtil.getCurrentDateTime())); // 最后修改时间
						functionTaskInfoDao.save(functionTaskInfo);
						
						// 4.2、记录系统建设台账完成日志
						logService.setLogs(functionTaskInfo.getId(), "完成任务书", "完成软件功能开发/修改任务书", user.getId(), user
								.getName(), new Date(), request.getRemoteAddr());
						
						// 4.3、发送提醒消息给任务下达人
						if (StringUtil.isNotEmpty(functionTaskInfo.getCreate_user_id())) {
							Employee employee = employeesService.get(functionTaskInfo.getCreate_user_id());
							if (employee != null) {
								String mobile = employee.getMobileTel();
								if (StringUtil.isNotEmpty(mobile)) {
									if (mobile.length() == 11) {
										map1.put("advanceUser", functionTaskInfo.getCreate_user_name());
										
										// 获取发送内容
										String content = Constant.getMaintenanceNoticeContent(maintenanceInfo, "2", functionTaskInfo.getCreate_user_name());
										if ("1".equals(send_msg_type)) {
											messageService.sendMassageByConfig(request,employee.getId(), mobile,content, "maintenance2", null, map1, null,
													"1",Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD);
											// 发送微信
											/*messageService.sendWxMsg(request, employee.getId(), Constant.INSPECTION_CORPID,
													Constant.INSPECTION_PWD, content, mobile);*/
										} else if ("2".equals(send_msg_type)) {
											
											messageService.sendMassageByConfig(request,employee.getId(), mobile,content, "maintenance2", null, map1, null,
													"0",Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD);
											// 发送短信
											//messageService.sendMoMsg(request, employee.getId(), content, mobile);				
										} else if ("3".equals(send_msg_type)) {
											
											messageService.sendMassageByConfig(request,employee.getId(), mobile,content, "maintenance2", null, map1, null,
													"2",Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD);
											
											/*// 发送微信
											messageService.sendWxMsg(request, employee.getId(), Constant.INSPECTION_CORPID,
													Constant.INSPECTION_PWD, content, mobile);
											// 发送短信
											messageService.sendMoMsg(request, employee.getId(), content, mobile);*/
										}
									} else {
										return JsonWrapper.failureWrapperMsg("发送失败：手机号码不正确，请核实！");
									}
								} else {
									return JsonWrapper.failureWrapperMsg("发送失败：系统暂未设置手机号码！请联系系统管理员！");
								}
							}
						}
					}
				}
			map.put("success", true);
		 }catch(Exception e){
			 e.printStackTrace();
			 map.put("success", false);
			 map.put("msg", "请求超时，请稍后再试！");
		 }
		return map;
	}
	
	// 标记论证
	public HashMap<String, Object> prove(HttpServletRequest request, MaintenanceInfoDTO entity) throws Exception {
		CurrentSessionUser cur_user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String ids = entity.getId();
		String send_msg_type = entity.getSend_msg_type();
		try {
			String temp[] = ids.split(",");
			for (int i = 0; i < temp.length; i++) {
				// 1、保存
				MaintenanceInfo maintenanceInfo = maintenanceInfoDao.get(temp[i]);
				
			
				maintenanceInfo.setProve_user_id(entity.getProve_user_id());
				maintenanceInfo.setProve_user_name(entity.getProve_user_name());
				maintenanceInfo.setProve_type(entity.getProve_type());
				maintenanceInfo.setProve_remark(entity.getProve_remark());
				maintenanceInfo.setExpect_finish_date(entity.getExpect_finish_date());
				if("1".equals(maintenanceInfo.getData_status())){
					maintenanceInfo.setData_status("2"); // 2：已论证
					maintenanceInfo.setReceive_user_id(cur_user.getId()); 		// 论证记录人ID
					maintenanceInfo.setReceive_user_name(cur_user.getName()); 	// 论证记录人姓名
					maintenanceInfo.setReceive_date(new Date()); 				// 论证记录时间
				}
				maintenanceInfo.setMdy_user_id(cur_user.getId()); 		// 最后修改人ID
				maintenanceInfo.setMdy_user_name(cur_user.getName()); 	// 最后修改人姓名
				maintenanceInfo.setMdy_date(new Date()); 				// 最后修改时间
				maintenanceInfoDao.save(maintenanceInfo);

				HashMap<String, Object> map1 = new HashMap<String, Object>();
				map1.put("advanceUser", maintenanceInfo.getAdvance_user_name());
				map1.put("proDesc", "“"+(maintenanceInfo.getPro_desc().length()>128?maintenanceInfo.getPro_desc().substring(0,128)+"......":maintenanceInfo.getPro_desc())+"”");
				map1.put("finishDate", DateUtil.format(maintenanceInfo.getExpect_finish_date(), "yyyy-MM-dd"));
				map1.put("remark", maintenanceInfo.getProve_remark());
				
				
				
				if (StringUtil.isNotEmpty(maintenanceInfo.getProve_user_id()) && !"0".equals(send_msg_type)) {
					// 2、发送提醒消息给论证人（send_msg_type 0：不发送任何消息提醒）
					String[] userId = maintenanceInfo.getProve_user_id().split(",");
					for(String userIds:userId){  //循环人员ID
						// 获取手机号码
						/*String mobile = employeesService.getMobileByUserId(userIds);
						Employee employee = employeesService.get(userIds);*/
						String user = reportErrorDao.gettol(userIds).toString();
						
						String mobile = Pattern.compile("\\b([\\w\\W])\\b").matcher(user.toString()
								.substring(1,user.toString().length()-1)).replaceAll("'$1'");
						if (StringUtil.isNotEmpty(mobile)) {
							if (mobile.length() == 11) {
								// 获取发送内容
								String content =  Constant.getMaintenanceProveContent(maintenanceInfo);
								// 发送信息类型（1：微信 2：短信 3：微信和短信）
								if(StringUtil.isEmpty(send_msg_type)){
									send_msg_type = "1";	// 默认发送微信
								}
								String code = "maintenanceProve1";
								if("0".equals(maintenanceInfo.getProve_type())){
									code = "maintenanceProve1";
								}else {
									code = "maintenanceProve2";
								}
								
								if("1".equals(send_msg_type)){
									
									messageService.sendMassageByConfig(request,maintenanceInfo.getId(), mobile,content, code, null, map1, null,
											"1",Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD);
									
									// 发送微信
									//messageService.sendWxMsg(request, maintenanceInfo.getId(), Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD, content, mobile);
								}else if("2".equals(send_msg_type)){
									messageService.sendMassageByConfig(request,maintenanceInfo.getId(), mobile,content, code, null, map1, null,
											"0",Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD);
								/*	// 发送短信
									messageService.sendMoMsg(request, maintenanceInfo.getId(), content, mobile);*/	
								}else if("3".equals(send_msg_type)){
									messageService.sendMassageByConfig(request,maintenanceInfo.getId(), mobile,content, code, null, map1, null,
											"2",Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD);
									/*// 发送微信
									 messageService.sendWxMsg(request, maintenanceInfo.getId(), Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD, content, mobile);	
									// 发送短信
									messageService.sendMoMsg(request, maintenanceInfo.getId(), content, mobile);	*/
								}
							} else {
								return JsonWrapper.failureWrapperMsg("发送失败：手机号码不正确，请核实！");
							}
						} else {
							return JsonWrapper.failureWrapperMsg("发送失败：系统暂未设置手机号码！请联系系统管理员！");
						}
					}
				}

				// 3、记录日志
				logService.setLogs(maintenanceInfo.getId(), "标记论证", "系统运行维护台账，标记论证情况", cur_user.getId(), cur_user.getName(),
						new Date(), request.getRemoteAddr());
			}
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "请求超时，请稍后再试！");
		}
		return map;
	}
	
	// 未标记开发直接标记完成
	public HashMap<String, Object> finishs2(HttpServletRequest request,
			MaintenanceInfoDTO entity, String uploadFiles) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String ids = entity.getId();
		String send_msg_type = entity.getSend_msg_type();
		try {
			String temp[] = ids.split(",");
			for (int i = 0; i < temp.length; i++) {
				// 1、保存
				MaintenanceInfo maintenanceInfo = maintenanceInfoDao.get(temp[i]);
				maintenanceInfo.setDevelop_user_name(entity.getDevelop_user_name());
				if (entity.getDevelop_start_date() == null) {
					maintenanceInfo.setDevelop_start_date(new Date());
				} else {
					maintenanceInfo.setDevelop_start_date(entity.getDevelop_start_date());
				}
				if (entity.getDevelop_end_date() == null) {
					maintenanceInfo.setDevelop_end_date(new Date());
				} else {
					maintenanceInfo.setDevelop_end_date(entity.getDevelop_end_date());
				}
				maintenanceInfo.setDevelop_desc(entity.getDevelop_desc()); // 开发完成情况描述
				maintenanceInfo.setData_status("3");	// 3：已完成
				
				maintenanceInfo.setTest_user_name(entity.getTest_user_name());
				maintenanceInfo.setTest_date(entity.getTest_date());
				
				maintenanceInfo.setMdy_user_id(user.getId()); // 最后修改人ID
				maintenanceInfo.setMdy_user_name(user.getName()); // 最后修改人姓名
				maintenanceInfo.setMdy_date(new Date()); // 最后修改时间

				
				if(StringUtil.isEmpty(maintenanceInfo.getSn())){
					String pre_sn = TS_Util.getCurYearMonth();
					maintenanceInfo.setSn(pre_sn + maintenanceInfoDao.queryNextSn(pre_sn));
				}
				
				maintenanceInfoDao.save(maintenanceInfo);


				HashMap<String, Object> map1 = new HashMap<String, Object>();
				map1.put("advanceUser", maintenanceInfo.getAdvance_user_name());
				map1.put("proDesc", "“"+(maintenanceInfo.getPro_desc().length()>128?maintenanceInfo.getPro_desc().substring(0,128)+"......":maintenanceInfo.getPro_desc())+"”");
				map1.put("finishDate", DateUtil.format(maintenanceInfo.getExpect_finish_date(), "yyyy-MM-dd"));
				map1.put("remark", maintenanceInfo.getProve_remark());
				
				
				// 2.2、保存更新记录附件
				if (StringUtil.isNotEmpty(uploadFiles)) {
					String[] files = uploadFiles.replaceAll("/^,/", "").split(",");
					for (String file : files) {
						if (StringUtil.isNotEmpty(file)) {
							attachmentManager.setAttachmentBusinessId(file,
									maintenanceInfo.getId(), "update"); 
						}
					}
				}
				
				// 3、发送提醒消息给报告人（send_msg_type 0：不发送任何消息提醒）
				if (StringUtil.isNotEmpty(maintenanceInfo.getAdvance_user_id()) && !"0".equals(send_msg_type)) {
					Employee employee = employeesService.get(maintenanceInfo.getAdvance_user_id());
					if (employee != null) {
						String mobile = employee.getMobileTel();
						if (StringUtil.isNotEmpty(mobile)) {
							if (mobile.length() == 11) {
								// 获取发送内容
								String content = Constant.getMaintenanceNoticeContent(maintenanceInfo, "1", "");

								if ("1".equals(send_msg_type)) {
									messageService.sendMassageByConfig(request,employee.getId(), mobile,content, "maintenance2", null, map1, null,
											"1",Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD);
									
									// 发送微信
									//messageService.sendWxMsg(request, employee.getId(), Constant.INSPECTION_CORPID,
									//		Constant.INSPECTION_PWD, content, mobile);
								} else if ("2".equals(send_msg_type)) {
									messageService.sendMassageByConfig(request,employee.getId(), mobile,content, "maintenance2", null, map1, null,
											"0",Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD);
									
									// 发送短信
									//messageService.sendMoMsg(request, employee.getId(), content, mobile);
								} else if ("3".equals(send_msg_type)) {
									messageService.sendMassageByConfig(request,employee.getId(), mobile,content, "maintenance2", null, map1, null,
											"2",Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD);
									/*
									// 发送微信
									messageService.sendWxMsg(request, employee.getId(), Constant.INSPECTION_CORPID,
											Constant.INSPECTION_PWD, content, mobile);
									// 发送短信
									messageService.sendMoMsg(request, employee.getId(), content, mobile);*/
								}
							} else {
								return JsonWrapper.failureWrapperMsg("发送失败：手机号码不正确，请核实！");
							}
						} else {
							return JsonWrapper.failureWrapperMsg("发送失败：系统暂未设置手机号码！请联系系统管理员！");
						}
					}
				}

				// 4、记录日志
				logService.setLogs(maintenanceInfo.getId(), "标记完成", "系统建设台账，标记业务功能完成情况", user.getId(), user.getName(),
						new Date(), request.getRemoteAddr());

				// 5、标记关联的任务书状态
				if (StringUtil.isNotEmpty(maintenanceInfo.getFk_func_task_id())) {
					FunctionTaskInfo functionTaskInfo = functionTaskInfoDao.get(maintenanceInfo.getFk_func_task_id());
					if(functionTaskInfo != null){
						if(!"99".equals(functionTaskInfo.getData_status())){
							// 4.1、保存更新任务
							functionTaskInfo.setData_status("4"); // 4：任务开发完成
							functionTaskInfo.setMdy_user_id(user.getId()); // 最后修改人ID
							functionTaskInfo.setMdy_user_name(user.getName()); // 最后修改人姓名
							functionTaskInfo.setMdy_date(
									DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", DateUtil.getCurrentDateTime())); // 最后修改时间
							functionTaskInfoDao.save(functionTaskInfo);

							// 5.2、记录系统建设台账完成日志
							logService.setLogs(functionTaskInfo.getId(), "完成任务书", "完成软件功能开发/修改任务书", user.getId(),
									user.getName(), new Date(), request.getRemoteAddr());

							// send_msg_type 0：不发送任何消息提醒
							if(!"0".equals(send_msg_type)){
								// 5.3、发送提醒消息给任务下达人
								if (StringUtil.isNotEmpty(functionTaskInfo.getCreate_user_id())) {
									Employee employee = employeesService.get(functionTaskInfo.getCreate_user_id());
									if (employee != null) {
										String mobile = employee.getMobileTel();
										if (StringUtil.isNotEmpty(mobile)) {
											if (mobile.length() == 11) {
												map1.put("advanceUser", maintenanceInfo.getCreate_user_name());
												// 获取发送内容
												String content = Constant.getMaintenanceNoticeContent(maintenanceInfo, "2", functionTaskInfo.getCreate_user_name());
												if ("1".equals(send_msg_type)) {
													
													messageService.sendMassageByConfig(request,employee.getId(), mobile,content, "maintenance2", null, map1, null,
															"1",Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD);
													
													// 发送微信
													/*messageService.sendWxMsg(request, employee.getId(), Constant.INSPECTION_CORPID,
															Constant.INSPECTION_PWD, content, mobile);*/
												} else if ("2".equals(send_msg_type)) {
													
													messageService.sendMassageByConfig(request,employee.getId(), mobile,content, "maintenance2", null, map1, null,
															"0",Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD);
													
													
													// 发送短信
													//messageService.sendMoMsg(request, employee.getId(), content, mobile);				
												} else if ("3".equals(send_msg_type)) {
													
													messageService.sendMassageByConfig(request,employee.getId(), mobile,content, "maintenance2", null, map1, null,
															"2",Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD);
													
													/*
													// 发送微信
													messageService.sendWxMsg(request, employee.getId(), Constant.INSPECTION_CORPID,
															Constant.INSPECTION_PWD, content, mobile);
													// 发送短信
													messageService.sendMoMsg(request, employee.getId(), content, mobile);*/
												}
											} else {
												return JsonWrapper.failureWrapperMsg("发送失败：手机号码不正确，请核实！");
											}
										} else {
											return JsonWrapper.failureWrapperMsg("发送失败：系统暂未设置手机号码！请联系系统管理员！");
										}
									}
								}
							}
						}
					}
				}
			}
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "请求超时，请稍后再试！");
		}
		return map;
	}
	
	// 发送临时消息
	public HashMap<String, Object> sendMsg(HttpServletRequest request, MaintenanceInfoDTO entity, String send_msg_type)
			throws Exception {
		//CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			// 发送临时消息（send_msg_type 0：不发送任何消息）
			if (!"0".equals(send_msg_type)) {
				String mobile = entity.getMobile();
				if (StringUtil.isNotEmpty(mobile)) {
					if (mobile.length() == 11) {
						// 获取发送内容
						String content = entity.getContent();
						if ("1".equals(send_msg_type)) {
							// 发送微信
							messageService.sendWxMsg(request, "", Constant.INSPECTION_CORPID,
									Constant.INSPECTION_PWD, content, mobile);
						} else if ("2".equals(send_msg_type)) {
							// 发送短信
							messageService.sendMoMsg(request, "", content, mobile);
						} else if ("3".equals(send_msg_type)) {
							// 发送微信
							messageService.sendWxMsg(request, "", Constant.INSPECTION_CORPID,
									Constant.INSPECTION_PWD, content, mobile);
							// 发送短信
							messageService.sendMoMsg(request, "", content, mobile);
						}
					} else {
						return JsonWrapper.failureWrapperMsg("发送失败：手机号码不正确，请核实！");
					}
				} else {
					return JsonWrapper.failureWrapperMsg("发送失败：系统暂未设置手机号码！请联系系统管理员！");
				}
			}
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "请求超时，请稍后再试！");
		}
		return map;
	}
	
	// 发布
	public HashMap<String, Object> publishs(HttpServletRequest request)
		throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object>  map = new HashMap<String, Object>();
		String ids = request.getParameter("ids");
		try{
				String temp[] = ids.split(",");
				for(int i=0;i<temp.length;i++){
					MaintenanceInfo maintenanceInfo =  maintenanceInfoDao.get(temp[i]);
					maintenanceInfo.setPublish_user_id(user.getId());
					maintenanceInfo.setPublish_user_name(user.getName());
					maintenanceInfo.setPublish_date(new Date());
					// 数据状态（数据字典MAINTENANCE_DATA_STATUS，0：未受理 1：已受理 2：开发中 3：已完成 4：已发布 99：已删除）
					maintenanceInfo.setData_status("4");
					maintenanceInfo.setMdy_user_id(user.getId());		// 最后修改人ID
					maintenanceInfo.setMdy_user_name(user.getName());	// 最后修改人姓名
					maintenanceInfo.setMdy_date(new Date());			// 最后修改时间
					maintenanceInfoDao.save(maintenanceInfo);
					
					logService.setLogs(maintenanceInfo.getId(), "发布功能", "系统建设台账，发布业务功能", user.getId(), user
							.getName(), new Date(), request.getRemoteAddr());
				}
			map.put("success", true);
		 }catch(Exception e){
			 e.printStackTrace();
			 map.put("success", false);
			 map.put("msg", "请求超时，请稍后再试！");
		 }
		return map;
	}
	
	// 确认
	public HashMap<String, Object> confirms(HttpServletRequest request) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String ids = request.getParameter("ids");
		try {
			String temp[] = ids.split(",");
			for (int i = 0; i < temp.length; i++) {
				MaintenanceInfo maintenanceInfo =  maintenanceInfoDao.get(temp[i]);
				maintenanceInfo.setConfirm_user_id(user.getId());
				maintenanceInfo.setConfirm_user_name(user.getName());
				maintenanceInfo.setConfirm_date(new Date());
				maintenanceInfo.setData_status("5");	// 5：已确认
				maintenanceInfo.setMdy_user_id(user.getId());		// 最后修改人ID
				maintenanceInfo.setMdy_user_name(user.getName());	// 最后修改人姓名
				maintenanceInfo.setMdy_date(new Date());			// 最后修改时间
				maintenanceInfoDao.save(maintenanceInfo);
				
				logService.setLogs(maintenanceInfo.getId(), "确认完成", "确认功能已完成", user.getId(), user
						.getName(), new Date(), request.getRemoteAddr());
			}
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "请求超时，请稍后再试！");
		}
		return map;
	}
	
	// 获取建设台账业务流转情况
	@SuppressWarnings("unchecked")
	public HashMap<String, Object>  getFlowStep(String id) throws Exception{	
		HashMap<String, Object> map = new HashMap<String, Object>();		
		List<SysLog> list = maintenanceInfoDao.createQuery("  from SysLog where business_id ='"+id+"' order by op_time,id asc").list();
		map.put("flowStep", list);
		map.put("size", list.size());
		//map.put("sn", maintenanceInfoDao.get(id).getSn());
		map.put("success", true);
		return map;
    }
	
	// 获取业务流水号后三位序号
	public String queryNextSn(String sn_pre){
		return maintenanceInfoDao.queryNextSn(sn_pre);
	}
	
	
}
