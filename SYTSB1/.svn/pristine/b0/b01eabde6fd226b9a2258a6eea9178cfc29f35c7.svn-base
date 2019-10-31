package com.khnt.rtbox.print.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import com.khnt.core.crud.bean.BaseEntity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import java.util.Date;

/*******************************************************************************
 * 
 * <p>
 * 
 * </p>
 * 
 * @ClassName PrintPdfLog
 * @JDK 1.7
 * @author CODER_V3.0
 * @date 2018-07-27 14:53:37
 */
@Entity
@Table(name = "RT_PRINT_PDF_LOG")
@JsonIgnoreProperties(value = { "printPdfTask" })
public class PrintPdfLog implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;//
	private PrintPdfTask printPdfTask;
	private String fkInspectionId;// 报告ID
	private String reportSn;// 报告编号
	private String pageNo;// 报告分页编号
	private String pageUrl;// 报告URL
	private String pagePath;// 报告保存地址
	private String result;// 打印结果
	private String errorType;// 错误类别
	private String log;// 打印日志
	private Date printTime;// 打印时间
	private String printBatch;// 打印批次

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_TASK_ID")
	public PrintPdfTask getPrintPdfTask() {
		return this.printPdfTask;
	}

	public void setPrintPdfTask(PrintPdfTask printPdfTask) {
		this.printPdfTask = printPdfTask;
	}

	@Column(name = "FK_INSPECTION_ID")
	public String getFkInspectionId() {
		return fkInspectionId;
	}

	public void setFkInspectionId(String fkInspectionId) {
		this.fkInspectionId = fkInspectionId;
	}

	@Column(name = "REPORT_SN")
	public String getReportSn() {
		return reportSn;
	}

	public void setReportSn(String reportSn) {
		this.reportSn = reportSn;
	}

	@Column(name = "PAGE_NO")
	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	@Column(name = "PAGE_URL")
	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	@Column(name = "PAGE_PATH")
	public String getPagePath() {
		return pagePath;
	}

	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}

	@Column(name = "RESULT")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Column(name = "ERROR_TYPE")
	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	@Column(name = "LOG")
	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	@Column(name = "PRINT_TIME")
	public Date getPrintTime() {
		return printTime;
	}

	public void setPrintTime(Date printTime) {
		this.printTime = printTime;
	}

	@Column(name = "PRINT_BATCH")
	public String getPrintBatch() {
		return printBatch;
	}

	public void setPrintBatch(String printBatch) {
		this.printBatch = printBatch;
	}

	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "RT_PRINT_PDF_LOG:ID=" + id;

	}
}
