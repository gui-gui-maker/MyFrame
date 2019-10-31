package com.lsts.archives.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_ARCHIVES_BOX")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArchivesBox implements BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;//${columnb.remarks}

    private String registrant;//创建人

    private java.util.Date registrantTime;//创建人时间

    private String registrantId;//创建人id

    private String reportNumber;//档案编号
    private String reportNumberId;//档案id
    
    private String reportNumber2;//缺少的档案编号
    private String reportNumberId2;//缺少的档案id

    private String reportCabinetId;//档案柜id

    private Integer reportNum;//档案数量

    private String managerId;//管理人id

    private String managerName;//管理人姓名

    private String status;//档案盒状态
    
    private String prefix;//前缀
    private Integer years;
    private Integer startNumber;
    private Integer endNumber;
    
    
    private String identifier;//档案盒档案开始编号
    private String identifier2;//档案盒档案结束编号
    
    private String archivesBoxId;//档案盒编号
    
    private Integer total_nums;
    

   
	public void setArchivesBoxId(String archivesBoxId) {
		this.archivesBoxId = archivesBoxId;
	}
	public void setId(String value){
        this.id = value;
    }
    public void setRegistrant(String value){
        this.registrant = value;
    }
    public void setRegistrantTime(java.util.Date value){
        this.registrantTime = value;
    }
    public void setRegistrantId(String value){
        this.registrantId = value;
    }
    public void setReportNumber(String value){
        this.reportNumber = value;
    }
    public void setReportNumberId(String value){
        this.reportNumberId = value;
    }
    public void setReportNumber2(String value){
        this.reportNumber2 = value;
    }
    public void setReportNumberId2(String value){
        this.reportNumberId2 = value;
    }
    public void setReportCabinetId(String value){
        this.reportCabinetId = value;
    }

    public void setManagerId(String value){
        this.managerId = value;
    }
    public void setManagerName(String value){
        this.managerName = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    public void setIdentifier(String value){
        this.identifier = value;
    }
    public void setIdentifier2(String value){
        this.identifier2 = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="REGISTRANT",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getRegistrant(){
        return this.registrant;
    }
    @Column(name ="REGISTRANT_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getRegistrantTime(){
        return this.registrantTime;
    }
    @Column(name ="REGISTRANT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getRegistrantId(){
        return this.registrantId;
    }
    @Column(name ="REPORT_NUMBER",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getReportNumber(){
        return this.reportNumber;
    }
    @Column(name ="REPORT_NUMBER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getReportNumberId(){
        return this.reportNumberId;
    }
    @Column(name ="REPORT_NUMBER2",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getReportNumber2(){
        return this.reportNumber2;
    }
    @Column(name ="REPORT_NUMBER_ID2",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getReportNumberId2(){
        return this.reportNumberId2;
    }
    @Column(name ="REPORT_CABINET_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getReportCabinetId(){
        return this.reportCabinetId;
    }
    
    @Column(name ="MANAGER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getManagerId(){
        return this.managerId;
    }
    @Column(name ="MANAGER_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getManagerName(){
        return this.managerName;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="IDENTIFIER",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getIdentifier(){
        return this.identifier;
    }
    @Column(name ="IDENTIFIER2",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getIdentifier2(){
        return this.identifier2;
    }
    @Column(name ="ARCHIVES_BOX_ID",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getArchivesBoxId() {
		return archivesBoxId;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public Integer getYears() {
		return years;
	}
	public void setYears(Integer years) {
		this.years = years;
	}
	public Integer getStartNumber() {
		return startNumber;
	}
	public void setStartNumber(Integer startNumber) {
		this.startNumber = startNumber;
	}
	public Integer getEndNumber() {
		return endNumber;
	}
	public void setEndNumber(Integer endNumber) {
		this.endNumber = endNumber;
	}
	public Integer getReportNum() {
		return reportNum;
	}
	public void setReportNum(Integer reportNum) {
		this.reportNum = reportNum;
	}
	public Integer getTotal_nums() {
		return total_nums;
	}
	public void setTotal_nums(Integer total_nums) {
		this.total_nums = total_nums;
	}
	
	
    
} 
