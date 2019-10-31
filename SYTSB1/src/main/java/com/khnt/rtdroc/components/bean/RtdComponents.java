package com.khnt.rtdroc.components.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import com.khnt.core.crud.bean.BaseEntity;

/*******************************************************************************
 * 
 * <p>
 * 
 * </p>
 * 
 * @ClassName RtdComponents
 * @JDK 1.5
 * @author CODER_V3.0
 * @date 2018-09-01 09:51:04
 */
@Entity
@Table(name = "RTD_COMPONENTS")
public class RtdComponents implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;//
	private String componentName;// 组件名
	private String fkAttIdDraw;// 绘制ID
	private String fkAttIdImg;// 图片ID
	private String componentType;// 组件类型(电梯、锅炉)
	private String info;// 描述
	private Date handleTime;// 操作时间
	private Date enableTime;// 最近启用时间
	private String status;// 状态(enable,disable)
	private String isDel;// 是否删除(是：1、否：0)

	// 页面传值
	private String drawData;// 绘制数据
	private String imgData;// 图片数据

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "COMPONENT_NAME")
	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	@Column(name = "FK_ATT_ID_DRAW")
	public String getFkAttIdDraw() {
		return fkAttIdDraw;
	}

	public void setFkAttIdDraw(String fkAttIdDraw) {
		this.fkAttIdDraw = fkAttIdDraw;
	}

	@Column(name = "FK_ATT_ID_IMG")
	public String getFkAttIdImg() {
		return fkAttIdImg;
	}

	public void setFkAttIdImg(String fkAttIdImg) {
		this.fkAttIdImg = fkAttIdImg;
	}

	@Column(name = "COMPONENT_TYPE")
	public String getComponentType() {
		return componentType;
	}

	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}

	@Column(name = "INFO")
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Column(name = "HANDLE_TIME")
	public Date getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}

	@Column(name = "ENABLE_TIME")
	public Date getEnableTime() {
		return enableTime;
	}

	public void setEnableTime(Date enableTime) {
		this.enableTime = enableTime;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "IS_DEL")
	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	@Transient
	public String getImgData() {
		return imgData;
	}

	public void setImgData(String imgData) {
		this.imgData = imgData;
	}

	@Transient
	public String getDrawData() {
		return drawData;
	}

	public void setDrawData(String drawData) {
		this.drawData = drawData;
	}

	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "RTD_COMPONENTS:ID=" + id;

	}
}
