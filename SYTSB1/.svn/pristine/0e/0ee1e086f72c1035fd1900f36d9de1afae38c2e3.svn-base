package com.scts.discipline.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name="TJY2_DISCIPLINE_PLAN")
public class DisciplinePlan implements BaseEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;		
	private Integer orders;		//序号
	private String unit;	//部门
	private String com_name;	//报检单位
	private String maintain_name;	//电梯维保单位
	private String contact_man;	//联系人
	private String phone_num;	//电话号码
	private String inspect_date;	//检验时间
	private String check_op;	//检验人员
	private String inspector_grade;	//检验检测工作服务满意程度
	private String self_discipline; 
	private String other_suggest;	//其他意见及建议
	private String enter_op;
	private String enter_op_id;	
	private Date enter_time;	//录入时间
	private String year;//年份
	private String month;//月份
	private String data_status;	//数据状态 0正常 99 删除
	private String flag;		//0 未回访 1已回访
	
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCom_name() {
		return com_name;
	}

	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}

	public String getMaintain_name() {
		return maintain_name;
	}

	public void setMaintain_name(String maintain_name) {
		this.maintain_name = maintain_name;
	}

	public String getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}

	public String getInspect_date() {
		return inspect_date;
	}

	public void setInspect_date(String inspect_date) {
		this.inspect_date = inspect_date;
	}

	public String getCheck_op() {
		return check_op;
	}

	public void setCheck_op(String check_op) {
		this.check_op = check_op;
	}

	public String getInspector_grade() {
		return inspector_grade;
	}

	public void setInspector_grade(String inspector_grade) {
		this.inspector_grade = inspector_grade;
	}

	public String getOther_suggest() {
		return other_suggest;
	}

	public void setOther_suggest(String other_suggest) {
		this.other_suggest = other_suggest;
	}

	public String getEnter_op() {
		return enter_op;
	}

	public void setEnter_op(String enter_op) {
		this.enter_op = enter_op;
	}

	public String getEnter_op_id() {
		return enter_op_id;
	}

	public void setEnter_op_id(String enter_op_id) {
		this.enter_op_id = enter_op_id;
	}

	public Date getEnter_time() {
		return enter_time;
	}

	public void setEnter_time(Date enter_time) {
		this.enter_time = enter_time;
	}

	public String getData_status() {
		return data_status;
	}

	public void setData_status(String data_status) {
		this.data_status = data_status;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getContact_man() {
		return contact_man;
	}

	public void setContact_man(String contact_man) {
		this.contact_man = contact_man;
	}

	public String getSelf_discipline() {
		return self_discipline;
	}

	public void setSelf_discipline(String self_discipline) {
		this.self_discipline = self_discipline;
	}

	
	
}
