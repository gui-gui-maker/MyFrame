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
import com.khnt.utils.StringUtil;
import com.scts.car.bean.CarFuelCard;
import com.scts.car.bean.CarMpg;
import com.scts.car.dao.CarFuelCardDao;

@Service("carFuelCardManager")
public class CarFuelCardManager extends EntityManageImpl<CarFuelCard, CarFuelCardDao> {
	@Autowired
	CarFuelCardDao carFuelCardDao;
	@Autowired
	CarInfoDao carInfoDao;

	/**
	 * 获取油卡信息详情
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getDetail(String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			// 获取油卡信息
			CarFuelCard carFuelCard = carFuelCardDao.get(id);
			// 获取车辆信息
			CarInfo carInfo = new CarInfo();
			if (StringUtil.isNotEmpty(carFuelCard.getFkCarId())) {
				carInfo = carInfoDao.get(carFuelCard.getFkCarId());
			}
			map.put("data", carFuelCard);
			map.put("carInfo", carInfo);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
		return map;
	}

	/**
	 * 保存油卡信息
	 * 
	 * @param request
	 * @param carCm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> saveInfo(HttpServletRequest request, CarFuelCard carFuelCard) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		String pageStatus = request.getParameter("pageStatus");
		try {
			if ("add".equals(pageStatus)) {
				carFuelCard.setCreateUserId(curUser.getId());
				carFuelCard.setCreateUserName(curUser.getName());
				carFuelCard.setCreateDate(new Date());
			} else if ("modify".equals(pageStatus)) {
				carFuelCard.setLastModifyUserId(curUser.getId());
				carFuelCard.setLastModifyUserName(curUser.getName());
				carFuelCard.setLastModifyDate(new Date());
			}
			carFuelCardDao.save(carFuelCard);
			return JsonWrapper.successWrapper("操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("操作失败！");
		}
	}

	/**
	 * 油卡统计定时任务
	 */
	public void carFuelCardUpdate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			System.out.println("=======================================执行油卡更新定时任务 开始");
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
			System.out.println("=======================================执行月初更新 开始");
			List<CarFuelCard> carFuelCardListNow = carFuelCardDao.getCarFuelCardByYearMonth(firstDayNow);// 获取当前月份的油卡信息
			List<CarFuelCard> carFuelCardListLast = carFuelCardDao.getCarFuelCardByYearMonth(firstDayLast);// 获取上一月份的油卡信息
			if (carFuelCardListLast != null && carFuelCardListLast.size() != 0) {
				if (carFuelCardListNow != null && carFuelCardListNow.size() != 0) {
					for (int i = 0; i < carFuelCardListLast.size(); i++) {
						Boolean isExits = false;// 是否已存在当月油卡信息
						CarFuelCard carFuelCardLast = carFuelCardListLast.get(i);
						for (int j = 0; j < carFuelCardListNow.size(); j++) {
							CarFuelCard carFuelCardNow = carFuelCardListNow.get(j);
							if (carFuelCardLast.getCardNum().equals(carFuelCardNow.getCardNum())) {
								isExits = true;
								break;
							}
						}
						if (!isExits) {
							CarFuelCard carFuelCardNew = new CarFuelCard();
							carFuelCardNew.setYearMonth(firstDayNow); // 年月份
							carFuelCardNew.setCardNum(carFuelCardLast.getCardNum()); // 卡号
							carFuelCardNew.setFkCarId(carFuelCardLast.getFkCarId()); // 车辆id
							carFuelCardNew.setCardType(carFuelCardLast.getCardType()); // 卡类别
							carFuelCardNew.setMoneyInitial(carFuelCardLast.getMoneyLeft());
							carFuelCardNew.setUseDepartmentId(carFuelCardLast.getUseDepartmentId());
							carFuelCardNew.setUseDepartment(carFuelCardLast.getUseDepartment());
							carFuelCardNew.setDataStatus("0");
							carFuelCardDao.save(carFuelCardNew);
						}
					}
				} else {
					for (int j = 0; j < carFuelCardListLast.size(); j++) {
						CarFuelCard carFuelCardLast = carFuelCardListLast.get(j);
						CarFuelCard carFuelCardNew = new CarFuelCard();
						carFuelCardNew.setYearMonth(firstDayNow); // 年月份
						carFuelCardNew.setCardNum(carFuelCardLast.getCardNum()); // 卡号
						carFuelCardNew.setFkCarId(carFuelCardLast.getFkCarId()); // 车辆id
						carFuelCardNew.setCardType(carFuelCardLast.getCardType()); // 卡类别
						carFuelCardNew.setMoneyInitial(carFuelCardLast.getMoneyLeft());
						carFuelCardNew.setUseDepartmentId(carFuelCardLast.getUseDepartmentId());
						carFuelCardNew.setUseDepartment(carFuelCardLast.getUseDepartment());
						carFuelCardNew.setDataStatus("0");
						carFuelCardDao.save(carFuelCardNew);
					}
				}
			}
			System.out.println("=======================================执行月初更新 结束");
			System.out.println("=======================================执行油卡更新定时任务 结束");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
	}

	/**
	 * 根据车辆id、年月份、油卡号查询油卡记录 （若车辆id不存在则依据油卡号查询条件）
	 * 
	 * @param fkCarId
	 * @param yearMonth
	 * @return
	 */
	public List<CarFuelCard> getCarFuelCard(String fkCarId, Date yearMonth, String cardNum) {
		List<CarFuelCard> list = carFuelCardDao.getCarFuelCard(fkCarId, yearMonth, cardNum);
		return list;
	}
}
