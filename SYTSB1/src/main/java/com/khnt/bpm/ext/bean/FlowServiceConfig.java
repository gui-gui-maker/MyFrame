package com.khnt.bpm.ext.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 业务工作流配置，每个工作流业务都预先设定执行的工作流程，通过业务编码识别。
 * 
 * @author hansel
 * 
 */
@Entity
@Table(name = "FLOW_SERVICE_CONFIG")
public class FlowServiceConfig implements BaseEntity {

	private static final long serialVersionUID = 1L;

	private String id;
	private String serviceCode;// 业务编码
	private String flowId;// 工作流ID
	private String flowName;// 工作流名称
	private String remark;// 备注
	private String orgId;// 单位ID
	private String orgName;// 单位名称

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "SERVICE_CODE")
	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	@Column(name = "FLOW_ID")
	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	@Column(name = "FLOW_NAME")
	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "ORG_ID")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "ORG_NAME")
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
