package com.fwxm.contract.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fwxm.contract.bean.ContractCustomLevel;
import com.fwxm.contract.dao.ContractCustomLevelDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;

@Service("contractCustomLevelService")
public class ContractCustomLevelService extends EntityManageImpl<ContractCustomLevel, ContractCustomLevelDao> {

	@Autowired
	private ContractCustomLevelDao customLevelDao;

	public void del(String ids) {
		String [] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			ContractCustomLevel customLevel = customLevelDao.get(id[i]);
			customLevel.setData_status("99");
			customLevelDao.save(customLevel);
		}
		
	}
}
