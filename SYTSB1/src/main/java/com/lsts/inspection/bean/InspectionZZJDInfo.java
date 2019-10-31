package com.lsts.inspection.bean;


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
 * 安装/制造/改造及重大修理 监督检验报检业务明细表
 * 
 * @ClassName InspectionZZJDInfo
 * @JDK 1.6
 * @author GaoYa
 * @date 2015-01-12 下午02:35:00
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true, value = { "inspectionZZJD"})
@Table(name = "tzsb_inspection_zzjd_info")
public class InspectionZZJDInfo implements BaseEntity{
	
	private static final long serialVersionUID = 1L;
	private String	id	;	//	ID
	//private String  fk_inspection_zzjd_id;// 制造监督检验报检业务主表ID
	private String  fk_inspection_info_id;	// 业务信息表ID
	
	private String  sn;					// 业务流水号
	private String	made_unit_name;		// 制造单位名称
	private String  made_unit_addr;		// 制造单位地址
	private String	made_unit_code;		// 制造单位机构代码
	private String	made_license_code;	// 制造许可证编号
	private String	made_license_level;	// 制造许可证级别
	private String	made_date;			// 制造日期
	private String	install_unit_name;		// 安装单位名称
	private String	install_license_code;	// 安装许可证编号
	private String	install_date;			// 安装（竣工）日期/投入使用日期
	private String  design_unit_name;	// 设计单位名称
	private String  design_unit_code;	// 设计单位机构代码
	private String  design_license_code;// 设计许可证编号
	private String	design_date;		// 设计日期/设计使用年限
	
	
	private String  construction_unit_name; 		// 施工/建设单位名称
	private String  construction_unit_code; 		// 施工单位组织机构代码
	private String  construction_license_level; 	// 改造修理许可级别
	private String  construction_license_code; 	// 改造修理许可证编号
	private String  construction_type;			// 施工类别
	private String  repair_finish_date;			// 竣工日期	
	
	private String  com_name; 					// 使用单位名称
	private String  com_code; 					// 使用单位组织机构代码
	private String  com_addr;					// 使用单位地址
	private String  com_registration_num;		// 使用登记证编号
	private String  com_zip_code;				// 邮政编码
	private String  device_use_place; 			// 设备使用地点

	private String  device_type_code;	// 设备类别代码
	private String  device_type_name;	// 设备类别名称
	private String	device_name;	// 产品名称
	private String	device_no;		// 产品编号
	private String  device_batch_num;	// 产品批号
	private String	device_code;	// 设备代码
	private String	device_pic_no;	// 产品图号
	
	private String  device_model;	// 产品型号/产品规格
	private String  device_standard;// 产品/制造标准
	private String  device_count;	// 产品数量
	private String  material_num;	// 材料牌号
	private String  confirm_date1;	// 确认日期1
	private String  confirm_date2;	// 确认日期2
	private String  confirm_date3;	// 确认日期3
	private String  device_processing;	// 来料加工（是）
	private String  device_processing2;	// 来料加工（否）
	
	private String  device_pressure;	// 公称压力/设计压力/额定出口压力
	private String  device_medium;		// 工作介质/产品试用介质/输送介质
	private String  device_car_num;		// 车辆牌号/车辆识别代码
	private String  check_device_no;	// 监检所抽的产品编号
	private String  trial_device_no;	// 本证书适用的产品编号
	
	private String	device_file_check;	// 气瓶文件审查
	private String	file_check_date;	// 文件审查日期
	private String	install_data_check;	// 安装资料审核
	private String	data_check_date;	// 资料审查日期
	private String	device_surface_check;	// 气瓶外观检查
	private String	surface_check_date;		// 外观检查日期
	private String	install_quality_check;	// 安装质量检查
	private String	quality_check_date;		// 安装质量检查日期
	private String	leak_test_check;		// 泄漏试验确认
	private String	leak_check_date;		// 泄漏试验日期
	private String	finish_data_check;		// 安装竣工资料审查
	private String	finish_check_date;		// 安装竣工资料审查日期
	
