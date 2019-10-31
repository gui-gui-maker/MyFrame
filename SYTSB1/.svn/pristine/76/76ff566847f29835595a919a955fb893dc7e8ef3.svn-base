package com.lsts.equipment2.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_EQUIP_INSTOCK")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquipInstockRecord implements BaseEntity {

	private static final long serialVersionUID = 1L;

	private String id;//入库记录ID

    private java.math.BigDecimal instock_num;//入库数量

    private String instock_type;//入库方式

    private String bz;//备注

    private java.util.Date instock_date;//入库时间

    private String instock_name;//入库登记人

    private java.util.Date create_date;//创建时间

    private String create_name;//创建人
    
    private java.util.Date back_time;//归还时间
    
    private String back_user_id;//归还人ID
    
    private String back_user_name;//归还人姓名
    
    private String instock_position;//入库后设备所在位置
    
    private String instock_position_id;//入库后设备所在位置ID
    
    private String instock_manager;//入库后设备所在位置
    
    private String instock_manager_id;//入库后设备所在位置ID
    
    private List<Equipment> baseEquipment2s;//设备列表
    
    private List<EquipmentBox> equipmentBoxs;//设备箱列表

    public void setId(String value){
        this.id = value;
    }
    public void setInstock_num(java.math.BigDecimal value){
        this.instock_num = value;
    }
    public void setInstock_type(String value){
        this.instock_type = value;
    }
    public void setBz(String value){
        this.bz = value;
    }
    public void setInstock_date(java.util.Date value){
        this.instock_date = value;
    }
    public void setInstock_name(String value){
        this.instock_name = value;
    }
    public void setCreate_date(java.util.Date value){
        this.create_date = value;
    }
    public void setCreate_name(String value){
        this.create_name = value;
    }
    public void setBack_time(java.util.Date value) {
		this.back_time = value;
	}
	public void setBack_user_id(String value) {
		this.back_user_id = value;
	}
	public void setBack_user_name(String value) {
		this.back_user_name = value;
	}
	public void setInstock_position(String value) {
		this.instock_position = value;
	}
	public void setInstock_position_id(String value) {
		this.instock_position_id = value;
	}
	public void setInstock_manager(String value) {
		this.instock_manager = value;
	}
	public void setInstock_manager_id(String value) {
		this.instock_manager_id = value;
	}
	@Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="INSTOCK_NUM",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getInstock_num(){
        return this.instock_num;
    }
    @Column(name ="INSTOCK_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getInstock_type(){
        return this.instock_type;
    }
    @Column(name ="BZ",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getBz(){
        return this.bz;
    }
    @Column(name ="INSTOCK_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getInstock_date(){
        return this.instock_date;
    }
    @Column(name ="INSTOCK_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getInstock_name(){
        return this.instock_name;
    }
    @Column(name ="CREATE_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getCreate_date(){
        return this.create_date;
    }
    @Column(name ="CREATE_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getCreate_name(){
        return this.create_name;
    }
    @Column(name ="BACK_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public java.util.Date getBack_time() {
		return back_time;
	}
    @Column(name ="BACK_USER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=100)
	public String getBack_user_id() {
		return back_user_id;
	}
    @Column(name ="BACK_USER_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
	public String getBack_user_name() {
		return back_user_name;
	}
    @Column(name ="INSTOCK_POSITION",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getInstock_position() {
		return instock_position;
	}
    @Column(name ="INSTOCK_POSITION_ID",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getInstock_position_id() {
		return instock_position_id;
	}
    @Column(name ="INSTOCK_MANAGER",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getInstock_manager() {
		return instock_manager;
	}
    @Column(name ="INSTOCK_MANAGER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getInstock_manager_id() {
		return instock_manager_id;
	}
	@Transient
	public List<Equipment> getBaseEquipment2s() {
		return baseEquipment2s;
	}
	public void setBaseEquipment2s(List<Equipment> baseEquipment2s) {
		this.baseEquipment2s = baseEquipment2s;
	}
	@Transient
	public List<EquipmentBox> getEquipmentBoxs() {
		return equipmentBoxs;
	}
	public void setEquipmentBoxs(List<EquipmentBox> equipmentBoxs) {
		this.equipmentBoxs = equipmentBoxs;
	}
}
