package com.fwxm.contract.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fwxm.contract.bean.ContractEvaluate;
import com.fwxm.contract.dao.ContractEvaluateDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;

@Service("contractEvaluateService")
public class ContractEvaluateService extends EntityManageImpl<ContractEvaluate, ContractEvaluateDao> {

	@Autowired
	private ContractEvaluateDao contractEvaluateDao;

	public void delete(String ids) {
		
		String [] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			ContractEvaluate evaluate = contractEvaluateDao.get(id[i]);
			evaluate.setData_status("99");
			contractEvaluateDao.save(evaluate);
		}
		
	}
	
	
}
