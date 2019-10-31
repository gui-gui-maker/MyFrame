package com.lsts.relevant.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 特种作业人员持证情况
 * RelevantPeopleCert entity. 
 * @author GaoYa
 * @date 2014-02-13 16:40:00
 */
@Entity
@Table(name = "TZSB_RELEVANT_PEOPLE_CERT")
@JsonIgnoreProperties("relevantPeople")
public class RelevantPeopleCert implements BaseEntity{

	private static final long serialVersionUID = 1L;
	private String id;	// ID	
	//private String fk_people_id;	// 人员信息ID
	private String cert_type;	// 证书类型（码表：BASE_RELEVANT_LETTER）
	private String cert_no;	// 证书编号
	private String cert_authority;	// 发证机构
	private Date cert_begin_date;	// 发证日期
	private Date cert_end_date;	// 证书有效截止日期
	private String cert_level;	// 证书级别
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
	/*public String getFk_people_id() {
		return fk_people_id;
	}
	public void setFk_people_id(String fk_people_id) {
		this.fk_people_id = fk_people_id;
	}*/
	public RelevantPeople relevantPeople;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_people_id")
	public RelevantPeople getRelevantPeople() {
		return relevantPeople;
	}
	public void setRelevantPeople(RelevantPeople relevantPeople) {
		this.relevantPeople = relevantPeople;
	}
	public String getCert_type() {
		return cert_type;
	}
	public void setCert_type(String cert_type) {
		this.cert_type = cert_type;
	}
	public String getCert_no() {
		return cert_no;
	}
	public void setCert_no(String cert_no) {
		this.cert_no = cert_no;
	}
	public String getCert_authority() {
		return cert_authority;
	}
	public void setCert_authority(String cert_authority) {
		this.cert_authority = cert_authority;
	}
	public String getCert_level() {
		return cert_level;
	}
	public void setCert_level(String cert_level) {
		this.cert_level = cert_level;
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
	public Date getCert_begin_date() {
		return cert_begin_date;
	}
	public void setCert_begin_date(Date cert_begin_date) {
		this.cert_begin_date = cert_begin_date;
	}
	public Date getCert_end_date() {
		return cert_end_date;
	}
	public void setCert_end_date(Date cert_end_date) {
		this.cert_end_date = cert_end_date;
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
	
	@SuppressWarnings("unchecked")
	public Map to_Map() {
        Map map = new HashMap();
        map.put("id", id);
        map.put("fk_people_id", relevantPeople.getId());
        map.put("cert_type", cert_type);
        map.put("cert_no", cert_no);
        map.put("cert_authority", cert_authority);
        map.put("cert_begin_date", cert_begin_date);
        map.put("cert_end_date", cert_end_date);
        map.put("cert_level", cert_level);
        map.put("created_by", created_by);
        map.put("created_date", created_date);
        map.put("last_upd_by", last_upd_by);
        map.put("last_upd_date", last_upd_date);
        return map;
    }
}
