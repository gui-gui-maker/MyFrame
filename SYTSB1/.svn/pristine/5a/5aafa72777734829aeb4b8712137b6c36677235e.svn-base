package com.fwxm.scientific.bean;

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
@Table(name = "TJY2_TECH_EXCHANGE")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TechExchange implements BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;//${columnb.remarks}

    private String actTitle;//交流/培训/对比题目

    private String actContent;//交流/培训/对比内容

    private String personnelId;//交流/培训/对比人员ID

    private String personnel;//交流/培训/对比人员

    private String actResult;//交流/培训/对比结果

    private java.util.Date actDate;//交流/培训/对比时间

    private String remark;//备注

    private String createId;//${columnb.remarks}

    private String createBy;//${columnb.remarks}

    private java.util.Date createDate;//${columnb.remarks}

    private String lastModifyId;//${columnb.remarks}

    private String lastModifyBy;//${columnb.remarks}

    private java.util.Date lastModifyDate;//${columnb.remarks}
    
    private String type;//类别
    private String actOrg;//比对机构
    private String inspectType;//检验项目

    public void setId(String value){
        this.id = value;
    }
    public void setActTitle(String value){
        this.actTitle = value;
    }
    public void setActContent(String value){
        this.actContent = value;
    }
    public void setPersonnelId(String value){
        this.personnelId = value;
    }
    public void setPersonnel(String value){
        this.personnel = value;
    }
    public void setActResult(String value){
        this.actResult = value;
    }
    public void setActDate(java.util.Date value){
        this.actDate = value;
    }
    public void setRemark(String value){
        this.remark = value;
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
    public void setLastModifyId(String value){
        this.lastModifyId = value;
    }
    public void setLastModifyBy(String value){
        this.lastModifyBy = value;
    }
    public void setLastModifyDate(java.util.Date value){
        this.lastModifyDate = value;
    }
    public void setType(String value){
        this.type = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="ACT_TITLE",unique=false,nullable=true,insertable=true,updatable=true,length=128)
    public String getActTitle(){
        return this.actTitle;
    }
    @Column(name ="ACT_CONTENT",unique=false,nullable=true,insertable=true,updatable=true,length=3000)
    public String getActContent(){
        return this.actContent;
    }
    @Column(name ="PERSONNEL_ID",unique=false,nullable=true,insertable=true,updatable=true,length=3000)
    public String getPersonnelId(){
        return this.personnelId;
    }
    @Column(name ="PERSONNEL",unique=false,nullable=true,insertable=true,updatable=true,length=3000)
    public String getPersonnel(){
        return this.personnel;
    }
    @Column(name ="ACT_RESULT",unique=false,nullable=true,insertable=true,updatable=true,length=3000)
    public String getActResult(){
        return this.actResult;
    }
    @Column(name ="ACT_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getActDate(){
        return this.actDate;
    }
    @Column(name ="REMARK",unique=false,nullable=true,insertable=true,updatable=true,length=3000)
    public String getRemark(){
        return this.remark;
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
    @Column(name ="LAST_MODIFY_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getLastModifyId(){
        return this.lastModifyId;
    }
    @Column(name ="LAST_MODIFY_BY",unique=false,nullable=true,insertable=true,updatable=true,length=128)
    public String getLastModifyBy(){
        return this.lastModifyBy;
    }
    @Column(name ="LAST_MODIFY_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getLastModifyDate(){
        return this.lastModifyDate;
    }
    @Column(name ="TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getType(){
        return this.type;
    }
    @Column(name ="ACT_ORG")
	public String getActOrg() {
		return actOrg;
	}
	public void setActOrg(String actOrg) {
		this.actOrg = actOrg;
	}
	@Column(name ="INSPECT_TYPE")
	public String getInspectType() {
		return inspectType;
	}
	public void setInspectType(String inspectType) {
		this.inspectType = inspectType;
	}


} 
