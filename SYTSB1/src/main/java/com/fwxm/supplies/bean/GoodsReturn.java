package com.fwxm.supplies.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.fwxm.material.bean.GoodsAndOrder;
import com.khnt.core.crud.bean.BaseEntity;

/**
 * 物资退货
 * @author Administrator
 *
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "TJY2_CH_GOODSRETURN")
public class GoodsReturn implements BaseEntity {
	private static final long serialVersionUID = 1L;
	private String id;
	private String rkdbh;//退货的入库单编号
	private String thgys;//退货供应商
	private String thgys_id;//退货供应商ID
	private Date thtime;//退货时间
	private String jsr;//接收人
	private String tel;//接收人电话
	private String thbh;//退货单编号
	private String state;//状态：1:有效；99删除
	private String create_user_name;
	private String create_user_Id;
	private String create_org_name;
	private String create_org_id;
	private Date create_time;
	private List<GoodsAndOrder> goodsAndOrder;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@OneToMany(cascade={CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_order_id",insertable = false, updatable = false)
	public List<GoodsAndOrder> getGoodsAndOrder() {
		return goodsAndOrder;
	}
	public void setGoodsAndOrder(List<GoodsAndOrder> goodsAndOrder) {
		this.goodsAndOrder = goodsAndOrder;
	}

	@Column(name="THGYS")
	public String getThgys() {
		return thgys;
	}
	public void setThgys(String thgys) {
		this.thgys = thgys;
	}
	
	
	@Column(name="THTIME")
	public Date getThtime() {
		return thtime;
	}
	public void setThtime(Date thtime) {
		this.thtime = thtime;
	}
	@Column(name="JSR")
	public String getJsr() {
		return jsr;
	}
	public void setJsr(String jsr) {
		this.jsr = jsr;
	}
	@Column(name="TEL")
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Column(name="CREATE_USER_NAME")
	public String getCreate_user_name() {
		return create_user_name;
	}
	public void setCreate_user_name(String create_user_name) {
		this.create_user_name = create_user_name;
	}
	@Column(name="CREATE_USER_ID")
	public String getCreate_user_Id() {
		return create_user_Id;
	}
	public void setCreate_user_Id(String create_user_Id) {
		this.create_user_Id = create_user_Id;
	}
	@Column(name="CREATE_ORG_NAME")
	public String getCreate_org_name() {
		return create_org_name;
	}
	public void setCreate_org_name(String create_org_name) {
		this.create_org_name = create_org_name;
	}
	@Column(name="CREATE_ORG_ID")
	public String getCreate_org_id() {
		return create_org_id;
	}
	public void setCreate_org_id(String create_org_id) {
		this.create_org_id = create_org_id;
	}
	@Column(name="CREATE_TIME")
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	@Column(name="THBH")
	public String getThbh() {
		return thbh;
	}
	public void setThbh(String thbh) {
		this.thbh = thbh;
	}
	
	@Column(name="STATE")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Column(name="THGYS_ID")
	public String getThgys_id() {
		return thgys_id;
	}
	public void setThgys_id(String thgys_id) {
		this.thgys_id = thgys_id;
	}
	@Column(name="RKDBH")
	public String getRkdbh() {
		return rkdbh;
	}
	public void setRkdbh(String rkdbh) {
		this.rkdbh = rkdbh;
	}
	
	
	
	
}
