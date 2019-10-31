package com.lsts.qualitymanage.bean;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_QUALTTY_ALLOT")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskAllot implements BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;//主键

    private String taskSn;//任务书编号
    
    private String dutyDepId; //责任部门ID

    private String dutyDep;//责任部门

    private String dutyName;//责任人

    private String itemName;//项目名称

    private Date itemDate;//期望完成时间

    private String itemContent;//项目内容

    private String itemRequire;//项目要求

    private String remark;//备注

    private String principalName;//签收人

    private String dutyId;//责任人ID

    private String registerId;//登记人ID

    private String registerName;//登记人

    private Date registerDate;//登记时间

    private String status;//状态

    private Date itemTime;//完成时间

    private Date principalDate;//签收时间
    
    private Date checkTime;//确认完成时间

    private Date sendTime; //下发时间
    
    private Date delay;  //延期
    
    
    private Set<TaskAllotFk> taskAllotFks;
   
    
    public void setId(String value){
        this.id = value;
    }
    public void setTaskSn(String value){
        this.taskSn = value;
    }
    public void setDutyDep(String value){
        this.dutyDep = value;
    }
    public void setDutyName(String value){
        this.dutyName = value;
    }
    public void setItemName(String value){
        this.itemName = value;
    }
    public void setItemDate(java.util.Date value){
        this.itemDate = value;
    }
    public void setItemContent(String value){
        this.itemContent = value;
    }
    public void setItemRequire(String value){
        this.itemRequire = value;
    }
    public void setRemark(String value){
        this.remark = value;
    }
    public void setPrincipalName(String value){
        this.principalName = value;
    }
    public void setDutyId(String value){
        this.dutyId = value;
    }
    public void setRegisterId(String value){
        this.registerId = value;
    }
    public void setRegisterName(String value){
        this.registerName = value;
    }
    public void setRegisterDate(java.util.Date value){
        this.registerDate = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    public void setItemTime(java.util.Date value){
        this.itemTime = value;
    }
    public void setPrincipalDate(java.util.Date value){
        this.principalDate = value;
    }
    public void setCheckTime(java.util.Date value){
        this.checkTime = value;
    }
    public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
   
	
    
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="TASK_SN")
    public String getTaskSn(){
        return this.taskSn;
    }
    @Column(name ="DUTY_DEP")
    public String getDutyDep(){
        return this.dutyDep;
    }
    @Column(name ="DUTY_NAME")
    public String getDutyName(){
        return this.dutyName;
    }
    @Column(name ="ITEM_NAME")
    public String getItemName(){
        return this.itemName;
    }
    @Column(name ="ITEM_DATE")
    public java.util.Date getItemDate(){
        return this.itemDate;
    }
    @Column(name ="ITEM_CONTENT")
    public String getItemContent(){
        return this.itemContent;
    }
    @Column(name ="ITEM_REQUIRE")
    public String getItemRequire(){
        return this.itemRequire;
    }
    @Column(name ="REMARK")
    public String getRemark(){
        return this.remark;
    }
    @Column(name ="PRINCIPAL_NAME")
    public String getPrincipalName(){
        return this.principalName;
    }
    @Column(name ="DUTY_ID")
    public String getDutyId(){
        return this.dutyId;
    }
    @Column(name ="REGISTER_ID")
    public String getRegisterId(){
        return this.registerId;
    }
    @Column(name ="REGISTER_NAME")
    public String getRegisterName(){
        return this.registerName;
    }
    @Column(name ="REGISTER_DATE")
    public java.util.Date getRegisterDate(){
        return this.registerDate;
    }
    @Column(name ="STATUS")
    public String getStatus(){
        return this.status;
    }
    @Column(name ="ITEM_TIME")
    public java.util.Date getItemTime(){
        return this.itemTime;
    }
    @Column(name ="PRINCIPAL_DATE")
    public java.util.Date getPrincipalDate(){
        return this.principalDate;
    }
    @Column(name ="CHECK_TIME")
    public java.util.Date getCheckTime(){
        return this.checkTime;
    }
    @Column(name ="SEND_TIME")
	public Date getSendTime() {
		return sendTime;
    }
	
	@Column(name = "DUTY_DEP_ID")
	public String getDutyDepId() {
		return dutyDepId;
	}
	public void setDutyDepId(String dutyDepId) {
		this.dutyDepId = dutyDepId;
	}
	@Column(name = "DELAY")
	public Date getDelay() {
		return delay;
	}
	public void setDelay(Date delay) {
		this.delay = delay;
	}
	
	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.LAZY,mappedBy="taskAllot")
	@OrderBy
	public Set<TaskAllotFk> getTaskAllotFks() {
		return taskAllotFks;
	}
	public void setTaskAllotFks(Set<TaskAllotFk> taskAllotFks) {
		this.taskAllotFks = taskAllotFks;
	}
	
	
	
	

    
} 
