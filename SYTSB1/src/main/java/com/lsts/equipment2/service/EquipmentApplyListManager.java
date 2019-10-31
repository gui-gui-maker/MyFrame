package com.lsts.equipment2.service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.equipment2.bean.Equipment;
import com.lsts.equipment2.bean.EquipmentApplyList;
import com.lsts.equipment2.dao.EquipmentApplyListDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("EquipmentApplyListManager")
public class EquipmentApplyListManager extends EntityManageImpl<EquipmentApplyList,EquipmentApplyListDao>{
    @Autowired
    EquipmentApplyListDao equipmentApplyListDao;
    /**
     * 删除申请设备
     */
    public void delete(String id) {
    	equipmentApplyListDao.delete(id);
    }
    /**
     * 根据申请ID查询设备列表
     */
    public List<EquipmentApplyList> getEquipmentApplyList(String id){
    	return equipmentApplyListDao.getEquipmentApplyList(id);
    }
}
