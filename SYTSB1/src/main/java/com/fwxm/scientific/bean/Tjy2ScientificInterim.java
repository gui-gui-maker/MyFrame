package com.fwxm.scientific.bean;

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
@Table(name = "TJY2_SCIENTIFIC_INTERIM")
public class Tjy2ScientificInterim implements BaseEntity{

    private String id;//${columnb.remarks}

    private String fkScientificId;//科研项目id

    private String projectName;//项目名称

    private String projectNo;//项目编号

    private String projectSource;//项目来源

    private String projectHead;//项目负责人

    private String projectParticipationMan;//项目参与人

    private String approvalMoney;//批准经费

    private String projectStartEnd;//项目计划起止日期

    private java.util.Date projectMidDate;//中期检查时间

    private String projectPhase;//项目研究阶段

    private String scheduledTask;//本年度计划任务

    private String projectProgress;//进展情况

    private String progressInstructions;//进展情况说明

    private String totalCost;//已支出总金额

    private String totalCost1;//设备费

    private String totalCost2;//材料费

    private String totalCost3;//测试化验加工费

    private String totalCost4;//差旅费

    private String totalCost5;//会议费

    private String totalCost6;//出版/文献/信息传播/知识产权事务费

    private String totalCost7;//劳务费

    private String totalCost8;//专家咨询费

    private String totalCost9;//其他费用

    private String results1;//成果1

    private String results2;//成果2

    private String results3;//成果3

    private String results4;//成果4

    private String results5;//成果5

    private String results6;//成果6

    private String results7;//成果7

    private java.util.Date createDate;//创建时间

    private String createMan;//创建人

    private String opinion;//意见

    
    @Id
   	@GeneratedValue(generator = "system-uuid")
   	@GenericGenerator(name = "system-uuid", strategy = "uuid")
   	public String getId() {
   		return id;
   	}

   	public void setId(String id) {
   		this.id = id;
   	}
    
