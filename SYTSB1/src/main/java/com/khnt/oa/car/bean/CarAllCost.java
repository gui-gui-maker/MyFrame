package com.khnt.oa.car.bean;

import java.util.Date;

/**
 * 车辆所有费用bean
 * 
 * @author ch
 * 
 */
public class CarAllCost {
	public String carId;// 车子主键id
	public String unitId;// 机构id
	public String carNum;// 车牌号
	public String manageDep;// 管理部门
	public Date buyDate;// 购买日期
	public String carBrand;// c车牌车型
	public float carMil;// 行驶里程
	public float carMilMonth;// 本月形式里程
	public float carOilMonth;// 维修费(元)
	public float carOil100;// 保养费(元)
	public float carRoadFee;// 保险费(元)
	public float carWashFee;// 过路过桥费(元)
	public float carParkFee;// 洗车费(元)
	public float carRepairFee;// 停车费(元)
	public float carYearFee;// 审车费(元)
	public float carBeautiFee;// 审车费(元)
	public float carBxFee;// 保险费(元)

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getManageDep() {
		return manageDep;
	}

	public void setManageDep(String manageDep) {
		this.manageDep = manageDep;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public float getCarMil() {
		return carMil;
	}

	public void setCarMil(float carMil) {
		this.carMil = carMil;
	}

	public float getCarMilMonth() {
		return carMilMonth;
	}

	public void setCarMilMonth(float carMilMonth) {
		this.carMilMonth = carMilMonth;
	}

	public float getCarOilMonth() {
		return carOilMonth;
	}

	public void setCarOilMonth(float carOilMonth) {
		this.carOilMonth = carOilMonth;
	}

	public float getCarOil100() {
		return carOil100;
	}

	public void setCarOil100(float carOil100) {
		this.carOil100 = carOil100;
	}

	public float getCarRoadFee() {
		return carRoadFee;
	}

	public void setCarRoadFee(float carRoadFee) {
		this.carRoadFee = carRoadFee;
	}

	public float getCarWashFee() {
		return carWashFee;
	}

	public void setCarWashFee(float carWashFee) {
		this.carWashFee = carWashFee;
	}

	public float getCarParkFee() {
		return carParkFee;
	}

	public void setCarParkFee(float carParkFee) {
		this.carParkFee = carParkFee;
	}

	public float getCarRepairFee() {
		return carRepairFee;
	}

	public void setCarRepairFee(float carRepairFee) {
		this.carRepairFee = carRepairFee;
	}

	public float getCarYearFee() {
		return carYearFee;
	}

	public void setCarYearFee(float carYearFee) {
		this.carYearFee = carYearFee;
	}

	public float getCarBeautiFee() {
		return carBeautiFee;
	}

	public void setCarBeautiFee(float carBeautiFee) {
		this.carBeautiFee = carBeautiFee;
	}

	public float getCarBxFee() {
		return carBxFee;
	}

	public void setCarBxFee(float carBxFee) {
		this.carBxFee = carBxFee;
	}

}