	private String  repair_project;			// 改造与重大修理项目
	private String  safely_tech_standard;	// 有关安全技术监察规程/检验依据
	private String  install_evaluate;		// 对安装单位质量体系运转情况的评价
	private String  inspection_events;		// 记事/问题及其处理
	
	// 安全阀安全性能监督检验证书
	private String  use_temp;				// 产品适用温度/设计温度/额定出口温度
	private String  factory_code_1;			// 出厂编号（1）开始到
	private String  factory_code_2;			// 出厂编号（2）结束
	
	// 压力管道元件监检证书（埋弧焊钢管）
	private String  device_no_2;			// 部件编号及管号
	
	// 压力管道元件监检证书（聚乙烯管）
	private String  device_model_2;			// 等级/型号/安全状况等级评定
	
	// 金属常压罐体制造委托监督检验证书
	private String  engine_no;				// 发动机编号
	private String  device_volume;			// 容积
	private String  bz1;			// 内径
	private String  bz2;			// 容积
	
	// 压力管道安装安全质量监督检验证书
	private String  project_name;			// 工程名称
	private String  device_meterial;		// 设备材料（管道材质）
	private String  device_length;			// 设备长度（管道长度）
	
	// 锅炉设计文件鉴定报告
	private String  check_project_code;		// 鉴定项目编号
	private String  design_property;		// 设计属性
	private String  exam_property;			// 审查属性
	private String  check_property;			// 鉴定属性
	private String  rated_output;			// 额定出力
	private String  design_heat;			// 设计热效率
	private String  work_range;				// 稳定工况范围
	private String  use_temp2;				// 锅炉给水（回水）温度（℃）/使用温度
	private String  struct_type;			// 结构型式/主体结构形式
	private String  design_fuel_type;		// 设计燃料类型
	private String  burn_mode;				// 燃烧方式
	private String  calorific_value;		// 燃料低位发热量不低于（MJ/Kg） 
	private String  burner_model;			// 燃烧机型号
	/*
	 * 用于锅炉设计文件鉴定报告收费金额计算
	 * modify by GY
	 * date 2017-11-27
	 */
	private String  weight_steels;			// 本体耗钢量（吨）
	
	
	/*
	 * 以下数据存储多个数值时，以英文小写逗号“,”分隔
	 * modify by GY
	 * date 2016-03-11
	 */
	private String  glsjwj_jdqd_ztbh;		// 总图编号/设计/审核/批准人员/批准日期/备注
	private String  glsjwj_jdqd_bttbh;		// 水冷（壁）系统图或本体图编号/设计/审核/批准人员/批准日期/备注
	private String  glsjwj_jdqd_dlgsbh;		// 对流管束编号/设计/审核/批准人员/批准日期/备注
	private String  glsjwj_jdqd_gtbh;		// 锅筒、汽水分离器、储水罐编号/设计/审核/批准人员/批准日期和备注
	private String  glsjwj_jdqd_grqbh;		// 过热器编号/设计/审核/批准人员/批准日期/备注
	private String  glsjwj_jdqd_jwqbh;		// 减温器编号/设计/审核/批准人员/批准日期/备注
	private String  glsjwj_jdqd_zrqbh;		// 再热器编号/设计/审核/批准人员/批准日期/备注
	private String  glsjwj_jdqd_smqbh;		// 省煤器编号/设计/审核/批准人员/批准日期/备注
	private String  glsjwj_jdqd_rylxttbh;	// 有机热载体锅炉系统图编号/设计/审核/批准人员/批准日期和备注
	private String  glsjwj_jdqd_qdjshzbbh;	// 强度计算汇总表编号/设计/审核/批准人员/批准日期/备注
	//2018年9月份增加
	private String  glsjwj_jdqd_zjsgdbh;		// 主给水管道编号/设计/审核/批准人员/批准日期和备注
	private String  glsjwj_jdqd_zzqgdbh;		// 主蒸汽管道编号/设计/审核/批准人员/批准日期和备注
	private String  glsjwj_jdqd_zrzqldgdbh;	// 再热蒸汽冷段管道编号/设计/审核/批准人员/批准日期和备注
	private String  glsjwj_jdqd_zrzqrdgdbh;	// 再热蒸汽热段管道编号/设计/审核/批准人员/批准日期和备注
	/*
	 * 以上数据存储多个数值时，以|分隔
	 */
	
