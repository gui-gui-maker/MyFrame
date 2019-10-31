package com.lsts.employee.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;



@Entity
@Table(name = "TJY2_ALLOWANCEFO")
@JsonIgnoreProperties(ignoreUnknown = true, value = { "employeeallowance" })
public class Allowancefo implements BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String other_applicants_id;
	private String other_applicants;
	private String overtime_type;
	private String subsidy_money;
	
	private String take_date_start;
	private String take_date_end;
	private Employeeallowance employeeallowance;
	
	@Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=64)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getOther_applicants_id() {
		return other_applicants_id;
	}
	public void setOther_applicants_id(String other_applicants_id) {
		this.other_applicants_id = other_applicants_id;
	}
	public String getOther_applicants() {
		return other_applicants;
	}
	public void setOther_applicants(String other_applicants) {
		this.other_applicants = other_applicants;
	}
	public String getOvertime_type() {
		return overtime_type;
	}
	public void setOvertime_type(String overtime_type) {
		this.overtime_type = overtime_type;
	}
	public String getTake_date_start() {
		return take_date_start;
	}
	public void setTake_date_start(String take_date_start) {
		this.take_date_start = take_date_start;
	}
	public String getTake_date_end() {
		return take_date_end;
	}
	public void setTake_date_end(String take_date_end) {
		this.take_date_end = take_date_end;
	}
	public String getSubsidy_money() {
		return subsidy_money;
	}
	public void setSubsidy_money(String subsidy_money) {
		this.subsidy_money = subsidy_money;
	}
	
	 public String toString() {
			return "TJY2_ALLOWANCEFO:ID="+id;
		}

	 @ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "fk_plan_id")
		public Employeeallowance getEmployeeallowance() {
			return employeeallowance;
		}
		public void setEmployeeallowance(Employeeallowance employeeallowance) {
			this.employeeallowance = employeeallowance;
		}

}
