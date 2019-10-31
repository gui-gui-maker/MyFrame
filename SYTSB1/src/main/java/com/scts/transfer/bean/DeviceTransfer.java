package com.scts.transfer.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 设备数据传输控制器
 * DeviceTransfer entity. 
 * @author GaoYa
 * @date 2017-02-22 上午10:13:00
 */
@Entity
@Table(name = "TZSB_DEVICE_TRANSFER")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceTransfer implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;		// ID
	private String pro_name;	// 项目名称
	private String pro_desc;	// 项目描述
	private String op_user_id;	// 操作人ID
	private String op_user_name;// 操作人姓名
	private Date   op_date;		// 操作日期
	private String remarks;		// 备注
	private String data_status;	// 数据传输状态（数据字典DEVICE_TRANSFER_STATUS，0：未启动 1：已启动 2：已停止运行 99：已作废）

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getData_status() {
		return data_status;
	}

	public void setData_status(String data_status) {
		this.data_status = data_status;
	}

	public String getOp_user_id() {
		return op_user_id;
	}

	public void setOp_user_id(String op_user_id) {
		this.op_user_id = op_user_id;
	}

	public String getOp_user_name() {
		return op_user_name;
	}

	public void setOp_user_name(String op_user_name) {
		this.op_user_name = op_user_name;
	}

	public Date getOp_date() {
		return op_date;
	}

	public void setOp_date(Date op_date) {
		this.op_date = op_date;
	}

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	public String getPro_desc() {
		return pro_desc;
	}

	public void setPro_desc(String pro_desc) {
		this.pro_desc = pro_desc;
	}
}
