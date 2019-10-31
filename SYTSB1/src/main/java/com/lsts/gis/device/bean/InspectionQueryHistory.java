package com.lsts.gis.device.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;
@SuppressWarnings("serial")
@Entity
@Table(name = "TZSB_INSPECTION_QUERY")
@JsonIgnoreProperties(ignoreUnknown=true)
public class InspectionQueryHistory implements BaseEntity{
	
	private String id;
	private String query_op_id;
	private String query_op;
	private Date query_time;
	private String query_type;    
	private String query_content;
	private Integer query_result;
	@Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQuery_op_id() {
		return query_op_id;
	}
	public void setQuery_op_id(String query_op_id) {
		this.query_op_id = query_op_id;
	}
	public String getQuery_op() {
		return query_op;
	}
	public void setQuery_op(String query_op) {
		this.query_op = query_op;
	}
	public Date getQuery_time() {
		return query_time;
	}
	public void setQuery_time(Date query_time) {
		this.query_time = query_time;
	}
	public String getQuery_type() {
		return query_type;
	}
	public void setQuery_type(String query_type) {
		this.query_type = query_type;
	}
	public String getQuery_content() {
		return query_content;
	}
	public void setQuery_content(String query_content) {
		this.query_content = query_content;
	}
	public Integer getQuery_result() {
		return query_result;
	}
	public void setQuery_result(Integer query_result) {
		this.query_result = query_result;
	}
	
	
	

}
