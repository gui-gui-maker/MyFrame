package com.lsts.equipment2.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_EQUIP_BUY_YIJIAN")
public class BuyYijian implements BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;//意见表ID

    private String buyApplyId;//申请表ID

    private String auditOpinion;//审核意见

    private java.util.Date auditTime;//审核时间

    private String auditStep;//审核步骤

    private String auditResult;//审核结果

    private String auditManId;//审核人id

    private String auditManName;//审核人

    private String cxYy;//撤销原因

    public void setId(String value){
        this.id = value;
    }
    public void setBuyApplyId(String value){
        this.buyApplyId = value;
    }
    public void setAuditOpinion(String value){
        this.auditOpinion = value;
    }
    public void setAuditTime(java.util.Date value){
        this.auditTime = value;
    }
    public void setAuditStep(String value){
        this.auditStep = value;
    }
    public void setAuditResult(String value){
        this.auditResult = value;
    }
    public void setAuditManId(String value){
        this.auditManId = value;
    }
    public void setAuditManName(String value){
        this.auditManName = value;
    }
    public void setCxYy(String value){
        this.cxYy = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="BUY_APPLY_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getBuyApplyId(){
        return this.buyApplyId;
    }
    @Column(name ="AUDIT_OPINION",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getAuditOpinion(){
        return this.auditOpinion;
    }
    @Column(name ="AUDIT_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getAuditTime(){
        return this.auditTime;
    }
    @Column(name ="AUDIT_STEP",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getAuditStep(){
        return this.auditStep;
    }
    @Column(name ="AUDIT_RESULT",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getAuditResult(){
        return this.auditResult;
    }
    @Column(name ="AUDIT_MAN_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getAuditManId(){
        return this.auditManId;
    }
    @Column(name ="AUDIT_MAN_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getAuditManName(){
        return this.auditManName;
    }
    @Column(name ="CX_YY",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getCxYy(){
        return this.cxYy;
    }


} 
