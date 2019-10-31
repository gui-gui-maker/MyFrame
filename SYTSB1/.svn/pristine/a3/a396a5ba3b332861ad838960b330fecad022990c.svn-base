package com.scts.discipline.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;
@Entity
@Table(name="TJY2_DISCIPLINE_CALL")
public class DisciplineCall implements BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;			//	
	private String business_id;	//	报告id
	private String report_sn;		//	报告编号
	private String fk_com_id;		//	单位id
	private String com_name;	//	单位名称
	private String contact_man;		//	联系人
	private String phone_number;		//	呼叫号码
	private String call_id;			//	呼叫id
	private String call_mins;		//	呼叫时长
	private String create_op;		//	创建人
	private String create_op_id;		//	创建人id
	private Date create_date;		//呼叫时间
	private String call_type;		//	0 呼出 1呼入 2转呼
	private String phone;			//	分机号码
	private String is_connect;		//	是否接通 0 未接通 1接通
	private String other_phone;		//	转接转呼电话
	private String judge_grade;		//	评分
	private String data_status;		//	99 删除
	private String path;		//	录音文件位置
	private String content;		//	
	private String create_org_id;//检验部门id(该字段没有用)
	private String create_org_name;//检验部门name
	
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	public String getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}

	public String getReport_sn() {
		return report_sn;
	}

	public void setReport_sn(String report_sn) {
		this.report_sn = report_sn;
	}

	public String getFk_com_id() {
		return fk_com_id;
	}

	public void setFk_com_id(String fk_com_id) {
		this.fk_com_id = fk_com_id;
	}

	public String getCom_name() {
		return com_name;
	}

	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}

	public String getContact_man() {
		return contact_man;
	}

	public void setContact_man(String contact_man) {
		this.contact_man = contact_man;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getCall_id() {
		return call_id;
	}

	public void setCall_id(String call_id) {
		this.call_id = call_id;
	}

	public String getCall_mins() {
		return call_mins;
	}

	public void setCall_mins(String call_mins) {
		this.call_mins = call_mins;
	}

	public String getCreate_op() {
		return create_op;
	}

	public void setCreate_op(String create_op) {
		this.create_op = create_op;
	}

	public String getCreate_op_id() {
		return create_op_id;
	}

	public void setCreate_op_id(String create_op_id) {
		this.create_op_id = create_op_id;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getCall_type() {
		return call_type;
	}

	public void setCall_type(String call_type) {
		this.call_type = call_type;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIs_connect() {
		return is_connect;
	}

	public void setIs_connect(String is_connect) {
		this.is_connect = is_connect;
	}

	public String getOther_phone() {
		return other_phone;
	}

	public void setOther_phone(String other_phone) {
		this.other_phone = other_phone;
	}

	public String getJudge_grade() {
		return judge_grade;
	}

	public void setJudge_grade(String judge_grade) {
		this.judge_grade = judge_grade;
	}

	public String getData_status() {
		return data_status;
	}

	public void setData_status(String data_status) {
		this.data_status = data_status;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Transient
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreate_org_id() {
		return create_org_id;
	}

	public void setCreate_org_id(String create_org_id) {
		this.create_org_id = create_org_id;
	}

	public String getCreate_org_name() {
		return create_org_name;
	}

	public void setCreate_org_name(String create_org_name) {
		this.create_org_name = create_org_name;
	}

	
}
