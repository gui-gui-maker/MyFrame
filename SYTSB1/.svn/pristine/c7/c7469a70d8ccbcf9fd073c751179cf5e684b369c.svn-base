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
@Table(name = "TJY2_RL_WORKTITLE_RECORD")
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorktitleRecord implements BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;//${columnb.remarks}

    private String empId;//员工ID

    private String worktitleId;//职务ID

    private String worktitleName;//职务名称

    private java.util.Date startDate;//开始时间

    private java.util.Date endDate;//结束时间
    
    private String uploadFiles;//文件

    private String status;//状态

    private String createId;//${columnb.remarks}

    private String createBy;//${columnb.remarks}
    
    private java.util.Date createDate;//${columnb.remarks}

    public void setId(String value){
        this.id = value;
    }
    public void setEmpId(String value){
        this.empId = value;
    }
    public void setWorktitleId(String value){
        this.worktitleId = value;
    }
    public void setWorktitleName(String value){
        this.worktitleName = value;
    }
    public void setStartDate(java.util.Date value){
        this.startDate = value;
    }
    public void setEndDate(java.util.Date value){
        this.endDate = value;
    }
    public void setUploadFiles(String value){
        this.uploadFiles = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    public void setCreateId(String value){
        this.createId = value;
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
    @Column(name ="EMP_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getEmpId(){
        return this.empId;
    }
    @Column(name ="WORKTITLE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getWorktitleId(){
        return this.worktitleId;
    }
    @Column(name ="WORKTITLE_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=128)
    public String getWorktitleName(){
        return this.worktitleName;
    }
    @Column(name ="START_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getStartDate(){
        return this.startDate;
    }
    @Column(name ="END_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getEndDate(){
        return this.endDate;
    }
    @Column(name ="UPLOADFILES",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getUploadFiles(){
        return this.uploadFiles;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="CREATE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getCreateId(){
        return this.createId;
    }
    @Column(name ="CREATE_BY",unique=false,nullable=true,insertable=true,updatable=true,length=128)
    public String getCreateBy(){
        return this.createBy;
    }
    @Column(name ="CREATE_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getCreateDate(){
        return this.createDate;
    }


} 
