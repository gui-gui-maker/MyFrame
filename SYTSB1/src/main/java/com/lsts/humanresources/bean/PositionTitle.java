package com.lsts.humanresources.bean;

import com.khnt.core.crud.bean.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "TJY2_RL_POSITION_TITLE")
public class PositionTitle implements BaseEntity{

    private String id;

    private String positionTitleType;//职称类型

    private java.util.Date positionTitleStartDate;//合同开始日期

    private java.util.Date positionTitleStopDate;//合同结束日期

    private java.math.BigDecimal trialMoney;//试用工资

    private java.util.Date trialStartDate;//试用开始日期

    private java.util.Date trialStopDate;//试用结束日期

    private java.math.BigDecimal positiveMoney;//转正工资

    private String signedMan;//当事人
    private String documentId;
    private String documentName;
    private byte documentDoc[];
    private String fkEmployeeId;//基本信息id
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
    public void setPositionTitleType(String value){
        this.positionTitleType = value;
    }
    public void setPositionTitleStartDate(java.util.Date value){
        this.positionTitleStartDate = value;
    }
    public void setPositionTitleStopDate(java.util.Date value){
        this.positionTitleStopDate = value;
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
    public void setFkEmployeeId(String value){
        this.fkEmployeeId = value;
    }
    @Column(name ="POSITION_TITLE_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getPositionTitleType(){
        return this.positionTitleType;
    }
    @Column(name ="POSITION_TITLE_START_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getPositionTitleStartDate(){
        return this.positionTitleStartDate;
    }
    @Column(name ="POSITION_TITLE_STOP_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getPositionTitleStopDate(){
        return this.positionTitleStopDate;
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
    public String getFkEmployeeId(){
        return this.fkEmployeeId;
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
