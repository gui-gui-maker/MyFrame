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
@Table(name = "TJY2_RL_LEAVE")
@JsonIgnoreProperties(ignoreUnknown = true)
public class WxLeave implements BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;//主键

    private String peopleId;//请假人ID

    private String peopleName;//请假人姓名

    private String depId;//所在部门ID

    private String depName;//所在部门名称

    private String leaveType;//申请假期种类

    private java.util.Date startDate;//假期开始时间

    private java.util.Date endDate;//假期结束时间

    private String depHeadName;//部门负责人确认签字

    private String leaveReason;//请假理由

    private String peopleSign;//请假人签名

    private java.util.Date peopleSignDate;//签名日期

    private String rsyj;//人事意见

    private java.util.Date rsyjDate;//人事意见日期

    private String ksfzryj;//科室负责人意见

    private java.util.Date ksfzryjDate;//科室负责人意见日期

    private String fgyldyj;//分管院领导意见

    private java.util.Date fgyldyjDate;//分管院领导日期

    private String yzyj;//院长意见

    private java.util.Date yzyjDate;//院长意见日期

    private java.util.Date xjrqDate;//销假日期

    private String xjPeopleSign;//本人签字

    private String xjHrSign;//人力资源管理部经办人签字

    private String applyStatus;//申请状态

    private String createName;//创建人

    private java.util.Date createDate;//创建时间

    private String lastModifyName;//最近修改人

    private java.util.Date lastModifyDate;//最近修改时间

    private String rsyjSign;//人事签字

    private String ksfzryjSing;//科室负责人签字

    private String fgyldyjSign;//分管院领导签字

    private String yzyjSign;//院长签字
    
    private String leaveCount1;//假期天数
    
    private String leaveCount2;//实际假期天数
    
    private String total;//已请假种类及天数
    
    public void setId(String value){
        this.id = value;
    }
    public void setPeopleId(String value){
        this.peopleId = value;
    }
    public void setPeopleName(String value){
        this.peopleName = value;
    }
    public void setDepId(String value){
        this.depId = value;
    }
    public void setDepName(String value){
        this.depName = value;
    }
    public void setLeaveType(String value){
        this.leaveType = value;
    }
    public void setStartDate(java.util.Date value){
        this.startDate = value;
    }
    public void setEndDate(java.util.Date value){
        this.endDate = value;
    }
    public void setDepHeadName(String value){
        this.depHeadName = value;
    }
    public void setLeaveReason(String value){
        this.leaveReason = value;
    }
    public void setPeopleSign(String value){
        this.peopleSign = value;
    }
    public void setPeopleSignDate(java.util.Date value){
        this.peopleSignDate = value;
    }
    public void setRsyj(String value){
        this.rsyj = value;
    }
    public void setRsyjDate(java.util.Date value){
        this.rsyjDate = value;
    }
    public void setKsfzryj(String value){
        this.ksfzryj = value;
    }
    public void setKsfzryjDate(java.util.Date value){
        this.ksfzryjDate = value;
    }
    public void setFgyldyj(String value){
        this.fgyldyj = value;
    }
    public void setFgyldyjDate(java.util.Date value){
        this.fgyldyjDate = value;
    }
    public void setYzyj(String value){
        this.yzyj = value;
    }
    public void setYzyjDate(java.util.Date value){
        this.yzyjDate = value;
    }
    public void setXjrqDate(java.util.Date value){
        this.xjrqDate = value;
    }
    public void setXjPeopleSign(String value){
        this.xjPeopleSign = value;
    }
    public void setXjHrSign(String value){
        this.xjHrSign = value;
    }
    public void setApplyStatus(String value){
        this.applyStatus = value;
    }
    public void setCreateName(String value){
        this.createName = value;
    }
    public void setCreateDate(java.util.Date value){
        this.createDate = value;
    }
    public void setLastModifyName(String value){
        this.lastModifyName = value;
    }
    public void setLastModifyDate(java.util.Date value){
        this.lastModifyDate = value;
    }
    public void setRsyjSign(String value){
        this.rsyjSign = value;
    }
    public void setKsfzryjSing(String value){
        this.ksfzryjSing = value;
    }
    public void setFgyldyjSign(String value){
        this.fgyldyjSign = value;
    }
    public void setYzyjSign(String value){
        this.yzyjSign = value;
    }
    public void setLeaveCount1(String value){
        this.leaveCount1 = value;
    }
    public void setLeaveCount2(String value){
        this.leaveCount2 = value;
    }
    public void setTotal(String value){
        this.total = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="PEOPLE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getPeopleId(){
        return this.peopleId;
    }
    @Column(name ="PEOPLE_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getPeopleName(){
        return this.peopleName;
    }
    @Column(name ="DEP_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getDepId(){
        return this.depId;
    }
    @Column(name ="DEP_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getDepName(){
        return this.depName;
    }
    @Column(name ="LEAVE_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getLeaveType(){
        return this.leaveType;
    }
    @Column(name ="START_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getStartDate(){
        return this.startDate;
    }
    @Column(name ="END_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getEndDate(){
        return this.endDate;
    }
    @Column(name ="DEP_HEAD_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getDepHeadName(){
        return this.depHeadName;
    }
    @Column(name ="LEAVE_REASON",unique=false,nullable=true,insertable=true,updatable=true,length=400)
    public String getLeaveReason(){
        return this.leaveReason;
    }
    @Column(name ="PEOPLE_SIGN",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getPeopleSign(){
        return this.peopleSign;
    }
    @Column(name ="PEOPLE_SIGN_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getPeopleSignDate(){
        return this.peopleSignDate;
    }
    @Column(name ="RSYJ",unique=false,nullable=true,insertable=true,updatable=true,length=400)
    public String getRsyj(){
        return this.rsyj;
    }
    @Column(name ="RSYJ_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getRsyjDate(){
        return this.rsyjDate;
    }
    @Column(name ="KSFZRYJ",unique=false,nullable=true,insertable=true,updatable=true,length=400)
    public String getKsfzryj(){
        return this.ksfzryj;
    }
    @Column(name ="KSFZRYJ_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getKsfzryjDate(){
        return this.ksfzryjDate;
    }
    @Column(name ="FGYLDYJ",unique=false,nullable=true,insertable=true,updatable=true,length=400)
    public String getFgyldyj(){
        return this.fgyldyj;
    }
    @Column(name ="FGYLDYJ_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getFgyldyjDate(){
        return this.fgyldyjDate;
    }
    @Column(name ="YZYJ",unique=false,nullable=true,insertable=true,updatable=true,length=400)
    public String getYzyj(){
        return this.yzyj;
    }
    @Column(name ="YZYJ_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getYzyjDate(){
        return this.yzyjDate;
    }
    @Column(name ="XJRQ_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getXjrqDate(){
        return this.xjrqDate;
    }
    @Column(name ="XJ_PEOPLE_SIGN",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getXjPeopleSign(){
        return this.xjPeopleSign;
    }
    @Column(name ="XJ_HR_SIGN",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getXjHrSign(){
        return this.xjHrSign;
    }
    @Column(name ="APPLY_STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getApplyStatus(){
        return this.applyStatus;
    }
    @Column(name ="CREATE_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getCreateName(){
        return this.createName;
    }
    @Column(name ="CREATE_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getCreateDate(){
        return this.createDate;
    }
    @Column(name ="LAST_MODIFY_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getLastModifyName(){
        return this.lastModifyName;
    }
    @Column(name ="LAST_MODIFY_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getLastModifyDate(){
        return this.lastModifyDate;
    }
    @Column(name ="RSYJ_SIGN",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getRsyjSign(){
        return this.rsyjSign;
    }
    @Column(name ="KSFZRYJ_SING",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getKsfzryjSing(){
        return this.ksfzryjSing;
    }
    @Column(name ="FGYLDYJ_SIGN",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getFgyldyjSign(){
        return this.fgyldyjSign;
    }
    @Column(name ="YZYJ_SIGN",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getYzyjSign(){
        return this.yzyjSign;
    }
    @Column(name ="LEAVE_COUNT1",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getLeaveCount1(){
        return this.leaveCount1;
    }
    @Column(name ="LEAVE_COUNT2",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getLeaveCount2(){
        return this.leaveCount2;
    }
    @Column(name ="TOTAL",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getTotal(){
        return this.total;
    }


} 
