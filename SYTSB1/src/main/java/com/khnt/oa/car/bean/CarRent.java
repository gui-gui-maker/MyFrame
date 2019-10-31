package com.khnt.oa.car.bean;

import com.khnt.core.crud.bean.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/*******************************************************************************
 * 
 * <p>
 *  
 * </p>
 * 
 * @ClassName CarRent
 * @JDK 1.5
 * @author 
 * @date 2
 */
@Entity
@Table(name = "OA_CAR_RENT")
public class CarRent implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;//
	private String agentId;//经办人id
	private String agentName;//经办人姓名
	private Date leaseTime;//租车时间
	private String company;//租赁公司
	private String phone;//公司联系电话
	private String reason;//租车事由
	private Float money;//租车费用
	private String applyDepartmentId;//用车部门id
	private String applyDepartmentName;//用车部门
	private String driver;//驾驶员
	private String carId;//车牌id
	private String carNum;//车牌号
	private Date returnTime;//规还时间
	private String status;//车辆状态0：未归还；1：已归还
	private String startKm;//起始公里数
	private String endKm;//结算公里数

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	@Column(name="AGENT_ID")
	public String getAgentId(){
		return agentId;
	}

	public void setAgentId(String agentId){
		this.agentId=agentId;
	}

	@Column(name="AGENT_NAME")
	public String getAgentName(){
		return agentName;
	}

	public void setAgentName(String agentName){
		this.agentName=agentName;
	}

	@Column(name="LEASE_TIME")
	public Date getLeaseTime(){
		return leaseTime;
	}

	public void setLeaseTime(Date leaseTime){
		this.leaseTime=leaseTime;
	}

	@Column(name="COMPANY")
	public String getCompany(){
		return company;
	}

	public void setCompany(String company){
		this.company=company;
	}

	@Column(name="PHONE")
	public String getPhone(){
		return phone;
	}

	public void setPhone(String phone){
		this.phone=phone;
	}

	@Column(name="REASON")
	public String getReason(){
		return reason;
	}

	public void setReason(String reason){
		this.reason=reason;
	}

	@Column(name="MONEY")
	public Float getMoney(){
		return money;
	}

	public void setMoney(Float money){
		this.money=money;
	}
	
	@Column(name="DRIVER")
	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	@Column(name="APPLY_DEPARTMENT_ID")
	public String getApplyDepartmentId(){
		return applyDepartmentId;
	}

	public void setApplyDepartmentId(String applyDepartmentId){
		this.applyDepartmentId=applyDepartmentId;
	}

	@Column(name="APPLY_DEPARTMENT_NAME")
	public String getApplyDepartmentName(){
		return applyDepartmentName;
	}

	public void setApplyDepartmentName(String applyDepartmentName){
		this.applyDepartmentName=applyDepartmentName;
	}

	@Column(name="RETURN_TIME")
	public Date getReturnTime(){
		return returnTime;
	}

	public void setReturnTime(Date returnTime){
		this.returnTime=returnTime;
	}
	
	@Column(name="CAR_NUM")
	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}
	
	@Column(name="CAR_ID")
	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name="START_KM")
	public String getStartKm(){
		return startKm;
	}

	public void setStartKm(String startKm){
		this.startKm=startKm;
	}

	@Column(name="END_KM")
	public String getEndKm(){
		return endKm;
	}

	public void setEndKm(String endKm){
		this.endKm=endKm;
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
