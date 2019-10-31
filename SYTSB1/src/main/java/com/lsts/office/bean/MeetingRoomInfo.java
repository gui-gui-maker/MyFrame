package com.lsts.office.bean;

import com.khnt.core.crud.bean.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/*******************************************************************************
 * 
 * <p>
 *  
 * </p>
 * 
 * @ClassName CarInfo
 * @JDK 1.5
 * @author 
 * @date 2
 */
@Entity
@Table(name = "TJY2_OFFICE_MEETING_ROOM")
public class MeetingRoomInfo implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;//
	private String code;//会议室编号
	private String place;//所在地
	private String facilityOption;//会议室设施
	private String facility;//会议室其它设施
	private String accommodate_no;//可容纳人数
	private String unit_id;//所属单位id
	private String unit_name;//所属单位
	private String manager_id;//管理人员id
	private String manager_name;//管理人员名称
	private String manager_phone;//联系电话
	private String creator_id;//创建人id
	private String creator_name;//创建人名称
	private Date create_time;//创建时间
	private String creator_unit_id;//创建人所在单位id
	private String creator_unit_name;//创建人所在单位
	private String roompic_id;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	@Column(name="CODE")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name="PLACE")
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@Column(name="FACILITY_OPTION")
	public String getFacilityOption() {
		return facilityOption;
	}

	public void setFacilityOption(String facilityOption) {
		this.facilityOption = facilityOption;
	}

	@Column(name="FACILITY")
	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	@Column(name="ACCOMMODATE_NO")
	public String getAccommodate_no() {
		return accommodate_no;
	}

	public void setAccommodate_no(String accommodate_no) {
		this.accommodate_no = accommodate_no;
	}
	
	@Column(name="UNIT_ID")
	public String getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}

	@Column(name="UNIT_NAME")
	public String getUnit_name() {
		return unit_name;
	}

	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}

	@Column(name="MANAGER_ID")
	public String getManager_id() {
		return manager_id;
	}

	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}

	@Column(name="MANAGER_NAME")
	public String getManager_name() {
		return manager_name;
	}

	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}

	@Column(name="MANAGER_PHONE")
	public String getManager_phone() {
		return manager_phone;
	}

	public void setManager_phone(String manager_phone) {
		this.manager_phone = manager_phone;
	}

	@Column(name="CREATE_TIME")
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	@Column(name="CREATOR_UNIT_ID")
	public String getCreator_unit_id() {
		return creator_unit_id;
	}

	public void setCreator_unit_id(String creator_unit_id) {
		this.creator_unit_id = creator_unit_id;
	}

	@Column(name="CREATOR_UNIT_NAME")
	public String getCreator_unit_name() {
		return creator_unit_name;
	}

	public void setCreator_unit_name(String creator_unit_name) {
		this.creator_unit_name = creator_unit_name;
	}
	
	@Column(name="CREATOR_ID")
	public String getCreator_id() {
		return creator_id;
	}

	public void setCreator_id(String creator_id) {
		this.creator_id = creator_id;
	}

	@Column(name="CREATOR_NAME")
	public String getCreator_name() {
		return creator_name;
	}

	public void setCreator_name(String creator_name) {
		this.creator_name = creator_name;
	}

	@Column(name="ROOMPIC_ID")
	public String getRoompic_id() {
		return roompic_id;
	}

	public void setRoompic_id(String roompic_id) {
		this.roompic_id = roompic_id;
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
