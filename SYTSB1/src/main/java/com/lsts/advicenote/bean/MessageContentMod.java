package com.lsts.advicenote.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name="TJY2_MESSAGE_CONTENT_MODULES")
public class MessageContentMod implements BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 527950757105201754L;
	private String id;		
	private String module_code;//编号（不能重复，不能修改）
	private String module_name;//模块名称
	private String module_desc;//模块功能描述
	private String create_op_id;//创建人id
	private String create_op;//创建人
	private Date create_date;//创建时间
	private String send_target;//消息发送对象描述
	private String param_01;//参数列，示意：name:code,name:code
	private String param_02;//备用		
	private String param_03;//Y			
	private String param_04;//Y			
	private String param_05;//Y		
	private String data_status;//数据状态 0正常，99 删除

	

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	public String getModule_code() {
		return module_code;
	}

	public void setModule_code(String module_code) {
		this.module_code = module_code;
	}

	public String getModule_name() {
		return module_name;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	public String getModule_desc() {
		return module_desc;
	}

	public void setModule_desc(String module_desc) {
		this.module_desc = module_desc;
	}

	public String getCreate_op_id() {
		return create_op_id;
	}

	public void setCreate_op_id(String create_op_id) {
		this.create_op_id = create_op_id;
	}

	public String getCreate_op() {
		return create_op;
	}

	public void setCreate_op(String create_op) {
		this.create_op = create_op;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getSend_target() {
		return send_target;
	}

	public void setSend_target(String send_target) {
		this.send_target = send_target;
	}

	public String getParam_01() {
		return param_01;
	}

	public void setParam_01(String param_01) {
		this.param_01 = param_01;
	}

	public String getParam_02() {
		return param_02;
	}

	public void setParam_02(String param_02) {
		this.param_02 = param_02;
	}

	public String getParam_03() {
		return param_03;
	}

	public void setParam_03(String param_03) {
		this.param_03 = param_03;
	}

	public String getParam_04() {
		return param_04;
	}

	public void setParam_04(String param_04) {
		this.param_04 = param_04;
	}

	public String getParam_05() {
		return param_05;
	}

	public void setParam_05(String param_05) {
		this.param_05 = param_05;
	}

	public String getData_status() {
		return data_status;
	}

	public void setData_status(String data_status) {
		this.data_status = data_status;
	}
	

}
