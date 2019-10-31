package com.lsts.archives.bean;

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
@Table(name = "TJY2_ARCHIVES_FILE")
public class ArchivesFile implements BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;//档案归档id

    private String reportNumber;//报告编号

    private String certificateNumber;//证书编号

    private String productName;//产品名称

    private String productSpecifications;//产品规格

    private String serialNumber;//出厂编号

    private String productNumber;//产品编号

    private String useUnit;//使用单位

    private String manufactureUnit;//制造单位

    private String installUnit;//安装单位

    private String checker;//检验员

    private java.util.Date issuingTime;//发证日期

    private String registrant;//登记人

    private java.util.Date registrantTime;//登记时间

    private String checkerId;//检验员id

    private String status;//状态
    
    private String fj;//状态

   
	public void setFj(String fj) {
		this.fj = fj;
	}
	public void setId(String value){
        this.id = value;
    }
    public void setReportNumber(String value){
        this.reportNumber = value;
    }
    public void setCertificateNumber(String value){
        this.certificateNumber = value;
    }
    public void setProductName(String value){
        this.productName = value;
    }
    public void setProductSpecifications(String value){
        this.productSpecifications = value;
    }
    public void setSerialNumber(String value){
        this.serialNumber = value;
    }
    public void setProductNumber(String value){
        this.productNumber = value;
    }
    public void setUseUnit(String value){
        this.useUnit = value;
    }
    public void setManufactureUnit(String value){
        this.manufactureUnit = value;
    }
    public void setInstallUnit(String value){
        this.installUnit = value;
    }
    public void setChecker(String value){
        this.checker = value;
    }
    public void setIssuingTime(java.util.Date value){
        this.issuingTime = value;
    }
    public void setRegistrant(String value){
        this.registrant = value;
    }
    public void setRegistrantTime(java.util.Date value){
        this.registrantTime = value;
    }
    public void setCheckerId(String value){
        this.checkerId = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="REPORT_NUMBER",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getReportNumber(){
        return this.reportNumber;
    }
    @Column(name ="CERTIFICATE_NUMBER",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getCertificateNumber(){
        return this.certificateNumber;
    }
    @Column(name ="PRODUCT_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getProductName(){
        return this.productName;
    }
    @Column(name ="PRODUCT_SPECIFICATIONS",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getProductSpecifications(){
        return this.productSpecifications;
    }
    @Column(name ="SERIAL_NUMBER",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getSerialNumber(){
        return this.serialNumber;
    }
    @Column(name ="PRODUCT_NUMBER",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getProductNumber(){
        return this.productNumber;
    }
    @Column(name ="USE_UNIT",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getUseUnit(){
        return this.useUnit;
    }
    @Column(name ="MANUFACTURE_UNIT",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getManufactureUnit(){
        return this.manufactureUnit;
    }
    @Column(name ="INSTALL_UNIT",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getInstallUnit(){
        return this.installUnit;
    }
    @Column(name ="CHECKER",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getChecker(){
        return this.checker;
    }
    @Column(name ="ISSUING_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getIssuingTime(){
        return this.issuingTime;
    }
    @Column(name ="REGISTRANT",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getRegistrant(){
        return this.registrant;
    }
    @Column(name ="REGISTRANT_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getRegistrantTime(){
        return this.registrantTime;
    }
    @Column(name ="CHECKER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getCheckerId(){
        return this.checkerId;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="FJ",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getFj() {
		return fj;
	}

} 
