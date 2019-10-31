package com.lsts.gis.quality.bean;

import java.math.BigDecimal;
import java.util.Date;



/**
 * 统计
 * 
 * 
 *
 * 
 */
public class QualityTjDTO {
	private String num;//数量
	private String name;//名称
	private String date;//日期
	private String no;//编号
	private String type;//类型
	private String status;//状态
	private String id;//报告主表id
	private String report_sn;//报告编号
	private String advance_time;//检验日期
	private String last_check_time;//下次检验日期
	private String check_op_name;//参与人员
	private String inspection_conclusion;//检验结论
	private String report_id;//
	private String table_name;//更改类型
	private String old_value;//修改之前内容
	private String new_value;//修改之后内容
	private String op_user_name;//修改人
	private String op_time;//修改时间
	private String item_name;//报告列名
	private String item_value;//报告列值
	private String report_name;//报告名称
	private String to_swf;//pdf
	private String export_pdf;//
	private String is_cur_error;//是否纠错
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReport_sn() {
		return report_sn;
	}
	public void setReport_sn(String report_sn) {
		this.report_sn = report_sn;
	}
	public String getAdvance_time() {
		return advance_time;
	}
	public void setAdvance_time(String advance_time) {
		this.advance_time = advance_time;
	}
	public String getLast_check_time() {
		return last_check_time;
	}
	public void setLast_check_time(String last_check_time) {
		this.last_check_time = last_check_time;
	}
	public String getCheck_op_name() {
		return check_op_name;
	}
	public void setCheck_op_name(String check_op_name) {
		this.check_op_name = check_op_name;
	}
	public String getInspection_conclusion() {
		return inspection_conclusion;
	}
	public void setInspection_conclusion(String inspection_conclusion) {
		this.inspection_conclusion = inspection_conclusion;
	}
	public String getReport_id() {
		return report_id;
	}
	public void setReport_id(String report_id) {
		this.report_id = report_id;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getOld_value() {
		return old_value;
	}
	public void setOld_value(String old_value) {
		this.old_value = old_value;
	}
	public String getNew_value() {
		return new_value;
	}
	public void setNew_value(String new_value) {
		this.new_value = new_value;
	}
	public String getOp_user_name() {
		return op_user_name;
	}
	public void setOp_user_name(String op_user_name) {
		this.op_user_name = op_user_name;
	}
	public String getOp_time() {
		return op_time;
	}
	public void setOp_time(String op_time) {
		this.op_time = op_time;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getItem_value() {
		return item_value;
	}
	public void setItem_value(String item_value) {
		this.item_value = item_value;
	}
	public String getReport_name() {
		return report_name;
	}
	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}
	public String getTo_swf() {
		return to_swf;
	}
	public void setTo_swf(String to_swf) {
		this.to_swf = to_swf;
	}
	public String getExport_pdf() {
		return export_pdf;
	}
	public void setExport_pdf(String export_pdf) {
		this.export_pdf = export_pdf;
	}
	public String getIs_cur_error() {
		return is_cur_error;
	}
	public void setIs_cur_error(String is_cur_error) {
		this.is_cur_error = is_cur_error;
	}
	
	
}
