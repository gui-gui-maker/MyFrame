package com.lsts.flow.bean;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 流程配置
 * BaseFlowConfig entity. 
 * @author GaoYa
 * @date 2014-03-11 13:48:00
 */
@Entity
@Table(name = "base_unit_flow")
public class BaseFlowConfig implements BaseEntity{

	private static final long serialVersionUID = 1L;
	private String id;	// ID
	private String fk_unit_id;	// 检验单位ID
	private String device_type;	// 设备类别
	private String check_type;	// 检验类别
	private String fk_report_id;	// 报告类型ID
	private String report_name;		// 报告类型名称
	private String fk_flow_id;	// 流程ID
	private String flow_name;	// 流程名称
	private String report_sn;	// 报告书编号格式
	private String remark;	// 备注

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFk_unit_id() {
		return fk_unit_id;
	}
	public void setFk_unit_id(String fk_unit_id) {
		this.fk_unit_id = fk_unit_id;
	}
	public String getDevice_type() {
		return device_type;
	}
	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}
	public String getCheck_type() {
		return check_type;
	}
	public void setCheck_type(String check_type) {
		this.check_type = check_type;
	}
	public String getFk_report_id() {
		return fk_report_id;
	}
	public void setFk_report_id(String fk_report_id) {
		this.fk_report_id = fk_report_id;
	}
	public String getReport_name() {
		return report_name;
	}
	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}
	public String getFk_flow_id() {
		return fk_flow_id;
	}
	public void setFk_flow_id(String fk_flow_id) {
		this.fk_flow_id = fk_flow_id;
	}
	public String getFlow_name() {
		return flow_name;
	}
	public void setFlow_name(String flow_name) {
		this.flow_name = flow_name;
	}
	public String getReport_sn() {
		return report_sn;
	}
	public void setReport_sn(String report_sn) {
		this.report_sn = report_sn;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@SuppressWarnings("unchecked")
	public Map to_Map() {
        Map map = new HashMap();
        map.put("id", id);
        map.put("device_type", device_type);  
        map.put("check_type", check_type);      
        map.put("fk_report_id", fk_report_id);
        map.put("report_name", report_name);
        map.put("fk_flow_id", fk_flow_id);
        map.put("flow_name", flow_name);
        map.put("remark", remark);
        return map;
    }
}
