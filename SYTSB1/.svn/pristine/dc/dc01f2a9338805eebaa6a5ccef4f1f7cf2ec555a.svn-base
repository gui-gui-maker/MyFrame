package com.scts.car.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.scts.car.bean.CarCm;
import com.scts.car.bean.CarIns;

/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName CarCmDao
 * @JDK 1.5
 * @author
 * @date
 */
@Repository("carCmDao")
public class CarCmDao extends EntityDaoImpl<CarCm> {

	/**
	 * 根据车辆id获取中间表信息
	 * 
	 * @param fkCarId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CarCm> getCarCmsByFkCarId(String fkCarId) {
		List<CarCm> list = new ArrayList<CarCm>();
		String hql = "from CarCm t where t.fkCarId=? and t.dataStatus <> '99'";
		list = this.createQuery(hql, fkCarId).list();
		return list;
	}

	/**
	 * 根据保养信息id获取中间表信息
	 * 
	 * @param fkCmDetailId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CarCm> getCarCmsByFkCmDetailId(String fkCmDetailId) {
		List<CarCm> list = new ArrayList<CarCm>();
		String hql = "from CarCm t where t.fkCmDetailId=? and t.dataStatus <> '99'";
		list = this.createQuery(hql, fkCmDetailId).list();
		return list;
	}
}