package com.lsts.equipment2.bean;
import java.util.Date;

import com.khnt.core.crud.bean.BaseEntity;
/**
 * 设备出入库记录信息
 * BaseEquipment entity. 
 * 
 */
public class InoutRecord implements BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private String inoutClass;	// 出入库类别
	private String inoutType;	// 出入库类型
	private String relatedName;	// 相关人员
	private String inoutDate;	// 出入库日期
	public String getInoutClass() {
		return inoutClass;
	}
	public void setInoutClass(String inoutClass) {
		this.inoutClass = inoutClass;
	}
	public String getInoutType() {
		return inoutType;
	}
	public void setInoutType(String inoutType) {
		this.inoutType = inoutType;
	}
	public String getRelatedName() {
		return relatedName;
	}
	public void setRelatedName(String relatedName) {
		this.relatedName = relatedName;
	}
	public String getInoutDate() {
		return inoutDate;
	}
	public void setInoutDate(String inoutDate) {
		this.inoutDate = inoutDate;
	}
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setId(String arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
