package com.lsts.employee.bean;

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
@Table(name = "EMPLOYEE_PERMISSIONS")
public class EmployeePermissions implements BaseEntity{

    private String id;//${columnb.remarks}

    private String fkEmployeeId;//人员信息id

    private String isAuditMan;//是否审核人
    private String auditDevice;//分管设备类别
    private String auditDeviceScope;//设备类别审核范围

    private String auditDepartmentScope;//部门审核范围

    private String isSign;//是否签发人

    private String signScope;//签发范围
    
    private String signScopezType;//设备类别签发范围

    @Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    public void setFkEmployeeId(String value){
        this.fkEmployeeId = value;
    }
    public void setIsAuditMan(String value){
        this.isAuditMan = value;
    }
    public void setAuditDeviceScope(String value){
        this.auditDeviceScope = value;
    }
    public void setAuditDepartmentScope(String value){
        this.auditDepartmentScope = value;
    }
    public void setIsSign(String value){
        this.isSign = value;
    }
    public void setSignScope(String value){
        this.signScope = value;
    }
    @Column(name ="FK_EMPLOYEE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFkEmployeeId(){
        return this.fkEmployeeId;
    }
    @Column(name ="IS_AUDIT_MAN",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getIsAuditMan(){
        return this.isAuditMan;
    }
    @Column(name ="AUDIT_DEVICE_SCOPE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getAuditDeviceScope(){
        return this.auditDeviceScope;
    }
    @Column(name ="AUDIT_DEPARTMENT_SCOPE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getAuditDepartmentScope(){
        return this.auditDepartmentScope;
    }
    @Column(name ="IS_SIGN",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getIsSign(){
        return this.isSign;
    }
    @Column(name ="SIGN_SCOPE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getSignScope(){
        return this.signScope;
    }
    @Column(name ="AUDIT_DEVICE")
	public String getAuditDevice() {
		return auditDevice;
	}
	public void setAuditDevice(String auditDevice) {
		this.auditDevice = auditDevice;
	}
	 @Column(name ="SIGN_SCOPE_TYPE")
	public String getSignScopezType() {
		return signScopezType;
	}
	public void setSignScopezType(String signScopezType) {
		this.signScopezType = signScopezType;
	}
   

} 
