package com.lsts.humanresources.bean;

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
@Table(name = "TJY2_RL_REMOVE")
public class Remove implements BaseEntity{

    private String id;//${columnb.remarks}

    private String manner;//移除方式(1.解聘 2.退休)

    private String reason;//移除理由

    private String operateMan;//操作人

    private java.util.Date operateDate;//操作时间

    private String fkEmployeeBaseId;//基础信息id

    @Id
   	@GeneratedValue(generator = "system-uuid")
   	@GenericGenerator(name = "system-uuid", strategy = "uuid")
   	public String getId() {
   		return id;
   	}
   	public void setId(String id) {
   		this.id = id;
   	}
    public void setManner(String value){
        this.manner = value;
    }
    public void setReason(String value){
        this.reason = value;
    }
    public void setOperateMan(String value){
        this.operateMan = value;
    }
    public void setOperateDate(java.util.Date value){
        this.operateDate = value;
    }
    public void setFkEmployeeBaseId(String value){
        this.fkEmployeeBaseId = value;
    }

    @Column(name ="MANNER",unique=false,nullable=true,insertable=true,updatable=true,length=10)
    public String getManner(){
        return this.manner;
    }
    @Column(name ="REASON",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getReason(){
        return this.reason;
    }
    @Column(name ="OPERATE_MAN",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getOperateMan(){
        return this.operateMan;
    }
    @Column(name ="OPERATE_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getOperateDate(){
        return this.operateDate;
    }
    @Column(name ="FK_EMPLOYEE_BASE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFkEmployeeBaseId(){
        return this.fkEmployeeBaseId;
    }


} 
