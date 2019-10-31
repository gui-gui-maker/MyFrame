package com.scts.car.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import com.khnt.core.crud.bean.BaseEntity;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "TJY2_CAR_WB_RECORD_ITEM")
public class CarWbRecordItem implements BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;// 主键id

	private String fkWbRecordId;// 维保记录id

	private String fkCarId;// 车辆id

	private String itemName;// 维保项目

	private String itemType;// 维保项目类型

	private String isRenew;// 是否换零部件

	private String itemValue;// 维保金额

	private String dataStatus;// 数据状态

	public void setId(String value) {
		this.id = value;
	}

	public void setFkWbRecordId(String value) {
		this.fkWbRecordId = value;
	}

	public void setFkCarId(String value) {
		this.fkCarId = value;
	}

	public void setItemName(String value) {
		this.itemName = value;
	}

	public void setItemType(String value) {
		this.itemType = value;
	}

	public void setIsRenew(String value) {
		this.isRenew = value;
	}

	public void setItemValue(String value) {
		this.itemValue = value;
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

	@Column(name = "FK_WB_RECORD_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getFkWbRecordId() {
		return this.fkWbRecordId;
	}

	@Column(name = "FK_CAR_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getFkCarId() {
		return this.fkCarId;
	}

	@Column(name = "ITEM_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 4000)
	public String getItemName() {
		return this.itemName;
	}

	@Column(name = "ITEM_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getItemType() {
		return this.itemType;
	}

	@Column(name = "IS_RENEW", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getIsRenew() {
		return this.isRenew;
	}

	@Column(name = "ITEM_VALUE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getItemValue() {
		return this.itemValue;
	}

	@Column(name = "DATA_STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getDataStatus() {
		return this.dataStatus;
	}

}
