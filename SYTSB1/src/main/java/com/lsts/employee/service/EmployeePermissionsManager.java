package com.lsts.employee.service;


import java.util.List;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.employee.bean.EmployeePermissions;
import com.lsts.employee.dao.EmployeePermissionsDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("employeePermissions")
public class EmployeePermissionsManager extends EntityManageImpl<EmployeePermissions,EmployeePermissionsDao>{
    @Autowired
    EmployeePermissionsDao employeePermissionsDao;
    
    public EmployeePermissions detailPermissions(String id){
    	String hql="from EmployeePermissions where FK_EMPLOYEE_ID=?";
    	List<EmployeePermissions> list= employeePermissionsDao.createQuery(hql, id).list();
    	if(list.size()>0){
    		return list.get(0);
    	}else{
    		return null;
    	}
    }
}
