package com.fwxm.scientific.bean;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

import java.util.Date;
import java.util.List;

/*******************************************************************************
 * 
 * <p>
 *  
 * </p>
 * 
 * @ClassName InstrumentDevice
 * @JDK 1.7
 * @author CODER_V3.0
 * @date 2018-01-18 15:20:10
 */
@Entity
@Table(name = "TJY2_INSTRUMENT_DEVICE")
public class InstrumentDevice implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private String id;//
	private String headMan;//负责人
	private String headId;//负责人id
	private String departmentName;//部门名称
	private String departmentId;//部门id
	private Date createDate;//填报时间
	private String createId;//创建人id
	private String createMan;//创建人
	private String status;//状态
	private String audit_man;//审核人
	private Date audit_date;//审核时间
	private String remark;//审核意见
	private List<InstrumentDeviceInfo> instrumentDeviceInfo;
	
	
	 @OneToMany(cascade=CascadeType.ALL,mappedBy="instrumentDevice")
	 public List<InstrumentDeviceInfo> getInstrumentDeviceInfo() {
			return instrumentDeviceInfo;
		}
		public void setInstrumentDeviceInfo(
				List<InstrumentDeviceInfo> instrumentDeviceInfo) {
			this.instrumentDeviceInfo = instrumentDeviceInfo;
		}
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	@Column(name="HEAD_MAN")
	public String getHeadMan(){
		return headMan;
	}
		
	public void setHeadMan(String headMan){
		this.headMan=headMan;
	}

	@Column(name="HEAD_ID")
	public String getHeadId(){
		return headId;
	}
		
	public void setHeadId(String headId){
		this.headId=headId;
	}

	@Column(name="DEPARTMENT_NAME")
	public String getDepartmentName(){
		return departmentName;
	}
		
	public void setDepartmentName(String departmentName){
		this.departmentName=departmentName;
	}

	@Column(name="DEPARTMENT_ID")
	public String getDepartmentId(){
		return departmentId;
	}
		
	public void setDepartmentId(String departmentId){
		this.departmentId=departmentId;
	}

	@Column(name="CREATE_DATE")
	public Date getCreateDate(){
		return createDate;
	}
		
	public void setCreateDate(Date createDate){
		this.createDate=createDate;
	}

	@Column(name="CREATE_ID")
	public String getCreateId(){
		return createId;
	}
		
	public void setCreateId(String createId){
		this.createId=createId;
	}

	@Column(name="CREATE_MAN")
	public String getCreateMan(){
		return createMan;
	}
		
	public void setCreateMan(String createMan){
		this.createMan=createMan;
	}

	@Column(name="STATUS")
	public String getStatus(){
		return status;
	}
		
	public void setStatus(String status){
		this.status=status;
	}

	
	
	
	public String getAudit_man() {
		return audit_man;
	}
	public void setAudit_man(String audit_man) {
		this.audit_man = audit_man;
	}
	public Date getAudit_date() {
		return audit_date;
	}
	public void setAudit_date(Date audit_date) {
		this.audit_date = audit_date;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "TJY2_INSTRUMENT_DEVICE:ID="+id;

	}
}
