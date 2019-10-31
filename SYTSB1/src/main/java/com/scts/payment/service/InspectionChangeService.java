package com.scts.payment.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.base.Factory;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;
import com.lsts.employee.service.EmployeesService;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.inspection.dao.InspectionInfoDao;
import com.lsts.log.service.SysLogService;
import com.scts.payment.bean.InspectionChange;
import com.scts.payment.bean.InspectionChangeMoney;
import com.scts.payment.bean.InspectionChangeMoneyDTO;
import com.scts.payment.dao.InspectionChangeDao;
import com.scts.payment.dao.InspectionChangeMoneyDao;

/**
 * 金额修改审批流程主表业务逻辑对象
 * @ClassName InspectionChangeService
 * @JDK 1.7
 * @author GaoYa
 * @date 2016-11-16 下午03:25:00
 */
@Service("inspectionChangeService")
public class InspectionChangeService extends
		EntityManageImpl<InspectionChange, InspectionChangeDao> {
	@Autowired
	private InspectionChangeDao inspectionChangeDao;
	@Autowired
	private InspectionChangeMoneyDao inspectionChangeMoneyDao;
	@Autowired
	private EmployeesService employeesService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private SysLogService logService;
	@Autowired
	private InspectionInfoDao infoDao;
	
	/**
	 * 
	 * 保存金额修改申请
	 * 
	 * @return
	 * @throws Exception
	 */
	public void saveBasic(InspectionChange inspectionChange,
			HttpServletRequest request) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User) curUser.getSysUser();
		Employee emp = (Employee) user.getEmployee();
		try {
			for (InspectionChangeMoney info : inspectionChange.getChangeMoneyInfo()) {
				// 1、保存金额修改明细业务
				if (StringUtil.isEmpty(info.getCreate_emp_id())) {
					info.setCreate_emp_id(emp.getId());
					info.setCreate_emp_name(emp.getName());
				}
				if (info.getCreate_date() == null) {
					info.setCreate_date(
							DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", DateUtil.getCurrentDateTime())); // 申请时间
				}
				info.setData_status("0"); // 数据状态（0：未审核 1：审核通过 2：审核不通过 99：已作废）
				info.setMdy_emp_id(emp.getId());
				info.setMdy_emp_name(emp.getName());
				info.setMdy_date(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", DateUtil.getCurrentDateTime()));
				info.setInspectionChange(inspectionChange);
				
				// 2、记录报告业务日志
				logService.setLogs(info.getFk_inspection_info_id(), "提交金额修改申请", "提交金额修改申请", curUser.getId(),
						curUser.getName(), new Date(), request.getRemoteAddr());
				
				// 3、发送提醒消息给审核人员（默认审核人：孙宇艺和雷兰）
				String emp_id = Factory.getSysPara().getProperty("CHANGE_MONEY_CHK_EMP_ID");
				String[] emp_ids = null;
				if(StringUtil.isNotEmpty(emp_id)){
					emp_ids = emp_id.split(",");
				}else{
					emp_ids = Constant.CHANGE_MONEY_CHK_EMP_ID;
				}			
				for(int i=0; i<emp_ids.length; i++){
					Employee employee = employeesService.get(emp_ids[i]);
					// 获取发送目标号码
					String destNumber = employee.getMobileTel();
					// 获取发送内容
					String content = Constant.getChangeMoneyNoticeContent(info.getCreate_emp_name(),
							info.getReport_com_name(), info.getReport_sn());

					// 发送微信
					messageService.sendWxMsg(request, info.getId(), Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD,
							content, destNumber);
					// 发送短信
					//messageService.sendMoMsg(request, info.getId(), content, destNumber);
				}
			}
			
			// 4、保存修改金额申请主表
			inspectionChange.setCreate_emp_id(emp.getId()); // 此处不用到电子签名，所以不使用employee
			inspectionChange.setCreate_emp_name(emp.getName());
			inspectionChange
					.setCreate_date(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", DateUtil.getCurrentDateTime()));
			inspectionChange.setMdy_emp_id(emp.getId());
			inspectionChange.setMdy_emp_name(emp.getName());
			inspectionChange
					.setMdy_date(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", DateUtil.getCurrentDateTime()));
			inspectionChange.setData_status("0"); // 数据状态（0：未审核 1：审核通过 2：审核不通过 99：已作废）
			inspectionChangeDao.save(inspectionChange);

			logService.setLogs(inspectionChange.getId(), "提交金额修改申请", "提交金额修改申请", curUser.getId(), curUser.getName(),
					new Date(), request.getRemoteAddr());
			// 4.1、记录发送消息提醒日志
			logService.setLogs(inspectionChange.getId(), "发送审核提醒", "发送给孙宇艺和雷兰", curUser.getId(),
					curUser.getName(), new Date(), request.getRemoteAddr());
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
	}
	
	/**
	 * 获取报告明细列表信息
	 * 
	 * @param info_ids --
	 *            报告业务ID集合
	 * @return
	 * @throws Exception
	 */
	public List<InspectionChangeMoneyDTO> getReportInfos(String info_ids) {
		List<InspectionChangeMoneyDTO> list = new ArrayList<InspectionChangeMoneyDTO>();
		try {
			//20190326优化效率
			String idss = info_ids.replace(",", "','");
			String sql = "select i.id,\n" +
							"      i.report_com_name,\n" + 
							"      i.report_sn,\n" + 
							"      i.advance_fees\n" + 
							"  from tzsb_inspection_info i where i.id in ('"+idss+"')";
			
			List<Object> infoList = infoDao.createSQLQuery(sql).list();
			for (int i = 0; i < infoList.size(); i++) {
				Object[] obj = (Object[]) infoList.get(i);
				InspectionChangeMoneyDTO changeMoneyDTO = new InspectionChangeMoneyDTO();
				changeMoneyDTO.setFk_inspection_info_id(obj[0] == null ? "" : obj[0].toString());
				changeMoneyDTO.setReport_com_name(obj[1] == null ? "" : obj[1].toString());
				changeMoneyDTO.setReport_sn(obj[2] == null ? "" : obj[2].toString());
				changeMoneyDTO.setAdvance_fees(obj[3] == null ? 0 : Double.parseDouble(obj[3].toString()));
				list.add(changeMoneyDTO);
			}
			
			/*String[] ids = info_ids.split(",");
			for (int i = 0; i < ids.length; i++) {
				InspectionInfo inspectionInfo = infoDao.get(ids[i]);
				if(inspectionInfo != null){
					InspectionChangeMoneyDTO changeMoneyDTO = new InspectionChangeMoneyDTO();
					changeMoneyDTO.setFk_inspection_info_id(inspectionInfo.getId());
					changeMoneyDTO.setReport_com_name(inspectionInfo.getReport_com_name());
					changeMoneyDTO.setReport_sn(inspectionInfo.getReport_sn());
					changeMoneyDTO.setAdvance_fees(inspectionInfo.getAdvance_fees());
					list.add(changeMoneyDTO);
				}
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
		return list;
	}

	// 删除
	public void del(HttpServletRequest request, String id) {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		try {
			// 查询金额修改审批流程明细表
			List<InspectionChangeMoney> list = inspectionChangeMoneyDao
					.getInfos(id);
			if (!list.isEmpty()) {
				for (InspectionChangeMoney info : list) {
					// 1、删除维护日志（系统建设台账）明细表（TZSB_INSPECTION_CHANGE_MONEY）
					inspectionChangeMoneyDao
							.createSQLQuery(
									"update TZSB_INSPECTION_CHANGE_MONEY set data_status='99' where id = ? ",
									info.getId()).executeUpdate();
					// 写入日志
					logService.setLogs(info.getId(), "删除申请", "删除修改金额申请",
							user.getId(), user.getName(), new Date(), request
									.getRemoteAddr());
					logService.setLogs(info.getFk_inspection_info_id(), "删除申请", "删除修改金额申请",
							user.getId(), user.getName(), new Date(), request
									.getRemoteAddr());
				}
			}
			// 2、删除金额修改审批流程主表（TZSB_INSPECTION_CHANGE）
			inspectionChangeMoneyDao
					.createSQLQuery(
							"update TZSB_INSPECTION_CHANGE set data_status='99' where id = ? ",
							id).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
	}
}
