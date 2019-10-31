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
@Table(name = "TJY2_CAR_WB")
public class CarWb implements BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;// 主键id

	private String sn;// 维保申请编号

	private String fkCarId;// 维保车辆id

	private String carUnit;// 车属单位

	private String carNum;// 车牌号

	private String carBrand;// 品牌型号

	private String engineNo;// 发动机号

	private String frameNo;// 车辆识别代码
	
	private String carMileage;// 车辆行驶里程

	private String applyUserId;// 送修人id

	private String applyUserName;// 送修人姓名

	private java.util.Date applyDate;// 送修时间

	private String contents;// 维保项目

	private String fleetDealId;// 车队负责人id

	private String fleetDealName;// 车队负责人姓名

	private String fleetDealResult;// 车队负责人审核结果

	private String fleetDealRemark;// 车队负责人审核结果备注

	private java.util.Date fleetDealDate;// 车队负责人审核时间

	private String officeDealId;// 办公室负责人id

	private String officeDealName;// 办公室负责人姓名

	private String officeDealResult;// 办公室负责人审核结果

	private String officeDealRemark;// 办公室负责人审核结果备注

	private java.util.Date officeDealDate;// 办公室负责人审核时间

	private String sealUserId;// 盖章人id

	private String sealUserName;// 盖章人姓名

	private java.util.Date sealData;// 盖章时间

	private String sealId;// 印章id

	private String createUserId;// 创建人id

	private String createUserName;// 创建人姓名

	private java.util.Date createDate;// 创建时间

	private String lastModifyUserId;// 最后修改人id

	private String lastModifyUserName;// 最后修改人姓名

	private java.util.Date lastModifyDate;// 最后修改时间

	private String dataStatus;// 数据状态

	public void setId(String value) {
		this.id = value;
	}

	public void setSn(String value) {
		this.sn = value;
	}

	public void setFkCarId(String value) {
		this.fkCarId = value;
	}

	public void setCarUnit(String value) {
		this.carUnit = value;
	}

	public void setCarNum(String value) {
		this.carNum = value;
	}

	public void setCarBrand(String value) {
		this.carBrand = value;
	}

	public void setEngineNo(String value) {
		this.engineNo = value;
	}

	public void setCarMileage(String value) {
		this.carMileage = value;
	}
	
	public void setFrameNo(String value) {
		this.frameNo = value;
	}

	public void setApplyUserId(String value) {
		this.applyUserId = value;
	}

	public void setApplyUserName(String value) {
		this.applyUserName = value;
	}

	public void setApplyDate(java.util.Date value) {
		this.applyDate = value;
	}

	public void setContents(String value) {
		this.contents = value;
	}

	public void setFleetDealId(String value) {
		this.fleetDealId = value;
	}

	public void setFleetDealName(String value) {
		this.fleetDealName = value;
	}

	public void setFleetDealResult(String value) {
		this.fleetDealResult = value;
	}

	public void setFleetDealRemark(String value) {
		this.fleetDealRemark = value;
	}

	public void setFleetDealDate(java.util.Date value) {
		this.fleetDealDate = value;
	}

	public void setOfficeDealId(String value) {
		this.officeDealId = value;
	}

	public void setOfficeDealName(String value) {
		this.officeDealName = value;
	}

	public void setOfficeDealResult(String value) {
		this.officeDealResult = value;
	}

	public void setOfficeDealRemark(String value) {
		this.officeDealRemark = value;
	}

	public void setOfficeDealDate(java.util.Date value) {
		this.officeDealDate = value;
	}

	public void setSealUserId(String value) {
		this.sealUserId = value;
	}

	public void setSealUserName(String value) {
		this.sealUserName = value;
	}

	public void setSealData(java.util.Date value) {
		this.sealData = value;
	}

	public void setSealId(String value) {
		this.sealId = value;
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

	@Column(name = "SN", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	public String getSn() {
		return this.sn;
	}

	@Column(name = "FK_CAR_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getFkCarId() {
		return this.fkCarId;
	}

	@Column(name = "CAR_UNIT", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getCarUnit() {
		return this.carUnit;
	}

	@Column(name = "CAR_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getCarNum() {
		return this.carNum;
	}

	@Column(name = "CAR_BRAND", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getCarBrand() {
		return this.carBrand;
	}

	@Column(name = "ENGINE_NO", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getEngineNo() {
		return this.engineNo;
	}

	@Column(name = "FRAME_NO", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getFrameNo() {
		return this.frameNo;
	}
	
	@Column(name = "CAR_MILEAGE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getCarMileage() {
		return this.carMileage;
	}

	@Column(name = "APPLY_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getApplyUserId() {
		return this.applyUserId;
	}

	@Column(name = "APPLY_USER_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getApplyUserName() {
		return this.applyUserName;
	}

	@Column(name = "APPLY_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public java.util.Date getApplyDate() {
		return this.applyDate;
	}

	@Column(name = "CONTENTS", unique = false, nullable = true, insertable = true, updatable = true, length = 4000)
	public String getContents() {
		return this.contents;
	}

	@Column(name = "FLEET_DEAL_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getFleetDealId() {
		return this.fleetDealId;
	}

	@Column(name = "FLEET_DEAL_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getFleetDealName() {
		return this.fleetDealName;
	}

	@Column(name = "FLEET_DEAL_RESULT", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getFleetDealResult() {
		return this.fleetDealResult;
	}

	@Column(name = "FLEET_DEAL_REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public String getFleetDealRemark() {
		return this.fleetDealRemark;
	}

	@Column(name = "FLEET_DEAL_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public java.util.Date getFleetDealDate() {
		return this.fleetDealDate;
	}

	@Column(name = "OFFICE_DEAL_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getOfficeDealId() {
		return this.officeDealId;
	}

	@Column(name = "OFFICE_DEAL_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getOfficeDealName() {
		return this.officeDealName;
	}

	@Column(name = "OFFICE_DEAL_RESULT", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getOfficeDealResult() {
		return this.officeDealResult;
	}

	@Column(name = "OFFICE_DEAL_REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public String getOfficeDealRemark() {
		return this.officeDealRemark;
	}

	@Column(name = "OFFICE_DEAL_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public java.util.Date getOfficeDealDate() {
		return this.officeDealDate;
	}

	@Column(name = "SEAL_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getSealUserId() {
		return this.sealUserId;
	}

	@Column(name = "SEAL_USER_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getSealUserName() {
		return this.sealUserName;
	}

	@Column(name = "SEAL_DATA", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public java.util.Date getSealData() {
		return this.sealData;
	}

	@Column(name = "SEAL_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getSealId() {
		return this.sealId;
	}

	@Column(name = "CREATE_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getCreateUserId() {
		return this.createUserId;
	}

	@Column(name = "CREATE_USER_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
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

	@Column(name = "LAST_MODIFY_USER_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
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

}
