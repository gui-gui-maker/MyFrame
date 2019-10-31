package com.lsts.approve.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 报告自动手枪签字分配规则
 * 
 * @ClassName CertificateRule
 * @JDK 1.6
 * @author GaoYa
 * @date 2017-06-25 下午02:27:00
 */
@Entity
@Table(name = "BASE_CERTIFICATE_RULE")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CertificateRule implements BaseEntity{

	private static final long serialVersionUID = 1L;
	private String id;
	private String device_classify_id;	// 设备大类id
	private String device_classify_name;// 设备大类名称
	private String dept_id;
	private String dept;
	private String report_id;
	private String report_name;
	private String certificate_rule;	// 分配规则  1、量少优先分配（默认），2、随机分配
	private String is_allow_self;		// 是否可签本部门报告    1：是 0：否（默认） 
	private String is_same_unit;		// 是否匹配相同单位      1：是（默认） 0：否  
	private String is_same_person;		// 是否允许签发与审核为同一人      1：是 0：否（默认）  

	private List<CertificateBy> list = new ArrayList<CertificateBy>();
	
	@Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDevice_classify_id() {
		return device_classify_id;
	}
	public void setDevice_classify_id(String device_classify_id) {
		this.device_classify_id = device_classify_id;
	}
	public String getDevice_classify_name() {
		return device_classify_name;
	}
	public void setDevice_classify_name(String device_classify_name) {
		this.device_classify_name = device_classify_name;
	}
	public String getCertificate_rule() {
		return certificate_rule;
	}
	public void setCertificate_rule(String certificate_rule) {
		this.certificate_rule = certificate_rule;
	}
	public String getDept_id() {
		return dept_id;
	}
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getReport_id() {
		return report_id;
	}
	public void setReport_id(String report_id) {
		this.report_id = report_id;
	}
	public String getReport_name() {
		return report_name;
	}
	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}
	@Transient
	public List<CertificateBy> getList() {
		return list;
	}
	public void setList(List<CertificateBy> list) {
		this.list = list;
	}
	public String getIs_allow_self() {
		return is_allow_self;
	}
	public void setIs_allow_self(String is_allow_self) {
		this.is_allow_self = is_allow_self;
	}
	public String getIs_same_unit() {
		return is_same_unit;
	}
	public void setIs_same_unit(String is_same_unit) {
		this.is_same_unit = is_same_unit;
	}
	public String getIs_same_person() {
		return is_same_person;
	}
	public void setIs_same_person(String is_same_person) {
		this.is_same_person = is_same_person;
	}
	
}
