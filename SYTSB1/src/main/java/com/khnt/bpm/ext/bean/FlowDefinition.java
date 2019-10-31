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
 * 流程定义
 * 
 * @ClassName FlowDefinition
 * @JDK 1.5
 * @author
 * @date 2
 */
@Entity
@Table(name = "FLOW_DEFINITION")
public class FlowDefinition implements BaseEntity {

	private static final long serialVersionUID = 1L;
	private String id;// 主键
	private String flowname;// 流程名称
	private String flowxml;// 流程内容
	private String remark;// 描述
	private String state;// 状态
	private String sort;// 排序
	private String flowtype;// 流程类型

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "FLOWNAME")
	public String getFlowname() {
		return flowname;
	}

	public void setFlowname(String flowname) {
		this.flowname = flowname;
	}

	@Column(name = "FLOWXML")
	public String getFlowxml() {
		return flowxml;
	}

	public void setFlowxml(String flowxml) {
		this.flowxml = flowxml;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "STATE")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "SORT")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@Column(name = "FLOWTYPE")
	public String getFlowtype() {
		return flowtype;
	}

	public void setFlowtype(String flowtype) {
		this.flowtype = flowtype;
	}

	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "流程定义" + this.getFlowname() + "(id=" + this.getId() + ")";
	}
}
