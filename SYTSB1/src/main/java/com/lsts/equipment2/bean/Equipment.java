package com.lsts.equipment2.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;
import com.lsts.equipment2.service.EquipmentManager;

/**
 * 设备信息
 * BaseEquipment entity. 
 * @author GaoYa
 * @date 2014-02-17 17:15:00
 */
@Entity
@Table(name = "TJY2_EQUIPMENT")
@JsonIgnoreProperties(ignoreUnknown = true,value={"equipmentBox"})
public class Equipment implements BaseEntity{

	private static final long serialVersionUID = 1L;
	private String id;	// ID
	private String card_no;	// 卡片编号
	private String eq_no;	// 设备编号
	private String eq_category;	// 设备类型   码表：TJY2_EQ_CATEGORY
	private String eq_name;	// 设备名称
	private String eq_model;	// 规格型号
	private String eq_sn;	// 出厂编号
	private String eq_used;	// 设备用途
	private String eq_manufacturer;	// 制造厂家
	private String eq_supplier_name;	// 供应商名称
	private String eq_supplier_id;	// 供应商ID
	private String eq_status;	//设备状态 码表：TJY2_EQ_STATUS
	private String inventory;//盘点状态
	private Date inventory_date;//盘点时间
	private String inventory_id;//盘点人ID
	private String inventory_name;//盘点人姓名
	private String eq_use_status;	// 设备使用状态 码表：TJY2_EQ_USE_STATUS	
	private String eq_value;	// 设备原值（元）
	private String eq_accurate;	// 精度
	private String is_check;			// 计量方式 码表：EQ_IS_CHECK（1：校准 2：检定 3：自校）
	private Date eq_next_check_date;	// 下次检定/校准日期
	private Date eq_qj_check_date;	// 期间核查日期
	
	private String eq_user_id;		//管理(使用)人员ID
	private String eq_user;		// 管理(使用)人员
	private String eq_user1_id;		//当前管理(使用)人员ID
	private String eq_user1;		// 当前管理(使用)人员
	private String eq_use_department_id;		//管理(使用)部门ID
	private String eq_use_department;	// 管理(使用)部门
	private String eq_use_department1_id;	// 当前管理(使用)部门ID
	private String eq_use_department1;	// 当前管理(使用)部门
	
