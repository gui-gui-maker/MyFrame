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
@Table(name = "FOOD_ORDER")
public class FoodOrder implements BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int OK = 1;
	public static final int PREDETERMINE = 0;
	public static final int REMOVED = -1;
	
	private String id;
	private String card_no;//一卡通
	private String emp_id;//员工id
	private String order_man;//订餐人
	private Date gettime;//领餐时间，刷卡时间
	private String fpo_id;//用餐时间
	private Date use_time;
	private String meal_name;
	private int quantum;//用餐时间段
	private Date preparedTime;//备餐时间
	private int prepareNumber;//备餐编号
	private String fpm_ids;//菜单
	private String fpm_names;//菜单名称
	private Date order_date;//订餐时间
	private int count;//份数
	private int wdw; //几号窗口
	private int grade;//优先级别
	private String remark;//备注
	private int ostatus = 0;//状态
	private String isSigned="no";//是否已签到；yes/no
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	
	
	public Date getGettime() {
		return gettime;
	}
	public void setGettime(Date gettime) {
		this.gettime = gettime;
	}

	public Date getPreparedTime() {
		return preparedTime;
	}
	public void setPreparedTime(Date preparedTime) {
		this.preparedTime = preparedTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getFpo_id() {
		return fpo_id;
	}
	public void setFpo_id(String fpo_id) {
		this.fpo_id = fpo_id;
	}

	public int getQuantum() {
		return quantum;
	}
	public void setQuantum(int quantum) {
		this.quantum = quantum;
	}
	public String getFpm_ids() {
		return fpm_ids;
	}
	public void setFpm_ids(String fpm_ids) {
		this.fpm_ids = fpm_ids;
	}
	
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	
	public String getFpm_names() {
		return fpm_names;
	}
	public void setFpm_names(String fpm_names) {
		this.fpm_names = fpm_names;
	}
	public String getOrder_man() {
		return order_man;
	}
	public void setOrder_man(String order_man) {
		this.order_man = order_man;
	}
	public int getOstatus() {
		return ostatus;
	}
	public void setOstatus(int ostatus) {
		this.ostatus = ostatus;
	}
	public String getCard_no() {
		return card_no;
	}
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getWdw() {
		return wdw;
	}
	public void setWdw(int wdw) {
		this.wdw = wdw;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getPrepareNumber() {
		return prepareNumber;
	}
	public void setPrepareNumber(int prepareNumber) {
		this.prepareNumber = prepareNumber;
	}
	public String getIsSigned() {
		return isSigned;
	}
	public void setIsSigned(String isSigned) {
		this.isSigned = isSigned;
	}
	public Date getUse_time() {
		return use_time;
	}
	public void setUse_time(Date use_time) {
		this.use_time = use_time;
	}
	public String getMeal_name() {
		return meal_name;
	}
	public void setMeal_name(String meal_name) {
		this.meal_name = meal_name;
	}
	
	
}
