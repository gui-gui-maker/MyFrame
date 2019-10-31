package com.lsts.equipment2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.equipment2.bean.EquipmentBuyRelation;
import com.lsts.equipment2.dao.EquipmentBuyRelationDao;

/**
 * 设备申请关联业务逻辑对象
 * @ClassName EquipmentBuyRelationService
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-02-18 下午03:29:00
 */
@Service("equipmentBuyRelationService")
public class EquipmentBuyRelationService extends EntityManageImpl<EquipmentBuyRelation, EquipmentBuyRelationDao>{

	@Autowired
	private EquipmentBuyRelationDao baseEquipmentApplyRelationDao;
	
	// 根据申请ID查询申请关联详情
    public List<EquipmentBuyRelation> queryBaseEquipmentApplyRelationByApplyId(String id){
    	return baseEquipmentApplyRelationDao.queryBaseEquipmentApplyRelationByApplyId(id);
    }
    

    // 查询已归还数量
    public int queryReturn_count(String id) {
    	return baseEquipmentApplyRelationDao.queryReturn_count(id);
    }
	
	
	// 删除设备申请关联记录
    public void delete(String ids) {
    	baseEquipmentApplyRelationDao.delete(ids);
    }
}
