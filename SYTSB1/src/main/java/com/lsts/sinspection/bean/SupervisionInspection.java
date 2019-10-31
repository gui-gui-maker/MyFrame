
package com.lsts.sinspection.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 产品监检
 * SupervisionInspection entity. 
 * @author GaoYa
 * @date 2014-03-26 11:45:00
 */
@Entity
@Table(name = "TZSB_SUPERVISION_INSPECTION")
public class SupervisionInspection implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;	//流水号

	private String inspection_code;	// 监检编号
	public String getInspection_code() {
		return inspection_code;
	}

	public void setInspection_code(String inspection_code) {
		this.inspection_code = inspection_code;
	}


	private String made_unit_id;	// 制造单位编号
	private String made_unit_name;	// 制造单位名称
	private String license_num;		// 许可证级别及编号
	private String device_type;		// 设备类别（数据字典：BASE_CHECK_DEVICE_TYPE）
	private String product_name;	// 产品名称
	private String product_model;	// 产品型号
	private String product_type;	// 产品类别
	private String product_code;	// 产品编号

	private Date product_made_date;	// 产品制造日期	

	private String product_pressure;	// 公称压力
	private String product_diameter;	// 公称直径
	private String product_temperature;	// 设计温度
	private String product_medium;		// 介质
	private String product_main_material;	// 主要材料
	private String product_welding_material;// 焊接材料
	private String product_valuebody_material;	// 阀体材料
	private String product_wall_thickness;	// 壁厚
	private String content_doc_id;			// 正文（附件ID）
	private String send_department_id;		// 流转科室编号
	private String send_department_name;	// 流转科室名称
	private String send_user_id;		// 流转监检人员编号
	private String send_user_name;		// 流转监检人员姓名
	private String enter_user_id;	// 录入人编号
	private String enter_user_name;	// 录入人姓名
	private Date enter_date;		// 录入时间
	private String last_modify_id;	// 最后修改人编号
	private String last_modify_name;	// 最后修改人姓名
	private Date last_modify_date;		// 最后修改时间
	private String status;			// 状态（0：未提交，1：已提交，2：已生成报告）
	private String fk_inspection_info_id;

	

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	@Column(name="ENTER_USER_ID")
	public String getEnter_user_id() {
		return enter_user_id;
	}

	public void setEnter_user_id(String enter_user_id) {
		this.enter_user_id = enter_user_id;
	}

	@Column(name="ENTER_USER_NAME")
	public String getEnter_user_name() {
		return enter_user_name;
	}

	public void setEnter_user_name(String enter_user_name) {
		this.enter_user_name = enter_user_name;
	}



	@Column(name="MADE_UNIT_ID")
	public String getMade_unit_id() {
		return made_unit_id;
	}

	public void setMade_unit_id(String made_unit_id) {
		this.made_unit_id = made_unit_id;
	}

	@Column(name="MADE_UNIT_NAME")
	public String getMade_unit_name() {
		return made_unit_name;
	}

	public void setMade_unit_name(String made_unit_name) {
		this.made_unit_name = made_unit_name;
	}

	@Column(name="DEVICE_TYPE")
	public String getDevice_type() {
		return device_type;
	}

	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}

	@Column(name="PRODUCT_NAME")
	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	@Column(name="PRODUCT_MODEL")
	public String getProduct_model() {
		return product_model;
	}

	public void setProduct_model(String product_model) {
		this.product_model = product_model;
	}

	@Column(name="PRODUCT_TYPE")
	public String getProduct_type() {
		return product_type;
	}

	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}

	@Column(name="PRODUCT_CODE")
	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	@Column(name="PRODUCT_PRESSURE")
	public String getProduct_pressure() {
		return product_pressure;
	}

	public void setProduct_pressure(String product_pressure) {
		this.product_pressure = product_pressure;
	}

	@Column(name="PRODUCT_DIAMETER")
	public String getProduct_diameter() {
		return product_diameter;
	}

	public void setProduct_diameter(String product_diameter) {
		this.product_diameter = product_diameter;
	}

	@Column(name="PRODUCT_TEMPERATURE")
	public String getProduct_temperature() {
		return product_temperature;
	}

	public void setProduct_temperature(String product_temperature) {
		this.product_temperature = product_temperature;
	}

	@Column(name="PRODUCT_MEDIUM")
	public String getProduct_medium() {
		return product_medium;
	}

	public void setProduct_medium(String product_medium) {
		this.product_medium = product_medium;
	}

	@Column(name="PRODUCT_MAIN_MATERIAL")
	public String getProduct_main_material() {
		return product_main_material;
	}

	public void setProduct_main_material(String product_main_material) {
		this.product_main_material = product_main_material;
	}

	@Column(name="PRODUCT_WELDING_MATERIAL")
	public String getProduct_welding_material() {
		return product_welding_material;
	}

	public void setProduct_welding_material(String product_welding_material) {
		this.product_welding_material = product_welding_material;
	}

	@Column(name="PRODUCT_VALUEBODY_MATERIAL")
	public String getProduct_valuebody_material() {
		return product_valuebody_material;
	}

	public void setProduct_valuebody_material(String product_valuebody_material) {
		this.product_valuebody_material = product_valuebody_material;
	}

	@Column(name="PRODUCT_WALL_THICKNESS")
	public String getProduct_wall_thickness() {
		return product_wall_thickness;
	}

	public void setProduct_wall_thickness(String product_wall_thickness) {
		this.product_wall_thickness = product_wall_thickness;
	}

	@Column(name="CONTENT_DOC_ID")
	public String getContent_doc_id() {
		return content_doc_id;
	}

	public void setContent_doc_id(String content_doc_id) {
		this.content_doc_id = content_doc_id;
	}

	@Column(name="SEND_DEPARTMENT_ID")
	public String getSend_department_id() {
		return send_department_id;
	}

	public void setSend_department_id(String send_department_id) {
		this.send_department_id = send_department_id;
	}

	@Column(name="SEND_DEPARTMENT_NAME")
	public String getSend_department_name() {
		return send_department_name;
	}

	public void setSend_department_name(String send_department_name) {
		this.send_department_name = send_department_name;
	}

	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name="PRODUCT_MADE_DATE")
	public Date getProduct_made_date() {
		return product_made_date;
	}

	public void setProduct_made_date(Date product_made_date) {
		this.product_made_date = product_made_date;
	}

	@Column(name="ENTER_DATE")
	public Date getEnter_date() {
		return enter_date;
	}

	public void setEnter_date(Date enter_date) {
		this.enter_date = enter_date;
	}

	@Column(name="LICENSE_NUM")
	public String getLicense_num() {
		return license_num;
	}

	public void setLicense_num(String license_num) {
		this.license_num = license_num;
	}

	@Column(name="LAST_MODIFY_ID")
	public String getLast_modify_id() {
		return last_modify_id;
	}

	public void setLast_modify_id(String last_modify_id) {
		this.last_modify_id = last_modify_id;
	}

	@Column(name="LAST_MODIFY_NAME")
	public String getLast_modify_name() {
		return last_modify_name;
	}

	public void setLast_modify_name(String last_modify_name) {
		this.last_modify_name = last_modify_name;
	}

	@Column(name="LAST_MODIFY_DATE")
	public Date getLast_modify_date() {
		return last_modify_date;
	}

	public void setLast_modify_date(Date last_modify_date) {
		this.last_modify_date = last_modify_date;
	}
	@Column(name="FK_INSPECTION_INFO_ID")
	public String getFk_inspection_info_id() {
		return fk_inspection_info_id;
	}

	public void setFk_inspection_info_id(String fk_inspection_info_id) {
		this.fk_inspection_info_id = fk_inspection_info_id;
	}
	@Column(name="SEND_USER_ID")
	public String getSend_user_id() {
		return send_user_id;
	}

	public void setSend_user_id(String send_user_id) {
		this.send_user_id = send_user_id;
	}
	@Column(name="SEND_USER_NAME")
	public String getSend_user_name() {
		return send_user_name;
	}

	public void setSend_user_name(String send_user_name) {
		this.send_user_name = send_user_name;
	}

}

