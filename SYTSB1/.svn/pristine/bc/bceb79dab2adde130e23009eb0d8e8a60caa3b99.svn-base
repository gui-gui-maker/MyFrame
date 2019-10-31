package com.fwxm.dining.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "FOOD_SEAT_ORDER")
public class SeatOrder implements BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String id;
	private String food_ids;//菜单
	private String food_names;//菜单名称
	private int use_num;//用餐人数
	private Date use_time;//用餐时间
	private Date order_time;//订餐时间
	private Date real_use_time;//订餐时间
	private String emp_id;//订餐人id
	private String order_man;//订餐人
	private String tel;//订餐人电话
	private int sostatus;//是否已使用
	private String  reviewed_msg;//审核信息
	private String review_man;
	private Date review_time;
	private String remark;//备注
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public Date getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}
	public String getFood_ids() {
		return food_ids;
	}
	public void setFood_ids(String food_ids) {
		this.food_ids = food_ids;
	}
	public String getFood_names() {
		return food_names;
	}
	public void setFood_names(String food_names) {
		this.food_names = food_names;
	}
	public int getUse_num() {
		return use_num;
	}
	public void setUse_num(int use_num) {
		this.use_num = use_num;
	}
	public Date getUse_time() {
		return use_time;
	}
	public void setUse_time(Date use_time) {
		this.use_time = use_time;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getOrder_man() {
		return order_man;
	}
	public void setOrder_man(String order_man) {
		this.order_man = order_man;
	}
	
	public int getSostatus() {
		return sostatus;
	}
	public void setSostatus(int sostatus) {
		this.sostatus = sostatus;
	}
	public String getReviewed_msg() {
		return reviewed_msg;
	}
	public void setReviewed_msg(String reviewed_msg) {
		this.reviewed_msg = reviewed_msg;
	}
	public String getReview_man() {
		return review_man;
	}
	public void setReview_man(String review_man) {
		this.review_man = review_man;
	}
	public Date getReview_time() {
		return review_time;
	}
	public void setReview_time(Date review_time) {
		this.review_time = review_time;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Date getReal_use_time() {
		return real_use_time;
	}
	public void setReal_use_time(Date real_use_time) {
		this.real_use_time = real_use_time;
	}
	
	
	
}
