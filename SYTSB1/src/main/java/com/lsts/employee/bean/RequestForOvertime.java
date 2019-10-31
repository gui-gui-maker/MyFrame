
package com.lsts.employee.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.JSONObject;
import com.khnt.core.crud.bean.BaseEntity;

/**********************************************************
 * @author WSL
 * @date 创建时间：2017年12月19日 上午10:26:33
 * @version 1.0
 ***********************************************************/

@Entity
@Table(name = "TJY2_REQUEST_FOR_OVERTIME")
public class RequestForOvertime implements BaseEntity {

	private static final long serialVersionUID = 1L;

	
	private String handle_id;// 业务所在人员ID
	private String overtime_type;// 加班种类
	private Date overtime_date_start;// 加班开始时间
	private String personnel_audit;// 人事流程审核人
	private String personnel_audit_id;// 人事ID
	private String leader_audit_remark;// 分管领导审核备注
	private String overtime_reason;// 加班原因
	private String handle_op;// 业务所在人员
	private String flow_step;// 流程（0 编制 1部长审核 2 人事批准 3 分管领导审核 4 院长审核 5 通过 6 不通过）
	private String department;// 所在部门
	private String leader_audit;// 分管领导审核流程审核人
	private String applicants_id;// 加班申请人ID
	private String overtime_place;// 加班地点
	private String dean_audit;// 院长审核流程审核人
	private String id;// 主键ID
	private String applicants;// 申请人
	private String dean_audit_remark;// 院长审核备注
	private String dean_id;// 院长ID
	private Date overtime_date_end;// 加班结束时间
	private String overtime_day;// 总共加班天数
	private String minister_audit;// 部长审核流程审核人
	private String leader_audit_op;// 分管领导ID
	private String department_id;// 所在部门的ID
	private String minister_audit_remark;// 部长审核备注
	private String minister_audit_id;// 部长ID
	private String personnel_audit_remark;// 人事审核备注
	private String overtime_hour;// 加班小时
	private String data_status;// 数据状态（1正常 99 删除）
	private String enter_op;//编制人
	private String enter_op_id;//编制人ID
	private Date enter_time;//编制时间
	private Date minister_audit_time;//部长审核时间
	private Date personnel_audit_time;//人事审核时间
	private Date leader_audit_time;//分管领导审核时间
	private Date dean_audit_time;//院长审核时间
	private String sub_status;//提交状态
	private String start_flow;//流程开始状态
	private String other_applicants_id;//其他加班申请人ID
	private String other_applicants;//其他加班申请人
	private String role_flag;//角色标记
	private String role_status;//用于标记普通人员的流程状态 0 正常流程（不用院长审核） 1（需要院长审核）
	
	 private String sn;//编号 自定义的，目前以申请日期为规则20180101001
	 
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHandle_id() {
		return handle_id;
	}

	public void setHandle_id(String handle_id) {
		this.handle_id = handle_id;
	}

	public String getOvertime_type() {
		return overtime_type;
	}

	public void setOvertime_type(String overtime_type) {
		this.overtime_type = overtime_type;
	}

	public Date getOvertime_date_start() {
		return overtime_date_start;
	}

	public void setOvertime_date_start(Date overtime_date_start) {
		this.overtime_date_start = overtime_date_start;
	}

	public String getPersonnel_audit() {
		return personnel_audit;
	}

	public void setPersonnel_audit(String personnel_audit) {
		this.personnel_audit = personnel_audit;
	}

	public String getPersonnel_audit_id() {
		return personnel_audit_id;
	}

	public void setPersonnel_audit_id(String personnel_audit_id) {
		this.personnel_audit_id = personnel_audit_id;
	}

	public String getLeader_audit_remark() {
		return leader_audit_remark;
	}

	public void setLeader_audit_remark(String leader_audit_remark) {
		this.leader_audit_remark = leader_audit_remark;
	}

	public String getOvertime_reason() {
		return overtime_reason;
	}

	public void setOvertime_reason(String overtime_reason) {
		this.overtime_reason = overtime_reason;
	}

	public String getHandle_op() {
		return handle_op;
	}

	public void setHandle_op(String handle_op) {
		this.handle_op = handle_op;
	}

	public String getFlow_step() {
		return flow_step;
	}

	public void setFlow_step(String flow_step) {
		this.flow_step = flow_step;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getLeader_audit() {
		return leader_audit;
	}

	public void setLeader_audit(String leader_audit) {
		this.leader_audit = leader_audit;
	}

	public String getApplicants_id() {
		return applicants_id;
	}

	public void setApplicants_id(String applicants_id) {
		this.applicants_id = applicants_id;
	}

	public String getOvertime_place() {
		return overtime_place;
	}

	public void setOvertime_place(String overtime_place) {
		this.overtime_place = overtime_place;
	}

	public String getDean_audit() {
		return dean_audit;
	}

	public void setDean_audit(String dean_audit) {
		this.dean_audit = dean_audit;
	}

	public String getApplicants() {
		return applicants;
	}

	public void setApplicants(String applicants) {
		this.applicants = applicants;
	}

	public String getDean_audit_remark() {
		return dean_audit_remark;
	}

	public void setDean_audit_remark(String dean_audit_remark) {
		this.dean_audit_remark = dean_audit_remark;
	}

	public String getDean_id() {
		return dean_id;
	}

	public void setDean_id(String dean_id) {
		this.dean_id = dean_id;
	}

	public Date getOvertime_date_end() {
		return overtime_date_end;
	}

	public void setOvertime_date_end(Date overtime_date_end) {
		this.overtime_date_end = overtime_date_end;
	}

	public String getOvertime_day() {
		return overtime_day;
	}

	public void setOvertime_day(String overtime_day) {
		this.overtime_day = overtime_day;
	}

	public String getMinister_audit() {
		return minister_audit;
	}

	public void setMinister_audit(String minister_audit) {
		this.minister_audit = minister_audit;
	}

	public String getLeader_audit_op() {
		return leader_audit_op;
	}

	public void setLeader_audit_op(String leader_audit_op) {
		this.leader_audit_op = leader_audit_op;
	}

	public String getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}

