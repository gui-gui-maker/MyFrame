package com.fwxm.dining.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 一卡通流水账
 * @author Guido
 *
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "FoodCardDayToDayAccount")
public class FoodCardDayToDayAccount implements BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String cardNo;//卡号
	private int oldCount;//原账户余额
	private int countAdd;//充值
	private int countDecrease;//消费
	private String summary;//摘要
	private int newCount;
	private String operator;
	private Date actionTime;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public int getOldCount() {
		return oldCount;
	}
	public void setOldCount(int oldCount) {
		this.oldCount = oldCount;
	}
	public int getCountAdd() {
		return countAdd;
	}
	public void setCountAdd(int countAdd) {
		this.countAdd = countAdd;
	}
	public int getCountDecrease() {
		return countDecrease;
	}
	public void setCountDecrease(int countDecrease) {
		this.countDecrease = countDecrease;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public int getNewCount() {
		return newCount;
	}
	public void setNewCount(int newCount) {
		this.newCount = newCount;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getActionTime() {
		return actionTime;
	}
	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}
	
	
	
	
}
