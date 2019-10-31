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
 * 锅炉參數
 * 
 * @author 肖慈边 2014-1-15
 * 
 */

@Entity
@JsonIgnoreProperties(ignoreUnknown = true, value = { "deviceDocument" })
@Table(name = "BASE_DEVICE_BOILER")
public class BoilerPara implements BaseEntity{

	
	private static final long serialVersionUID = 1L;
	
    private String id;//id

//    private String fk_tsjc_device_document_id;//设备基本信息ID

    private String registration_num;//使用登记证编号

    private String boiler_model;//锅炉型号

    private String boiler_room_type;//锅炉房类型

    private String manufacturer;//制造国

    private String fk_inspection_unit_id;//产品监检单位ID

    private String boiler_structure;//锅炉结构形式

    private String design_pressure;//设计工作压力

    private String permit_pressure;//许可使用压力

    private String rated_output;//额定出力

    private String outlet_temperature;//介质出口温度

    private String heating_method;//加热方式

    private String fuel_type;//燃料种类

    private String boiler_use;//锅炉用途

    private String use_state;//使用状态

    private String combustion_type;//燃烧方式

    private String water_treatment;//水处理方式

    private String deoxidization;//除氧方式

    private String deslagging;//除渣方式

    private String smoke_dust;//消烟除尘方式

    private String stoker_num;//单位司炉数量

    private String water_quality_num;//水质人员数量

    private String p10001011;//水循环方式

    private String p10001012;//燃烧器布置方式

    private String p10001013;//设计燃料可燃基挥发份

    private String p10001015;//锅炉再热蒸汽调温方式

    private String p10001016;//锅炉设计规范

    private String p10001017;//锅炉制造规范

    private String p10001018;//设备新旧状况

    private String p10001019;//锅炉监检形式

    private String p10001020;//锅炉过热蒸汽调温方式

    private String p10001021;//锅炉补给水处理方式

    private String p10001022;//锅炉汽水分离装置(方式)

    private String p10001023;//锅炉水质报告水处理方法

    private String p10001024;//锅炉水压报告试验理由

    private String p10001025;//油压报告试验理由

    private String p10002005;//锅炉最大连续蒸发量(t/h)

    private String p10002006;//锅炉再热蒸汽流量(t/h)

    private String p10002007;//锅筒工作压力(MPa):

    private String p10002008;//锅炉过热器出口压力(MPa)

    private String p10002009;//锅炉再热器入口压力(MPa)

    private String p10002010;//锅炉再热器出口压力(MPa)

    private String p10002011;//锅炉给水温度(C)

    private String p10002012;//锅炉过热器出口温度(C)

    private String p10002013;//锅炉再热器入口温度(C)

    private String p10002014;//锅炉再热器出口温度(C)

    private String p10002015;//直流锅炉启动压力(MPa)

    private String p10002016;//直流锅炉启动流量(t/h)

    private String p10002017;//锅炉设计燃料应用基低位发热值(大卡)

    private String p_slmbzfs;//受热面布置方式

    private String p_ztcl;//主体材料

    private String cp_by1;//产品监检单位名称

    
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
//
//	public void setFk_tsjc_device_document_id(String fk_tsjc_device_document_id) {
//		this.fk_tsjc_device_document_id = fk_tsjc_device_document_id;
//	}

	public String getRegistration_num() {
		return registration_num;
	}

	public void setRegistration_num(String registration_num) {
		this.registration_num = registration_num;
	}

	public String getBoiler_model() {
		return boiler_model;
	}

	public void setBoiler_model(String boiler_model) {
		this.boiler_model = boiler_model;
	}

	public String getBoiler_room_type() {
		return boiler_room_type;
	}

