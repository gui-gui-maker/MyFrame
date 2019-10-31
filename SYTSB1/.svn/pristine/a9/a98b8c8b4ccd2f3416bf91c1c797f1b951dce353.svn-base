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
@Table(name = "TJY2_SCIENTIFIC_PATENT")
public class Tjy2ScientificPatent implements BaseEntity{

    private String id;//${columnb.remarks}

    private String patentName;//专利名称

    private java.util.Date patentDate;//专利申请时间

    private String patentMan;//专利申请人

    private String patentNumber;//专利号

    private String patentType;//专利类型

    private String patentInvent;//专利发明人

    private String patentManAddress;//专利申请人地址

    private java.util.Date createDate;//创建时间

    private String createMan;//创建人

    private java.util.Date lastModifyDate;//最后修改时间

    private String lastModifyMan;//最后修改人

    private String status;//状态

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
    public void setPatentName(String value){
        this.patentName = value;
    }
    public void setPatentDate(java.util.Date value){
        this.patentDate = value;
    }
    public void setPatentMan(String value){
        this.patentMan = value;
    }
    public void setPatentNumber(String value){
        this.patentNumber = value;
    }
    public void setPatentType(String value){
        this.patentType = value;
    }
    public void setPatentInvent(String value){
        this.patentInvent = value;
    }
    public void setPatentManAddress(String value){
        this.patentManAddress = value;
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
    public void setFkTjy2ScientificId(String value){
        this.fkTjy2ScientificId = value;
    }
    @Column(name ="PATENT_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getPatentName(){
        return this.patentName;
    }
    @Column(name ="PATENT_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getPatentDate(){
        return this.patentDate;
    }
    @Column(name ="PATENT_MAN",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getPatentMan(){
        return this.patentMan;
    }
    @Column(name ="PATENT_NUMBER",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getPatentNumber(){
        return this.patentNumber;
    }
    @Column(name ="PATENT_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getPatentType(){
        return this.patentType;
    }
    @Column(name ="PATENT_INVENT",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getPatentInvent(){
        return this.patentInvent;
    }
    @Column(name ="PATENT_MAN_ADDRESS",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getPatentManAddress(){
        return this.patentManAddress;
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
    @Column(name ="FK_TJY2_SCIENTIFIC_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFkTjy2ScientificId(){
        return this.fkTjy2ScientificId;
    }
    @SuppressWarnings("unchecked")
	public Map to_Map() {
        Map map = new HashMap();
        map.put("id", id);
        map.put("patentName", patentName);
        map.put("patentDate", patentDate);
        map.put("patentMan", patentMan);
        map.put("patentNumber", patentNumber);
        map.put("patentType", patentType);
        map.put("patentInvent", patentInvent);
        map.put("patentManAddress", patentManAddress);
        map.put("fkTjy2ScientificId", fkTjy2ScientificId);
        return map;
    }

} 
