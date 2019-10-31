package com.lsts.report.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.khnt.core.crud.bean.BaseEntity;

/*******************************************************************************
 * 
 * <p>
 *  
 * </p>
 * 
 * @ClassName BaseReportsItem
 * @JDK 1.5
 * @author CODER_V1.0
 * @date 2014-12-18 13:49:35
 */
@Entity
@Table(name = "BASE_REPORT_MENU_CONOFIG")
public class BaseReportsMenuConfig implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private String id;//ID
	private String fkReportsId;//报告ID
	private String reportMenuCode;//报告目录code
	private String reportMenuName;//报告目录名称
	private String recordMenuCode;//原始记录code
	private String recordMenuName;//原始记录名称

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}
	
	@Column(name="FK_REPORT_ID")
	public String getFkReportsId(){
		return fkReportsId;
	}
		
	public void setFkReportsId(String fkReportsId){
	this.fkReportsId=fkReportsId;
	}

	
	@Column(name="REPORT_MENU_CODE")
	public String getReportMenuCode() {
		return reportMenuCode;
	}

	public void setReportMenuCode(String reportMenuCode) {
		this.reportMenuCode = reportMenuCode;
	}
	@Column(name="REPORT_MENU_NAME")
	public String getReportMenuName() {
		return reportMenuName;
	}

	public void setReportMenuName(String reportMenuName) {
		this.reportMenuName = reportMenuName;
	}
	@Column(name="RECORD_MENU_CODE")
	public String getRecordMenuCode() {
		return recordMenuCode;
	}

	public void setRecordMenuCode(String recordMenuCode) {
		this.recordMenuCode = recordMenuCode;
	}
	@Column(name="RECORD_MENU_NAME")
	public String getRecordMenuName() {
		return recordMenuName;
	}

	public void setRecordMenuName(String recordMenuName) {
		this.recordMenuName = recordMenuName;
	}

	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "未说明";

	}
}
