package com.lsts.equipment2.bean;

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
@Table(name = "TJY2_USE_REGISTER")
public class EquipmentUseRegister implements BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;//id
	
	private String useSite;//使用地点

    private String createId;//创建人id
    
    private String equipmentName;//设备名称

    private String createName;//创建人姓名

    private java.util.Date createTime;//创建时间

    private String borrowerId;//借用/领用 人id
    
    private String borrowerName;//借用/领用人姓名

    private java.util.Date borrowerTime;//开始时间

    private java.util.Date returnTime;//归还时间
    
    private String departmentId;//使用部门ID
    
    private String departmentName;//使用部门名称

    private String remark;//备注

    private String status;//状态
    
    private String equipId;//设备ID
    
    private String useType;//使用类型

    public void setId(String value){
        this.id = value;
    }
    public void setCreateId(String value){
        this.createId = value;
    }
    public void setUseSite(String value){
        this.useSite = value;
    }
    public void setCreateName(String value){
        this.createName = value;
    }
    public void setEquipmentName(String value){
        this.equipmentName = value;
    }
    public void setCreateTime(java.util.Date value){
        this.createTime = value;
    }
    public void setBorrowerName(String value){
        this.borrowerName = value;
    }
    public void setBorrowerTime(java.util.Date value){
        this.borrowerTime = value;
    }
    public void setReturnTime(java.util.Date value){
        this.returnTime = value;
    }
    public void setBorrowerId(String value){
        this.borrowerId = value;
    }
    public void setRemark(String value){
        this.remark = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    public void setDepartmentId(String value){
        this.departmentId = value;
    }
    public void setDepartmentName(String value){
        this.departmentName = value;
    }
    public void setEquipId(String value){
        this.equipId = value;
    }
    public void setUseType(String value){
        this.useType = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="CREATE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getCreateId(){
        return this.createId;
    }
    @Column(name ="CREATE_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=20)
    public String getCreateName(){
        return this.createName;
    }
    @Column(name ="CREATE_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getCreateTime(){
        return this.createTime;
    }
    @Column(name ="BORROWER_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=40)
    public String getBorrowerName(){
        return this.borrowerName;
    }
    @Column(name ="BORROWER_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getBorrowerTime(){
        return this.borrowerTime;
    }
    @Column(name ="RETURN_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getReturnTime(){
        return this.returnTime;
    }
    @Column(name ="BORROWER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getBorrowerId(){
        return this.borrowerId;
    }
    @Column(name ="REMARK",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getRemark(){
        return this.remark;
    }
    @Column(name ="USESITE",unique=false,nullable=true,insertable=true,updatable=true,length=40)
    public String getUseSite(){
        return this.useSite;
    }
    @Column(name ="EQUIPMENTNAME",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentName(){
        return this.equipmentName;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="DEPARTMENT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getDepartmentId(){
        return this.departmentId;
    }
    @Column(name ="DEPARTMENT_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=40)
    public String getDepartmentName(){
        return this.departmentName;
    }
    @Column(name ="EQUIP_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getEquipId(){
        return this.equipId;
    }
    @Column(name ="USE_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getUseType(){
        return this.useType;
    }


} 
