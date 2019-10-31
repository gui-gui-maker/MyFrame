package com.fwxm.contract.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fwxm.contract.bean.ContractCustomType;
import com.fwxm.contract.dao.ContractCustomTypeDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;

@Service("contractCustomTypeService")
public class ContractCustomTypeService extends EntityManageImpl<ContractCustomType, ContractCustomTypeDao> {

	@Autowired
	private ContractCustomTypeDao customTypeDao;

	public void del(String ids) {
		String [] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			ContractCustomType customType = customTypeDao.get(id[i]);
			customType.setData_status("99");
			customTypeDao.save(customType);
		}
		
	}
}
