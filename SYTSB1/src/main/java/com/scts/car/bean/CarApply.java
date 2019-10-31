package com.scts.car.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 公务用车申请
 * CarApply entity. 
 * @author GaoYa
 * @date 2018-06-27 上午11:12:00
 */
@Entity
@Table(name = "TZSB_CAR_APPLY")
public class CarApply implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;		// ID
	private String apply_sn;		// 申请编号（年月+3位序号）
	private String apply_dep_id;	// 申请部门ID
	private String apply_dep_name;	// 申请部门名称
	private String apply_user_id;	// 申请人ID
	private String apply_user_name;	// 申请人姓名
	private Date   apply_date;		// 申请时间/填报日期	
	
	private String use_dep_id;		// 用车部门ID
	private String use_dep_name;	// 用车部门名称
	private String use_user_id;		// 用车/联系人ID
	private String use_user_name;	// 用车/联系人姓名
	private String use_user_phone;	// 联系电话
	private Date   use_start_date;	// 申请用车开始时间
	private Date   use_end_date;	// 申请用车结束时间
	private Integer use_days;		// 用车天数
	private Integer passengers_count;	// 乘车人数
	private String  need_driver;	// 是否需要司机（0：否 1：是）默认否
	
	private String apply_reason;	// 用车申请原因/用车具体任务
	private String drive_route;		// 行驶路线
	private String destination;		// 目的地
	private String apply_remark;	// 申请备注
	
	private String dep_deal_id;		// 用车部门负责人ID
	private String dep_deal_name;	// 用车部门负责人姓名
	private String dep_deal_result;	// 用车部门负责人审核结果（同意 or 不同意，不同意时填写原因）
	private String dep_deal_remark;	// 用车部门负责人审核结果备注
	private Date   dep_deal_date;	// 用车部门负责人审核时间
	
	private String office_deal_id;		// 办公室负责人ID
	private String office_deal_name;	// 办公室负责人姓名
	private String office_deal_result;	// 办公室负责人审核结果（同意 or 不同意，不同意时填写原因）
	private String office_deal_remark;	// 办公室负责人审核结果备注
	private Date   office_deal_date;	// 办公室负责人审核时间
	
	private String leader_deal_id;		// 分管院领导ID
	private String leader_deal_name;	// 分管院领导姓名
	private String leader_deal_result;	// 分管院领导审核结果（同意 or 不同意，不同意时填写原因）
	private String leader_deal_remark;	// 分管院领导审核结果备注
	private Date   leader_deal_date;	// 分管院领导审核时间
	
	private String fleet_deal_id;		// 车队负责人ID
	private String fleet_deal_name;		// 车队负责人姓名
	private String fleet_deal_result;	// 车队负责人审核结果（同意 or 不同意，不同意时填写原因）
	private String fleet_deal_remark;	// 车队负责人审核结果备注
	private Date   fleet_deal_date;		// 车队负责人审核时间
	
	private String send_deal_id;		// 派车人ID
	private String send_deal_name;		// 派车人姓名
	private Date   send_date;			// 派车时间
	private String fk_car_id;			// 派用车辆ID
	private String plate_number;		// 派用车辆车牌号	
	
	private String start_km;			// 公里起数
	private String end_km;				// 公里止数
	private Date   out_date;			// 出车时间
	private Date   back_date;			// 返回时间
	
	private String driver_user_id;		// 驾驶员ID
	private String driver_user_name;	// 驾驶员姓名
	
	private String receive_deal_id;		// 收车人ID
	private String receive_deal_name;	// 收车人姓名
	private Date   receive_date;		// 收车时间
	
	private String return_user_id;		// 还车人ID
	private String return_user_name;	// 还车人姓名
	private Date   return_date;			// 还车时间

	private String data_status;			// 数据状态，数据字典（TZSB_CAR_APPLY_STATUS）（0：提交申请/用车部门负责人待审核 2：分管院领导待审核 3：车队负责人待审核 4：派车中  1：办公室负责人待审核  5：用车中 6：已收车 98：已退回 99：已作废）
	
	private String expenses;//是否报销0否、1是
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	public String getApply_sn() {
		return apply_sn;
	}

	public void setApply_sn(String apply_sn) {
		this.apply_sn = apply_sn;
	}

	public String getApply_dep_id() {
		return apply_dep_id;
	}

	public void setApply_dep_id(String apply_dep_id) {
		this.apply_dep_id = apply_dep_id;
	}

	public String getApply_dep_name() {
		return apply_dep_name;
	}

	public void setApply_dep_name(String apply_dep_name) {
		this.apply_dep_name = apply_dep_name;
	}

	public String getApply_user_id() {
		return apply_user_id;
	}

	public void setApply_user_id(String apply_user_id) {
		this.apply_user_id = apply_user_id;
	}

	public String getApply_user_name() {
		return apply_user_name;
	}

	public void setApply_user_name(String apply_user_name) {
		this.apply_user_name = apply_user_name;
	}

	public Date getApply_date() {
		return apply_date;
	}

	public void setApply_date(Date apply_date) {
		this.apply_date = apply_date;
	}

	public String getUse_dep_id() {
		return use_dep_id;
	}

	public void setUse_dep_id(String use_dep_id) {
		this.use_dep_id = use_dep_id;
	}

	public String getUse_dep_name() {
		return use_dep_name;
	}

	public void setUse_dep_name(String use_dep_name) {
		this.use_dep_name = use_dep_name;
	}

	public String getUse_user_id() {
		return use_user_id;
	}

	public void setUse_user_id(String use_user_id) {
		this.use_user_id = use_user_id;
	}

	public String getUse_user_name() {
		return use_user_name;
	}

	public void setUse_user_name(String use_user_name) {
		this.use_user_name = use_user_name;
	}

	public String getUse_user_phone() {
		return use_user_phone;
	}

	public void setUse_user_phone(String use_user_phone) {
		this.use_user_phone = use_user_phone;
	}

	public Date getUse_start_date() {
		return use_start_date;
	}

	public void setUse_start_date(Date use_start_date) {
		this.use_start_date = use_start_date;
	}

	public Date getUse_end_date() {
		return use_end_date;
	}

	public void setUse_end_date(Date use_end_date) {
		this.use_end_date = use_end_date;
	}

	public String getApply_reason() {
		return apply_reason;
	}

	public void setApply_reason(String apply_reason) {
		this.apply_reason = apply_reason;
	}

	public Date getOut_date() {
		return out_date;
	}

	public void setOut_date(Date out_date) {
		this.out_date = out_date;
	}

	public Date getBack_date() {
		return back_date;
	}

	public void setBack_date(Date back_date) {
		this.back_date = back_date;
	}

	public String getDrive_route() {
		return drive_route;
	}

	public void setDrive_route(String drive_route) {
		this.drive_route = drive_route;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDep_deal_id() {
		return dep_deal_id;
	}

	public void setDep_deal_id(String dep_deal_id) {
		this.dep_deal_id = dep_deal_id;
	}

	public String getDep_deal_name() {
		return dep_deal_name;
	}

	public void setDep_deal_name(String dep_deal_name) {
		this.dep_deal_name = dep_deal_name;
	}

	public String getDep_deal_result() {
		return dep_deal_result;
	}

	public void setDep_deal_result(String dep_deal_result) {
		this.dep_deal_result = dep_deal_result;
	}

	public String getDep_deal_remark() {
		return dep_deal_remark;
	}

	public void setDep_deal_remark(String dep_deal_remark) {
		this.dep_deal_remark = dep_deal_remark;
	}

	public Date getDep_deal_date() {
		return dep_deal_date;
	}

	public void setDep_deal_date(Date dep_deal_date) {
		this.dep_deal_date = dep_deal_date;
	}

	public String getOffice_deal_id() {
		return office_deal_id;
	}

	public void setOffice_deal_id(String office_deal_id) {
		this.office_deal_id = office_deal_id;
	}

	public String getOffice_deal_name() {
		return office_deal_name;
	}

	public void setOffice_deal_name(String office_deal_name) {
		this.office_deal_name = office_deal_name;
	}

	public String getOffice_deal_result() {
		return office_deal_result;
	}

	public void setOffice_deal_result(String office_deal_result) {
		this.office_deal_result = office_deal_result;
	}

	public String getOffice_deal_remark() {
		return office_deal_remark;
	}

	public void setOffice_deal_remark(String office_deal_remark) {
		this.office_deal_remark = office_deal_remark;
	}

	public Date getOffice_deal_date() {
		return office_deal_date;
	}

	public void setOffice_deal_date(Date office_deal_date) {
		this.office_deal_date = office_deal_date;
	}

	public String getLeader_deal_id() {
		return leader_deal_id;
	}

	public void setLeader_deal_id(String leader_deal_id) {
		this.leader_deal_id = leader_deal_id;
	}

	public String getLeader_deal_name() {
		return leader_deal_name;
	}

	public void setLeader_deal_name(String leader_deal_name) {
		this.leader_deal_name = leader_deal_name;
	}

	public String getLeader_deal_result() {
		return leader_deal_result;
	}

	public void setLeader_deal_result(String leader_deal_result) {
		this.leader_deal_result = leader_deal_result;
	}

	public String getLeader_deal_remark() {
		return leader_deal_remark;
	}

	public void setLeader_deal_remark(String leader_deal_remark) {
		this.leader_deal_remark = leader_deal_remark;
	}

	public Date getLeader_deal_date() {
		return leader_deal_date;
	}

	public void setLeader_deal_date(Date leader_deal_date) {
		this.leader_deal_date = leader_deal_date;
	}

	public String getFleet_deal_id() {
		return fleet_deal_id;
	}

	public void setFleet_deal_id(String fleet_deal_id) {
		this.fleet_deal_id = fleet_deal_id;
	}

	public String getFleet_deal_name() {
		return fleet_deal_name;
	}

	public void setFleet_deal_name(String fleet_deal_name) {
		this.fleet_deal_name = fleet_deal_name;
	}

	public String getFleet_deal_result() {
		return fleet_deal_result;
	}

	public void setFleet_deal_result(String fleet_deal_result) {
		this.fleet_deal_result = fleet_deal_result;
	}

	public String getFleet_deal_remark() {
		return fleet_deal_remark;
	}

	public void setFleet_deal_remark(String fleet_deal_remark) {
		this.fleet_deal_remark = fleet_deal_remark;
	}

	public Date getFleet_deal_date() {
		return fleet_deal_date;
	}

	public void setFleet_deal_date(Date fleet_deal_date) {
		this.fleet_deal_date = fleet_deal_date;
	}

	public String getSend_deal_id() {
		return send_deal_id;
	}

	public void setSend_deal_id(String send_deal_id) {
		this.send_deal_id = send_deal_id;
	}

	public String getSend_deal_name() {
		return send_deal_name;
	}

	public void setSend_deal_name(String send_deal_name) {
		this.send_deal_name = send_deal_name;
	}

	public Date getSend_date() {
		return send_date;
	}

	public void setSend_date(Date send_date) {
		this.send_date = send_date;
	}

	public String getFk_car_id() {
		return fk_car_id;
	}

	public void setFk_car_id(String fk_car_id) {
		this.fk_car_id = fk_car_id;
	}

	public String getPlate_number() {
		return plate_number;
	}

	public void setPlate_number(String plate_number) {
		this.plate_number = plate_number;
	}

	public String getStart_km() {
		return start_km;
	}

	public void setStart_km(String start_km) {
		this.start_km = start_km;
	}

	public String getEnd_km() {
		return end_km;
	}

	public void setEnd_km(String end_km) {
		this.end_km = end_km;
	}

	public String getDriver_user_id() {
		return driver_user_id;
	}

	public void setDriver_user_id(String driver_user_id) {
		this.driver_user_id = driver_user_id;
	}

	public String getDriver_user_name() {
		return driver_user_name;
	}

	public void setDriver_user_name(String driver_user_name) {
		this.driver_user_name = driver_user_name;
	}

	public String getReceive_deal_id() {
		return receive_deal_id;
	}

	public void setReceive_deal_id(String receive_deal_id) {
		this.receive_deal_id = receive_deal_id;
	}

	public String getReceive_deal_name() {
		return receive_deal_name;
	}

	public void setReceive_deal_name(String receive_deal_name) {
		this.receive_deal_name = receive_deal_name;
	}

	public Date getReceive_date() {
		return receive_date;
	}

	public void setReceive_date(Date receive_date) {
		this.receive_date = receive_date;
	}

	public String getReturn_user_id() {
		return return_user_id;
	}

	public void setReturn_user_id(String return_user_id) {
		this.return_user_id = return_user_id;
	}

	public String getReturn_user_name() {
		return return_user_name;
	}

	public void setReturn_user_name(String return_user_name) {
		this.return_user_name = return_user_name;
	}

	public Date getReturn_date() {
		return return_date;
	}

	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}

	public String getData_status() {
		return data_status;
	}

	public void setData_status(String data_status) {
		this.data_status = data_status;
	}

	public Integer getUse_days() {
		return use_days;
	}

	public void setUse_days(Integer use_days) {
		this.use_days = use_days;
	}

	public String getExpenses() {
		return expenses;
	}

	public void setExpenses(String expenses) {
		this.expenses = expenses;
	}

	public Integer getPassengers_count() {
		return passengers_count;
	}

	public void setPassengers_count(Integer passengers_count) {
		this.passengers_count = passengers_count;
	}

	public String getNeed_driver() {
		return need_driver;
	}

	public void setNeed_driver(String need_driver) {
		this.need_driver = need_driver;
	}

	public String getApply_remark() {
		return apply_remark;
	}

	public void setApply_remark(String apply_remark) {
		this.apply_remark = apply_remark;
	}
	
}
