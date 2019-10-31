package com.lsts.finance.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_CW_BANK_FEFUND")
public class CwBankFefund implements BaseEntity{

    private String id;//主键

    private String fkBankDetailId;//往来帐ID

    private String fefundId;//退款人ID

    private String fefundName;//退款人

    private java.util.Date fefundDate;//退款时间

    private String unitName;//单位名称

    private java.math.BigDecimal fefundMoney;//退款金额

    private String fefundReason;//退款理由

    private String operatorId;//操作人ID

    private String operatorName;//操作人
    
    private Date operatorTime; //操作时间
    
    private java.math.BigDecimal sumAmount; //总金额
    
    private java.math.BigDecimal agoAmount; //退款前金额
    
    private java.math.BigDecimal rearAmount;//退款后金额

    private String checkOpId;//审核人ID
    
    private String checkOpName;//审核人
    
    private Date checkOpTime;//审核时间
    
    private String confirmOpId;//确认人ID
    
    private String confirmOpName;//确认人
    
    private Date confirmOpTime;//确认时间
    
    private String dataStatus;//数据状态
    
    public void setId(String value){
        this.id = value;
    }
   
    public void setFefundId(String value){
        this.fefundId = value;
    }
    public void setFkBankDetailId(String value) {
		this.fkBankDetailId = value;
	}
    public void setFefundName(String value){
        this.fefundName = value;
    }
    public void setFefundDate(java.util.Date value){
        this.fefundDate = value;
    }
    public void setUnitName(String value){
        this.unitName = value;
    }
    public void setFefundMoney(java.math.BigDecimal value){
        this.fefundMoney = value;
    }
    public void setFefundReason(String value){
        this.fefundReason = value;
    }
    public void setOperatorId(String value){
        this.operatorId = value;
    }
    public void setOperatorName(String value){
        this.operatorName = value;
    }
    //添加审核人、确认人和数据状态
    public void setCheckOpId(String value){
        this.checkOpId = value;
    }
    public void setCheckOpName(String value){
        this.checkOpName = value;
    }
    public void setCheckOpTime(java.util.Date value){
        this.checkOpTime = value;
    }
    public void setConfirmOpId(String value){
        this.confirmOpId = value;
    }
    public void setConfirmOpName(String value){
        this.confirmOpName = value;
    }
    public void setConfirmOpTime(java.util.Date value){
        this.confirmOpTime = value;
    }
    public void setDataStatus(String value){
        this.dataStatus = value;
    }
    
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
   
    @Column(name ="FEFUND_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFefundId(){
        return this.fefundId;
    }
    @Column(name ="FEFUND_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getFefundName(){
        return this.fefundName;
    }
    @Column(name ="FEFUND_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getFefundDate(){
        return this.fefundDate;
    }
    @Column(name ="UNIT_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getUnitName(){
        return this.unitName;
    }
    @Column(name ="FEFUND_MONEY",unique=false,nullable=true,insertable=true,updatable=true,length=9)
    public java.math.BigDecimal getFefundMoney(){
        return this.fefundMoney;
    }
    @Column(name ="FEFUND_REASON",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getFefundReason(){
        return this.fefundReason;
    }
    @Column(name ="OPERATOR_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getOperatorId(){
        return this.operatorId;
    }
    @Column(name ="OPERATOR_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getOperatorName(){
        return this.operatorName;
    }
    @Column(name ="FK_BANK_DETAIL_ID")
	public String getFkBankDetailId() {
		return fkBankDetailId;
	}
    
    @Column(name = "SUM_AMOUNT")
	public java.math.BigDecimal getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(java.math.BigDecimal sumAmount) {
		this.sumAmount = sumAmount;
	}
	@Column(name = "AGO_AMOUNT")
	public java.math.BigDecimal getAgoAmount() {
		return agoAmount;
	}

	public void setAgoAmount(java.math.BigDecimal agoAmount) {
		this.agoAmount = agoAmount;
	}
	@Column(name = "REAR_AMOUNT")
	public java.math.BigDecimal getRearAmount() {
		return rearAmount;
	}

	public void setRearAmount(java.math.BigDecimal rearAmount) {
		this.rearAmount = rearAmount;
	}
	@Column(name = "OPERATOR_TIME")
	public Date getOperatorTime() {
		return operatorTime;
	}

	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}

	@Column(name ="CHECK_OP_ID")
    public String getCheckOpId(){
        return this.checkOpId;
    }
    @Column(name ="CHECK_OP_NAME")
    public String getCheckOpName(){
        return this.checkOpName;
    }
    @Column(name ="CHECK_OP_TIME")
    public Date getCheckOpTime(){
        return this.checkOpTime;
    }
    @Column(name ="CONFIRM_OP_ID")
    public String getConfirmOpId(){
        return this.confirmOpId;
    }
    @Column(name ="CONFIRM_OP_NAME")
    public String getConfirmOpName(){
        return this.confirmOpName;
    }
    @Column(name ="CONFIRM_OP_TIME")
    public Date getConfirmOpTime(){
        return this.confirmOpTime;
    }
    @Column(name ="DATA_STATUS")
    public String getDataStatus(){
        return this.dataStatus;
    }
} 
