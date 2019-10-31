package com.khnt.bpm.core.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/*******************************************************************************
 * s***********************************
 * 
 * <p>
 * 流程跟踪
 * </p>
 * 
 * @ClassName FlowTrask
 * @JDK 1.5
 * @author
 * @date 2011-8-16 上午12:05:58
 */

@Entity
@Table(name = "FLOW_TRASK")
public class Trask implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;// 主键

	private String processId;// 流程实例

	private Date exeDate;// 执行时间

	private String personId;// 人员ID

	private String person;// 人员姓名

	private String action;// 动作

	private String activitId;// 活动事项

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "EXE_DATE")
	public Date getExeDate() {
		return exeDate;
	}

	public void setExeDate(Date exeDate) {
		this.exeDate = exeDate;
	}

	@Column(name = "PERSON_ID")
	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	@Column(name = "PERSON")
	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	@Column(name = "ACTION")
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Column(name = "ACTIVITY_ID")
	public String getActivitId() {
		return activitId;
	}

	public void setActivitId(String activitId) {
		this.activitId = activitId;
	}

	@Column(name = "PROCESS_ID")
	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "流程操作日志";

	}

}
