package com.lsts.qualitymanage.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_QUALITY_TASK")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskConvey implements BaseEntity {
	
	private String id; //主键
	
	private String ck_nature; //检验性质
	
	private String ck_sn; //检验编号
	
	private String report_sn; //报告编号
	
	private String user_name;//用户名称
	
	private String device_name; //设备名称
	
	private String device_sn; //设备编号
	
	private String device_place;//设备所在地
	
	private String ck_content;//检验/委托内容
	
	private String material_part;//资料份数
	
	private Date report_date;// 报检/委托时间
	
	private String user_phone;//联系电话
	
	private String user_man;//联系人
	
	private String compact_sn; //合同编号
	
	private BigDecimal compact_money; //合同金额
	
	private Date compact_date;//签订时间
	
	private String remark;//备注
	
	private String agent_id;//经办人ID
	
	private String agent_name;//经办人
	
	private Date agent_date;//经办日期
	
	private String status;//状态
	
	private Date next_date;//下发时间
	
	private String register_id;//登记人ID
	
	private String register_name;//登记人
	
	private Date register_date;//登记时间
	
	private String reception_id;//接收人ID
	
	private String reception_name;//接收人
	
	private Date reception_date;//接收时间

	private String receive_dep; //承接部门
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@Column(name ="CK_NATURE")
	public String getCk_nature() {
		return ck_nature;
	}

	public void setCk_nature(String ck_nature) {
		this.ck_nature = ck_nature;
	}
	@Column(name = "CK_SN")
	public String getCk_sn() {
		return ck_sn;
	}

	public void setCk_sn(String ck_sn) {
		this.ck_sn = ck_sn;
	}
	@Column(name = "REPORT_SN")
	public String getReport_sn() {
		return report_sn;
	}

	public void setReport_sn(String report_sn) {
		this.report_sn = report_sn;
	}
	@Column(name = "USER_NAME")
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	@Column(name = "DEVICE_NAME")
	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}
	@Column(name = "DEVICE_SN")
	public String getDevice_sn() {
		return device_sn;
	}

	public void setDevice_sn(String device_sn) {
		this.device_sn = device_sn;
	}
	@Column(name = "DEVICE_PLACE")
	public String getDevice_place() {
		return device_place;
	}

	public void setDevice_place(String device_place) {
		this.device_place = device_place;
	}
	@Column(name = "CK_CONTENT")
	public String getCk_content() {
		return ck_content;
	}

	public void setCk_content(String ck_content) {
		this.ck_content = ck_content;
	}
	@Column(name = "MATERIAL_PART")
	public String getMaterial_part() {
		return material_part;
	}

	public void setMaterial_part(String material_part) {
		this.material_part = material_part;
	}
	@Column(name = "REPORT_DATE")
	public Date getReport_date() {
		return report_date;
	}

	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}
	@Column(name = "USER_PHONE")
	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	@Column(name = "USER_MAN")
	public String getUser_man() {
		return user_man;
	}

	public void setUser_man(String user_man) {
		this.user_man = user_man;
	}
	@Column(name = "COMPACT_SN")
	public String getCompact_sn() {
		return compact_sn;
	}

	public void setCompact_sn(String compact_sn) {
		this.compact_sn = compact_sn;
	}
	@Column(name = "COMPACT_MONEY")
	public BigDecimal getCompact_money() {
		return compact_money;
	}

	public void setCompact_money(BigDecimal compact_money) {
		this.compact_money = compact_money;
	}
	@Column(name = "COMPACT_DATE")
	public Date getCompact_date() {
		return compact_date;
	}

	public void setCompact_date(Date compact_date) {
		this.compact_date = compact_date;
	}
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "AGENT_ID")
	public String getAgent_id() {
		return agent_id;
	}

	public void setAgent_id(String agent_id) {
		this.agent_id = agent_id;
	}
	@Column(name = "AGENT_NAME")
	public String getAgent_name() {
		return agent_name;
	}

	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}
	@Column(name = "AGENT_DATE")
	public Date getAgent_date() {
		return agent_date;
	}

	public void setAgent_date(Date agent_date) {
		this.agent_date = agent_date;
	}
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "NEXT_DATE")
	public Date getNext_date() {
		return next_date;
	}

	public void setNext_date(Date next_date) {
		this.next_date = next_date;
	}
	@Column(name = "REGISTER_ID")
	public String getRegister_id() {
		return register_id;
	}

	public void setRegister_id(String register_id) {
		this.register_id = register_id;
	}
	@Column(name = "REGISTER_NAME")
	public String getRegister_name() {
		return register_name;
	}

	public void setRegister_name(String register_name) {
		this.register_name = register_name;
	}
	@Column(name = "REGISTER_DATE")
	public Date getRegister_date() {
		return register_date;
	}

	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}
	@Column(name = "RECEPTION_ID")
	public String getReception_id() {
		return reception_id;
	}

	public void setReception_id(String reception_id) {
		this.reception_id = reception_id;
	}
	@Column(name = "RECEPTION_NAME")
	public String getReception_name() {
		return reception_name;
	}

	public void setReception_name(String reception_name) {
		this.reception_name = reception_name;
	}
	@Column(name = "RECEPTION_DATE")
	public Date getReception_date() {
		return reception_date;
	}

	public void setReception_date(Date reception_date) {
		this.reception_date = reception_date;
	}

	@Column(name = "RECEIVE_DEP")
	public String getReceive_dep() {
		return receive_dep;
	}

	public void setReceive_dep(String receive_dep) {
		this.receive_dep = receive_dep;
	}
	
	
	
	
	
}
