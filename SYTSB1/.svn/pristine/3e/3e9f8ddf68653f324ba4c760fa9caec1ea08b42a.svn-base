package com.scts.car.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.scts.car.bean.CarFuelCard;
import com.scts.car.bean.CarMpg;

/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName CarFuelCardDao
 * @JDK 1.5
 * @author
 * @date
 */
@Repository("carFuelCardDao")
public class CarFuelCardDao extends EntityDaoImpl<CarFuelCard> {

	/**
	 * 获取给定时间的所有车辆油卡信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CarFuelCard> getCarFuelCardByYearMonth(Date yearMonth) {
		String hql = "from CarFuelCard where dataStatus != '99' and yearMonth = ?";
		List<CarFuelCard> list = this.createQuery(hql, yearMonth).list();
		return list;
	}

	/**
	 * 根据车辆id、年月份、油卡号查询油卡记录 （若车辆id不存在则依据油卡号查询条件）
	 * 
	 * @param fkCarId
	 * @param yearMonth
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CarFuelCard> getCarFuelCard(String fkCarId, Date yearMonth, String cardNum) {
		String hql = "from CarFuelCard where dataStatus != '99' and yearMonth = ? ";
		if (StringUtil.isNotEmpty(fkCarId)) {
			hql += "and fkCarId = '" + fkCarId + "' ";
		} else {
			hql += "and cardNum = '" + cardNum + "' ";
		}
		List<CarFuelCard> list = this.createQuery(hql, yearMonth).list();
		return list;
	}
}