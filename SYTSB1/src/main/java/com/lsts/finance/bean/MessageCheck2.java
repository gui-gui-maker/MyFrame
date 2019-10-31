package com.lsts.finance.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_MESSAGE_CHECK")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageCheck2 implements BaseEntity{

    private String id;//${columnb.remarks}

    private String businessId;//业务类型

    private String sendType;//验证码发送方式 DX：短信 WX：微信

    private String account;//接收手机号码

    private String wxNumber;//接收微信号码

    private java.util.Date sendTime;//发送时间

    private java.util.Date endTime;//超时时间

    private java.util.Date checkTime;//验证时间

    private String status;//状态  enable：有效 disable：失效

    private String ip;//IP地址

    private String fkMsg;//验证码

    public void setId(String value){
        this.id = value;
    }
    public void setBusinessId(String value){
        this.businessId = value;
    }
    public void setSendType(String value){
        this.sendType = value;
    }
    public void setAccount(String value){
        this.account = value;
    }
    public void setWxNumber(String value){
        this.wxNumber = value;
    }
    public void setSendTime(java.util.Date value){
        this.sendTime = value;
    }
    public void setEndTime(java.util.Date value){
        this.endTime = value;
    }
    public void setCheckTime(java.util.Date value){
        this.checkTime = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    public void setIp(String value){
        this.ip = value;
    }
    public void setFkMsg(String value){
        this.fkMsg = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="BUSINESS_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getBusinessId(){
        return this.businessId;
    }
    @Column(name ="SEND_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=20)
    public String getSendType(){
        return this.sendType;
    }
    @Column(name ="ACCOUNT",unique=false,nullable=true,insertable=true,updatable=true,length=20)
    public String getAccount(){
        return this.account;
    }
    @Column(name ="WX_NUMBER",unique=false,nullable=true,insertable=true,updatable=true,length=20)
    public String getWxNumber(){
        return this.wxNumber;
    }
    @Column(name ="SEND_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getSendTime(){
        return this.sendTime;
    }
    @Column(name ="END_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getEndTime(){
        return this.endTime;
    }
    @Column(name ="CHECK_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getCheckTime(){
        return this.checkTime;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=20)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="IP",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getIp(){
        return this.ip;
    }
    @Column(name ="FK_MSG",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getFkMsg(){
        return this.fkMsg;
    }


} 
