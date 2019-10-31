package com.khnt.oa.car.bean;

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
@Table(name = "TJY2_CARREPAIR_NOTE")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CarrepairNote implements BaseEntity{

	private static final long serialVersionUID = 1L;
    private String id;//${columnb.remarks}

    private String useDepartmentId;//使用部门ID

    private String useDepartment;//使用部门

    private String carNum;//车牌号

    private String carLogo;//品牌型号

    private String engineNo;//发动机号

    private String carCode;//车辆识别代码

    private String senrepairId;//送修人ID

    private String senrepairName;//送修人

    private java.util.Date senrepairDate;//送修时间

    private String repairItem;//维修项目

    private String useDepartmentManagerId;//使用部门负责人ID

    private String useDepartmentManager;//使用部门负责人

    private String useDepartmentManagerOpinion;//使用部门负责人意见

    private java.util.Date useDepartmentManagerDate;//使用部门负责人审核时间

    private String fleetManagerId;//车队负责人ID

    private String fleetManager;//车队负责人

    private String fleetManagerOpinion;//车队负责人意见

    private java.util.Date fleetManagerDate;//车队负责人审核时间

    private String departmentManagerId;//管理部门负责人ID

    private String departmentManager;//管理部门负责人

    private String departmentManagerOpinion;//管理部门负责人意见

    private java.util.Date departmentManagerDate;//管理部门负责人审核时间

    private String auditorId;//${columnb.remarks}

    private String auditor;//${columnb.remarks}

    private java.util.Date auditDate;//${columnb.remarks}

    private String type;//${columnb.remarks}

    private java.util.Date createDate;//${columnb.remarks}

    private String createId;//${columnb.remarks}

    private String createBy;//${columnb.remarks}

    private java.util.Date lastModifyDate;//${columnb.remarks}

    private String lastModifyId;//${columnb.remarks}

    private String lastModifyBy;//${columnb.remarks}

    private String status;//状态
    
    public void setId(String value){
        this.id = value;
    }
    public void setUseDepartmentId(String value){
        this.useDepartmentId = value;
    }
    public void setUseDepartment(String value){
        this.useDepartment = value;
    }
    public void setCarNum(String value){
        this.carNum = value;
    }
    public void setCarLogo(String value){
        this.carLogo = value;
    }
    public void setEngineNo(String value){
        this.engineNo = value;
    }
    public void setCarCode(String value){
        this.carCode = value;
    }
    public void setSenrepairId(String value){
        this.senrepairId = value;
    }
    public void setSenrepairName(String value){
        this.senrepairName = value;
    }
    public void setSenrepairDate(java.util.Date value){
        this.senrepairDate = value;
    }
    public void setRepairItem(String value){
        this.repairItem = value;
    }
    public void setUseDepartmentManagerId(String value){
        this.useDepartmentManagerId = value;
    }
    public void setUseDepartmentManager(String value){
        this.useDepartmentManager = value;
    }
    public void setUseDepartmentManagerOpinion(String value){
        this.useDepartmentManagerOpinion = value;
    }
    public void setUseDepartmentManagerDate(java.util.Date value){
        this.useDepartmentManagerDate = value;
    }
    public void setFleetManagerId(String value){
        this.fleetManagerId = value;
    }
    public void setFleetManager(String value){
        this.fleetManager = value;
    }
    public void setFleetManagerOpinion(String value){
        this.fleetManagerOpinion = value;
    }
    public void setFleetManagerDate(java.util.Date value){
        this.fleetManagerDate = value;
    }
    public void setDepartmentManagerId(String value){
        this.departmentManagerId = value;
    }
    public void setDepartmentManager(String value){
        this.departmentManager = value;
    }
    public void setDepartmentManagerOpinion(String value){
        this.departmentManagerOpinion = value;
    }
    public void setDepartmentManagerDate(java.util.Date value){
        this.departmentManagerDate = value;
    }
    public void setAuditorId(String value){
        this.auditorId = value;
    }
    public void setAuditor(String value){
        this.auditor = value;
    }
    public void setAuditDate(java.util.Date value){
        this.auditDate = value;
    }
    public void setType(String value){
        this.type = value;
    }
    public void setCreateDate(java.util.Date value){
        this.createDate = value;
    }
    public void setCreateId(String value){
        this.createId = value;
    }
    public void setCreateBy(String value){
        this.createBy = value;
    }
    public void setLastModifyDate(java.util.Date value){
        this.lastModifyDate = value;
    }
    public void setLastModifyId(String value){
        this.lastModifyId = value;
    }
    public void setLastModifyBy(String value){
        this.lastModifyBy = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=false,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="USE_DEPARTMENT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getUseDepartmentId(){
        return this.useDepartmentId;
    }
    @Column(name ="USE_DEPARTMENT",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getUseDepartment(){
        return this.useDepartment;
    }
    @Column(name ="CAR_NUM",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getCarNum(){
        return this.carNum;
    }
    @Column(name ="CAR_LOGO",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getCarLogo(){
        return this.carLogo;
    }
    @Column(name ="ENGINE_NO",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getEngineNo(){
        return this.engineNo;
    }
    @Column(name ="CAR_CODE",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getCarCode(){
        return this.carCode;
    }
    @Column(name ="SENREPAIR_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getSenrepairId(){
        return this.senrepairId;
    }
    @Column(name ="SENREPAIR_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getSenrepairName(){
        return this.senrepairName;
    }
    @Column(name ="SENREPAIR_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getSenrepairDate(){
        return this.senrepairDate;
    }
    @Column(name ="REPAIR_ITEM",unique=false,nullable=true,insertable=true,updatable=true,length=3000)
    public String getRepairItem(){
        return this.repairItem;
    }
    @Column(name ="USE_DEPARTMENT_MANAGER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getUseDepartmentManagerId(){
        return this.useDepartmentManagerId;
    }
    @Column(name ="USE_DEPARTMENT_MANAGER",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getUseDepartmentManager(){
        return this.useDepartmentManager;
    }
    @Column(name ="USE_DEPARTMENT_MANAGER_OPINION",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getUseDepartmentManagerOpinion(){
        return this.useDepartmentManagerOpinion;
    }
    @Column(name ="USE_DEPARTMENT_MANAGER_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getUseDepartmentManagerDate(){
        return this.useDepartmentManagerDate;
    }
    @Column(name ="FLEET_MANAGER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFleetManagerId(){
        return this.fleetManagerId;
    }
    @Column(name ="FLEET_MANAGER",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getFleetManager(){
        return this.fleetManager;
    }
    @Column(name ="FLEET_MANAGER_OPINION",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getFleetManagerOpinion(){
        return this.fleetManagerOpinion;
    }
    @Column(name ="FLEET_MANAGER_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getFleetManagerDate(){
        return this.fleetManagerDate;
    }
    @Column(name ="DEPARTMENT_MANAGER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getDepartmentManagerId(){
        return this.departmentManagerId;
    }
    @Column(name ="DEPARTMENT_MANAGER",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getDepartmentManager(){
        return this.departmentManager;
    }
    @Column(name ="DEPARTMENT_MANAGER_OPINION",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getDepartmentManagerOpinion(){
        return this.departmentManagerOpinion;
    }
    @Column(name ="DEPARTMENT_MANAGER_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getDepartmentManagerDate(){
        return this.departmentManagerDate;
    }
    @Column(name ="AUDITOR_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getAuditorId(){
        return this.auditorId;
    }
    @Column(name ="AUDITOR",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getAuditor(){
        return this.auditor;
    }
    @Column(name ="AUDIT_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getAuditDate(){
        return this.auditDate;
    }
    @Column(name ="TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getType(){
        return this.type;
    }
    @Column(name ="CREATE_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getCreateDate(){
        return this.createDate;
    }
    @Column(name ="CREATE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getCreateId(){
        return this.createId;
    }
    @Column(name ="CREATE_BY",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getCreateBy(){
        return this.createBy;
    }
    @Column(name ="LAST_MODIFY_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getLastModifyDate(){
        return this.lastModifyDate;
    }
    @Column(name ="LAST_MODIFY_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getLastModifyId(){
        return this.lastModifyId;
    }
    @Column(name ="LAST_MODIFY_BY",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getLastModifyBy(){
        return this.lastModifyBy;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getStatus(){
        return this.status;
    }


} 
