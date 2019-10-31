package com.lsts.humanresources.bean;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_RL_MESSAGE")
public class RlMessage implements BaseEntity{

    private String id;//${columnb.remarks}

    private String message;//消息内容

    private java.util.Date sendDate;//发送时间

    private String sendMan;//发送人


    private String sendManner;//发送方式
    private List<EmployeeBase> employee;
     
    @Id
   	@GeneratedValue(generator = "system-uuid")
   	@GenericGenerator(name = "system-uuid", strategy = "uuid")
   	public String getId() {
   		return id;
   	}
   	public void setId(String id) {
   		this.id = id;
   	}
    public void setMessage(String value){
        this.message = value;
    }
    public void setSendDate(java.util.Date value){
        this.sendDate = value;
    }
    public void setSendMan(String value){
        this.sendMan = value;
    }
    public void setSendManner(String value){
        this.sendManner = value;
    }
    @Column(name ="MESSAGE",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getMessage(){
        return this.message;
    }
    @Column(name ="SEND_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getSendDate(){
        return this.sendDate;
    }
    @Column(name ="SEND_MAN",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getSendMan(){
        return this.sendMan;
    }
    @Column(name ="SEND_MANNER",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getSendManner(){
        return this.sendManner;
    }
    @OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.LAZY,mappedBy="fkMessageId")
	public List<EmployeeBase> getEmployee() {
		return employee;
	}
	public void setEmployee(List<EmployeeBase> employee) {
		this.employee = employee;
	}


} 
