package com.lsts.hall.bean;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * @author Mr.Dawn
 * @date 2014-12-22
 * @summary 大厅报检参数
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true, value = { "reportHall" })
@Table(name = "TZSB_INSPECTION_HALL_PARA")
public class ReportHallPara implements BaseEntity {

	private static final long serialVersionUID = 1L;	
		private String	id	;	//	
//		private String	fk_hall_id	;	// 报检大厅ID
		private String	unit_code	;	// 流转科室代码
		private String	unit_nam	;	// 流转科室名称
		private String	device_type	;	// 设备类型
		private String device_name ;	// 设备名称
		private String	device_no	;	// 设备数量
		private String	remark	;		// 备注
		private String	is_rec	;		// 是否报检
		private String	inc_op_id;		// 项目负责人id
		private String	check_op_ids;	// 参见人员id
		private String	op_name;		// 项目负责人
		private String	check_name;		// 参见人员
		private String	received_user_name;	// 接收人
		private Date	inc_time;			// 安排检验日期
		private String	is_plan;			// 是否分配
		private String	transfer_op;		// 转送人员
		private Date	transfer_date;   	// 转送日期
		private String  assign_user_id;		// 分配人ID
		private String  assign_user_name;	// 分配人姓名
		private Date    assign_time;		// 分配时间
		private String  charge_situation; 	// 收费情况
		private String  inspection_num; 	// 报检数量

		
		@Id
		@GeneratedValue(generator = "system-uuid")
		@GenericGenerator(name = "system-uuid", strategy = "uuid")
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
		
		private ReportHall reportHall;

		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "fk_hall_id")
		public ReportHall getReportHall() {
			return reportHall;
		}
		public void setReportHall(ReportHall reportHall) {
			this.reportHall = reportHall;
		}
		
//		public String getFk_hall_id() {
//			return fk_hall_id;
//		}
//		public void setFk_hall_id(String fk_hall_id) {
//			this.fk_hall_id = fk_hall_id;
//		}
		public String getUnit_code() {
			return unit_code;
		}
		public void setUnit_code(String unit_code) {
			this.unit_code = unit_code;
		}
		public String getUnit_nam() {
			return unit_nam;
		}
		public void setUnit_nam(String unit_nam) {
			this.unit_nam = unit_nam;
		}
		public String getDevice_type() {
			return device_type;
		}
		public void setDevice_type(String device_type) {
			this.device_type = device_type;
		}
		public String getDevice_name() {
			return device_name;
		}
		public void setDevice_name(String device_name) {
			this.device_name = device_name;
		}
		public String getDevice_no() {
			return device_no;
		}
		public void setDevice_no(String device_no) {
			this.device_no = device_no;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getIs_rec() {
			return is_rec;
		}
		public void setIs_rec(String is_rec) {
			this.is_rec = is_rec;
		}
		public String getInc_op_id() {
			return inc_op_id;
		}
		public void setInc_op_id(String inc_op_id) {
			this.inc_op_id = inc_op_id;
		}
		public String getCheck_op_ids() {
			return check_op_ids;
		}
		public void setCheck_op_ids(String check_op_ids) {
			this.check_op_ids = check_op_ids;
		}
		public String getIs_plan() {
			return is_plan;
		}
		public void setIs_plan(String is_plan) {
			this.is_plan = is_plan;
		}
		public String getTransfer_op() {
			return transfer_op;
		}
		public void setTransfer_op(String transfer_op) {
			this.transfer_op = transfer_op;
		}
		public Date getTransfer_date() {
			return transfer_date;
		}
		public Date getInc_time() {
			return inc_time;
		}
		public void setInc_time(Date inc_time) {
			this.inc_time = inc_time;
		}
		public void setTransfer_date(Date transfer_date) {
			this.transfer_date = transfer_date;
		}
		public String getCharge_situation() {
			return charge_situation;
		}
		public void setCharge_situation(String charge_situation) {
			this.charge_situation = charge_situation;
		}
		public String getOp_name() {
			return op_name;
		}
		public void setOp_name(String op_name) {
			this.op_name = op_name;
		}
		public String getCheck_name() {
			return check_name;
		}
		public void setCheck_name(String check_name) {
			this.check_name = check_name;
		}
		public String getInspection_num() {
			return inspection_num;
		}
		public void setInspection_num(String inspection_num) {
			this.inspection_num = inspection_num;
		}
		public String getReceived_user_name() {
			return received_user_name;
		}
		public void setReceived_user_name(String received_user_name) {
			this.received_user_name = received_user_name;
		}
		public String getAssign_user_id() {
			return assign_user_id;
		}
		public void setAssign_user_id(String assign_user_id) {
			this.assign_user_id = assign_user_id;
		}
		public String getAssign_user_name() {
			return assign_user_name;
		}
		public void setAssign_user_name(String assign_user_name) {
			this.assign_user_name = assign_user_name;
		}
		public Date getAssign_time() {
			return assign_time;
		}
		public void setAssign_time(Date assign_time) {
			this.assign_time = assign_time;
		}


}
