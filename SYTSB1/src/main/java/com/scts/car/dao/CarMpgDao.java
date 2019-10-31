package com.scts.car.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.scts.car.bean.CarMpg;

/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName CarMpgDao
 * @JDK 1.5
 * @author
 * @date
 */
@Repository("carMpgDao")
public class CarMpgDao extends EntityDaoImpl<CarMpg> {

	/**
	 * 获取给定时间的所有车辆油耗信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CarMpg> getCarMpgByYearMonth(Date yearMonth) {
		String hql = "from CarMpg where dataStatus != '99' and yearMonth = ?";
		List<CarMpg> list = this.createQuery(hql, yearMonth).list();
		return list;
	}

	/**
	 * 根据车辆id和年月份查询油耗记录
	 * 
	 * @param fkCarId
	 * @param yearMonth
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CarMpg> getCarMpg(String fkCarId, Date yearMonth) {
		String hql = "from CarMpg where dataStatus != '99' and fkCarId = ? and yearMonth = ?";
		List<CarMpg> list = this.createQuery(hql, fkCarId, yearMonth).list();
		return list;
	}
}