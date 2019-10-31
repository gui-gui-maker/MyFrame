package com.khnt.bpm.core.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/*******************************************************************************
 * 
 * <p>
 * 流程活动对应的事件
 * </p>
 * 
 * @ClassName FlowForm
 * @JDK 1.5
 * @author
 * @date 2
 */
@Entity
@Table(name = "FLOW_ACTION")
public class Action implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;// 主键

	private String actionTime;// 事件类型/触发时间:

	private String actionDo;// 业务操作

	private String remark;// 描述

	private String bindingId;// 绑定ID 活动环节或流程实例

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "ACTION_DO")
	public String getActionDo() {
		return actionDo;
	}

	public void setActionDo(String actionDo) {
		this.actionDo = actionDo;
	}

	@Column(name = "ACTION_TIME")
	public String getActionTime() {
		return actionTime;
	}

	public void setActionTime(String actionTime) {
		this.actionTime = actionTime;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "BINDING_ID")
	public String getBindingId() {
		return bindingId;
	}

	public void setBindingId(String bindingId) {
		this.bindingId = bindingId;
	}

	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "流程活动事件" + this.actionTime + "(id=" + this.getId() + ")";
	}

}
