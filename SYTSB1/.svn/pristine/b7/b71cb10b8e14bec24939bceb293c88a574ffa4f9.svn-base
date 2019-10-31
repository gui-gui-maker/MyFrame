package com.scts.car.service;

import com.khnt.base.Factory;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;
import com.lsts.employee.service.EmployeesService;
import com.scts.car.bean.CarIns;
import com.scts.car.bean.CarInsDetail;
import com.scts.car.dao.CarInsDao;
import com.scts.car.dao.CarInsDetailDao;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("carInsDetailManager")
public class CarInsDetailManager extends EntityManageImpl<CarInsDetail, CarInsDetailDao> {
	@Autowired
	CarInsDetailDao carInsDetailDao;
	@Autowired
	CarInsDao carInsDao;
	@Autowired
	private MessageService messageService;
	@Autowired
	private EmployeesService employeesService;

	/**
	 * 保存保险信息
	 * 
	 * @param request
	 * @param carInsDetail
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> saveInfo(HttpServletRequest request, CarInsDetail carInsDetail) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		try {
			List<CarIns> list = carInsDao.getCarInssByFkCarId(carInsDetail.getFkCarId());
			if (list == null || list.size() <= 1) {
				carInsDetail.setInsEndDate(this.getNextDate(carInsDetail.getInsStartDate()).getTime());
				carInsDetail.setCreateUserId(curUser.getId());
				carInsDetail.setCreateUserName(curUser.getName());
				carInsDetail.setCreateDate(new Date());
				carInsDetailDao.save(carInsDetail);
				if (list == null || list.size() == 0) {
					CarIns carIns = new CarIns();
					carIns.setFkCarId(carInsDetail.getFkCarId());
					carIns.setFkInsDetailId(carInsDetail.getId());
					carIns.setDataStatus("0");
					carInsDao.save(carIns);
				} else if (list.size() == 1) {
					CarIns carIns = list.get(0);
					carIns.setFkCarId(carInsDetail.getFkCarId());
					carIns.setFkInsDetailId(carInsDetail.getId());
					carInsDao.save(carIns);
				}
				return JsonWrapper.successWrapper("操作成功！");
			} else {
				return JsonWrapper.failureWrapperMsg("车辆当前保险信息不正确！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("操作失败！");
		}
	}

	/**
	 * 删除保险信息
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> deleteInfo(HttpServletRequest request, String id) throws Exception {
		try {
			CarInsDetail carInsDetail = carInsDetailDao.get(id);
			carInsDetail.setDataStatus("99");
			carInsDetailDao.save(carInsDetail);
			List<CarIns> list = carInsDao.getCarInssByFkInsDetailId(carInsDetail.getId());
			if (list != null && list.size() != 0) {
				for (CarIns carIns : list) {
					carIns.setFkInsDetailId(null);
					carInsDao.save(carIns);
				}
			}
			return JsonWrapper.successWrapper("操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("操作失败！");
		}
	}

	/**
	 * 获取下一个日期（当前日期加一年减一天）
	 * 
	 * @param date
	 * @return
	 */
	public Calendar getNextDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, 1);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
		return calendar;
	}

	/**
	 * 保存历史保险信息
	 * 
	 * @param request
	 * @param carInsDetail
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> saveHistoryInfo(HttpServletRequest request, CarInsDetail carInsDetail)
			throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		String pageStatus = request.getParameter("pageStatus");
		try {
			if ("add".equals(pageStatus)) {
				carInsDetail.setCreateUserId(curUser.getId());
				carInsDetail.setCreateUserName(curUser.getName());
				carInsDetail.setCreateDate(new Date());
			} else if ("modify".equals(pageStatus)) {
				carInsDetail.setLastModifyUserId(curUser.getId());
				carInsDetail.setLastModifyUserName(curUser.getName());
				carInsDetail.setLastModifyDate(new Date());
				List<CarIns> list = carInsDao.getCarInssByFkInsDetailId(carInsDetail.getId());
				if (list != null && list.size() != 0) {
					for (CarIns carIns : list) {
						carIns.setFkCarId(carInsDetail.getFkCarId());
						carIns.setFkInsDetailId(carInsDetail.getId());
						carInsDao.save(carIns);
					}
				}
			}
			carInsDetail.setInsEndDate(this.getNextDate(carInsDetail.getInsStartDate()).getTime());
			carInsDetailDao.save(carInsDetail);
			return JsonWrapper.successWrapper("操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("操作失败！");
		}
	}

	/**
	 * 车辆保险即将到期提醒任务
	 */
	public void doInsRemind() {
		try {
			List<?> list = carInsDetailDao.getInsRemindData();
			if (list != null && list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					Object[] obj = (Object[]) list.get(i);
					String carId = obj[0].toString();
					String carNum = obj[1].toString();
					Date insStartDate = (Date) obj[2];
					Date insEndDate = (Date) obj[3];
					map.put("carId", carId);
					map.put("carNum", carNum);
					map.put("insStartDate", DateUtil.format(insStartDate, Constant.defaultDatePattern));
					map.put("insEndDate", DateUtil.format(insEndDate, Constant.defaultDatePattern));
					sendInsRemindMsg(map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
	}

	/**
	 * 发送消息提醒
	 * 
	 * @param map
	 */
	private void sendInsRemindMsg(HashMap<String, Object> map) {
		try {
			String msg_code = "car_ins_remind";
			String[] emp_ids = null;
			String emp_id = Factory.getSysPara().getProperty("CAR_INS_REMIND_REVEIVE");
			if (StringUtil.isNotEmpty(emp_id)) {
				emp_ids = emp_id.split(",");
				if (emp_ids != null) {
					for (int i = 0; i < emp_ids.length; i++) {
						Employee employee = employeesService.get(emp_ids[i]);
						if (StringUtil.isNotEmpty(employee.getMobileTel())) {
							messageService.sendMassageByConfig(null, null, employee.getMobileTel(), null, msg_code,
									null, map, null, null, Constant.OFFICE_CORPID, Constant.OFFICE_PWD);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
	}
}
