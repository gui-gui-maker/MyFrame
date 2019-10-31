package com.lsts.office.bean;

import com.khnt.core.crud.bean.BaseEntity;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/*******************************************************************************
 * 
 * <p>
 *  
 * </p>
 * 
 * @ClassName MeetingNotes
 * @JDK 1.6
 * @author 
 * @date 2
 */
@Entity
@Table(name = "TJY2_OFFICE_MEETING_MINUTES")
@JsonIgnoreProperties(ignoreUnknown=true)
public class MeetingNotes implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;//
	private String fkroomid;//会议室id
	private String fkreqid;//会议申请id
	private String name;//会议名称
	private String department;//申请部门
	//private String departmentid;//申请部门id
	private String compere;//主持人
/*	private String meetingOrg;//参会单位
	private String meetingUser;//参会人
*/	private Date startTime;//开始时间
	private Date endTime;//结束时间
	private String personId;//会议记录人id
	private String personName;//会议记录人姓名
	private Date notesTime;//记录时间
	private String content;//会议内容
	private String remarks;//备注
	private String uploadIds;
	

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}
	@Column(name="SQ_DEPARTMENT")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	/*@Column(name="SQ_DEPARTMENT")
	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}*/
	@Column(name="FKROOMID")
	public String getFkroomid(){
		return fkroomid;
	}

	public void setFkroomid(String fkroomid){
		this.fkroomid=fkroomid;
	}

	@Column(name="FKREQID")
	public String getFkreqid(){
		return fkreqid;
	}

	public void setFkreqid(String fkreqid){
		this.fkreqid=fkreqid;
	}

	@Column(name="NAME")
	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name=name;
	}

	@Column(name="COMPERE")
	public String getCompere(){
		return compere;
	}

	public void setCompere(String compere){
		this.compere=compere;
	}

	/*@Column(name="MEETING_ORG")
	public String getMeetingOrg(){
		return meetingOrg;
	}

	public void setMeetingOrg(String meetingOrg){
		this.meetingOrg=meetingOrg;
	}

	@Column(name="MEETING_USER")
	public String getMeetingUser(){
		return meetingUser;
	}

	public void setMeetingUser(String meetingUser){
		this.meetingUser=meetingUser;
	}*/

	@Column(name="START_TIME")
	public Date getStartTime(){
		return startTime;
	}

	public void setStartTime(Date startTime){
		this.startTime=startTime;
	}

	@Column(name="END_TIME")
	public Date getEndTime(){
		return endTime;
	}

	public void setEndTime(Date endTime){
		this.endTime=endTime;
	}

	@Column(name="PERSON_ID")
	public String getPersonId(){
		return personId;
	}

	public void setPersonId(String personId){
		this.personId=personId;
	}

	@Column(name="PERSON_NAME")
	public String getPersonName(){
		return personName;
	}

	public void setPersonName(String personName){
		this.personName=personName;
	}

	@Column(name="NOTES_TIME")
	public Date getNotesTime(){
		return notesTime;
	}

	public void setNotesTime(Date notesTime){
		this.notesTime=notesTime;
	}

	@Column(name="CONTENT")
	public String getContent(){
		return content;
	}

	public void setContent(String content){
		this.content=content;
	}

	@Column(name="REMARKS")
	public String getRemarks(){
		return remarks;
	}

	public void setRemarks(String remarks){
		this.remarks=remarks;
	}

	@Transient
	public String getUploadIds() {
		return uploadIds;
	}

	public void setUploadIds(String uploadIds) {
		this.uploadIds = uploadIds;
	}

	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "未说明";

	}
}
