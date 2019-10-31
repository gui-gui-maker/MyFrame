package com.khnt.rtbox.config.bean;

import com.khnt.core.crud.bean.BaseEntity;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "rt_marks")
public class ReportMark implements BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String fkBusinessId;
	private String item;//字段名
	private String itemValue;//字段值
	private String originalContent;//其实就是itemValue,只是录入阶段改变了itemValue这也不会变
	private String page;//報表頁碼
	private String markType;//标注类型
	private String markContent;//标注内容
	private String markById;//标注者
	private String markBy;//标注者
	private Date markTime;//标注时间
	private String editById;//处理人
	private String editBy;//处理人
	private Date editTime;//处理时间
	private String status;//标注状态
	private String isDel;//是否删除
	private String delBy;
	private String delById;
	private Date delTime;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="MARK_TYPE")
	public String getMarkType() {
		return markType;
	}
	public void setMarkType(String markType) {
		this.markType = markType;
	}
	@Column(name="ORIGINAL_CONTENT")
	public String getOriginalContent() {
		return originalContent;
	}
	public void setOriginalContent(String originalContent) {
		this.originalContent = originalContent;
	}
	@Column(name="MARK_CONTENT")
	public String getMarkContent() {
		return markContent;
	}
	public void setMarkContent(String markContent) {
		this.markContent = markContent;
	}
	@Column(name="MARK_BY")
	public String getMarkBy() {
		return markBy;
	}
	public void setMarkBy(String markBy) {
		this.markBy = markBy;
	}
	@Column(name="MARK_TIME")
	public Date getMarkTime() {
		return markTime;
	}
	public void setMarkTime(Date markTime) {
		this.markTime = markTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="EDIT_BY")
	public String getEditBy() {
		return editBy;
	}
	public void setEditBy(String editBy) {
		this.editBy = editBy;
	}
	@Column(name="EDIT_TIME")
	public Date getEditTime() {
		return editTime;
	}
	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	@Column(name="ITEM_VALUE")
	public String getItemValue() {
		return itemValue;
	}
	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}
	@Column(name="IS_DEL")
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	@Column(name="MARK_BY_ID")
	public String getMarkById() {
		return markById;
	}
	
	public void setMarkById(String markById) {
		this.markById = markById;
	}
	@Column(name="EDIT_BY_ID")
	public String getEditById() {
		return editById;
	}
	public void setEditById(String editById) {
		this.editById = editById;
	}
	@Column(name="FK_BUSINESS_ID")
	public String getFkBusinessId() {
		return fkBusinessId;
	}
	public void setFkBusinessId(String fkBusinessId) {
		this.fkBusinessId = fkBusinessId;
	}
	@Column(name="DEL_BY")
	public String getDelBy() {
		return delBy;
	}
	public void setDelBy(String delBy) {
		this.delBy = delBy;
	}
	@Column(name="DEL_BY_ID")
	public String getDelById() {
		return delById;
	}
	public void setDelById(String delById) {
		this.delById = delById;
	}
	@Column(name="DEL_TIME")
	public Date getDelTime() {
		return delTime;
	}
	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}
	

}
