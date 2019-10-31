package com.scts.car.bean;

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
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "TJY2_CAR_WB_RECORD")
public class CarWbRecord implements BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;// 主键id

	private String fkCarId;// 车辆id

	private String carNum;// 车牌号

	private String fkWbId;// 维保id

	private String wbSn;// 维保申请编号

	private String wbComName;// 维保单位

	private String wbContacts;// 维保联系人

	private String wbContactsTel;// 维保联系人电话

	private String param1;// 备用字段1

	private String param2;// 备用字段2

	private String param3;// 备用字段3

	private String param4;// 备用字段4

	private String param5;// 备用字段5

	private String createUserId;// 创建人id

	private String createUserName;// 创建人姓名

	private java.util.Date createDate;// 创建时间

	private String lastModifyUserId;// 最后修改人id

	private String lastModifyUserName;// 最后修改人姓名

	private java.util.Date lastModifyDate;// 最后修时间

	private String dataStatus;// 数据状态

	public void setId(String value) {
		this.id = value;
	}

	public void setFkCarId(String value) {
		this.fkCarId = value;
	}

	public void setCarNum(String value) {
		this.carNum = value;
	}

	public void setFkWbId(String value) {
		this.fkWbId = value;
	}

	public void setWbSn(String value) {
		this.wbSn = value;
	}

	public void setWbComName(String value) {
		this.wbComName = value;
	}

	public void setWbContacts(String value) {
		this.wbContacts = value;
	}

	public void setWbContactsTel(String value) {
		this.wbContactsTel = value;
	}

	public void setParam1(String value) {
		this.param1 = value;
	}

	public void setParam2(String value) {
		this.param2 = value;
	}

	public void setParam3(String value) {
		this.param3 = value;
	}

	public void setParam4(String value) {
		this.param4 = value;
	}

	public void setParam5(String value) {
		this.param5 = value;
	}

	public void setCreateUserId(String value) {
		this.createUserId = value;
	}

	public void setCreateUserName(String value) {
		this.createUserName = value;
	}

	public void setCreateDate(java.util.Date value) {
		this.createDate = value;
	}

	public void setLastModifyUserId(String value) {
		this.lastModifyUserId = value;
	}

	public void setLastModifyUserName(String value) {
		this.lastModifyUserName = value;
	}

	public void setLastModifyDate(java.util.Date value) {
		this.lastModifyDate = value;
	}

	public void setDataStatus(String value) {
		this.dataStatus = value;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	public String getId() {
		return this.id;
	}

	@Column(name = "CAR_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getCarNum() {
		return this.carNum;
	}

	@Column(name = "FK_CAR_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getFkCarId() {
		return this.fkCarId;
	}

	@Column(name = "FK_WB_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getFkWbId() {
		return this.fkWbId;
	}

	@Column(name = "WB_SN", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getWbSn() {
		return this.wbSn;
	}

	@Column(name = "WB_COM_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getWbComName() {
		return this.wbComName;
	}

	@Column(name = "WB_CONTACTS", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getWbContacts() {
		return this.wbContacts;
	}

	@Column(name = "WB_CONTACTS_TEL", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getWbContactsTel() {
		return this.wbContactsTel;
	}

	@Column(name = "PARAM_1", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getParam1() {
		return this.param1;
	}

	@Column(name = "PARAM_2", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getParam2() {
		return this.param2;
	}

	@Column(name = "PARAM_3", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getParam3() {
		return this.param3;
	}

	@Column(name = "PARAM_4", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getParam4() {
		return this.param4;
	}

	@Column(name = "PARAM_5", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getParam5() {
		return this.param5;
	}

	@Column(name = "CREATE_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getCreateUserId() {
		return this.createUserId;
	}

	@Column(name = "CREATE_USER_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getCreateUserName() {
		return this.createUserName;
	}

	@Column(name = "CREATE_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public java.util.Date getCreateDate() {
		return this.createDate;
	}

	@Column(name = "LAST_MODIFY_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getLastModifyUserId() {
		return this.lastModifyUserId;
	}

	@Column(name = "LAST_MODIFY_USER_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getLastModifyUserName() {
		return this.lastModifyUserName;
	}

	@Column(name = "LAST_MODIFY_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public java.util.Date getLastModifyDate() {
		return this.lastModifyDate;
	}

	@Column(name = "DATA_STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getDataStatus() {
		return this.dataStatus;
	}

	private List<CarWbRecordItem> carWbRecordItems;// 维保记录明细

	@Transient
	public List<CarWbRecordItem> getCarWbRecordItems() {
		return this.carWbRecordItems;
	}

	public void setCarWbRecordItems(List<CarWbRecordItem> value) {
		this.carWbRecordItems = value;
	}

}
