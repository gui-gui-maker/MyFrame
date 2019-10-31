package com.lsts.office.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_BG_FEEDBACK")
@JsonIgnoreProperties(ignoreUnknown=true , value={"weightyTask"})
public class TaskFeedback implements BaseEntity{

    private String id;//主键

    private String feedbackPlan;//反馈进度

    private String status;//反馈状态

    private String feedbackRemark;//反馈备注
    
    private String unfinishedTask;//未完成理由
    
    private WeightyTask weightyTask; //母表

    public void setId(String value){
        this.id = value;
    }
   
    public void setFeedbackPlan(String value){
        this.feedbackPlan = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    public void setFeedbackRemark(String value){
        this.feedbackRemark = value;
    }
   
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=false,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
 
    @Column(name ="FEEDBACK_PLAN",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getFeedbackPlan(){
        return this.feedbackPlan;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=10)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="FEEDBACK_REMARK",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getFeedbackRemark(){
        return this.feedbackRemark;
    }
    
    
    @Column(name = "UNFINISHED_TASK")
    public String getUnfinishedTask() {
		return unfinishedTask;
	}

	public void setUnfinishedTask(String unfinishedTask) {
		this.unfinishedTask = unfinishedTask;
	}

	@ManyToOne
	@JoinColumn(name="PK_FEEDBACK_ID")
	public WeightyTask getWeightyTask() {
		return weightyTask;
	}
	public void setWeightyTask(WeightyTask weightyTask) {
		this.weightyTask = weightyTask;
	}


} 
