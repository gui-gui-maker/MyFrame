package com.scts.car.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.scts.car.bean.CarIns;

/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName CarInsDao
 * @JDK 1.5
 * @author
 * @date
 */
@Repository("carInsDao")
public class CarInsDao extends EntityDaoImpl<CarIns> {

	/**
	 * 根据车辆id获取车辆保险提醒信息
	 * @param fkCarId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CarIns> getCarInssByFkCarId(String fkCarId) {
		List<CarIns> list = new ArrayList<CarIns>();
		String hql = "from CarIns t where t.fkCarId=? and t.dataStatus <> '99'";
		list = this.createQuery(hql, fkCarId).list();
		return list;
	}
	/**
	 * 根据保险信息id获取车辆保险提醒信息
	 * @param fkInsDetailId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CarIns> getCarInssByFkInsDetailId(String fkInsDetailId) {
		List<CarIns> list = new ArrayList<CarIns>();
		String hql = "from CarIns t where t.fkInsDetailId=? and t.dataStatus <> '99'";
		list = this.createQuery(hql, fkInsDetailId).list();
		return list;
	}

}