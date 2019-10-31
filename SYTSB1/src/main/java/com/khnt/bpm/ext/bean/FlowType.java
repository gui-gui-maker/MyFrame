package com.khnt.bpm.ext.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 工作流分类
 */
@Entity
@Table(name = "FLOW_TYPE")
@JsonIgnoreProperties(value = { "flowTypes" })
public class FlowType implements BaseEntity {

	private static final long serialVersionUID = 1L;
	private String id;// 主键
	private String typeCode;// 分类编号
	private String typeName;// 分类名称
	private String remark;// 描述
	private String sort;// 排序
	private FlowType flowType;// 上级类型
	private Set<FlowType> flowTypes;// 下级类型

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "TYPE_CODE")
	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	@Column(name = "TYPE_NAME")
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "SORT")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PARENT_ID")
	public FlowType getFlowType() {
		return flowType;
	}

	public void setFlowType(FlowType flowType) {
		this.flowType = flowType;
	}

	@OneToMany(mappedBy = "flowType", cascade = CascadeType.ALL)
	@OrderBy("sort")
	public Set<FlowType> getFlowTypes() {
		return flowTypes;
	}

	public void setFlowTypes(Set<FlowType> flowTypes) {
		this.flowTypes = flowTypes;
	}

	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "流程类型" + this.getTypeName() + "(id=" + this.getId() + ")";
	}
}
