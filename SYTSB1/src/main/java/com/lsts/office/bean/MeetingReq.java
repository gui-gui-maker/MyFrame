package com.lsts.office.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/*******************************************************************************
 * 
 * <p>
 *  
 * </p>
 * 
 * @ClassName MeetingReq
 * @JDK 1.6
 * @author 
 * @date 2
 */
@Entity
@Table(name = "TJY2_OFFICE_MEETING_REQ")
@JsonIgnoreProperties(ignoreUnknown=true)
public class MeetingReq implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;//
	private String roomCode;//会议室编号
	private String fkOaMeetingRoom;//会议室id
	private String name;//会议名称
	private String compere;//承办人
	private Date startTime;//开始时间
	private Date endTime;//结束时间
	private String content;//会议简介
	 private String sqDepartment;//申请部门
	
	    private String ifZzhb;//是否制作会标

	    private String ifHyby;//是否制做欢迎标语

	    private int pnumber;//参会人数

	    private String ifZzhyzp;//是否制做会议座牌

	    private String hyAdress;//会议地址

	    private String ifZzjczp;//是否制做就餐座牌

	    private String hysbzxs;//会议室布置形式

	    private String ifFlowers;//是否摆鲜花

	    private String ifFruits;//是否要水果

	    private String officeOtherPz;//需要办公室配合的其它事项

	    private String szrYj;//室主任意见

	    private String officeYj;//办公室意见

	    private String fgyzYj;//分管院长意见

	    private String yzYj;//院长意见

	private String djPeople;// 登记人
	
	private String djPeopleId;// 登记人id

	



	private java.util.Date djDate;// 登记时间

	private String remarks;// 备注
	private String meeting_status="0";//会议召开状态   0`未发送通知`1`已发开会通知`2`会议已结束`
	
	




	private String status;// 会议室申请状态 1`未提交`2`待审核`3`审核中`4`审核通过`5`审核失败6已撤销
							
	// private List<MeetingUser> lUser;//参会人员
	// private List<MeetingOrg> lOrg;//参会单位
	private MeetingNotes meetingNotes;// 会议记录



	/*	private String  meetingUser;//参会人id
	    
	    private String  meetingOrg;//参会组织 机构id
	    
	    public String getMeetingUser() {
	    	return meetingUser;
	    }
	    
	    public void setMeetingUser(String meetingUser) {
	    	this.meetingUser = meetingUser;
	    }
	    
	    public String getMeetingOrg() {
	    	return meetingOrg;
	    }
	    
	    public void setMeetingOrg(String meetingOrg) {
	    	this.meetingOrg = meetingOrg;
	    }
	    */

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}
	 public void setSqDepartment(String value){
	    	this.sqDepartment = value;
	    }
	    public void setIfZzhb(String value){
	        this.ifZzhb = value;
	    }
	    public void setIfHyby(String value){
	        this.ifHyby = value;
	    }
	    public void setPnumber(int value){
	        this.pnumber = value;
	    }
	    public void setIfZzhyzp(String value){
	        this.ifZzhyzp = value;
	    }
	    public void setHyAdress(String value){
	        this.hyAdress = value;
	    }
	    public void setIfZzjczp(String value){
	        this.ifZzjczp = value;
	    }
	    public void setHysbzxs(String value){
	        this.hysbzxs = value;
	    }
	    public void setIfFlowers(String value){
	        this.ifFlowers = value;
	    }
	    public void setIfFruits(String value){
	        this.ifFruits = value;
	    }
	    public void setOfficeOtherPz(String value){
	        this.officeOtherPz = value;
	    }
	    public void setSzrYj(String value){
	        this.szrYj = value;
	    }
	    public void setOfficeYj(String value){
	        this.officeYj = value;
	    }
	    public void setFgyzYj(String value){
	        this.fgyzYj = value;
	    }
	    public void setYzYj(String value){
	        this.yzYj = value;
	    }
	    public void setDjPeople(String value){
	        this.djPeople = value;
	    }
	    public void setDjDate(java.util.Date value){
	        this.djDate = value;
	    }
	    public void setMeeting_status(String meeting_status) {
			this.meeting_status = meeting_status;
		}
	   

		public void setDjPeopleId(String djPeopleId) {
			this.djPeopleId = djPeopleId;
		}
		 @Column(name ="DJ_PEOPPLEID",unique=false,nullable=true,insertable=true,updatable=true,length=40)
		 public String getDjPeopleId() {
				return this.djPeopleId;
			}
	    @Column(name ="SQ_DEPARTMENT",unique=false,nullable=true,insertable=true,updatable=true,length=100)
	    public String getSqDepartment(){
	        return this.sqDepartment;
	    }
	    @Column(name ="IF_ZZHB",unique=false,nullable=true,insertable=true,updatable=true,length=4)
	    public String getIfZzhb(){
	        return this.ifZzhb;
	    }
	    @Column(name ="IF_HYBY",unique=false,nullable=true,insertable=true,updatable=true,length=4)
	    public String getIfHyby(){
	        return this.ifHyby;
	    }
	    @Column(name ="P_NUMBER",unique=false,nullable=true,insertable=true,updatable=true,length=40)
	    public Integer getPnumber(){
	        return this.pnumber;
	    }
	    @Column(name ="IF_ZZHYZP",unique=false,nullable=true,insertable=true,updatable=true,length=4)
	    public String getIfZzhyzp(){
	        return this.ifZzhyzp;
	    }
	    @Column(name ="HY_ADRESS",unique=false,nullable=true,insertable=true,updatable=true,length=100)
	    public String getHyAdress(){
	        return this.hyAdress;
	    }
	    @Column(name ="IF_ZZJCZP",unique=false,nullable=true,insertable=true,updatable=true,length=4)
	    public String getIfZzjczp(){
	        return this.ifZzjczp;
	    }
	    @Column(name ="HYSBZXS",unique=false,nullable=true,insertable=true,updatable=true,length=50)
	    public String getHysbzxs(){
	        return this.hysbzxs;
	    }
	    @Column(name ="IF_FLOWERS",unique=false,nullable=true,insertable=true,updatable=true,length=4)
	    public String getIfFlowers(){
	        return this.ifFlowers;
	    }
	    @Column(name ="IF_FRUITS",unique=false,nullable=true,insertable=true,updatable=true,length=4)
	    public String getIfFruits(){
	        return this.ifFruits;
	    }
	    @Column(name ="OFFICE_OTHER_PZ",unique=false,nullable=true,insertable=true,updatable=true,length=200)
	    public String getOfficeOtherPz(){
	        return this.officeOtherPz;
	    }
	    @Column(name ="SZR_YJ",unique=false,nullable=true,insertable=true,updatable=true,length=100)
	    public String getSzrYj(){
	        return this.szrYj;
	    }
	    @Column(name ="OFFICE_YJ",unique=false,nullable=true,insertable=true,updatable=true,length=100)
	    public String getOfficeYj(){
	        return this.officeYj;
	    }
	    @Column(name ="FGYZ_YJ",unique=false,nullable=true,insertable=true,updatable=true,length=100)
	    public String getFgyzYj(){
	        return this.fgyzYj;
	    }
	    @Column(name ="YZ_YJ",unique=false,nullable=true,insertable=true,updatable=true,length=100)
	    public String getYzYj(){
	        return this.yzYj;
	    }
	    @Column(name ="DJ_PEOPLE",unique=false,nullable=true,insertable=true,updatable=true,length=10)
	    public String getDjPeople(){
	        return this.djPeople;
	    }
	    @Column(name ="DJ_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=50)
	    public java.util.Date getDjDate(){
	        return this.djDate;
	    } 
	    @Column(name ="MEETING_STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=10)
		public String getMeeting_status() {
			return meeting_status;
		}
	@Column(name="ROOM_CODE")
	public String getRoomCode(){
		return roomCode;
	}

	public void setRoomCode(String roomCode){
		this.roomCode=roomCode;
	}

	@Column(name="FK_OFFICE_MEETING_ROOM")
	public String getFkOaMeetingRoom(){
		return fkOaMeetingRoom;
	}

	public void setFkOaMeetingRoom(String fkOaMeetingRoom){
		this.fkOaMeetingRoom=fkOaMeetingRoom;
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

	@Column(name="START_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	public java.util.Date getStartTime(){
		return startTime;
	}

	public void setStartTime(java.util.Date  startTime){
		this.startTime=startTime;
	}

	@Column(name="END_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	public java.util.Date getEndTime(){
		return endTime;
	}

	public void setEndTime(java.util.Date endTime){
		this.endTime=endTime; 
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
	@Column(name="STATUS")
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status=status;
	}
	

	
	@Transient
	public MeetingNotes getMeetingNotes() {
		return meetingNotes;
	}

	public void setMeetingNotes(MeetingNotes meetingNotes) {
		this.meetingNotes = meetingNotes;
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
