package com.khnt.signseal.bean;

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
@Table(name = "COMMON_SIGNSEAL")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignSeal implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;//
	private String businessId;// 业务ID
	private Float num;// 数量
	private String top;// 相对位置
	private String left;// 位置
	private String isAuto;// 是否自动加盖
	private String sealdata;// 印章数据
	private Date time;// 添加时间
	private String creator;// 添加人
	private String types;// 分类，一个页面多个印章时候使用
	private String isSign;// 是否是签章
	private String protectData;// 保护数据
	private String width;// 宽度
	private String height;// 高度

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "BUSINESS_ID")
	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	@Column(name = "NUM")
	public Float getNum() {
		return num;
	}

	public void setNum(Float num) {
		this.num = num;
	}

	@Column(name = "TOP")
	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	@Column(name = "LEFT")
	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	@Column(name = "IS_AUTO")
	public String getIsAuto() {
		return isAuto;
	}

	public void setIsAuto(String isAuto) {
		this.isAuto = isAuto;
	}

	@Column(name = "SEALDATA")
	public String getSealdata() {
		return sealdata;
	}

	public void setSealdata(String sealdata) {
		this.sealdata = sealdata;
	}

	@Column(name = "TIME")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "CREATOR")
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Column(name = "TYPES")
	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	@Column(name = "IS_SIGN")
	public String getIsSign() {
		return isSign;
	}

	public void setIsSign(String isSign) {
		this.isSign = isSign;
	}

	@Column(name = "PROTECTDATA")
	public String getProtectData() {
		return protectData;
	}

	public void setProtectData(String protectData) {
		this.protectData = protectData;
	}

	@Column(name = "WIDTH")
	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	@Column(name = "HEIGHT")
	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "未说明";

	}
}
