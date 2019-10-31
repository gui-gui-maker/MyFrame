package com.lsts.finance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.finance.bean.CwBankAccount;
import com.lsts.finance.dao.CwBankAccountDao;

@Service
public class CwBankAccountService extends EntityManageImpl<CwBankAccount, CwBankAccountDao>{

	@Autowired
	private CwBankAccountDao cwBankAccountDao;

	public void deletes(String ids) throws Exception{
		String[] idss = ids.split(",");
		for (String id : idss) {
			cwBankAccountDao.removeById(id);
		}
	}
	
}
