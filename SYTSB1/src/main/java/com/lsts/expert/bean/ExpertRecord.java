package com.lsts.expert.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name="TJY2_EXPERT_RECODE")
public class ExpertRecord implements BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7462511823908431709L;
	private String id;
	private Date create_date;//创建时间
	private String expert_type;
	private String expert_type_name; //专家组名字
	private Integer expert_num;  //机选专家数量
	private String status;
	private String code; //编号
	private String item_name;//项目名称
	private Date meeting_date;//会议时间
	private String meeting_addr;//会议地点
	private String jx_option;//机选操作
	private String jx_option_id;//
	private Integer candidate_num;//候选人数量
	private String nocome_op;//没来人员id
	private String nocome_op_name;//没来人员名字
	private String prevent_op;//代表人名字
	private String speak_op;//发言人名字
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getExpert_type() {
		return expert_type;
	}
	public void setExpert_type(String expert_type) {
		this.expert_type = expert_type;
	}
	public String getExpert_type_name() {
		return expert_type_name;
	}
	public void setExpert_type_name(String expert_type_name) {
		this.expert_type_name = expert_type_name;
	}
	public Integer getExpert_num() {
		return expert_num;
	}
	public void setExpert_num(Integer expert_num) {
		this.expert_num = expert_num;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public Date getMeeting_date() {
		return meeting_date;
	}
	public void setMeeting_date(Date meeting_date) {
		this.meeting_date = meeting_date;
	}
	public String getMeeting_addr() {
		return meeting_addr;
	}
	public void setMeeting_addr(String meeting_addr) {
		this.meeting_addr = meeting_addr;
	}
	public String getJx_option() {
		return jx_option;
	}
	public void setJx_option(String jx_option) {
		this.jx_option = jx_option;
	}
	public Integer getCandidate_num() {
		return candidate_num;
	}
	public void setCandidate_num(Integer candidate_num) {
		this.candidate_num = candidate_num;
	}
	public String getNocome_op() {
		return nocome_op;
	}
	public void setNocome_op(String nocome_op) {
		this.nocome_op = nocome_op;
	}
	public String getNocome_op_name() {
		return nocome_op_name;
	}
	public void setNocome_op_name(String nocome_op_name) {
		this.nocome_op_name = nocome_op_name;
	}
	public String getPrevent_op() {
		return prevent_op;
	}
	public void setPrevent_op(String prevent_op) {
		this.prevent_op = prevent_op;
	}
	public String getSpeak_op() {
		return speak_op;
	}
	public void setSpeak_op(String speak_op) {
		this.speak_op = speak_op;
	}
	public String getJx_option_id() {
		return jx_option_id;
	}
	public void setJx_option_id(String jx_option_id) {
		this.jx_option_id = jx_option_id;
	}
	
	

}
