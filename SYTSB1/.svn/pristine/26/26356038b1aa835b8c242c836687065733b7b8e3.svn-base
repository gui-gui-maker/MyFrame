package com.scts.maintenance.bean;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 系统建设台账表
 * Maintenance entity. 
 * @author GaoYa
 * @date 2015-09-08 下午01:15:00
 */
@Entity
@Table(name = "TZSB_MAINTENANCE")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Maintenance implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;		// ID
	private String title;	// 标题
	private String create_user_id;	// 创建人ID
	private String create_user_name;// 创建人姓名
	private Date   create_date;		// 创建日期
	private String remarks;			// 备注
	private String data_status;		// 数据状态（数据字典MAINTENANCE_DATA_STATUS，0：未受理 1：已受理 2：开发中 3：已完成 4：已发布 99：已删除）



	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	public Collection<MaintenanceInfo> maintenanceInfo;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "maintenance", orphanRemoval = true)
	public Collection<MaintenanceInfo> getMaintenanceInfo() {
		return maintenanceInfo;
	}

	public void setMaintenanceInfo(Collection<MaintenanceInfo> maintenanceInfo) {
		this.maintenanceInfo = maintenanceInfo;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreate_user_id() {
		return create_user_id;
	}

	public void setCreate_user_id(String create_user_id) {
		this.create_user_id = create_user_id;
	}

	public String getCreate_user_name() {
		return create_user_name;
	}

	public void setCreate_user_name(String create_user_name) {
		this.create_user_name = create_user_name;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
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
	
}
