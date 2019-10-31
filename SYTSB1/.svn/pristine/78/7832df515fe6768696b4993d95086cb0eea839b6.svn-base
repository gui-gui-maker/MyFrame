package com.lsts.qualitymanage.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_QUALITY_ZSSQ")
public class QualityZssq implements BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;//id

    private String createName;//创建人姓名

    private String createId;//创建人id
    
    private String status; //状态

    private java.util.Date createTime;//创建时间

    private String fileNumber;//文件编号

    private java.util.Date implementationDate;//实施日期

    private String department;//申请部门

    private String departmentId;//部门id

    private String applyName;//申请人

    private String peopleConcernedId;//申请人id

    private String useUnit;//使用单位名称

    private String useUnitId;//使用单位id

    private String total;//数量

    private java.util.Date applyTime;//申请日期

    private java.util.Date returnTime;//拟归还原始资料日期

    private String reportNumber;//报告编号

    private String reportNumberId;//报告编号id

    private String applyOpinion;//申请部门负责人意见

    private java.util.Date applyOpinionTime;//申请部门负责人意见时间

    private String serviceOpinion;//服务部意见

    private java.util.Date serviceOpinionTime;//服务部意见时间

    private String recipientOpinion;//服务接收人

    private java.util.Date recipientOpinionTime;//服务接收时间
    
    private java.util.Date nextTime;//归还时间
    
    private String nextPeople;//归还人
    
    private String nextStatus;//归还状态
    
    private String bmfzr;//部门负责人
    private String fwbfzr;//服务部负责人
    private String fwbjbr;//服务部经办人
    private List<QualityZssqSub> qualityZssqSubs; 

	public void setBmfzr(String bmfzr) {
		this.bmfzr = bmfzr;
	}
	public void setFwbfzr(String fwbfzr) {
		this.fwbfzr = fwbfzr;
	}
	public void setFwbjbr(String fwbjbr) {
		this.fwbjbr = fwbjbr;
	}
	public void setId(String value){
        this.id = value;
    }
    public void setNextPeople(String value){
        this.nextPeople = value;
    }
    public void setCreateName(String value){
        this.createName = value;
    }
    public void setCreateId(String value){
        this.createId = value;
    }
    public void setNextStatus(String value){
        this.nextStatus = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    public void setCreateTime(java.util.Date value){
        this.createTime = value;
    }
    public void setFileNumber(String value){
        this.fileNumber = value;
    }
    public void setImplementationDate(java.util.Date value){
        this.implementationDate = value;
    }
    public void setDepartment(String value){
        this.department = value;
    }
    public void setDepartmentId(String value){
        this.departmentId = value;
    }
    public void setApplyName(String value){
        this.applyName = value;
    }
    public void setPeopleConcernedId(String value){
        this.peopleConcernedId = value;
    }
    public void setUseUnit(String value){
        this.useUnit = value;
    }
    public void setUseUnitId(String value){
        this.useUnitId = value;
    }
    public void setTotal(String value){
        this.total = value;
    }
    public void setApplyTime(java.util.Date value){
        this.applyTime = value;
    }
    public void setReturnTime(java.util.Date value){
        this.returnTime = value;
    }
    public void setNextTime(java.util.Date value){
        this.nextTime = value;
    }
    public void setReportNumber(String value){
        this.reportNumber = value;
    }
    public void setReportNumberId(String value){
        this.reportNumberId = value;
    }
    public void setApplyOpinion(String value){
        this.applyOpinion = value;
    }
    public void setApplyOpinionTime(java.util.Date value){
        this.applyOpinionTime = value;
    }
    public void setServiceOpinion(String value){
        this.serviceOpinion = value;
    }
    public void setServiceOpinionTime(java.util.Date value){
        this.serviceOpinionTime = value;
    }
    public void setRecipientOpinion(String value){
        this.recipientOpinion = value;
    }
    public void setRecipientOpinionTime(java.util.Date value){
        this.recipientOpinionTime = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="CREATE_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getCreateName(){
        return this.createName;
    }
    @Column(name ="CREATE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getCreateId(){
        return this.createId;
    }
    @Column(name ="NEXT_STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getNextStatus(){
        return this.nextStatus;
    }
    @Column(name ="CREATE_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getCreateTime(){
        return this.createTime;
    }
    @Column(name ="FILE_NUMBER",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getFileNumber(){
        return this.fileNumber;
    }
    @Column(name ="IMPLEMENTATION_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getImplementationDate(){
        return this.implementationDate;
    }
    @Column(name ="DEPARTMENT",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getDepartment(){
        return this.department;
    }
    @Column(name ="DEPARTMENT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getDepartmentId(){
        return this.departmentId;
    }
    @Column(name ="NEXT_PEOPLE",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getNextPeople(){
        return this.nextPeople;
    }
    @Column(name ="APPLY_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getApplyName(){
        return this.applyName;
    }
    @Column(name ="PEOPLE_CONCERNED_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getPeopleConcernedId(){
        return this.peopleConcernedId;
    }
    @Column(name ="USE_UNIT",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getUseUnit(){
        return this.useUnit;
    }
    @Column(name ="USE_UNIT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getUseUnitId(){
        return this.useUnitId;
    }
    @Column(name ="TOTAL",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getTotal(){
        return this.total;
    }
    @Column(name ="APPLY_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getApplyTime(){
        return this.applyTime;
    }
    @Column(name ="RETURN_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getReturnTime(){
        return this.returnTime;
    }
    @Column(name ="REPORT_NUMBER",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getReportNumber(){
        return this.reportNumber;
    }
    @Column(name ="REPORT_NUMBER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getReportNumberId(){
        return this.reportNumberId;
    }
    @Column(name ="APPLY_OPINION",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getApplyOpinion(){
        return this.applyOpinion;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="APPLY_OPINION_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getApplyOpinionTime(){
        return this.applyOpinionTime;
    }
    @Column(name ="SERVICE_OPINION",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getServiceOpinion(){
        return this.serviceOpinion;
    }
    @Column(name ="SERVICE_OPINION_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getServiceOpinionTime(){
        return this.serviceOpinionTime;
    }
    @Column(name ="NEXT_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getNextTime(){
        return this.nextTime;
    }
    @Column(name ="RECIPIENT_OPINION",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getRecipientOpinion(){
        return this.recipientOpinion;
    }
    @Column(name ="RECIPIENT_OPINION_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getRecipientOpinionTime(){
        return this.recipientOpinionTime;
    }
    @Column(name ="BMFZR",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getBmfzr() {
		return bmfzr;
	}
    @Column(name ="FWBFZR",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getFwbfzr() {
    	return fwbfzr;
    }
    @Column(name ="FWBJBR",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getFwbjbr() {
    	return fwbjbr;
    }
    @Transient
	public List<QualityZssqSub> getQualityZssqSubs() {
		return qualityZssqSubs;
	}
	
	public void setBaseEquipment2s(List<QualityZssqSub> value) {
		this.qualityZssqSubs = value;
	}

} 
