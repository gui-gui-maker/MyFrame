package com.lsts.office.bean;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_BG_WEIGHTYTASK")
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeightyTask implements BaseEntity{

    /**
	 * 重大任务主表
	 */
	private static final long serialVersionUID = 1L;

	private String id;//主键

    private String mainDep;//牵头部门

    private String mainLeadName;//牵头领导

    private String taskContent;//工作肉容
    
    private String performance;//完成情况
    
    private String unfinishedReason;//未完成原因

    private Date finishTime;//完成日期

    private String mainDutyId;//主要责任人ID

    private String mainDutyName;//主要责任人

    private String registrantId;//登记人ID

    private String registrantName;//登记人

    private java.util.Date registrantTime;//登记时间

    private String mainLeadId;//牵头领导ID

    private java.util.Date startTime;//开始时间

    private String status = "WXF";//状态

    private String mainDepId;//牵头部门ID

    private String finishLimit;//完成时限


    private Set<TaskFeedback> taskFeedbacks;
    
    private Set<WeightyDep> weightyDeps;
    
    
    public void setId(String value){
        this.id = value;
    }
    public void setMainDep(String value){
        this.mainDep = value;
    }
    public void setMainLeadName(String value){
        this.mainLeadName = value;
    }
    public void setTaskContent(String value){
        this.taskContent = value;
    }
    public void setPerformance(String value){
        this.performance = value;
    }
    public void setUnfinishedReason(String value){
        this.unfinishedReason = value;
    }
    public void setFinishTime(Date value){
        this.finishTime = value;
    }
    public void setMainDutyId(String value){
        this.mainDutyId = value;
    }
    public void setMainDutyName(String value){
        this.mainDutyName = value;
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
    public void setMainLeadId(String value){
        this.mainLeadId = value;
    }

    public void setStartTime(java.util.Date value){
        this.startTime = value;
    }
    public void setStatus(String value){
        this.status = value;
    }

    public void setMainDepId(String value){
        this.mainDepId = value;
    }
 
   
 
    
    
    
    
    
    
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=false,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="MAIN_DEP")
    public String getMainDep(){
        return this.mainDep;
    }
    @Column(name ="MAIN_LEAD_NAME")
    public String getMainLeadName(){
        return this.mainLeadName;
    }
    @Column(name ="TASK_CONTENT")
    public String getTaskContent(){
        return this.taskContent;
    }
    @Column(name ="PERFORMANCE")
    public String getPerformance(){
        return this.performance;
    }
    @Column(name ="UNFINISHED_REASON")
    public String getUnfinishedReason(){
        return this.unfinishedReason;
    }
    @Column(name ="FINISH_TIME")
    public Date getFinishTime(){
        return this.finishTime;
    }
    @Column(name ="MAIN_DUTY_ID")
    public String getMainDutyId(){
        return this.mainDutyId;
    }
    @Column(name ="MAIN_DUTY_NAME")
    public String getMainDutyName(){
        return this.mainDutyName;
    }
    @Column(name ="REGISTRANT_ID")
    public String getRegistrantId(){
        return this.registrantId;
    }
    @Column(name ="REGISTRANT_NAME")
    public String getRegistrantName(){
        return this.registrantName;
    }
    @Column(name ="REGISTRANT_TIME")
    public java.util.Date getRegistrantTime(){
        return this.registrantTime;
    }
    @Column(name ="MAIN_LEAD_ID")
    public String getMainLeadId(){
        return this.mainLeadId;
    }

    @Column(name ="START_TIME")
    public java.util.Date getStartTime(){
        return this.startTime;
    }
    @Column(name ="STATUS")
    public String getStatus(){
        return this.status;
    }

    @Column(name ="MAiN_DEP_ID")
    public String getMainDepId(){
        return this.mainDepId;
    }


    @Column(name = "FINISH_LIMIT")
    public String getFinishLimit() {
		return finishLimit;
	}
	public void setFinishLimit(String finishLimit) {
		this.finishLimit = finishLimit;
	}
	
	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.LAZY,mappedBy="weightyTask")
	public Set<TaskFeedback> getTaskFeedbacks() {
		return taskFeedbacks;
	}
	public void setTaskFeedbacks(Set<TaskFeedback> taskFeedbacks) {
		this.taskFeedbacks = taskFeedbacks;
	}
	
	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.LAZY,mappedBy="weightyTask")
	public Set<WeightyDep> getWeightyDeps() {
		return weightyDeps;
	}
	public void setWeightyDeps(Set<WeightyDep> weightyDeps) {
		this.weightyDeps = weightyDeps;
	}


} 