	public String getMinister_audit_remark() {
		return minister_audit_remark;
	}

	public void setMinister_audit_remark(String minister_audit_remark) {
		this.minister_audit_remark = minister_audit_remark;
	}

	public String getMinister_audit_id() {
		return minister_audit_id;
	}

	public void setMinister_audit_id(String minister_audit_id) {
		this.minister_audit_id = minister_audit_id;
	}

	public String getPersonnel_audit_remark() {
		return personnel_audit_remark;
	}

	public void setPersonnel_audit_remark(String personnel_audit_remark) {
		this.personnel_audit_remark = personnel_audit_remark;
	}

	public String getOvertime_hour() {
		return overtime_hour;
	}

	public void setOvertime_hour(String overtime_hour) {
		this.overtime_hour = overtime_hour;
	}

	public String getData_status() {
		return data_status;
	}

	public void setData_status(String data_status) {
		this.data_status = data_status;
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
	public Date getMinister_audit_time() {
		return minister_audit_time;
	}

	public void setMinister_audit_time(Date minister_audit_time) {
		this.minister_audit_time = minister_audit_time;
	}

	public Date getPersonnel_audit_time() {
		return personnel_audit_time;
	}

	public void setPersonnel_audit_time(Date personnel_audit_time) {
		this.personnel_audit_time = personnel_audit_time;
	}

	public Date getLeader_audit_time() {
		return leader_audit_time;
	}

	public void setLeader_audit_time(Date leader_audit_time) {
		this.leader_audit_time = leader_audit_time;
	}

	public Date getDean_audit_time() {
		return dean_audit_time;
	}

	public void setDean_audit_time(Date dean_audit_time) {
		this.dean_audit_time = dean_audit_time;
	}
	public String getSub_status() {
		return sub_status;
	}

	public void setSub_status(String sub_status) {
		this.sub_status = sub_status;
	}
	

	public String getStart_flow() {
		return start_flow;
	}

	public void setStart_flow(String start_flow) {
		this.start_flow = start_flow;
	}

	public String getRole_flag() {
		return role_flag;
	}

	public void setRole_flag(String role_flag) {
		this.role_flag = role_flag;
	}

	public String getOther_applicants_id() {
		return other_applicants_id;
	}

	public void setOther_applicants_id(String other_applicants_id) {
		this.other_applicants_id = other_applicants_id;
	}

	public String getOther_applicants() {
		return other_applicants;
	}

	public void setOther_applicants(String other_applicants) {
		this.other_applicants = other_applicants;
	}

	public String getRole_status() {
		return role_status;
	}

	public void setRole_status(String role_status) {
		this.role_status = role_status;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("申请人", applicants);// 申请人
		json.put("加班人", other_applicants);
		json.put("申请部门", department);// 所在部门
		json.put("加班种类", overtime_type) ;// 加班种类
		json.put("加班原因", overtime_reason);// 加班原因
		
		json.put("加班地点", overtime_place);// 加班地点
		
		
		
		json.put("开始时间", overtime_date_start);// 加班开始时间
		
		json.put("结束时间", overtime_date_end);// 加班结束时间
		json.put("加班天数", overtime_day);// 总共加班天数
		
		json.put("加班小时", overtime_hour);// 加班小时
		
		return json;
	}
	
	public String toString() {
		
		String beanString = "{";
		
		beanString = beanString + "\"申请人\":\""+applicants==null?"":applicants+"\",";
		
		beanString = beanString + "\"加班人\":\""+other_applicants==null?"":other_applicants+"\",";
		beanString = beanString + "\"申请部门\":\""+department==null?"":department+"\",";
		beanString = beanString + "\"加班种类\":\""+overtime_type==null?"":overtime_type+"\",";
		beanString = beanString + "\"加班原因\":\""+overtime_reason==null?"":overtime_reason+"\",";

		beanString = beanString + "\"加班地点\":\""+overtime_place==null?"":overtime_place+"\",";
		beanString = beanString + "\"开始时间\":\""+overtime_date_start==null?"":overtime_date_start+"\",";
		beanString = beanString + "\"结束时间\":\""+overtime_date_end==null?"":overtime_date_end+"\",";
		beanString = beanString + "\"加班天数\":\""+overtime_day==null?"":overtime_day+"\",";
		beanString = beanString + "\"加班小时\":\""+overtime_hour==null?"":overtime_hour+"\"";
		
		beanString = beanString + "}";

		
		return beanString;
	}
	
}
