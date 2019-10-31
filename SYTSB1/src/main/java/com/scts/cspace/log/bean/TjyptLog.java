package com.scts.cspace.log.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name="TJYPT_LOG")
public class TjyptLog implements BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//
	private String target_type;//对象类型（1 文件夹 2 文件）
	private String ip;//ip地址
	private String op_name;//操作人姓名
	private Date op_time;//操作时间
	private String target_id;//操作文件对象
	private String event;//操作内容
	private String op_id;//操作人id
	private String op_space;//操作人id
	private String target_space;//操作人id
	private String op_type;//操作类型 1 上传文件 2 下载文件 3 预览文件 4 分享文件 5 删除文件 6 恢复文件
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTarget_type() {
		return target_type;
	}

	public void setTarget_type(String target_type) {
		this.target_type = target_type;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOp_name() {
		return op_name;
	}

	public void setOp_name(String op_name) {
		this.op_name = op_name;
	}

	public Date getOp_time() {
		return op_time;
	}

	public void setOp_time(Date op_time) {
		this.op_time = op_time;
	}

	public String getTarget_id() {
		return target_id;
	}

	public void setTarget_id(String target_id) {
		this.target_id = target_id;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getOp_id() {
		return op_id;
	}

	public void setOp_id(String op_id) {
		this.op_id = op_id;
	}

	public String getOp_space() {
		return op_space;
	}

	public void setOp_space(String op_space) {
		this.op_space = op_space;
	}

	public String getTarget_space() {
		return target_space;
	}

	public void setTarget_space(String target_space) {
		this.target_space = target_space;
	}

	public String getOp_type() {
		return op_type;
	}

	public void setOp_type(String op_type) {
		this.op_type = op_type;
	}

	

}
