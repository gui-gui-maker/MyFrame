package com.lsts.qualitymanage.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_QUALITY_TASKBOOK")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Taskbook implements BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;//${columnb.remarks}

    private String testNature;//检验性质（法定，委托）

    private String identifier;//编号

    private String registrant;//登记人

    private String registrantId;//登记人id

    private java.util.Date registrantDate;//登记时间

    private String status;//状态

    private String reportNumber;//报告编号

    private String userName;//用户名称

    private String equipmentName;//设备名称

    private String equipmentNumber;//设备编号

    private String equipmentLocation;//设备所在地址

    private String content;//检验/委托内容审批表中的（设备品种）（设备类别）

    private String zlfs;//资料份数（台数）

    private java.util.Date bjwtsj;//报检/委托时间

    private String tel;//联系电话

    private String linkman;//联系人

    private String department;//承接部门（申请部门）

    private String departmentId;//部门id

    private String contractNumber;//合同编号
    
    private String rwdId;//任务单id
    private String rwdSn;//任务单编号
    private String rwdMoney;//任务单金额

    private String contractMoney;//合同金额

    private java.util.Date signTime;//签订时间

    private String remarks;//备注

    private String ybfwbjbr;//业务服务部经办人

    private java.util.Date jbsj;//经办时间
    
    private String ywid;//业务id
    
    

    @Column(name = "RWD_ID")
	public String getRwdId() {
		return rwdId;
	}
	public void setRwdId(String rwdId) {
		this.rwdId = rwdId;
	}
	@Column(name = "RWD_SN")
	public String getRwdSn() {
		return rwdSn;
	}
	public void setRwdSn(String rwdSn) {
		this.rwdSn = rwdSn;
	}
	@Column(name = "RWD_MONEY")
	public String getRwdMoney() {
		return rwdMoney;
	}
	public void setRwdMoney(String rwdMoney) {
		this.rwdMoney = rwdMoney;
	}
	public void setYwid(String ywid) {
		this.ywid = ywid;
	}
	public void setId(String value){
        this.id = value;
    }
    public void setTestNature(String value){
        this.testNature = value;
    }
    public void setIdentifier(String value){
        this.identifier = value;
    }
    public void setRegistrant(String value){
        this.registrant = value;
    }
    public void setRegistrantId(String value){
        this.registrantId = value;
    }
    public void setRegistrantDate(java.util.Date value){
        this.registrantDate = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    public void setReportNumber(String value){
        this.reportNumber = value;
    }
    public void setUserName(String value){
        this.userName = value;
    }
    public void setEquipmentName(String value){
        this.equipmentName = value;
    }
    public void setEquipmentNumber(String value){
        this.equipmentNumber = value;
    }
    public void setEquipmentLocation(String value){
        this.equipmentLocation = value;
    }
    public void setContent(String value){
        this.content = value;
    }
    public void setZlfs(String value){
        this.zlfs = value;
    }
    public void setBjwtsj(java.util.Date value){
        this.bjwtsj = value;
    }
    public void setTel(String value){
        this.tel = value;
    }
    public void setLinkman(String value){
        this.linkman = value;
    }
    public void setDepartment(String value){
        this.department = value;
    }
    public void setDepartmentId(String value){
        this.departmentId = value;
    }
    public void setContractNumber(String value){
        this.contractNumber = value;
    }
    public void setContractMoney(String value){
        this.contractMoney = value;
    }
    public void setSignTime(java.util.Date value){
        this.signTime = value;
    }
    public void setRemarks(String value){
        this.remarks = value;
    }
    public void setYbfwbjbr(String value){
        this.ybfwbjbr = value;
    }
    public void setJbsj(java.util.Date value){
        this.jbsj = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="TEST_NATURE",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getTestNature(){
        return this.testNature;
    }
    @Column(name ="IDENTIFIER",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getIdentifier(){
        return this.identifier;
    }
    @Column(name ="REGISTRANT",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getRegistrant(){
        return this.registrant;
    }
    @Column(name ="REGISTRANT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getRegistrantId(){
        return this.registrantId;
    }
    @Column(name ="REGISTRANT_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getRegistrantDate(){
        return this.registrantDate;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="REPORT_NUMBER",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getReportNumber(){
        return this.reportNumber;
    }
    @Column(name ="USER_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getUserName(){
        return this.userName;
    }
    @Column(name ="EQUIPMENT_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getEquipmentName(){
        return this.equipmentName;
    }
    @Column(name ="EQUIPMENT_NUMBER",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getEquipmentNumber(){
        return this.equipmentNumber;
    }
    @Column(name ="EQUIPMENT_LOCATION",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getEquipmentLocation(){
        return this.equipmentLocation;
    }
    @Column(name ="CONTENT",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getContent(){
        return this.content;
    }
    @Column(name ="ZLFS",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getZlfs(){
        return this.zlfs;
    }
    @Column(name ="BJWTSJ",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getBjwtsj(){
        return this.bjwtsj;
    }
    @Column(name ="TEL",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getTel(){
        return this.tel;
    }
    @Column(name ="LINKMAN",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getLinkman(){
        return this.linkman;
    }
    @Column(name ="DEPARTMENT",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getDepartment(){
        return this.department;
    }
    @Column(name ="DEPARTMENT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getDepartmentId(){
        return this.departmentId;
    }
    @Column(name ="CONTRACT_NUMBER",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getContractNumber(){
        return this.contractNumber;
    }
    @Column(name ="CONTRACT_MONEY",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getContractMoney(){
        return this.contractMoney;
    }
    @Column(name ="SIGN_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getSignTime(){
        return this.signTime;
    }
    @Column(name ="REMARKS",unique=false,nullable=true,insertable=true,updatable=true,length=4000)
    public String getRemarks(){
        return this.remarks;
    }
    @Column(name ="YBFWBJBR",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getYbfwbjbr(){
        return this.ybfwbjbr;
    }
    @Column(name ="JBSJ",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getJbsj(){
        return this.jbsj;
    }
    @Column(name ="YWID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getYwid() {
		return ywid;
	}

} 
