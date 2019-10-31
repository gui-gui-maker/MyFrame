package com.lsts.inspection.bean;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;


/**
 * 检验检测收费标准配置表
 * 
 * @ClassName InspectionInfoPay
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-12-30 上午09:27:00
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "tzsb_inspection_pay")
public class InspectionInfoPay implements BaseEntity{
	
	private static final long serialVersionUID = 1L;
	private String	id	;	//	ID
	private String  device_type;		// 设备类型（1：锅炉 2：压力容器 3：电梯 4：起重机 5：厂内机动车 6：游乐设施 
										// 7：压力管道元件 8：压力管道 9：客运索道 F：安全附件）
	
	private String  device_category;	// 设备类别
	private String  device_sort_code;	// 设备名称代码
	private String  device_name;		// 设备名称
	private String  check_type;			// 检验类别（数据字典：BASE_CHECK_TYPE）
	private String  pay_key;			// 收费指标
	private Double  pay_value;			// 收费金额（元）
	private String  pay_desc;			// 收费指标描述
	private String  last_mdy_userid;	// 最后修改用户ID
	private String  last_mdy_username;	// 最后修改用户姓名
	private Date    last_mdy_date;		// 最后修改日期
	
	private String  data_status;		// 数据状态(0：已启用  99：停用)

	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getCheck_type() {
		return check_type;
	}
	public void setCheck_type(String check_type) {
		this.check_type = check_type;
	}
	public String getDevice_type() {
		return device_type;
	}
	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}
	public String getDevice_category() {
		return device_category;
	}
	public void setDevice_category(String device_category) {
		this.device_category = device_category;
	}
	public String getDevice_sort_code() {
		return device_sort_code;
	}
	public void setDevice_sort_code(String device_sort_code) {
		this.device_sort_code = device_sort_code;
	}
	public String getDevice_name() {
		return device_name;
	}
	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}
	public String getPay_key() {
		return pay_key;
	}
	public void setPay_key(String pay_key) {
		this.pay_key = pay_key;
	}
	public Double getPay_value() {
		return pay_value;
	}
	public void setPay_value(Double pay_value) {
		this.pay_value = pay_value;
	}
	public String getData_status() {
		return data_status;
	}
	public void setData_status(String data_status) {
		this.data_status = data_status;
	}
	public String getPay_desc() {
		return pay_desc;
	}
	public void setPay_desc(String pay_desc) {
		this.pay_desc = pay_desc;
	}
	public String getLast_mdy_userid() {
		return last_mdy_userid;
	}
	public void setLast_mdy_userid(String last_mdy_userid) {
		this.last_mdy_userid = last_mdy_userid;
	}
	public String getLast_mdy_username() {
		return last_mdy_username;
	}
	public void setLast_mdy_username(String last_mdy_username) {
		this.last_mdy_username = last_mdy_username;
	}
	public Date getLast_mdy_date() {
		return last_mdy_date;
	}
	public void setLast_mdy_date(Date last_mdy_date) {
		this.last_mdy_date = last_mdy_date;
	}

}
