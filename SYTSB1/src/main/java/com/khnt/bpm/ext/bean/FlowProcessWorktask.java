package com.khnt.bpm.ext.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 
 * 工作流系统中，业务类型映射。
 * 
 * <p>
 * 对每一个启动的工作流实例，都会对应一个业务类型编码，以此在全局可用。
 * </p>
 * 
 * @author hansel 2013-03-15
 */
@Entity
@Table(name = "PUB_FLOW_PROCESS_WORKTASK")
public class FlowProcessWorktask implements BaseEntity {

	private static final long serialVersionUID = 1L;

	private String id;// 主键

	private String typeCode;// 分类编号

	private String serviceTitle;// 业务标题

	private String processId;// 分类编号

	private String remark;// 描述

	private String parameter;// 工作任务业务参数，为url query string格式

	private String urgentLevel;// 紧急程度，0一般，1紧急

	public FlowProcessWorktask() {
		super();
	}

	public FlowProcessWorktask(String typeCode, String serviceTitle, String processId, String parameter,
			String urgentLevel, String remark) {
		super();
		this.serviceTitle = serviceTitle;
		this.typeCode = typeCode;
		this.processId = processId;
		this.parameter = parameter;
		this.urgentLevel = urgentLevel;
		this.remark = remark;
	}

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

	@Column(name = "SERVICE_TITLE")
	public String getServiceTitle() {
		return serviceTitle;
	}

	public void setServiceTitle(String serviceTitle) {
		this.serviceTitle = serviceTitle;
	}

	@Column(name = "PROCESS_ID")
	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "PARAMETER")
	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	@Column(name = "URGENT_LEVEL")
	public String getUrgentLevel() {
		return urgentLevel;
	}

	public void setUrgentLevel(String urgentLevel) {
		this.urgentLevel = urgentLevel;
	}

	/**
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "流程类型" + this.typeCode + this.remark;
	}
}
