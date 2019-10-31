package com.lsts.office.bean;

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
@Table(name = "TJY2_OFFICE_OWNNOTE")
@JsonIgnoreProperties(ignoreUnknown=true)
public class OfficeOwnnote implements BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;//${columnb.remarks}

    private String workContent;//工作内容

    private String workRecord;//工作记录

    private String workStatus;//工作状态

    private java.util.Date createDate;//创建时间

    private String createId;//创建人ID

    private String createBy;//创建人

    private java.util.Date lastModifyDate;//最后更新时间

    private String lastModifyId;//最后更新人ID

    private String lastModifyBy;//最后更新人

    public void setId(String value){
        this.id = value;
    }
    public void setWorkContent(String value){
        this.workContent = value;
    }
    public void setWorkRecord(String value){
        this.workRecord = value;
    }
    public void setWorkStatus(String value){
        this.workStatus = value;
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
    @Column(name ="WORK_CONTENT",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getWorkContent(){
        return this.workContent;
    }
    @Column(name ="WORK_RECORD",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getWorkRecord(){
        return this.workRecord;
    }
    @Column(name ="WORK_STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getWorkStatus(){
        return this.workStatus;
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


} 
