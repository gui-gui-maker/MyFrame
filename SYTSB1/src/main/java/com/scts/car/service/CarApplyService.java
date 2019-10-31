package com.scts.car.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.base.Factory;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.oa.car.bean.CarInfo;
import com.khnt.oa.car.dao.CarInfoDao;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.Org;
import com.khnt.rbac.impl.bean.User;
import com.khnt.rbac.impl.manager.UserManagerImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.advicenote.service.MsgLinkAuditService;
import com.lsts.constant.Constant;
import com.lsts.employee.service.EmployeesService;
import com.lsts.log.bean.SysLog;
import com.lsts.log.service.SysLogService;
import com.scts.car.bean.CarApply;
import com.scts.car.bean.CarApplyDTO;
import com.scts.car.dao.CarApplyDao;

import util.TS_Util;

/**
 * 用车申请业务逻辑对象
 * 
 * @ClassName CarApplyService
 * @JDK 1.7
 * @author GaoYa
 * @date 2018-06-27 下午02:14:00
 */
@Service("carApplyService")
public class CarApplyService extends EntityManageImpl<CarApply, CarApplyDao> {
	@Autowired
	private CarApplyDao carApplyDao;
	@Autowired
	private CarInfoDao carInfoDao;
	@Autowired
	private MessageService messageService;
	@Autowired
	private EmployeesService employeesService;
	@Autowired
	private MsgLinkAuditService msgLinkAuditService;
	@Autowired
	private UserManagerImpl userManager;
	@Autowired
	private SysLogService logService;

