package com.lsts.common.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_MESSAGE")
public class MessageXinxi implements BaseEntity{
	private static final long serialVersionUID = 1L;

	private String id;//id

    private String personId;//接收人ID

    private String personName;//接收人姓名

    private String account;//电话号码
    
    private String accountWeixin;//微信帐号

    private Date sendTime;//发送时间

    private String status;//状态

    private String fkMsg;//短信内容

    private String businessType;//业务类型（内控，办公等）

    private String sendType;//发送类型（定时发送、实时发送）

    private String transmitMode;//发送方式（短信，微信）


   
	public void setAccountWeixin(String accountWeixin) {
		this.accountWeixin = accountWeixin;
	}
	public void setId(String value){
        this.id = value;
    }
    public void setPersonId(String value){
        this.personId = value;
    }
    public void setPersonName(String value){
        this.personName = value;
    }
    public void setAccount(String value){
        this.account = value;
    }
    public void setSendTime(Date sendTime){
        this.sendTime = sendTime;
    }
    public void setStatus(String value){
        this.status = value;
    }
    public void setFkMsg(String value){
        this.fkMsg = value;
    }
    public void setBusinessType(String value){
        this.businessType = value;
    }
    public void setSendType(String value){
        this.sendType = value;
    }
    public void setTransmitMode(String value){
        this.transmitMode = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="PERSON_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getPersonId(){
        return this.personId;
    }
    @Column(name ="PERSON_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getPersonName(){
        return this.personName;
    }
    @Column(name ="ACCOUNT",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getAccount(){
        return this.account;
    }
    @Column(name ="SEND_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public Date getSendTime(){
        return this.sendTime;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=8)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="FK_MSG",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getFkMsg(){
        return this.fkMsg;
    }
    @Column(name ="BUSINESS_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getBusinessType(){
        return this.businessType;
    }
    @Column(name ="SEND_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getSendType(){
        return this.sendType;
    }
    @Column(name ="TRANSMIT_MODE",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getTransmitMode(){
        return this.transmitMode;
    }
    @Column(name ="ACCOUNT_WEIXIN",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getAccountWeixin() {
		return accountWeixin;
	}

} 
