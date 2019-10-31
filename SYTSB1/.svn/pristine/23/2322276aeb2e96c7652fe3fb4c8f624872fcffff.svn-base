package com.lsts.equipment2.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.equipment2.bean.EquipInstockRecord;
import com.lsts.equipment2.bean.EquipmentUseRegister;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName EquipmentUseRegisterDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("equipmentUseRegisterDao")
public class EquipmentUseRegisterDao extends EntityDaoImpl<EquipmentUseRegister> {
	//通过equip_id查询设备所有使用登记记录
	@SuppressWarnings("unchecked")
	public List<EquipmentUseRegister> searchRecord(String equip_id) {
		List<EquipmentUseRegister> useRecords = new ArrayList<EquipmentUseRegister>();
		try{
			String hql = "from EquipmentUseRegister where EQUIP_ID = ?";
			useRecords = this.createQuery(hql,equip_id).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return useRecords;
	}
	//保存归还时间
	@SuppressWarnings("unchecked")
	public void saveReturn(String equip_id,EquipInstockRecord equipInstockRecord) {
		List<EquipmentUseRegister> useRecords = new ArrayList<EquipmentUseRegister>();
		EquipmentUseRegister useRecord = new EquipmentUseRegister();
		try {
			String hql = "from EquipmentUseRegister e where e.equipId = ? and e.returnTime is null";
			useRecords = this.createQuery(hql,equip_id).list();
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(useRecords!=null&&useRecords.size()>0){
			useRecord = useRecords.get(0);
			useRecord.setReturnTime(equipInstockRecord.getInstock_date());
			useRecord.setStatus("02");//已归还
			this.save(useRecord);
		}else{
			System.out.println("此设备暂无使用记录！");
		}
	}
}