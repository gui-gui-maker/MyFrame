package com.fwxm.contract.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fwxm.contract.bean.ContractCustom;
import com.fwxm.contract.dao.ContractCustomDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;

@Service("contractCustomService")
public class ContractCustomService extends EntityManageImpl<ContractCustom, ContractCustomDao> {

	@Autowired
	private ContractCustomDao contractCustomDao;

	public void del(String ids) {
		String id = ids.replace(",", "','");
		String hql = "update ContractCustom c set c.com_status='99' where c.id in ('"+id+"')";
		contractCustomDao.createQuery(hql).executeUpdate();
	}
	
}
