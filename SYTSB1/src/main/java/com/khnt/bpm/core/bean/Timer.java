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
 * 
 * <p>
 * 定时任务
 * </p>
 * 
 * @ClassName Timer
 * @JDK 1.5
 * @author
 * @date 2
 */
@Entity
@Table(name = "FLOW_TIMER")
public class Timer implements BaseEntity {

	private static final long serialVersionUID = 1L;
	private String id;// ID
	private String timerName;// 定时器名称
	private String timerKind;// 定时器业务类型
	private String timerKindID;// 定时器业务类型对应ID
	private String action;// 调用方法
	private Date registeTime;// 注册时间
	private Date triggeTime;// 触发时间
	private String currentstate;// 当前状态1(激活),2(挂起),3(终止)

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "TIMER_NAME")
	public String getTimerName() {
		return timerName;
	}

	public void setTimerName(String timerName) {
		this.timerName = timerName;
	}

	@Column(name = "TIMER_KIND")
	public String getTimerKind() {
		return timerKind;
	}

	public void setTimerKind(String timerKind) {
		this.timerKind = timerKind;
	}

	@Column(name = "TIMER_KIND_ID")
	public String getTimerKindID() {
		return timerKindID;
	}

	public void setTimerKindID(String timerKindID) {
		this.timerKindID = timerKindID;
	}

	@Column(name = "ACTION")
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Column(name = "REGIS_TETIME")
	public Date getRegisteTime() {
		return registeTime;
	}

	public void setRegisteTime(Date registeTime) {
		this.registeTime = registeTime;
	}

	@Column(name = "TRIGGE_TIME")
	public Date getTriggeTime() {
		return triggeTime;
	}

	public void setTriggeTime(Date triggeTime) {
		this.triggeTime = triggeTime;
	}

	@Column(name = "CURRENTSTATE")
	public String getCurrentstate() {
		return currentstate;
	}

	public void setCurrentstate(String currentstate) {
		this.currentstate = currentstate;
	}

	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "定时器";
	}
}
