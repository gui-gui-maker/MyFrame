package com.lsts.employee.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.employee.bean.EmployeePrinter;

/**
 * 人员打印机设置数据处理对象
 * @ClassName EmployeePrinterDao
 * @JDK 1.6
 * @author GaoYa
 * @date 2014-03-05 下午02:47:00
 */
@Repository("employeePrinterDao")
public class EmployeePrinterDao extends EntityDaoImpl<EmployeePrinter> {

	/**
	 * 获取人员打印机设置
	 * @param employee_id -- 人员ID
	 * @return 
	 * @author GaoYa
	 * @date 2014-03-05 下午02:55:00
	 */
	@SuppressWarnings("unchecked")
	public EmployeePrinter queryByEmployeeID(String employee_id){
		EmployeePrinter employeePrinter = null;
    	try {
    		if (StringUtil.isNotEmpty(employee_id)) {
    			String hql = "from EmployeePrinter p where p.employee.id=?";
    			List list = this.createQuery(hql, employee_id).list();
    			if(!list.isEmpty()){
    				employeePrinter = (EmployeePrinter)list.get(0);
    			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return employeePrinter;
	}
	
	/**
	 * 获取人员打印机名称
	 * @param employee_id -- 人员ID
	 * @param type -- 1：报告打印机 2：标签打印机
	 * @return 
	 * @author GaoYa
	 * @date 2014-03-06 下午01:49:00
	 */
	@SuppressWarnings("unchecked")
	public String queryPrinterName(String employee_id, String type){
		EmployeePrinter employeePrinter = null;
    	try {
    		if (StringUtil.isNotEmpty(employee_id)) {
    			String hql = "from EmployeePrinter p where p.employee.id=?";
    			List list = this.createQuery(hql, employee_id).list();
    			if(!list.isEmpty()){
    				employeePrinter = (EmployeePrinter)list.get(0);
    				if (employeePrinter!=null) {
    					if ("1".equals(type)) {
    						return employeePrinter.getPrinter_name();
						}else{
							return employeePrinter.getPrinter_name_tags();
						}
					}
    			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "";
	}
}
