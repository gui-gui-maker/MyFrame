package com.fwxm.contract.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name="CONTRACT_CUSTOM_LEVEL")
public class ContractCustomLevel implements BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3439701072064241007L;
	private String id;
	private String levels;
	private String main_product;
	private String remark;
	private String data_status;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLevels() {
		return levels;
	}

	public void setLevels(String levels) {
		this.levels = levels;
	}

	public String getMain_product() {
		return main_product;
	}

	public void setMain_product(String main_product) {
		this.main_product = main_product;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getData_status() {
		return data_status;
	}

	public void setData_status(String data_status) {
		this.data_status = data_status;
	}

	

}
