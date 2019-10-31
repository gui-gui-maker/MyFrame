package com.lsts.equipment2.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_EQUIPMENT_LOAN")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EquipmentLoan implements BaseEntity{

    private String id;//主键
    
    private String loanNo;//编号

    private String loanId;//借用/领用人ID

    private String loanName;//借用/领用人

    private String depId;//部门ID

    private String depName;//部门名称

    private java.util.Date loanTime;//借用/领用时间

    private java.util.Date repayTime;//归还时间

    private String reason;//借用/领用事由

    private java.math.BigDecimal amount;//数量

    private String status;//状态

    private String registerId;//登记人ID

    private String registerName;//登记人

    private java.util.Date registerTime;//登记时间
    
    private String loanType; //借用/领用类型
    
    private String auditStatus; //审核状态
    
    private List<EquipmentCentre> equipmentCentres; //中间表
    
    private List<Equipment> baseEquipment2s; //设备表
        
    private List<EquipmentAudit> equipmentAudits; //审核意见表
    
    public void setId(String value){
        this.id = value;
    }
    public void setLoanNo(String value){
        this.loanNo = value;
    }
    public void setLoanId(String value){
        this.loanId = value;
    }
    public void setLoanName(String value){
        this.loanName = value;
    }
  
    public void setDepId(String value){
        this.depId = value;
    }
    public void setDepName(String value){
        this.depName = value;
    }
    public void setLoanTime(java.util.Date value){
        this.loanTime = value;
    }
    public void setRepayTime(java.util.Date value){
        this.repayTime = value;
    }
    public void setReason(String value){
        this.reason = value;
    }
    public void setAmount(java.math.BigDecimal value){
        this.amount = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    public void setRegisterId(String value){
        this.registerId = value;
    }
    public void setRegisterName(String value){
        this.registerName = value;
    }
    public void setRegisterTime(java.util.Date value){
        this.registerTime = value;
    }
    public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
    
    
    @Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="LOAN_NO",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getLoanNo(){
        return this.loanNo;
    }
    @Column(name ="LOAN_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getLoanId(){
        return this.loanId;
    }
    @Column(name ="LOAN_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getLoanName(){
        return this.loanName;
    }
    @Column(name ="DEP_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getDepId(){
        return this.depId;
    }
    @Column(name ="DEP_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getDepName(){
        return this.depName;
    }
    @Column(name ="LOAN_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getLoanTime(){
        return this.loanTime;
    }
    @Column(name ="REPAY_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getRepayTime(){
        return this.repayTime;
    }
    @Column(name ="REASON",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getReason(){
        return this.reason;
    }
    @Column(name ="AMOUNT",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getAmount(){
        return this.amount;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=10)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="REGISTER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getRegisterId(){
        return this.registerId;
    }
    @Column(name ="REGISTER_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getRegisterName(){
        return this.registerName;
    }
    @Column(name ="REGISTER_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getRegisterTime(){
        return this.registerTime;
    }
    @Column(name="LOAN_TYPE")
    public String getLoanType() {
		return loanType;
	}
	
    
    
    @Column(name = "AUDIT_STATUS")
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	
	
	//@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.LAZY,mappedBy="equipmentLoan")
	@Transient
	public List<Equipment> getBaseEquipment2s() {
		return baseEquipment2s;
	}
	
	public void setBaseEquipment2s(List<Equipment> baseEquipment2s) {
		this.baseEquipment2s = baseEquipment2s;
	}
	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.LAZY,mappedBy="equipmentLoan")
	public List<EquipmentAudit> getEquipmentAudits() {
		return equipmentAudits;
	}
	
	public void setEquipmentAudits(List<EquipmentAudit> equipmentAudits) {
		this.equipmentAudits = equipmentAudits;
	}
	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.LAZY,mappedBy="equipmentLoan")
	public List<EquipmentCentre> getEquipmentCentres() {
		return equipmentCentres;
	}
	public void setEquipmentCentres(List<EquipmentCentre> equipmentCentres) {
		this.equipmentCentres = equipmentCentres;
	}

	

	

    
    
    
    
} 
