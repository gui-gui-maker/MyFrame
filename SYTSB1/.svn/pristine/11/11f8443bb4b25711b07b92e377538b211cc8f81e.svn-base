package com.khnt.rtbox.print.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/*******************************************************************************
 * 
 * <p>
 * 
 * </p>
 * 
 * @ClassName PrintPdfTask
 * @JDK 1.7
 * @author CODER_V3.0
 * @date 2018-07-27 14:53:37
 */
@Entity
@Table(name = "RT_PRINT_PDF_TASK")
public class PrintPdfTask implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;//
	private String fkInspectionInfoId;// 报告ID
	private String reportSn;// 报告编号
	private String reportType;// 报告类型
	private String reportTplId;// 报告模板ID
	private String reportTplNo;// 报告模板编号
	private String reportTplUrl;// 报告模板地址

	private String pdfAtt;// 报告附件ID
	private String reportDir;// 报告目录
	private String pdfPath;// 报告保存地址
	private Date createTime;// 创建时间
	private String status;// 任务状态
	private Date printTime;// 打印时间
	private Integer tryCount;// 尝试次数
	private String remark;// 备注
	private String printHost;// 打印主机

	private List<PrintPdfLog> printPdfLogs;

	private String inspectDate;// 检验日期 保存路径使用

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "FK_INSPECTION_INFO_ID")
	public String getFkInspectionInfoId() {
		return fkInspectionInfoId;
	}

	public void setFkInspectionInfoId(String fkInspectionInfoId) {
		this.fkInspectionInfoId = fkInspectionInfoId;
	}

	@Column(name = "REPORT_SN")
	public String getReportSn() {
		return reportSn;
	}

	public void setReportSn(String reportSn) {
		this.reportSn = reportSn;
	}

	@Column(name = "REPORT_TYPE")
	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	@Column(name = "REPORT_TPL_ID")
	public String getReportTplId() {
		return reportTplId;
	}

	public void setReportTplId(String reportTplId) {
		this.reportTplId = reportTplId;
	}

	@Column(name = "REPORT_TPL_NO")
	public String getReportTplNo() {
		return reportTplNo;
	}

	public void setReportTplNo(String reportTplNo) {
		this.reportTplNo = reportTplNo;
	}

	@Column(name = "REPORT_TPL_URL")
	public String getReportTplUrl() {
		return reportTplUrl;
	}

	public void setReportTplUrl(String reportTplUrl) {
		this.reportTplUrl = reportTplUrl;
	}

	@Column(name = "PDF_ATT")
	public String getPdfAtt() {
		return pdfAtt;
	}

	public void setPdfAtt(String pdfAtt) {
		this.pdfAtt = pdfAtt;
	}

	@Column(name = "PDF_PATH")
	public String getPdfPath() {
		return pdfPath;
	}

	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}

	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "PRINT_TIME")
	public Date getPrintTime() {
		return printTime;
	}

	public void setPrintTime(Date printTime) {
		this.printTime = printTime;
	}

	@Column(name = "TRY_COUNT")
	public Integer getTryCount() {
		return tryCount;
	}

	public void setTryCount(Integer tryCount) {
		this.tryCount = tryCount;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "printPdfTask")
	public List<PrintPdfLog> getPrintPdfLogs() {
		return printPdfLogs;
	}

	public void setPrintPdfLogs(List<PrintPdfLog> printPdfLogs) {
		this.printPdfLogs = printPdfLogs;
	}

	@Column(name = "REPORT_DIR")
	public String getReportDir() {
		return reportDir;
	}

	public void setReportDir(String reportDir) {
		this.reportDir = reportDir;
	}

	@Column(name = "INSPECT_DATE")
	public String getInspectDate() {
		return inspectDate;
	}

	public void setInspectDate(String inspectDate) {
		this.inspectDate = inspectDate;
	}

	@Column(name = "PRINT_HOST")
	public String getPrintHost() {
		return printHost;
	}

	public void setPrintHost(String printHost) {
		this.printHost = printHost;
	}

	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "RT_PRINT_PDF_TASK:ID=" + id;

	}
}
