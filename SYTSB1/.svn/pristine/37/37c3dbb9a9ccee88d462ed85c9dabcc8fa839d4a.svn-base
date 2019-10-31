package com.lsts.finance.bean;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
@Table(name = "TJY2_CW_BORROW_MONEY")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CwBorrowMoney implements BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;//借款单id

    private String unit;//单位

    private String department;//部门

    private java.util.Date borrowMoneyDate;//当前日期

    private BigDecimal cash;//现金

    private String payee;//收款单位

    private String openingBank;//开户行

    private String accounts;//帐号

    private String rmb;//人民币大写

    private String remark;//备注

    private String borrower;//借款人

    private BigDecimal money;//金额
    
    private BigDecimal import_money;//输入金额
    
    private String status;//状态
    
    private String registrant;//登记人
    
    private String repayment_status; 	//还款状态
    
    private String repayment_man;   //还款人
    
    private java.util.Date repayment_time;   //还款时间

    private String registrantId;//登记人id

    private String borrowerId;//借款人id

    private String departmentId;//部门ID
	
    private String repayment_man_id;   //还款人id

    private String handleId;//处理人ID
    private String handleName;//处理人
    private java.util.Date handleTime;//处理人时间
    private String handleOpinion;//处理人意见
    
    private String handleIdh;//处理人ID还款
    private String handleNameh;//处理人还款
    private java.util.Date handleTimeh;//处理人时间还款
    private String handleOpinionh;//处理人意见还款
	
    private String identifier;//编号
    
    private java.util.Date abolishTime;//废除时间
    
    private String abolish; //废除状态
    
    private String handleIdj;//处理人ID借款确认
    private String handleNamej;//处理人借款确认
    private java.util.Date handleTimej;//处理时间借款确认
   
    public void setHandleIdj(String handleIdj) {
		this.handleIdj = handleIdj;
	}
	public void setHandleNamej(String handleNamej) {
		this.handleNamej = handleNamej;
	}
	public void setHandleTimej(java.util.Date handleTimej) {
		this.handleTimej = handleTimej;
	}
	public void setAbolish(String abolish) {
		this.abolish = abolish;
	}
	public void setAbolishTime(java.util.Date abolishTime) {
		this.abolishTime = abolishTime;
	}
	public void setIdentifier(String value){
        this.identifier = value;
    }
	public void setHandleIdh(String handleIdh) {
		this.handleIdh = handleIdh;
	}
	public void setHandleNameh(String handleNameh) {
		this.handleNameh = handleNameh;
	}
	public void setHandleTimeh(java.util.Date handleTimeh) {
		this.handleTimeh = handleTimeh;
	}
	public void setHandleOpinionh(String handleOpinionh) {
		this.handleOpinionh = handleOpinionh;
	}
	public void setHandleId(String handleId) {
		this.handleId = handleId;
	}
	public void setHandleName(String handleName) {
		this.handleName = handleName;
	}
	public void setHandleTime(java.util.Date handleTime) {
		this.handleTime = handleTime;
	}
	public void setHandleOpinion(String handleOpinion) {
		this.handleOpinion = handleOpinion;
	}
	public void setRepayment_man_id(String repayment_man_id) {
		this.repayment_man_id = repayment_man_id;
	}
	public void setBorrowerId(String borrowerId) {
		this.borrowerId = borrowerId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public void setRegistrantId(String registrantId) {
		this.registrantId = registrantId;
	}
	public void setRepayment_time(java.util.Date repayment_time) {
		this.repayment_time = repayment_time;
	}
	public void setRepayment_man(String repayment_man) {
		this.repayment_man = repayment_man;
	}
	public void setRepayment_status(String repayment_status) {
		this.repayment_status = repayment_status;
	}
	public void setId(String value){
        this.id = value;
    }
    public void setUnit(String value){
        this.unit = value;
    }
    public void setDepartment(String value){
        this.department = value;
    }
    public void setBorrowMoneyDate(java.util.Date value){
        this.borrowMoneyDate = value;
    }
    public void setCash(BigDecimal value){
        this.cash = value;
    }
    public void setPayee(String value){
        this.payee = value;
    }
    public void setOpeningBank(String value){
        this.openingBank = value;
    }
    public void setAccounts(String value){
        this.accounts = value;
    }
    public void setRmb(String value){
        this.rmb = value;
    }
    public void setRemark(String value){
        this.remark = value;
    }
    public void setBorrower(String value){
        this.borrower = value;
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
    @Column(name ="DEPARTMENT",unique=false,nullable=true,insertable=true,updatable=true,length=20)
    public String getDepartment(){
        return this.department;
    }
    @Column(name ="BORROW_MONEY_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getBorrowMoneyDate(){
        return this.borrowMoneyDate;
    }
    @Column(name ="CASH",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public BigDecimal getCash(){
        return this.cash;
    }
    @Column(name ="PAYEE",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getPayee(){
        return this.payee;
    }
    @Column(name ="OPENING_BANK",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getOpeningBank(){
        return this.openingBank;
    }
    @Column(name ="ACCOUNTS",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getAccounts(){
        return this.accounts;
    }
    @Column(name ="RMB",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getRmb(){
        return this.rmb;
    }
    @Column(name ="REMARK",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getRemark(){
        return this.remark;
    }
    @Column(name ="BORROWER",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getBorrower(){
        return this.borrower;
    }
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	@Column(name ="IMPORT_MONEY",unique=false,nullable=true,insertable=true,updatable=true,length=100)
	public BigDecimal getImport_money() {
		return import_money;
	}
	public void setImport_money(BigDecimal import_money) {
		this.import_money = import_money;
	}
	@Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=20)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name ="REGISTRANT",unique=false,nullable=true,insertable=true,updatable=true,length=50)
	public String getRegistrant() {
		return registrant;
	}
	public void setRegistrant(String registrant) {
		this.registrant = registrant;
	}
	@Column(name ="REPAYMENT_STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=50)
	public String getRepayment_status() {
		return repayment_status;
	}
	@Column(name ="REPAYMENT_MAN",unique=false,nullable=true,insertable=true,updatable=true,length=50)
	public String getRepayment_man() {
		return repayment_man;
	}
    @Column(name ="REPAYMENT_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	public java.util.Date getRepayment_time() {
		return repayment_time;
	}
    @Column(name ="REGISTRANTID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getRegistrantId() {
		return registrantId;
	}
    @Column(name ="BORROWER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getBorrowerId() {
		return borrowerId;
	}
    @Column(name ="DEPARTMENT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
	public String getDepartmentId() {
		return departmentId;
	}
    @Column(name ="REPAYMENT_MAN_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getRepayment_man_id() {
		return repayment_man_id;
	}
    @Column(name ="HANDLE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getHandleId() {
		return handleId;
	}
    @Column(name ="HANDLE_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
	public String getHandleName() {
		return handleName;
	}
    @Column(name ="HANDLE_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	public java.util.Date getHandleTime() {
		return handleTime;
	}
    @Column(name ="HANDLE_OPINION",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
	public String getHandleOpinion() {
		return handleOpinion;
	}
    @Column(name ="HANDLE_IDH",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getHandleIdh() {
		return handleIdh;
	}
    @Column(name ="HANDLE_NAMEH",unique=false,nullable=true,insertable=true,updatable=true,length=100)
	public String getHandleNameh() {
		return handleNameh;
	}
    @Column(name ="HANDLE_TIMEH",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	public java.util.Date getHandleTimeh() {
		return handleTimeh;
	}
    @Column(name ="HANDLE_OPINIONH",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
	public String getHandleOpinionh() {
		return handleOpinionh;
	}
    @Column(name ="IDENTIFIER",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getIdentifier(){
        return this.identifier;
    }
    @Column(name ="ABOLISH_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getAbolishTime() {
		return abolishTime;
	}
    @Column(name = "ABOLISH",unique=false,nullable=true,insertable=true,updatable=true,length=100)
   	public String getAbolish() {
   		return abolish;
   	}
    @Column(name ="HANDLE_IDJ",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getHandleIdj() {
		return handleIdj;
	}
    @Column(name ="HANDLE_NAMEJ",unique=false,nullable=true,insertable=true,updatable=true,length=100)
	public String getHandleNamej() {
		return handleNamej;
	}
    @Column(name ="HANDLE_TIMEJ",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	public java.util.Date getHandleTimej() {
		return handleTimej;
	}
    public Map<String,String> beanToMap(){
    	Map<String,String> map = new HashMap<String,String>();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	
    	map.put("ID",id);
    	map.put("UNIT",unit);
    	map.put("DEPARTMENT",department);
    	map.put("BORROW_MONEY_DATE",borrowMoneyDate!=null?sdf.format(borrowMoneyDate):"");
    	map.put("CASH",cash!=null?cash.toString():""); 
    	map.put("PAYEE",payee);
    	map.put("OPENING_BANK",openingBank);
    	map.put("ACCOUNTS",accounts);
    	map.put("RMB",rmb);
    	map.put("REMARK",remark);
    	map.put("BORROWER",borrower);
    	map.put("MONEY",money!=null?money.toString():"");
    	map.put("IMPORT_MONEY",import_money!=null?import_money.toString():"");
    	map.put("STATUS",status);
    	map.put("REGISTRANT",registrant);
    	map.put("REPAYMENT_STATUS",repayment_status);
    	map.put("REPAYMENT_MAN",repayment_man);
    	map.put("REPAYMENT_TIME",repayment_time!=null?sdf.format(repayment_time):""); 
    	map.put("REGISTRANTID",registrantId);
    	map.put("DEPARTMENT_ID",departmentId);
    	map.put("BORROWER_ID",borrowerId);
    	map.put("REPAYMENT_MAN_ID",repayment_man_id);
    	map.put("HANDLE_ID",handleId);
    	map.put("HANDLE_NAME",handleName);
    	map.put("HANDLE_TIME",handleTime!=null?sdf.format(handleTime):"");
    	map.put("HANDLE_OPINION",handleOpinion);
    	map.put("HANDLE_IDH",handleIdh);
    	map.put("HANDLE_NAMEH",handleNameh);
    	map.put("HANDLE_TIMEH",handleTimeh!=null?sdf.format(handleTimeh):"");
    	map.put("HANDLE_OPINIONH",handleOpinionh);
    	map.put("IDENTIFIER",identifier);
    	map.put("ABOLISH_TIME",abolishTime!=null?sdf.format(abolishTime):"");

    	return map ;
    }
} 
