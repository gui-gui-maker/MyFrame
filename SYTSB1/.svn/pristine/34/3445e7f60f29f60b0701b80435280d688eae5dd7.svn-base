package com.lsts.equipment2.service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.lsts.equipment2.bean.EquipmentContract;
import com.lsts.equipment2.dao.EquipmentContractDao;
import com.lsts.humanresources.bean.Contract;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("EquipmentContract")
public class EquipmentContractManager extends EntityManageImpl<EquipmentContract,EquipmentContractDao>{
    @Autowired
    EquipmentContractDao equipmentContractDao;
    //根据申请单ID查询合同信息
    public EquipmentContract getByBuyApplyId(String buyApplyId){
    	EquipmentContract equipmentContract=new EquipmentContract();
    	List<EquipmentContract> list=equipmentContractDao.getByBuyApplyId(buyApplyId);
		if(list.size()>0){
			equipmentContract=list.get(0);
		}
    	return equipmentContract;
    }
    /**
	 * 取出合同文档
	 * @param id
	 * @return
	 * @throws KhntException
	 */
	public EquipmentContract getFile(String id) throws KhntException{
		//取得文档
		byte[] order = equipmentContractDao.getFile(id);
		//取得表记录
		EquipmentContract equipmentContract = equipmentContractDao.get(id);
		equipmentContract.setDocumentDoc(order);
		return equipmentContract;
	}
}
