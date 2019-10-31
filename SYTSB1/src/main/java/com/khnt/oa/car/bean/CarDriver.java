package com.khnt.oa.car.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "OA_CAR_DRIVER")
public class CarDriver implements BaseEntity{

    private String id;//主键

    private String name;//姓名

    private String telphone;//联系电话

    private String idno;//身份证号码

    private String code;//编号

    private java.math.BigDecimal driverAge;//实际驾龄

    private java.util.Date cardDate;//初次申领驾照日期
    
    private String unitId;//所属单位id
    
    private java.util.Date validDateStart;//有效起始日期
    
    private String quasiCarType;//准驾车型
    
    private String fileNumber;//档案编号
    
    private String status;//驾驶证状态
    
    private java.math.BigDecimal points;//扣分情况
    
    private String validYear;//有效期限
    
    private String employeeId;

    public void setId(String value){
        this.id = value;
    }
    public void setName(String value){
        this.name = value;
    }
    public void setTelphone(String value){
        this.telphone = value;
    }
    public void setIdno(String value){
        this.idno = value;
    }
    public void setQuasiCarType(String value){
        this.quasiCarType = value;
    }
    public void setDriverAge(java.math.BigDecimal value){
        this.driverAge = value;
    }
    public void setCardDate(java.util.Date value){
        this.cardDate = value;
    }
    public void setValidDateStart(java.util.Date value){
        this.validDateStart = value;
    }
    public void setCode(String value){
        this.code = value;
    }
    public void setFileNumber(String value){
        this.fileNumber = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    public void setPoints(java.math.BigDecimal value){
        this.points = value;
    }
    public void setValidYear(String value){
        this.validYear = value;
    }
    public void setEmployeeId(String value){
        this.employeeId = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="NAME",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getName(){
        return this.name;
    }
    @Column(name ="TELPHONE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getTelphone(){
        return this.telphone;
    }
    @Column(name ="IDNO",unique=false,nullable=true,insertable=true,updatable=true,length=18)
    public String getIdno(){
        return this.idno;
    }
    @Column(name ="CODE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getCode(){
        return this.code;
    }
    @Column(name ="DRIVER_AGE",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getDriverAge(){
        return this.driverAge;
    }
    @Column(name ="CARD_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getCardDate(){
        return this.cardDate;
    }
    @Column(name ="VALID_DATE_START",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getValidDateStart(){
        return this.validDateStart;
    }
    @Column(name ="QUASI_CAR_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getQuasiCarType(){
        return this.quasiCarType;
    }
    @Column(name ="FILE_NUMBER",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFileNumber(){
        return this.fileNumber;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="POINTS",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getPoints(){
        return this.points;
    }
    @Column(name ="VALID_YEAR",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getValidYear(){
        return this.validYear;
    }
    @Column(name ="EMPLOYEE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getEmployeeId(){
        return this.employeeId;
    }
    
    @Column(name="UNIT_ID")
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}


} 
