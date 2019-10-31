package com.scts.car.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.scts.car.bean.CarWbRecord;

/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName CarWbRecordDao
 * @JDK 1.5
 * @author
 * @date
 */
@Repository("carWbRecordDao")
public class CarWbRecordDao extends EntityDaoImpl<CarWbRecord> {
	/**
	 * 根据维保申请id获取维保记录
	 * 
	 * @param fkWbId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CarWbRecord> getWbRecordsByFkWbId(String fkWbId) throws Exception {
		String hql = "from CarWbRecord where fkWbId = ? and dataStatus <> '99'";
		List<CarWbRecord> list = new ArrayList<CarWbRecord>();
		try {
			if (StringUtil.isNotEmpty(fkWbId)) {
				list = this.createQuery(hql, fkWbId).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据车辆id获取维保记录
	 * 
	 * @param fkCarId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CarWbRecord> getWbRecordsByFkCarId(String fkCarId) throws Exception {
		String hql = "from CarWbRecord where fkCarId = ? and dataStatus <> '99'";
		List<CarWbRecord> list = new ArrayList<CarWbRecord>();
		try {
			if (StringUtil.isNotEmpty(fkCarId)) {
				list = this.createQuery(hql, fkCarId).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}