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
@Table(name = "TJY2_EQUIP_APPLY_LIST")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquipmentApplyList implements BaseEntity{

	private static final long serialVersionUID = 1L;

    private String id;//主键

    private String applyListId;//购买申请表ID

    private String eqName;//设备名称

    private String eqModel;//规格型号

    private java.lang.Double eqBuyPrice;//单价

    private java.math.BigDecimal eqBuyCount;//数量

    private String eqUsed;//用途

    private String createBy;//创建人

    private java.util.Date createDate;//创建时间

    public void setId(String value){
        this.id = value;
    }
    public void setApplyListId(String value){
        this.applyListId = value;
    }
    public void setEqName(String value){
        this.eqName = value;
    }
    public void setEqModel(String value){
        this.eqModel = value;
    }
    public void setEqBuyPrice(java.lang.Double value){
        this.eqBuyPrice = value;
    }
    public void setEqBuyCount(java.math.BigDecimal value){
        this.eqBuyCount = value;
    }
    public void setEqUsed(String value){
        this.eqUsed = value;
    }
    public void setCreateBy(String value){
        this.createBy = value;
    }
    public void setCreateDate(java.util.Date value){
        this.createDate = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="APPLY_LIST_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getApplyListId(){
        return this.applyListId;
    }
    @Column(name ="EQ_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEqName(){
        return this.eqName;
    }
    @Column(name ="EQ_MODEL",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEqModel(){
        return this.eqModel;
    }
    @Column(name ="EQ_BUY_PRICE",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public java.lang.Double getEqBuyPrice(){
        return this.eqBuyPrice;
    }
    @Column(name ="EQ_BUY_COUNT",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getEqBuyCount(){
        return this.eqBuyCount;
    }
    @Column(name ="EQ_USED",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getEqUsed(){
        return this.eqUsed;
    }
    @Column(name ="CREATE_BY",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getCreateBy(){
        return this.createBy;
    }
    @Column(name ="CREATE_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getCreateDate(){
        return this.createDate;
    }


} 
