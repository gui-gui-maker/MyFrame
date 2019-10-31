package com.lsts.equipment2.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository; 
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.equipment2.bean.Equipment;
import com.lsts.equipment2.bean.EquipmentApplyList;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName Tjy2EquipApplyListDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("EquipmentApplyListDao")
public class EquipmentApplyListDao extends EntityDaoImpl<EquipmentApplyList> {
/**
 * 删除申请设备
 */
	public void delete(String id) {
		String deleteSql = "delete TJY2_EQUIP_APPLY_LIST  where APPLY_LIST_ID = ?";
		this.createSQLQuery(deleteSql,id).executeUpdate();
    }
/**
 * 通过申请ID查找购买申请的设备
 */
	public List<EquipmentApplyList> getEquipmentApplyList(String id) {
		List<EquipmentApplyList> equipmentApplyLists = new ArrayList<EquipmentApplyList>();
		try {
			String hql = "from EquipmentApplyList  where APPLY_LIST_ID = ?";
			equipmentApplyLists = this.createQuery(hql,id).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return equipmentApplyLists;
    }
}