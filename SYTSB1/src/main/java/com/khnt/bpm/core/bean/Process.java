package com.khnt.bpm.core.bean;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.sf.json.JSONObject;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;
import com.khnt.utils.StringUtil;

/**
 * 流程实例
 */
@Entity
@Table(name = "FLOW_PROCESS")
@JsonIgnoreProperties({ "activitys", "transitions" })
public class Process implements BaseEntity {

	private static final long serialVersionUID = 1L;
	private String id;// ID
	private String flowName;// 流程名称
	private String flowTypeCode;// 流程类型编号
	private String definitionId;// 流程定义ID
	private String businessId;// 业务ID
	private String dealForm; // 业务处理表单
	private String viewForm; // 业务浏览表单
	private String remark;// 流程描述
	private String creator;// 流程创建人姓名
	private String creatorId;// 流程创建人
	private Date creatorDate;// 流程创时间
	private String dataBus;// 参数
	private String overClass;// 流程终止回调类
	private String overPersonId;// 终止人员
	private String overPerson;// 终止人员姓名
	private Date overDate;// 终止时间
	private String flowxml;// 流程内容
	private String state;// 流程状态 100挂起; 200:运行中 300：终止 500:完成
	private Boolean isLimit; // 是否启用超时时限
	private String limitDesc; // 时限描述
	private Boolean isRemind; // 是否启用提醒
	private String remindDesc; // 提醒描述
	private Set<Activity> activitys;// 流程活动
	private Set<Transition> transitions;// 活动路由/迁移
	private Activity parentActivity;// 父流程连接环节

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "FLOW_NAME")
	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	@Column(name = "FLOW_TYPE_CODE")
	public String getFlowTypeCode() {
		return flowTypeCode;
	}

	public void setFlowTypeCode(String flowTypeCode) {
		this.flowTypeCode = flowTypeCode;
	}

	@Column(name = "DEFINITION_ID")
	public String getDefinitionId() {
		return definitionId;
	}

	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}

	@Column(name = "DEAL_FORM")
	public String getDealForm() {
		return dealForm;
	}

	public void setDealForm(String dealForm) {
		this.dealForm = dealForm;
	}

	@Column(name = "VIEW_FORM")
	public String getViewForm() {
		return viewForm;
	}

	public void setViewForm(String viewForm) {
		this.viewForm = viewForm;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "DATA_BUS")
	public String getDataBus() {
		return dataBus;
	}

	public void setDataBus(String dataBus) {
		this.dataBus = dataBus;
	}

	public void setDataBus(Map<String, String> dataBus) {
		if (dataBus != null) {
			JSONObject jo;
			if (StringUtil.isEmpty(this.dataBus)) {
				jo = new JSONObject();
			}
			else {
				jo = JSONObject.fromObject(this.getDataBus());
			}
			Set<String> key = dataBus.keySet();
			for (Iterator<String> it = key.iterator(); it.hasNext();) {
				String k = (String) it.next();
				jo.put(k, dataBus.get(k));
			}
			this.setDataBus(jo.toString());
		}
	}

	@Transient
	public JSONObject getDataBusJson() {
		JSONObject jo;
		if (StringUtil.isEmpty(this.dataBus)) {
			jo = new JSONObject();
		}
		else {
			jo = JSONObject.fromObject(this.getDataBus());
		}
		return jo;
	}

	@Column(name = "CREATOR")
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Column(name = "CREATOR_ID")
	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	@Column(name = "CREATOR_DATE")
	public Date getCreatorDate() {
		return creatorDate;
	}

	public void setCreatorDate(Date creatorDate) {
		this.creatorDate = creatorDate;
	}

	@Column(name = "OVER_CLASS")
	public String getOverClass() {
		return overClass;
	}

	public void setOverClass(String overClass) {
		this.overClass = overClass;
	}

	@Column(name = "OVER_PERSON_ID")
	public String getOverPersonId() {
		return overPersonId;
	}

	public void setOverPersonId(String overPersonId) {
		this.overPersonId = overPersonId;
	}

	@Column(name = "OVER_PERSON")
	public String getOverPerson() {
		return overPerson;
	}

	public void setOverPerson(String overPerson) {
		this.overPerson = overPerson;
	}

	@Column(name = "OVER_DATE")
	public Date getOverDate() {
		return overDate;
	}

	public void setOverDate(Date overDate) {
		this.overDate = overDate;
	}

	@Column(name = "BUSINESS_ID")
	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	@Column(name = "FLOWXML")
	public String getFlowxml() {
		return flowxml;
	}

	public void setFlowxml(String flowxml) {
		this.flowxml = flowxml;
	}

	@OneToMany(mappedBy = "process", cascade = CascadeType.ALL)
	public Set<Activity> getActivitys() {
		return activitys;
	}

	public void setActivitys(Set<Activity> activitys) {
		this.activitys = activitys;
	}

	@OneToMany(mappedBy = "process", cascade = CascadeType.ALL)
	@OrderBy("orderby")
	public Set<Transition> getTransitions() {
		return transitions;
	}

	public void setTransitions(Set<Transition> transitions) {
		this.transitions = transitions;
	}

	@Column(name = "STATE")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "IS_LIMIT")
	public Boolean getIsLimit() {
		return isLimit;
	}

	public void setIsLimit(Boolean isLimit) {
		this.isLimit = isLimit;
	}

	@Column(name = "IS_REMIND")
	public Boolean getIsRemind() {
		return isRemind;
	}

	public void setIsRemind(Boolean isRemind) {
		this.isRemind = isRemind;
	}

	@Column(name = "LIMIT_DESC")
	public String getLimitDesc() {
		return limitDesc;
	}

	public void setLimitDesc(String limitDesc) {
		this.limitDesc = limitDesc;
	}

	@Column(name = "REMIND_DESC")
	public String getRemindDesc() {
		return remindDesc;
	}

	public void setRemindDesc(String remindDesc) {
		this.remindDesc = remindDesc;
	}

	@ManyToOne
	@JoinColumn(name = "fk_parent_activity", nullable = true)
	public Activity getParentActivity() {
		return parentActivity;
	}

	public void setParentActivity(Activity parentActivity) {
		this.parentActivity = parentActivity;
	}

}
