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
 * @ClassName CarSafe
 * @JDK 1.5
 * @author 
 * @date 2
 */
@Entity
@Table(name = "OA_CAR_SAFE")
public class CarSafe implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;//编号
	private CarInfo car;//
	private String writeMan;//经办人
	private String writeManCode;//经办人编号
	private String safeCompany;//保险公司
	private String safeMoney;//保费
	private String phone;//联系电话
	private Date startDate;//投保日期
	private Date endDate;//结束时间
	private String remark;
	private String safeType;
	
	private String managers;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CAR_ID")
	public CarInfo getCar() {
		return car;
	}

	public void setCar(CarInfo car) {
		this.car = car;
	}

	@Column(name="WRITE_MAN")
	public String getWriteMan(){
		return writeMan;
	}

	public void setWriteMan(String writeMan){
		this.writeMan=writeMan;
	}

	@Column(name="WRITE_MAN_CODE")
	public String getWriteManCode(){
		return writeManCode;
	}

	public void setWriteManCode(String writeManCode){
		this.writeManCode=writeManCode;
	}

	@Column(name="SAFE_COMPANY")
	public String getSafeCompany(){
		return safeCompany;
	}

	public void setSafeCompany(String safeCompany){
		this.safeCompany=safeCompany;
	}

	@Column(name="SAFE_MONEY")
	public String getSafeMoney(){
		return safeMoney;
	}

	public void setSafeMoney(String safeMoney){
		this.safeMoney=safeMoney;
	}

	@Column(name="PHONE")
	public String getPhone(){
		return phone;
	}

	public void setPhone(String phone){
		this.phone=phone;
	}

	@Column(name="START_DATE")
	public Date getStartDate(){
		return startDate;
	}

	public void setStartDate(Date startDate){
		this.startDate=startDate;
	}

	@Column(name="END_DATE")
	public Date getEndDate(){
		return endDate;
	}

	public void setEndDate(Date endDate){
		this.endDate=endDate;
	}
	
	
	@Column(name="REMARK")

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="SAFE_TYPE")
	public String getSafeType() {
		return safeType;
	}

	public void setSafeType(String safeType) {
		this.safeType = safeType;
	}
	
	@Column(name="MANAGERS")
	public String getManagers() {
		return managers;
	}

	public void setManagers(String managers) {
		this.managers = managers;
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
