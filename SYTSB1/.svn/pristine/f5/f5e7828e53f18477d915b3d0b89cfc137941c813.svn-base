package com.lsts.report.bean;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;


/**
 * 报告书编号作废编号记录表
 * 
 * @ClassName ReportSnDelCode
 * @JDK 1.7
 * @author GaoYa
 * @date 2017-05-06 下午03:10:00
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "TZSB_DEL_CODE")
public class ReportSnDelCode implements BaseEntity{
	
	private static final long serialVersionUID = 1L;
	private String	id	;			// ID
	private String report_type;		// 报告类型编号
	private String report_sn;      	// 报告书编号
	

	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReport_type() {
		return report_type;
	}
	public void setReport_type(String report_type) {
		this.report_type = report_type;
	}
	public String getReport_sn() {
		return report_sn;
	}
	public void setReport_sn(String report_sn) {
		this.report_sn = report_sn;
	}
	
}
