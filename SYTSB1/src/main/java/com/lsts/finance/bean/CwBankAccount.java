package com.lsts.finance.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Table(name = "TJY2_CW_CUS_ACCOUNT")
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class CwBankAccount implements BaseEntity {

	private String id;// id
	private String account_name;
	private String transfer_person;
	private String transfer_person_tel;
	private String transfer_address;
	
	@Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getTransfer_person() {
		return transfer_person;
	}

	public void setTransfer_person(String transfer_person) {
		this.transfer_person = transfer_person;
	}

	public String getTransfer_person_tel() {
		return transfer_person_tel;
	}

	public void setTransfer_person_tel(String transfer_person_tel) {
		this.transfer_person_tel = transfer_person_tel;
	}

	public String getTransfer_address() {
		return transfer_address;
	}

	public void setTransfer_address(String transfer_address) {
		this.transfer_address = transfer_address;
	}

}
