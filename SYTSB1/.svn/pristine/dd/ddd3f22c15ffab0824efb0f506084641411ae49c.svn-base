package com.fwxm.dining.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 食堂员工登录绑定窗口号
 * @author Guido
 *
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "FOODDiningWindow")
public class DiningWindow implements BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private int windowNumber;
	private String employeeId;
	private String employeeName;
	private int windowStatus;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getWindowNumber() {
		return windowNumber;
	}
	public void setWindowNumber(int windowNumber) {
		this.windowNumber = windowNumber;
	}
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public int getWindowStatus() {
		return windowStatus;
	}
	public void setWindowStatus(int windowStatus) {
		this.windowStatus = windowStatus;
	}
	
	
	
}
