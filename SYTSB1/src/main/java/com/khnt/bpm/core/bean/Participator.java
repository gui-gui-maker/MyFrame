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

/*******************************************************************************
 * 
 * <p>
 * 流程参与者表
 * </p>
 * 
 * @JDK 1.5
 * @author
 * @date 2012-2-24 下午01:28:38
 */
@Entity
@Table(name = "FLOW_PARTICIPATOR")
public class Participator implements BaseEntity {

	private static final long serialVersionUID = 1L;

	private String id;// 主键

	private Activity activity;// 活动环节

	private String participantType;// 参与者类型 person（个人），role(角色)，org（机构）,

	private String participantId;// 参与者值ID

	private String participantName;// 参与者值NAME

	/**
	 * 参与者范围限定,当为角色时，可指定一个参与者范围。
	 * 
	 * 比如，指定范围为某个机构，那么最终该参与者就是在此机构内有此角色的人员
	 */
	private String participantRange;

	/**
	 * 角色参与者范围类别
	 */
	private String participantRangeType;

	private String chooseRolePerson;// 提交前动态选取角色人员

	private String partiInType;// 参与类型 EXE（待执行）,GET（待领取）

	private Boolean executed = false;// 是否已执行

	private Boolean defined = false;

	public Participator() {
		super();
	}

	public Participator(Activity activity, String participantType, String participantId, String participantName,
			String participantRange, String participantRangeType, String partiInType, String chooseRolePerson,
			Boolean defined) {
		super();
		this.activity = activity;
		this.participantType = participantType;
		this.participantId = participantId;
		this.participantName = participantName;
		this.participantRange = participantRange;
		this.partiInType = partiInType;
		this.participantRangeType = participantRangeType;
		this.chooseRolePerson = chooseRolePerson;
		this.defined = defined;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ACTIVITY_ID")
	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	@Column(name = "PARTICIPANT_ID")
	public String getParticipantId() {
		return participantId;
	}

	public void setParticipantId(String participantId) {
		this.participantId = participantId;
	}

	@Column(name = "PARTICIPANT_NAME")
	public String getParticipantName() {
		return participantName;
	}

	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}

	@Column(name = "PARTICIPANT_RANGE")
	public String getParticipantRange() {
		return participantRange;
	}

	public void setParticipantRange(String participantRange) {
		this.participantRange = participantRange;
	}

	@Column(name = "PARTICIPANT_TYPE")
	public String getParticipantType() {
		return participantType;
	}

	public void setParticipantType(String participantType) {
		this.participantType = participantType;
	}

	@Column(name = "PARTIIN_TYPE")
	public String getPartiInType() {
		return partiInType;
	}

	public void setPartiInType(String partiInType) {
		this.partiInType = partiInType;
	}

	@Column(name = "EXECUTED")
	public Boolean getExecuted() {
		return executed == null ? false : executed;
	}

	public void setExecuted(Boolean executed) {
		this.executed = executed;
	}

	@Column(name = "DEFINED")
	public Boolean getDefined() {
		return defined == null ? false : defined;
	}

	public void setDefined(Boolean defined) {
		this.defined = defined;
	}

	@Column(name = "PATICIPANT_RANGE_TYPE")
	public String getParticipantRangeType() {
		return participantRangeType;
	}

	public void setParticipantRangeType(String participantRangeType) {
		this.participantRangeType = participantRangeType;
	}

	@Column(name = "CHOOSE_ROLE_PERSON")
	public String getChooseRolePerson() {
		return chooseRolePerson;
	}

	public void setChooseRolePerson(String chooseRolePerson) {
		this.chooseRolePerson = chooseRolePerson;
	}

	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "流程实例活动参与者[" + this.getParticipantName() + ":" + this.getId() + "]";
	}
}
