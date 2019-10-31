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

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "FOOD_EVALUATION")
public class FoodEvaluation implements BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String id;
	private String pubm_id;//菜品发布id
	private String emp_id;
	private String eval_man;//
	private String content;//评价内容
	private Date eval_time;//评价内容
	private int grade;//评价等级
	private String expect_food;//期望菜单
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPubm_id() {
		return pubm_id;
	}
	public void setPubm_id(String pubm_id) {
		this.pubm_id = pubm_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getEval_man() {
		return eval_man;
	}
	public void setEval_man(String eval_man) {
		this.eval_man = eval_man;
	}
	
	
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	
	public Date getEval_time() {
		return eval_time;
	}
	public void setEval_time(Date eval_time) {
		this.eval_time = eval_time;
	}
	
	
	public String getExpect_food() {
		return expect_food;
	}
	public void setExpect_food(String expect_food) {
		this.expect_food = expect_food;
	}
	public Map to_Map() {
		Map m = new HashMap();
		m.put("id", id);
		m.put("emp_id", emp_id);
		m.put("pubm_id", pubm_id);
		m.put("eval_man", eval_man);
		m.put("content", content);
		m.put("grade", grade);
		m.put("eval_time", eval_time);
		m.put("expect_food", expect_food);
		return m;
	}
	
	
}
