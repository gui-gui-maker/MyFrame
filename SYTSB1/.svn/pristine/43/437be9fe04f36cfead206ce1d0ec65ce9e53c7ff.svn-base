package com.lsts.device.bean;

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

/*******************************************************************************
 * 
 * 压力容器参数
 * 
 * @author 肖慈边 2014-1-21
 * 
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true, value = { "deviceDocument" })
@Table(name = "BASE_DEVICE_PRESSUREVESSELS")
public class PressurevesselsPara implements BaseEntity{

	private static final long serialVersionUID = 1L;
	private String	id	;	//	id
//	private String	fk_tsjc_device_document_id	;	//	设备基本信息ID
//	private String	registration_num	;	//	使用登记证编号
	private String	container_name	;	//	容器名称
	private String	container_claasify	;	//	容器分类
	private String	fk_design_units_id	;	//	设计单位ID
	private String	manufacturer	;	//	制造国
	private String	monitor_unit_id	;	//	产品监检单位ID
	private String	workshop	;	//	所在车间分厂
	private String	container_minor_diameter	;	//	容器内径
	private String	shell_material	;	//	筒体材料
	private String	head_material	;	//	封头材料
	private String	lining_material	;	//	内衬材料
	private String	clamp_material	;	//	夹套材料
	private String	shell_thk	;	//	筒体厚度
	private String	head_thk	;	//	封头厚度
	private String	lining_thk	;	//	内衬壁厚
	private String	clamp_thk	;	//	夹套厚度
	private String	container_capacity	;	//	容器容积
	private String	container_length	;	//	容器高(长)
	private String	shell_weight	;	//	壳体重量
	private String	lining_weight	;	//	内件重量
	private String	capacity_weight	;	//	充装重量
	private String	is_insulate	;	//	有无保温绝热
	private String	shell_design_press	;	//	壳程设计压力
	private String	shell_design_temper	;	//	壳程设计温度
	private String	shell_max_press	;	//	壳程工作压力
	private String	lining_design_press	;	//	管程设计压力
	private String	lining_design_temper	;	//	管程设计温度
	private String	lining_max_press	;	//	管程工作压力
	private String	clamp_design_press	;	//	夹套设计压力
	private String	clamp_design_temper	;	//	夹套设计温度
	private String	clamp_max_press	;	//	夹套工作压力
	private String	shell_medium	;	//	壳程介质
	private String	lining_medium	;	//	管程介质
	private String	clamp_medium	;	//	夹套介质
	private String	chamber_light	;	//	氧舱照明
	private String	chamber_motor	;	//	氧舱空调电机
	private String	chamber_oxygen_test	;	//	氧舱测氧方式
	private String	ladle_car_number	;	//	罐车牌号
	private String	ladle_car_structure	;	//	罐车结构型式
	private String	ladle_car_domain_num	;	//	罐车地盘号码
	private String	property_right_unit	;	//	产权单位
	private String	property_right_unit_code	;	//	产权单位代码
	private String	p20001002	;	//	压力容器型式
	private String	p20001003	;	//	压力容器类别
	private String	p20001004	;	//	压力容器压力等级
	private String	p20001006	;	//	压力容器检验类别
	private String	p20001007	;	//	设备新旧状况
	private String	p20001008	;	//	压力容器监检形式
	private String	p20001009	;	//	安全阀型号
	private String	p20001010	;	//	安全阀规格
	private String	p20001011	;	//	压力表量程
	private String	p20001012	;	//	压力表精度
	private String	p20001013	;	//	液面计型式
	private String	p20001014	;	//	爆破片型号
	private String	p20001015	;	//	紧急切断阀形式
	private String	p20001016	;	//	温度计形式
	private String	p20001030	;	//	压力容器使用状态
	private String	p20001042	;	//	封头型式
	private String	p20001043	;	//	支座型式
	private String	p20001045	;	//	是否快开门
	private String	p_czjz	;	//	充装介质
	private String	p_lhgwxph	;	//	罐式集装箱联合国危险品编号
	private String	p_gsjzxcd	;	//	罐式集装箱长（mm）
	private String	p_gsjzxkd	;	//	罐式集装箱宽（mm）
	private String	p_gsjzxgd	;	//	罐式集装箱高（mm）
	private String	p_bwcl	;	//	保温材料
	private String	p_yljgdjh	;	//	医疗机构登记号
	private String	p_ycrx	;	//	氧舱容限
	private String	p_xzfs	;	//	装卸方式
	private String	p_bwfs	;	//	保温方式
	private String	p_kzzl	;	//	空载重量(Kg)
	private String	p_mzzl	;	//	满载总重量(Kg)
	private String	p_gtmp	;	//	罐体(铭牌)编号
	private String	p_gtwj	;	//	罐体外形-外径（mm）
	private String	p_gtzc	;	//	罐体外形-总长（mm）
	private String	p_gtntcl	;	//	罐体材料-内筒
	private String	p_gtwtcl	;	//	罐体材料-外筒
	private String	p_syyl	;	//	试验压力:（MPa）

	private String	p_zggzyl	;	//	最高工作压力（MPa）
	private String	p_sjyl	;	//	设计压力
	private String	p_sjwd	;	//	设计温度（℃）
	private String	p_yxzdczl	;	//	允许最大充装量（Kg）
	private String	p_czxs	;	//	充装系数
	private String	p_rkwz	;	//	入孔位置
	private String	p_mpwz	;	//	铭牌位置
	private String	p_gtys	;	//	罐体颜色
	private String	p_gtttbh	;	//	罐体壁厚-筒（mm）
	private String	p_gtftbh	;	//	罐体壁厚-封头（mm）
	private String	p_gtwtbh	;	//	罐体壁厚-外筒（mm）
	private String	p_aqfsl	;	//	安全阀数量（个）
	private String	p_bppsl	;	//	爆破片数量（个）
	private String	p_jjqdhsl	;	//	紧急切断阀数量（个）
	private String	p_fzsssl	;	//	防撞设施数量（处）
	private String	p_yyjsl	;	//	液面计数量（个）
	private String	cp_by1	;	//	气瓶组编号
	private String	cp_by2	;	//	产品监检单位名称
	private String	cp_by3	;	//	产品监检单位名称
	private String	cp_by4	;	//	产品监检单位名称
	private String	cp_by5	;	//	产品监检单位名称
	private String	cp_by6	;	//	产品监检单位名称
	private String	cp_by7	;	//	产品监检单位名称
	private String	cbz	;	//	设计单位名称
	private String	p20001046	;	//	压力容器结构形式
	private String	p20003001	;	//	车牌号码(挂牌号码、罐式集装箱编号)
	private String	p20003002	;	//	车盘（车架）号码
	private String	design_units_name	;	//	设计单位名称
	private String	design_units_code	;	//	设计单位代码
	private String	design_date	;			//	设计日期
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public DeviceDocument deviceDocument;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_tsjc_device_document_id")
	public DeviceDocument getDeviceDocument() {
		return deviceDocument;
	}

	public void setDeviceDocument(DeviceDocument deviceDocument) {
		this.deviceDocument = deviceDocument;
	}
	
//	public String getFk_tsjc_device_document_id() {
//		return fk_tsjc_device_document_id;
//	}
//	public void setFk_tsjc_device_document_id(String fk_tsjc_device_document_id) {
//		this.fk_tsjc_device_document_id = fk_tsjc_device_document_id;
//	}
//	public String getRegistration_num() {
//		return registration_num;
//	}
//	public void setRegistration_num(String registration_num) {
//		this.registration_num = registration_num;
//	}
	public String getContainer_name() {
		return container_name;
	}
	public void setContainer_name(String container_name) {
		this.container_name = container_name;
	}
	public String getContainer_claasify() {
		return container_claasify;
	}
	public void setContainer_claasify(String container_claasify) {
		this.container_claasify = container_claasify;
	}
	public String getFk_design_units_id() {
		return fk_design_units_id;
	}
	public void setFk_design_units_id(String fk_design_units_id) {
		this.fk_design_units_id = fk_design_units_id;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getMonitor_unit_id() {
		return monitor_unit_id;
	}
	public void setMonitor_unit_id(String monitor_unit_id) {
		this.monitor_unit_id = monitor_unit_id;
	}
	public String getWorkshop() {
		return workshop;
	}
	public void setWorkshop(String workshop) {
		this.workshop = workshop;
	}
	public String getContainer_minor_diameter() {
		return container_minor_diameter;
	}
	public void setContainer_minor_diameter(String container_minor_diameter) {
		this.container_minor_diameter = container_minor_diameter;
	}
	public String getShell_material() {
		return shell_material;
	}
	public void setShell_material(String shell_material) {
		this.shell_material = shell_material;
	}
	public String getHead_material() {
		return head_material;
	}
	public void setHead_material(String head_material) {
		this.head_material = head_material;
	}
	public String getLining_material() {
		return lining_material;
	}
	public void setLining_material(String lining_material) {
		this.lining_material = lining_material;
	}
	public String getClamp_material() {
		return clamp_material;
	}
	public void setClamp_material(String clamp_material) {
		this.clamp_material = clamp_material;
	}
	public String getShell_thk() {
		return shell_thk;
	}
	public void setShell_thk(String shell_thk) {
		this.shell_thk = shell_thk;
	}
	public String getHead_thk() {
		return head_thk;
	}
	public void setHead_thk(String head_thk) {
		this.head_thk = head_thk;
	}
	public String getLining_thk() {
		return lining_thk;
	}
	public void setLining_thk(String lining_thk) {
		this.lining_thk = lining_thk;
	}
	public String getClamp_thk() {
		return clamp_thk;
	}
	public void setClamp_thk(String clamp_thk) {
		this.clamp_thk = clamp_thk;
	}
	public String getContainer_capacity() {
		return container_capacity;
	}
	public void setContainer_capacity(String container_capacity) {
		this.container_capacity = container_capacity;
	}
	public String getContainer_length() {
		return container_length;
	}
	public void setContainer_length(String container_length) {
		this.container_length = container_length;
	}
	public String getShell_weight() {
		return shell_weight;
	}
	public void setShell_weight(String shell_weight) {
		this.shell_weight = shell_weight;
	}
	public String getLining_weight() {
		return lining_weight;
	}
	public void setLining_weight(String lining_weight) {
		this.lining_weight = lining_weight;
	}
	public String getCapacity_weight() {
		return capacity_weight;
	}
	public void setCapacity_weight(String capacity_weight) {
		this.capacity_weight = capacity_weight;
	}
	public String getIs_insulate() {
		return is_insulate;
	}
	public void setIs_insulate(String is_insulate) {
		this.is_insulate = is_insulate;
	}
	public String getShell_design_press() {
		return shell_design_press;
	}
	public void setShell_design_press(String shell_design_press) {
		this.shell_design_press = shell_design_press;
	}
	public String getShell_design_temper() {
		return shell_design_temper;
	}
	public void setShell_design_temper(String shell_design_temper) {
		this.shell_design_temper = shell_design_temper;
	}
	public String getShell_max_press() {
		return shell_max_press;
	}
	public void setShell_max_press(String shell_max_press) {
		this.shell_max_press = shell_max_press;
	}
	public String getLining_design_press() {
		return lining_design_press;
	}
	public void setLining_design_press(String lining_design_press) {
		this.lining_design_press = lining_design_press;
	}
	public String getLining_design_temper() {
		return lining_design_temper;
	}
	public void setLining_design_temper(String lining_design_temper) {
		this.lining_design_temper = lining_design_temper;
	}
	public String getLining_max_press() {
		return lining_max_press;
	}
	public void setLining_max_press(String lining_max_press) {
		this.lining_max_press = lining_max_press;
	}
	public String getClamp_design_press() {
		return clamp_design_press;
	}
	public void setClamp_design_press(String clamp_design_press) {
		this.clamp_design_press = clamp_design_press;
	}
	public String getClamp_design_temper() {
		return clamp_design_temper;
	}
	public void setClamp_design_temper(String clamp_design_temper) {
		this.clamp_design_temper = clamp_design_temper;
	}
	public String getClamp_max_press() {
		return clamp_max_press;
	}
	public void setClamp_max_press(String clamp_max_press) {
		this.clamp_max_press = clamp_max_press;
	}
	public String getShell_medium() {
		return shell_medium;
	}
	public void setShell_medium(String shell_medium) {
		this.shell_medium = shell_medium;
	}
	public String getLining_medium() {
		return lining_medium;
	}
	public void setLining_medium(String lining_medium) {
		this.lining_medium = lining_medium;
	}
	public String getClamp_medium() {
		return clamp_medium;
	}
	public void setClamp_medium(String clamp_medium) {
		this.clamp_medium = clamp_medium;
	}
	public String getChamber_light() {
		return chamber_light;
	}
	public void setChamber_light(String chamber_light) {
		this.chamber_light = chamber_light;
	}
	public String getChamber_motor() {
		return chamber_motor;
	}
	public void setChamber_motor(String chamber_motor) {
		this.chamber_motor = chamber_motor;
	}
	public String getChamber_oxygen_test() {
		return chamber_oxygen_test;
	}
	public void setChamber_oxygen_test(String chamber_oxygen_test) {
		this.chamber_oxygen_test = chamber_oxygen_test;
	}
	public String getLadle_car_number() {
		return ladle_car_number;
	}
	public void setLadle_car_number(String ladle_car_number) {
		this.ladle_car_number = ladle_car_number;
	}
	public String getLadle_car_structure() {
		return ladle_car_structure;
	}
	public void setLadle_car_structure(String ladle_car_structure) {
		this.ladle_car_structure = ladle_car_structure;
	}
	public String getLadle_car_domain_num() {
		return ladle_car_domain_num;
	}
	public void setLadle_car_domain_num(String ladle_car_domain_num) {
		this.ladle_car_domain_num = ladle_car_domain_num;
	}
	public String getProperty_right_unit() {
		return property_right_unit;
	}
	public void setProperty_right_unit(String property_right_unit) {
		this.property_right_unit = property_right_unit;
	}
	public String getProperty_right_unit_code() {
		return property_right_unit_code;
	}
	public void setProperty_right_unit_code(String property_right_unit_code) {
		this.property_right_unit_code = property_right_unit_code;
	}
	public String getP20001002() {
		return p20001002;
	}
	public void setP20001002(String p20001002) {
		this.p20001002 = p20001002;
	}
	public String getP20001003() {
		return p20001003;
	}
	public void setP20001003(String p20001003) {
		this.p20001003 = p20001003;
	}
	public String getP20001004() {
		return p20001004;
	}
	public void setP20001004(String p20001004) {
		this.p20001004 = p20001004;
	}
	public String getP20001006() {
		return p20001006;
	}
	public void setP20001006(String p20001006) {
		this.p20001006 = p20001006;
	}
	public String getP20001007() {
		return p20001007;
	}
	public void setP20001007(String p20001007) {
		this.p20001007 = p20001007;
	}
	public String getP20001008() {
		return p20001008;
	}
	public void setP20001008(String p20001008) {
		this.p20001008 = p20001008;
	}
	public String getP20001009() {
		return p20001009;
	}
	public void setP20001009(String p20001009) {
		this.p20001009 = p20001009;
	}
	public String getP20001010() {
		return p20001010;
	}
	public void setP20001010(String p20001010) {
		this.p20001010 = p20001010;
	}
	public String getP20001011() {
		return p20001011;
	}
	public void setP20001011(String p20001011) {
		this.p20001011 = p20001011;
	}
	public String getP20001012() {
		return p20001012;
	}
	public void setP20001012(String p20001012) {
		this.p20001012 = p20001012;
	}
	public String getP20001013() {
		return p20001013;
	}
	public void setP20001013(String p20001013) {
		this.p20001013 = p20001013;
	}
	public String getP20001014() {
		return p20001014;
	}
	public void setP20001014(String p20001014) {
		this.p20001014 = p20001014;
	}
	public String getP20001015() {
		return p20001015;
	}
	public void setP20001015(String p20001015) {
		this.p20001015 = p20001015;
	}
	public String getP20001016() {
		return p20001016;
	}
	public void setP20001016(String p20001016) {
		this.p20001016 = p20001016;
	}
	public String getP20001030() {
		return p20001030;
	}
	public void setP20001030(String p20001030) {
		this.p20001030 = p20001030;
	}
	public String getP20001042() {
		return p20001042;
	}
	public void setP20001042(String p20001042) {
		this.p20001042 = p20001042;
	}
	public String getP20001043() {
		return p20001043;
	}
	public void setP20001043(String p20001043) {
		this.p20001043 = p20001043;
	}
	public String getP20001045() {
		return p20001045;
	}
	public void setP20001045(String p20001045) {
		this.p20001045 = p20001045;
	}
	public String getP_czjz() {
		return p_czjz;
	}
	public void setP_czjz(String p_czjz) {
		this.p_czjz = p_czjz;
	}
	public String getP_lhgwxph() {
		return p_lhgwxph;
	}
	public void setP_lhgwxph(String p_lhgwxph) {
		this.p_lhgwxph = p_lhgwxph;
	}
	public String getP_gsjzxcd() {
		return p_gsjzxcd;
	}
	public void setP_gsjzxcd(String p_gsjzxcd) {
		this.p_gsjzxcd = p_gsjzxcd;
	}
	public String getP_gsjzxkd() {
		return p_gsjzxkd;
	}
	public void setP_gsjzxkd(String p_gsjzxkd) {
		this.p_gsjzxkd = p_gsjzxkd;
	}
	public String getP_gsjzxgd() {
		return p_gsjzxgd;
	}
	public void setP_gsjzxgd(String p_gsjzxgd) {
		this.p_gsjzxgd = p_gsjzxgd;
	}
	public String getP_bwcl() {
		return p_bwcl;
	}
	public void setP_bwcl(String p_bwcl) {
		this.p_bwcl = p_bwcl;
	}
	public String getP_yljgdjh() {
		return p_yljgdjh;
	}
	public void setP_yljgdjh(String p_yljgdjh) {
		this.p_yljgdjh = p_yljgdjh;
	}
	public String getP_ycrx() {
		return p_ycrx;
	}
	public void setP_ycrx(String p_ycrx) {
		this.p_ycrx = p_ycrx;
	}
	public String getP_xzfs() {
		return p_xzfs;
	}
	public void setP_xzfs(String p_xzfs) {
		this.p_xzfs = p_xzfs;
	}
	public String getP_bwfs() {
		return p_bwfs;
	}
	public void setP_bwfs(String p_bwfs) {
		this.p_bwfs = p_bwfs;
	}
	public String getP_kzzl() {
		return p_kzzl;
	}
	public void setP_kzzl(String p_kzzl) {
		this.p_kzzl = p_kzzl;
	}
	public String getP_mzzl() {
		return p_mzzl;
	}
	public void setP_mzzl(String p_mzzl) {
		this.p_mzzl = p_mzzl;
	}
	public String getP_gtmp() {
		return p_gtmp;
	}
	public void setP_gtmp(String p_gtmp) {
		this.p_gtmp = p_gtmp;
	}
	public String getP_gtwj() {
		return p_gtwj;
	}
	public void setP_gtwj(String p_gtwj) {
		this.p_gtwj = p_gtwj;
	}
	public String getP_gtzc() {
		return p_gtzc;
	}
	public void setP_gtzc(String p_gtzc) {
		this.p_gtzc = p_gtzc;
	}
	public String getP_gtntcl() {
		return p_gtntcl;
	}
	public void setP_gtntcl(String p_gtntcl) {
		this.p_gtntcl = p_gtntcl;
	}
	public String getP_gtwtcl() {
		return p_gtwtcl;
	}
	public void setP_gtwtcl(String p_gtwtcl) {
		this.p_gtwtcl = p_gtwtcl;
	}
	public String getP_syyl() {
		return p_syyl;
	}
	public void setP_syyl(String p_syyl) {
		this.p_syyl = p_syyl;
	}

	public String getP_zggzyl() {
		return p_zggzyl;
	}
	public void setP_zggzyl(String p_zggzyl) {
		this.p_zggzyl = p_zggzyl;
	}
	
	public String getP_sjwd() {
		return p_sjwd;
	}
	public void setP_sjwd(String p_sjwd) {
		this.p_sjwd = p_sjwd;
	}
	public String getP_yxzdczl() {
		return p_yxzdczl;
	}
	public void setP_yxzdczl(String p_yxzdczl) {
		this.p_yxzdczl = p_yxzdczl;
	}
	public String getP_czxs() {
		return p_czxs;
	}
	public void setP_czxs(String p_czxs) {
		this.p_czxs = p_czxs;
	}
	public String getP_rkwz() {
		return p_rkwz;
	}
	public void setP_rkwz(String p_rkwz) {
		this.p_rkwz = p_rkwz;
	}
	public String getP_mpwz() {
		return p_mpwz;
	}
	public void setP_mpwz(String p_mpwz) {
		this.p_mpwz = p_mpwz;
	}
	public String getP_gtys() {
		return p_gtys;
	}
	public void setP_gtys(String p_gtys) {
		this.p_gtys = p_gtys;
	}
	public String getP_gtttbh() {
		return p_gtttbh;
	}
	public void setP_gtttbh(String p_gtttbh) {
		this.p_gtttbh = p_gtttbh;
	}
	public String getP_gtftbh() {
		return p_gtftbh;
	}
	public void setP_gtftbh(String p_gtftbh) {
		this.p_gtftbh = p_gtftbh;
	}
	public String getP_gtwtbh() {
		return p_gtwtbh;
	}
	public void setP_gtwtbh(String p_gtwtbh) {
		this.p_gtwtbh = p_gtwtbh;
	}
	public String getP_aqfsl() {
		return p_aqfsl;
	}
	public void setP_aqfsl(String p_aqfsl) {
		this.p_aqfsl = p_aqfsl;
	}
	public String getP_bppsl() {
		return p_bppsl;
	}
	public void setP_bppsl(String p_bppsl) {
		this.p_bppsl = p_bppsl;
	}
	public String getP_jjqdhsl() {
		return p_jjqdhsl;
	}
	public void setP_jjqdhsl(String p_jjqdhsl) {
		this.p_jjqdhsl = p_jjqdhsl;
	}
	public String getP_fzsssl() {
		return p_fzsssl;
	}
	public void setP_fzsssl(String p_fzsssl) {
		this.p_fzsssl = p_fzsssl;
	}
	public String getP_yyjsl() {
		return p_yyjsl;
	}
	public void setP_yyjsl(String p_yyjsl) {
		this.p_yyjsl = p_yyjsl;
	}
	public String getCp_by1() {
		return cp_by1;
	}
	public void setCp_by1(String cp_by1) {
		this.cp_by1 = cp_by1;
	}
	public String getCp_by2() {
		return cp_by2;
	}
	public void setCp_by2(String cp_by2) {
		this.cp_by2 = cp_by2;
	}
	public String getCbz() {
		return cbz;
	}
	public void setCbz(String cbz) {
		this.cbz = cbz;
	}
	public String getP20001046() {
		return p20001046;
	}
	public void setP20001046(String p20001046) {
		this.p20001046 = p20001046;
	}
	public String getP20003001() {
		return p20003001;
	}
	public void setP20003001(String p20003001) {
		this.p20003001 = p20003001;
	}
	public String getP20003002() {
		return p20003002;
	}
	public void setP20003002(String p20003002) {
		this.p20003002 = p20003002;
	}
	public String getDesign_units_code() {
		return design_units_code;
	}
	public void setDesign_units_code(String design_units_code) {
		this.design_units_code = design_units_code;
	}
	public String getDesign_units_name() {
		return design_units_name;
	}
	public void setDesign_units_name(String design_units_name) {
		this.design_units_name = design_units_name;
	}
	public String getDesign_date() {
		return design_date;
	}
	public void setDesign_date(String design_date) {
		this.design_date = design_date;
	}
	public String getP_sjyl() {
		return p_sjyl;
	}
	public void setP_sjyl(String p_sjyl) {
		this.p_sjyl = p_sjyl;
	}
	public String getCp_by3() {
		return cp_by3;
	}
	public void setCp_by3(String cp_by3) {
		this.cp_by3 = cp_by3;
	}
	public String getCp_by4() {
		return cp_by4;
	}
	public void setCp_by4(String cp_by4) {
		this.cp_by4 = cp_by4;
	}
	public String getCp_by5() {
		return cp_by5;
	}
	public void setCp_by5(String cp_by5) {
		this.cp_by5 = cp_by5;
	}
	public String getCp_by6() {
		return cp_by6;
	}
	public void setCp_by6(String cp_by6) {
		this.cp_by6 = cp_by6;
	}
	public String getCp_by7() {
		return cp_by7;
	}
	public void setCp_by7(String cp_by7) {
		this.cp_by7 = cp_by7;
	}

}
