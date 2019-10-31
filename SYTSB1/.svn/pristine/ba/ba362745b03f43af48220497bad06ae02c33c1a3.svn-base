package com.lsts.archives.bean;

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
@Table(name = "TJY2_ARCHIVES_BORROW_SUB")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArchivesBorrowSub implements BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;//${columnb.remarks}

    private java.util.Date advanceTime;//检验时间

    private String fkReportId;//报告外键ID

    private String reportSn;//报告书编号

    private String checkOpName;//参检人员名字

    private String checkUnitId;//检验部门ID

    private String orgName;//检验部门

    private String comName;//使用单位

    private String deviceRegistrationCode;//设备注册代码

    private java.math.BigDecimal reportCount;//台数

    private String fkArchivesBorrowId;//主表外键ID

    private String isBack;//'0'表示未归还，'1'已归还

    public void setId(String value){
        this.id = value;
    }
    public void setAdvanceTime(java.util.Date value){
        this.advanceTime = value;
    }
    public void setFkReportId(String value){
        this.fkReportId = value;
    }
    public void setReportSn(String value){
        this.reportSn = value;
    }
    public void setCheckOpName(String value){
        this.checkOpName = value;
    }
    public void setCheckUnitId(String value){
        this.checkUnitId = value;
    }
    public void setOrgName(String value){
        this.orgName = value;
    }
    public void setComName(String value){
        this.comName = value;
    }
    public void setDeviceRegistrationCode(String value){
        this.deviceRegistrationCode = value;
    }
    public void setReportCount(java.math.BigDecimal value){
        this.reportCount = value;
    }
    public void setFkArchivesBorrowId(String value){
        this.fkArchivesBorrowId = value;
    }
    public void setIsBack(String value){
        this.isBack = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="ADVANCE_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getAdvanceTime(){
        return this.advanceTime;
    }
    @Column(name ="FK_REPORT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFkReportId(){
        return this.fkReportId;
    }
    @Column(name ="REPORT_SN",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getReportSn(){
        return this.reportSn;
    }
    @Column(name ="CHECK_OP_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getCheckOpName(){
        return this.checkOpName;
    }
    @Column(name ="CHECK_UNIT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getCheckUnitId(){
        return this.checkUnitId;
    }
    @Column(name ="ORG_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getOrgName(){
        return this.orgName;
    }
    @Column(name ="COM_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getComName(){
        return this.comName;
    }
    @Column(name ="DEVICE_REGISTRATION_CODE",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getDeviceRegistrationCode(){
        return this.deviceRegistrationCode;
    }
    @Column(name ="REPORT_COUNT",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getReportCount(){
        return this.reportCount;
    }
    @Column(name ="FK_ARCHIVES_BORROW_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFkArchivesBorrowId(){
        return this.fkArchivesBorrowId;
    }
    @Column(name ="IS_BACK",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getIsBack(){
        return this.isBack;
    }


} 
