package com.scts.car.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.oa.car.bean.CarInfo;
import com.khnt.oa.car.dao.CarInfoDao;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.scts.car.bean.CarMpg;
import com.scts.car.dao.CarMpgDao;

@Service("carMpgManager")
public class CarMpgManager extends EntityManageImpl<CarMpg, CarMpgDao> {
	@Autowired
	CarMpgDao carMpgDao;
	@Autowired
	CarInfoDao carInfoDao;

	/**
	 * 获取油耗信息详情
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getDetail(String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			// 获取油耗信息
			CarMpg carMpg = carMpgDao.get(id);
			// 获取车辆信息
			CarInfo carInfo = carInfoDao.get(carMpg.getFkCarId());
			map.put("data", carMpg);
			map.put("carInfo", carInfo);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
		return map;
	}

	/**
	 * 保存油耗信息
	 * 
	 * @param request
	 * @param carCm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> saveInfo(HttpServletRequest request, CarMpg carMpg) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		String pageStatus = request.getParameter("pageStatus");
		try {
			if ("add".equals(pageStatus)) {
				carMpg.setCreateUserId(curUser.getId());
				carMpg.setCreateUserName(curUser.getName());
				carMpg.setCreateDate(new Date());
			} else if ("modify".equals(pageStatus)) {
				carMpg.setLastModifyUserId(curUser.getId());
				carMpg.setLastModifyUserName(curUser.getName());
				carMpg.setLastModifyDate(new Date());
			}
			carMpgDao.save(carMpg);
			return JsonWrapper.successWrapper("操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("操作失败！");
		}
	}

	/**
	 * 油耗统计定时任务
	 */
	public void carMpgUpdate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			System.out.println("=======================================执行油耗更新定时任务 开始");
			// 获取上一月的第一天
			Calendar cal_1 = Calendar.getInstance();// 获取当前日期
			cal_1.add(Calendar.MONTH, -1);
			cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为上月第一天
			Date firstDayLast = sdf.parse((sdf.format(cal_1.getTime())));
			// 获取当前月第一天
			Calendar cal_2 = Calendar.getInstance();
			cal_2.add(Calendar.MONTH, 0);
			cal_2.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
			Date firstDayNow = sdf.parse((sdf.format(cal_2.getTime())));

			System.out.println("=======================================执行月初更新");
			List<CarInfo> carInfoList = carInfoDao.getAllCarInfo();
			if (carInfoList != null && carInfoList.size() != 0) {
				System.out.println("=======================================执行月初更新 开始");
				List<CarMpg> carMpgListNow = carMpgDao.getCarMpgByYearMonth(firstDayNow);// 获取当前月份的油耗信息
				List<CarMpg> carMpgListLast = carMpgDao.getCarMpgByYearMonth(firstDayLast);// 获取上一月份的油耗信息
				for (CarInfo carInfo : carInfoList) {
					if (carMpgListNow != null && carMpgListNow.size() != 0) {
						Boolean isExits = false;// 是否已存在当月油耗信息
						for (int i = 0; i < carMpgListNow.size(); i++) {
							if (carInfo.getId().equals(carMpgListNow.get(i).getFkCarId())) {
								isExits = true;
								break;
							}
						}
						if (!isExits) {
							CarMpg carMpgNew = new CarMpg();
							carMpgNew.setFkCarId(carInfo.getId());
							carMpgNew.setYearMonth(firstDayNow);
							carMpgNew.setDataStatus("0");
							if (carMpgListLast != null & carMpgListLast.size() != 0) {
								for (int j = 0; j < carMpgListLast.size(); j++) {
									CarMpg carMpgLast = carMpgListLast.get(j);
									if (carInfo.getId().equals(carMpgLast.getFkCarId())) {
										carMpgNew.setKmInitial(carMpgLast.getKmFinal());
									}
								}
							}
							carMpgDao.save(carMpgNew);
						}
					} else {
						CarMpg carMpgNew = new CarMpg();
						carMpgNew.setFkCarId(carInfo.getId());
						carMpgNew.setYearMonth(firstDayNow);
						carMpgNew.setDataStatus("0");
						if (carMpgListLast != null & carMpgListLast.size() != 0) {
							for (int j = 0; j < carMpgListLast.size(); j++) {
								CarMpg carMpgLast = carMpgListLast.get(j);
								if (carInfo.getId().equals(carMpgLast.getFkCarId())) {
									carMpgNew.setKmInitial(carMpgLast.getKmFinal());
								}
							}
						}
						carMpgDao.save(carMpgNew);
					}
				}
				System.out.println("=======================================执行月初更新 结束");
			}
			System.out.println("=======================================执行油耗更新定时任务 结束");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
	}

	/**
	 * 根据车辆id和年月份查询油耗记录
	 * 
	 * @param fkCarId
	 * @param yearMonth
	 * @return
	 */
	public List<CarMpg> getCarMpg(String fkCarId, Date yearMonth) {
		List<CarMpg> list = carMpgDao.getCarMpg(fkCarId, yearMonth);
		return list;
	}
}
