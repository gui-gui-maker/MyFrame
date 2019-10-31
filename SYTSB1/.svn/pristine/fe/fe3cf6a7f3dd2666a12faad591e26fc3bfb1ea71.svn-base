package com.scts.maintenance.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.rbac.bean.Org;
import com.khnt.rbac.impl.manager.OrgManagerImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.employee.service.EmployeesService;
import com.lsts.log.service.SysLogService;
import com.scts.maintenance.bean.Maintenance;
import com.scts.maintenance.bean.MaintenanceInfo;
import com.scts.maintenance.dao.MaintenanceDao;
import com.scts.maintenance.dao.MaintenanceInfoDao;

/**
 * 系统更新维护日志（系统建设台账）业务逻辑对象
 * @ClassName MaintenanceService
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-09-08 下午01:43:00
 */
@Service("maintenanceService")
public class MaintenanceService extends
		EntityManageImpl<Maintenance, MaintenanceDao> {
	@Autowired
	private MaintenanceDao maintenanceDao;
	@Autowired
	private MaintenanceInfoDao maintenanceInfoDao;
	@Autowired
	private OrgManagerImpl orgManager;
	@Autowired
	private EmployeesService employeesService;
	@Autowired
	private SysLogService logService;
	
	/**
	 * 
	 * 保存维护日志（系统建设台账）信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Maintenance saveBasic(Maintenance maintenance,
			HttpServletRequest request) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		try {
			for (MaintenanceInfo info : maintenance.getMaintenanceInfo()) {
				if (StringUtil.isNotEmpty(info.getOrg_id())) {
					Org org = orgManager.get(info.getOrg_id());
					info.setOrg_name(org.getOrgName());
				}else{
					info.setOrg_id(user.getDepartment().getId());
					info.setOrg_name(user.getDepartment().getOrgName());
				}	
				
				String advance_user_name = info.getAdvance_user_name();
				if (StringUtil.isNotEmpty(advance_user_name)) {	// 报告人
					String user_id = employeesService.getEmployee(info.getOrg_id(), advance_user_name.trim());
					if(StringUtil.isNotEmpty(user_id)){
						info.setAdvance_user_id(user_id);
					}
					info.setAdvance_user_name(advance_user_name.trim());
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
				
				// 保存既已受理
				info.setData_status("1");	// 数据状态（0：未受理 1：已受理 2：开发中 3：已完成 4：已发布 99：已删除）
				info.setMaintenance(maintenance);
			}
			maintenance.setCreate_user_id(user.getId()); // 此处不用到电子签名，所以不使用employee
			maintenance.setCreate_user_name(user.getName());
			maintenance.setCreate_date(new Date());	
			// 保存既已受理
			maintenance.setData_status("1");	// 数据状态（0：未受理 1：已受理 2：开发中 3：已完成 4：已发布 99：已删除）
			maintenanceDao.save(maintenance);

			logService.setLogs(maintenance.getId(), "业务受理", "系统建设台账业务受理", user.getId(), user
					.getName(), new Date(), request.getRemoteAddr());
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
		return maintenance;
	}
	
	/**
	 * 获取维护日志（系统建设台账）信息
	 * 
	 * @param id --
	 *            维护日志（系统建设台账）主表ID
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getDetail(String id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			// 查询维护日志（系统建设台账）主表
			Maintenance maintenance = maintenanceDao.get(id);
			// 查询维护日志（系统建设台账）明细表
			List<MaintenanceInfo> list = maintenanceInfoDao
					.getInfos(id);
			map.put("data", maintenance);
			map.put("maintenanceInfo", list);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
		return map;
	}

	// 删除
	public void del(HttpServletRequest request, String id) {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		try {
			// 查询维护日志（系统建设台账）明细表
			List<MaintenanceInfo> list = maintenanceInfoDao
					.getInfos(id);
			if (!list.isEmpty()) {
				for (MaintenanceInfo maintenanceInfo : list) {
					// 1、删除维护日志（系统建设台账）明细表（TZSB_MAINTENANCE_INFO）
					maintenanceInfoDao
							.createSQLQuery(
									"update TZSB_MAINTENANCE_INFO set data_status='99' where id = ? ",
									maintenanceInfo.getId()).executeUpdate();
					// 写入日志
					logService.setLogs(maintenanceInfo
							.getId(), "删除业务明细", "系统建设台账，删除业务明细",
							user.getId(), user.getName(), new Date(), request
									.getRemoteAddr());
				}
			}
			// 2、删除维护日志（系统建设台账）主表（TZSB_MAINTENANCE）
			maintenanceInfoDao
					.createSQLQuery(
							"update TZSB_MAINTENANCE set data_status='99' where id = ? ",
							id).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
	}
}
