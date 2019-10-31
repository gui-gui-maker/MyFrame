package com.lsts.hall.bean;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 移动检验业务任务接收明细表
 * 
 * @author GaoYa
 * @date 2016-04-18
 */
@Entity
@Table(name = "TZSB_INSPECTION_HALL_INFO")
public class ReportHallInfo implements BaseEntity {

	private static final long serialVersionUID = 1L;	
		private String	id	;	//	
		private String	fk_hall_para_id	;	//	报检大厅ID
		private String  fk_device_id;	// 接收设备ID
		private String	receive_user_id;		// 接收人ID
		private String	receive_user_name;		// 接收人姓名
		private Date	receive_time;	// 接收时间
		private Date    op_time;		// 最后操作时间
		private String  remarks;		// 备注
		private String  data_status;	// 数据状态（0：正常 99：已作废）
		
		@Id
		@GeneratedValue(generator = "system-uuid")
		@GenericGenerator(name = "system-uuid", strategy = "uuid")
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getFk_hall_para_id() {
			return fk_hall_para_id;
		}
		public void setFk_hall_para_id(String fk_hall_para_id) {
			this.fk_hall_para_id = fk_hall_para_id;
		}
		public String getFk_device_id() {
			return fk_device_id;
		}
		public void setFk_device_id(String fk_device_id) {
			this.fk_device_id = fk_device_id;
		}
	
		public String getReceive_user_id() {
			return receive_user_id;
		}
		public void setReceive_user_id(String receive_user_id) {
			this.receive_user_id = receive_user_id;
		}
		public String getReceive_user_name() {
			return receive_user_name;
		}
		public void setReceive_user_name(String receive_user_name) {
			this.receive_user_name = receive_user_name;
		}
		public Date getReceive_time() {
			return receive_time;
		}
		public void setReceive_time(Date receive_time) {
			this.receive_time = receive_time;
		}
		public Date getOp_time() {
			return op_time;
		}
		public void setOp_time(Date op_time) {
			this.op_time = op_time;
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
