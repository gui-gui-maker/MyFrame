package com.khnt.oa.car.dao;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.oa.car.bean.Apply;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName ApplyDao
 * @JDK 1.5
 * @author 
 * @date 2011-5-4 下午03:25:06
 */
@Repository("applyDao")
public class ApplyDao extends EntityDaoImpl<Apply> {
	
	public List<Apply> getApplyByUsedCar(String carid){
		try{
		String hql="from Apply where car.id=? and (state='未派车' or state='已派车')";
		Query q=this.createQuery(hql, carid);
		List<Apply> list=q.list();
		return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}

	public List<Map<String, Object>> exportHis(String ids) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		
		String infoId = "'"+ids.replaceAll(",", "','")+"'" ;
		StringBuffer sql = new StringBuffer();
		sql.append("select c.CAR_NUM,"
				+ " t.start_time, "
				+ "o.org_name,"
				+ " t.destination,"
				+ " t.used_car_reason,"
				+ " t.business_travel_approval,"
				+ " t.driver,"
				+ "t.send_car_man,"
				+ "t.start_km,"
				+ "t.end_km,"
				+ " t.end_time,"
				+ " t.refuel_record,"
				+ "t.editor"
				+ " from oa_car_apply t, oa_car_info c, sys_org o "
				+ "where t.car_id = c.id"
				+ " and o.id = c.manager_room_code");
		sql.append(" and t.id in (").append(infoId).append(") order by t.start_time desc");
		List result = this.createSQLQuery(sql.toString()).list();
		for (int i = 0; i < result.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			Object[] objArr = result.toArray();
			Object[] obj = (Object[]) objArr[i];
			
			map.put("carNum", obj[0]);
			map.put("startTime", obj[1].toString().substring(0, 16));
			
			map.put("orgName", obj[2]);
			map.put("destination", obj[3]);
			map.put("usedCarReason", obj[4]);
			map.put("businessTravelApproval", obj[5]);
			map.put("driver", obj[6]);
			
			map.put("sendCarMan", obj[7]);
			map.put("startKm", obj[8]);
			map.put("endKm", obj[9]);
			map.put("endTime", obj[10].toString().substring(0, 16));
			map.put("refuelRecord", obj[11]);
			map.put("editor", obj[12]);
			
			resultList.add(map);
		}
		
		return resultList;
	}
}
