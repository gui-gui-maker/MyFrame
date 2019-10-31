package com.lsts.office.bean;

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
 *  
 * </p>
 * 
 * @ClassName MeetingUser
 * @JDK 1.6
 * @author 
 * @date 2
 */
@Entity
@Table(name = "TJY2_OFFICE_MEETING_REQ_USER")
public class MeetingUser implements BaseEntity {
	private static final long serialVersionUID = 1L;
	private String id;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}
	private String userId;//参会人id
	private String reqId;//会议室申请表id
	

	  
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	
	@Column(name ="REQ_ID",unique=true,nullable=true,insertable=true,updatable=true,length=32)
	public String getReqId() {
		return this.reqId;
	}
	
    @Column(name ="USER_ID",unique=true,nullable=true,insertable=true,updatable=true,length=32)
    public String getUserId() {
		return this.userId;
	}
}
