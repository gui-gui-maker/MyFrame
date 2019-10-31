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
@Table(name = "TJY2_YWHBSGZ_FK")
@JsonIgnoreProperties(ignoreUnknown=true,value={"ywhbsgz"})
public class YwhbsgzFk implements BaseEntity{
	
	private Ywhbsgz ywhbsgz;
	
	private String notCompleteReason;//未完成原因

    private String id;//${columnb.remarks}
    
    private String feedbackPlan;//已完成原因

    private String status1;//${columnb.remarks}

    private String feedbackRemark;//${columnb.remarks}

    public void setId(String value){
        this.id = value;
    }
    
    public void setFeedbackPlan(String value){
        this.feedbackPlan = value;
    }
    public void setStatus1(String value){
        this.status1 = value;
    }

    public void setFeedbackRemark(String value){
        this.feedbackRemark = value;
    }
    public void setNotCompleteReason(String value){
        this.notCompleteReason = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="FEEDBACK_PLAN",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getFeedbackPlan(){
        return this.feedbackPlan;
    }
    @Column(name ="STATUS1",unique=false,nullable=true,insertable=true,updatable=true,length=10)
    public String getStatus1(){
        return this.status1;
    }
    @Column(name ="NOT_COMPLETE_REASON",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getNotCompleteReason(){
        return this.notCompleteReason;
    }
    @Column(name ="FEEDBACK_REMARK",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getFeedbackRemark(){
        return this.feedbackRemark;
    }

    
    @ManyToOne
   	@JoinColumn(name="PK_RWFK_ID",nullable=false)
   	public Ywhbsgz getYwhbsgz() {
   		return ywhbsgz;
   	}

    public void setYwhbsgz(Ywhbsgz ywhbsgz) {
		this.ywhbsgz = ywhbsgz;
	}

} 
