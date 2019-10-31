package com.lsts.equipment2.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.equipment2.bean.EquipmentCentre;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName EquipmentCentreDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("equipmentCentreDao")
public class EquipmentCentreDao extends EntityDaoImpl<EquipmentCentre> {

	/**
	 * 根据领用/借用记录id获取领用/借用关联信息
	 * @param fk_record_id -- 领用/借用id
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2015-11-21 12:56:00
	 */
	@SuppressWarnings("unchecked")
	public List<EquipmentCentre> getInfos(String fk_record_id) {
		List<EquipmentCentre> list = new ArrayList<EquipmentCentre>();
		String hql = "from EquipmentCentre t where t.equipmentLoan.id=? ";
		list = this.createQuery(hql, fk_record_id).list();
		return list;
	}
	/**
	 * 根据记录ID删除中间表记录
	 */
	public void delete(String recordId){
			String deleteSql = "delete TJY2_EQUIPMENT_LOAN_CENTRE  where FK_RECORD_ID = ?";
			this.createSQLQuery(deleteSql,recordId).executeUpdate();
	}
}
