package com.lsts.office.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_OFFCIE_MEET_YIJINA")
public class MeetUpdateYijina implements BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;//id

    private String fileId;//申请表id

    private String auditOpinion;//审核意见

    private String auditMan;//审核人

    private java.util.Date auditTime;//审核时间

    private String auditManId;//审核人id

    private String auditStep;//审核步骤

    private String auditResult;//审核结果
    
    private String cxyy;//撤销原因
    
    
    public void setCxyy(String cxyy) {
		this.cxyy = cxyy;
	}
	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}
	public void setId(String value){
        this.id = value;
    }
    public void setFileId(String value){
        this.fileId = value;
    }
    public void setAuditOpinion(String value){
        this.auditOpinion = value;
    }
    public void setAuditMan(String value){
        this.auditMan = value;
    }
    public void setAuditTime(java.util.Date value){
        this.auditTime = value;
    }
    public void setAuditManId(String value){
        this.auditManId = value;
    }
    public void setAuditStep(String value){
        this.auditStep = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="FILE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFileId(){
        return this.fileId;
    }
    @Column(name ="AUDIT_OPINION",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getAuditOpinion(){
        return this.auditOpinion;
    }
    @Column(name ="AUDIT_MAN",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getAuditMan(){
        return this.auditMan;
    }
    @Column(name ="AUDIT_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getAuditTime(){
        return this.auditTime;
    }
    @Column(name ="AUDIT_MAN_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getAuditManId(){
        return this.auditManId;
    }
    @Column(name ="AUDIT_STEP",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getAuditStep(){
        return this.auditStep;
    }
    @Column(name ="AUDIT_RESULT",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getAuditResult() {
		return auditResult;
	}
    @Column(name ="CX_YY",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getCxyy() {
		return cxyy;
	}
} 
