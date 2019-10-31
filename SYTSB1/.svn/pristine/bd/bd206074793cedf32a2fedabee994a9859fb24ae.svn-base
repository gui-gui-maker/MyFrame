package com.scts.car.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.scts.car.bean.CarWbRecordItem;

/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName CarWbRecordItemDao
 * @JDK 1.5
 * @author
 * @date
 */
@Repository("carWbRecordItemDao")
public class CarWbRecordItemDao extends EntityDaoImpl<CarWbRecordItem> {

	/**
	 * 根据维保记录主表id查询维保明细
	 * 
	 * @param fkWbId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CarWbRecordItem> getWbRecordItems(String fkWbRecordId) {
		List<CarWbRecordItem> list = new ArrayList<CarWbRecordItem>();
		String hql = "from CarWbRecordItem t where t.fkWbRecordId=? and t.dataStatus <> '99'";
		list = this.createQuery(hql, fkWbRecordId).list();
		return list;
	}

	/**
	 * 根据维保记录主表id删除维保明细（逻辑删除）
	 * 
	 * @param fkWbId
	 * @return
	 */
	public void deleteWbRecordItemsLogic(String fkWbRecordId) {
		String hql = "update CarWbRecordItem t set t.dataStatus = '99' where t.fkWbRecordId=? and t.dataStatus <> '99'";
		this.createQuery(hql, fkWbRecordId).executeUpdate();
	}

	/**
	 * 根据维保记录主表id删除维保明细（物理删除）
	 * 
	 * @param fkWbId
	 * @return
	 */
	public void deleteWbRecordItemsPhy(String fkWbRecordId) throws Exception {
		String sql = "delete from TJY2_CAR_WB_RECORD_ITEM t where t.FK_WB_RECORD_ID=?";
		this.createSQLQuery(sql, fkWbRecordId).executeUpdate();
	}

}