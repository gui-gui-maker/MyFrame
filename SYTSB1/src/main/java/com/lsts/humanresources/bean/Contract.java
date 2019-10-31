package com.lsts.humanresources.bean;

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
@Table(name = "TJY2_RL_CONTRACT")
public class Contract implements BaseEntity{

    private String id;//${columnb.remarks}

    private String contractType;//合同类型

    private java.util.Date contractStartDate;//合同开始日期

    private java.util.Date contractStopDate;//合同结束日期

    private java.math.BigDecimal trialMoney;//试用工资

    private java.util.Date trialStartDate;//试用开始日期

    private java.util.Date trialStopDate;//试用结束日期

    private java.math.BigDecimal positiveMoney;//转正工资

    private String signedMan;//当事人
    private String documentId;
    private String documentName;
    private byte documentDoc[];
    private String fkemployeeid;//基本信息id
    private String termination;//终止

    @Id
   	@GeneratedValue(generator = "system-uuid")
   	@GenericGenerator(name = "system-uuid", strategy = "uuid")
   	public String getId() {
   		return id;
   	}
   	public void setId(String id) {
   		this.id = id;
   	}
    public void setContractType(String value){
        this.contractType = value;
    }
    public void setContractStartDate(java.util.Date value){
        this.contractStartDate = value;
    }
    public void setContractStopDate(java.util.Date value){
        this.contractStopDate = value;
    }
    public void setTrialMoney(java.math.BigDecimal value){
        this.trialMoney = value;
    }
    public void setTrialStartDate(java.util.Date value){
        this.trialStartDate = value;
    }
    public void setTrialStopDate(java.util.Date value){
        this.trialStopDate = value;
    }
    public void setPositiveMoney(java.math.BigDecimal value){
        this.positiveMoney = value;
    }
    public void setSignedMan(String value){
        this.signedMan = value;
    }
    public void setFkemployeeid(String value){
        this.fkemployeeid = value;
    }
    @Column(name ="CONTRACT_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getContractType(){
        return this.contractType;
    }
    @Column(name ="CONTRACT_START_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getContractStartDate(){
        return this.contractStartDate;
    }
    @Column(name ="CONTRACT_STOP_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getContractStopDate(){
        return this.contractStopDate;
    }
    @Column(name ="TRIAL_MONEY",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getTrialMoney(){
        return this.trialMoney;
    }
    @Column(name ="TRIAL_START_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getTrialStartDate(){
        return this.trialStartDate;
    }
    @Column(name ="TRIAL_STOP_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getTrialStopDate(){
        return this.trialStopDate;
    }
    @Column(name ="POSITIVE_MONEY",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getPositiveMoney(){
        return this.positiveMoney;
    }
    @Column(name ="SIGNED_MAN",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getSignedMan(){
        return this.signedMan;
    }
    @Column(name ="FK_EMPLOYEE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFkemployeeid(){
        return this.fkemployeeid;
    }
    @Column(name="TERMINATION")
	public String getTermination() {
		return termination;
	}
	public void setTermination(String termination) {
		this.termination = termination;
	}
	@Column(name="DOCUMENT_ID")
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	@Column(name="DOCUMENT_NAME")
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	@Column(name="DOCUMENT_DOC")
	public byte[] getDocumentDoc() {
		return documentDoc;
	}
	public void setDocumentDoc(byte[] documentDoc) {
		this.documentDoc = documentDoc;
	}
    


} 
