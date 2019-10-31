package com.lsts.nk_message.bean;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_SEND_MESSAGE")
public class MobileMessage implements BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;//${columnb.remarks}

//    private String personId;//接收人ID

    private String personName;//接收人姓名

    private String account;//消息接受帐号(电话号码)

    private java.util.Date sendTime;//发送时间

//    private String status;//状态

    private String fkMsg;//短信内容

//    private String businessType;//业务类型（内控，办公等）

//   private String sendType;//发送类型（定时发送、实时发送）

//    private String transmitMode;//发送方式（短信，微信）


    public void setId(String value){
        this.id = value;
    }
    public void setPersonName(String value){
        this.personName = value;
    }
    public void setAccount(String value){
        this.account = value;
    }
    public void setSendTime(java.util.Date value){
        this.sendTime = value;
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
    @Column(name ="PERSON_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getPersonName(){
        return this.personName;
    }
    @Column(name ="ACCOUNT",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getAccount(){
        return this.account;
    }
    public java.util.Date getSendTime(){
        return this.sendTime;
    }
    @Column(name ="FK_MSG",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getFkMsg(){
        return this.fkMsg;
    }


} 
