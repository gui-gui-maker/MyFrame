//package com.scts.transfer.bean;
//import java.text.SimpleDateFormat;
//import java.util.Collection;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import javax.persistence.Transient;
//
//import org.codehaus.jackson.annotate.JsonIgnoreProperties;
//import org.hibernate.annotations.GenericGenerator;
//
//import com.khnt.core.crud.bean.BaseEntity;
//import com.khnt.utils.StringUtil;
//
//import zjpt.common.bean.VThreeCertTogether;
//
///*******************************************************************************
// * 
// * <p>
// *  
// * </p>
// * 
// * @ClassName DeviceDocument
// * @JDK 1.5
// * @author CODER_V2.0
// * @date 2015-08-10 11:03:34
// */
//@Entity
//@Table(name = "TS_DEVICE_DOCUMENT")
//@JsonIgnoreProperties(ignoreUnknown = true)
//public class TransferDeviceDocument implements BaseEntity {
//	private static final long serialVersionUID = 1L;
//	
//	private String registrationNum;//使用登记证号
//	private String registrationCode;//注册代码
//	private String registrationAgencies;//注册登记机构
//	private Date registrationDate;//注册登记日期
//	private String deviceRegistrationCode;//设备注册代码
//	private Date updateDate;//更新日期
//	private String internalNum;//单位内部编码
//	private String registrationOp;//注册登记人员
//	private String companyCode;//使用单位三码合一号
//	private String companyName;//使用单位
//	private String companyAddress;//使用单位地址
//	private String securityManagement;//安全管理机构
//	private String securityOp;//安全管理人员
//	private String securityTel;//安全管理人员联系电话
//	private String deviceSort;//设备类别
//	private String deviceName;//设备名称 created
//	private String fkCompanyInfoMakeId;//制造单位ID
//	private String makeDate;//制造日期
//	private String factoryCode;//出厂编号
//	private String fkCompanyInfoInstallId;//安装单位ID
//	private String installFinishDate;//安装竣工日期
//	private String maintainUnitCode ;//维保单位ID
//	private String maintainUnitName; // 维保单位名称
//	private String maintenanceMan; // 维保单位联系人
//	private String maintenanceTel; // 维保单位联系人电话
//	private String useDate;//投用日期
//	private String deviceStatus;//设备状态(0,告知,1,使用,2,停用,3,报废) - 0 告知 1 审批中 2 使用 4 拆除 5停用   6 报废 7 废弃信息
//	private String deviceSortCode;//设备类别代码
//	//private String deviceClassCode;//设备品种代码
//	//private String deviceTypeName;//设备品种名称
//	private String makeUnitsName;//制造单位名称
//	private String constructionUnitsName;//安装单位名称
//	//private String synStatus;//同步状态(1或者空表示属于监察的信息，检验不能修改，2表示检验可以修改)
//	private String createdBy;//创建人
//	private String lastUpdBy;//最后更新人
//	private Date lastUpdDate;//最后更新日期
//	private String deviceAreaCode;//设备所在区域
//	private String inspectConclusion;//检验结论
//	private Date inspectNextDate;//下次检验日期 
//	private Date inspectDate;//检验日期
//	private String deviceUsePlace;//设备使用地点
//	private String deviceCode;//设备代码
//	private String deviceStreetCode;//设备所在街道
//	//private String importId;//导入时原福建系统ID
//	//private String extension;//申请延期标识
//	private String useSite;//使用单位分厂/分站/楼盘
//	private String note;//备注
//	private String checkType;//检验类别
//	private String useSiteAddress;//使用单位分厂/分站/楼盘地址
//	private String makeLicenceNo;//制造许可证号
//	//private String flag;//1特检院 2 局里
//	//private String ifWarn;//是否预警
//	//private Date advanceDate;//预警日期
//	private Date createdDate;//创建日期
//	private String deviceModel;//设备型号
//	private String deviceSpec;//规格
//	private String devicePattern;//设备形式
//	private String point;//坐标
//	private String pointx;//坐标
//	private String pointy;//坐标
//	private String electronicLabelNum;//电子标签序列号
//	//private String docFlag;//
//	private String attr9;//
//	private String attr1;//
//	private String attr10;//
//	private String attr4;//
//	//private String charId;//
//	private Date attr7;//
//	private Date attr8;//
//	private String attr2;//
//	private String attr5;//
//	private String id;//
//	private String attr6;//
//	private String attr3;//
//
//	@Id
//	@GeneratedValue(generator = "idGenerator")
//	@GenericGenerator(name = "idGenerator", strategy = "assigned")
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
//	
//	//public DeviceDocument deviceDocument;
//
//	/*@Column(name="device_class")
//	public String getDeviceTypeName() {
//		return deviceTypeName;
//	}
//	public void setDeviceTypeName(String deviceTypeName) {
//		this.deviceTypeName = deviceTypeName;
//	}*/
//	@Column(name="registration_num")
//	public String getRegistrationNum(){
//		return registrationNum;
//	}
//		
//	public void setRegistrationNum(String registrationNum){
//	this.registrationNum=registrationNum;
//	}
//
//	@Column(name="registration_code")
//	public String getRegistrationCode(){
//		return registrationCode;
//	}
//		
//	public void setRegistrationCode(String registrationCode){
//	this.registrationCode=registrationCode;
//	}
//
//	@Column(name="registration_agencies")
//	public String getRegistrationAgencies(){
//		return registrationAgencies;
//	}
//		
//	public void setRegistrationAgencies(String registrationAgencies){
//	this.registrationAgencies=registrationAgencies;
//	}
//
//	@Column(name="registration_date")
//	public Date getRegistrationDate(){
//		return registrationDate;
//	}
//		
//	public void setRegistrationDate(Date registrationDate){
//	this.registrationDate=registrationDate;
//	}
//
//	@Column(name="device_registration_code")
//	public String getDeviceRegistrationCode(){
//		return deviceRegistrationCode;
//	}
//		
//	public void setDeviceRegistrationCode(String deviceRegistrationCode){
//	this.deviceRegistrationCode=deviceRegistrationCode;
//	}
//
//	@Column(name="update_date")
//	public Date getUpdateDate(){
//		return updateDate;
//	}
//		
//	public void setUpdateDate(Date updateDate){
//	this.updateDate=updateDate;
//	}
//
//	@Column(name="internal_num")
//	public String getInternalNum(){
//		return internalNum;
//	}
//		
//	public void setInternalNum(String internalNum){
//	this.internalNum=internalNum;
//	}
//
//	@Column(name="registration_op")
//	public String getRegistrationOp(){
//		return registrationOp;
//	}
//		
//	public void setRegistrationOp(String registrationOp){
//	this.registrationOp=registrationOp;
//	}
//
//	@Column(name="fk_company_info_use_id")
//	public String getCompanyCode(){
//		return companyCode;
//	}
//		
//	public void setCompanyCode(String companyCode){
//	this.companyCode=companyCode;
//	}
//    
//	@Column(name="company_name")
//	public String getCompanyName() {
//		return companyName;
//	}
//	public void setCompanyName(String companyName) {
//		this.companyName = companyName;
//	}
//	@Column(name="security_management")
//	public String getSecurityManagement(){
//		return securityManagement;
//	}
//		
//	public void setSecurityManagement(String securityManagement){
//	this.securityManagement=securityManagement;
//	}
//
//	@Column(name="security_op")
//	public String getSecurityOp(){
//		return securityOp;
//	}
//		
//	public void setSecurityOp(String securityOp){
//	this.securityOp=securityOp;
//	}
//
//	@Column(name="security_tel")
//	public String getSecurityTel(){
//		return securityTel;
//	}
//		
//	public void setSecurityTel(String securityTel){
//	this.securityTel=securityTel;
//	}
//
//	@Column(name="device_sort")
//	public String getDeviceSort(){
//		return deviceSort;
//	}
//		
//	public void setDeviceSort(String deviceSort){
//	this.deviceSort=deviceSort;
//	}
//
//	@Column(name="device_name")
//	public String getDeviceName(){
//		return deviceName;
//	}
//		
//	public void setDeviceName(String deviceName){
//	this.deviceName=deviceName;
//	}
//
//	@Column(name="fk_company_info_make_id")
//	public String getFkCompanyInfoMakeId(){
//		return fkCompanyInfoMakeId;
//	}
//		
//	public void setFkCompanyInfoMakeId(String fkCompanyInfoMakeId){
//	this.fkCompanyInfoMakeId=fkCompanyInfoMakeId;
//	}
//
//	@Column(name="make_date")
//	public String getMakeDate(){
//		return makeDate;
//	}
//		
//	public void setMakeDate(String makeDate){
//	this.makeDate=makeDate;
//	}
//
//	@Column(name="factory_code")
//	public String getFactoryCode(){
//		return factoryCode;
//	}
//		
//	public void setFactoryCode(String factoryCode){
//	this.factoryCode=factoryCode;
//	}
//
//	@Column(name="fk_company_info_install_id")
//	public String getFkCompanyInfoInstallId(){
//		return fkCompanyInfoInstallId;
//	}
//		
//	public void setFkCompanyInfoInstallId(String fkCompanyInfoInstallId){
//	this.fkCompanyInfoInstallId=fkCompanyInfoInstallId;
//	}
//
//	@Column(name="install_finish_date")
//	public String getInstallFinishDate(){
//		return installFinishDate;
//	}
//		
//	public void setInstallFinishDate(String installFinishDate){
//	this.installFinishDate=installFinishDate;
//	}
//
//	@Column(name="use_date")
//	public String getUseDate(){
//		return useDate;
//	}
//		
//	public void setUseDate(String useDate){
//	this.useDate=useDate;
//	}
//
//	@Column(name="device_status")
//	public String getDeviceStatus(){
//		return deviceStatus;
//	}
//		
//	public void setDeviceStatus(String deviceStatus){
//	this.deviceStatus=deviceStatus;
//	}
//
//	@Column(name="device_sort_code")
//	public String getDeviceSortCode(){
//		return deviceSortCode;
//	}
//		
//	public void setDeviceSortCode(String deviceSortCode){
//	this.deviceSortCode=deviceSortCode;
//	}
//
//	@Column(name="make_units_name")
//	public String getMakeUnitsName(){
//		return makeUnitsName;
//	}
//		
//	public void setMakeUnitsName(String makeUnitsName){
//	this.makeUnitsName=makeUnitsName;
//	}
//
//	@Column(name="construction_units_name")
//	public String getConstructionUnitsName(){
//		return constructionUnitsName;
//	}
//		
//	public void setConstructionUnitsName(String constructionUnitsName){
//	this.constructionUnitsName=constructionUnitsName;
//	}
//
//	/*@Column(name="syn_status")
//	public String getSynStatus(){
//		return synStatus;
//	}
//		
//	public void setSynStatus(String synStatus){
//	this.synStatus=synStatus;
//	}*/
//
//	@Column(name="created_by")
//	public String getCreatedBy(){
//		return createdBy;
//	}
//		
//	public void setCreatedBy(String createdBy){
//	this.createdBy=createdBy;
//	}
//
//	@Column(name="last_upd_by")
//	public String getLastUpdBy(){
//		return lastUpdBy;
//	}
//		
//	public void setLastUpdBy(String lastUpdBy){
//	this.lastUpdBy=lastUpdBy;
//	}
//
//	@Column(name="last_upd_date")
//	public Date getLastUpdDate(){
//		return lastUpdDate;
//	}
//		
//	public void setLastUpdDate(Date lastUpdDate){
//	this.lastUpdDate=lastUpdDate;
//	}
//
//	@Column(name="device_area_code")
//	public String getDeviceAreaCode(){
//		return deviceAreaCode;
//	}
//		
//	public void setDeviceAreaCode(String deviceAreaCode){
//	this.deviceAreaCode=deviceAreaCode;
//	}
//
//	@Column(name="inspect_conclusion")
//	public String getInspectConclusion(){
//		return inspectConclusion;
//	}
//		
//	public void setInspectConclusion(String inspectConclusion){
//	this.inspectConclusion=inspectConclusion;
//	}
//
//	@Column(name="inspect_next_date")
//	public Date getInspectNextDate(){
//		return inspectNextDate;
//	}
//	/*@Column(name="device_class_code")	
//	public String getDeviceClassCode() {
//		return deviceClassCode;
//	}
//	public void setDeviceClassCode(String deviceClassCode) {
//		this.deviceClassCode = deviceClassCode;
//	}*/
//
//	
//	public void setInspectNextDate(Date inspectNextDate){
//	this.inspectNextDate=inspectNextDate;
//	}
//
//	@Column(name="inspect_date")
//	public Date getInspectDate(){
//		return inspectDate;
//	}
//		
//	public void setInspectDate(Date inspectDate){
//	this.inspectDate=inspectDate;
//	}
//
//	@Column(name="device_use_place")
//	public String getDeviceUsePlace(){
//		return deviceUsePlace;
//	}
//		
//	public void setDeviceUsePlace(String deviceUsePlace){
//	this.deviceUsePlace=deviceUsePlace;
//	}
//
//	@Column(name="device_code")
//	public String getDeviceCode(){
//		return deviceCode;
//	}
//		
//	public void setDeviceCode(String deviceCode){
//	this.deviceCode=deviceCode;
//	}
//
//	@Column(name="device_street_code")
//	public String getDeviceStreetCode(){
//		return deviceStreetCode;
//	}
//		
//	public void setDeviceStreetCode(String deviceStreetCode){
//	this.deviceStreetCode=deviceStreetCode;
//	}
//
//	/*@Column(name="import_id")
//	public String getImportId(){
//		return importId;
//	}
//		
//	public void setImportId(String importId){
//	this.importId=importId;
//	}
//
//	@Column(name="extension")
//	public String getExtension(){
//		return extension;
//	}
//		
//	public void setExtension(String extension){
//	this.extension=extension;
//	}*/
//
//	@Column(name="use_site")
//	public String getUseSite(){
//		return useSite;
//	}
//		
//	public void setUseSite(String useSite){
//	this.useSite=useSite;
//	}
//
//	@Column(name="note")
//	public String getNote(){
//		return note;
//	}
//		
//	public void setNote(String note){
//	this.note=note;
//	}
//
//	@Column(name="check_type")
//	public String getCheckType(){
//		return checkType;
//	}
//		
//	public void setCheckType(String checkType){
//	this.checkType=checkType;
//	}
//
//	@Column(name="use_site_address")
//	public String getUseSiteAddress(){
//		return useSiteAddress;
//	}
//		
//	public void setUseSiteAddress(String useSiteAddress){
//	this.useSiteAddress=useSiteAddress;
//	}
//
//	@Column(name="make_licence_no")
//	public String getMakeLicenceNo(){
//		return makeLicenceNo;
//	}
//		
//	public void setMakeLicenceNo(String makeLicenceNo){
//	this.makeLicenceNo=makeLicenceNo;
//	}
///*
//	@Column(name="flag")
//	public String getFlag(){
//		return flag;
//	}
//		
//	public void setFlag(String flag){
//	this.flag=flag;
//	}
//
//	@Column(name="if_warn")
//	public String getIfWarn(){
//		return ifWarn;
//	}
//		
//	public void setIfWarn(String ifWarn){
//	this.ifWarn=ifWarn;
//	}
//
//	@Column(name="advance_date")
//	public Date getAdvanceDate(){
//		return advanceDate;
//	}
//		
//	public void setAdvanceDate(Date advanceDate){
//	this.advanceDate=advanceDate;
//	}*/
//
//	@Column(name="created_date")
//	public Date getCreatedDate(){
//		return createdDate;
//	}
//		
//	public void setCreatedDate(Date createdDate){
//	this.createdDate=createdDate;
//	}
//
//	/*@Column(name="doc_flag")
//	public String getDocFlag(){
//		return docFlag;
//	}
//		
//	public void setDocFlag(String docFlag){
//	this.docFlag=docFlag;
//	}*/
//
//	@Column(name="attr9")
//	public String getAttr9(){
//		return attr9;
//	}
//		
//	public void setAttr9(String attr9){
//	this.attr9=attr9;
//	}
//
//
//	@Column(name="attr1")
//	public String getAttr1(){
//		return attr1;
//	}
//		
//	public void setAttr1(String attr1){
//	this.attr1=attr1;
//	}
//
//	@Column(name="attr10")
//	public String getAttr10(){
//		return attr10;
//	}
//		
//	public void setAttr10(String attr10){
//	this.attr10=attr10;
//	}
//
//	@Column(name="attr4")
//	public String getAttr4(){
//		return attr4;
//	}
//		
//	public void setAttr4(String attr4){
//	this.attr4=attr4;
//	}
//
//	/*@Column(name="char_id")
//	public String getCharId(){
//		return charId;
//	}
//		
//	public void setCharId(String charId){
//	this.charId=charId;
//	}
//*/
//	@Column(name="attr7")
//	public Date getAttr7(){
//		return attr7;
//	}
//		
//	public void setAttr7(Date attr7){
//	this.attr7=attr7;
//	}
//
//	@Column(name="attr8")
//	public Date getAttr8(){
//		return attr8;
//	}
//		
//	public void setAttr8(Date attr8){
//	this.attr8=attr8;
//	}
//
//	@Column(name="attr2")
//	public String getAttr2(){
//		return attr2;
//	}
//		
//	public void setAttr2(String attr2){
//	this.attr2=attr2;
//	}
//
//	@Column(name="attr5")
//	public String getAttr5(){
//		return attr5;
//	}
//		
//	public void setAttr5(String attr5){
//	this.attr5=attr5;
//	}
//
//
//
//	@Column(name="attr6")
//	public String getAttr6(){
//		return attr6;
//	}
//		
//	public void setAttr6(String attr6){
//	this.attr6=attr6;
//	}
//
//	@Column(name="attr3")
//	public String getAttr3(){
//		return attr3;
//	}
//		
//	public void setAttr3(String attr3){
//	this.attr3=attr3;
//	}
//	
//	
//	@Column(name="fk_maintain_unit_id")
//	public String getMaintainUnitCode() {
//		return maintainUnitCode;
//	}
//	public void setMaintainUnitCode(String maintainUnitCode) {
//		this.maintainUnitCode = maintainUnitCode;
//	}
//	@Column(name="maintain_unit_name")
//	public String getMaintainUnitName() {
//		return maintainUnitName;
//	}
//	public void setMaintainUnitName(String maintainUnitName) {
//		this.maintainUnitName = maintainUnitName;
//	}
//	@Column(name="maintenance_man")
//	public String getMaintenanceMan() {
//		return maintenanceMan;
//	}
//	public void setMaintenanceMan(String maintenanceMan) {
//		this.maintenanceMan = maintenanceMan;
//	}
//	@Column(name="maintenance_tel")
//	public String getMaintenanceTel() {
//		return maintenanceTel;
//	}
//	public void setMaintenanceTel(String maintenanceTel) {
//		this.maintenanceTel = maintenanceTel;
//	}
//	@Column(name="device_model")
//	public String getDeviceModel() {
//		return deviceModel;
//	}
//	public void setDeviceModel(String deviceModel) {
//		this.deviceModel = deviceModel;
//	}
//	@Column(name="device_spec")
//	public String getDeviceSpec() {
//		return deviceSpec;
//	}
//	public void setDeviceSpec(String deviceSpec) {
//		this.deviceSpec = deviceSpec;
//	}
//	@Column(name="device_pattern")
//	public String getDevicePattern() {
//		return devicePattern;
//	}
//	public void setDevicePattern(String devicePattern) {
//		this.devicePattern = devicePattern;
//	}
//	@Column(name="point")
//	public String getPoint() {
//		return point;
//	}
//	public void setPoint(String point) {
//		this.point = point;
//	}
//	@Column(name="electronic_label_num")
//	public String getElectronicLabelNum() {
//		return electronicLabelNum;
//	}
//	public void setElectronicLabelNum(String electronicLabelNum) {
//		this.electronicLabelNum = electronicLabelNum;
//	}
//
//		// 关联电梯参数对象
//		 public Collection<DeviceElevator> elevatorParas;
////		public ElevatorPara elevatorParas;
//
//		@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "deviceDocument", orphanRemoval = true)
//		public Collection<DeviceElevator> getElevatorParas() {
//			return elevatorParas;
//		}
//
//		public void setElevatorParas(Collection<DeviceElevator> elevatorParas) {
//			this.elevatorParas = elevatorParas;
//		}
//		
//		// 关联锅炉参数对象
//			
//		public Collection<DeviceBoiler> boiler;
//
//		@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "deviceDocument", orphanRemoval = true)
//		public Collection<DeviceBoiler> getBoiler() {
//			return boiler;
//		}
//
//		public void setBoiler(Collection<DeviceBoiler> boiler) {
//			this.boiler = boiler;
//		}
//		
//		// 关联起重参数对象
//		
//		public Collection<DeviceCrane> crane;
//
//		@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "deviceDocument", orphanRemoval = true)
//		public Collection<DeviceCrane> getCrane() {
//			return crane;
//		}
//
//		public void setCrane(Collection<DeviceCrane> crane) {
//			this.crane = crane;
//		}
//		
//		// 关联压力容器参数对象
//		
//		public Collection<DevicePressurevessels> pressurevessels;
//
//		@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "deviceDocument", orphanRemoval = true)
//		public Collection<DevicePressurevessels> getPressurevessels() {
//			return pressurevessels;
//		}
//
//		public void setPressurevessels(Collection<DevicePressurevessels> pressurevessels) {
//			this.pressurevessels = pressurevessels;
//		}
//		
//		// 关联压力管道参数对象
//		
//		public Collection<DevicePressurePipeline> pressurepipelines;
//		
//		@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "deviceDocument", orphanRemoval = true)
//		public Collection<DevicePressurePipeline> getPressurepipelines() {
//			return pressurepipelines;
//		}
//
//		public void setPressurepipelines(Collection<DevicePressurePipeline> pressurepipelines) {
//			this.pressurepipelines = pressurepipelines;
//		}
//		
//		// 关联厂内机动车参数对象
//		
//		public Collection<DeviceEngine> engine;
//
//		@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "deviceDocument", orphanRemoval = true)
//		public Collection<DeviceEngine> getEngine() {
//			return engine;
//		}
//
//		public void setEngine(Collection<DeviceEngine> engine) {
//			this.engine = engine;
//		}
//		
//		// 关联游乐设施参数对象
//		
//		public Collection<DeviceYlss> ridesPara;
//
//		@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "deviceDocument", orphanRemoval = true)
//		public Collection<DeviceYlss> getRidesPara() {
//			return ridesPara;
//		}
//
//		public void setRidesPara(Collection<DeviceYlss> ridesPara) {
//			this.ridesPara = ridesPara;
//		}
//		
//		//关联客运索道参数对象
//		public Collection<DeviceKysd> kysd;
//		@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "deviceDocument", orphanRemoval = true)
//		public Collection<DeviceKysd> getKysd() {
//			return kysd;
//		}
//		public void setKysd(Collection<DeviceKysd> kysd) {
//			this.kysd = kysd;
//		}
//		
//		// 关联安全阀参数对象
//		
//		public Collection<DeviceAccessory> accessory;
//
//	
//		@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "deviceDocument", orphanRemoval = true)
//		public Collection<DeviceAccessory> getAccessory() {
//			return accessory;
//		}
//
//		public void setAccessory(Collection<DeviceAccessory> accessory) {
//			this.accessory = accessory;
//		}
//
//		@Column(name="company_address")
//		public String getCompanyAddress() {
//			return companyAddress;
//		}
//		public void setCompanyAddress(String companyAddress) {
//			this.companyAddress = companyAddress;
//		}
//		@Column(name="pointx")
//		public String getPointx() {
//			return pointx;
//		}
//		public void setPointx(String pointx) {
//			this.pointx = pointx;
//		}
//		@Column(name="pointy")
//		public String getPointy() {
//			return pointy;
//		}
//		public void setPointy(String pointy) {
//			this.pointy = pointy;
//		}
//	/***************************************************************************
//	 * bean说明
//	 * 
//	 * @return
//	 */
//	public String toString() {
//		return "未说明";
//
//	}
//	
//	
//	public Map<String,String> toMap(){
//		Map<String,String> map = new HashMap<String,String>();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		map.put("registrationNum", StringUtil.transformNull(registrationNum));
//		map.put("deviceRegistrationCode", StringUtil.transformNull(deviceRegistrationCode));
//		map.put("registrationDate", registrationDate == null ? "" :sdf.format(registrationDate));
//		map.put("internalNum", StringUtil.transformNull(internalNum));
//		map.put("companyCode", StringUtil.transformNull(companyCode));
//		map.put("companyName", StringUtil.transformNull(companyName));
//		map.put("companyAddress", StringUtil.transformNull(companyAddress));
//		map.put("securityManagement", StringUtil.transformNull(securityManagement));
//		map.put("securityOp", StringUtil.transformNull(securityOp));
//		map.put("securityTel", StringUtil.transformNull(securityTel));
//		map.put("deviceSortCode", StringUtil.transformNull(deviceSortCode));
//		map.put("deviceName", StringUtil.transformNull(deviceName));
//		map.put("makeDate", StringUtil.transformNull(makeDate));
//		map.put("factoryCode", StringUtil.transformNull(factoryCode));
//		map.put("constructionUnitsName", StringUtil.transformNull(constructionUnitsName));
//		map.put("makeUnitsName", StringUtil.transformNull(makeUnitsName));
//		map.put("MaintainUnitCode", StringUtil.transformNull(maintainUnitCode));
//		map.put("maintainUnitName", StringUtil.transformNull(maintainUnitName));
//		map.put("maintenanceMan", StringUtil.transformNull(maintenanceMan));
//		map.put("maintenanceTel", StringUtil.transformNull(maintenanceTel));
//		map.put("useDate", StringUtil.transformNull(useDate));
//		map.put("deviceAreaCode", StringUtil.transformNull(deviceAreaCode));
//		map.put("inspectDate", inspectDate == null ? "" :sdf.format(inspectDate));
//		map.put("inspectNextDate", inspectNextDate == null ? "" :sdf.format(inspectNextDate));
//		map.put("inspectConclusion", StringUtil.transformNull(inspectConclusion));
//		map.put("deviceUsePlace", StringUtil.transformNull(deviceUsePlace));
//		map.put("deviceStreetCode", StringUtil.transformNull(deviceStreetCode));
//		map.put("rqCode", StringUtil.transformNull(electronicLabelNum));
//		map.put("deviceModel", StringUtil.transformNull(deviceModel));
//		map.put("deviceSpec", StringUtil.transformNull(deviceSpec));
//		map.put("devicePattern", StringUtil.transformNull(devicePattern));
//		
//		return map ;
//	}
//	
//	public Set<String> bean_to_set(){
//	    Set<String> set = new HashSet<String>();
//	    //set.add("internalNum");
//	    set.add("companyCode");
//	    set.add("companyName");
//	    set.add("companyAddress");
//	    set.add("securityManagement");
//	    set.add("securityOp");
//	    set.add("securityTel");
//	    set.add("deviceName");
//	    set.add("constructionUnitsName");
//	    set.add("inspectDate");
//	    set.add("inspectNextDate");
//	    set.add("inspectConclusion");
//	    set.add("makeUnitsName");
//	    set.add("MaintainUnitCode");
//	    set.add("maintenanceMan");
//	    set.add("maintenanceTel");
//	    set.add("deviceAreaCode");
//	    set.add("useDate");
//	    set.add("makeDate");
//	    set.add("deviceUsePlace");
//	    set.add("deviceStreetCode");
//	    set.add("deviceModel");
//	    set.add("deviceSpec");
//	    set.add("devicePattern");
//		return set;
//	}
//}
