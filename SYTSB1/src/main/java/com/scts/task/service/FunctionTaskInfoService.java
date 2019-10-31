package com.scts.task.service;

import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.base.Factory;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.Org;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;
import com.lsts.employee.service.EmployeesService;
import com.lsts.log.bean.SysLog;
import com.lsts.log.service.SysLogService;
import com.scts.maintenance.bean.MaintenanceInfo;
import com.scts.maintenance.dao.MaintenanceInfoDao;
import com.scts.task.bean.FunctionTaskInfo;
import com.scts.task.bean.FunctionTaskInfoDTO;
import com.scts.task.dao.FunctionTaskInfoDao;

import net.sf.json.JSONObject;
import util.TS_Util;

/**
 * 检验和质量管理软件功能开发（修改）任务书业务逻辑对象
 * @ClassName FunctionTaskInfoService
 * @JDK 1.7
 * @author GaoYa
 * @date 2016-11-22 15:55:00
 */
@Service("functionTaskInfoService")
public class FunctionTaskInfoService extends
		EntityManageImpl<FunctionTaskInfo, FunctionTaskInfoDao> {
	@Autowired
	private FunctionTaskInfoDao functionTaskInfoDao;
	@Autowired
	private SysLogService logService;
	@Autowired
	private AttachmentManager attachmentManager;
	@Autowired
	private EmployeesService employeesService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private MaintenanceInfoDao maintenanceInfoDao;
	
	private static Connection conn = null;  // 数据库连接

	// 保存
	public void saveInfo(HttpServletRequest request, FunctionTaskInfo info, String uploadFiles, String status) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User) curUser.getSysUser();
		Employee emp = (Employee) user.getEmployee();
		Org org = (Org) curUser.getDepartment();

		// 1、保存
		if (StringUtil.isEmpty(info.getSn())) {
			String sn_pre = TS_Util.getCurYear() + "-";
			info.setSn(sn_pre + queryNextSn(sn_pre));
		}

		if (StringUtil.isEmpty(info.getAdvance_org_id())) {
			info.setAdvance_org_id(org.getId());
			info.setAdvance_org_name(org.getOrgName());
		}
		if (StringUtil.isEmpty(info.getAdvance_user_id())) {
			info.setAdvance_user_id(emp.getId());
			info.setAdvance_user_name(emp.getName());
		}
		if (info.getAdvance_date() == null) {
			info.setAdvance_date(DateUtil.convertStringToDate("yyyy-MM-dd", DateUtil.getCurrentDateTime()));
		}

		info.setCreate_org_id(org.getId());
		info.setCreate_org_name(org.getOrgName());
		info.setCreate_user_id(emp.getId()); // 此处使用emp，接收微信提醒消息
		info.setCreate_user_name(emp.getName());
		info.setCreate_date(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", DateUtil.getCurrentDateTime()));

		if("add".equals(status)){
			info.setData_status("8"); // 数据状态（8：未提交）
		}
		functionTaskInfoDao.save(info);

		// 2、保存附件
		if (StringUtil.isNotEmpty(uploadFiles)) {
			String[] files = uploadFiles.replaceAll("/^,/", "").split(",");
			for (String file : files) {
				if (StringUtil.isNotEmpty(file)) {
					attachmentManager.setAttachmentBusinessId(file, info.getId());
				}
			}
		}

		// 3、发送微信提醒用户签发（暂不提醒，免打扰）
		String status_text = "保存";
		if("modify".equals(status)){
			status_text = "修改";
		}
		// 4、记录日志
		logService.setLogs(info.getId(), status_text+"任务书", status_text+"软件功能开发/修改任务书", user.getId(), user.getName(), new Date(),
				request.getRemoteAddr());
	}

	// 签发
	public HashMap<String, Object> issues(HttpServletRequest request) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User) curUser.getSysUser();
		Employee emp = (Employee) user.getEmployee();
		//Org org = (Org) curUser.getDepartment();

		HashMap<String, Object> map = new HashMap<String, Object>();
		String ids = request.getParameter("ids");
		try {
			String temp[] = ids.split(",");
			for (int i = 0; i < temp.length; i++) {
				// 1、保存签发
				FunctionTaskInfo info = functionTaskInfoDao.get(temp[i]);
				info.setIssue_user_id(emp.getId());
				info.setIssue_user_name(emp.getName());
				info.setIssue_date(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", DateUtil.getCurrentDateTime()));
				info.setData_status("1"); // 1：任务已签发
				info.setMdy_user_id(user.getId()); // 最后修改人ID
				info.setMdy_user_name(user.getName()); // 最后修改人姓名
				info.setMdy_date(new Date()); // 最后修改时间
				functionTaskInfoDao.save(info);

				// 2、记录日志
				logService.setLogs(info.getId(), "签发任务书", "签发软件功能开发/修改任务书", user.getId(), user.getName(), new Date(),
						request.getRemoteAddr());

				// 3、发送微信提醒用户接收
				// 发送提醒消息给信息中心相关负责人（默认接收人：张展彬）
				String emp_id = Factory.getSysPara().getProperty("FUNCTION_TASK_RECEIVE_EMP_ID");
				String[] emp_ids = null;
				if (StringUtil.isNotEmpty(emp_id)) {
					emp_ids = emp_id.split(",");
				} else {
					emp_ids = Constant.FUNCTION_TASK_RECEIVE_EMP_ID.split(",");
				}
				for (int j = 0; j < emp_ids.length; j++) {
					Employee employee = employeesService.get(emp_ids[j]);
					// 获取发送目标号码
					String destNumber = employee.getMobileTel();
					// 获取发送内容
					String content = Constant.getFuncTaskReceiveNoticeContent(info.getCreate_org_name(),
							info.getCreate_user_name(), info.getSn());
					// 发送微信
					messageService.sendWxMsg(request, info.getId(), Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD,
							content, destNumber);
					// 发送短信
					// messageService.sendMoMsg(request, info.getId(), content,
					// destNumber);
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
	
	// 提交（下达任务）
	public HashMap<String, Object> commits(HttpServletRequest request) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User) curUser.getSysUser();
		// Employee emp = (Employee) user.getEmployee();
		// Org org = (Org) curUser.getDepartment();

		HashMap<String, Object> map = new HashMap<String, Object>();
		String ids = request.getParameter("ids");
		try {
			String temp[] = ids.split(",");
			for (int i = 0; i < temp.length; i++) {
				// 1、保存
				FunctionTaskInfo info = functionTaskInfoDao.get(temp[i]);
				info.setData_status("0"); 				// 0：任务下达
				info.setMdy_user_id(user.getId()); 		// 最后修改人ID
				info.setMdy_user_name(user.getName()); 	// 最后修改人姓名
				info.setMdy_date(new Date()); 			// 最后修改时间
				functionTaskInfoDao.save(info);

				// 2、记录日志
				logService.setLogs(info.getId(), "提交任务书", "提交软件功能开发/修改任务书", user.getId(), user.getName(), new Date(),
						request.getRemoteAddr());
			}
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "请求超时，请稍后再试！");
		}
		return map;
	}
	
	// 接收
	public HashMap<String, Object> receives(HttpServletRequest request) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User) curUser.getSysUser();
		Employee emp = (Employee) user.getEmployee();
		Org org = (Org) curUser.getDepartment();

		HashMap<String, Object> map = new HashMap<String, Object>();
		String ids = request.getParameter("ids");
		try {
			String temp[] = ids.split(",");
			for (int i = 0; i < temp.length; i++) {
				// 1、保存接收
				FunctionTaskInfo info = functionTaskInfoDao.get(temp[i]);
				info.setReceive_org_id(org.getId());
				info.setReceive_org_name(org.getOrgName());
				info.setReceive_user_id(emp.getId());
				info.setReceive_user_name(emp.getName());
				info.setReceive_date(
						DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", DateUtil.getCurrentDateTime()));
				info.setData_status("2"); 		// 2：任务已接收
				info.setMdy_user_id(user.getId()); 		// 最后修改人ID
				info.setMdy_user_name(user.getName()); 	// 最后修改人姓名
				info.setMdy_date(new Date()); 			// 最后修改时间
				functionTaskInfoDao.save(info);

				// 2、接收任务后，自动生成系统建设台账
				MaintenanceInfo maintenanceInfo = new MaintenanceInfo();
				if(StringUtil.isEmpty(maintenanceInfo.getSn())){
					String pre_sn = TS_Util.getCurYearMonth();
					maintenanceInfo.setSn(pre_sn + maintenanceInfoDao.queryNextSn(pre_sn));
				}
				maintenanceInfo.setFk_func_task_id(temp[i]);
				maintenanceInfo.setInfo_type(info.getTask_type());
				maintenanceInfo.setPriority(info.getTask_priority());
				maintenanceInfo.setFunc_name(info.getTask_category());
				
				String pro_desc = "";
				if(StringUtil.isNotEmpty(info.getTask_name())){
					pro_desc = info.getTask_name();
				}
				if(StringUtil.isNotEmpty(info.getTask_requirement())){
					if(StringUtil.isNotEmpty(pro_desc)){
						pro_desc += "（";
					}
					pro_desc += info.getTask_requirement();
					if(pro_desc.contains("（")){
						pro_desc += "）";
					}
				}
				maintenanceInfo.setPro_desc(pro_desc);
				maintenanceInfo.setAdvance_user_id(info.getAdvance_user_id());
				maintenanceInfo.setAdvance_user_name(info.getAdvance_user_name());
				maintenanceInfo.setOrg_id(info.getAdvance_org_id());
				maintenanceInfo.setOrg_name(info.getAdvance_org_name());
				maintenanceInfo.setAdvance_date(info.getAdvance_date());
				maintenanceInfo.setCreate_user_id(Constant.FUNCTION_TASK_RECEIVE_EMP_ID);
				maintenanceInfo.setCreate_user_name(Constant.FUNCTION_TASK_RECEIVE_EMP_NAME);
				maintenanceInfo.setCreate_date(
						DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", DateUtil.getCurrentDateTime()));
				maintenanceInfo.setReceive_user_id(emp.getId());
				maintenanceInfo.setReceive_user_name(emp.getName());
				maintenanceInfo.setReceive_date(
						DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", DateUtil.getCurrentDateTime()));
				maintenanceInfo.setMdy_user_id(user.getId()); 		// 最后修改人ID
				maintenanceInfo.setMdy_user_name(user.getName()); 	// 最后修改人姓名
				maintenanceInfo.setMdy_date(
						DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", DateUtil.getCurrentDateTime())); // 最后修改时间
				maintenanceInfo.setData_status("1"); // 1：已受理
				
				maintenanceInfo.setExpect_finish_date(info.getExpect_finish_date());//预计完成日期
				maintenanceInfoDao.save(maintenanceInfo);
				
				// 3、处理附件（将附件自动生成到运维日志里）
				// 2017-08-25张展彬提出增加附件处理
				List<Attachment> list = attachmentManager.getBusAttachment(temp[i]);
				for(Attachment atta : list){
					Attachment attachment = new Attachment();
					BeanUtils.copyProperties(atta, attachment);
					attachment.setId(null);
					attachment.setTz_category("task");
					attachment.setBusinessId(maintenanceInfo.getId());
					attachmentManager.save(attachment);
				}

				// 4、记录日志
				logService.setLogs(info.getId(), "接收任务书", "接收软件功能开发/修改任务书", user.getId(), user.getName(), new Date(),
						request.getRemoteAddr());
			}
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "请求超时，请稍后再试！");
		}
		return map;
	}
	
	// 退回
	public HashMap<String, Object> backs(HttpServletRequest request, String dataStr) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User) curUser.getSysUser();

		HashMap<String, Object> map = new HashMap<String, Object>();
		
		JSONObject dataMap = JSONObject.fromString(dataStr);
		String ids = request.getParameter("ids");
		String remarks = dataMap.get("remarks").toString();
		try {
			String temp[] = ids.split(",");
			for (int i = 0; i < temp.length; i++) {
				// 1、退回保存
				FunctionTaskInfo info = functionTaskInfoDao.get(temp[i]);
				info.setData_status("9"); // 9：任务退回
				info.setRemarks(remarks);
				info.setMdy_user_id(user.getId()); 		// 最后修改人ID
				info.setMdy_user_name(user.getName()); 	// 最后修改人姓名
				info.setMdy_date(new Date()); 			// 最后修改时间
				functionTaskInfoDao.save(info);
				
				// 2、记录日志
				logService.setLogs(info.getId(), "退回任务书", remarks, user.getId(), user.getName(), new Date(),
						request.getRemoteAddr());
				
				// 3、发送微信提醒
				Employee employee = employeesService.get(info.getCreate_user_id());
				// 获取发送目标号码
				String destNumber = employee.getMobileTel();
				// 获取发送内容
				String content = Constant.getFuncTaskBackNoticeContent(user.getName(), info.getSn());
				// 发送微信
				messageService.sendWxMsg(request, info.getId(), Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD,
						content, destNumber);	
			}
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "请求超时，请稍后再试！");
		}
		return map;
	}
	
	// 确认完成
	public HashMap<String, Object> finishs(HttpServletRequest request, FunctionTaskInfoDTO entity)
			throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String ids = request.getParameter("ids");
		try {
			String temp[] = ids.split(",");
			for (int i = 0; i < temp.length; i++) {
				FunctionTaskInfo info = functionTaskInfoDao.get(temp[i]);
				info.setZlb_desc(entity.getZlb_desc());
				info.setZlb_remark(entity.getZlb_remark());
				info.setZlb_user_id(user.getId());
				info.setZlb_user_name(user.getName());
				info.setDevelop_end_date(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", DateUtil.getCurrentDateTime()));
				info.setMdy_user_id(user.getId()); 		// 最后修改人ID
				info.setMdy_user_name(user.getName()); 	// 最后修改人姓名
				info.setMdy_date(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", DateUtil.getCurrentDateTime())); 			// 最后修改时间
				info.setData_status("7");	// 7：任务确认完成
				
				functionTaskInfoDao.save(info);

				logService.setLogs(temp[i], "确认完成任务书", "确认完成软件功能开发/修改任务书", user.getId(), user.getName(),
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
	
	// 删除
	public void del(HttpServletRequest request, String id) {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		try {
			if (StringUtil.isNotEmpty(id)) {
				// 1、删除
				functionTaskInfoDao.createSQLQuery("update FUNCTION_TASK_INFO set data_status='99' where id = ? ", id)
						.executeUpdate();
				// 2、写入日志
				logService.setLogs(id, "任务书作废", "任务书作废", user.getId(), user.getName(), new Date(),
						request.getRemoteAddr());
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
	}

	// 获取流转情况
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> getFlowStep(String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<SysLog> list = functionTaskInfoDao
				.createQuery(" from SysLog where business_id ='" + id + "' order by op_time,id asc").list();
		map.put("flowStep", list);
		map.put("size", list.size());
		map.put("sn", functionTaskInfoDao.get(id).getSn());
		map.put("success", true);
		return map;
	}
	
	// 获取任务书编号后三位序号
	public String queryNextSn(String sn_pre){
		return functionTaskInfoDao.queryNextSn(sn_pre);
	}

	// 获取数据库连接
    public Connection getConn() {
        try {
            conn = Factory.getDB().getConnetion();
        } catch (Exception e) {
        	log.error("获取数据库连接失败：" + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

    // 关闭连接
    public void closeConn() {
        try {
            /*if (null != rs)
                rs.close();
            if (null != pstmt)
                pstmt.close();
            if (null != conn)
                conn.close();*/
        	Factory.getDB().freeConnetion(conn);	// 释放连接
        } catch (Exception e) {
        	log.error("关闭数据库连接错误：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
