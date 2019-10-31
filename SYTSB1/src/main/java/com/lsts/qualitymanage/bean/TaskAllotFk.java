package com.lsts.qualitymanage.bean;

import java.util.Date;

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
@Table(name = "TJY2_QUALTTY_ALLOT_FK")
@JsonIgnoreProperties(ignoreUnknown=true,value={"taskAllot"})
public class TaskAllotFk implements BaseEntity{

    private String id;//主键

    private String status;//状态

    private String feedback;//反馈

    private String unfinished;//未完成
    
    private Date delay; //延期 

    private TaskAllot taskAllot;
    
    public void setId(String value){
        this.id = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    public void setFeedback(String value){
        this.feedback = value;
    }
    public void setUnfinished(String value){
        this.unfinished = value;
    }
    
    
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=10)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="FEEDBACK",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getFeedback(){
        return this.feedback;
    }
    @Column(name ="UNFINISHED",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getUnfinished(){
        return this.unfinished;
    }
    @Column(name="DELAY")
    public Date getDelay() {
		return delay;
	}
	public void setDelay(Date delay) {
		this.delay = delay;
	}
	
	@ManyToOne
   	@JoinColumn(name="PK_FEEDBACK_ID")
	public TaskAllot getTaskAllot() {
		return taskAllot;
	}
	public void setTaskAllot(TaskAllot taskAllot) {
		this.taskAllot = taskAllot;
	}
   


} 
