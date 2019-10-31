package com.lsts.employee.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.JSONObject;
import com.khnt.core.crud.bean.BaseEntity;
import com.khnt.rbac.bean.Employee;
import com.lsts.humanresources.bean.EmployeeBase;

/**
 * 人员持证情况
 * EmployeeCert entity. 
 * @author GaoYa
 * @date 2014-03-03 16:51:00
 */
@Entity
@Table(name = "employee_cert")
@JsonIgnoreProperties("employee")
public class EmployeeCert implements BaseEntity{

	private static final long serialVersionUID = 1L;
	private String id;	// ID
	//private String employee_id;	// 人员基本信息ID
	private String cert_type;	// 证书类型
	private String cert_code;	// 证书代码
	private String cert_category;	// 证书类别
	private String cert_no;	// 证书编号
	private String cert_level;	// 证书级别
	private String cert_issue_org;	// 发证机关
	private java.util.Date first_get_date;	// 初次取证日期
	private java.util.Date cert_begin_date;	// 发证日期
	private java.util.Date cert_end_date;	// 证书有效截止日期
	private String cert_status;	// 授权状态
	private String status;//状态
	private String cert_file;	// 证书附件id
	private String cert_file_name;//证书附件名字
	
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
	public String getCert_type() {
		return cert_type;
	}
	public void setCert_type(String cert_type) {
		this.cert_type = cert_type;
	}
	public String getCert_code() {
		return cert_code;
	}
	public void setCert_code(String cert_code) {
		this.cert_code = cert_code;
	}
	public String getCert_category() {
		return cert_category;
	}
	public void setCert_category(String cert_category) {
		this.cert_category = cert_category;
	}
	public String getCert_no() {
		return cert_no;
	}
	public void setCert_no(String cert_no) {
		this.cert_no = cert_no;
	}
	public String getCert_level() {
		return cert_level;
	}
	public void setCert_level(String cert_level) {
		this.cert_level = cert_level;
	}
	public java.util.Date getCert_begin_date() {
		return cert_begin_date;
	}
	public void setCert_begin_date(java.util.Date cert_begin_date) {
		this.cert_begin_date = cert_begin_date;
	}
	public java.util.Date getCert_end_date() {
		return cert_end_date;
	}
	public void setCert_end_date(java.util.Date cert_end_date) {
		this.cert_end_date = cert_end_date;
	}
	public String getCert_status() {
		return cert_status;
	}
	public void setCert_status(String cert_status) {
		this.cert_status = cert_status;
	}
	public String getCert_issue_org() {
		return cert_issue_org;
	}
	public void setCert_issue_org(String cert_issue_org) {
		this.cert_issue_org = cert_issue_org;
	}
	
	public java.util.Date getFirst_get_date() {
		return first_get_date;
	}
	public void setFirst_get_date(java.util.Date first_get_date) {
		this.first_get_date = first_get_date;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@SuppressWarnings("unchecked")
	public Map to_Map() {
        Map map = new HashMap();
        map.put("id", id);
        map.put("employee_id", employee.getId());
        map.put("cert_type", cert_type);
        map.put("cert_no", cert_no);
        map.put("cert_code", cert_code);
        map.put("cert_issue_org", cert_issue_org);
        map.put("cert_begin_date", cert_begin_date);
        map.put("cert_end_date", cert_end_date);
        map.put("cert_level", cert_level);
        map.put("cert_category", cert_category);
        map.put("cert_status", cert_status); 
        map.put("first_get_date", first_get_date);
        return map;
    }
	
	public JSONObject toJson() {
       JSONObject map = new JSONObject();
       DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        map.put("id", id);
        map.put("employee_id", employee.getId());
        map.put("cert_type", cert_type);
        map.put("cert_no", cert_no);
        map.put("cert_code", cert_code);
        map.put("cert_issue_org", cert_issue_org);
        if(cert_begin_date!=null){
        	 map.put("cert_begin_date", df.format(cert_begin_date));
        }else{
        	map.put("cert_begin_date", "");
        }
        if(cert_end_date!=null){
        	map.put("cert_end_date", df.format(cert_end_date));
	    }else{
	    	   map.put("cert_end_date", "");
	    }
        map.put("cert_level", cert_level);
        map.put("cert_category", cert_category);
        map.put("cert_status", cert_status); 
        map.put("first_get_date", first_get_date);
        return map;
    }
	
	@Transient
	public String getCert_file() {
		return cert_file;
	}
	public void setCert_file(String cert_file) {
		this.cert_file = cert_file;
	}
	@Transient
	public String getCert_file_name() {
		return cert_file_name;
	}
	public void setCert_file_name(String cert_file_name) {
		this.cert_file_name = cert_file_name;
	}
}
