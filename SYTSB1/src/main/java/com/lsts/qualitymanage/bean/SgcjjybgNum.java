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
@Table(name = "TJY2_QUALITY_SGCJJYBG_NUM")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SgcjjybgNum implements BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;//${columnb.remarks}

    private String fwbjhbgNum;//服务部计划的报告/证书编号

    private String remarks;//备注

    private String registrant;//登记人

    private String registrantId;//登记人id

    private java.util.Date registrantDate;//登记人时间

    private String status;//状态
    
    private String identifierType;//编号

    private String fksgId;//业务id
    private String rowId;//行id（1~7）
    private String testCoding;//检验项目编码
    
    private String type;//种类
    private String variety;//种类
    private String category;//种类

	
	public void setVariety(String variety) {
		this.variety = variety;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setTestCoding(String testCoding) {
		this.testCoding = testCoding;
	}
	public void setFksgId(String fksgId) {
		this.fksgId = fksgId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	public void setIdentifierType(String identifierType) {
		this.identifierType = identifierType;
	}
	public void setId(String value){
        this.id = value;
    }
    public void setFwbjhbgNum(String value){
        this.fwbjhbgNum = value;
    }
    public void setRemarks(String value){
        this.remarks = value;
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
    @Column(name ="FWBJHBG_NUM",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getFwbjhbgNum(){
        return this.fwbjhbgNum;
    }
    @Column(name ="REMARKS",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getRemarks(){
        return this.remarks;
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
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="IDENTIFIER_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getIdentifierType() {
		return identifierType;
	}
    @Column(name ="FKSG_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFksgId() {
		return fksgId;
	}
    @Column(name ="ROW_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
	public String getRowId() {
		return rowId;
	}
    @Column(name ="TEST_CODING",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getTestCoding() {
		return testCoding;
	}
    @Column(name ="TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getType() {
		return type;
	}
    @Column(name ="VARIETY",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getVariety() {
		return variety;
	}
    @Column(name ="CATEGORY",unique=false,nullable=true,insertable=true,updatable=true,length=200)
	public String getCategory() {
		return category;
	}
} 
