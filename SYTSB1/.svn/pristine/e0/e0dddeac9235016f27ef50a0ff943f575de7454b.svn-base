package com.lsts.humanresources.bean;

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
@Table(name = "TJY2_RL_ORGID_LEADERID")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrgidLeaderid implements BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;//${columnb.remarks}

    private String fkRlEmplpyeeId;//院领导ID

    private String empName;//院领导姓名

    private String fkSysOrgId;//部门ID

    private String orgName;//部门名称

    private java.util.Date createDate;//创建时间

    private String createId;//创建人ID

    private String createBy;//创建人

    private java.util.Date lastModifyDate;//最后更新时间

    private String lastModifyId;//最后更新人ID

    private String lastModifyBy;//最后更新人

    public void setId(String value){
        this.id = value;
    }
    public void setFkRlEmplpyeeId(String value){
        this.fkRlEmplpyeeId = value;
    }
    public void setEmpName(String value){
        this.empName = value;
    }
    public void setFkSysOrgId(String value){
        this.fkSysOrgId = value;
    }
    public void setOrgName(String value){
        this.orgName = value;
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
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="FK_RL_EMPLPYEE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFkRlEmplpyeeId(){
        return this.fkRlEmplpyeeId;
    }
    @Column(name ="EMP_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=128)
    public String getEmpName(){
        return this.empName;
    }
    @Column(name ="FK_SYS_ORG_ID",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getFkSysOrgId(){
        return this.fkSysOrgId;
    }
    @Column(name ="ORG_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=3000)
    public String getOrgName(){
        return this.orgName;
    }
    @Column(name ="CREATE_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getCreateDate(){
        return this.createDate;
    }
    @Column(name ="CREATE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getCreateId(){
        return this.createId;
    }
    @Column(name ="CREATE_BY",unique=false,nullable=true,insertable=true,updatable=true,length=128)
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
    @Column(name ="LAST_MODIFY_BY",unique=false,nullable=true,insertable=true,updatable=true,length=128)
    public String getLastModifyBy(){
        return this.lastModifyBy;
    }


} 
