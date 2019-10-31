package com.fwxm.dining.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fwxm.dining.dao.EmployeefDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.rbac.impl.bean.Employee;

@Service("employeefService")
public class EmployeefService extends
EntityManageImpl<Employee, EmployeefDao>{
	@Autowired
	private EmployeefDao employeefDao;
	
	public Employee getEmpBySfz(String sfz) throws Exception{
		
		return employeefDao.getEmpBySfz(sfz);
		
	}
	@SuppressWarnings("unchecked")
	public List<Employee> getEmployees(){
		return employeefDao.createQuery("from Employee").list();
	}
}
