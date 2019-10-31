package com.lsts.qualitymanage.bean;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_QUALITY_XYBZ_FILE")
public class QualityXybzFile implements BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;//检验标准文件id

    private String tzEquipmentType;//特种设备类型

    private String checkoutType;//检验类型

    private String status;//状态（停用、启用）

    private java.util.Date stopTime;//停用时间


    private java.util.Date effectiveStarttime;//有效期(开始)

    private java.util.Date effectiveStoptime;//有效期(结束)

    private String registrant;//登记人
    
    private String registrantId;//登记人id
    
    private String endRegistrant;//停用人
    
    private String endRegistrantId;//停用人id

    private java.util.Date registrantTime;//创建时间

    private String fileName;//文件名称

    private String fileId;//文件编号

    private String qualityXybzFileId;//质量体系文件的检验标准文件id

   
	public void setRegistrantId(String registrantId) {
		this.registrantId = registrantId;
	}
	public void setEndRegistrant(String endRegistrant) {
		this.endRegistrant = endRegistrant;
	}
	public void setEndRegistrantId(String endRegistrantId) {
		this.endRegistrantId = endRegistrantId;
	}
	public void setId(String value){
        this.id = value;
    }
    public void setTzEquipmentType(String value){
        this.tzEquipmentType = value;
    }
    public void setCheckoutType(String value){
        this.checkoutType = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    public void setStopTime(java.util.Date value){
        this.stopTime = value;
    }
    public void setEffectiveStarttime(java.util.Date value){
        this.effectiveStarttime = value;
    }
    public void setEffectiveStoptime(java.util.Date value){
        this.effectiveStoptime = value;
    }
    public void setRegistrant(String value){
        this.registrant = value;
    }
    public void setRegistrantTime(java.util.Date value){
        this.registrantTime = value;
    }
    public void setFileName(String value){
        this.fileName = value;
    }
    public void setFileId(String value){
        this.fileId = value;
    }
    public void setQualityXybzFileId(String value){
        this.qualityXybzFileId = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="TZ_EQUIPMENT_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getTzEquipmentType(){
        return this.tzEquipmentType;
    }
    @Column(name ="CHECKOUT_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getCheckoutType(){
        return this.checkoutType;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="STOP_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getStopTime(){
        return this.stopTime;
    }
    @Column(name ="EFFECTIVE_STARTTIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getEffectiveStarttime(){
        return this.effectiveStarttime;
    }
    @Column(name ="EFFECTIVE_STOPTIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getEffectiveStoptime(){
        return this.effectiveStoptime;
    }
    @Column(name ="REGISTRANT",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getRegistrant(){
        return this.registrant;
    }
    @Column(name ="REGISTRANT_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getRegistrantTime(){
        return this.registrantTime;
    }
    @Column(name ="FILE_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getFileName(){
        return this.fileName;
    }
    @Column(name ="FILE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFileId(){
        return this.fileId;
    }
    @Column(name ="QUALITY_XYBZ_FILE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getQualityXybzFileId(){
        return this.qualityXybzFileId;
    }
    @Column(name ="REGISTRANT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getRegistrantId() {
		return registrantId;
	}
    @Column(name ="END_REGISTRANT",unique=false,nullable=true,insertable=true,updatable=true,length=200)
	public String getEndRegistrant() {
		return endRegistrant;
	}
    @Column(name ="END_REGISTRANT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
	public String getEndRegistrantId() {
		return endRegistrantId;
	}
} 
