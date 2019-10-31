package com.lsts.employee.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.employee.bean.Allowancefo;


@Repository("allowancefoDao")
public class AllowancefoDao extends EntityDaoImpl<Allowancefo> {

	public Allowancefo getDetailByOp(String id, String opId) {
		
		String hql = "from Allowancefo t where t.employeeallowance.id = ? and t.other_applicants_id=?";
		List<Allowancefo> list = this.createQuery(hql, id,opId).list();
		if(list.size()>0){
			return list.get(0);
		}
		
		return null;
	}

}
