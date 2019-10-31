package com.scts.car.bean;

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
@Table(name = "TJY2_CAR_FUEL_CARD")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarFuelCard implements BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;// 主键id

	private String fkCarId;// 车辆id

	private String cardNum;// 卡号

	private String cardType;// 卡类别

	private java.util.Date yearMonth;// 年月份

	private java.math.BigDecimal moneyInitial;// 初始金额

	private java.math.BigDecimal moneyAdd;// 冲入金额

	private java.util.Date moneyAddDate;// 充值日期

	private java.math.BigDecimal moneyFinal;// 最终金额

	private java.math.BigDecimal moneyUsed;// 消费金额

	private java.math.BigDecimal moneyLeft;// 剩余金额

	private String useDepartmentId;// 使用部门id

	private String useDepartment;// 使用部门
	
	private java.math.BigDecimal mileage;// 公里数

	private String remark;// 备注

	private String createUserId;// 创建人id

	private String createUserName;// 创建人

	private java.util.Date createDate;// 创建时间

	private String lastModifyUserId;// 最后修改人id

	private String lastModifyUserName;// 最后修改人

	private java.util.Date lastModifyDate;// 最后修改时间

	private String dataStatus;// 数据状态

	public void setId(String value) {
		this.id = value;
	}

	public void setFkCarId(String value) {
		this.fkCarId = value;
	}

	public void setCardNum(String value) {
		this.cardNum = value;
	}

	public void setCardType(String value) {
		this.cardType = value;
	}

	public void setYearMonth(java.util.Date value) {
		this.yearMonth = value;
	}

	public void setMoneyInitial(java.math.BigDecimal value) {
		this.moneyInitial = value;
	}

	public void setMoneyAdd(java.math.BigDecimal value) {
		this.moneyAdd = value;
	}

	public void setMoneyAddDate(java.util.Date value) {
		this.moneyAddDate = value;
	}

	public void setMoneyFinal(java.math.BigDecimal value) {
		this.moneyFinal = value;
	}

	public void setMoneyUsed(java.math.BigDecimal value) {
		this.moneyUsed = value;
	}

	public void setMoneyLeft(java.math.BigDecimal value) {
		this.moneyLeft = value;
	}

	public void setUseDepartmentId(String value) {
		this.useDepartmentId = value;
	}

	public void setUseDepartment(String value) {
		this.useDepartment = value;
	}

	public void setMileage(java.math.BigDecimal value) {
		this.mileage = value;
	}
	
	public void setRemark(String value) {
		this.remark = value;
	}

	public void setCreateUserId(String value) {
		this.createUserId = value;
	}

	public void setCreateUserName(String value) {
		this.createUserName = value;
	}

	public void setCreateDate(java.util.Date value) {
		this.createDate = value;
	}

	public void setLastModifyUserId(String value) {
		this.lastModifyUserId = value;
	}

	public void setLastModifyUserName(String value) {
		this.lastModifyUserName = value;
	}

	public void setLastModifyDate(java.util.Date value) {
		this.lastModifyDate = value;
	}

	public void setDataStatus(String value) {
		this.dataStatus = value;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	public String getId() {
		return this.id;
	}

	@Column(name = "FK_CAR_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getFkCarId() {
		return this.fkCarId;
	}

	@Column(name = "CARD_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getCardNum() {
		return this.cardNum;
	}

	@Column(name = "CARD_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getCardType() {
		return this.cardType;
	}

	@Column(name = "YEAR_MONTH", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public java.util.Date getYearMonth() {
		return this.yearMonth;
	}

	@Column(name = "MONEY_INITIAL", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	public java.math.BigDecimal getMoneyInitial() {
		return this.moneyInitial;
	}

	@Column(name = "MONEY_ADD", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	public java.math.BigDecimal getMoneyAdd() {
		return this.moneyAdd;
	}

	@Column(name = "MONEY_ADD_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public java.util.Date getMoneyAddDate() {
		return this.moneyAddDate;
	}

	@Column(name = "MONEY_FINAL", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	public java.math.BigDecimal getMoneyFinal() {
		return this.moneyFinal;
	}

	@Column(name = "MONEY_USED", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	public java.math.BigDecimal getMoneyUsed() {
		return this.moneyUsed;
	}

	@Column(name = "MONEY_LEFT", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	public java.math.BigDecimal getMoneyLeft() {
		return this.moneyLeft;
	}

	@Column(name = "USE_DEPARTMENT_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getUseDepartmentId() {
		return this.useDepartmentId;
	}

	@Column(name = "USE_DEPARTMENT", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getUseDepartment() {
		return this.useDepartment;
	}

	@Column(name = "MILEAGE", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	public java.math.BigDecimal getMileage() {
		return this.mileage;
	}
	
	@Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getRemark() {
		return this.remark;
	}

	@Column(name = "CREATE_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getCreateUserId() {
		return this.createUserId;
	}

	@Column(name = "CREATE_USER_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getCreateUserName() {
		return this.createUserName;
	}

	@Column(name = "CREATE_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public java.util.Date getCreateDate() {
		return this.createDate;
	}

	@Column(name = "LAST_MODIFY_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getLastModifyUserId() {
		return this.lastModifyUserId;
	}

	@Column(name = "LAST_MODIFY_USER_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getLastModifyUserName() {
		return this.lastModifyUserName;
	}

	@Column(name = "LAST_MODIFY_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public java.util.Date getLastModifyDate() {
		return this.lastModifyDate;
	}

	@Column(name = "DATA_STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getDataStatus() {
		return this.dataStatus;
	}

}
