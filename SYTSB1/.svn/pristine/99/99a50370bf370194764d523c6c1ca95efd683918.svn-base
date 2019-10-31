package com.fwxm.scientific.bean;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_SCIENTIFIC_PAPER")
public class Tjy2ScientificPaper implements BaseEntity{

    private String id;//${columnb.remarks}

    private String paperName;//论文题目

    private java.util.Date paperDate;//论文发表时间

    private String paperType;//发表类型

    private String paperAuthor;//论文作者

    private String paperPress;//出版社

    private java.util.Date createDate;//创建时间

    private String createMan;//创建人

    private java.util.Date lastModifyDate;//最后修改时间

    private String lastModifyMan;//最后修改人

    private String status;//状态 0正常 99删除
    private String fkTjy2ScientificId;//科研项目表id

    @Id
   	@GeneratedValue(generator = "system-uuid")
   	@GenericGenerator(name = "system-uuid", strategy = "uuid")
   	public String getId() {
   		return id;
   	}

   	public void setId(String id) {
   		this.id = id;
   	}
    public void setPaperName(String value){
        this.paperName = value;
    }
    public void setPaperDate(java.util.Date value){
        this.paperDate = value;
    }
    public void setPaperType(String value){
        this.paperType = value;
    }
    public void setPaperAuthor(String value){
        this.paperAuthor = value;
    }
    public void setPaperPress(String value){
        this.paperPress = value;
    }
    public void setCreateDate(java.util.Date value){
        this.createDate = value;
    }
    public void setCreateMan(String value){
        this.createMan = value;
    }
    public void setLastModifyDate(java.util.Date value){
        this.lastModifyDate = value;
    }
    public void setLastModifyMan(String value){
        this.lastModifyMan = value;
    }
    public void setStatus(String value){
        this.status = value;
    }

    @Column(name ="PAPER_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getPaperName(){
        return this.paperName;
    }
    @Column(name ="PAPER_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getPaperDate(){
        return this.paperDate;
    }
    @Column(name ="PAPER_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getPaperType(){
        return this.paperType;
    }
    @Column(name ="PAPER_AUTHOR",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getPaperAuthor(){
        return this.paperAuthor;
    }
    @Column(name ="PAPER_PRESS",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getPaperPress(){
        return this.paperPress;
    }
    @Column(name ="CREATE_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getCreateDate(){
        return this.createDate;
    }
    @Column(name ="CREATE_MAN",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getCreateMan(){
        return this.createMan;
    }
    @Column(name ="LAST_MODIFY_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getLastModifyDate(){
        return this.lastModifyDate;
    }
    @Column(name ="LAST_MODIFY_MAN",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getLastModifyMan(){
        return this.lastModifyMan;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getStatus(){
        return this.status;
    }
    
    @Column(name ="FK_TJY2_SCIENTIFIC_ID")
    public String getFkTjy2ScientificId() {
		return fkTjy2ScientificId;
	}

	public void setFkTjy2ScientificId(String fkTjy2ScientificId) {
		this.fkTjy2ScientificId = fkTjy2ScientificId;
	}

	@SuppressWarnings("unchecked")
	public Map to_Map() {
        Map map = new HashMap();
        map.put("id", id);
        map.put("paperName", paperName);
        map.put("paperDate", paperDate);
        map.put("paperType", paperType);
        map.put("paperAuthor", paperAuthor);
        map.put("paperPress", paperPress);
        map.put("fkTjy2ScientificId", fkTjy2ScientificId);
        return map;
    }

} 
