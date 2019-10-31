package com.lsts.equipment2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.rbac.bean.Employee;
import com.lsts.equipment2.bean.EquipmentSupplier;
import com.lsts.equipment2.dao.EquipmentSupplierDao;

/**
 * 供货商信息业务逻辑对象
 * @ClassName BaseEquipmentSupplierService
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-02-18 上午10:30:00
 */
@Service("baseEquipment2SupplierService")
public class EquipmentSupplierManager extends EntityManageImpl<EquipmentSupplier, EquipmentSupplierDao>{

	@Autowired
	private EquipmentSupplierDao baseEquipmentSupplierDao;
	
	// 删除供应商信息
    public void delete(String ids) {
    	baseEquipmentSupplierDao.delete(ids);
    }
    /** 根据厂商名称或电话号码获取厂商信息
	 * @param q
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public List<EquipmentSupplier> sugguest(String q) throws IllegalArgumentException, IllegalAccessException {
		return baseEquipmentSupplierDao.suggest(q);
	}
}
