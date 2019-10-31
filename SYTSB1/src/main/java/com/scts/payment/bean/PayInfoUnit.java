package com.scts.payment.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;
/**
 * 往来账部门分摊
 * @author pingZhou
 *
 */
@Entity
@Table(name="TJY2_PAY_INFO_UNIT")
public class PayInfoUnit implements BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String fk_pay_info_id;
	private String unit_id;
	private String unit_name;

    private java.math.BigDecimal money;//转入金额
    private java.math.BigDecimal restMoney;//剩余金额（保留）

    private java.math.BigDecimal usedMoney=new BigDecimal(0);//冲账金额（保留）

    private String remark;//备注
    private String op_id;//操作人id
    private String op_name;//操作人姓名
    private Date op_time;//操作时间
		
		

	  @Id
	    @GeneratedValue(generator = "system-uuid")
	    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
	    public String getId(){
	        return this.id;
	    }

	  public void setId(String id) {
			// TODO Auto-generated method stub
			this.id = id;
		}

	
	public String getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}

	public String getUnit_name() {
		return unit_name;
	}

	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}

	public java.math.BigDecimal getMoney() {
		return money;
	}

	public void setMoney(java.math.BigDecimal money) {
		this.money = money;
	}

	@Column(name="REST_MONEY")
	public java.math.BigDecimal getRestMoney() {
		return restMoney;
	}

	public void setRestMoney(java.math.BigDecimal restMoney) {
		this.restMoney = restMoney;
	}

	@Column(name="USED_MONEY")
	public java.math.BigDecimal getUsedMoney() {
		return usedMoney;
	}

	public void setUsedMoney(java.math.BigDecimal usedMoney) {
		this.usedMoney = usedMoney;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOp_id() {
		return op_id;
	}

	public void setOp_id(String op_id) {
		this.op_id = op_id;
	}

	public String getOp_name() {
		return op_name;
	}

	public void setOp_name(String op_name) {
		this.op_name = op_name;
	}

	public Date getOp_time() {
		return op_time;
	}

	public void setOp_time(Date op_time) {
		this.op_time = op_time;
	}

	public String getFk_pay_info_id() {
		return fk_pay_info_id;
	}

	public void setFk_pay_info_id(String fk_pay_info_id) {
		this.fk_pay_info_id = fk_pay_info_id;
	}
	  
	  
	  
}
