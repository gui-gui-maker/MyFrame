package com.lsts.equipment2.bean;


import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_EQUIP_DOCIMASY_FK")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocimasyFk implements BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private java.util.Date equipmentTime;
	
	private Set<DocimasyFks> docimasyFkss;//关联子类

	private String eqType;//设备型号
	
	private String eqNumbe;//出厂编号
	
	private java.math.BigDecimal period;//检定周期（月）

    private String  id;//${columnb.remarks}
    
    private java.util.Date createTime ;//创建时间
    
    private String createName;//创建人姓名
    
    private String createId;//创建人ID

    private java.util.Date  practicalTime;//实际检定日期（反馈回写）
    
    private java.util.Date nextTime;//计划日期

    private String  unit;//检定单位 

    private String  equipmentName;//检定设备名称

    private String  equipmentId;//检定设备id

    private String  result;//检定结果（反馈回写）

    private String  remark;//备注

    private String  status;//检定状态 

    private String  equipmentNumbe;//检定设备编号
    
    private String  checkOpId;//送检负责人ID
    
    private String  checkOp;//送检负责人

    public void setId(String value){
        this.id = value;
    }
   
    public void setPracticalTime(java.util.Date value){
        this.practicalTime = value;
    }
    public void setCreateTime(java.util.Date value){
        this.createTime = value;
    }
    public void setUnit(String value){
        this.unit = value;
    }
    public void setCreateName(String value){
        this.createName = value;
    }
    public void setEquipmentName(String value){
        this.equipmentName = value;
    }
    public void setCreateId(String value){
        this.createId = value;
    }
    public void setEquipmentId(String value){
        this.equipmentId = value;
    }
    public void setNextTime(java.util.Date value){
        this.nextTime = value;
    }
    public void setEquipmentTime(java.util.Date value){
        this.equipmentTime = value;
    }
    public void setResult(String value){
        this.result = value;
    }
    public void setPeriod(java.math.BigDecimal value){
        this.period = value;
    }
    public void setRemark(String value){
        this.remark = value;
    }
    public void setEqNumbe(String value){
        this.eqNumbe = value;
    }
    public void setEqType(String value){
        this.eqType = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    public void setEquipmentNumbe(String value){
        this.equipmentNumbe = value;
    }
    public void setCheckOpId(String value){
        this.checkOpId = value;
    }
    public void setCheckOp(String value){
        this.checkOp = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
   
    @Column(name ="EQUIPMENT_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getEquipmentTime(){
        return this.equipmentTime;
    }
    @Column(name ="PRACTICAL_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getPracticalTime(){
        return this.practicalTime;
    }
    @Column(name ="CREATE_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getCreateTime(){
        return this.createTime;
    }
    @Column(name ="UNIT",unique=false,nullable=true,insertable=true,updatable=true,length=40)
    public String getUnit(){
        return this.unit;
    }
    @Column(name ="CREATE_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=40)
    public String getCreateName(){
        return this.createName;
    }
    @Column(name ="EQUIPMENT_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getEquipmentName(){
        return this.equipmentName;
    }
    @Column(name ="CREATE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getCreateId(){
        return this.createId;
    }
    @Column(name ="EQUIPMENT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getEquipmentId(){
        return this.equipmentId;
    }
    @Column(name ="RESULT",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getResult(){
        return this.result;
    }
    @Column(name ="REMARK",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getRemark(){
        return this.remark;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=40)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="EQUIPMENT_NUMBE",unique=false,nullable=true,insertable=true,updatable=true,length=40)
    public String getEquipmentNumbe(){
        return this.equipmentNumbe;
    }
    @Column(name ="CHECK_OP_ID",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getCheckOpId(){
        return this.checkOpId;
    }
    @Column(name ="CHECK_OP",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getCheckOp(){
        return this.checkOp;
    }
    @Column(name ="PERIOD",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getPeriod(){
        return this.period;
    }
    @Column(name ="NEXT_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getNextTime(){
        return this.nextTime;
    }

    
    @Column(name ="EQ_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=40)
    public String getEqType(){
        return this.eqType;
    }
    @Column(name ="EQ_NUMBE",unique=false,nullable=true,insertable=true,updatable=true,length=40)
    public String getEqNumbe(){
        return this.eqNumbe;
    }
    
    public void setDocimasyFkss(Set<DocimasyFks> docimasyFkss) {
		this.docimasyFkss = docimasyFkss;
	}
    
    @OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.LAZY,mappedBy="docimasyFk")
	public Set<DocimasyFks> getDocimasyFkss() {
		return docimasyFkss;
	}
    

} 
