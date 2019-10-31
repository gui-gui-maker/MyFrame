package com.fwxm.dining.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;
/**
 * 菜单发布者根据时间发布菜单
 * @author zgz
 *
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "FOOD_PUBO")
public class FoodPubo implements BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private int meal_name;//早中晚餐
	private Date use_time;//就餐日期
	private String mark;//标识唯一，拼接use_date+meal_type
	private int pub_status = 0;
	private String pub_man;
	private Date pub_time;
	private List<Food> foods = new ArrayList<Food>();
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getMeal_name() {
		return meal_name;
	}
	public void setMeal_name(int meal_name) {
		this.meal_name = meal_name;
	}

	public Date getUse_time() {
		return use_time;
	}
	public void setUse_time(Date use_time) {
		this.use_time = use_time;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public int getPub_status() {
		return pub_status;
	}
	public void setPub_status(int pub_status) {
		this.pub_status = pub_status;
	}
	@Transient
	public List<Food> getFoods() {
		return foods;
	}
	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}
	public String getPub_man() {
		return pub_man;
	}
	public void setPub_man(String pub_man) {
		this.pub_man = pub_man;
	}
	public Date getPub_time() {
		return pub_time;
	}
	public void setPub_time(Date pub_time) {
		this.pub_time = pub_time;
	}
	
	
	
	
}
