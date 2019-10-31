package com.lsts.report.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "Report_Draw_Prepared_Qrcode")
public class ReportDrawPreparedQrcode implements BaseEntity{
	
	String id;//二维码
	String report_ids;//关联报告id 多个
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReport_ids() {
		return report_ids;
	}
	public void setReport_ids(String report_ids) {
		this.report_ids = report_ids;
	}
}
