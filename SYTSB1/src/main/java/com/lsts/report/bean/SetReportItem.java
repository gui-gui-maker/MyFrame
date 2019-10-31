package com.lsts.report.bean;

import com.khnt.core.crud.bean.BaseEntity;

/*******************************************************************************
 * 
 * 报表配置项目
 * 
 * @author 肖慈边 2014-2-24
 * 
 */


public class SetReportItem implements BaseEntity{

	
//	private static final long serialVersionUID = 1L;
	
	private String	id	;				// ID
	private String	item_page_id	;			// 报告页ID
	private String	page_index;			// 报告页索引
	private String	page_name;			// 报告页名称
	private String index_text;
	private String	page_inspection_op;	// 报告页检验人员姓名
	private String	page_check_op;		// 报告页审核人员姓名
	private String	page_eval_pic_op;	// 报告页评片人员姓名
	private String	page_inspection_id;	// 报告页检验人员ID
	private String	page_check_id;		// 报告页审核人员ID
	private String	page_eval_pic_id;	// 报告页评片人员ID
	private String	is_must	;			// 是否是必选项
	private String	is_disCheck; 		// 是否显示勾选框
	private String	is_inspection;		// 是否有检验人员
	private String	is_check;			// 是否有审核人员
	private String  is_eval_pic;		// 是否有评片人员
	private Integer	inspect_seq;		// 检验人员位置标记
	private Integer	audit_seq;			// 审核人员位置标记
	private Integer	eval_pic_seq;		// 评片人员位置标记
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPage_index() {
		return page_index;
	}
	public void setPage_index(String page_index) {
		this.page_index = page_index;
	}
	public String getPage_inspection_op() {
		return page_inspection_op;
	}
	public void setPage_inspection_op(String page_inspection_op) {
		this.page_inspection_op = page_inspection_op;
	}
	public String getPage_check_op() {
		return page_check_op;
	}
	public void setPage_check_op(String page_check_op) {
		this.page_check_op = page_check_op;
	}
	public String isIs_must() {
		return is_must;
	}
	public void setIs_must(String is_must) {
		this.is_must = is_must;
	}
	public String getIs_check() {
		return is_check;
	}
	public void setIs_check(String is_check) {
		this.is_check = is_check;
	}
	public String getIndex_text() {
		return index_text;
	}
	public void setIndex_text(String index_text) {
		this.index_text = index_text;
	}
	public String getIs_disCheck() {
		return is_disCheck;
	}
	public void setIs_disCheck(String is_disCheck) {
		this.is_disCheck = is_disCheck;
	}
	public String getIs_inspection() {
		return is_inspection;
	}
	public void setIs_inspection(String is_inspection) {
		this.is_inspection = is_inspection;
	}
	public String getIs_must() {
		return is_must;
	}
	public String getPage_inspection_id() {
		return page_inspection_id;
	}
	public void setPage_inspection_id(String page_inspection_id) {
		this.page_inspection_id = page_inspection_id;
	}
	public String getPage_check_id() {
		return page_check_id;
	}
	public void setPage_check_id(String page_check_id) {
		this.page_check_id = page_check_id;
	}
	public Integer getInspect_seq() {
		return inspect_seq;
	}
	public void setInspect_seq(Integer inspect_seq) {
		this.inspect_seq = inspect_seq;
	}
	public Integer getAudit_seq() {
		return audit_seq;
	}
	public void setAudit_seq(Integer audit_seq) {
		this.audit_seq = audit_seq;
	}
	public String getIs_eval_pic() {
		return is_eval_pic;
	}
	public void setIs_eval_pic(String is_eval_pic) {
		this.is_eval_pic = is_eval_pic;
	}
	public Integer getEval_pic_seq() {
		return eval_pic_seq;
	}
	public void setEval_pic_seq(Integer eval_pic_seq) {
		this.eval_pic_seq = eval_pic_seq;
	}
	public String getPage_eval_pic_op() {
		return page_eval_pic_op;
	}
	public void setPage_eval_pic_op(String page_eval_pic_op) {
		this.page_eval_pic_op = page_eval_pic_op;
	}
	public String getPage_eval_pic_id() {
		return page_eval_pic_id;
	}
	public void setPage_eval_pic_id(String page_eval_pic_id) {
		this.page_eval_pic_id = page_eval_pic_id;
	}
	public String getPage_name() {
		return page_name;
	}
	public void setPage_name(String page_name) {
		this.page_name = page_name;
	}
	public String getItem_page_id() {
		return item_page_id;
	}
	public void setItem_page_id(String item_page_id) {
		this.item_page_id = item_page_id;
	}

	
} 
