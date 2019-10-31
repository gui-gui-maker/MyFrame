package com.khnt.rbac.impl.bean;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "EMPLOYEE")
@JsonIgnoreProperties({ "positions" })
public class Employee implements BaseEntity {
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String name;
	private String gender;
	private String education;
	private String degree;
	private String englishLevel;
	private String pcLevel;
	private String schoolName;
	private Date graduateTime;
	private String major;
	private String email;
	private String nation;
	private String nativePlace;
	private String birthPlace;
	private String homePlace;
	private String currentPlace;
	private Date hiredDate;
	private String idNo;
	private String posId;
	private String maritalStatus;
	private String postcode;
	private Date birthDate;
	private String polstatus;
	private String tel;
	private String mobileTel;
	private String homeTel;
	private String officeTel;
	private String isLeave;
	private String isDriver;
	private String comments;
	private Float operator;
	private Date opTime;
	private Date deleteTime;
	private Float deleteEmployee;
	private String picture;
	private String employeeState;
	private String eyelevel;
	private String bloodtype;
	private String bloodpressure;
	private String weight;
	private String height;
	private String health;
	private String faith;
	private Date joinComsomolTime;
	private Date joinCommyTime;
	private String joinCommyIntroName;
	private Date introJoinCommyTime;
	private String otherName;
	private String levelsalarylevel;
	private Float levelsalary;
	private Float dutysalary;
	private String dutysalarylevel;
	private String duty;
	private String evername;
	private String euserType;
	private String workType;
	private String note;
	private String employeeGov;
	private String position;
	private String educationbackground;
	private String bankAccount;
	private Org org;
	private Set<Position> positions; 	// 员工岗位
	
