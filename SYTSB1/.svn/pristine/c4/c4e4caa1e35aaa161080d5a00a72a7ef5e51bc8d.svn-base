package com.lsts.equipment2.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.equipment2.bean.EquipmentCentre;
import com.lsts.equipment2.bean.EquipmentLoan;
import com.lsts.equipment2.dao.EquipmentCentreDao;

@Service("equipmentCentreManager")
public class EquipmentCentreManager extends EntityManageImpl<EquipmentCentre, EquipmentCentreDao>{
	
	@Autowired
	EquipmentCentreDao equipmentCentreDao;
	/**
	 * 根据申请记录ID获取中间表相关信息
	 */
	public List<EquipmentCentre> queryEquipmentCentre(String id) throws Exception {
		//根据申请记录ID获取中间表相关信息
		List<EquipmentCentre> equipmentCentres= new ArrayList<EquipmentCentre>();
		equipmentCentres = equipmentCentreDao.getInfos(id);
		return equipmentCentres;
	}
	/**
	 * 根据记录ID删除中间表的记录
	 */
	public void delete(String recordId){
		equipmentCentreDao.delete(recordId);
	}
}
