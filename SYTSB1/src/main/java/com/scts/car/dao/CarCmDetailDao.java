package com.scts.car.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.scts.car.bean.CarCmDetail;

/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName CarCmDetailDao
 * @JDK 1.5
 * @author
 * @date
 */
@Repository("carCmDetailDao")
public class CarCmDetailDao extends EntityDaoImpl<CarCmDetail> {

	/**
	 * 根据业务id更新数据状态
	 * 
	 * @param id
	 * @param dataStatus
	 */
	public void updateDataStatus(String id, String dataStatus) {
		String sql = "update TJY2_CAR_CM_DETAIL t set t.data_status = ? where t.id = ?";
		this.createSQLQuery(sql, dataStatus, id).executeUpdate();
	}

	/**
	 * 查询即将需要保养的车辆信息
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	public List<?> getCmRemindData() {
		String sql = "select t.id, t.car_num, t.car_mileage, d.cm_km, d.cm_date, d.next_cm_km, d.next_cm_date "
				+ " from OA_CAR_INFO t, TJY2_CAR_CM m, TJY2_CAR_CM_DETAIL d  where t.id = m.fk_car_id(+) "
				+ " and m.fk_cm_detail_id = d.id(+) and t.state <> '99' "
				+ " and (d.next_cm_date - sysdate <= 0 or d.next_cm_km - t.car_mileage <= 0) ";
		List<?> list = this.createSQLQuery(sql).list();
		return list;
	}
}