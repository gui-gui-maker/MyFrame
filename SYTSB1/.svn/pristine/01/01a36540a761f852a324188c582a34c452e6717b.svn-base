package com.khnt.oa.car.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "OA_CAR_RECORD")
public class CarRecord implements BaseEntity{

    private String id;//主键id

    private String carId;//车子id

    private String reason;//未交车的情况

    private java.util.Date regDate;//登记时间

    private String regWeek;//登记对应周

    public void setId(String value){
        this.id = value;
    }
    public void setCarId(String value){
        this.carId = value;
    }
    public void setReason(String value){
        this.reason = value;
    }
    public void setRegDate(java.util.Date value){
        this.regDate = value;
    }
    public void setRegWeek(String value){
        this.regWeek = value;
    }
    @Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
    public String getId(){
        return this.id;
    }
    @Column(name ="CAR_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getCarId(){
        return this.carId;
    }
    @Column(name ="REASON",unique=false,nullable=true,insertable=true,updatable=true,length=512)
    public String getReason(){
        return this.reason;
    }
    @Column(name ="REG_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getRegDate(){
        return this.regDate;
    }
    @Column(name ="REG_WEEK",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getRegWeek(){
        return this.regWeek;
    }


} 
