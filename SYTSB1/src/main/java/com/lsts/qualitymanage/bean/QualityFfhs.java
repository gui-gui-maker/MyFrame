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
@Table(name = "TJY2_QUALITY_FFHS")
@JsonIgnoreProperties(ignoreUnknown = true)
public class QualityFfhs implements BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;//${columnb.remarks}

    private String registrant;//登记人

    private String registrantId;//登记人id

    private java.util.Date registrantDate;//登记人时间

    private String identifier;//编号

    private String status;//状态

    private String fileName;//文件名称

    private String useDepartment;//领用部门

    private String useDepartmentId;//领用部门id

    private String extendMark;//发放号

    private java.util.Date useTime;//领用时间

    private String useName;//领用者签名

    private java.util.Date reclaimTime;//回收时间

    private String reclaimName;//回收人

    private String reclaimStatus;//回收状况

    private String useNameId;//领用者签名id

    private String reclaimNameId;//回收人id

    public void setId(String value){
        this.id = value;
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
    public void setFileName(String value){
        this.fileName = value;
    }
    public void setUseDepartment(String value){
        this.useDepartment = value;
    }
    public void setUseDepartmentId(String value){
        this.useDepartmentId = value;
    }
    public void setExtendMark(String value){
        this.extendMark = value;
    }
    public void setUseTime(java.util.Date value){
        this.useTime = value;
    }
    public void setUseName(String value){
        this.useName = value;
    }
    public void setReclaimTime(java.util.Date value){
        this.reclaimTime = value;
    }
    public void setReclaimName(String value){
        this.reclaimName = value;
    }
    public void setReclaimStatus(String value){
        this.reclaimStatus = value;
    }
    public void setUseNameId(String value){
        this.useNameId = value;
    }
    public void setReclaimNameId(String value){
        this.reclaimNameId = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
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
    @Column(name ="IDENTIFIER",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getIdentifier(){
        return this.identifier;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="FILE_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getFileName(){
        return this.fileName;
    }
    @Column(name ="USE_DEPARTMENT",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getUseDepartment(){
        return this.useDepartment;
    }
    @Column(name ="USE_DEPARTMENT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getUseDepartmentId(){
        return this.useDepartmentId;
    }
    @Column(name ="EXTEND_MARK",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getExtendMark(){
        return this.extendMark;
    }
    @Column(name ="USE_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getUseTime(){
        return this.useTime;
    }
    @Column(name ="USE_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getUseName(){
        return this.useName;
    }
    @Column(name ="RECLAIM_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getReclaimTime(){
        return this.reclaimTime;
    }
    @Column(name ="RECLAIM_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getReclaimName(){
        return this.reclaimName;
    }
    @Column(name ="RECLAIM_STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getReclaimStatus(){
        return this.reclaimStatus;
    }
    @Column(name ="USE_NAME_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getUseNameId(){
        return this.useNameId;
    }
    @Column(name ="RECLAIM_NAME_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getReclaimNameId(){
        return this.reclaimNameId;
    }


} 
