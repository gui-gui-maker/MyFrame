package com.lsts.finance.bean;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_CW_REFUNDMENT")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CwRefundment implements BaseEntity{

    
	private static final long serialVersionUID = 1L;

	private String id;//主键
    
    //private String unitid;//单位ID

    private String unit;//单位

    private String departmentId;//部门ID

    private String department;//部门

    private Date dmDate;//领款时间

    private String dmId;//领款人ID

    private String dmName;//领款人

    private String dmFor;//领款事由
    	
    private String amountinwords; //金额大写

    private BigDecimal money;//金额
    
    private String registrantId; //登记人ID
    
    private String	registrant; //登记人
    
    private Date   registrantDate; //登记时间
    
    private String proposerId; //申请人ID
    
    private String proposer;//申请人
    
    private Date proposerDate;//申请时间

    private String remark;//备注

    private String status;//状态
    
    private String handleId; //处理人ID 
    
    private String handleName; //处理人
    
    private Date handleTime; //处理时间
    
    private String number_tab; //编号

    private Date abolish_time; //废除时间
 
    private String abolish; //废除状态
//    private String handleOpinion; //处理意见
    private String dmQrId; //领款确认人ID
    private String dmQrName; //领款确认人
    private Date dmQrDate; //确认时间
    
    public void setId(String value){
        this.id = value;
    }
    public void setUnit(String value){
        this.unit = value;
    }
    public void setDepartment(String value){
        this.department = value;
    }
    public void setDmDate(java.util.Date value){
        this.dmDate = value;
    }
    public void setDmId(String value){
        this.dmId = value;
    }
    public void setDmName(String value){
        this.dmName = value;
    }
    public void setDmFor(String value){
        this.dmFor = value;
    }
    public void setAmountinwords(String value) {
		this.amountinwords = value;
	}
    public void setMoney(BigDecimal value){
        this.money = value;
    }
    
    public void setRegistrant(String registrant) {
		this.registrant = registrant;
	}
	
	public void setRegistrantDate(Date registrantDate) {
		this.registrantDate = registrantDate;
	}
    public void setRemark(String value){
        this.remark = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    
    public void setProposerDate(Date value) {
		this.proposerDate = value;
	}
    
    public void setProposer(String value) {
		this.proposer = value;
	}
    
    public void setProposerId(String value) {
		this.proposerId = value;
	}
    
    public void setRegistrantId(String value) {
		this.registrantId = value;
	}
    
    
    
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    
    @Column(name ="UNIT",unique=false,nullable=true,insertable=true,updatable=true,length=20)
    public String getUnit(){
        return this.unit;
    }
    @Column(name ="DEPARTMENT",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getDepartment(){
        return this.department;
    }
    @Column(name ="DM_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getDmDate(){
        return this.dmDate;
    }

    @Column(name ="DM_ID",unique=true,nullable=true,insertable=true,updatable=true,length=32)
    public String getDmId(){
        return this.dmId;
    }
    @Column(name ="DM_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getDmName(){
        return this.dmName;
    }
    @Column(name ="DM_FOR",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getDmFor(){
        return this.dmFor;
    }
    @Column(name ="MONEY",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public BigDecimal getMoney(){
        return this.money;
    }
    @Column(name ="REMARK",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getRemark(){
        return this.remark;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="AMOUNT_IN_WORDS",unique=true,nullable=true,insertable=true,updatable=true,length=100)
	public String getAmountinwords() {
		return amountinwords;
	}
    @Column(name ="REGISTRANT",unique=false,nullable=true,insertable=true,updatable=true,length=50)
	public String getRegistrant() {
		return registrant;
	}
    @Column(name ="REGISTRANT_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	public Date getRegistrantDate() {
		return registrantDate;
	}
    
    
    @Column(name ="REGISTRANT_ID",unique=true,nullable=true,insertable=true,updatable=true,length=32)
	public String getRegistrantId() {
		return registrantId;
	}
    
    
    @Column(name ="PROPOSER_ID",unique=true,nullable=true,insertable=true,updatable=true,length=32)
	public String getProposerId() {
		return proposerId;
	}
    @Column(name ="PROPOSER",unique=true,nullable=true,insertable=true,updatable=true,length=50)
	public String getProposer() {
		return proposer;
	}
    @Column(name ="PROPOSER_DATE",unique=true,nullable=true,insertable=true,updatable=true,length=7)
	public Date getProposerDate() {
		return proposerDate;
	}
    @Column(name = "DEPARTMENT_ID")
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	
	
	
	@Column(name = "NUMBER_TAB")
	public String getNumber_tab() {
		return number_tab;
	}
	public void setNumber_tab(String number_tab) {
		this.number_tab = number_tab;
	}
	@Column(name = "HANDLE_ID")
	public String getHandleId() {
		return handleId;
	}
	public void setHandleId(String handleId) {
		this.handleId = handleId;
	}
	@Column(name = "HANDLE_NAME")
	public String getHandleName() {
		return handleName;
	}
	public void setHandleName(String handleName) {
		this.handleName = handleName;
	}
	@Column(name = "HANDLE_TIME")
	public Date getHandleTime() {
		return handleTime;
	}
	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}
	@Column(name = "ABOLISH_TIME")
	public Date getAbolish_time() {
		return abolish_time;
	}
	public void setAbolish_time(Date abolish_time) {
		this.abolish_time = abolish_time;
	}
	@Column(name = "ABOLISH")
	public String getAbolish() {
		return abolish;
	}
	public void setAbolish(String abolish) {
		this.abolish = abolish;
	}
	@Column(name = "DM_QR_ID")
	public String getDmQrId() {
		return dmQrId;
	}
	public void setDmQrId(String dmQrId) {
		this.dmQrId = dmQrId;
	}
	@Column(name = "DM_QR_NAME")
	public String getDmQrName() {
		return dmQrName;
	}
	public void setDmQrName(String dmQrName) {
		this.dmQrName = dmQrName;
	}
	@Column(name = "DM_QR_DATE")
	public Date getDmQrDate() {
		return dmQrDate;
	}
	public void setDmQrDate(Date dmQrDate) {
		this.dmQrDate = dmQrDate;
	}
//	@Column(name = "HANDLE_OPINION")
//	public String getHandleOpinion() {
//		return handleOpinion;
//	}
//	public void setHandleOpinion(String handleOpinion) {
//		this.handleOpinion = handleOpinion;
//	}
 
    
	
	
	
	public Map<String,String> beanToMap(){
    	Map<String,String> map = new HashMap<String,String>();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
    	map.put("ID",id);
    	map.put("UNIT",unit);
    	map.put("DEPARTMENT",department);
    	map.put("DM_DATE",dmDate!=null?sdf.format(dmDate):"");
    	map.put("DM_ID",dmId);
    	map.put("DM_NAME",dmName);
    	map.put("DM_FOR",dmFor);
    	map.put("MONEY",money!=null?money.toString():"");
    	map.put("REMARK",remark);
    	map.put("STATUS",status);
    	map.put("AMOUNT_IN_WORDS",amountinwords);
    	map.put("REGISTRANT",registrant);
    	map.put("REGISTRANT_DATE",registrantDate!=null?sdf.format(registrantDate):"");
    	map.put("REGISTRANT_ID",registrantId);
    	map.put("PROPOSER_ID",proposerId);
    	map.put("PROPOSER",proposer);
    	map.put("PROPOSER_DATE",proposerDate!=null?sdf.format(proposerDate):""); 
    	map.put("DEPARTMENT_ID",departmentId);

    	return map;
	
	}

} 
