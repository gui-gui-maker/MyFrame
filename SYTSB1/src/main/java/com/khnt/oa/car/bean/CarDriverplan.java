package com.khnt.oa.car.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "OA_CAR_DRIVERPLAN")
public class CarDriverplan implements BaseEntity{

    private String id;//主键id

    private String driverId;//驾驶员id

    private String driver;//驾驶员

    private String driverTelephone;//联系电话

    private java.util.Date planDate;//出车日期

    private String planWeek;//出车周

    private String planReason;//出车事由

    private String remark;//备注
    
    private String unitId;//单位id
    
    private CarInfo car;

    public void setId(String value){
        this.id = value;
    }
    public void setDriverId(String value){
        this.driverId = value;
    }
    public void setDriver(String value){
        this.driver = value;
    }
    public void setDriverTelephone(String value){
        this.driverTelephone = value;
    }
    public void setPlanDate(java.util.Date value){
        this.planDate = value;
    }
    public void setPlanWeek(String value){
        this.planWeek = value;
    }
    public void setPlanReason(String value){
        this.planReason = value;
    }
    public void setRemark(String value){
        this.remark = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="DRIVER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getDriverId(){
        return this.driverId;
    }
    @Column(name ="DRIVER",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getDriver(){
        return this.driver;
    }
    @Column(name ="DRIVER_TELEPHONE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getDriverTelephone(){
        return this.driverTelephone;
    }
    @Column(name ="PLAN_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getPlanDate(){
        return this.planDate;
    }
    @Column(name ="PLAN_WEEK",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getPlanWeek(){
        return this.planWeek;
    }
    @Column(name ="PLAN_REASON",unique=false,nullable=true,insertable=true,updatable=true,length=512)
    public String getPlanReason(){
        return this.planReason;
    }
    @Column(name ="REMARK",unique=false,nullable=true,insertable=true,updatable=true,length=512)
    public String getRemark(){
        return this.remark;
    }
    @Column(name="UNITID")
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	
	@ManyToOne
	@JoinColumn(name="CARID")
	public CarInfo getCar() {
		return car;
	}
	public void setCar(CarInfo car) {
		this.car = car;
	}


} 
