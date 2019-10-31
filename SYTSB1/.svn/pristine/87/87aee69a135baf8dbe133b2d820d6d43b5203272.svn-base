package com.lsts.finance.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 银行转账记录数据传输对象 
 * CwBankDTO
 * @author GaoYa
 * @date 2015-11-18 下午02:55:00
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CwBankDTO implements Serializable{

	private String id;		// id
    private Date jyTime;	// 交易时间
    private BigDecimal money;		// 转入金额
    private String accountName;		// 对方户号
    private BigDecimal restMoney;	// 剩余金额
    private BigDecimal usedMoney;	// 本次收费金额
    private String pay_remark;		// 备注
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getJyTime() {
		return jyTime;
	}
	public void setJyTime(Date jyTime) {
		this.jyTime = jyTime;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public BigDecimal getRestMoney() {
		return restMoney;
	}
	public void setRestMoney(BigDecimal restMoney) {
		this.restMoney = restMoney;
	}
	public BigDecimal getUsedMoney() {
		return usedMoney;
	}
	public void setUsedMoney(BigDecimal usedMoney) {
		this.usedMoney = usedMoney;
	}
	public String getPay_remark() {
		return pay_remark;
	}
	public void setPay_remark(String pay_remark) {
		this.pay_remark = pay_remark;
	}


    
} 
