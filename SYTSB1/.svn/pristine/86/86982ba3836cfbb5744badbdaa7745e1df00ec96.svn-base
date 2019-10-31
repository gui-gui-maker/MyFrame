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
 * @ClassName CarCost
 * @JDK 1.5
 * @author 
 * @date 2
 */
@Entity
@Table(name = "OA_CAR_COST")
public class CarCost implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;//编号
	private CarInfo car;//
	private String costType;//费用类型
	private String cost;//车辆费用
	private String writeMan;//录入人
	private String writeManCode;//录入人编号
	private Date writeTime;//录入时间
	private Date registerDate;//登记日期
	private String remark;
	
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

	@Column(name="COST_TYPE")
	public String getCostType(){
		return costType;
	}

	public void setCostType(String costType){
		this.costType=costType;
	}

	@Column(name="COST")
	public String getCost(){
		return cost;
	}

	public void setCost(String cost){
		this.cost=cost;
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

	@Column(name="WRITE_TIME")
	public Date getWriteTime(){
		return writeTime;
	}

	public void setWriteTime(Date writeTime){
		this.writeTime=writeTime;
	}

	@Column(name="REGISTER_DATE")
	public Date getRegisterDate(){
		return registerDate;
	}

	public void setRegisterDate(Date registerDate){
		this.registerDate=registerDate;
	}
	
	

	@Column(name="REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
