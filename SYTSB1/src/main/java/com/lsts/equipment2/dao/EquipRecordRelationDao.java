package com.lsts.equipment2.dao;

import com.lsts.equipment2.bean.EquipmentBuy;
import com.lsts.equipment2.bean.EquipRecordRelation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository; 
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
/**
 * 
 * @ClassName  EquipRecordRelationDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("EquipRecordRelationDao")
public class EquipRecordRelationDao extends EntityDaoImpl<EquipRecordRelation> {
	//保存到中间表
	public void addEquipRecordRelation(EquipRecordRelation equipCenter){
		this.save(equipCenter);
	}
	/**
	 * 根据中间表ID查出记录ID
	 */
	public String getRecordId(String centerId){
		List<EquipRecordRelation> list = new ArrayList<EquipRecordRelation>();
		EquipRecordRelation equipRecordRelation = new EquipRecordRelation();
		String recordId;
		try {
			String hql = "from EquipRecordRelation where ID = ?";
			list = this.createQuery(hql,centerId).list();
			equipRecordRelation = list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		recordId = equipRecordRelation.getInstock_id();
		return recordId;
	}
	/**
	 * 根据记录ID查出设备的数目
	 */
	public int countEquip(String recordId){
		String sql = "select t.* from TJY2_EQUIPMENT_CENTER t where t.instock_id = ?";
		int count = this.createSQLQuery(sql, recordId).list().size();
		return count;
	}
	/**
	 * 根据ID删除中间表设备
	 */
	public void deleteByCenterId(String centerId){
		String sql = "delete  from TJY2_EQUIPMENT_CENTER t where t.id = ?";
		this.createSQLQuery(sql, centerId).executeUpdate();
	}
	
}