package com.lsts.humanresources.bean;

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
@Table(name = "TJY2_RL_REMIND_MESSAGE")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RemindMessage implements BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;//${columnb.remarks}

    private String fkRlEmplpyeeId;//员工信息表id

    private String stopRemindTime;//聘用到期提醒提前周/月

    private String stopRemind;//聘用到期提醒内容

    private String stopRemindSelf;//当事人聘用到期提醒内容

    private String stopRemindId;//聘用到期提醒对象ID

    private String stopRemindName;//聘用到期提醒对象姓名
    
    private String stopSendType;//聘用到期提醒发送方式

    private String birthdayRemindTime;//生日到期提醒提前周/月

    private String birthdayRemind;//生日到期提醒内容

    private String birthdayRemindSelf;//当事人生日到期提醒内容

    private String birthdayRemindId;//生日到期提醒对象ID

    private String birthdayRemindName;//生日到期提醒对象姓名

    private String birthdaySendType;//生日到期提醒发送方式

    private String retireRemindTime;//退休提醒提前周/月

    private String retireRemind;//退休提醒内容

    private String retireRemindSelf;//当事人退休提醒内容

    private String retireRemindId;//退休提醒对象ID

    private String retireRemindName;//退休提醒对象姓名
    
    private String retireSendType;//退休提醒发送方式

    private java.util.Date createDate;//创建时间

    private String createId;//创建人ID

    private String createBy;//创建人

    private java.util.Date lastModifyDate;//最后更新时间

    private String lastModifyId;//最后更新人ID

    private String lastModifyBy;//最后更新人

    public void setId(String value){
        this.id = value;
    }
    public void setFkRlEmplpyeeId(String value){
        this.fkRlEmplpyeeId = value;
    }
    public void setStopRemindTime(String value){
        this.stopRemindTime = value;
    }
    public void setStopRemind(String value){
        this.stopRemind = value;
    }
    public void setStopRemindSelf(String value){
        this.stopRemindSelf = value;
    }
    public void setStopRemindId(String value){
        this.stopRemindId = value;
    }
    public void setStopRemindName(String value){
        this.stopRemindName = value;
    }
    public void setStopSendType(String value){
        this.stopSendType = value;
    }
    public void setBirthdayRemindTime(String value){
        this.birthdayRemindTime = value;
    }
    public void setBirthdayRemind(String value){
        this.birthdayRemind = value;
    }
    public void setBirthdayRemindSelf(String value){
        this.birthdayRemindSelf = value;
    }
    public void setBirthdayRemindId(String value){
        this.birthdayRemindId = value;
    }
    public void setBirthdayRemindName(String value){
        this.birthdayRemindName = value;
    }
    public void setBirthdaySendType(String value){
        this.birthdaySendType = value;
    }
    public void setRetireRemindTime(String value){
        this.retireRemindTime = value;
    }
    public void setRetireRemind(String value){
        this.retireRemind = value;
    }
    public void setRetireRemindSelf(String value){
        this.retireRemindSelf = value;
    }
    public void setRetireRemindId(String value){
        this.retireRemindId = value;
    }
    public void setRetireRemindName(String value){
        this.retireRemindName = value;
    }
    public void setRetireSendType(String value){
        this.retireSendType = value;
    }
    public void setCreateDate(java.util.Date value){
        this.createDate = value;
    }
    public void setCreateId(String value){
        this.createId = value;
    }
    public void setCreateBy(String value){
        this.createBy = value;
    }
    public void setLastModifyDate(java.util.Date value){
        this.lastModifyDate = value;
    }
    public void setLastModifyId(String value){
        this.lastModifyId = value;
    }
    public void setLastModifyBy(String value){
        this.lastModifyBy = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="FK_RL_EMPLPYEE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFkRlEmplpyeeId(){
        return this.fkRlEmplpyeeId;
    }
    @Column(name ="STOP_REMIND_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getStopRemindTime(){
        return this.stopRemindTime;
    }
    @Column(name ="STOP_REMIND",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getStopRemind(){
        return this.stopRemind;
    }
    @Column(name ="STOP_REMIND_SELF",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getStopRemindSelf(){
        return this.stopRemindSelf;
    }
    @Column(name ="STOP_REMIND_ID",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getStopRemindId(){
        return this.stopRemindId;
    }
    @Column(name ="STOP_REMIND_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getStopRemindName(){
        return this.stopRemindName;
    }
    @Column(name ="STOP_SEND_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getStopSendType(){
        return this.stopSendType;
    }
    @Column(name ="BIRTHDAY_REMIND_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getBirthdayRemindTime(){
        return this.birthdayRemindTime;
    }
    @Column(name ="BIRTHDAY_REMIND",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getBirthdayRemind(){
        return this.birthdayRemind;
    }
    @Column(name ="BIRTHDAY_REMIND_SELF",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getBirthdayRemindSelf(){
        return this.birthdayRemindSelf;
    }
    @Column(name ="BIRTHDAY_REMIND_ID",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getBirthdayRemindId(){
        return this.birthdayRemindId;
    }
    @Column(name ="BIRTHDAY_REMIND_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getBirthdayRemindName(){
        return this.birthdayRemindName;
    }
    @Column(name ="BIRTHDAY_SEND_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getBirthdaySendType(){
        return this.birthdaySendType;
    }
    @Column(name ="RETIRE_REMIND_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getRetireRemindTime(){
        return this.retireRemindTime;
    }
    @Column(name ="RETIRE_REMIND",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getRetireRemind(){
        return this.retireRemind;
    }
    @Column(name ="RETIRE_REMIND_SELF",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getRetireRemindSelf(){
        return this.retireRemindSelf;
    }
    @Column(name ="RETIRE_REMIND_ID",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getRetireRemindId(){
        return this.retireRemindId;
    }
    @Column(name ="RETIRE_REMIND_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getRetireRemindName(){
        return this.retireRemindName;
    }
    @Column(name ="RETIRE_SEND_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getRetireSendType(){
        return this.retireSendType;
    }
    @Column(name ="CREATE_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getCreateDate(){
        return this.createDate;
    }
    @Column(name ="CREATE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getCreateId(){
        return this.createId;
    }
    @Column(name ="CREATE_BY",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getCreateBy(){
        return this.createBy;
    }
    @Column(name ="LAST_MODIFY_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getLastModifyDate(){
        return this.lastModifyDate;
    }
    @Column(name ="LAST_MODIFY_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getLastModifyId(){
        return this.lastModifyId;
    }
    @Column(name ="LAST_MODIFY_BY",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getLastModifyBy(){
        return this.lastModifyBy;
    }


} 