	/*
	 * 安全阀校验报告
	 * modify by GY
	 * date 2016-08-25
	 */
	private String  code_type;				// 编号类型（单位内编号/产品编号/车牌号）
	private String  code_value;				// 编号值
	private String  check_op;				// 联系人
	private String  check_tel;				// 联系电话
	private String  yqzdyl;					// 要求整定压力
	private String  check_method;			// 校验方式
	private String  check_medium;			// 校验介质
	private String  zdyl;					// 整定压力/使用压力
	private String  mfsyyl;					// 密封试验压力
	private String  last_inspection_date;	// 下次校验日期
	private String  report_sn;				// 报告编号
	/*
	 * 安全阀校验报告
	 */
	
	private String  inspection_conclusion;	// 检验结论
	
	private String  inspection_user_name1;	// 检验员1
	private String  inspection_user_name2;	// 检验员2
	private String 	inspection_unit_id;		// 检验部门ID
	private String 	inspection_unit_name;	// 检验部门名称
	private Date	inspection_date;		// 检验日期/编制日期
	private Double	advance_fees;			// 预收金额
	
	
	private String  is_self_sn;				// 是否自编号（0：否 1：是）
												// 如果是自编号，系统不自动生成报告编号，用户可修改；
												// 如果不是自编号，系统自动生成报告编号，用户不可修改；
	private String  remark;					// 备注
	private String  data_status;			// 数据状态(1：正常 99：已删除)

	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public InspectionZZJD inspectionZZJD;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_inspection_zzjd_id")
	public InspectionZZJD getInspectionZZJD() {
		return inspectionZZJD;
	}

	public void setInspectionZZJD(InspectionZZJD inspectionZZJD) {
		this.inspectionZZJD = inspectionZZJD;
	}
	
	/*public InspectionInfo inspectionInfo;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_inspection_info_id")
	public InspectionInfo getInspectionInfo() {
		return inspectionInfo;
	}
	public void setInspectionInfo(InspectionInfo inspectionInfo) {
		this.inspectionInfo = inspectionInfo;
	}*/
	
	
	
	public String getData_status() {
		return data_status;
	}
	public void setData_status(String data_status) {
		this.data_status = data_status;
	}
	
