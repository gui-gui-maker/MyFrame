package com.khnt.oa.car.bean;

import com.khnt.core.crud.bean.BaseEntity;
import com.khnt.utils.DateUtil;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*******************************************************************************
 * 
 * <p>
 *  
 * </p>
 * 
 * @ClassName CarInfo
 * @JDK 1.5
 * @author 
 * @date 2
 */
@Entity
@Table(name = "OA_CAR_INFO")
public class CarInfo implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;//编号
	private String orgCode;//单位编号
	private String orgName;//单位名称
	private String approvalNumber;//批复文号
	private String carBrand;//车辆品牌
	private String address;//产地
	private String carType;//车型
	private String carNum;//车牌号
	private String changeNum;//变更车号
	private Date buyDate;//购车时间
	private String carMoney;//车辆原值
	private String carDisplacement;//排量
	private String carMileage;//行驶里程
	private String technicalCondition;//技术状况
	private String loadNumber;//核定载人数
	private String color;//颜色
	private String remark;//备注
	private String managerRoomCode;//所属处室编号
	private String managerRoomName;//所属处室
	private String state;//车辆状态
	private String driver;//驾驶员
	private String driverCode;//驾驶员编号
	private String dayRentalPrice;//日租价(元/日)
	private String basicMileage;//基本里程(公里/日)
	private String basicTime;//基本时间(小时/日)
	private String exceedMileagePrice;//超基本里程单价(元/公里) 
	private String exceedTimePrice;//超基本时间单价(元/小时)
	
	private String oilConsumption100;
	
	private String carState;	// 车辆使用状态（1：已收车/空闲可用 0：用车中 2：派车中）
	private Date carStateDate;
	
	private String carpicId;
	private String engineNo;//发动机号
	private String frameNo;//车架号码
	
	private String type;//分类
	private String repairType;//维修状态
	
	private String use_dep_id;		// 用车部门ID
	private String use_dep_name;	// 用车部门名称
	private Date   use_start_date;	// 用车开始时间
	private Date   use_end_date;	// 用车结束时间

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	@Column(name="ORG_CODE")
	public String getOrgCode(){
		return orgCode;
	}

	public void setOrgCode(String orgCode){
		this.orgCode=orgCode;
	}

	@Column(name="ORG_NAME")
	public String getOrgName(){
		return orgName;
	}

	public void setOrgName(String orgName){
		this.orgName=orgName;
	}

	@Column(name="APPROVAL_NUMBER")
	public String getApprovalNumber(){
		return approvalNumber;
	}

	public void setApprovalNumber(String approvalNumber){
		this.approvalNumber=approvalNumber;
	}

	@Column(name="CAR_BRAND")
	public String getCarBrand(){
		return carBrand;
	}

	public void setCarBrand(String carBrand){
		this.carBrand=carBrand;
	}

	@Column(name="ADDRESS")
	public String getAddress(){
		return address;
	}

	public void setAddress(String address){
		this.address=address;
	}

	@Column(name="CAR_TYPE")
	public String getCarType(){
		return carType;
	}

	public void setCarType(String carType){
		this.carType=carType;
	}

	@Column(name="CAR_NUM")
	public String getCarNum(){
		return carNum;
	}

	public void setCarNum(String carNum){
		this.carNum=carNum;
	}

	@Column(name="CHANGE_NUM")
	public String getChangeNum(){
		return changeNum;
	}

	public void setChangeNum(String changeNum){
		this.changeNum=changeNum;
	}

	@Column(name="BUY_DATE")
	public Date getBuyDate(){
		return buyDate;
	}

	public void setBuyDate(Date buyDate){
		this.buyDate=buyDate;
	}

	@Column(name="CAR_MONEY")
	public String getCarMoney(){
		return carMoney;
	}

	public void setCarMoney(String carMoney){
		this.carMoney=carMoney;
	}

	@Column(name="CAR_DISPLACEMENT")
	public String getCarDisplacement(){
		return carDisplacement;
	}

	public void setCarDisplacement(String carDisplacement){
		this.carDisplacement=carDisplacement;
	}

	@Column(name="CAR_MILEAGE")
	public String getCarMileage(){
		return carMileage;
	}

	public void setCarMileage(String carMileage){
		this.carMileage=carMileage;
	}

	@Column(name="TECHNICAL_CONDITION")
	public String getTechnicalCondition(){
		return technicalCondition;
	}

	public void setTechnicalCondition(String technicalCondition){
		this.technicalCondition=technicalCondition;
	}

	@Column(name="LOAD_NUMBER")
	public String getLoadNumber(){
		return loadNumber;
	}

	public void setLoadNumber(String loadNumber){
		this.loadNumber=loadNumber;
	}

	@Column(name="COLOR")
	public String getColor(){
		return color;
	}

	public void setColor(String color){
		this.color=color;
	}

	@Column(name="REMARK")
	public String getRemark(){
		return remark;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}

	@Column(name="MANAGER_ROOM_CODE")
	public String getManagerRoomCode(){
		return managerRoomCode;
	}

	public void setManagerRoomCode(String managerRoomCode){
		this.managerRoomCode=managerRoomCode;
	}

	@Column(name="MANAGER_ROOM_NAME")
	public String getManagerRoomName(){
		return managerRoomName;
	}

	public void setManagerRoomName(String managerRoomName){
		this.managerRoomName=managerRoomName;
	}

	@Column(name="STATE")
	public String getState(){
		return state;
	}

	public void setState(String state){
		this.state=state;
	}

	@Column(name="DRIVER")
	public String getDriver(){
		return driver;
	}

	public void setDriver(String driver){
		this.driver=driver;
	}

	@Column(name="DRIVER_CODE")
	public String getDriverCode(){
		return driverCode;
	}

	public void setDriverCode(String driverCode){
		this.driverCode=driverCode;
	}
	@Column(name="OIL_CONSUMPTION_100")
	public String getOilConsumption100(){
		return oilConsumption100;
	}

	public void setOilConsumption100(String oilConsumption100){
		this.oilConsumption100=oilConsumption100;
	}
	
	@Column(name="CAR_STATE")
	public String getCarState() {
		return carState;
	}

	public void setCarState(String carState) {
		this.carState = carState;
	}
	@Column(name="CAR_STATE_DATE")
	public Date getCarStateDate() {
		return carStateDate;
	}

	public void setCarStateDate(Date carStateDate) {
		this.carStateDate = carStateDate;
	}
	
	@Column(name="DAY_RENTAL_PRICE")
	public String getDayRentalPrice() {
		return dayRentalPrice;
	}

	public void setDayRentalPrice(String dayRentalPrice) {
		this.dayRentalPrice = dayRentalPrice;
	}

	@Column(name="BASIC_MILEAGE")
	public String getBasicMileage() {
		return basicMileage;
	}

	public void setBasicMileage(String basicMileage) {
		this.basicMileage = basicMileage;
	}

	@Column(name="BASIC_TIME")
	public String getBasicTime() {
		return basicTime;
	}

	public void setBasicTime(String basicTime) {
		this.basicTime = basicTime;
	}

	@Column(name="EXCEED_MILEAGE_PRICE")
	public String getExceedMileagePrice() {
		return exceedMileagePrice;
	}

	public void setExceedMileagePrice(String exceedMileagePrice) {
		this.exceedMileagePrice = exceedMileagePrice;
	}

	@Column(name="EXCEED_TIME_PRICE")
	public String getExceedTimePrice() {
		return exceedTimePrice;
	}

	public void setExceedTimePrice(String exceedTimePrice) {
		this.exceedTimePrice = exceedTimePrice;
	}

	@Column(name="CARPIC_ID")
	public String getCarpicId() {
		return carpicId;
	}

	public void setCarpicId(String carpicId) {
		this.carpicId = carpicId;
	}
	@Column(name="ENGINE_NO")
	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}
	@Column(name="FRAME_NO")
	public String getFrameNo() {
		return frameNo;
	}

	public void setFrameNo(String frameNo) {
		this.frameNo = frameNo;
	}
	
	@Column(name="TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name="REPAIR_TYPE")
	public String getRepairType() {
		return repairType;
	}

	public void setRepairType(String repairType) {
		this.repairType = repairType;
	}
	
	@Column(name="USE_DEP_ID")
	public String getUse_dep_id() {
		return use_dep_id;
	}

	public void setUse_dep_id(String use_dep_id) {
		this.use_dep_id = use_dep_id;
	}

	@Column(name="USE_DEP_NAME")
	public String getUse_dep_name() {
		return use_dep_name;
	}

	public void setUse_dep_name(String use_dep_name) {
		this.use_dep_name = use_dep_name;
	}

	@Column(name="USE_START_DATE")
	public Date getUse_start_date() {
		return use_start_date;
	}

	public void setUse_start_date(Date use_start_date) {
		this.use_start_date = use_start_date;
	}

	@Column(name="USE_END_DATE")
	public Date getUse_end_date() {
		return use_end_date;
	}

	public void setUse_end_date(Date use_end_date) {
		this.use_end_date = use_end_date;
	}

	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "未说明";

	}
}
