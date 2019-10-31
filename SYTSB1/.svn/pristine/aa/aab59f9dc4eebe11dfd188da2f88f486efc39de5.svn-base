package com.khnt.oa.car.dao;

import java.util.List;
import java.util.Map;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.oa.car.bean.CarInfo;
import com.khnt.utils.StringUtil;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName CarInfoDao
 * @JDK 1.5
 * @author
 * @date 2011-5-4 下午03:25:06
 */
@Repository("carInfoDao")
public class CarInfoDao extends EntityDaoImpl<CarInfo> {

	public List<Map<String, Object>> queryCarType(String type) {
		String sql = "select car.*,(case when car.type='1' THEN '一类车辆' when car.type='2' THEN '二类车辆' when car.type='3' THEN '三类车辆' \n"
				+ "when car.type='4' then '其他类车辆'  when car.type='5' THEN '维修车辆' end) types from OA_CAR_INFO car \n"
				+ "where car.type=? and car.STATE='0' ";
		Query query = this.createSQLQuery(sql, type).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list = query.list();
		return list;
	}

	public void updateRepairType(String ids, String repair_type) {
		String arr[] = ids.split(",");
		for (int i = 0; i < arr.length; i++) {
			String sql = "update OA_CAR_INFO t set t.repair_type = ? where t.id= ?";
			this.createSQLQuery(sql, repair_type, arr[i]).executeUpdate();
		}
	}
	/**
	 * 获取所有车辆信息
	 * @return
	 */
	public List<CarInfo> getAllCarInfo() {
		String hql ="from CarInfo where state != '99'";
		List<CarInfo> list = this.createQuery(hql).list();
		return list;
	}
}
