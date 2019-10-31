package com.scts.car.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.scts.car.bean.CarInsDetail;

/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName CarInsDetailDao
 * @JDK 1.5
 * @author
 * @date
 */
@Repository("carInsDetailDao")
public class CarInsDetailDao extends EntityDaoImpl<CarInsDetail> {

	/**
	 * 查询保险即将到期车辆信息
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	public List<?> getInsRemindData() {
		String sql = "select t.id, t.car_num, d.ins_start_date, d.ins_end_date "
				+ " from OA_CAR_INFO t, TJY2_CAR_INS i, TJY2_CAR_INS_DETAIL d where t.id = i.fk_car_id(+) "
				+ " and i.fk_ins_detail_id = d.id(+) and t.state <> '99' "
				+ " and d.ins_end_date is not null and ceil(d.ins_end_date - sysdate) = 30 ";
		List<?> list = this.createSQLQuery(sql).list();
		return list;
	}
}