	public void setBoiler_room_type(String boiler_room_type) {
		this.boiler_room_type = boiler_room_type;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getFk_inspection_unit_id() {
		return fk_inspection_unit_id;
	}

	public void setFk_inspection_unit_id(String fk_inspection_unit_id) {
		this.fk_inspection_unit_id = fk_inspection_unit_id;
	}

	public String getBoiler_structure() {
		return boiler_structure;
	}

	public void setBoiler_structure(String boiler_structure) {
		this.boiler_structure = boiler_structure;
	}

	public String getDesign_pressure() {
		return design_pressure;
	}

	public void setDesign_pressure(String design_pressure) {
		this.design_pressure = design_pressure;
	}

	public String getPermit_pressure() {
		return permit_pressure;
	}

	public void setPermit_pressure(String permit_pressure) {
		this.permit_pressure = permit_pressure;
	}

	public String getRated_output() {
		return rated_output;
	}

	public void setRated_output(String rated_output) {
		this.rated_output = rated_output;
	}

	public String getOutlet_temperature() {
		return outlet_temperature;
	}

	public void setOutlet_temperature(String outlet_temperature) {
		this.outlet_temperature = outlet_temperature;
	}

	public String getHeating_method() {
		return heating_method;
	}

	public void setHeating_method(String heating_method) {
		this.heating_method = heating_method;
	}

	public String getFuel_type() {
		return fuel_type;
	}

	public void setFuel_type(String fuel_type) {
		this.fuel_type = fuel_type;
	}

	public String getBoiler_use() {
		return boiler_use;
	}

	public void setBoiler_use(String boiler_use) {
		this.boiler_use = boiler_use;
	}

	public String getUse_state() {
		return use_state;
	}

	public void setUse_state(String use_state) {
		this.use_state = use_state;
	}

	public String getCombustion_type() {
		return combustion_type;
	}

	public void setCombustion_type(String combustion_type) {
		this.combustion_type = combustion_type;
	}

	public String getWater_treatment() {
		return water_treatment;
	}

	public void setWater_treatment(String water_treatment) {
		this.water_treatment = water_treatment;
	}

	public String getDeoxidization() {
		return deoxidization;
	}

	public void setDeoxidization(String deoxidization) {
		this.deoxidization = deoxidization;
	}

	public String getDeslagging() {
		return deslagging;
	}

	public void setDeslagging(String deslagging) {
		this.deslagging = deslagging;
	}

	public String getSmoke_dust() {
		return smoke_dust;
	}

	public void setSmoke_dust(String smoke_dust) {
		this.smoke_dust = smoke_dust;
	}

	public String getStoker_num() {
		return stoker_num;
	}

	public void setStoker_num(String stoker_num) {
		this.stoker_num = stoker_num;
	}

	public String getWater_quality_num() {
		return water_quality_num;
	}

	public void setWater_quality_num(String water_quality_num) {
		this.water_quality_num = water_quality_num;
	}

	public String getP10001011() {
		return p10001011;
	}

	public void setP10001011(String p10001011) {
		this.p10001011 = p10001011;
	}

	public String getP10001012() {
		return p10001012;
	}

	public void setP10001012(String p10001012) {
		this.p10001012 = p10001012;
	}

	public String getP10001013() {
		return p10001013;
	}

	public void setP10001013(String p10001013) {
		this.p10001013 = p10001013;
	}

	public String getP10001015() {
		return p10001015;
	}

	public void setP10001015(String p10001015) {
		this.p10001015 = p10001015;
	}

	public String getP10001016() {
		return p10001016;
	}

	public void setP10001016(String p10001016) {
		this.p10001016 = p10001016;
	}

	public String getP10001017() {
		return p10001017;
	}

	public void setP10001017(String p10001017) {
		this.p10001017 = p10001017;
	}

	public String getP10001018() {
		return p10001018;
	}

	public void setP10001018(String p10001018) {
		this.p10001018 = p10001018;
	}

	public String getP10001019() {
		return p10001019;
	}

	public void setP10001019(String p10001019) {
		this.p10001019 = p10001019;
	}

	public String getP10001020() {
		return p10001020;
	}

	public void setP10001020(String p10001020) {
		this.p10001020 = p10001020;
	}

	public String getP10001021() {
		return p10001021;
	}

	public void setP10001021(String p10001021) {
		this.p10001021 = p10001021;
	}

	public String getP10001022() {
		return p10001022;
	}

	public void setP10001022(String p10001022) {
		this.p10001022 = p10001022;
	}

	public String getP10001023() {
		return p10001023;
	}

	public void setP10001023(String p10001023) {
		this.p10001023 = p10001023;
	}

	public String getP10001024() {
		return p10001024;
	}

	public void setP10001024(String p10001024) {
		this.p10001024 = p10001024;
	}

	public String getP10001025() {
		return p10001025;
	}

	public void setP10001025(String p10001025) {
		this.p10001025 = p10001025;
	}

	public String getP10002005() {
		return p10002005;
	}

	public void setP10002005(String p10002005) {
		this.p10002005 = p10002005;
	}

	public String getP10002006() {
		return p10002006;
	}

	public void setP10002006(String p10002006) {
		this.p10002006 = p10002006;
	}

	public String getP10002007() {
		return p10002007;
	}

	public void setP10002007(String p10002007) {
		this.p10002007 = p10002007;
	}

	public String getP10002008() {
		return p10002008;
	}

	public void setP10002008(String p10002008) {
		this.p10002008 = p10002008;
	}

	public String getP10002009() {
		return p10002009;
	}

	public void setP10002009(String p10002009) {
		this.p10002009 = p10002009;
	}

	public String getP10002010() {
		return p10002010;
	}

	public void setP10002010(String p10002010) {
		this.p10002010 = p10002010;
	}

	public String getP10002011() {
		return p10002011;
	}

	public void setP10002011(String p10002011) {
		this.p10002011 = p10002011;
	}

	public String getP10002012() {
		return p10002012;
	}

	public void setP10002012(String p10002012) {
		this.p10002012 = p10002012;
	}

	public String getP10002013() {
		return p10002013;
	}

	public void setP10002013(String p10002013) {
		this.p10002013 = p10002013;
	}

	public String getP10002014() {
		return p10002014;
	}

	public void setP10002014(String p10002014) {
		this.p10002014 = p10002014;
	}

	public String getP10002015() {
		return p10002015;
	}

	public void setP10002015(String p10002015) {
		this.p10002015 = p10002015;
	}

	public String getP10002016() {
		return p10002016;
	}

	public void setP10002016(String p10002016) {
		this.p10002016 = p10002016;
	}

	public String getP10002017() {
		return p10002017;
	}

	public void setP10002017(String p10002017) {
		this.p10002017 = p10002017;
	}

	public String getP_slmbzfs() {
		return p_slmbzfs;
	}

	public void setP_slmbzfs(String p_slmbzfs) {
		this.p_slmbzfs = p_slmbzfs;
	}

	public String getP_ztcl() {
		return p_ztcl;
	}

	public void setP_ztcl(String p_ztcl) {
		this.p_ztcl = p_ztcl;
	}

	public String getCp_by1() {
		return cp_by1;
	}

	public void setCp_by1(String cp_by1) {
		this.cp_by1 = cp_by1;
	}

	

   

 
   


} 
