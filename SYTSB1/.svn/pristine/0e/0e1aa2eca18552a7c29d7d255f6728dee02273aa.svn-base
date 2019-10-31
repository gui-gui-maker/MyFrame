package com.lsts.device.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/*******************************************************************************
 * 
 * 设备告知书
 * 
 * @author liming 2014-5-9
 * 
 */

@Entity
@Table(name = "TZSB_APPLICATION")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TzsbApplication implements BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8473696099585447874L;
	private String id;
	private String device_sort;
	private String construct_sort;
	private Date application_date;
	private String application_code;
	private String application_remark;
	private String receive_op;
	private Date receive_date;
	private String supervise_results;
	private String results_notice_num;
	private Date results_notice_date;
	private String fk_construction_units_id;
	private String construction_units_name;
	private String device_sort_code;
	private String construction_units_permit_id;
	private String construction_units_permit_no;
	private Date construction_units_permit_date;
	private String created_by;
	private Date created_date;
	private String last_upd_by;
	private Date last_upd_date;
	private Character data_status;

	private TzsbAppConstrucationOrg tzsbAppConstrucationOrg;
	
	private Set<TzsbAppOutsour> tzsbAppOutsours=new HashSet<TzsbAppOutsour>();
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDevice_sort() {
		return device_sort;
	}

	public void setDevice_sort(String device_sort) {
		this.device_sort = device_sort;
	}

	public String getConstruct_sort() {
		return construct_sort;
	}

	public void setConstruct_sort(String construct_sort) {
		this.construct_sort = construct_sort;
	}

	public Date getApplication_date() {
		return application_date;
	}

	public void setApplication_date(Date application_date) {
		this.application_date = application_date;
	}

	public String getApplication_code() {
		return application_code;
	}

	public void setApplication_code(String application_code) {
		this.application_code = application_code;
	}

	public String getApplication_remark() {
		return application_remark;
	}

	public void setApplication_remark(String application_remark) {
		this.application_remark = application_remark;
	}

	public String getReceive_op() {
		return receive_op;
	}

	public void setReceive_op(String receive_op) {
		this.receive_op = receive_op;
	}

	public Date getReceive_date() {
		return receive_date;
	}

	public void setReceive_date(Date receive_date) {
		this.receive_date = receive_date;
	}

	public String getSupervise_results() {
		return supervise_results;
	}

	public void setSupervise_results(String supervise_results) {
		this.supervise_results = supervise_results;
	}

	public String getResults_notice_num() {
		return results_notice_num;
	}

	public void setResults_notice_num(String results_notice_num) {
		this.results_notice_num = results_notice_num;
	}

	public Date getResults_notice_date() {
		return results_notice_date;
	}

	public void setResults_notice_date(Date results_notice_date) {
		this.results_notice_date = results_notice_date;
	}

	public String getFk_construction_units_id() {
		return fk_construction_units_id;
	}

	public void setFk_construction_units_id(String fk_construction_units_id) {
		this.fk_construction_units_id = fk_construction_units_id;
	}

	public String getConstruction_units_name() {
		return construction_units_name;
	}

	public void setConstruction_units_name(String construction_units_name) {
		this.construction_units_name = construction_units_name;
	}

	public String getDevice_sort_code() {
		return device_sort_code;
	}

	public void setDevice_sort_code(String device_sort_code) {
		this.device_sort_code = device_sort_code;
	}

	public String getConstruction_units_permit_id() {
		return construction_units_permit_id;
	}

	public void setConstruction_units_permit_id(String construction_units_permit_id) {
		this.construction_units_permit_id = construction_units_permit_id;
	}

	public String getConstruction_units_permit_no() {
		return construction_units_permit_no;
	}

	public void setConstruction_units_permit_no(String construction_units_permit_no) {
		this.construction_units_permit_no = construction_units_permit_no;
	}

	public Date getConstruction_units_permit_date() {
		return construction_units_permit_date;
	}

	public void setConstruction_units_permit_date(
			Date construction_units_permit_date) {
		this.construction_units_permit_date = construction_units_permit_date;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public String getLast_upd_by() {
		return last_upd_by;
	}

	public void setLast_upd_by(String last_upd_by) {
		this.last_upd_by = last_upd_by;
	}

	public Date getLast_upd_date() {
		return last_upd_date;
	}

	public void setLast_upd_date(Date last_upd_date) {
		this.last_upd_date = last_upd_date;
	}

	public Character getData_status() {
		return data_status;
	}

	public void setData_status(Character data_status) {
		this.data_status = data_status;
	}
    @Transient
	public Set<TzsbAppOutsour> getTzsbAppOutsours() {
		return tzsbAppOutsours;
	}

	public void setTzsbAppOutsours(Set<TzsbAppOutsour> tzsbAppOutsours) {
		this.tzsbAppOutsours = tzsbAppOutsours;
	}
    @Transient
	public TzsbAppConstrucationOrg getTzsbAppConstrucationOrg() {
		return tzsbAppConstrucationOrg;
	}

	public void setTzsbAppConstrucationOrg(
			TzsbAppConstrucationOrg tzsbAppConstrucationOrg) {
		this.tzsbAppConstrucationOrg = tzsbAppConstrucationOrg;
	}

}
