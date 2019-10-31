package com.fwxm.dining.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.access.prepost.PreInvocationAuthorizationAdviceVoter;

import com.khnt.core.crud.bean.BaseEntity;
/**
 * 菜品发布
 * @author Administrator
 *
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "FOOD_PUBM")
public class FoodPubm implements BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String fpo_id;//就餐时间id
	private String food_name;//菜品id
	private String food;//菜品名称
	private String pic_path;//菜品图片
	private String pub_man;//发布人
	private Date pub_time;//发布时间
	private int status=1;//发布状态
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFpo_id() {
		return fpo_id;
	}
	public void setFpo_id(String fpo_id) {
		this.fpo_id = fpo_id;
	}
	public String getFood_name() {
		return food_name;
	}
	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}
	public String getPub_man() {
		return pub_man;
	}
	public void setPub_man(String pub_man) {
		this.pub_man = pub_man;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getPub_time() {
		return pub_time;
	}
	public void setPub_time(Date pub_time) {
		this.pub_time = pub_time;
	}
	
	public String getPic_path() {
		return pic_path;
	}
	public void setPic_path(String pic_path) {
		this.pic_path = pic_path;
	}
	
	public String getFood() {
		return food;
	}
	public void setFood(String food) {
		this.food = food;
	}
	public Map<String,Object> to_Map() {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id", id);
        map.put("fpo_id", fpo_id);
        map.put("food_name", food_name);
        map.put("pub_man", pub_man);
        map.put("pub_time", pub_time);
        map.put("status", status);
        map.put("food", food);
        return map;
    }
	
}