    public void setFkScientificId(String value){
        this.fkScientificId = value;
    }
    public void setProjectName(String value){
        this.projectName = value;
    }
    public void setProjectNo(String value){
        this.projectNo = value;
    }
    public void setProjectSource(String value){
        this.projectSource = value;
    }
    public void setProjectHead(String value){
        this.projectHead = value;
    }
    public void setProjectParticipationMan(String value){
        this.projectParticipationMan = value;
    }
    public void setApprovalMoney(String value){
        this.approvalMoney = value;
    }
    public void setProjectStartEnd(String value){
        this.projectStartEnd = value;
    }
    public void setProjectMidDate(java.util.Date value){
        this.projectMidDate = value;
    }
    public void setProjectPhase(String value){
        this.projectPhase = value;
    }
    public void setScheduledTask(String value){
        this.scheduledTask = value;
    }
    public void setProjectProgress(String value){
        this.projectProgress = value;
    }
    public void setProgressInstructions(String value){
        this.progressInstructions = value;
    }
    public void setTotalCost(String value){
        this.totalCost = value;
    }
    public void setTotalCost1(String value){
        this.totalCost1 = value;
    }
    public void setTotalCost2(String value){
        this.totalCost2 = value;
    }
    public void setTotalCost3(String value){
        this.totalCost3 = value;
    }
    public void setTotalCost4(String value){
        this.totalCost4 = value;
    }
    public void setTotalCost5(String value){
        this.totalCost5 = value;
    }
    public void setTotalCost6(String value){
        this.totalCost6 = value;
    }
    public void setTotalCost7(String value){
        this.totalCost7 = value;
    }
    public void setTotalCost8(String value){
        this.totalCost8 = value;
    }
    public void setTotalCost9(String value){
        this.totalCost9 = value;
    }
    public void setResults1(String value){
        this.results1 = value;
    }
    public void setResults2(String value){
        this.results2 = value;
    }
    public void setResults3(String value){
        this.results3 = value;
    }
    public void setResults4(String value){
        this.results4 = value;
    }
    public void setResults5(String value){
        this.results5 = value;
    }
    public void setResults6(String value){
        this.results6 = value;
    }
    public void setResults7(String value){
        this.results7 = value;
    }
    public void setCreateDate(java.util.Date value){
        this.createDate = value;
    }
    public void setCreateMan(String value){
        this.createMan = value;
    }
    public void setOpinion(String value){
        this.opinion = value;
    }
    @Column(name ="FK_SCIENTIFIC_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFkScientificId(){
        return this.fkScientificId;
    }
    @Column(name ="PROJECT_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getProjectName(){
        return this.projectName;
    }
    @Column(name ="PROJECT_NO",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getProjectNo(){
        return this.projectNo;
    }
    @Column(name ="PROJECT_SOURCE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getProjectSource(){
        return this.projectSource;
    }
    @Column(name ="PROJECT_HEAD",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getProjectHead(){
        return this.projectHead;
    }
    @Column(name ="PROJECT_PARTICIPATION_MAN",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getProjectParticipationMan(){
        return this.projectParticipationMan;
    }
    @Column(name ="APPROVAL_MONEY",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getApprovalMoney(){
        return this.approvalMoney;
    }
    @Column(name ="PROJECT_START_END",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getProjectStartEnd(){
        return this.projectStartEnd;
    }
    @Column(name ="PROJECT_MID_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getProjectMidDate(){
        return this.projectMidDate;
    }
    @Column(name ="PROJECT_PHASE",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getProjectPhase(){
        return this.projectPhase;
    }
    @Column(name ="SCHEDULED_TASK",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getScheduledTask(){
        return this.scheduledTask;
    }
    @Column(name ="PROJECT_PROGRESS",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getProjectProgress(){
        return this.projectProgress;
    }
    @Column(name ="PROGRESS_INSTRUCTIONS",unique=false,nullable=true,insertable=true,updatable=true,length=4000)
    public String getProgressInstructions(){
        return this.progressInstructions;
    }
    @Column(name ="TOTAL_COST",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getTotalCost(){
        return this.totalCost;
    }
    @Column(name ="TOTAL_COST1",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getTotalCost1(){
        return this.totalCost1;
    }
    @Column(name ="TOTAL_COST2",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getTotalCost2(){
        return this.totalCost2;
    }
    @Column(name ="TOTAL_COST3",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getTotalCost3(){
        return this.totalCost3;
    }
    @Column(name ="TOTAL_COST4",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getTotalCost4(){
        return this.totalCost4;
    }
    @Column(name ="TOTAL_COST5",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getTotalCost5(){
        return this.totalCost5;
    }
    @Column(name ="TOTAL_COST6",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getTotalCost6(){
        return this.totalCost6;
    }
    @Column(name ="TOTAL_COST7",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getTotalCost7(){
        return this.totalCost7;
    }
    @Column(name ="TOTAL_COST8",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getTotalCost8(){
        return this.totalCost8;
    }
    @Column(name ="TOTAL_COST9",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getTotalCost9(){
        return this.totalCost9;
    }
    @Column(name ="RESULTS1",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getResults1(){
        return this.results1;
    }
    @Column(name ="RESULTS2",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getResults2(){
        return this.results2;
    }
    @Column(name ="RESULTS3",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getResults3(){
        return this.results3;
    }
    @Column(name ="RESULTS4",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getResults4(){
        return this.results4;
    }
    @Column(name ="RESULTS5",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getResults5(){
        return this.results5;
    }
    @Column(name ="RESULTS6",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getResults6(){
        return this.results6;
    }
    @Column(name ="RESULTS7",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getResults7(){
        return this.results7;
    }
    @Column(name ="CREATE_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getCreateDate(){
        return this.createDate;
    }
    @Column(name ="CREATE_MAN",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getCreateMan(){
        return this.createMan;
    }
    @Column(name ="OPINION",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getOpinion(){
        return this.opinion;
    }


} 
