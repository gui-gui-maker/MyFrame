package com.lsts.finance.bean;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_CW_BANK_DETAIL")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CwBankDetail implements BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;//id
    private java.util.Date jyTime;//交易时间

    private java.math.BigDecimal money;//转入金额

    private String accountName;//对方户号

    private java.math.BigDecimal restMoney;//剩余金额（保留）

    private java.math.BigDecimal usedMoney=new BigDecimal(0);//冲账金额（保留）
    
    private String transferPerson;//转账人
    
    private String transferPersonTel;//转账电话
    
    private String transferAddress;//转账地址

    private String remrk;//备注

    public void setId(String value){
        this.id = value;
    }
    public void setJyTime(java.util.Date value){
        this.jyTime = value;
    }
    public void setMoney(java.math.BigDecimal value){
        this.money = value;
    }
    public void setAccountName(String value){
        this.accountName = value;
    }
    public void setRestMoney(java.math.BigDecimal value){
        this.restMoney = value;
    }
    public void setUsedMoney(java.math.BigDecimal value){
        this.usedMoney = value;
    }
    public void setRemrk(String value){
        this.remrk = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="JY_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getJyTime(){
        return this.jyTime;
    }
    @Column(name ="MONEY",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getMoney(){
        return this.money;
    }
    @Column(name ="ACCOUNT_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getAccountName(){
        return this.accountName;
    }
    @Column(name ="REST_MONEY",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getRestMoney(){
        return this.restMoney;
    }
    @Column(name ="USED_MONEY",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getUsedMoney(){
        return this.usedMoney;
    }
    @Column(name ="REMRK",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getRemrk(){
        return this.remrk;
    }
    @Column(name ="TRANSFER_PERSON")
	public String getTransferPerson() {
		return transferPerson;
	}
	public void setTransferPerson(String transferPerson) {
		this.transferPerson = transferPerson;
	}
	@Column(name ="TRANSFER_PERSON_TEL")
	public String getTransferPersonTel() {
		return transferPersonTel;
	}
	public void setTransferPersonTel(String transferPersonTel) {
		this.transferPersonTel = transferPersonTel;
	}
	@Column(name ="TRANSFER_ADDRESS")
	public String getTransferAddress() {
		return transferAddress;
	}
	public void setTransferAddress(String transferAddress) {
		this.transferAddress = transferAddress;
	}


} 
