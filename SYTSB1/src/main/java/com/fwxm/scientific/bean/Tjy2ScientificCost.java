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
@Table(name = "TJY2_SCIENTIFIC_COST")
public class Tjy2ScientificCost implements BaseEntity{

    private String id;//${columnb.remarks}

    private String costType;//费用类型

    private String money;//金额

    private String costInstructions;//说明

    private String costReason;//理由

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
    public void setCostType(String value){
        this.costType = value;
    }
    public void setMoney(String value){
        this.money = value;
    }
    public void setCostInstructions(String value){
        this.costInstructions = value;
    }
    public void setCostReason(String value){
        this.costReason = value;
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
   
    @Column(name ="COST_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getCostType(){
        return this.costType;
    }
    @Column(name ="MONEY",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getMoney(){
        return this.money;
    }
    @Column(name ="COST_INSTRUCTIONS",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getCostInstructions(){
        return this.costInstructions;
    }
    @Column(name ="COST_REASON",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getCostReason(){
        return this.costReason;
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
        map.put("costType", costType);
        map.put("money", money);
        map.put("costInstructions", costInstructions);
        map.put("costReason", costReason);
        map.put("fkTjy2ScientificId", fkTjy2ScientificId);
        return map;
    }

} 
