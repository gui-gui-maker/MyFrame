package com.scts.payment.bean;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 制造监督检验报告收费数据传输对象 
 * InspectionZZJDPayInfoDTO
 * 
 * @author GaoYa
 * @date 2015-01-24 下午02:53:00
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InspectionZZJDPayInfoDTO{
	
	private static final long serialVersionUID = 1L;
	private String	id;	// ID
	private String	sn;	// 业务流水号
	private String	made_unit_name;		// 制造单位名称
	private String	made_unit_code;		// 制造单位机构代码
	private String	made_license_code;	// 制造许可证编号
	private String	made_license_level;	// 制造许可证级别
	private String	made_date;		// 制造日期
	private String	install_unit_name;		// 安装单位名称
	private String	install_license_code;	// 安装许可证编号
	private String	install_date;			// 安装日期
	private String	device_name;	// 产品名称
	private String	device_no;		// 产品编号
	private String	device_code;	// 设备代码
	private String	device_pic_no;	// 产品图号
	private String  device_pressure;	// 公称压力
	private String  device_medium;		// 工作介质
	private String  device_car_num;		// 车辆牌号
	private String  device_type_code;		// 设备类别代码
	private String  device_type_name;	// 设备类别名称
	private String  design_unit_name;	// 设计单位名称
	private String  design_unit_code;	// 设计单位机构代码
	private String  design_license_code;// 设计许可证编号
	private String	design_date;		// 设计日期
	private String  safely_tech_standard;	// 有关安全技术监察规程
	private String  inspection_user_name1;	// 检验员1
	private String  inspection_user_name2;	// 检验员2
	private String 	inspection_unit_id;		// 检验部门ID
	private String 	inspection_unit_name;	// 检验部门名称
	private Date	inspection_date;		// 检验日期
	private String  remark;					// 备注
	private double	advance_fees = 0;		// 预收金额
	private double	receivable;			//	实收金额
	private String 	report_sn;			// 报告书编号
	private Date	advance_time;	//	检验日期
	private String	fee_status;		//	收费状态(0 默认 1 待收费 2 已收费 3 借报告 4 借发票 5 借报告和发票)
	private String	advance_type;	//	收费类型 0 正常收费 1 协议收费 2 免收费
	private String	check_op_name;	//	参检人员
	private String	enter_op_name;	//	录入人员
	private Date	enter_time;		//	录入时间
	private Date    cur_date;		//  当前时间

	public InspectionZZJDPayInfoDTO() {
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getDevice_name() {
		return device_name;
	}
	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}
	public String getReport_sn() {
		return report_sn;
	}
	public void setReport_sn(String report_sn) {
		this.report_sn = report_sn;
	}
	public double getAdvance_fees() {
		return advance_fees;
	}

	public void setAdvance_fees(double advance_fees) {
		this.advance_fees = advance_fees;
	}

	public Date getAdvance_time() {
		return advance_time;
	}

	public void setAdvance_time(Date advance_time) {
		this.advance_time = advance_time;
	}

	public String getAdvance_type() {
		return advance_type;
	}

	public void setAdvance_type(String advance_type) {
		this.advance_type = advance_type;
	}

	public String getCheck_op_name() {
		return check_op_name;
	}

	public void setCheck_op_name(String check_op_name) {
		this.check_op_name = check_op_name;
	}

	public String getEnter_op_name() {
		return enter_op_name;
	}

	public void setEnter_op_name(String enter_op_name) {
		this.enter_op_name = enter_op_name;
	}

	public Date getEnter_time() {
		return enter_time;
	}

	public void setEnter_time(Date enter_time) {
		this.enter_time = enter_time;
	}

	public String getFee_status() {
		return fee_status;
	}

	public void setFee_status(String fee_status) {
		this.fee_status = fee_status;
	}

	public double getReceivable() {
		return receivable;
	}

	public void setReceivable(double receivable) {
		this.receivable = receivable;
	}
	
	public Date getCur_date() {
		return cur_date;
	}

	public String getMade_unit_name() {
		return made_unit_name;
	}

	public void setMade_unit_name(String made_unit_name) {
		this.made_unit_name = made_unit_name;
	}

	public String getMade_unit_code() {
		return made_unit_code;
	}

	public void setMade_unit_code(String made_unit_code) {
		this.made_unit_code = made_unit_code;
	}

	public String getMade_license_code() {
		return made_license_code;
	}

	public void setMade_license_code(String made_license_code) {
		this.made_license_code = made_license_code;
	}

	public String getMade_license_level() {
		return made_license_level;
	}

	public void setMade_license_level(String made_license_level) {
		this.made_license_level = made_license_level;
	}

	public String getMade_date() {
		return made_date;
	}

	public void setMade_date(String made_date) {
		this.made_date = made_date;
	}

	public String getDevice_no() {
		return device_no;
	}

	public void setDevice_no(String device_no) {
		this.device_no = device_no;
	}

	public String getDevice_code() {
		return device_code;
	}

	public void setDevice_code(String device_code) {
		this.device_code = device_code;
	}

	public String getDevice_pic_no() {
		return device_pic_no;
	}

	public void setDevice_pic_no(String device_pic_no) {
		this.device_pic_no = device_pic_no;
	}

	public String getDevice_type_code() {
		return device_type_code;
	}

	public void setDevice_type_code(String device_type_code) {
		this.device_type_code = device_type_code;
	}

	public String getDevice_type_name() {
		return device_type_name;
	}

	public void setDevice_type_name(String device_type_name) {
		this.device_type_name = device_type_name;
	}

	public String getDesign_unit_name() {
		return design_unit_name;
	}

	public void setDesign_unit_name(String design_unit_name) {
		this.design_unit_name = design_unit_name;
	}

	public String getDesign_unit_code() {
		return design_unit_code;
	}

	public void setDesign_unit_code(String design_unit_code) {
		this.design_unit_code = design_unit_code;
	}

	public String getDesign_license_code() {
		return design_license_code;
	}

	public void setDesign_license_code(String design_license_code) {
		this.design_license_code = design_license_code;
	}

	public String getDesign_date() {
		return design_date;
	}

	public void setDesign_date(String design_date) {
		this.design_date = design_date;
	}

	public String getSafely_tech_standard() {
		return safely_tech_standard;
	}

	public void setSafely_tech_standard(String safely_tech_standard) {
		this.safely_tech_standard = safely_tech_standard;
	}

	public String getInspection_user_name1() {
		return inspection_user_name1;
	}

	public void setInspection_user_name1(String inspection_user_name1) {
		this.inspection_user_name1 = inspection_user_name1;
	}

	public String getInspection_user_name2() {
		return inspection_user_name2;
	}

	public void setInspection_user_name2(String inspection_user_name2) {
		this.inspection_user_name2 = inspection_user_name2;
	}

	public String getInspection_unit_id() {
		return inspection_unit_id;
	}

	public void setInspection_unit_id(String inspection_unit_id) {
		this.inspection_unit_id = inspection_unit_id;
	}

	public String getInspection_unit_name() {
		return inspection_unit_name;
	}

	public void setInspection_unit_name(String inspection_unit_name) {
		this.inspection_unit_name = inspection_unit_name;
	}

	public Date getInspection_date() {
		return inspection_date;
	}

	public void setInspection_date(Date inspection_date) {
		this.inspection_date = inspection_date;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setCur_date(Date cur_date) {
		this.cur_date = cur_date;
	}

	public String getInstall_unit_name() {
		return install_unit_name;
	}

	public void setInstall_unit_name(String install_unit_name) {
		this.install_unit_name = install_unit_name;
	}

	public String getInstall_license_code() {
		return install_license_code;
	}

	public void setInstall_license_code(String install_license_code) {
		this.install_license_code = install_license_code;
	}

	public String getInstall_date() {
		return install_date;
	}

	public void setInstall_date(String install_date) {
		this.install_date = install_date;
	}

	public String getDevice_pressure() {
		return device_pressure;
	}

	public void setDevice_pressure(String device_pressure) {
		this.device_pressure = device_pressure;
	}

	public String getDevice_medium() {
		return device_medium;
	}

	public void setDevice_medium(String device_medium) {
		this.device_medium = device_medium;
	}

	public String getDevice_car_num() {
		return device_car_num;
	}

	public void setDevice_car_num(String device_car_num) {
		this.device_car_num = device_car_num;
	}
	
	
}
