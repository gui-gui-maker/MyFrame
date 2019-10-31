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
@Table(name = "TJY2_RL_OVERTIME")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Overtime implements BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;//主键ID

    private String applicantId;//申请人ID

    private String applicant;//申请人

    private String departmentId;//所在部门ID

    private String departmentName;//所在部门

    private String overtimePlace;//加班地点

    private String overtimeType;//加班种类

    private String overtimeContent;//加班事由、事项

    private java.util.Date overtimeStart;//开始时间

    private java.util.Date overtimeEnd;//结束时间

    private String overtimeDuration;//加班时长

    private String deptHeadId;//部门负责人ID

    private String deptHeadName;//部门负责人姓名

    private String deptHeadOpinion;//部门负责人意见

    private java.util.Date headCheckTime;//部门负责人审核日期

    private String orgLeaderId;//分管领导ID

    private String orgLeaderName;//分管领导姓名

    private String orgLeaderOpinion;//分管领导意见

    private java.util.Date leaderCheckTime;//分管领导审核时间
    
    private String status;//状态

    private String createById;//创建人ID

    private String createBy;//创建人

    private java.util.Date createDate;//创建时间

    private String lastmodifyById;//最后修改人ID

    private String lastmodifyBy;//最后修改人

    private java.util.Date lastmodifyDate;//最后修改时间

    public void setId(String value){
        this.id = value;
    }
    public void setApplicantId(String value){
        this.applicantId = value;
    }
    public void setApplicant(String value){
        this.applicant = value;
    }
    public void setDepartmentId(String value){
        this.departmentId = value;
    }
    public void setDepartmentName(String value){
        this.departmentName = value;
    }
    public void setOvertimePlace(String value){
        this.overtimePlace = value;
    }
    public void setOvertimeType(String value){
        this.overtimeType = value;
    }
    public void setOvertimeContent(String value){
        this.overtimeContent = value;
    }
    public void setOvertimeStart(java.util.Date value){
        this.overtimeStart = value;
    }
    public void setOvertimeEnd(java.util.Date value){
        this.overtimeEnd = value;
    }
    public void setOvertimeDuration(String value){
        this.overtimeDuration = value;
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
    public void setStatus(String value){
        this.status = value;
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
    public void setLastmodifyById(String value){
        this.lastmodifyById = value;
    }
    public void setLastmodifyBy(String value){
        this.lastmodifyBy = value;
    }
    public void setLastmodifyDate(java.util.Date value){
        this.lastmodifyDate = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="APPLICANT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getApplicantId(){
        return this.applicantId;
    }
    @Column(name ="APPLICANT",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getApplicant(){
        return this.applicant;
    }
    @Column(name ="DEPARTMENT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getDepartmentId(){
        return this.departmentId;
    }
    @Column(name ="DEPARTMENT_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getDepartmentName(){
        return this.departmentName;
    }
    @Column(name ="OVERTIME_PLACE",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getOvertimePlace(){
        return this.overtimePlace;
    }
    @Column(name ="OVERTIME_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getOvertimeType(){
        return this.overtimeType;
    }
    @Column(name ="OVERTIME_CONTENT",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getOvertimeContent(){
        return this.overtimeContent;
    }
    @Column(name ="OVERTIME_START",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getOvertimeStart(){
        return this.overtimeStart;
    }
    @Column(name ="OVERTIME_END",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getOvertimeEnd(){
        return this.overtimeEnd;
    }
    @Column(name ="OVERTIME_DURATION",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getOvertimeDuration(){
        return this.overtimeDuration;
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
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getStatus(){
        return this.status;
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
    @Column(name ="LASTMODIFY_BY_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getLastmodifyById(){
        return this.lastmodifyById;
    }
    @Column(name ="LASTMODIFY_BY",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getLastmodifyBy(){
        return this.lastmodifyBy;
    }
    @Column(name ="LASTMODIFY_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getLastmodifyDate(){
        return this.lastmodifyDate;
    }


} 
