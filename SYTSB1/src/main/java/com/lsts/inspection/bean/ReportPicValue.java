package com.lsts.inspection.bean;


import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;


/*******************************************************************************
 * 
 * 
 * 
 * 
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "TZSB_REPORT_PIC_VALUE")
public class ReportPicValue implements BaseEntity{
	
	private static final long serialVersionUID = 1L;
	private String id; // ID
	private String business_id; // 业务ID
	private byte[] pic_blob; // 图片内容
	private String pic_clob; // 图片内容
	private String type; // 类型B-BLOB  C-CLOB
	private Date upload_date; // 上传日期
	private String item_name; //属性名
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBusiness_id() {
		return business_id;
	}
	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}
	
	@Column(name = "pic_blob", columnDefinition = "BLOB",nullable=true)
	public byte[] getPic_blob() {
		return pic_blob;
	}
	public void setPic_blob(byte[] pic_blob) {
		this.pic_blob = pic_blob;
	}
	
	@Column(name="pic_clob", columnDefinition="CLOB", nullable=true)
	public String getPic_clob() {
		return pic_clob;
	}
	public void setPic_clob(String pic_clob) {
		this.pic_clob = pic_clob;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	
	
}
