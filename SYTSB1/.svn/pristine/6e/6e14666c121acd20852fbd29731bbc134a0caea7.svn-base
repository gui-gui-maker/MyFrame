package com.lsts.report.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/*******************************************************************************
 * 
 * 报告证书数据
 * 
 * @author 肖慈边 2014-1-24
 * 
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true, value = { "report" })
@Table(name = "base_reports_cert")
public class ReportCert implements BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String	id	;	//	ID
	
	private String	fk_reports_id	;	//	报告ID
	private String	cert_code;	//	证书编号
	private String	cert_name	;	//证书名称
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
//	public Report report;
//	
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "fk_reports_id")
//	public Report getReport() {
//		return report;
//	}
//
//	public void setReport(Report report) {
//		this.report = report;
//	}
//	
	
	public String getFk_reports_id() {
		return fk_reports_id;
	}
	public void setFk_reports_id(String fk_reports_id) {
		this.fk_reports_id = fk_reports_id;
	}
	public String getCert_code() {
		return cert_code;
	}
	public void setCert_code(String cert_code) {
		this.cert_code = cert_code;
	}
	public String getCert_name() {
		return cert_name;
	}
	public void setCert_name(String cert_name) {
		this.cert_name = cert_name;
	}
}
