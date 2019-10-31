package com.scts.car.service;

import com.khnt.base.Factory;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.oa.car.bean.CarInfo;
import com.khnt.oa.car.dao.CarInfoDao;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;
import com.lsts.employee.service.EmployeesService;
import com.scts.car.bean.CarCm;
import com.scts.car.bean.CarCmDetail;
import com.scts.car.dao.CarCmDao;
import com.scts.car.dao.CarCmDetailDao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("carCmDetailManager")
public class CarCmDetailManager extends EntityManageImpl<CarCmDetail, CarCmDetailDao> {
	@Autowired
	CarCmDetailDao carCmDetailDao;
	@Autowired
	CarCmDao carCmDao;
	@Autowired
	CarInfoDao carInfoDao;
	@Autowired
	private MessageService messageService;
	@Autowired
	private EmployeesService employeesService;

	/**
	 * 保存保养提醒信息
	 * 
	 * @param request
	 * @param carCm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> saveInfo(HttpServletRequest request, CarCmDetail carCmDetail) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		List<CarCm> list = carCmDao.getCarCmsByFkCarId(carCmDetail.getFkCarId());
		try {
			if (list == null || list.size() <= 1) {
				carCmDetail.setCreateUserId(curUser.getId());
				carCmDetail.setCreateUserName(curUser.getName());
				carCmDetail.setCreateDate(new Date());
				carCmDetailDao.save(carCmDetail);
				if (list == null || list.size() == 0) {
					CarCm carCm = new CarCm();
					carCm.setFkCarId(carCmDetail.getFkCarId());
					carCm.setFkCmDetailId(carCmDetail.getId());
					carCm.setDataStatus("0");
					carCmDao.save(carCm);
				} else if (list.size() == 1) {
					CarCm carCm = list.get(0);
					carCm.setFkCarId(carCmDetail.getFkCarId());
					carCm.setFkCmDetailId(carCmDetail.getId());
					carCm.setDataStatus("0");
					carCmDao.save(carCm);
				}
				return JsonWrapper.successWrapper("操作成功！");
			} else {
				return JsonWrapper.failureWrapperMsg("车辆当前保养提醒信息不正确！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("操作失败！");
		}
	}

	/**
	 * 获取保养提醒信息详情
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getDetail(String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			// 获取保养提醒信息
			CarCmDetail carCmDetail = carCmDetailDao.get(id);
			// 获取车辆信息
			CarInfo carInfo = carInfoDao.get(carCmDetail.getFkCarId());
			map.put("data", carCmDetail);
			map.put("carInfo", carInfo);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
		return map;
	}

	/**
	 * 删除保养提醒信息
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> deleteInfo(HttpServletRequest request, String id) throws Exception {
		try {
			CarCmDetail carCmDetail = carCmDetailDao.get(id);
			carCmDetail.setDataStatus("99");
			carCmDetailDao.save(carCmDetail);
			List<CarCm> list = carCmDao.getCarCmsByFkCmDetailId(carCmDetail.getId());
			if (list != null && list.size() != 0) {
				for (CarCm carCm : list) {
					carCm.setFkCmDetailId(null);
					;
					carCmDao.save(carCm);
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
	 * 保存历史保养提醒信息
	 * 
	 * @param request
	 * @param carInsDetail
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> saveHistoryInfo(HttpServletRequest request, CarCmDetail carCmDetail)
			throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		String pageStatus = request.getParameter("pageStatus");
		try {
			if ("add".equals(pageStatus)) {
				carCmDetail.setCreateUserId(curUser.getId());
				carCmDetail.setCreateUserName(curUser.getName());
				carCmDetail.setCreateDate(new Date());
			} else if ("modify".equals(pageStatus)) {
				carCmDetail.setLastModifyUserId(curUser.getId());
				carCmDetail.setLastModifyUserName(curUser.getName());
				carCmDetail.setLastModifyDate(new Date());
			}
			carCmDetailDao.save(carCmDetail);
			return JsonWrapper.successWrapper("操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("操作失败！");
		}
	}

	/**
	 * 车辆即将需要保养提醒任务
	 */
	public void doCmRemind() {
		try {
			List<?> list = carCmDetailDao.getCmRemindData();
			if (list != null && list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					Object[] obj = (Object[]) list.get(i);
					String carId = obj[0].toString();
					String carNum = obj[1].toString();
					String carMileage = obj[2].toString();
					String cmKm = obj[3].toString();
					Date cmDate = (Date) obj[4];
					String nextCmKm = obj[5].toString();
					Date nextCmDate = (Date) obj[6];
					map.put("carId", carId);
					map.put("carNum", carNum);
					map.put("carMileage", carMileage);
					map.put("cmKm", cmKm);
					map.put("cmDate", DateUtil.format(cmDate, Constant.defaultDatePattern));
					map.put("nextCmKm", nextCmKm);
					map.put("nextCmDate", DateUtil.format(nextCmDate, Constant.defaultDatePattern));
					sendCmRemindMsg(map);
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
	private void sendCmRemindMsg(HashMap<String, Object> map) {
		try {
			String msg_code = "car_ins_remind";
			String[] emp_ids = null;
			String emp_id = Factory.getSysPara().getProperty("CAR_CM_REMIND_REVEIVE");
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
