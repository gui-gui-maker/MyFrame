package com.fwxm.dining.bean;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;
/**
 * 一卡通基本信息
 * @author Administrator
 *
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "FOOD_CARD")
public class FoodCard implements BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int NORMAL = 1;//正常状态 
	public static final int LOSS = 0;//挂失
	
	private String id;
	private String cardNo;//卡号,唯一
	private String userId;//员工emp_id
	private String cuser;//持卡人姓名
	private String tel;//电话
	private Integer count;//次数
	private String lastAction;//最后操作
	private Integer lastAdd;//最后操作
	private Integer lastDecrease;
	private String lastOperator;
	private Integer cardStatus;//挂失？正常？
	
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCuser() {
		return cuser;
	}
	public void setCuser(String cuser) {
		this.cuser = cuser;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getLastAction() {
		return lastAction;
	}
	public void setLastAction(String lastAction) {
		this.lastAction = lastAction;
	}
	public Integer getLastAdd() {
		return lastAdd;
	}
	public void setLastAdd(Integer lastAdd) {
		this.lastAdd = lastAdd;
	}
	public Integer getLastDecrease() {
		return lastDecrease;
	}
	public void setLastDecrease(Integer lastDecrease) {
		this.lastDecrease = lastDecrease;
	}
	public String getLastOperator() {
		return lastOperator;
	}
	public void setLastOperator(String lastOperator) {
		this.lastOperator = lastOperator;
	}
	public Integer getCardStatus() {
		return cardStatus;
	}
	public void setCardStatus(Integer cardStatus) {
		this.cardStatus = cardStatus;
	}
	
	
	
}