	/**
	 * 
	 * 保存用车申请单
	 * 
	 * @return
	 * @throws Exception
	 */
	public CarApply saveBasic(CarApply carApply, HttpServletRequest request) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User) curUser.getSysUser();
		// Employee emp = (Employee)user.getEmployee();
		Org org = TS_Util.getCurOrg(curUser);

		try {
			// 1、生成申请单编号
			if (StringUtil.isEmpty(carApply.getApply_sn())) {
				String pre_sn = TS_Util.getCurYearMonth();
				carApply.setApply_sn(pre_sn + carApplyDao.queryNextSn(pre_sn));
				carApply.setId(null);
			}

			// 2、计算用车天数

			int use_days = 1;
			if (carApply.getUse_start_date().before(carApply.getUse_end_date())) {
				use_days = TS_Util.daysBetween(carApply.getUse_start_date(), carApply.getUse_end_date()) + 1;
			}
			carApply.setUse_days(use_days);

			// 3、保存
			carApply.setApply_dep_id(org.getId());
			carApply.setApply_dep_name(org.getOrgName());
			carApply.setApply_user_id(user.getId());
			carApply.setApply_user_name(user.getName());
			carApply.setApply_date(new Date());

			if (StringUtil.isNotEmpty(carApply.getUse_user_name())) {
				if (carApply.getUse_user_name().contains("/")) {
					String[] arr = carApply.getUse_user_name().split("\\/");
					carApply.setUse_user_name(arr[0]);
					carApply.setUse_user_phone(arr[1]);
				}
			}
			List<?> list = carApplyDao.getUserRole(user.getId());//获取申请人的角色
			// 根据权限设置用车审核数据状态
			if(list.contains("公务用车申请-分管院领导审核")) {
				// 分管领导黄坚提交
				carApply.setData_status("3"); // 3：车队负责人待审核
			}else if(list.contains("公务用车申请-分管院领导申请")) {
				// 分管领导提交
				if(carApply.getUse_days()<3) {
					// 小于三天提交至车队负责人审核
					carApply.setData_status("3"); // 3：车队负责人待审核
				}else {
					// 大于三天提交至黄坚审核
					carApply.setData_status("2"); // 2：分管院领导待审核
				}
			}else if(list.contains("部门负责人")) {
				// 部门负责人提交至分管领导审核
				if(!list.contains("公务用车申请-分管院领导申请")) {
					if(carApply.getUse_days()<3) {
						// 小于三天提交至车队负责人审核
						carApply.setData_status("3"); // 3：车队负责人待审核
					}else {
						// 大于三天提交至黄坚审核
						carApply.setData_status("2"); // 2：分管院领导待审核
					}
				}
			}else {
				// 普通员工提交至用车部门负责人审核
				carApply.setData_status("0"); // 0：申请提交/用车部门负责人待审核
			}
			carApply.setExpenses("0");//是否报销
			carApplyDao.save(carApply);

			// 4、记录日志
			logService.setLogs(carApply.getId(), "用车申请", "提交申请", user.getId(), user.getName(), new Date(),
					request.getRemoteAddr());

			// 5、发送提醒消息
			try {
				sendMsg(request, carApply);
			} catch (Exception e) {
				e.printStackTrace();
				KhntException kh = new KhntException(e);
				log.error(kh.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			log.error(kh.getMessage());
		}
		return carApply;
	}
	
	/**
	 * 获取用车申请单信息
	 * 
	 * @param id
	 *            -- 申请单ID
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getDetail(String id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String[] ids = id.split(",");
			List<CarApply> list = new ArrayList<CarApply>();
			// 查询用车申请单
			for (String idd : ids) {
				CarApply carApply = carApplyDao.get(idd);
				list.add(carApply);
			}
			map.put("data", list);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
		return map;
	}

	// 逻辑删除
	public void del(HttpServletRequest request, String id) {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		try {
			CarApply carApply = carApplyDao.get(id);
			carApplyDao.createSQLQuery("update TZSB_CAR_APPLY set data_status='99' where id = ? ", id).executeUpdate();
			//标记车辆使用状态
			if(StringUtil.isNotEmpty(carApply.getFk_car_id())) {
				CarInfo carInfo = carInfoDao.get(carApply.getFk_car_id());
				carInfo.setCarState("1"); // 车辆使用状态（1：已收车/空闲可用 0：用车中 2：派车中）
				carInfo.setUse_dep_id("");
				carInfo.setUse_dep_name("");
				carInfo.setUse_start_date(null);
				carInfo.setUse_end_date(null);
				carInfoDao.save(carInfo);
			}
			
			setCheckStatusDel(carApply.getId());
			// 写入日志
			logService.setLogs(id, "用车申请作废", "作废申请单", user.getId(), user.getName(), new Date(),
					request.getRemoteAddr());
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
	}

	// 获取流转情况
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> getFlowStep(String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<SysLog> list = carApplyDao
				.createQuery(" from SysLog where business_id ='" + id + "' order by op_time,id asc").list();
		map.put("flowStep", list);
		map.put("size", list.size());
		map.put("sn", carApplyDao.get(id).getApply_sn());
		map.put("success", true);
		return map;
	}

	// 审核
	public HashMap<String, Object> checks(HttpServletRequest request, CarApplyDTO entity) throws Exception {
		CurrentSessionUser cur_user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String ids = entity.getId();
		try {
			String temp[] = ids.split(",");
			for (int i = 0; i < temp.length; i++) {
				// 1、保存
				CarApply carApply = carApplyDao.get(temp[i]);
				//为了避免出现2018.11.12出现的同一审核人由于网络原因出现的审核了两个步骤的问题，故将此出的data_status改为页面传过来的carApply0中的data_status
				/*String data_status = carApply.getData_status();*/
				String data_status = entity.getData_status();
				request.setAttribute("old_data_status", data_status);
				request.setAttribute("auditor", cur_user.getName());
				String flow_name = "";
				String flow_desc = "";
				if ("0".equals(data_status)) {
					// 用车部门负责人审核
					carApply.setDep_deal_result(entity.getDep_deal_result());
					carApply.setDep_deal_remark(entity.getDep_deal_remark());
					carApply.setDep_deal_id(cur_user.getId());
					carApply.setDep_deal_name(cur_user.getName());
					carApply.setDep_deal_date(new Date());

					// 小于三天（含三天），则直接流转到车队负责人审核。如果超过三天（不含三天），则提交到分管院领导进行审核（默认黄院）
					if (carApply.getUse_days() <= 3) {
						if ("1".equals(entity.getDep_deal_result())) {
							carApply.setData_status("3");
						} else {
							carApply.setData_status("98");
						}
					} else {
						if ("1".equals(entity.getDep_deal_result())) {
							carApply.setData_status("2");
						} else {
							carApply.setData_status("98");
						}
					}

					flow_name = "用车部门负责人审核";
					flow_desc = "1".equals(entity.getDep_deal_result()) ? "同意"
							: "不同意（" + entity.getDep_deal_remark() + "）";
				} else if ("1".equals(data_status)) {
					// 办公室负责人审核
					carApply.setOffice_deal_result(entity.getOffice_deal_result());
					carApply.setOffice_deal_remark(entity.getOffice_deal_remark());
					carApply.setOffice_deal_id(cur_user.getId());
					carApply.setOffice_deal_name(cur_user.getName());
					carApply.setOffice_deal_date(new Date());

					if ("1".equals(entity.getOffice_deal_result())) {
						carApply.setData_status("5");

						// 标记车辆使用状态
						CarInfo carInfo = carInfoDao.get(carApply.getFk_car_id());
						carInfo.setCarState("0"); // 车辆使用状态（1：空闲可用 0：外出在用）
						carInfoDao.save(carInfo);
					} else {
						carApply.setData_status("98");
					}

					flow_name = "办公室负责人审核";
					flow_desc = "1".equals(entity.getOffice_deal_result()) ? "同意"
							: "不同意（" + entity.getOffice_deal_remark() + "）";
				} else if ("2".equals(data_status)) {
					// 分管院领导审核
					carApply.setLeader_deal_result(entity.getLeader_deal_result());
					carApply.setLeader_deal_remark(entity.getLeader_deal_remark());
					carApply.setLeader_deal_id(cur_user.getId());
					carApply.setLeader_deal_name(cur_user.getName());
					carApply.setLeader_deal_date(new Date());

					if ("1".equals(entity.getLeader_deal_result())) {
						carApply.setData_status("3");
					} else {
						carApply.setData_status("98");
					}

					flow_name = "分管院领导审核";
					flow_desc = "1".equals(entity.getLeader_deal_result()) ? "同意"
							: "不同意（" + entity.getLeader_deal_remark() + "）";
				} else if ("3".equals(data_status)) {
					// 车队负责人审核
					if (StringUtil.isNotEmpty(entity.getFk_car_id())) {
						carApply.setFk_car_id(entity.getFk_car_id());
						carApply.setPlate_number(entity.getPlate_number());
					}
					carApply.setFleet_deal_result(entity.getFleet_deal_result());
					carApply.setFleet_deal_remark(entity.getFleet_deal_remark());
					carApply.setFleet_deal_id(cur_user.getId());
					carApply.setFleet_deal_name(cur_user.getName());
					carApply.setFleet_deal_date(new Date());

					if ("1".equals(entity.getFleet_deal_result())) {
						carApply.setData_status("4");
					} else {
						carApply.setData_status("98");
					}
					flow_name = "车队负责人审核";
					flow_desc = "1".equals(entity.getFleet_deal_result()) ? "同意"
							: "不同意（" + entity.getFleet_deal_remark() + "）";
				}
				carApplyDao.save(carApply);
				
				// 2、记录日志
				logService.setLogs(carApply.getId(), flow_name, flow_desc, cur_user.getId(), cur_user.getName(),
						new Date(), request.getRemoteAddr());
				
				// 3、更新统一消息接口业务审核状态
				// 因车队派车处理人目前2人有权限，故此处判断处理
				if ("4".equals(carApply.getData_status())) {// 4、派车中
					String[] emp_ids = null;
					String emp_id = Factory.getSysPara().getProperty("CAR_APPLY_FLEET2_EMP_ID");
					if (StringUtil.isNotEmpty(emp_id)) {
						emp_ids = emp_id.split(",");
					} else {
						emp_ids = Constant.CAR_APPLY_FLEET2_EMP_ID.split(",");
					}
					
					if(emp_ids != null) {
						for (int j = 0; j < emp_ids.length; j++) {
							Employee employee = employeesService.get(emp_ids[j]);
							setCheckStatus(carApply.getId(), employee.getMobileTel());
						}
					}
				}else {
					setCheckStatus(carApply.getId(), null);
				}
				
				// 4、发送提醒消息
				try {
					sendMsg(request, carApply);
				} catch (Exception e) {
					e.printStackTrace();
					KhntException kh = new KhntException(e);
					log.error(kh.getMessage());
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
	
	// 审核用车申请（微信）
	public HashMap<String, Object> wxChecks(HttpServletRequest request, CarApply carApply0) throws Exception {
		CurrentSessionUser cur_user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String ids = request.getParameter("id");
		String deal_result = request.getParameter("deal_result");	// 1：同意 0：不同意
		try {
			String temp[] = ids.split(",");
			for (int i = 0; i < temp.length; i++) {
				// 1、保存
				CarApply carApply = carApplyDao.get(temp[i]);
				//为了避免出现2018.11.12出现的同一审核人由于网络原因出现的审核了两个步骤的问题，故将此出的data_status改为页面传过来的carApply0中的data_status
				/*String data_status = carApply.getData_status();*/
				String data_status = carApply0.getData_status();
				request.setAttribute("old_data_status", data_status);
				request.setAttribute("auditor", cur_user.getName());
				String flow_name = "";
				String flow_desc = "";
				if ("0".equals(data_status)) {
					// 用车部门负责人审核
					carApply.setDep_deal_result(deal_result);
					carApply.setDep_deal_remark(carApply0.getDep_deal_remark());
					carApply.setDep_deal_id(cur_user.getId());
					carApply.setDep_deal_name(cur_user.getName());
					carApply.setDep_deal_date(new Date());

					// 小于三天（含三天），则直接流转到车队负责人审核。如果超过三天（不含三天），则提交到分管院领导进行审核（默认黄院）
					if (carApply.getUse_days() <= 3) {
						if ("1".equals(deal_result)) {
							carApply.setData_status("3");
						} else {
							carApply.setData_status("98");
						}
					} else {
						if ("1".equals(deal_result)) {
							carApply.setData_status("2");
						} else {
							carApply.setData_status("98");
						}
					}

					flow_name = "用车部门负责人审核";
					flow_desc = "1".equals(deal_result) ? "同意"
							: "不同意（" + carApply0.getDep_deal_remark() + "）";
				} else if ("1".equals(data_status)) {
					// 办公室负责人审核
					carApply.setOffice_deal_result(deal_result);
					carApply.setOffice_deal_remark(carApply0.getOffice_deal_remark());
					carApply.setOffice_deal_id(cur_user.getId());
					carApply.setOffice_deal_name(cur_user.getName());
					carApply.setOffice_deal_date(new Date());

					if ("1".equals(deal_result)) {
						carApply.setData_status("5");
						
						// 标记车辆使用状态
						CarInfo carInfo = carInfoDao.get(carApply.getFk_car_id());
						carInfo.setCarState("0"); // 车辆使用状态（1：已收车/空闲可用 0：用车中 2：派车中）
						carInfoDao.save(carInfo);
					} else {
						carApply.setData_status("98");
					}

					flow_name = "办公室负责人审核";
					flow_desc = "1".equals(deal_result) ? "同意"
							: "不同意（" + carApply0.getOffice_deal_remark() + "）";
				} else if ("2".equals(data_status)) {
					// 分管院领导审核
					carApply.setLeader_deal_result(deal_result);
					carApply.setLeader_deal_remark(carApply0.getLeader_deal_remark());
					carApply.setLeader_deal_id(cur_user.getId());
					carApply.setLeader_deal_name(cur_user.getName());
					carApply.setLeader_deal_date(new Date());

					if ("1".equals(deal_result)) {
						carApply.setData_status("3");
					} else {
						carApply.setData_status("98");
					}

					flow_name = "分管院领导审核";
					flow_desc = "1".equals(deal_result) ? "同意"
							: "不同意（" + carApply0.getLeader_deal_remark() + "）";
				} else if ("3".equals(data_status)) {
					// 车队负责人审核
					if (StringUtil.isNotEmpty(carApply0.getFk_car_id())) {
						carApply.setFk_car_id(carApply0.getFk_car_id());
						carApply.setPlate_number(carApply0.getPlate_number());
					}
					carApply.setFleet_deal_result(deal_result);
					carApply.setFleet_deal_remark(carApply0.getFleet_deal_remark());
					carApply.setFleet_deal_id(cur_user.getId());
					carApply.setFleet_deal_name(cur_user.getName());
					carApply.setFleet_deal_date(new Date());

					if ("1".equals(deal_result)) {
						carApply.setData_status("4");
					} else {
						carApply.setData_status("98");
					}
					flow_name = "车队负责人审核";
					flow_desc = "1".equals(deal_result) ? "同意"
							: "不同意（" + carApply0.getFleet_deal_remark() + "）";
				}
				carApplyDao.save(carApply);

				// 2、记录日志
				logService.setLogs(carApply.getId(), flow_name, flow_desc, cur_user.getId(), cur_user.getName(),
						new Date(), request.getRemoteAddr());

				// 3、更新统一消息接口业务审核状态
				// 因车队派车处理人目前2人有权限，故此处判断处理
				if ("4".equals(carApply.getData_status())) {// 4、派车中
					String[] emp_ids = null;
					String emp_id = Factory.getSysPara().getProperty("CAR_APPLY_FLEET2_EMP_ID");
					if (StringUtil.isNotEmpty(emp_id)) {
						emp_ids = emp_id.split(",");
					} else {
						emp_ids = Constant.CAR_APPLY_FLEET2_EMP_ID.split(",");
					}
					
					if(emp_ids != null) {
						for (int j = 0; j < emp_ids.length; j++) {
							Employee employee = employeesService.get(emp_ids[j]);
							setCheckStatus(carApply.getId(), employee.getMobileTel());
						}
					}
				}else {
					setCheckStatus(carApply.getId(), null);
				}
				
				// 4、发送提醒消息
				try {
					sendMsg(request, carApply);
				} catch (Exception e) {
					e.printStackTrace();
					KhntException kh = new KhntException(e);
					log.error(kh.getMessage());
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
	
	

	// 派车
	public HashMap<String, Object> assigns(HttpServletRequest request, CarApplyDTO entity) throws Exception {
		CurrentSessionUser cur_user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String ids = entity.getId();
		try {
			String temp[] = ids.split(",");
			for (int i = 0; i < temp.length; i++) {
				// 1、保存
				CarApply carApply = carApplyDao.get(temp[i]);
				carApply.setFk_car_id(entity.getFk_car_id());
				carApply.setPlate_number(entity.getPlate_number());
				if(StringUtil.isNotEmpty(entity.getDriver_user_id())) {
					carApply.setDriver_user_id(entity.getDriver_user_id());
				}
				carApply.setDriver_user_name(entity.getDriver_user_name());
				carApply.setOut_date(entity.getOut_date());
				carApply.setStart_km(entity.getStart_km().trim());
				carApply.setSend_deal_id(cur_user.getId());
				carApply.setSend_deal_name(cur_user.getName());
				carApply.setSend_date(new Date());
				carApply.setData_status("1");
				carApplyDao.save(carApply);
				
				// 2、标记车辆使用状态
				if(StringUtil.isNotEmpty(carApply.getFk_car_id())) {
					CarInfo carInfo = carInfoDao.get(carApply.getFk_car_id());
					carInfo.setCarState("2"); // 车辆使用状态（1：已收车/空闲可用 0：用车中 2：派车中）
					carInfo.setUse_dep_id(carApply.getUse_dep_id());
					carInfo.setUse_dep_name(carApply.getUse_dep_name());
					carInfo.setUse_start_date(carApply.getUse_start_date());
					carInfo.setUse_end_date(carApply.getUse_end_date());
					carInfoDao.save(carInfo);
				}

				// 3、记录日志
				String flow_name = "车队派车";
				String flow_desc = flow_name + "（ " + carApply.getPlate_number() + "，公里起数：" + carApply.getStart_km()
						+ " km ）";
				logService.setLogs(carApply.getId(), flow_name, flow_desc, cur_user.getId(), cur_user.getName(),
						new Date(), request.getRemoteAddr());
				
				// 4、更新统一消息接口业务审核状态
				setCheckStatus(carApply.getId(), null);
				
				// 5、发送提醒消息
				try {
					sendMsg(request, carApply);
				} catch (Exception e) {
					e.printStackTrace();
					KhntException kh = new KhntException(e);
					log.error(kh.getMessage());
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
	
	
	/**
	 * 更新统一消息接口业务审核状态
	 * 
	 * @param apply_id
	 *            -- 用车申请对象ID
	 * @param mobile_tel
	 *            -- 处理人手机号码（为空时，默认获取session中当前登录人作为处理人）
	 * @return
	 * @author GaoYa
	 * @date 2018-07-16 下午18:32:00
	 */
	private void setCheckStatusDel(String apply_id) {
		
		if(StringUtil.isNotEmpty(apply_id)) {
			msgLinkAuditService.setAuditStatusDel(apply_id);
		}
	}
	
	/**
	 * 更新统一消息接口业务审核状态
	 * 
	 * @param apply_id
	 *            -- 用车申请对象ID
	 * @param mobile_tel
	 *            -- 处理人手机号码（为空时，默认获取session中当前登录人作为处理人）
	 * @return
	 * @author GaoYa
	 * @date 2018-07-16 下午18:32:00
	 */
	private void setCheckStatus(String apply_id, String mobile_tel) {
		if(StringUtil.isEmpty(mobile_tel)) {
			CurrentSessionUser cur_user = SecurityUtil.getSecurityUser();
			User user = (User)cur_user.getSysUser();
			Employee employee = (Employee)user.getEmployee();
			mobile_tel = employee.getMobileTel();
		}
		if(StringUtil.isNotEmpty(mobile_tel)) {
			msgLinkAuditService.setAuditStatus(apply_id, mobile_tel);
		}
	}
	
	/**
	 * 用车申请消息提醒推送
	 * 
	 * @param request
	 *            -- 请求对象
	 * @param carApply
	 *            -- 用车申请单对象           
	 * @return
	 * @author GaoYa
	 * @date 2018-07-16 下午18:32:00
	 */
	@SuppressWarnings("unchecked")
	private void sendMsg(HttpServletRequest request, CarApply carApply) {
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("applyOp", carApply.getApply_user_name());
		map1.put("applySn", carApply.getApply_sn());
		String wx_check_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb0f376eb09e64dd3"
				+ "&redirect_uri=http://kh.scsei.org.cn/car/apply/weixinUserInfo.do?businessId=" + carApply.getId()
				+ "%26businessStatus=" + carApply.getData_status() + "&response_type=code&scope=snsapi_base&state=STATE";
		HashMap<String, Object> map2 = new HashMap<String, Object>();
		map2.put("url", wx_check_url);
		try {
			String msg_code = "carApply_check";
			// 获取发送内容
			String dx_content = Constant.getCarApplyDxTips(carApply.getApply_user_name(), carApply.getApply_sn());
			// String wx_content = Constant.getCarApplyWxTips(carApply.getApply_user_name(),
			// carApply.getApply_sn(), carApply.getId(), carApply.getData_status());
			String mobile = "";
			if ("0".equals(carApply.getData_status())) {
				List<Map<String, Object>> leaders = carApplyDao.queryDepLeaders();
				for (Iterator iterator = leaders.iterator(); iterator.hasNext();) {
					Map<String, Object> dataMap = (Map<String, Object>) iterator.next();
					String org_id = dataMap.get("org_id").toString();
					if (org_id.equals(carApply.getApply_dep_id())) {
						mobile = dataMap.get("mobile_tel").toString();
						break;
					}
				}
				if (StringUtil.isEmpty(mobile)) {
					String[] emp_ids = null;
					if ("100025".equals(carApply.getApply_dep_id()) || "100059".equals(carApply.getApply_dep_id())) {
						// 财务部、工会
						String emp_id = Factory.getSysPara().getProperty("CAR_APPLY_CW_EMP_ID");
						if (StringUtil.isNotEmpty(emp_id)) {
							emp_ids = emp_id.split(",");
						} else {
							emp_ids = Constant.CAR_APPLY_CW_EMP_ID.split(",");
						}
					} else if ("100030".equals(carApply.getApply_dep_id())) {
						// 科管部
						String emp_id = Factory.getSysPara().getProperty("CAR_APPLY_KG_EMP_ID");
						if (StringUtil.isNotEmpty(emp_id)) {
							emp_ids = emp_id.split(",");
						} else {
							emp_ids = Constant.CAR_APPLY_KG_EMP_ID.split(",");
						}
					} else if ("100044".equals(carApply.getApply_dep_id())) {
						// 司法鉴定中心
						String emp_id = Factory.getSysPara().getProperty("CAR_APPLY_SF_EMP_ID");
						if (StringUtil.isNotEmpty(emp_id)) {
							emp_ids = emp_id.split(",");
						} else {
							emp_ids = Constant.CAR_APPLY_SF_EMP_ID.split(",");
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
					messageService.sendMassageByConfig(request, carApply.getId(), mobile, dx_content, msg_code,
							null, map1, map2, null, Constant.OFFICE_CORPID, Constant.OFFICE_PWD);
					
					// 发送定时短信和微信提醒（发送类型以系统配置为准）
					messageService.sendMassageByConfig(request, carApply.getId(), mobile, dx_content, "carApply_check_time",
							null, map1, map2, null, Constant.OFFICE_CORPID, Constant.OFFICE_PWD);
				}
			} else {
				map1.put("applyOp", carApply.getApply_dep_name() + carApply.getApply_user_name());
				String[] emp_ids = null;
				if ("1".equals(carApply.getData_status())) {
					// 发送提醒消息给办公室负责人（默认接收人：阳晓薇）
					String emp_id = Factory.getSysPara().getProperty("CAR_APPLY_OFFICE_EMP_ID");
					if (StringUtil.isNotEmpty(emp_id)) {
						emp_ids = emp_id.split(",");
					} else {
						emp_ids = Constant.CAR_APPLY_OFFICE_EMP_ID.split(",");
					}
				} else if ("2".equals(carApply.getData_status())) {
					// 发送提醒消息给分管院领导（默认接收人：黄坚）
					String emp_id = Factory.getSysPara().getProperty("CAR_APPLY_LEADER_EMP_ID");
					if (StringUtil.isNotEmpty(emp_id)) {
						emp_ids = emp_id.split(",");
					} else {
						emp_ids = Constant.CAR_APPLY_LEADER_EMP_ID.split(",");
					}
				} else if ("3".equals(carApply.getData_status())) {
					// 发送提醒消息给车队负责人（默认接收人：贺飞峙）
					String emp_id = Factory.getSysPara().getProperty("CAR_APPLY_FLEET_EMP_ID");
					if (StringUtil.isNotEmpty(emp_id)) {
						emp_ids = emp_id.split(",");
					} else {
						emp_ids = Constant.CAR_APPLY_FLEET_EMP_ID.split(",");
					}
				} else if ("4".equals(carApply.getData_status())) {
					// 发送提醒消息给车队派车人（默认接收人：贺飞峙和杨东）
					String emp_id = Factory.getSysPara().getProperty("CAR_APPLY_FLEET2_EMP_ID");
					if (StringUtil.isNotEmpty(emp_id)) {
						emp_ids = emp_id.split(",");
					} else {
						emp_ids = Constant.CAR_APPLY_FLEET2_EMP_ID.split(",");
					}
				} else if ("5".equals(carApply.getData_status())) {
					User user = userManager.get(carApply.getApply_user_id());
					if(user != null) {
						emp_ids = user.getEmployee().getId().split(",");
					}
					msg_code = "carApply_assigned";
					map2 = null;
				} else if ("98".equals(carApply.getData_status())) {
					// 审核不通过时发消息给用车申请人
					String old_data_status = request.getAttribute("old_data_status").toString();
					String auditor = request.getAttribute("auditor").toString();
					User user = userManager.get(carApply.getApply_user_id());
					if(user != null) {
						emp_ids = user.getEmployee().getId().split(",");
					}
					if("0".equals(old_data_status)) {
						map1.put("auditLink", "用车部门负责人审核");
					}else if("2".equals(old_data_status)) {
						map1.put("auditLink", "分管院领导审核");
					}else if("3".equals(old_data_status)) {
						map1.put("auditLink", "车队负责人审核");
					}else if("1".equals(old_data_status)) {
						map1.put("auditLink", "办公室负责人审核");
					}
					map1.put("auditResult", "被退回");
					map1.put("auditor", auditor);
					msg_code = "carApply_fail";
					map2 = null;
				} else if ("99".equals(carApply.getData_status())) {
					// 申请作废时发消息给用车申请人
					String old_data_status = request.getAttribute("old_data_status").toString();
					String auditor = request.getAttribute("auditor").toString();
					User user = userManager.get(carApply.getApply_user_id());
					if(user != null) {
						emp_ids = user.getEmployee().getId().split(",");
					}
					if("0".equals(old_data_status)) {
						map1.put("auditLink", "用车部门负责人审核");
					}else if("2".equals(old_data_status)) {
						map1.put("auditLink", "分管院领导审核");
					}else if("3".equals(old_data_status)) {
						map1.put("auditLink", "车队负责人审核");
					}else if("1".equals(old_data_status)) {
						map1.put("auditLink", "办公室负责人审核");
					}else if("4".equals(old_data_status)) {
						map1.put("auditLink", "派车中");
					}else if("5".equals(old_data_status)) {
						map1.put("auditLink", "用车中");
					}else if("6".equals(old_data_status)) {
						map1.put("auditLink", "已收车");
					}else if("98".equals(old_data_status)) {
						map1.put("auditLink", "已退回");
					}
					map1.put("auditResult", "被作废");
					map1.put("auditor", auditor);
					msg_code = "carApply_fail";
					map2 = null;
				}

				if(emp_ids != null) {
					for (int j = 0; j < emp_ids.length; j++) {
						Employee employee = employeesService.get(emp_ids[j]);
						// 获取发送目标号码
						mobile = employee.getMobileTel();
						
						// 发送即时短信和微信提醒（发送类型以系统配置为准）
						messageService.sendMassageByConfig(request, carApply.getId(), mobile, dx_content, msg_code,
								null, map1, map2, null, Constant.OFFICE_CORPID, Constant.OFFICE_PWD);
						
						// 发送定时短信和微信提醒（发送类型以系统配置为准）
						messageService.sendMassageByConfig(request, carApply.getId(), mobile, dx_content, "carApply_check_time",
								null, map1, map2, null, Constant.OFFICE_CORPID, Constant.OFFICE_PWD);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
	}
	
	// 派车(微信)
	public HashMap<String, Object> wxAssigns(HttpServletRequest request, CarApply carApply0) throws Exception {
		CurrentSessionUser cur_user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String ids = carApply0.getId();
		try {
			String temp[] = ids.split(",");
			for (int i = 0; i < temp.length; i++) {
				// 1、保存
				CarApply carApply = carApplyDao.get(temp[i]);
				carApply.setFk_car_id(carApply0.getFk_car_id());
				carApply.setPlate_number(carApply0.getPlate_number());
				if(StringUtil.isNotEmpty(carApply0.getDriver_user_id())) {
					carApply.setDriver_user_id(carApply0.getDriver_user_id());
				}
				carApply.setDriver_user_name(carApply0.getDriver_user_name());
				carApply.setOut_date(carApply0.getOut_date());
				carApply.setStart_km(carApply0.getStart_km().trim());
				carApply.setSend_deal_id(cur_user.getId());
				carApply.setSend_deal_name(cur_user.getName());
				carApply.setSend_date(new Date());
				carApply.setData_status("1");
				carApplyDao.save(carApply);

				// 2、标记车辆使用状态
				if(StringUtil.isNotEmpty(carApply.getFk_car_id())) {
					CarInfo carInfo = carInfoDao.get(carApply.getFk_car_id());
					carInfo.setCarState("2"); // 车辆使用状态（1：已收车/空闲可用 0：用车中 2：派车中）
					carInfo.setUse_dep_id(carApply.getUse_dep_id());
					carInfo.setUse_dep_name(carApply.getUse_dep_name());
					carInfo.setUse_start_date(carApply.getUse_start_date());
					carInfo.setUse_end_date(carApply.getUse_end_date());
					carInfoDao.save(carInfo);
				}
				// 3、记录日志
				String flow_name = "车队派车";
				String flow_desc = flow_name + "（ " + carApply.getPlate_number() + "，公里起数：" + carApply.getStart_km()
						+ " km ）";
				logService.setLogs(carApply.getId(), flow_name, flow_desc, cur_user.getId(), cur_user.getName(),
						new Date(), request.getRemoteAddr());
				
				// 4、更新统一消息接口业务审核状态
				setCheckStatus(carApply.getId(), null);
				
				// 5、发送提醒消息
				try {
					sendMsg(request, carApply);
				} catch (Exception e) {
					e.printStackTrace();
					KhntException kh = new KhntException(e);
					log.error(kh.getMessage());
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

	// 收车
	public HashMap<String, Object> receives(HttpServletRequest request, CarApplyDTO entity) throws Exception {
		CurrentSessionUser cur_user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String ids = entity.getId();
		try {
			String temp[] = ids.split(",");
			for (int i = 0; i < temp.length; i++) {
				// 1、保存
				CarApply carApply = carApplyDao.get(temp[i]);
				carApply.setBack_date(entity.getBack_date());
				carApply.setEnd_km(entity.getEnd_km().trim());
				carApply.setReceive_deal_id(cur_user.getId());
				carApply.setReceive_deal_name(cur_user.getName());
				carApply.setReceive_date(new Date());
				carApply.setData_status("6");
				carApplyDao.save(carApply);

				// 2、标记车辆使用状态
				if(StringUtil.isNotEmpty(carApply.getFk_car_id())) {
					CarInfo carInfo = carInfoDao.get(carApply.getFk_car_id());
					carInfo.setCarMileage(carApply.getEnd_km());// 返写车辆止数到车辆信息表中
					carInfo.setCarState("1"); // 车辆使用状态（1：已收车/空闲可用 0：用车中 2：派车中）
					carInfo.setUse_dep_id("");
					carInfo.setUse_dep_name("");
					carInfo.setUse_start_date(null);
					carInfo.setUse_end_date(null);
					carInfoDao.save(carInfo);
				}
				
				// 3、记录日志
				String flow_name = "车队收车";
				String flow_desc = flow_name + "（ " + carApply.getPlate_number() + "，公里止数：" + carApply.getEnd_km()
						+ " km ）";
				logService.setLogs(carApply.getId(), flow_name, flow_desc, cur_user.getId(), cur_user.getName(),
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
	// 收车(微信)
	public HashMap<String, Object> wxReceives(HttpServletRequest request, CarApply carApply0) throws Exception {
		CurrentSessionUser cur_user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String ids = carApply0.getId();
		try {
			String temp[] = ids.split(",");
			for (int i = 0; i < temp.length; i++) {
				// 1、保存
				CarApply carApply = carApplyDao.get(temp[i]);
				carApply.setBack_date(carApply0.getBack_date());
				carApply.setEnd_km(carApply0.getEnd_km().trim());
				carApply.setReceive_deal_id(cur_user.getId());
				carApply.setReceive_deal_name(cur_user.getName());
				carApply.setReceive_date(new Date());
				carApply.setData_status("6");
				carApplyDao.save(carApply);

				// 2、标记车辆使用状态
				if(StringUtil.isNotEmpty(carApply.getFk_car_id())) {
					CarInfo carInfo = carInfoDao.get(carApply.getFk_car_id());
					carInfo.setCarMileage(carApply.getEnd_km());// 返写车辆止数到车辆信息表中
					carInfo.setCarState("1"); // 车辆使用状态（1：已收车/空闲可用 0：用车中 2：派车中）
					carInfo.setUse_dep_id("");
					carInfo.setUse_dep_name("");
					carInfo.setUse_start_date(null);
					carInfo.setUse_end_date(null);
					carInfoDao.save(carInfo);
				}

				// 3、记录日志
				String flow_name = "车队收车";
				String flow_desc = flow_name + "（ " + carApply.getPlate_number() + "，公里止数：" + carApply.getEnd_km()
						+ " km ）";
				logService.setLogs(carApply.getId(), flow_name, flow_desc, cur_user.getId(), cur_user.getName(),
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

	// 获取上一次该车收车时的公里止数
	public String getEndKmByCarId(String fk_car_id, String apply_id) throws Exception {
		return carApplyDao.getEndKmByCarId(fk_car_id, apply_id);
	}
	
	/**
	 * 根据用车部门ID获取该部门当前派用车辆
	 * 
	 * @param use_dep_id
	 *            -- 用车部门ID
	 * @return
	 * @author GaoYa
	 * @date 2018-07-16 下午18:38:00
	 */
	public List<CarApply> getCarApplyByUseDepId(String use_dep_id, String apply_id) {
		return carApplyDao.getCarApplyByUseDepId(use_dep_id, apply_id);
	}
	
	/**
	 * 根据申请部门ID获取该部门当前派用车辆
	 * 
	 * @param apply_dep_id
	 *            -- 申请部门ID
	 * @return
	 * @author GaoYa
	 * @date 2018-07-16 下午18:38:00
	 */
	public List<CarApply> getCarApplyByApplyDepId(String apply_dep_id, String apply_id) {
		return carApplyDao.getCarApplyByApplyDepId(apply_dep_id, apply_id);
	}
	
	/**
	 * 微信端获取代办事项信息
	 * @param car_apply_check_status
	 * @param userId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public List<CarApply> queryCheckList(String car_apply_check_status,String userId,String orgId) throws Exception {
		return carApplyDao.queryCheckList(car_apply_check_status,userId,orgId);
	}
	/**
	 * 微信端获取我的申请事项信息
	 * @param car_apply_check_status
	 * @param userId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public List<CarApply> queryWdsqList(String car_apply_check_status,String userId,String orgId) throws Exception {
		return carApplyDao.queryWdsqList(car_apply_check_status,userId,orgId);
	}
	/**
	 * 微信端获取已办理的事项信息
	 * @param car_apply_check_status
	 * @param userId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public List<CarApply> queryYblList(String car_apply_check_status,String userId,String orgId) throws Exception {
		return carApplyDao.queryYblList(car_apply_check_status,userId,orgId);
	}
	/**
	 * 获取部门信息
	 * @return
	 */
	public HashMap<String, Object> getOrgList() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			List<Object[]> orgList = new ArrayList<Object[]>();
			String sql = "select s.id, s.org_name" + 
					"  from SYS_ORG s" + 
					" where s.status = 'used'" + 
					"   and s.parent_id = '100000'" + 
					"   and s.id not in ('100084'," + 
					"                    '100069'," + 
					"                    '100090'," + 
					"                    '100032'," + 
					"                    '100038'," + 
					"                    '100039'," + 
					"                    '100061'," + 
					"                    '100091')" + 
					" order by s.orders" + 
					"";
			orgList = carApplyDao.createSQLQuery(sql).list();
			map.put("orgList", orgList);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
		return map;
	}
	/**
	 * 获取人员信息
	 * @return
	 */
	public HashMap<String, Object> getUserList(String org_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			List<Object[]> userList = new ArrayList<Object[]>();
			String sql = "select t.id, t.name" + 
					"  from SYS_USER t" + 
					" where t.org_id = ?" + 
					"   and t.status = '1'" + 
					" order by t.sort";
			userList = carApplyDao.createSQLQuery(sql,org_id).list();
			map.put("userList", userList);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
		return map;
	}
	/**
	 * 获取车辆信息
	 * @return
	 */
	public HashMap<String, Object> getCarList() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			List<Object[]> carList = new ArrayList<Object[]>();
			String sql = "select t.id, t.car_num from OA_CAR_INFO t order by t.car_num";
			carList = carApplyDao.createSQLQuery(sql).list();
			map.put("carList", carList);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
		return map;
	}
	
	/**
	 * 用车超期未还提醒任务
	 * 
	 * 用车时间到期，但未收车的，发消息提醒给用车申请人
	 * 
	 * @param request
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2018-08-16 上午09:25:00
	 */
	public void doRemindReturn() {
		try {
			// 1、获取未收车的用车申请业务
			List<CarApply> carApplyList = carApplyDao.getNotReturnedOfCarApply();
			if (!carApplyList.isEmpty()) {
				Date curDate = DateUtil.convertStringToDate(Constant.ymdhmsDatePattern, DateUtil.getCurrentDateTime());
				for (CarApply carApply : carApplyList) {
					// 用车时间到期的，发送消息提醒用车申请人
					if (carApply.getUse_end_date().before(curDate)) {
						sendReturnMsg(carApply);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
	}

	/**
	 * 用车到期未归还消息提醒推送
	 * 
	 * @param carApply
	 *            -- 用车申请单对象           
	 * @return
	 * @author GaoYa
	 * @date 2018-08-16 上午10:42:00
	 */
	private void sendReturnMsg(CarApply carApply) {
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("carNum", carApply.getPlate_number());
		map1.put("endDate", DateUtil.format(carApply.getUse_end_date(), Constant.defaultDatePattern));
		try {
			String msg_code = "carApply_return";
			// 获取发送内容
			String wx_content = Constant.getCarApplyReturnWxTips(carApply.getPlate_number(),
					DateUtil.format(carApply.getUse_end_date(), Constant.defaultDatePattern));
			String mobile = "";
			String[] emp_ids = null;
			User user = userManager.get(carApply.getApply_user_id());
			if (user != null) {
				emp_ids = user.getEmployee().getId().split(",");
			}
			for (int j = 0; j < emp_ids.length; j++) {
				Employee employee = employeesService.get(emp_ids[j]);
				// 获取发送目标号码
				if (StringUtil.isEmpty(mobile)) {
					mobile = employee.getMobileTel();
				}
			}
			if (StringUtil.isNotEmpty(mobile)) {
				// 发送短信和微信提醒（发送类型以系统配置为准）
				messageService.sendMassageByConfig(null, carApply.getId(), mobile, wx_content, msg_code, null, map1,
						null, null, Constant.OFFICE_CORPID, Constant.OFFICE_PWD);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
	}
	
	/**
	 * 查询用户所拥有的角色
	 * @param user_id
	 * @return
	 */
  	public List<?> getUserRole(String user_id){
		List<?> list = carApplyDao.getUserRole(user_id);
		return list;
	}
  	
  	public HashMap<String, Object> zuofei(HttpServletRequest request,String id) {
  		HashMap<String, Object> map=new HashMap<String, Object>();
  		try {
  			
  			if(StringUtil.isNotEmpty(id)){
  				CurrentSessionUser cur_user = SecurityUtil.getSecurityUser();
  	  			CarApply carApply=carApplyDao.get(id);
  	  			request.setAttribute("old_data_status", carApply.getData_status());
  	  			request.setAttribute("auditor", cur_user.getName());
  	  			carApply.setOffice_deal_id(cur_user.getId());
  	  			carApply.setOffice_deal_name(cur_user.getName());
  	  			carApply.setOffice_deal_result("2");
  	  			carApply.setOffice_deal_remark("不同意");
  	  			carApply.setOffice_deal_date(new Date());
  	  	 		carApply.setData_status("99");
  	  	 		this.save(carApply);
  	  	 		carApply.getFk_car_id();
  	  	 		CarInfo info=carInfoDao.get(carApply.getFk_car_id());
  	  	 		info.setUse_dep_id(null);
  	  	 		info.setUse_dep_name(null);
  	  	 		info.setUse_end_date(null);
  	  	 		info.setUse_start_date(null);
  	  	 		info.setCarState("1");
  	  	 		carApplyDao.save(info);
  	  	 		
  	  	 		
  	  	 		

				// 记录日志
				String flow_name = "办公室负责人审核";
				logService.setLogs(carApply.getId(), flow_name, "不同意，数据作废", cur_user.getId(), cur_user.getName(),
						new Date(), request.getRemoteAddr());
  	  	 		map.put("success", "true");
  	  	 		// 发送提醒消息
  				try {
  					sendMsg(request, carApply);
  				} catch (Exception e) {
  					e.printStackTrace();
  					KhntException kh = new KhntException(e);
  					log.error(kh.getMessage());
  				}
  			}else{
  				map.put("success", "false");
  				map.put("msg", "作废错处！");
  			}
		} catch (Exception e) {
	  		map.put("success", false);
	  		System.out.println(e);
			map.put("msg", "请求超时，请稍后再试！");
		}
  		return map;
  	}
}
