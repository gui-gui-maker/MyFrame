package com.scts.car.bean;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_CAR_MPG")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarMpg implements BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;//主键id

    private String fkCarId;//车辆id

    private String fkFuelCardId;//车辆卡id

    private java.util.Date yearMonth;//年月份

    private java.math.BigDecimal kmInitial;//月初公里数

    private java.math.BigDecimal kmFinal;//月末公里数

    private java.math.BigDecimal kmReal;//实际公里数

    private java.math.BigDecimal oilMoney;//加油金额

    private java.math.BigDecimal oilLitres;//加油升数

    private java.math.BigDecimal oilWear;//油耗

    private String createUserId;//创建人id

    private String createUserName;//创建人

    private java.util.Date createDate;//创建时间

    private String lastModifyUserId;//最后修改人id

    private String lastModifyUserName;//最后修改人

    private java.util.Date lastModifyDate;//最后修改时间

	private String dataStatus;//数据状态

    public void setId(String value){
        this.id = value;
    }
    public void setFkCarId(String value){
        this.fkCarId = value;
    }
    public void setFkFuelCardId(String value){
        this.fkFuelCardId = value;
    }
    public void setYearMonth(java.util.Date value){
        this.yearMonth = value;
    }
    public void setKmInitial(java.math.BigDecimal value){
        this.kmInitial = value;
    }
    public void setKmFinal(java.math.BigDecimal value){
        this.kmFinal = value;
    }
    public void setKmReal(java.math.BigDecimal value){
        this.kmReal = value;
    }
    public void setOilMoney(java.math.BigDecimal value){
        this.oilMoney = value;
    }
    public void setOilLitres(java.math.BigDecimal value){
        this.oilLitres = value;
    }
    public void setOilWear(java.math.BigDecimal value){
        this.oilWear = value;
    }
    public void setCreateUserId(String value){
        this.createUserId = value;
    }
    public void setCreateUserName(String value){
        this.createUserName = value;
    }
    public void setCreateDate(java.util.Date value){
        this.createDate = value;
    }
    public void setLastModifyUserId(String value){
        this.lastModifyUserId = value;
    }
    public void setLastModifyUserName(String value){
        this.lastModifyUserName = value;
    }
    public void setLastModifyDate(java.util.Date value){
        this.lastModifyDate = value;
    }
    public void setDataStatus(String value){
        this.dataStatus = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="FK_CAR_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFkCarId(){
        return this.fkCarId;
    }
    @Column(name ="FK_FUEL_CARD_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFkFuelCardId(){
        return this.fkFuelCardId;
    }
    @Column(name ="YEAR_MONTH",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getYearMonth(){
        return this.yearMonth;
    }
    @Column(name ="KM_INITIAL",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getKmInitial(){
        return this.kmInitial;
    }
    @Column(name ="KM_FINAL",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getKmFinal(){
        return this.kmFinal;
    }
    @Column(name ="KM_REAL",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getKmReal(){
        return this.kmReal;
    }
    @Column(name ="OIL_MONEY",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getOilMoney(){
        return this.oilMoney;
    }
    @Column(name ="OIL_LITRES",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getOilLitres(){
        return this.oilLitres;
    }
    @Column(name ="OIL_WEAR",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getOilWear(){
        return this.oilWear;
    }
    @Column(name ="CREATE_USER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getCreateUserId(){
        return this.createUserId;
    }
    @Column(name ="CREATE_USER_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getCreateUserName(){
        return this.createUserName;
    }
    @Column(name ="CREATE_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getCreateDate(){
        return this.createDate;
    }
    @Column(name ="LAST_MODIFY_USER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getLastModifyUserId(){
        return this.lastModifyUserId;
    }
    @Column(name ="LAST_MODIFY_USER_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getLastModifyUserName(){
        return this.lastModifyUserName;
    }
    @Column(name ="LAST_MODIFY_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getLastModifyDate(){
        return this.lastModifyDate;
    }
    @Column(name ="DATA_STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getDataStatus(){
        return this.dataStatus;
    }


} 
