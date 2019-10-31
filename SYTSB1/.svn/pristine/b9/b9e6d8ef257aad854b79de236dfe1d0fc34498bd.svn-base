package com.lsts.qualitymanage.bean;

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
@Table(name = "TJY2_QUALITY_TYFABH")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tyfabh implements BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;//${columnb.remarks}

    private String schemeName;//方案名称

    private String registrant;//登记人

    private String registrantId;//登记人id

    private java.util.Date registrantDate;//登记时间

    private String identifier;//编号

    private String status;//状态

    private String equipmentVariety;//设备种类

    private String equipmentCategoryPz;//设备类别或品种

    private String equipmentType;//检验类别

    private String remarks;//备注

    private java.util.Date jhwctime;//计划完成时间

    private String applyName;//申请人

    private String applyNameId;//申请人id

    private java.util.Date applyTime;//申请日期

    private String fabh;//服务部计划的方案编号

    private String ywfwbjbr;//业务服务部经办人签字

    private java.util.Date ywfwbjbrTime;//业务服务部经办人时间
    
    private String fabhId;//服务部计划的方案编号id
    
    private String cancelReason;//作废原因
    
    public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
    public void setFabhId(String fabhId) {
		this.fabhId = fabhId;
	}
	public void setId(String value){
        this.id = value;
    }
    public void setSchemeName(String value){
        this.schemeName = value;
    }
    public void setRegistrant(String value){
        this.registrant = value;
    }
    public void setRegistrantId(String value){
        this.registrantId = value;
    }
    public void setRegistrantDate(java.util.Date value){
        this.registrantDate = value;
    }
    public void setIdentifier(String value){
        this.identifier = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    public void setEquipmentVariety(String value){
        this.equipmentVariety = value;
    }
    public void setEquipmentCategoryPz(String value){
        this.equipmentCategoryPz = value;
    }
    public void setEquipmentType(String value){
        this.equipmentType = value;
    }
    public void setRemarks(String value){
        this.remarks = value;
    }
    public void setJhwctime(java.util.Date value){
        this.jhwctime = value;
    }
    public void setApplyName(String value){
        this.applyName = value;
    }
    public void setApplyNameId(String value){
        this.applyNameId = value;
    }
    public void setApplyTime(java.util.Date value){
        this.applyTime = value;
    }
    public void setFabh(String value){
        this.fabh = value;
    }
    public void setYwfwbjbr(String value){
        this.ywfwbjbr = value;
    }
    public void setYwfwbjbrTime(java.util.Date value){
        this.ywfwbjbrTime = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="SCHEME_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getSchemeName(){
        return this.schemeName;
    }
    @Column(name ="REGISTRANT",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getRegistrant(){
        return this.registrant;
    }
    @Column(name ="REGISTRANT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getRegistrantId(){
        return this.registrantId;
    }
    @Column(name ="REGISTRANT_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getRegistrantDate(){
        return this.registrantDate;
    }
    @Column(name ="IDENTIFIER",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getIdentifier(){
        return this.identifier;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="EQUIPMENT_VARIETY",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentVariety(){
        return this.equipmentVariety;
    }
    @Column(name ="EQUIPMENT_CATEGORY_PZ",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentCategoryPz(){
        return this.equipmentCategoryPz;
    }
    @Column(name ="EQUIPMENT_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentType(){
        return this.equipmentType;
    }
    @Column(name ="REMARKS",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getRemarks(){
        return this.remarks;
    }
    @Column(name ="JHWCTIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getJhwctime(){
        return this.jhwctime;
    }
    @Column(name ="APPLY_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getApplyName(){
        return this.applyName;
    }
    @Column(name ="APPLY_NAME_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getApplyNameId(){
        return this.applyNameId;
    }
    @Column(name ="APPLY_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getApplyTime(){
        return this.applyTime;
    }
    @Column(name ="FABH",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getFabh(){
        return this.fabh;
    }
    @Column(name ="YWFWBJBR",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getYwfwbjbr(){
        return this.ywfwbjbr;
    }
    @Column(name ="YWFWBJBR_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getYwfwbjbrTime(){
        return this.ywfwbjbrTime;
    }
    @Column(name ="FABH_ID",unique=false,nullable=true,insertable=true,updatable=true,length=4000)
    public String getFabhId() {
		return fabhId;
	}
    @Column(name ="CANCEL_REASON",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getCancelReason() {
		return cancelReason;
	}
} 
