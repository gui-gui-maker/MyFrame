package com.lsts.finance.bean;

import java.math.BigDecimal;
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
@Table(name = "TJY2_IMPORT_DATA_RECORDS")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ImportSalary implements BaseEntity{
	
	private String salaryYear;
	
	private String salaryTmonth;

    private String id;//${columnb.remarks}

    private java.util.Date importTime;//${columnb.remarks}

    private java.math.BigDecimal totalWages;//${columnb.remarks}

    private String importCerate;//${columnb.remarks}
    
    private java.util.Date salartTime; //几月份工资

    public void setId(String value){
        this.id = value;
    }
    public void setImportTime(java.util.Date value){
        this.importTime = value;
    }
    public void setTotalWages(java.math.BigDecimal value){
        this.totalWages = value;
    }
    public void setImportCerate(String value){
        this.importCerate = value;
    }
    public void setSalaryTmonth(String value){
        this.salaryTmonth = value;
    }
    public void setSalaryYear(String value){
        this.salaryYear = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="IMPORT_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getImportTime(){
        return this.importTime;
    }
    @Column(name ="TOTAL_WAGES",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getTotalWages(){
        return this.totalWages;
    }
    @Column(name ="IMPORT_CERATE",unique=false,nullable=true,insertable=true,updatable=true,length=20)
    public String getImportCerate(){
        return this.importCerate;
    }
    @Column(name ="SALART_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getSalartTime(){
        return this.salartTime;
    }
    public void setSalartTime(java.util.Date value){
        this.salartTime = value;
    }
    
    @Column(name ="SALART_YEAR",unique=false,nullable=true,insertable=true,updatable=true,length=10)
    public String getSalaryYear(){
        return this.salaryYear;
    }
    @Column(name ="SALAR_TMONTH",unique=false,nullable=true,insertable=true,updatable=true,length=10)
    public String getSalaryTmonth(){
        return this.salaryTmonth;
    }


} 
