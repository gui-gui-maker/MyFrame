package com.lsts.device.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/*******************************************************************************
 * 
 * 告知书提交的文件资料
 * 
 * @author liming 2014-5-9
 * 
 */

@Entity
@Table(name = "TZSB_APP_DOCUMENT_FILE")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TzsbAppDocumentFile implements BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7423552328412016611L;
	private String id;
	private String fk_tzsb_application_id;
	private String seq_num;
	private String file_name;
	private String page_num;
	private String remark;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFk_tzsb_application_id() {
		return fk_tzsb_application_id;
	}
	public void setFk_tzsb_application_id(String fk_tzsb_application_id) {
		this.fk_tzsb_application_id = fk_tzsb_application_id;
	}
	public String getSeq_num() {
		return seq_num;
	}
	public void setSeq_num(String seq_num) {
		this.seq_num = seq_num;
	}
	public String getPage_num() {
		return page_num;
	}
	public void setPage_num(String page_num) {
		this.page_num = page_num;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	
	

}
