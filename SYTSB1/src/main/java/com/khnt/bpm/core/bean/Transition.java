package com.khnt.bpm.core.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * <p>
 * 工作流环节路由信息模型
 * </p>
 */
@Entity
@Table(name = "FLOW_TRANSITION")
public class Transition implements BaseEntity {

	private static final long serialVersionUID = 1L;
	private String id;// 主键
	private Process process;// 流程实例
	private String fromId;// 开始环节ID
	private String toid;// 目标环节ID
	private String name;// 路由名称
	private String condition;// 路由条件
	private String type;// 路由类型
	private String orderby;// 排序
	private String parameter;// 参数
	private Boolean passed = false;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "FROM_ID")
	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	@Column(name = "TO_ID")
	public String getToid() {
		return toid;
	}

	public void setToid(String toid) {
		this.toid = toid;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "JUMP_CONDITION")
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	@Column(name = "TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "ORDER_BY")
	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	@Column(name = "PARAMETER")
	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROCESS")
	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	@Column(name = "passed")
	public Boolean getPassed() {
		return passed;
	}

	public void setPassed(Boolean passed) {
		this.passed = passed == null ? false : passed;
	}

	public String toString() {
		return "工作流环节路由:" + this.getId();
	}
}
