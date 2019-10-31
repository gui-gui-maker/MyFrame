package com.lsts.equipment2.bean;

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
@Table(name = "TJY2_EQUIPMENT_CENTER")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquipRecordRelation implements BaseEntity{

    private String id;//中间表ID

    private String instock_id;//设备出入库ID

    private String equip_id;//设备ID
    
    private String create_by;//创建人
    
    private java.util.Date create_date;//创建时间

    public void setId(String value){
        this.id = value;
    }
    public void setInstock_id(String value){
        this.instock_id = value;
    }
    public void setEquip_id(String value){
        this.equip_id = value;
    }
    public void setCreate_by(String value) {
		this.create_by = value;
	}
	public void setCreate_date(java.util.Date value) {
		this.create_date = value;
	}
	@Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=200)
    public String getId(){
        return this.id;
    }
    @Column(name ="INSTOCK_ID",unique=false,nullable=false,insertable=true,updatable=true,length=200)
    public String getInstock_id(){
        return this.instock_id;
    }
    @Column(name ="EQUIP_ID",unique=false,nullable=false,insertable=true,updatable=true,length=200)
    public String getEquip_id(){
        return this.equip_id;
    }
    @Column(name ="CREATE_BY",unique=false,nullable=false,insertable=true,updatable=true,length=200)
	public String getCreate_by() {
		return create_by;
	}
    @Column(name ="CREATE_DATE",unique=false,nullable=false,insertable=true,updatable=true,length=200)
	public java.util.Date getCreate_date() {
		return create_date;
	}
    

} 
