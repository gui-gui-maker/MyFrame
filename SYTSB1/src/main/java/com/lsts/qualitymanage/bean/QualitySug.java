package com.lsts.qualitymanage.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_QUALITY_SUG")
public class QualitySug implements BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;//主键

    private String businessId;//业务id

    private String sug;//处理意见

    private String ass;//签字id

    private String seal;//盖章id

    private String spUserName;//处理人

    private String spUserId;//处理人id

    private java.util.Date spTime;//处理时间

    private String spResult;//处理结果

    private String spLevel;//处理级别

    private String returnName;//退回环节名称

    private String status;//处理后状态

    private String businessName;//业务名称

    public void setId(String value){
        this.id = value;
    }
    public void setBusinessId(String value){
        this.businessId = value;
    }
    public void setSug(String value){
        this.sug = value;
    }
    public void setAss(String value){
        this.ass = value;
    }
    public void setSeal(String value){
        this.seal = value;
    }
    public void setSpUserName(String value){
        this.spUserName = value;
    }
    public void setSpUserId(String value){
        this.spUserId = value;
    }
    public void setSpTime(java.util.Date value){
        this.spTime = value;
    }
    public void setSpResult(String value){
        this.spResult = value;
    }
    public void setSpLevel(String value){
        this.spLevel = value;
    }
    public void setReturnName(String value){
        this.returnName = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    public void setBusinessName(String value){
        this.businessName = value;
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
    @Column(name ="SUG",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getSug(){
        return this.sug;
    }
    @Column(name ="ASS",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getAss(){
        return this.ass;
    }
    @Column(name ="SEAL",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getSeal(){
        return this.seal;
    }
    @Column(name ="SP_USER_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getSpUserName(){
        return this.spUserName;
    }
    @Column(name ="SP_USER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getSpUserId(){
        return this.spUserId;
    }
    @Column(name ="SP_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getSpTime(){
        return this.spTime;
    }
    @Column(name ="SP_RESULT",unique=false,nullable=true,insertable=true,updatable=true,length=10)
    public String getSpResult(){
        return this.spResult;
    }
    @Column(name ="SP_LEVEL",unique=false,nullable=true,insertable=true,updatable=true,length=40)
    public String getSpLevel(){
        return this.spLevel;
    }
    @Column(name ="RETURN_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getReturnName(){
        return this.returnName;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=10)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="BUSINESS_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getBusinessName(){
        return this.businessName;
    }


} 
