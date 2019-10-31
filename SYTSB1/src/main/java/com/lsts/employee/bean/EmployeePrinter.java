package com.lsts.employee.bean;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;
import com.khnt.rbac.bean.Employee;
import com.lsts.humanresources.bean.EmployeeBase;

/**
 * 人员打印机设置
 * EmployeePrinter entity. 
 * @author GaoYa
 * @date 2014-03-05 14:29:00
 */
@Entity
@Table(name = "employee_printer")
@JsonIgnoreProperties("employee")
public class EmployeePrinter implements BaseEntity{

	private static final long serialVersionUID = 1L;
	private String id;	// ID
	//private String employee_id;	// 人员基本信息ID
	private String printer_name;	// 报告打印机名称
	private String printer_name_tags;	// 标签打印机名称
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	// 关联人员对象
	public EmployeeBase employee;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	public EmployeeBase getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeBase employee) {
		this.employee = employee;
	}
	
	public String getPrinter_name() {
		return printer_name;
	}
	public void setPrinter_name(String printer_name) {
		this.printer_name = printer_name;
	}
	public String getPrinter_name_tags() {
		return printer_name_tags;
	}
	public void setPrinter_name_tags(String printer_name_tags) {
		this.printer_name_tags = printer_name_tags;
	}
	
	@SuppressWarnings("unchecked")
	public Map to_Map() {
        Map map = new HashMap();
        map.put("id", id);
        map.put("employee_id", employee.getId());
        map.put("printer_name", printer_name);
        map.put("printer_name_tags", printer_name_tags);      
        return map;
    }
}