	private Date eq_draw_date;	// 领用/借用时间
	private Date eq_return_date;	// 归还时间
	private String eq_buy_price;	// 设备单价
	private Date eq_buy_date;		// 购进时间	
	private String eq_buy_count;	// 入库数量（购买数量）
	private Date eq_instock_date;	// 入库时间
	private Date eq_scrap_date;		// 报废时间
	private Date eq_stop_date;		// 停用时间
	private Date eq_repair_date;	// 维修时间	
	private String eq_warning_count;	// 预警数量（0代表不预警）
	private String eq_validate_count;	// 剩余数量
	private Date create_date;	// 创建时间
	private String create_by;	// 创建人
	private Date last_modify_date;	// 最后更新时间
	private String last_modify_by;	// 最后更新人
	private String is_new;			// 是否是需要新购买（系统暂无）的设备（0：否 1：是）
	private String remark;			// 备注
	/*// 关联供应商对象
	public BaseEquipment2Supplier baseEquipmentSupplier;*/
	private String eq_archive_class_id;		//档案分类号
	private Date eq_produce_date;		//制造时间
	private Integer eq_check_cycle;		//检定周期
	private Date eq_last_check_date;		//上次检定日期
	private Date eq_execute_date;		//实际检定日期
	private String check_unit;		//检定单位
	private String check_op;		//送检负责人
	private String barcode;		//设备条形码
	private String box_number;		//设备条形码
	private String eq_isdelete;		//是否删除 0：未删除， 1：已删除
	private EquipmentBox equipmentBox;//固化箱
	private String eq_inout_status=EquipmentManager.INSTOCK1;		//待入库
	private String owner;  //归属
	private String position;  //设备位置
	private EquipmentLoan equipmentLoan; //借用/领用
	private String box_status=EquipmentManager.BOX_STATUS2;		//未装箱
	public void setId(String id) {
		this.id = id;
	}
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}
	public void setLast_modify_by(String last_modify_by) {
		this.last_modify_by = last_modify_by;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public void setLast_modify_date(Date last_modify_date) {
		this.last_modify_date = last_modify_date;
	}
	public void setEq_user(String eq_user) {
		this.eq_user = eq_user;
	}
	public void setEq_user1(String eq_user1) {
		this.eq_user1 = eq_user1;
	}
	public void setEq_buy_count(String eq_buy_count) {
		this.eq_buy_count = eq_buy_count;
	}
	public void setEq_warning_count(String eq_warning_count) {
		this.eq_warning_count = eq_warning_count;
	}
	public void setEq_validate_count(String eq_validate_count) {
		this.eq_validate_count = eq_validate_count;
	}
	public void setEq_no(String eq_no) {
		this.eq_no = eq_no;
	}
	public void setEq_name(String eq_name) {
		this.eq_name = eq_name;
	}
	public void setEq_model(String eq_model) {
		this.eq_model = eq_model;
	}
	public void setEq_sn(String eq_sn) {
		this.eq_sn = eq_sn;
	}
	public void setEq_used(String eq_used) {
		this.eq_used = eq_used;
	}
	public void setEq_manufacturer(String eq_manufacturer) {
		this.eq_manufacturer = eq_manufacturer;
	}
	public void setEq_supplier_id(String eq_supplier_id) {
		this.eq_supplier_id = eq_supplier_id;
	}
	public void setEq_archive_class_id(String eq_archive_class_id) {
		this.eq_archive_class_id = eq_archive_class_id;
	}
	public void setEq_produce_date(Date eq_produce_date) {
		this.eq_produce_date = eq_produce_date;
	}
	public void setEq_check_cycle(Integer eq_check_cycle) {
		this.eq_check_cycle = eq_check_cycle;
	}
	public void setEq_execute_date(Date eq_execute_date) {
		this.eq_execute_date = eq_execute_date;
	}
	public void setEq_last_check_date(Date eq_last_check_date) {
		this.eq_last_check_date= eq_last_check_date;
	}
	public void setEq_inout_status(String eq_inout_status) {
		this.eq_inout_status = eq_inout_status;
	}
	/*@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "eq_supplier_id")
	public BaseEquipment2Supplier getBaseEquipmentSupplier() {
		return baseEquipmentSupplier;
	}
	public void setBaseEquipmentSupplier(BaseEquipment2Supplier baseEquipmentSupplier) {
		this.baseEquipmentSupplier = baseEquipmentSupplier;
	}*/
	
	public void setEq_status(String eq_status) {
		this.eq_status = eq_status;
	}
	public void setInventory(String value) {
		this.inventory = value;
	}
	public void setInventory_date(Date value) {
		this.inventory_date = value;
	}
	public void setInventory_id(String value) {
		this.inventory_id = value;
	}
	public void setInventory_name(String value) {
		this.inventory_name = value;
	}
	public void setEq_use_status(String eq_use_status) {
		this.eq_use_status = eq_use_status;
	}
	public void setEq_accurate(String eq_accurate) {
		this.eq_accurate = eq_accurate;
	}
	public void setEq_buy_date(Date eq_buy_date) {
		this.eq_buy_date = eq_buy_date;
	}
	public void setEq_next_check_date(Date eq_next_check_date) {
		this.eq_next_check_date = eq_next_check_date;
	}
	public void setEq_use_department1_id(String eq_use_department1_id) {
		this.eq_use_department1_id = eq_use_department1_id;
	}
	public void setEq_use_department1(String eq_use_department1) {
		this.eq_use_department1 = eq_use_department1;
	}
	public void setEq_draw_date(Date eq_draw_date) {
		this.eq_draw_date = eq_draw_date;
	}
	public void setEq_return_date(Date eq_return_date) {
		this.eq_return_date = eq_return_date;
	}
	public void setEq_buy_price(String eq_buy_price) {
		this.eq_buy_price = eq_buy_price;
	}
	public void setEq_qj_check_date(Date eq_qj_check_date) {
		this.eq_qj_check_date = eq_qj_check_date;
	}
	public void setEq_category(String eq_category) {
		this.eq_category = eq_category;
	}
	public void setEq_use_department(String eq_use_department) {
		this.eq_use_department = eq_use_department;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setEq_instock_date(Date eq_instock_date) {
		this.eq_instock_date = eq_instock_date;
	}
	public void setIs_new(String is_new) {
		this.is_new = is_new;
	}
	public void setEq_value(String eq_value) {
		this.eq_value = eq_value;
	}
	public void setIs_check(String is_check) {
		this.is_check = is_check;
	}
	public void setEq_scrap_date(Date eq_scrap_date) {
		this.eq_scrap_date = eq_scrap_date;
	}
	public void setEq_stop_date(Date eq_stop_date) {
		this.eq_stop_date = eq_stop_date;
	}
	public void setEq_repair_date(Date eq_repair_date) {
		this.eq_repair_date = eq_repair_date;
	}
	public void setCheck_unit(String check_unit) {
		this.check_unit = check_unit;
	}
	public void setCheck_op(String check_op) {
		this.check_op = check_op;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public void setBox_number(String value) {
		this.box_number = value;
	}
	public void setEq_isdelete(String eq_isdelete) {
		this.eq_isdelete = eq_isdelete;
	}
	public void setBox_status(String box_status) {
		this.box_status = box_status;
	}
	
	public void setEq_user_id(String eq_user_id) {
		this.eq_user_id = eq_user_id;
	}
	public void setEq_user1_id(String eq_user1_id) {
		this.eq_user1_id = eq_user1_id;
	}
	public void setEq_use_department_id(String eq_use_department_id) {
		this.eq_use_department_id = eq_use_department_id;
	}
	public void setOwner(String value) {
		this.owner = value;
	}
	public void setPosition(String value) {
		this.position = value;
	}
	public void setEq_supplier_name(String eq_supplier_name) {
		this.eq_supplier_name = eq_supplier_name;
	}
	@ManyToOne
	@JoinColumn(name="FK_UV_ID")
	public EquipmentBox getEquipmentBox() {
		return equipmentBox;
	}
	public void setEquipmentBox(EquipmentBox equipmentBox) {
		this.equipmentBox = equipmentBox;
	}
	
	@ManyToOne
	@JoinColumn(name="FK_BORROW_DRAW_ID")
	public EquipmentLoan getEquipmentLoan() {
		return equipmentLoan;
	}
	public void setEquipmentLoan(EquipmentLoan equipmentLoan) {
		this.equipmentLoan = equipmentLoan;
	}
	
	@Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="CARD_NO",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getCard_no(){
        return this.card_no;
    }
	@Column(name ="EQ_SUPPLIER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
	public String getEq_supplier_id() {
		return eq_supplier_id;
	}
    @Column(name ="EQ_NO",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getEq_no(){
        return this.eq_no;
    }
    @Column(name ="EQ_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getEq_name(){
        return this.eq_name;
    }
    @Column(name ="EQ_MODEL",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getEq_model(){
        return this.eq_model;
    }
    @Column(name ="EQ_SN",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getEq_sn(){
        return this.eq_sn;
    }
    @Column(name ="EQ_USED",unique=false,nullable=true,insertable=true,updatable=true,length=64)
    public String getEq_used(){
        return this.eq_used;
    }
    @Column(name ="EQ_MANUFACTURER",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getEq_manufacturer(){
        return this.eq_manufacturer;
    }
    @Column(name ="EQ_STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=10)
    public String getEq_status(){
        return this.eq_status;
    }
    @Column(name ="INVENTORY",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getInventory(){
        return this.inventory;
    }
    @Column(name ="INVENTORY_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public Date getInventory_date(){
        return this.inventory_date;
    }
    @Column(name ="INVENTORY_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getInventory_id(){
        return this.inventory_id;
    }
    @Column(name ="INVENTORY_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getInventory_name(){
        return this.inventory_name;
    }
    @Column(name ="EQ_USE_STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=10)
    public String getEq_use_status(){
        return this.eq_use_status;
    }
    @Column(name ="EQ_ACCURATE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getEq_accurate(){
        return this.eq_accurate;
    }
    @Column(name ="EQ_NEXT_CHECK_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getEq_next_check_date(){
        return this.eq_next_check_date;
    }
    @Column(name ="EQ_USE_DEPARTMENT1_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getEq_use_department1_id(){
        return this.eq_use_department1_id;
    }
    @Column(name ="EQ_USE_DEPARTMENT1",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getEq_use_department1(){
        return this.eq_use_department1;
    }
    @Column(name ="EQ_USER",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getEq_user(){
        return this.eq_user;
    }
    @Column(name ="EQ_USER1",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getEq_user1(){
        return this.eq_user1;
    }
    @Column(name ="EQ_DRAW_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getEq_draw_date(){
        return this.eq_draw_date;
    }
    @Column(name ="EQ_RETURN_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getEq_return_date(){
        return this.eq_return_date;
    }
    @Column(name ="EQ_BUY_PRICE",unique=false,nullable=true,insertable=true,updatable=true,length=8)
    public String getEq_buy_price(){
        return this.eq_buy_price;
    }
    @Column(name ="EQ_BUY_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getEq_buy_date(){
        return this.eq_buy_date;
    }
    @Column(name ="EQ_BUY_COUNT",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getEq_buy_count(){
        return this.eq_buy_count;
    }
    @Column(name ="EQ_WARNING_COUNT",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getEq_warning_count(){
        return this.eq_warning_count;
    }
    @Column(name ="EQ_VALIDATE_COUNT",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getEq_validate_count(){
        return this.eq_validate_count;
    }
    @Column(name ="CREATE_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getCreate_date(){
        return this.create_date;
    }
    @Column(name ="CREATE_BY",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getCreate_by(){
        return this.create_by;
    }
    @Column(name ="LAST_MODIFY_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getLast_modify_date(){
        return this.last_modify_date;
    }
    @Column(name ="LAST_MODIFY_BY",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getLast_modify_by(){
        return this.last_modify_by;
    }
    @Column(name ="EQ_QJ_CHECK_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getEq_qj_check_date(){
        return this.eq_qj_check_date;
    }
    @Column(name ="EQ_CATEGORY",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getEq_category(){
        return this.eq_category;
    }
    @Column(name ="EQ_USE_DEPARTMENT",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getEq_use_department(){
        return this.eq_use_department;
    }
    @Column(name ="EQ_INSTOCK_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getEq_instock_date(){
        return this.eq_instock_date;
    }
    @Column(name ="REMARK",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getRemark(){
        return this.remark;
    }
    @Column(name ="IS_NEW",unique=false,nullable=true,insertable=true,updatable=true,length=1)
    public String getIs_new(){
        return this.is_new;
    }
    @Column(name ="EQ_VALUE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getEq_value(){
        return this.eq_value;
    }
    @Column(name ="IS_CHECK",unique=false,nullable=true,insertable=true,updatable=true,length=1)
    public String getIs_check(){
        return this.is_check;
    }
    @Column(name ="EQ_SCRAP_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getEq_scrap_date(){
        return this.eq_scrap_date;
    }
    @Column(name ="EQ_STOP_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getEq_stop_date(){
        return this.eq_stop_date;
    }
    @Column(name ="EQ_REPAIR_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getEq_repair_date(){
        return this.eq_repair_date;
    }
    @Column(name ="EQ_ARCHIVE_CLASS_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getEq_archive_class_id(){
        return this.eq_archive_class_id;
    }
    @Column(name ="EQ_PRODUCE_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getEq_produce_date(){
        return this.eq_produce_date;
    }
    @Column(name ="EQ_CHECK_CYCLE",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public Integer getEq_check_cycle(){
        return this.eq_check_cycle;
    }
    @Column(name ="EQ_EXECUTE_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getEq_execute_date(){
        return this.eq_execute_date;
    }
    @Column(name ="EQ_LAST_CHECK_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getEq_last_check_date(){
        return this.eq_last_check_date;
    }
    @Column(name ="CHECK_UNIT",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getCheck_unit(){
        return this.check_unit;
    }
    @Column(name ="CHECK_OP",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getCheck_op(){
        return this.check_op;
    }
    @Column(name ="BARCODE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getBarcode(){
        return this.barcode;
    }
    @Column(name ="BOX_NUMBER",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getBox_number(){
        return this.box_number;
    }
    @Column(name ="EQ_ISDELETE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getEq_isdelete(){
        return this.eq_isdelete;
    }
    @Column(name ="EQ_INOUT_STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getEq_inout_status(){
        return this.eq_inout_status;
    }
    @Column(name ="BOX_STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getBox_status(){
        return this.box_status;
    }
    @Column(name ="EQ_USER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getEq_user_id(){
        return this.eq_user_id;
    }
    @Column(name ="EQ_USER1_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getEq_user1_id(){
        return this.eq_user1_id;
    }
    @Column(name ="EQ_USE_DEPARTMENT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getEq_use_department_id(){
        return this.eq_use_department_id;
    }
    @Column(name ="EQ_SUPPLIER_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getEq_supplier_name(){
        return this.eq_supplier_name;
    }
    @Column(name ="OWNER",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getOwner(){
        return this.owner;
    }
    @Column(name ="POSITION",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getPosition(){
        return this.position;
    }
	@SuppressWarnings("unchecked")
	public Map to_Map() {
        Map map = new HashMap();
        map.put("id", id);
        map.put("eq_name", eq_name);
        map.put("eq_model", eq_model);
        map.put("eq_manufacturer", eq_manufacturer);
        map.put("eq_accurate", eq_accurate);
        map.put("eq_buy_price", eq_buy_price);
        map.put("create_by", create_by);
        map.put("create_date", create_date);
        map.put("last_modify_by", last_modify_by);
        map.put("last_modify_date", last_modify_date);
        return map;
    }
}