	/*public String getFk_inspection_zzjd_id() {
		return fk_inspection_zzjd_id;
	}
	public void setFk_inspection_zzjd_id(String fk_inspection_zzjd_id) {
		this.fk_inspection_zzjd_id = fk_inspection_zzjd_id;
	}*/
	public String getFk_inspection_info_id() {
		return fk_inspection_info_id;
	}
	public void setFk_inspection_info_id(String fk_inspection_info_id) {
		this.fk_inspection_info_id = fk_inspection_info_id;
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
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public Double getAdvance_fees() {
		return advance_fees;
	}
	public void setAdvance_fees(Double advance_fees) {
		this.advance_fees = advance_fees;
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
	public String getDevice_file_check() {
		return device_file_check;
	}
	public void setDevice_file_check(String device_file_check) {
		this.device_file_check = device_file_check;
	}
	public String getFile_check_date() {
		return file_check_date;
	}
	public void setFile_check_date(String file_check_date) {
		this.file_check_date = file_check_date;
	}
	public String getInstall_data_check() {
		return install_data_check;
	}
	public void setInstall_data_check(String install_data_check) {
		this.install_data_check = install_data_check;
	}
	public String getData_check_date() {
		return data_check_date;
	}
	public void setData_check_date(String data_check_date) {
		this.data_check_date = data_check_date;
	}
	public String getDevice_surface_check() {
		return device_surface_check;
	}
	public void setDevice_surface_check(String device_surface_check) {
		this.device_surface_check = device_surface_check;
	}
	public String getSurface_check_date() {
		return surface_check_date;
	}
	public void setSurface_check_date(String surface_check_date) {
		this.surface_check_date = surface_check_date;
	}
	public String getInstall_quality_check() {
		return install_quality_check;
	}
	public void setInstall_quality_check(String install_quality_check) {
		this.install_quality_check = install_quality_check;
	}
	public String getQuality_check_date() {
		return quality_check_date;
	}
	public void setQuality_check_date(String quality_check_date) {
		this.quality_check_date = quality_check_date;
	}
	public String getLeak_test_check() {
		return leak_test_check;
	}
	public void setLeak_test_check(String leak_test_check) {
		this.leak_test_check = leak_test_check;
	}
	public String getLeak_check_date() {
		return leak_check_date;
	}
	public void setLeak_check_date(String leak_check_date) {
		this.leak_check_date = leak_check_date;
	}
	public String getFinish_data_check() {
		return finish_data_check;
	}
	public void setFinish_data_check(String finish_data_check) {
		this.finish_data_check = finish_data_check;
	}
	public String getFinish_check_date() {
		return finish_check_date;
	}
	public void setFinish_check_date(String finish_check_date) {
		this.finish_check_date = finish_check_date;
	}
	public String getCheck_device_no() {
		return check_device_no;
	}
	public void setCheck_device_no(String check_device_no) {
		this.check_device_no = check_device_no;
	}
	public String getTrial_device_no() {
		return trial_device_no;
	}
	public void setTrial_device_no(String trial_device_no) {
		this.trial_device_no = trial_device_no;
	}
	public String getConstruction_unit_name() {
		return construction_unit_name;
	}
	public void setConstruction_unit_name(String construction_unit_name) {
		this.construction_unit_name = construction_unit_name;
	}
	public String getConstruction_unit_code() {
		return construction_unit_code;
	}
	public void setConstruction_unit_code(String construction_unit_code) {
		this.construction_unit_code = construction_unit_code;
	}
	public String getConstruction_license_level() {
		return construction_license_level;
	}
	public void setConstruction_license_level(String construction_license_level) {
		this.construction_license_level = construction_license_level;
	}
	public String getConstruction_license_code() {
		return construction_license_code;
	}
	public void setConstruction_license_code(String construction_license_code) {
		this.construction_license_code = construction_license_code;
	}
	public String getConstruction_type() {
		return construction_type;
	}
	public void setConstruction_type(String construction_type) {
		this.construction_type = construction_type;
	}
	public String getRepair_finish_date() {
		return repair_finish_date;
	}
	public void setRepair_finish_date(String repair_finish_date) {
		this.repair_finish_date = repair_finish_date;
	}
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	public String getCom_code() {
		return com_code;
	}
	public void setCom_code(String com_code) {
		this.com_code = com_code;
	}
	public String getCom_registration_num() {
		return com_registration_num;
	}
	public void setCom_registration_num(String com_registration_num) {
		this.com_registration_num = com_registration_num;
	}
	public String getDevice_use_place() {
		return device_use_place;
	}
	public void setDevice_use_place(String device_use_place) {
		this.device_use_place = device_use_place;
	}
	public String getRepair_project() {
		return repair_project;
	}
	public void setRepair_project(String repair_project) {
		this.repair_project = repair_project;
	}
	public String getDevice_model() {
		return device_model;
	}
	public void setDevice_model(String device_model) {
		this.device_model = device_model;
	}
	public String getDevice_standard() {
		return device_standard;
	}
	public void setDevice_standard(String device_standard) {
		this.device_standard = device_standard;
	}
	public String getDevice_count() {
		return device_count;
	}
	public void setDevice_count(String device_count) {
		this.device_count = device_count;
	}
	public String getMaterial_num() {
		return material_num;
	}
	public void setMaterial_num(String material_num) {
		this.material_num = material_num;
	}
	public String getConfirm_date1() {
		return confirm_date1;
	}
	public void setConfirm_date1(String confirm_date1) {
		this.confirm_date1 = confirm_date1;
	}
	public String getConfirm_date2() {
		return confirm_date2;
	}
	public void setConfirm_date2(String confirm_date2) {
		this.confirm_date2 = confirm_date2;
	}
	public String getConfirm_date3() {
		return confirm_date3;
	}
	public void setConfirm_date3(String confirm_date3) {
		this.confirm_date3 = confirm_date3;
	}
	public String getInspection_events() {
		return inspection_events;
	}
	public void setInspection_events(String inspection_events) {
		this.inspection_events = inspection_events;
	}
	public String getDevice_processing() {
		return device_processing;
	}
	public void setDevice_processing(String device_processing) {
		this.device_processing = device_processing;
	}
	public String getDevice_batch_num() {
		return device_batch_num;
	}
	public void setDevice_batch_num(String device_batch_num) {
		this.device_batch_num = device_batch_num;
	}
	public String getUse_temp() {
		return use_temp;
	}
	public void setUse_temp(String use_temp) {
		this.use_temp = use_temp;
	}
	public String getFactory_code_1() {
		return factory_code_1;
	}
	public void setFactory_code_1(String factory_code_1) {
		this.factory_code_1 = factory_code_1;
	}
	public String getFactory_code_2() {
		return factory_code_2;
	}
	public void setFactory_code_2(String factory_code_2) {
		this.factory_code_2 = factory_code_2;
	}
	public String getDevice_no_2() {
		return device_no_2;
	}
	public void setDevice_no_2(String device_no_2) {
		this.device_no_2 = device_no_2;
	}
	public String getDevice_model_2() {
		return device_model_2;
	}
	public void setDevice_model_2(String device_model_2) {
		this.device_model_2 = device_model_2;
	}
	public String getEngine_no() {
		return engine_no;
	}
	public void setEngine_no(String engine_no) {
		this.engine_no = engine_no;
	}
	public String getDevice_volume() {
		return device_volume;
	}
	public void setDevice_volume(String device_volume) {
		this.device_volume = device_volume;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getDevice_meterial() {
		return device_meterial;
	}
	public void setDevice_meterial(String device_meterial) {
		this.device_meterial = device_meterial;
	}
	public String getDevice_length() {
		return device_length;
	}
	public void setDevice_length(String device_length) {
		this.device_length = device_length;
	}
	public String getInspection_conclusion() {
		return inspection_conclusion;
	}
	public void setInspection_conclusion(String inspection_conclusion) {
		this.inspection_conclusion = inspection_conclusion;
	}
	public String getInstall_evaluate() {
		return install_evaluate;
	}
	public void setInstall_evaluate(String install_evaluate) {
		this.install_evaluate = install_evaluate;
	}
	public String getDevice_processing2() {
		return device_processing2;
	}
	public void setDevice_processing2(String device_processing2) {
		this.device_processing2 = device_processing2;
	}
	public String getMade_unit_addr() {
		return made_unit_addr;
	}
	public void setMade_unit_addr(String made_unit_addr) {
		this.made_unit_addr = made_unit_addr;
	}
	public String getDesign_property() {
		return design_property;
	}
	public void setDesign_property(String design_property) {
		this.design_property = design_property;
	}
	public String getCheck_property() {
		return check_property;
	}
	public void setCheck_property(String check_property) {
		this.check_property = check_property;
	}
	public String getRated_output() {
		return rated_output;
	}
	public void setRated_output(String rated_output) {
		this.rated_output = rated_output;
	}
	public String getStruct_type() {
		return struct_type;
	}
	public void setStruct_type(String struct_type) {
		this.struct_type = struct_type;
	}
	public String getDesign_fuel_type() {
		return design_fuel_type;
	}
	public void setDesign_fuel_type(String design_fuel_type) {
		this.design_fuel_type = design_fuel_type;
	}
	public String getBurn_mode() {
		return burn_mode;
	}
	public void setBurn_mode(String burn_mode) {
		this.burn_mode = burn_mode;
	}
	public String getGlsjwj_jdqd_ztbh() {
		return glsjwj_jdqd_ztbh;
	}
	public void setGlsjwj_jdqd_ztbh(String glsjwj_jdqd_ztbh) {
		this.glsjwj_jdqd_ztbh = glsjwj_jdqd_ztbh;
	}
	public String getGlsjwj_jdqd_bttbh() {
		return glsjwj_jdqd_bttbh;
	}
	public void setGlsjwj_jdqd_bttbh(String glsjwj_jdqd_bttbh) {
		this.glsjwj_jdqd_bttbh = glsjwj_jdqd_bttbh;
	}
	public String getGlsjwj_jdqd_dlgsbh() {
		return glsjwj_jdqd_dlgsbh;
	}
	public void setGlsjwj_jdqd_dlgsbh(String glsjwj_jdqd_dlgsbh) {
		this.glsjwj_jdqd_dlgsbh = glsjwj_jdqd_dlgsbh;
	}
	public String getGlsjwj_jdqd_gtbh() {
		return glsjwj_jdqd_gtbh;
	}
	public void setGlsjwj_jdqd_gtbh(String glsjwj_jdqd_gtbh) {
		this.glsjwj_jdqd_gtbh = glsjwj_jdqd_gtbh;
	}
	public String getGlsjwj_jdqd_grqbh() {
		return glsjwj_jdqd_grqbh;
	}
	public void setGlsjwj_jdqd_grqbh(String glsjwj_jdqd_grqbh) {
		this.glsjwj_jdqd_grqbh = glsjwj_jdqd_grqbh;
	}
	public String getGlsjwj_jdqd_jwqbh() {
		return glsjwj_jdqd_jwqbh;
	}
	public void setGlsjwj_jdqd_jwqbh(String glsjwj_jdqd_jwqbh) {
		this.glsjwj_jdqd_jwqbh = glsjwj_jdqd_jwqbh;
	}
	public String getGlsjwj_jdqd_zrqbh() {
		return glsjwj_jdqd_zrqbh;
	}
	public void setGlsjwj_jdqd_zrqbh(String glsjwj_jdqd_zrqbh) {
		this.glsjwj_jdqd_zrqbh = glsjwj_jdqd_zrqbh;
	}
	public String getGlsjwj_jdqd_smqbh() {
		return glsjwj_jdqd_smqbh;
	}
	public void setGlsjwj_jdqd_smqbh(String glsjwj_jdqd_smqbh) {
		this.glsjwj_jdqd_smqbh = glsjwj_jdqd_smqbh;
	}
	public String getGlsjwj_jdqd_rylxttbh() {
		return glsjwj_jdqd_rylxttbh;
	}
	public void setGlsjwj_jdqd_rylxttbh(String glsjwj_jdqd_rylxttbh) {
		this.glsjwj_jdqd_rylxttbh = glsjwj_jdqd_rylxttbh;
	}
	public String getGlsjwj_jdqd_qdjshzbbh() {
		return glsjwj_jdqd_qdjshzbbh;
	}
	public void setGlsjwj_jdqd_qdjshzbbh(String glsjwj_jdqd_qdjshzbbh) {
		this.glsjwj_jdqd_qdjshzbbh = glsjwj_jdqd_qdjshzbbh;
	}
	public String getGlsjwj_jdqd_zjsgdbh() {
		return glsjwj_jdqd_zjsgdbh;
	}
	public void setGlsjwj_jdqd_zjsgdbh(String glsjwj_jdqd_zjsgdbh) {
		this.glsjwj_jdqd_zjsgdbh = glsjwj_jdqd_zjsgdbh;
	}
	public String getGlsjwj_jdqd_zzqgdbh() {
		return glsjwj_jdqd_zzqgdbh;
	}
	public void setGlsjwj_jdqd_zzqgdbh(String glsjwj_jdqd_zzqgdbh) {
		this.glsjwj_jdqd_zzqgdbh = glsjwj_jdqd_zzqgdbh;
	}
	public String getGlsjwj_jdqd_zrzqldgdbh() {
		return glsjwj_jdqd_zrzqldgdbh;
	}
	public void setGlsjwj_jdqd_zrzqldgdbh(String glsjwj_jdqd_zrzqldgdbh) {
		this.glsjwj_jdqd_zrzqldgdbh = glsjwj_jdqd_zrzqldgdbh;
	}
	public String getGlsjwj_jdqd_zrzqrdgdbh() {
		return glsjwj_jdqd_zrzqrdgdbh;
	}
	public void setGlsjwj_jdqd_zrzqrdgdbh(String glsjwj_jdqd_zrzqrdgdbh) {
		this.glsjwj_jdqd_zrzqrdgdbh = glsjwj_jdqd_zrzqrdgdbh;
	}
	public String getCheck_project_code() {
		return check_project_code;
	}
	public void setCheck_project_code(String check_project_code) {
		this.check_project_code = check_project_code;
	}
	public String getExam_property() {
		return exam_property;
	}
	public void setExam_property(String exam_property) {
		this.exam_property = exam_property;
	}
	public String getDesign_heat() {
		return design_heat;
	}
	public void setDesign_heat(String design_heat) {
		this.design_heat = design_heat;
	}
	public String getWork_range() {
		return work_range;
	}
	public void setWork_range(String work_range) {
		this.work_range = work_range;
	}
	public String getUse_temp2() {
		return use_temp2;
	}
	public void setUse_temp2(String use_temp2) {
		this.use_temp2 = use_temp2;
	}
	public String getCalorific_value() {
		return calorific_value;
	}
	public void setCalorific_value(String calorific_value) {
		this.calorific_value = calorific_value;
	}
	public String getBurner_model() {
		return burner_model;
	}
	public void setBurner_model(String burner_model) {
		this.burner_model = burner_model;
	}
	public String getBz1() {
		return bz1;
	}
	public void setBz1(String bz1) {
		this.bz1 = bz1;
	}
	public String getBz2() {
		return bz2;
	}
	public void setBz2(String bz2) {
		this.bz2 = bz2;
	}
	public String getIs_self_sn() {
		return is_self_sn;
	}
	public void setIs_self_sn(String is_self_sn) {
		this.is_self_sn = is_self_sn;
	}
	public String getCom_addr() {
		return com_addr;
	}
	public void setCom_addr(String com_addr) {
		this.com_addr = com_addr;
	}
	public String getCom_zip_code() {
		return com_zip_code;
	}
	public void setCom_zip_code(String com_zip_code) {
		this.com_zip_code = com_zip_code;
	}
	public String getCode_type() {
		return code_type;
	}
	public void setCode_type(String code_type) {
		this.code_type = code_type;
	}
	public String getCode_value() {
		return code_value;
	}
	public void setCode_value(String code_value) {
		this.code_value = code_value;
	}
	public String getCheck_op() {
		return check_op;
	}
	public void setCheck_op(String check_op) {
		this.check_op = check_op;
	}
	public String getCheck_tel() {
		return check_tel;
	}
	public void setCheck_tel(String check_tel) {
		this.check_tel = check_tel;
	}
	public String getYqzdyl() {
		return yqzdyl;
	}
	public void setYqzdyl(String yqzdyl) {
		this.yqzdyl = yqzdyl;
	}
	public String getCheck_method() {
		return check_method;
	}
	public void setCheck_method(String check_method) {
		this.check_method = check_method;
	}
	public String getCheck_medium() {
		return check_medium;
	}
	public void setCheck_medium(String check_medium) {
		this.check_medium = check_medium;
	}
	public String getZdyl() {
		return zdyl;
	}
	public void setZdyl(String zdyl) {
		this.zdyl = zdyl;
	}
	public String getMfsyyl() {
		return mfsyyl;
	}
	public void setMfsyyl(String mfsyyl) {
		this.mfsyyl = mfsyyl;
	}
	public String getReport_sn() {
		return report_sn;
	}
	public void setReport_sn(String report_sn) {
		this.report_sn = report_sn;
	}
	public String getLast_inspection_date() {
		return last_inspection_date;
	}
	public void setLast_inspection_date(String last_inspection_date) {
		this.last_inspection_date = last_inspection_date;
	}
	public String getWeight_steels() {
		return weight_steels;
	}
	public void setWeight_steels(String weight_steels) {
		this.weight_steels = weight_steels;
	}
	
}
