package com.khnt.bpm.core.bean;

import java.util.Date;
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
import javax.persistence.Temporal;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 流程活动
 */
@Entity
@Table(name = "FLOW_ACTIVITY")
@JsonIgnoreProperties({ "participators", "subProcess" })
public class Activity implements BaseEntity {

	private static final long serialVersionUID = 1L;
	private String id;// 主键
	private Process process;// 流程实例
	private String activityId;// 活动定义ID
	private String name;// 活动名称
	private String signature; // 会签选项(顺签,选签,不会签)
	private String processUrl;// 流程处理URL
	private String viewUrl;// 业务浏览URL
	private String type;// 环节/活动类型
	private String function;// 功能
	private String opinion;// 意见
	private String participantType;// 参与者类型
	private Date createTime;// 创建时间
	private Date startTime;// 起动时间
	private Date endTime;// 结束时间
	private String state;// 状态 100 未创建 200：创建 300:启动 500:处理完成
	private Boolean isLimit; // 是否启用超时时限
	private String limitDesc; // 时限描述
	private Boolean isRemind; // 是否启用提醒
	private String remindDesc; // 提醒描述
	private String subFlowId;// 子流程ID
	private String subFlowName;// 子流程name
	private Set<Participator> participators;// 活动对应的参与者
	private Set<Process> subProcess;// 子流程
	private String innerFlow;// 内部协办流转,0否，1是

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "PROCESS_URL")
	public String getProcessUrl() {
		return processUrl;
	}

	public void setProcessUrl(String processUrl) {
		this.processUrl = processUrl;
	}

	@Column(name = "VIEW_URL")
	public String getViewUrl() {
		return viewUrl;
	}

	public void setViewUrl(String viewUrl) {
		this.viewUrl = viewUrl;
	}

	@Column(name = "\"FUNCTION\"")
	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	@Column(name = "OPINION")
	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	@Column(name = "STATE")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROCESS")
	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	@Column(name = "Type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "ACTIVITY_ID")
	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	@Column(name = "CREATE_TIME")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "END_TIME")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "START_TIME")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
	public Set<Participator> getParticipators() {
		return participators;
	}

	public void setParticipators(Set<Participator> participators) {
		this.participators = participators;
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

	@Column(name = "SIGNATURE")
	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	@OneToMany(mappedBy = "parentActivity", cascade = CascadeType.REMOVE)
	@OrderBy("creatorDate")
	public Set<Process> getSubProcess() {
		return subProcess;
	}

	public void setSubProcess(Set<Process> subProcess) {
		this.subProcess = subProcess;
	}

	@Column(name = "sub_flow_id")
	public String getSubFlowId() {
		return subFlowId;
	}

	@Column(name = "sub_flow_name")
	public String getSubFlowName() {
		return subFlowName;
	}

	public void setSubFlowId(String subFlowId) {
		this.subFlowId = subFlowId;
	}

	public void setSubFlowName(String subFlowName) {
		this.subFlowName = subFlowName;
	}

	@Column(name = "participant_type")
	public String getParticipantType() {
		return participantType;
	}

	public void setParticipantType(String participantType) {
		this.participantType = participantType;
	}

	@Column(name = "inner_flow")
	public String getInnerFlow() {
		return innerFlow == null ? "0" : innerFlow;
	}

	public void setInnerFlow(String innerFlow) {
		this.innerFlow = innerFlow;
	}
}
