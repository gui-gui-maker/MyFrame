package com.lsts.equipment2.bean;

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
@Table(name = "TJY2_PPE_LOAN_SUB")
@JsonIgnoreProperties(ignoreUnknown=true)
public class PpeLoanSub implements BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;//${columnb.remarks}

    private String loanFk;//登记表外键

    private String ppeFk;//资产外键

    private String selfNo;//资产编号

    private String assetName;//资产名称

    private String spaciModel;//规格型号

    private String sn;//序列号

    private java.math.BigDecimal numbers;//数量

    private String unit;//单位

    private String remark;//备注

    private String status;//借出/归还状态
    
    private java.util.Date backDate;//归还日期
    
    private java.util.Date preBackDate;//预计归还日期

    public void setId(String value){
        this.id = value;
    }
    public void setLoanFk(String value){
        this.loanFk = value;
    }
    public void setPpeFk(String value){
        this.ppeFk = value;
    }
    public void setSelfNo(String value){
        this.selfNo = value;
    }
    public void setAssetName(String value){
        this.assetName = value;
    }
    public void setSpaciModel(String value){
        this.spaciModel = value;
    }
    public void setSn(String value){
        this.sn = value;
    }
    public void setNumbers(java.math.BigDecimal value){
        this.numbers = value;
    }
    public void setUnit(String value){
        this.unit = value;
    }
    public void setRemark(String value){
        this.remark = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    public void setBackDate(java.util.Date value){
        this.backDate = value;
    }
    public void setPreBackDate(java.util.Date value){
        this.preBackDate = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="LOAN_FK",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getLoanFk(){
        return this.loanFk;
    }
    @Column(name ="PPE_FK",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getPpeFk(){
        return this.ppeFk;
    }
    @Column(name ="SELF_NO",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getSelfNo(){
        return this.selfNo;
    }
    @Column(name ="ASSET_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getAssetName(){
        return this.assetName;
    }
    @Column(name ="SPACI_MODEL",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getSpaciModel(){
        return this.spaciModel;
    }
    @Column(name ="SN",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getSn(){
        return this.sn;
    }
    @Column(name ="NUMBERS",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public java.math.BigDecimal getNumbers(){
        return this.numbers;
    }
    @Column(name ="UNIT",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getUnit(){
        return this.unit;
    }
    @Column(name ="REMARK",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getRemark(){
        return this.remark;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="BACK_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getBackDate(){
        return this.backDate;
    }
    @Column(name ="PRE_BACK_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getPreBackDate(){
        return this.preBackDate;
    }


} 
