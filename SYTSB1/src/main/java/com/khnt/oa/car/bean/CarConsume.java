package com.khnt.oa.car.bean;

import java.util.Date;

/**
 * 车辆消耗bean
 * @author ch
 *
 */
public class CarConsume {
	public String car_num;//车牌号
	public String manager_room_name;//管理处(室)
	public String change_num;//变更号
	public Date buy_date;
	public String driver;
	public String km100;//百公里油耗(升)
	public String startKM;//起始公里数
	public String endKM;//结束公里数
	public float allKM;//实际里程
	public float V;//汽油量(升)
	public float y;// 汽油金额(元)
	public String getCar_num() {
		return car_num;
	}
	public void setCar_num(String car_num) {
		this.car_num = car_num;
	}
	public String getManager_room_name() {
		return manager_room_name;
	}
	public void setManager_room_name(String manager_room_name) {
		this.manager_room_name = manager_room_name;
	}
	public String getChange_num() {
		return change_num;
	}
	public void setChange_num(String change_num) {
		this.change_num = change_num;
	}
	public Date getBuy_date() {
		return buy_date;
	}
	public void setBuy_date(Date buy_date) {
		this.buy_date = buy_date;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getKm100() {
		return km100;
	}
	public void setKm100(String km100) {
		this.km100 = km100;
	}
	public String getStartKM() {
		return startKM;
	}
	public void setStartKM(String startKM) {
		this.startKM = startKM;
	}
	public String getEndKM() {
		return endKM;
	}
	public void setEndKM(String endKM) {
		this.endKM = endKM;
	}
	public float getAllKM() {
		return allKM;
	}
	public void setAllKM(float allKM) {
		this.allKM = allKM;
	}
	public float getV() {
		return V;
	}
	public void setV(float v) {
		V = v;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	
	
}
