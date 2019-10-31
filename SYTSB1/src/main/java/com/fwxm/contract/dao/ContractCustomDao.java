package com.fwxm.contract.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fwxm.contract.bean.ContractCustom;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;

@Repository("contractCustomDao")
public class ContractCustomDao extends EntityDaoImpl<ContractCustom> {

	public List<ContractCustom> getDataByName(String name) {

		String hql = "from ContractCustom c where c.com_name like '%"+name+"%' and c.com_status<>'99' ";
		List<ContractCustom> list = this.createQuery(hql).list();
		return list;
	}

}
