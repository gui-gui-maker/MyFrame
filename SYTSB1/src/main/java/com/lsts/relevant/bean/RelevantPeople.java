package com.lsts.relevant.bean;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 特种作业人员基本信息
 * RelevantPeople entity. 
 * @author GaoYa
 * @date 2014-02-13 16:35:00
 */
@Entity
@Table(name = "TZSB_RELEVANT_PEOPLE")
@JsonIgnoreProperties("relevantPeopleCert")
public class RelevantPeople implements BaseEntity{

	private static final long serialVersionUID = 1L;
	private String id;	// ID
	private String people_name;	// 姓名
	private String id_card;	// 身份证号码
	private String people_type;	// 人员类别
	private String sex;	// 性别
	private String area_code;	// 行政区划
	private Date birth_date;	// 出生年月
	private String nationality;	// 民族
	private String work_com_id;	// 工作单位ID
	private String work_com_name;	// 工作单位名称
	private String tel;	// 联系电话
	private String fax;	// 传真
	private String email;	// 电子邮箱
	private String diploma;	// 学历
	private String graduate_school;	// 毕业院校
	private String professional;	// 所学专业
	private String jobs;	// 技术操作种类
	private String created_by;	// 创建人
	private Date created_date;	// 创建时间
	private String last_upd_by;	// 最后更新人
	private Date last_upd_date;	// 最后更新时间
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPeople_name() {
		return people_name;
	}
	public void setPeople_name(String people_name) {
		this.people_name = people_name;
	}
	public String getId_card() {
		return id_card;
	}
	public void setId_card(String id_card) {
		this.id_card = id_card;
	}
	public String getPeople_type() {
		return people_type;
	}
	public void setPeople_type(String people_type) {
		this.people_type = people_type;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getWork_com_id() {
		return work_com_id;
	}
	public void setWork_com_id(String work_com_id) {
		this.work_com_id = work_com_id;
	}
	public String getWork_com_name() {
		return work_com_name;
	}
	public void setWork_com_name(String work_com_name) {
		this.work_com_name = work_com_name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDiploma() {
		return diploma;
	}
	public void setDiploma(String diploma) {
		this.diploma = diploma;
	}
	public String getGraduate_school() {
		return graduate_school;
	}
	public void setGraduate_school(String graduate_school) {
		this.graduate_school = graduate_school;
	}
	public String getProfessional() {
		return professional;
	}
	public void setProfessional(String professional) {
		this.professional = professional;
	}
	public String getJobs() {
		return jobs;
	}
	public void setJobs(String jobs) {
		this.jobs = jobs;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getLast_upd_by() {
		return last_upd_by;
	}
	public void setLast_upd_by(String last_upd_by) {
		this.last_upd_by = last_upd_by;
	}
	public Date getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public Date getLast_upd_date() {
		return last_upd_date;
	}
	public void setLast_upd_date(Date last_upd_date) {
		this.last_upd_date = last_upd_date;
	}

	// 关联持证情况对象	
	public Collection<RelevantPeopleCert> relevantPeopleCert;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "relevantPeople", orphanRemoval = true)
	public Collection<RelevantPeopleCert> getRelevantPeopleCert() {
		return relevantPeopleCert;
	}

	public void setRelevantPeopleCert(Collection<RelevantPeopleCert> relevantPeopleCert) {
		this.relevantPeopleCert = relevantPeopleCert;
	}
	
}
