package com.fwxm.contract.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name="CONTRACT_EVALUATE")
public class ContractEvaluate implements BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String contract_id;//合同信息id
	private String evaluate_op_id;//评价人id
	private String evaluate_op;//评价人id
	private Date evaluate_date;//评价时间
	private String evaluate_contain;//评价内容
	private String data_status;//数据状态 0 正常 99 删除

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContract_id() {
		return contract_id;
	}

	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}

	public String getEvaluate_op_id() {
		return evaluate_op_id;
	}

	public void setEvaluate_op_id(String evaluate_op_id) {
		this.evaluate_op_id = evaluate_op_id;
	}

	public String getEvaluate_op() {
		return evaluate_op;
	}

	public void setEvaluate_op(String evaluate_op) {
		this.evaluate_op = evaluate_op;
	}

	public Date getEvaluate_date() {
		return evaluate_date;
	}

	public void setEvaluate_date(Date evaluate_date) {
		this.evaluate_date = evaluate_date;
	}

	public String getEvaluate_contain() {
		return evaluate_contain;
	}

	public void setEvaluate_contain(String evaluate_contain) {
		this.evaluate_contain = evaluate_contain;
	}

	public String getData_status() {
		return data_status;
	}

	public void setData_status(String data_status) {
		this.data_status = data_status;
	}

	
	
}
