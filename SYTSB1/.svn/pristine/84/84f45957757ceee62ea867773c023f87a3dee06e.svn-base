package com.fwxm.dining.dao;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.rbac.impl.bean.Employee;
@Repository("employeefDao")
public class EmployeefDao extends EntityDaoImpl<Employee>{

	public Employee getEmpBySfz(String sfz) {
		String hql = "from Employee e where e.idNo=?";
		return (Employee)this.createQuery(hql, sfz).uniqueResult();
	}

}
