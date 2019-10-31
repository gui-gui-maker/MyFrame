package com.khnt.bpm.ext.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/*******************************************************************************
 * 
 * 流程环节对应的功能
 * 
 * @ClassName FlowFunction
 * @JDK 1.5
 * @author
 * @date 2
 */
@Entity
@Table(name = "FLOW_FUNCTION")
public class FlowFunction implements BaseEntity {

	private static final long serialVersionUID = 1L;
	private String id;// 主键
	private String flowType;// 流程类型
	private String code;// 编号
	private String name;// 名称
	private String checked;// 是否默认拥有
	private String remark;// 描述

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "FLOWTYPE")
	public String getFlowType() {
		return flowType;
	}

	public void setFlowType(String flowType) {
		this.flowType = flowType;
	}

	@Column(name = "CODE")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "CHECKED")
	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "流程功能" + this.getName() + "(id=" + this.getId() + ")";
	}
}
