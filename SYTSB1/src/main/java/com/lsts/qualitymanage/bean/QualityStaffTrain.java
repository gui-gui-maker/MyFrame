package com.lsts.qualitymanage.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_QUALITY_STAFF_TRAIN")
@JsonIgnoreProperties(ignoreUnknown=true)
public class QualityStaffTrain implements BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;//主键

    private String userId;//职工ID

    private String userName;//职工姓名

    private String userGender;//职工性别

    private java.math.BigDecimal userAge;//职工年龄

    private String userDep;//职工所在部门

    private String userDepId;//职工所在部门ID

    private String userDuty;//职工职务

    private String trainUnit;//培训主办单位

    private String trainUnitId;//培训主办单位ID

    private String tratnFileNum;//培训文件文号

    private java.util.Date tratnTimeStart;//培训开始时间

    private java.util.Date tratnTimeEnd;//培训结束时间

    private String tratnSite;//培训地址

    private String tratnType;//培训类别 码表: TJY2_TRAIN_TYPE: (01:业务学习培训|02:取（换）检验资格证培训|03:其它培训)

    private String tratnCostDetail;//预计培训费用及出行方式

    private String applyReason;//申请理由

    private String applyName;//申请人

    private java.util.Date applyTime;//申请时间

    private String applyId;//申请人ID

    private String userDepIdea;//职工部门意见

    private String userDepLeader;//部门负责人

    private String userDepLeaderId;//部门负责人ID

    private java.util.Date userDepIdeaTime;//部门意见时间

    private String skillManageDepIdea;//科研技术管理部门意见

    private String skillManageDepId;//科研技术管理部门ID

    private String skillManageLeaderId;//科研技术管理部门负责人ID

    private String skillManageLeader;//科研技术管理部门负责人

    private java.util.Date skillManageIdeaTime;//科研技术管理意见时间

    private String branchedPassageLeaderIdea;//分管院领导意见

    private java.util.Date branchedPassageIdeaTime;//分管院领导意见时间

    private String trainFgLeaderIdea;//培训分管院领导意见

    private java.util.Date trainFgIdeaTime;//培训分管院领导意见时间

    private String deanExamineIdea;//院长审批意见

    private java.util.Date deanExamineIdeaTime;//院长审批意见时间

    private String status;//状态

    private String registrantId;//登记人ID

    private String registrantName;//登记人

    private java.util.Date registrantTime;//登记时间

    private String trainChartNumber;//编号
    
    private String applyDepId; //申请人部门ID
    
    private String applyDep;//申请人部门

    public void setId(String value){
        this.id = value;
    }
    public void setUserId(String value){
        this.userId = value;
    }
    public void setUserName(String value){
        this.userName = value;
    }
    public void setUserGender(String value){
        this.userGender = value;
    }
    public void setUserAge(java.math.BigDecimal value){
        this.userAge = value;
    }
    public void setUserDep(String value){
        this.userDep = value;
    }
    public void setUserDepId(String value){
        this.userDepId = value;
    }
    public void setUserDuty(String value){
        this.userDuty = value;
    }
    public void setTrainUnit(String value){
        this.trainUnit = value;
    }
    public void setTrainUnitId(String value){
        this.trainUnitId = value;
    }
    public void setTratnFileNum(String value){
        this.tratnFileNum = value;
    }
    public void setTratnTimeStart(java.util.Date value){
        this.tratnTimeStart = value;
    }
    public void setTratnTimeEnd(java.util.Date value){
        this.tratnTimeEnd = value;
    }
    public void setTratnSite(String value){
        this.tratnSite = value;
    }
    public void setTratnType(String value){
        this.tratnType = value;
    }
    public void setTratnCostDetail(String value){
        this.tratnCostDetail = value;
    }
    public void setApplyReason(String value){
        this.applyReason = value;
    }
    public void setApplyName(String value){
        this.applyName = value;
    }
    public void setApplyTime(java.util.Date value){
        this.applyTime = value;
    }
    public void setApplyId(String value){
        this.applyId = value;
    }
    public void setUserDepIdea(String value){
        this.userDepIdea = value;
    }
    public void setUserDepLeader(String value){
        this.userDepLeader = value;
    }
    public void setUserDepLeaderId(String value){
        this.userDepLeaderId = value;
    }
    public void setUserDepIdeaTime(java.util.Date value){
        this.userDepIdeaTime = value;
    }
    public void setSkillManageDepIdea(String value){
        this.skillManageDepIdea = value;
    }
    public void setSkillManageDepId(String value){
        this.skillManageDepId = value;
    }
    public void setSkillManageLeaderId(String value){
        this.skillManageLeaderId = value;
    }
    public void setSkillManageLeader(String value){
        this.skillManageLeader = value;
    }
    public void setSkillManageIdeaTime(java.util.Date value){
        this.skillManageIdeaTime = value;
    }
    public void setBranchedPassageLeaderIdea(String value){
        this.branchedPassageLeaderIdea = value;
    }
    public void setBranchedPassageIdeaTime(java.util.Date value){
        this.branchedPassageIdeaTime = value;
    }
    public void setTrainFgLeaderIdea(String value){
        this.trainFgLeaderIdea = value;
    }
    public void setTrainFgIdeaTime(java.util.Date value){
        this.trainFgIdeaTime = value;
    }
    public void setDeanExamineIdea(String value){
        this.deanExamineIdea = value;
    }
    public void setDeanExamineIdeaTime(java.util.Date value){
        this.deanExamineIdeaTime = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    public void setRegistrantId(String value){
        this.registrantId = value;
    }
    public void setRegistrantName(String value){
        this.registrantName = value;
    }
    public void setRegistrantTime(java.util.Date value){
        this.registrantTime = value;
    }
    public void setTrainChartNumber(String value){
        this.trainChartNumber = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=false,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="USER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getUserId(){
        return this.userId;
    }
    @Column(name ="USER_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getUserName(){
        return this.userName;
    }
    @Column(name ="USER_GENDER",unique=false,nullable=true,insertable=true,updatable=true,length=10)
    public String getUserGender(){
        return this.userGender;
    }
    @Column(name ="USER_AGE",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getUserAge(){
        return this.userAge;
    }
    @Column(name ="USER_DEP",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getUserDep(){
        return this.userDep;
    }
    @Column(name ="USER_DEP_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getUserDepId(){
        return this.userDepId;
    }
    @Column(name ="USER_DUTY",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getUserDuty(){
        return this.userDuty;
    }
    @Column(name ="TRAIN_UNIT",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getTrainUnit(){
        return this.trainUnit;
    }
    @Column(name ="TRAIN_UNIT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getTrainUnitId(){
        return this.trainUnitId;
    }
    @Column(name ="TRATN_FILE_NUM",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getTratnFileNum(){
        return this.tratnFileNum;
    }
    @Column(name ="TRATN_TIME_START",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getTratnTimeStart(){
        return this.tratnTimeStart;
    }
    @Column(name ="TRATN_TIME_END",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getTratnTimeEnd(){
        return this.tratnTimeEnd;
    }
    @Column(name ="TRATN_SITE",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getTratnSite(){
        return this.tratnSite;
    }
    @Column(name ="TRATN_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=10)
    public String getTratnType(){
        return this.tratnType;
    }
    @Column(name ="TRATN_COST_DETAIL",unique=false,nullable=true,insertable=true,updatable=true,length=4000)
    public String getTratnCostDetail(){
        return this.tratnCostDetail;
    }
    @Column(name ="APPLY_REASON",unique=false,nullable=true,insertable=true,updatable=true,length=4000)
    public String getApplyReason(){
        return this.applyReason;
    }
    @Column(name ="APPLY_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getApplyName(){
        return this.applyName;
    }
    @Column(name ="APPLY_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getApplyTime(){
        return this.applyTime;
    }
    @Column(name ="APPLY_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getApplyId(){
        return this.applyId;
    }
    @Column(name ="USER_DEP_IDEA",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getUserDepIdea(){
        return this.userDepIdea;
    }
    @Column(name ="USER_DEP_LEADER",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getUserDepLeader(){
        return this.userDepLeader;
    }
    @Column(name ="USER_DEP_LEADER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getUserDepLeaderId(){
        return this.userDepLeaderId;
    }
    @Column(name ="USER_DEP_IDEA_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getUserDepIdeaTime(){
        return this.userDepIdeaTime;
    }
    @Column(name ="SKILL_MANAGE_DEP_IDEA",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getSkillManageDepIdea(){
        return this.skillManageDepIdea;
    }
    @Column(name ="SKILL_MANAGE_DEP_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getSkillManageDepId(){
        return this.skillManageDepId;
    }
    @Column(name ="SKILL_MANAGE_LEADER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getSkillManageLeaderId(){
        return this.skillManageLeaderId;
    }
    @Column(name ="SKILL_MANAGE_LEADER",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getSkillManageLeader(){
        return this.skillManageLeader;
    }
    @Column(name ="SKILL_MANAGE_IDEA_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getSkillManageIdeaTime(){
        return this.skillManageIdeaTime;
    }
    @Column(name ="BRANCHED_PASSAGE_LEADER_IDEA",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getBranchedPassageLeaderIdea(){
        return this.branchedPassageLeaderIdea;
    }
    @Column(name ="BRANCHED_PASSAGE_IDEA_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getBranchedPassageIdeaTime(){
        return this.branchedPassageIdeaTime;
    }
    @Column(name ="TRAIN_FG_LEADER_IDEA",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getTrainFgLeaderIdea(){
        return this.trainFgLeaderIdea;
    }
    @Column(name ="TRAIN_FG_IDEA_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getTrainFgIdeaTime(){
        return this.trainFgIdeaTime;
    }
    @Column(name ="DEAN_EXAMINE_IDEA",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getDeanExamineIdea(){
        return this.deanExamineIdea;
    }
    @Column(name ="DEAN_EXAMINE_IDEA_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getDeanExamineIdeaTime(){
        return this.deanExamineIdeaTime;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=10)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="REGISTRANT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getRegistrantId(){
        return this.registrantId;
    }
    @Column(name ="REGISTRANT_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getRegistrantName(){
        return this.registrantName;
    }
    @Column(name ="REGISTRANT_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getRegistrantTime(){
        return this.registrantTime;
    }
    @Column(name ="TRAIN_CHART_NUMBER")
    public String getTrainChartNumber(){
        return this.trainChartNumber;
    }
    @Column(name = "APPLY_DEP_ID")
	public String getApplyDepId() {
		return applyDepId;
	}
	public void setApplyDepId(String applyDepId) {
		this.applyDepId = applyDepId;
	}
	@Column(name = "APPLY_DEP")
	public String getApplyDep() {
		return applyDep;
	}
	public void setApplyDep(String applyDep) {
		this.applyDep = applyDep;
	}


} 
