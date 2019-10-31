package com.scts.task.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 合同检验任务单
 * ContractTaskInfo entity. 
 * @author GaoYa
 * @date 2018-04-02 下午15:01:00
 */
@Entity
@Table(name = "CONTRACT_TASK_INFO")
public class ContractTaskInfo implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;					// ID
	private String sn;					// 检验任务单编号（格式为当前年份+“-”+序列号，例如2018-001）
	private String com_id;				// 报检单位ID
	private String com_name;			// 报检单位名称
	private Date   inspection_date;		// 报检日期
	private String check_type;			// 检验性质（数据字段：BASE_CHECK_TYPE）	
	private Double task_money;			// 检验任务单金额（单位：元）
	
										// 设备资料（设备类别及数量）
	private Integer device_rq_count;	// 压力容器数量
	private Integer device_gd_count;	// 压力管道数量
	private Integer device_gl_count;	// 锅炉数量
	private Integer device_qt_count;	// 其他数量
	
	private String sblx;//设备类型
	private String jylx;//检验类型
	private String cjry_id;//参检部门Id
	private String cjry_name;//参检部门
	private String sbsl;//设备数量
	private String cjsb_id;//参检设备
	private String task_money_s;//是否含税
	
	
	private Double js_money;//结算金额
	private Date cqsj;//超期时间
	private String xmmc;//项目名称
	
	
//	private String bz;//备注
	
	
	
	
	
	
										// 任务依据，合同协议
	private String contract_id;			// 合同ID
	private String contract_no;			// 合同编号
	private String org_id;				// 拟承办部门ID
	private String org_name;			// 拟承办部门名称
	private String remarks;				// 备注

	private String create_user_id;		// 检验任务单创建用户ID
	private String create_user_name;	// 检验任务单创建用户姓名
	private Date   create_date;			// 检验任务单创建日期
	private String mdy_user_id;			// 最后修改人ID
	private String mdy_user_name;		// 最后修改人姓名
	private Date   mdy_date;			// 最后修改日期
		
	private String data_status;			// 数据状态（0：创建  1：修改 99：已删除）



	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	public String getMdy_user_id() {
		return mdy_user_id;
	}

	public void setMdy_user_id(String mdy_user_id) {
		this.mdy_user_id = mdy_user_id;
	}

	public String getMdy_user_name() {
		return mdy_user_name;
	}

	public void setMdy_user_name(String mdy_user_name) {
		this.mdy_user_name = mdy_user_name;
	}

	public Date getMdy_date() {
		return mdy_date;
	}

	public void setMdy_date(Date mdy_date) {
		this.mdy_date = mdy_date;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getData_status() {
		return data_status;
	}

	public void setData_status(String data_status) {
		this.data_status = data_status;
	}

	public String getCom_id() {
		return com_id;
	}

	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}

	public String getCom_name() {
		return com_name;
	}

	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}

	public Date getInspection_date() {
		return inspection_date;
	}

	public void setInspection_date(Date inspection_date) {
		this.inspection_date = inspection_date;
	}

	public String getCheck_type() {
		return check_type;
	}

	public void setCheck_type(String check_type) {
		this.check_type = check_type;
	}

	public Double getTask_money() {
		return task_money;
	}

	public void setTask_money(Double task_money) {
		this.task_money = task_money;
	}

	public String getCreate_user_id() {
		return create_user_id;
	}

	public void setCreate_user_id(String create_user_id) {
		this.create_user_id = create_user_id;
	}

	public String getCreate_user_name() {
		return create_user_name;
	}

	public void setCreate_user_name(String create_user_name) {
		this.create_user_name = create_user_name;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Integer getDevice_rq_count() {
		return device_rq_count;
	}

	public void setDevice_rq_count(Integer device_rq_count) {
		this.device_rq_count = device_rq_count;
	}

	public Integer getDevice_gd_count() {
		return device_gd_count;
	}

	public void setDevice_gd_count(Integer device_gd_count) {
		this.device_gd_count = device_gd_count;
	}

	public Integer getDevice_gl_count() {
		return device_gl_count;
	}

	public void setDevice_gl_count(Integer device_gl_count) {
		this.device_gl_count = device_gl_count;
	}

	public Integer getDevice_qt_count() {
		return device_qt_count;
	}

	public void setDevice_qt_count(Integer device_qt_count) {
		this.device_qt_count = device_qt_count;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public String getContract_id() {
		return contract_id;
	}

	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}

	public String getContract_no() {
		return contract_no;
	}

	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}

	public String getSblx() {
		return sblx;
	}

	public void setSblx(String sblx) {
		this.sblx = sblx;
	}

	public String getJylx() {
		return jylx;
	}

	public void setJylx(String jylx) {
		this.jylx = jylx;
	}


	public String getCjry_id() {
		return cjry_id;
	}

	public void setCjry_id(String cjry_id) {
		this.cjry_id = cjry_id;
	}

	public String getCjry_name() {
		return cjry_name;
	}

	public void setCjry_name(String cjry_name) {
		this.cjry_name = cjry_name;
	}

	public String getSbsl() {
		return sbsl;
	}

	public void setSbsl(String sbsl) {
		this.sbsl = sbsl;
	}

	public String getCjsb_id() {
		return cjsb_id;
	}

	public void setCjsb_id(String cjsb_id) {
		this.cjsb_id = cjsb_id;
	}

	public String getTask_money_s() {
		return task_money_s;
	}

	public void setTask_money_s(String task_money_s) {
		this.task_money_s = task_money_s;
	}

	public Double getJs_money() {
		return js_money;
	}

	public void setJs_money(Double js_money) {
		this.js_money = js_money;
	}

	public Date getCqsj() {
		return cqsj;
	}

	public void setCqsj(Date cqsj) {
		this.cqsj = cqsj;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

//	public String getBz() {
//		return bz;
//	}
//
//	public void setBz(String bz) {
//		this.bz = bz;
//	}

	

	
}
