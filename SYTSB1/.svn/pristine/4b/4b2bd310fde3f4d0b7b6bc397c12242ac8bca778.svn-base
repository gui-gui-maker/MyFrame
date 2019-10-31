package com.scts.payment.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;


@Entity
@Table(name = "TZSB_INSPECTION_PAY_SIGN")
@JsonIgnoreProperties(ignoreUnknown = true)
public class InspectionPaySign implements BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String fk_pay_info_id; // 收费信息id
	private String dept_head_id; // 部门负责人
	private String dept_head;// 部门负责人
	private String checker_id;// 检验人员
	private String checker;// 检验人员
	private String brokerage_id;// 经办人
	private String brokerage;// 经办人
	private String brokerage_tel;// 经办人电话
	private Date sign_date;// 日期
	private String base64_sign_file;// 签名文件
	private String note;// 备注
	
	private String base64_text;//保存图片字符串，不保存在数据库中
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFk_pay_info_id() {
		return fk_pay_info_id;
	}

	public void setFk_pay_info_id(String fk_pay_info_id) {
		this.fk_pay_info_id = fk_pay_info_id;
	}

	public String getDept_head_id() {
		return dept_head_id;
	}

	public void setDept_head_id(String dept_head_id) {
		this.dept_head_id = dept_head_id;
	}

	public String getChecker_id() {
		return checker_id;
	}

	public void setChecker_id(String checker_id) {
		this.checker_id = checker_id;
	}

	public String getDept_head() {
		return dept_head;
	}

	public void setDept_head(String dept_head) {
		this.dept_head = dept_head;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public String getBrokerage() {
		return brokerage;
	}

	public void setBrokerage(String brokerage) {
		this.brokerage = brokerage;
	}

	public String getBrokerage_id() {
		return brokerage_id;
	}

	public void setBrokerage_id(String brokerage_id) {
		this.brokerage_id = brokerage_id;
	}

	public String getBrokerage_tel() {
		return brokerage_tel;
	}

	public void setBrokerage_tel(String brokerage_tel) {
		this.brokerage_tel = brokerage_tel;
	}

	public Date getSign_date() {
		return sign_date;
	}

	public void setSign_date(Date sign_date) {
		this.sign_date = sign_date;
	}

	public String getBase64_sign_file() {
		return base64_sign_file;
	}

	public void setBase64_sign_file(String base64_sign_file) {
		this.base64_sign_file = base64_sign_file;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	@Transient
	public String getBase64_text() {
		return base64_text;
	}

	public void setBase64_text(String base64_text) {
		this.base64_text = base64_text;
	}

}
