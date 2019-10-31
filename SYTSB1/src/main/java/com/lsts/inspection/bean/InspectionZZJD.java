package com.lsts.inspection.bean;


import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;


/**
 * 批量报检业务主表
 * 
 * @ClassName InspectionZZJD
 * @JDK 1.6
 * @author GaoYa
 * @date 2015-01-12 下午02:27:00
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "tzsb_inspection_zzjd")
public class InspectionZZJD implements BaseEntity{
	
	private static final long serialVersionUID = 1L;
	private String	id	;	//	ID
	private String  sn;				// 业务流水号
	private String  jdjy_type;		// 报告类别
	private String  check_type;		// 检验类别（数据字典：BASE_CHECK_TYPE）
	private String  report_type;	// 报告类型ID
	private String  file_path;		// 上传文件路径
	private String	enter_op_id;	// 起草人ID
	private String	enter_op_name;	// 起草人姓名
	private Date	enter_date;		// 起草时间
	private Date	commit_date;	// 提交时间
	private String  data_status;	// 数据状态(1：正常 99：已删除)

	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Collection<InspectionZZJDInfo> inspectionZZJDInfo;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "inspectionZZJD", orphanRemoval = true)
	public Collection<InspectionZZJDInfo> getInspectionZZJDInfo() {
		return inspectionZZJDInfo;
	}

	public void setInspectionZZJDInfo(Collection<InspectionZZJDInfo> inspectionZZJDInfo) {
		this.inspectionZZJDInfo = inspectionZZJDInfo;
	}
	
	public String getEnter_op_id() {
		return enter_op_id;
	}
	public void setEnter_op_id(String enter_op_id) {
		this.enter_op_id = enter_op_id;
	}
	public String getEnter_op_name() {
		return enter_op_name;
	}
	public void setEnter_op_name(String enter_op_name) {
		this.enter_op_name = enter_op_name;
	}
	public Date getEnter_date() {
		return enter_date;
	}
	public void setEnter_date(Date enter_date) {
		this.enter_date = enter_date;
	}
	public Date getCommit_date() {
		return commit_date;
	}
	public void setCommit_date(Date commit_date) {
		this.commit_date = commit_date;
	}
	public String getData_status() {
		return data_status;
	}
	public void setData_status(String data_status) {
		this.data_status = data_status;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getReport_type() {
		return report_type;
	}
	public void setReport_type(String report_type) {
		this.report_type = report_type;
	}
	public String getJdjy_type() {
		return jdjy_type;
	}
	public void setJdjy_type(String jdjy_type) {
		this.jdjy_type = jdjy_type;
	}
	public String getCheck_type() {
		return check_type;
	}
	public void setCheck_type(String check_type) {
		this.check_type = check_type;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
}
