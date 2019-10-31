package com.fwxm.contract.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fwxm.contract.bean.ContractLog;
import com.fwxm.contract.dao.ContractLogDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;

@Service("contractLogService")
public class ContractLogService extends EntityManageImpl<ContractLog, ContractLogDao> {

	@Autowired
	private ContractLogDao contractLogDao;
	
}
