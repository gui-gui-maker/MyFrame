package com.lsts.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.employee.bean.EmployeePrinter;
import com.lsts.employee.dao.EmployeePrinterDao;

/**
 * 人员打印机设置业务逻辑对象
 * @ClassName EmployeePrinterService
 * @JDK 1.6
 * @author GaoYa
 * @date 2014-03-05 下午02:56:00
 */
@Service("employeePrinterService")
public class EmployeePrinterService extends EntityManageImpl<EmployeePrinter, EmployeePrinterDao> {

	@Autowired
	private EmployeePrinterDao employeePrinterDao;

	@Override
	public void save(EmployeePrinter entity) throws Exception {
		employeePrinterDao.save(entity);
	}
	
	// 获取人员打印机设置
	public EmployeePrinter queryByEmployeeID(String employee_id){
		return employeePrinterDao.queryByEmployeeID(employee_id);
	}
	
	// 获取人员打印机名称
	public String queryPrinterName(String employee_id, String type){
		return employeePrinterDao.queryPrinterName(employee_id, type);
	}
}