	private String positionNames;		// 岗位名称
	private String positionIds;			// 岗位ID
	private String isHead;                  //是否为部门负责人

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "CODE")
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "NAME")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "GENDER")
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "EDUCATION")
	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@Column(name = "DEGREE")
	public String getDegree() {
		return this.degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	@Column(name = "ENGLISH_LEVEL")
	public String getEnglishLevel() {
		return this.englishLevel;
	}

	public void setEnglishLevel(String englishLevel) {
		this.englishLevel = englishLevel;
	}

	@Column(name = "PC_LEVEL")
	public String getPcLevel() {
		return this.pcLevel;
	}

	public void setPcLevel(String pcLevel) {
		this.pcLevel = pcLevel;
	}

	@Column(name = "SCHOOL_NAME")
	public String getSchoolName() {
		return this.schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	@Column(name = "GRADUATE_TIME")
	public Date getGraduateTime() {
		return this.graduateTime;
	}

	public void setGraduateTime(Date graduateTime) {
		this.graduateTime = graduateTime;
	}

	@Column(name = "MAJOR")
	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "NATION")
	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	@Column(name = "NATIVE_PLACE")
	public String getNativePlace() {
		return this.nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	@Column(name = "BIRTH_PLACE")
	public String getBirthPlace() {
		return this.birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	@Column(name = "HOME_PLACE")
	public String getHomePlace() {
		return this.homePlace;
	}

	public void setHomePlace(String homePlace) {
		this.homePlace = homePlace;
	}

	@Column(name = "CURRENT_PLACE")
	public String getCurrentPlace() {
		return this.currentPlace;
	}

	public void setCurrentPlace(String currentPlace) {
		this.currentPlace = currentPlace;
	}

	@Column(name = "HIRED_DATE")
	public Date getHiredDate() {
		return this.hiredDate;
	}

	public void setHiredDate(Date hiredDate) {
		this.hiredDate = hiredDate;
	}

	@Column(name = "ID_NO")
	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	@Column(name = "POS_ID")
	public String getPosId() {
		return this.posId;
	}

	public void setPosId(String posId) {
		this.posId = posId;
	}

	@Column(name = "MARITAL_STATUS")
	public String getMaritalStatus() {
		return this.maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	@Column(name = "POSTCODE")
	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Column(name = "BIRTH_DATE")
	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Column(name = "POLSTATUS")
	public String getPolstatus() {
		return this.polstatus;
	}

	public void setPolstatus(String polstatus) {
		this.polstatus = polstatus;
	}

	@Column(name = "TEL")
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "MOBILE_TEL")
	public String getMobileTel() {
		return this.mobileTel;
	}

	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}

	@Column(name = "HOME_TEL")
	public String getHomeTel() {
		return this.homeTel;
	}

	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}

	@Column(name = "OFFICE_TEL")
	public String getOfficeTel() {
		return this.officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	@Column(name = "IS_LEAVE")
	public String getIsLeave() {
		return this.isLeave;
	}

	public void setIsLeave(String isLeave) {
		this.isLeave = isLeave;
	}

	@Column(name = "IS_DRIVER")
	public String getIsDriver() {
		return this.isDriver;
	}

	public void setIsDriver(String isDriver) {
		this.isDriver = isDriver;
	}

	@Column(name = "COMMENTS")
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "OPERATOR")
	public Float getOperator() {
		return this.operator;
	}

	public void setOperator(Float operator) {
		this.operator = operator;
	}

	@Column(name = "OP_TIME")
	public Date getOpTime() {
		return this.opTime;
	}

	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}

	@Column(name = "DELETE_TIME")
	public Date getDeleteTime() {
		return this.deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	@Column(name = "DELETE_EMPLOYEE")
	public Float getDeleteEmployee() {
		return this.deleteEmployee;
	}

	public void setDeleteEmployee(Float deleteEmployee) {
		this.deleteEmployee = deleteEmployee;
	}

	@Column(name = "PICTURE")
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Column(name = "EMPLOYEE_STATE")
	public String getEmployeeState() {
		return this.employeeState;
	}

	public void setEmployeeState(String employeeState) {
		this.employeeState = employeeState;
	}

	@Column(name = "EYELEVEL")
	public String getEyelevel() {
		return this.eyelevel;
	}

	public void setEyelevel(String eyelevel) {
		this.eyelevel = eyelevel;
	}

	@Column(name = "BLOODTYPE")
	public String getBloodtype() {
		return this.bloodtype;
	}

	public void setBloodtype(String bloodtype) {
		this.bloodtype = bloodtype;
	}

	@Column(name = "BLOODPRESSURE")
	public String getBloodpressure() {
		return this.bloodpressure;
	}

	public void setBloodpressure(String bloodpressure) {
		this.bloodpressure = bloodpressure;
	}

	@Column(name = "WEIGHT")
	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	@Column(name = "HEIGHT")
	public String getHeight() {
		return this.height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	@Column(name = "HEALTH")
	public String getHealth() {
		return this.health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	@Column(name = "FAITH")
	public String getFaith() {
		return this.faith;
	}

	public void setFaith(String faith) {
		this.faith = faith;
	}

	@Column(name = "JOIN_COMSOMOL_TIME")
	public Date getJoinComsomolTime() {
		return this.joinComsomolTime;
	}

	public void setJoinComsomolTime(Date joinComsomolTime) {
		this.joinComsomolTime = joinComsomolTime;
	}

	@Column(name = "JOIN_COMMY_TIME")
	public Date getJoinCommyTime() {
		return this.joinCommyTime;
	}

	public void setJoinCommyTime(Date joinCommyTime) {
		this.joinCommyTime = joinCommyTime;
	}

	@Column(name = "JOIN_COMMY_INTRO_NAME")
	public String getJoinCommyIntroName() {
		return this.joinCommyIntroName;
	}

	public void setJoinCommyIntroName(String joinCommyIntroName) {
		this.joinCommyIntroName = joinCommyIntroName;
	}

	@Column(name = "INTRO_JOIN_COMMY_TIME")
	public Date getIntroJoinCommyTime() {
		return this.introJoinCommyTime;
	}

	public void setIntroJoinCommyTime(Date introJoinCommyTime) {
		this.introJoinCommyTime = introJoinCommyTime;
	}

	@Column(name = "OTHER_NAME")
	public String getOtherName() {
		return this.otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	@Column(name = "LEVELSALARYLEVEL")
	public String getLevelsalarylevel() {
		return this.levelsalarylevel;
	}

	public void setLevelsalarylevel(String levelsalarylevel) {
		this.levelsalarylevel = levelsalarylevel;
	}

	@Column(name = "LEVELSALARY")
	public Float getLevelsalary() {
		return this.levelsalary;
	}

	public void setLevelsalary(Float levelsalary) {
		this.levelsalary = levelsalary;
	}

	@Column(name = "DUTYSALARY")
	public Float getDutysalary() {
		return this.dutysalary;
	}

	public void setDutysalary(Float dutysalary) {
		this.dutysalary = dutysalary;
	}

	@Column(name = "DUTYSALARYLEVEL")
	public String getDutysalarylevel() {
		return this.dutysalarylevel;
	}

	public void setDutysalarylevel(String dutysalarylevel) {
		this.dutysalarylevel = dutysalarylevel;
	}

	@Column(name = "DUTY")
	public String getDuty() {
		return this.duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	@Column(name = "EVERNAME")
	public String getEvername() {
		return this.evername;
	}

	public void setEvername(String evername) {
		this.evername = evername;
	}

	@Column(name = "EUSER_TYPE")
	public String getEuserType() {
		return this.euserType;
	}

	public void setEuserType(String euserType) {
		this.euserType = euserType;
	}

	@Column(name = "WORK_TYPE")
	public String getWorkType() {
		return this.workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	@Column(name = "NOTE")
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "EMPLOYEE_GOV")
	public String getEmployeeGov() {
		return this.employeeGov;
	}

	public void setEmployeeGov(String employeeGov) {
		this.employeeGov = employeeGov;
	}

	@Column(name = "POSITION")
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "EDUCATIONBACKGROUND")
	public String getEducationbackground() {
		return this.educationbackground;
	}

	public void setEducationbackground(String educationbackground) {
		this.educationbackground = educationbackground;
	}

	@ManyToOne
	@JoinColumn(name = "ORG_ID")
	public Org getOrg() {
		return this.org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	@Column(name = "BANK_ACCOUNT")
	public String getBankAccount() {
		return this.bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	@ManyToMany(targetEntity = Position.class, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "sys_employee_position", joinColumns = {
			@JoinColumn(name = "employee_id") }, inverseJoinColumns = { @JoinColumn(name = "position_id") })
	public Set<Position> getPositions() {
		return positions;
	}

	public void setPositions(Set<Position> positions) {
		this.positions = positions;
	}

	public String toString() {
		return "用户【" + this.name + "】基础信息";
	}

	@Transient
	public String getPositionNames() {
		return positionNames;
	}

	public void setPositionNames(String positionNames) {
		this.positionNames = positionNames;
	}

	@Transient
	public String getPositionIds() {
		return positionIds;
	}

	public void setPositionIds(String positionIds) {
		this.positionIds = positionIds;
	}
	@Column(name = "IS_HEAD")
	public String getIsHead() {
		return isHead;
	}

	public void setIsHead(String isHead) {
		this.isHead = isHead;
	}
	
	
}
