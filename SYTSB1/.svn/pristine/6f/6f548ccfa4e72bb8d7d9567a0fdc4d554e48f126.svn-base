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
@Table(name = "TJY2_RL_SUBSIDY")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Subsidy implements BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;//主键ID

    private String fkOvertimeId;//加班申请外键ID

    private String overtimeWorkerId;//加班人ID

    private String overtimeWorker;//加班人
    
    private String overtimeDate;//加班时间

    private String overtimeType;//加班情形

    private String compensatedLeave;//是否补休

    private String compensatedLeaveDate;//补休时间

    private String subsidy;//是否补助

    private String subsidyMoney;//补助金额

    private String workerSignId;//加班签字人ID

    private String workerSignName;//加班签字人姓名

    private java.util.Date workerSignDate;//加班签字人签字时间

    private String deptHeadId;//部门负责人ID

    private String deptHeadName;//部门负责人姓名

    private String deptHeadOpinion;//部门负责人意见

    private java.util.Date headCheckTime;//部门负责人审核日期

    private String hrId;//人力资源部负责人ID

    private String hrName;//人力资源部负责人姓名

    private String hrOpinion;//人力资源部负责人ID

    private java.util.Date hrCheckTime;//人力资源部负责人审核时间

    private String orgLeaderId;//分管领导ID

    private String orgLeaderName;//分管领导姓名

    private String orgLeaderOpinion;//分管领导意见

    private java.util.Date leaderCheckTime;//分管领导审核时间
    
    private String subsidyStatus;//分管领导意见

    private String createById;//创建人ID

    private String createBy;//创建人

    private java.util.Date createDate;//创建时间

    public void setId(String value){
        this.id = value;
    }
    public void setFkOvertimeId(String value){
        this.fkOvertimeId = value;
    }
    public void setOvertimeWorkerId(String value){
        this.overtimeWorkerId = value;
    }
    public void setOvertimeWorker(String value){
        this.overtimeWorker = value;
    }
    public void setOvertimeDate(String value){
        this.overtimeDate = value;
    }
    public void setOvertimeType(String value){
        this.overtimeType = value;
    }
    public void setCompensatedLeave(String value){
        this.compensatedLeave = value;
    }
    public void setCompensatedLeaveDate(String value){
        this.compensatedLeaveDate = value;
    }
    public void setSubsidy(String value){
        this.subsidy = value;
    }
    public void setSubsidyMoney(String value){
        this.subsidyMoney = value;
    }
    public void setWorkerSignId(String value){
        this.workerSignId = value;
    }
    public void setWorkerSignName(String value){
        this.workerSignName = value;
    }
    public void setWorkerSignDate(java.util.Date value){
        this.workerSignDate = value;
    }
    public void setDeptHeadId(String value){
        this.deptHeadId = value;
    }
    public void setDeptHeadName(String value){
        this.deptHeadName = value;
    }
    public void setDeptHeadOpinion(String value){
        this.deptHeadOpinion = value;
    }
    public void setHeadCheckTime(java.util.Date value){
        this.headCheckTime = value;
    }
    public void setHrId(String value){
        this.hrId = value;
    }
    public void setHrName(String value){
        this.hrName = value;
    }
    public void setHrOpinion(String value){
        this.hrOpinion = value;
    }
    public void setHrCheckTime(java.util.Date value){
        this.hrCheckTime = value;
    }
    public void setOrgLeaderId(String value){
        this.orgLeaderId = value;
    }
    public void setOrgLeaderName(String value){
        this.orgLeaderName = value;
    }
    public void setOrgLeaderOpinion(String value){
        this.orgLeaderOpinion = value;
    }
    public void setLeaderCheckTime(java.util.Date value){
        this.leaderCheckTime = value;
    }
    public void setSubsidyStatus(String value){
        this.subsidyStatus = value;
    }
    public void setCreateById(String value){
        this.createById = value;
    }
    public void setCreateBy(String value){
        this.createBy = value;
    }
    public void setCreateDate(java.util.Date value){
        this.createDate = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="FK_OVERTIME_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFkOvertimeId(){
        return this.fkOvertimeId;
    }
    @Column(name ="OVERTIME_WORKER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getOvertimeWorkerId(){
        return this.overtimeWorkerId;
    }
    @Column(name ="OVERTIME_WORKER",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getOvertimeWorker(){
        return this.overtimeWorker;
    }
    @Column(name ="OVERTIME_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getOvertimeDate(){
        return this.overtimeDate;
    }
    @Column(name ="OVERTIME_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getOvertimeType(){
        return this.overtimeType;
    }
    @Column(name ="COMPENSATED_LEAVE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getCompensatedLeave(){
        return this.compensatedLeave;
    }
    @Column(name ="COMPENSATED_LEAVE_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getCompensatedLeaveDate(){
        return this.compensatedLeaveDate;
    }
    @Column(name ="SUBSIDY",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getSubsidy(){
        return this.subsidy;
    }
    @Column(name ="SUBSIDY_MONEY",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getSubsidyMoney(){
        return this.subsidyMoney;
    }
    @Column(name ="WORKER_SIGN_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getWorkerSignId(){
        return this.workerSignId;
    }
    @Column(name ="WORKER_SIGN_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getWorkerSignName(){
        return this.workerSignName;
    }
    @Column(name ="WORKER_SIGN_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getWorkerSignDate(){
        return this.workerSignDate;
    }
    @Column(name ="DEPT_HEAD_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getDeptHeadId(){
        return this.deptHeadId;
    }
    @Column(name ="DEPT_HEAD_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getDeptHeadName(){
        return this.deptHeadName;
    }
    @Column(name ="DEPT_HEAD_OPINION",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getDeptHeadOpinion(){
        return this.deptHeadOpinion;
    }
    @Column(name ="HEAD_CHECK_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getHeadCheckTime(){
        return this.headCheckTime;
    }
    @Column(name ="HR_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getHrId(){
        return this.hrId;
    }
    @Column(name ="HR_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getHrName(){
        return this.hrName;
    }
    @Column(name ="HR_OPINION",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getHrOpinion(){
        return this.hrOpinion;
    }
    @Column(name ="HR_CHECK_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getHrCheckTime(){
        return this.hrCheckTime;
    }
    @Column(name ="ORG_LEADER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getOrgLeaderId(){
        return this.orgLeaderId;
    }
    @Column(name ="ORG_LEADER_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getOrgLeaderName(){
        return this.orgLeaderName;
    }
    @Column(name ="ORG_LEADER_OPINION",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getOrgLeaderOpinion(){
        return this.orgLeaderOpinion;
    }
    @Column(name ="LEADER_CHECK_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getLeaderCheckTime(){
        return this.leaderCheckTime;
    }
    @Column(name ="SUBSIDY_STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getSubsidyStatus(){
        return this.subsidyStatus;
    }
    @Column(name ="CREATE_BY_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getCreateById(){
        return this.createById;
    }
    @Column(name ="CREATE_BY",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getCreateBy(){
        return this.createBy;
    }
    @Column(name ="CREATE_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getCreateDate(){
        return this.createDate;
    }


} 
