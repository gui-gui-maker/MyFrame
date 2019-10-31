package com.lsts.mobileapp.input.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name="TZSB_INSPECTION_RECORD_DIR")
public class InspectRecordDir implements BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id ;
	//原始记录id
	private String fkRecordId; 
	//页面code
	private String pageCode; 
	private String pageName; 
	private String pagePath; 
	//页面序号
	private Integer orders; 
	//复制页标记
	private String copyFlag; 
	//被复制次数
	private String copyTimes; 
	// 最后修改用户ID
	private String enterOpId;	
	// 最后修改用户姓名
	private String enterOpName; 
	// 最后修改时间 
	private Date enterTime;  
	// 数据状态，默认0（0：未生成报告  99：已删除）
	private String dataStatus;	
	//录入标记，是否录入过 0 未录入 1已录入	
	private String inputFlag;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name="FK_RECORD_ID")
	public String getFkRecordId() {
		return fkRecordId;
	}
	public void setFkRecordId(String fkRecordId) {
		this.fkRecordId = fkRecordId;
	}
	@Column(name="PAGE_CODE")
	public String getPageCode() {
		return pageCode;
	}
	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}
	@Column(name="PAGE_NAME")
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	@Column(name="ORDERS")
	public Integer getOrders() {
		return orders;
	}
	public void setOrders(Integer orders) {
		this.orders = orders;
	}
	@Column(name="COPY_FLAG")
	public String getCopyFlag() {
		return copyFlag;
	}
	public void setCopyFlag(String copyFlag) {
		this.copyFlag = copyFlag;
	}
	@Column(name="COPY_TIMES")
	public String getCopyTimes() {
		return copyTimes;
	}
	public void setCopyTimes(String copyTimes) {
		this.copyTimes = copyTimes;
	}
	@Column(name="ENTER_OP_ID")
	public String getEnterOpId() {
		return enterOpId;
	}
	public void setEnterOpId(String enterOpId) {
		this.enterOpId = enterOpId;
	}
	@Column(name="ENTER_OP_NAME")
	public String getEnterOpName() {
		return enterOpName;
	}
	public void setEnterOpName(String enterOpName) {
		this.enterOpName = enterOpName;
	}
	@Column(name="ENTER_TIME")
	public Date getEnterTime() {
		return enterTime;
	}
	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
	}
	@Column(name="DATA_STATUS")
	public String getDataStatus() {
		return dataStatus;
	}
	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}
	@Column(name="PAGE_PATH")
	public String getPagePath() {
		return pagePath;
	}
	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}
	@Column(name="input_flag")
	public String getInputFlag() {
		return inputFlag;
	}
	public void setInputFlag(String inputFlag) {
		this.inputFlag = inputFlag;
	}
	
	
}
