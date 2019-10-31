package com.lsts.equipment2.bean;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_PPE_LOAN")
@JsonIgnoreProperties(ignoreUnknown=true)
public class PpeLoan implements BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;//${columnb.remarks}

    private String identifier;//登记表编号

    private String departmentId;//借用部门ID

    private String departmentName;//借用部门

    private String loanerId;//借用人ID

    private String loanerName;//借用人

    private java.util.Date loanDate;//借用日期

    private String purpose;//借用用途

    private String loanSate;//借出时状况

    private String handoverId;//交接人ID

    private String handover;//交接人

    private String backerId;//归还人ID

    private String backer;//归还人

    private java.util.Date backDate;//归还日期

    private String backSate;//归还时状况

    private String receiverId;//接收人ID

    private String receiver;//接收人

    private String type;//类型

    private String status;//状态

    private String createId;//${columnb.remarks}

    private String createBy;//${columnb.remarks}

    private java.util.Date createDate;//${columnb.remarks}

    private String lastModifyId;//${columnb.remarks}

    private String lastModifyBy;//${columnb.remarks}

    private java.util.Date lastModifyDate;//${columnb.remarks}
    
    private List<PpeLoanSub> ppeLoanSubs;//申请设备列表

    private String fkPpeId;//资产外键ID

    private String ppeSelfNo;//资产自编号
    
    public void setId(String value){
        this.id = value;
    }
    public void setIdentifier(String value){
        this.identifier = value;
    }
    public void setDepartmentId(String value){
        this.departmentId = value;
    }
    public void setDepartmentName(String value){
        this.departmentName = value;
    }
    public void setLoanerId(String value){
        this.loanerId = value;
    }
    public void setLoanerName(String value){
        this.loanerName = value;
    }
    public void setLoanDate(java.util.Date value){
        this.loanDate = value;
    }
    public void setPurpose(String value){
        this.purpose = value;
    }
    public void setLoanSate(String value){
        this.loanSate = value;
    }
    public void setHandoverId(String value){
        this.handoverId = value;
    }
    public void setHandover(String value){
        this.handover = value;
    }
    public void setBackerId(String value){
        this.backerId = value;
    }
    public void setBacker(String value){
        this.backer = value;
    }
    public void setBackDate(java.util.Date value){
        this.backDate = value;
    }
    public void setBackSate(String value){
        this.backSate = value;
    }
    public void setReceiverId(String value){
        this.receiverId = value;
    }
    public void setReceiver(String value){
        this.receiver = value;
    }
    public void setType(String value){
        this.type = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    public void setCreateId(String value){
        this.createId = value;
    }
    public void setCreateBy(String value){
        this.createBy = value;
    }
    public void setCreateDate(java.util.Date value){
        this.createDate = value;
    }
    public void setLastModifyId(String value){
        this.lastModifyId = value;
    }
    public void setLastModifyBy(String value){
        this.lastModifyBy = value;
    }
    public void setLastModifyDate(java.util.Date value){
        this.lastModifyDate = value;
    }
    public void setFkPpeId(String value){
        this.fkPpeId = value;
    }
    public void setPpeSelfNo(String value){
        this.ppeSelfNo = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="IDENTIFIER",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getIdentifier(){
        return this.identifier;
    }
    @Column(name ="DEPARTMENT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getDepartmentId(){
        return this.departmentId;
    }
    @Column(name ="DEPARTMENT_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=128)
    public String getDepartmentName(){
        return this.departmentName;
    }
    @Column(name ="LOANER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getLoanerId(){
        return this.loanerId;
    }
    @Column(name ="LOANER_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getLoanerName(){
        return this.loanerName;
    }
    @Column(name ="LOAN_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getLoanDate(){
        return this.loanDate;
    }
    @Column(name ="PURPOSE",unique=false,nullable=true,insertable=true,updatable=true,length=3000)
    public String getPurpose(){
        return this.purpose;
    }
    @Column(name ="LOAN_SATE",unique=false,nullable=true,insertable=true,updatable=true,length=3000)
    public String getLoanSate(){
        return this.loanSate;
    }
    @Column(name ="HANDOVER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getHandoverId(){
        return this.handoverId;
    }
    @Column(name ="HANDOVER",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getHandover(){
        return this.handover;
    }
    @Column(name ="BACKER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getBackerId(){
        return this.backerId;
    }
    @Column(name ="BACKER",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getBacker(){
        return this.backer;
    }
    @Column(name ="BACK_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getBackDate(){
        return this.backDate;
    }
    @Column(name ="BACK_SATE",unique=false,nullable=true,insertable=true,updatable=true,length=3000)
    public String getBackSate(){
        return this.backSate;
    }
    @Column(name ="RECEIVER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getReceiverId(){
        return this.receiverId;
    }
    @Column(name ="RECEIVER",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getReceiver(){
        return this.receiver;
    }
    @Column(name ="TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getType(){
        return this.type;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="CREATE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getCreateId(){
        return this.createId;
    }
    @Column(name ="CREATE_BY",unique=false,nullable=true,insertable=true,updatable=true,length=128)
    public String getCreateBy(){
        return this.createBy;
    }
    @Column(name ="CREATE_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getCreateDate(){
        return this.createDate;
    }
    @Column(name ="LAST_MODIFY_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getLastModifyId(){
        return this.lastModifyId;
    }
    @Column(name ="LAST_MODIFY_BY",unique=false,nullable=true,insertable=true,updatable=true,length=128)
    public String getLastModifyBy(){
        return this.lastModifyBy;
    }
    @Column(name ="LAST_MODIFY_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getLastModifyDate(){
        return this.lastModifyDate;
    }
    @Column(name ="FK_PPE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=4000)
    public String getFkPpeId(){
        return this.fkPpeId;
    }
    @Column(name ="PPE_SELF_NO",unique=false,nullable=true,insertable=true,updatable=true,length=4000)
    public String getPpeSelfNo(){
        return this.ppeSelfNo;
    }
    
    @Transient
	public List<PpeLoanSub> getPpeLoanSubs() {
		return this.ppeLoanSubs;
	}
	public void setPpeLoanSubs(List<PpeLoanSub> value) {
		this.ppeLoanSubs = value;
	}


} 
