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
@Table(name = "TJY2_EQUIP_OUTSTOCK")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquipOutstockRecord implements BaseEntity{

	private static final long serialVersionUID = 1L;
	
    private String id;//出库记录ID

    private String outstock_type;//出库方式

    private java.util.Date return_date;//归还日期（预计）

    private java.math.BigDecimal outstock_count;//出库数量 

    private String remark;//备注

    private String create_by;//创建人

    private java.util.Date create_date;//创建时间

    private String outstock_by;//出库登记人

    private java.util.Date outstock_date;//出库时间
    
    private String borrow_draw_id;//借用/领用人ID

    private String borrow_draw_by;//借用/领用人

    private String outstock_position_id;//出库后设备所在位置id
    
    private String outstock_position;//出库后设备所在位置

    private java.util.Date borrow_draw_date;//借用/领用时间
    
    private List<Equipment> baseEquipment2s;//设备列表
    
    private List<EquipmentBox> equipmentBoxs;//设备箱列表

    public void setId(String value){
        this.id = value;
    }
    public void setOutstock_type(String value){
        this.outstock_type = value;
    }
    public void setReturn_date(java.util.Date value){
        this.return_date = value;
    }
    public void setOutstock_count(java.math.BigDecimal value){
        this.outstock_count = value;
    }
    public void setRemark(String value){
        this.remark = value;
    }
    public void setCreate_by(String value){
        this.create_by = value;
    }
    public void setCreate_date(java.util.Date value){
        this.create_date = value;
    }
    public void setOutstock_by(String value){
        this.outstock_by = value;
    }
    public void setOutstock_date(java.util.Date value){
        this.outstock_date = value;
    }
    public void setBorrow_draw_by(String value){
        this.borrow_draw_by = value;
    }
    public void setOutstock_position(String value){
        this.outstock_position = value;
    }
    public void setBorrow_draw_date(java.util.Date value){
        this.borrow_draw_date = value;
    }
    public void setBorrow_draw_id(String value) {
		this.borrow_draw_id = value;
	}
	public void setOutstock_position_id(String value) {
		this.outstock_position_id = value;
	}
	@Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="OUTSTOCK_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getOutstock_type(){
        return this.outstock_type;
    }
    @Column(name ="RETURN_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getReturn_date(){
        return this.return_date;
    }
    @Column(name ="OUTSTOCK_COUNT",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getOutstock_count(){
        return this.outstock_count;
    }
    @Column(name ="REMARK",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getRemark(){
        return this.remark;
    }
    @Column(name ="CREATE_BY",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getCreate_by(){
        return this.create_by;
    }
    @Column(name ="CREATE_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getCreate_date(){
        return this.create_date;
    }
    @Column(name ="OUTSTOCK_BY",unique=false,nullable=true,insertable=true,updatable=true,length=64)
    public String getOutstock_by(){
        return this.outstock_by;
    }
    @Column(name ="OUTSTOCK_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getOutstock_date(){
        return this.outstock_date;
    }
    @Column(name ="BORROW_DRAW_BY",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getBorrow_draw_by(){
        return this.borrow_draw_by;
    }
    @Column(name ="OUTSTOCK_POSITION",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getOutstock_position(){
        return this.outstock_position;
    }
    @Column(name ="BORROW_DRAW_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getBorrow_draw_date(){
        return this.borrow_draw_date;
    }
    @Column(name ="BORROW_DRAW_ID",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public String getBorrow_draw_id(){
        return this.borrow_draw_id;
    }
    @Column(name ="OUTSTOCK_POSITION_ID",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public String getOutstock_position_id(){
        return this.outstock_position_id;
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
