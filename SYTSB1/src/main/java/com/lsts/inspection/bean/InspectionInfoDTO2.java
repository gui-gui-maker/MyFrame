package com.lsts.inspection.bean;


import java.util.Date;


/**
 * 报告业务数据传输对象
 * @ClassName InspectionInfoDTO
 * @JDK 1.6
 * @author GaoYa
 * @date 2017-03-10 下午02:45:00
 */
public class InspectionInfoDTO2{
	
	private static final long serialVersionUID = 1L;
	private String	id	;	//	ID
	private String	sn	;	//	业务流水号
	private String	fk_inspection_id	;	//	报检表ID
	private String	fk_tsjc_device_document_id	;	//	设备ID
	
	private String	report_type	;	//	报告类型
	private String	fk_flow_index_id	;	//	引用流程ID
	private String	flow_note_num	;	//	流程环节编号
	private String	flow_note_id	;	//	流程环节id
	private Double	advance_fees	;	//	预收金额
	private Date	advance_time	;	//	预检日期
	private String	data_status	;	//	数据状态（0:表示移动端数据上传初始状态，未启动流程正式使用 1：使用 99：作废）
	private String	check_op	;	//	检验联系人
	private String	check_tel	;	//	联系人电话
	private String	item_op_id	;	//	项目负责人ID
	private String	item_op_name	;	//	项目负责人名字
	private String	check_op_id	;	//	参检人员ID
	private String	check_op_name	;	//	参检人员名字	
	private String	check_unit_id	;	//	检验部门ID
	private String	enter_op_id	;	//	录入人员ID
	private String	enter_op_name;	//	录入人员姓名
	private Date	enter_time	;	//	录入时间
	private String	report_item	;	//	报告项目
	private String	ysjl_item	;	//	原始记录项目（页码）
	private String	fee_status	;	//	收费状态(0,默认,1,待收费,2,已收费 3 已入账)数据字典：PAYMENT_STATUS
	private String	inspection_conclusion	;	//	检验结论
	private Date	last_check_time	;	//	下次检验日期
	private String	advance_remark	;	//	预收金额备注
	private String	advance_type	;	//	预收金额类型 0 正常收费 1 协议收费 2 免收费
	//private Date	last_upd_date	;	//	同步到监察系统时间
	private String	report_sn	;	//	报告书编号
	private String	report_com_name	;	//	报告书上使用单位名称
	private String	report_com_address	;	//	报告书上使用单位名地址
	private Double	receivable	;	//	实收金额
	private Integer	discount	;	//	折扣率
	private String	appoint_op_id	;	//	指定操作人ID
	private String	is_report_input	;	//	报告是否已经录入
	private String	apprresult	;	//	评价结果
	private String	is_report_confirm	;	// 原始记录是否已经校核，默认为0（0：未校核 1：校核通过 2：校核未通过）
	private String  report_confirm_remark;	// 报告校核备注
	private Integer	warning_deadline	;	// 预警时限
	private String	is_plan	;	//	是否分配
	private String	invoice_no	;	//	发票号
	private Date	invoice_date	;	//	缴费日期
	private String	enter_unit_id	;	//	录入人员所属部门ID
	private String	is_borrow	;	//	是否预借报告书
	private String	borrow_sn	;	//	预借报告书编号
	private Date    borrow_date;	// 外借日期
	private String	fee_type	;	//	收费方式 ["1","现金收费"],["2","支票"],["4","加急支票"],["3","扣除预付款"],["5","免收费"]
	private String	is_recheck	;	//	是否是复检
	private Date	print_time	;	//	报告第一次打印时间
	private String  is_print_tags;	//  是否已打印标签（0：未打印 1：已打印）
	private String	is_xsqsy	;	//	是否有限速器实验
	private String	is_zzsy	;	//	是否有载重实验
	private String	is_return_print	;	//	退回打印  1 退回打印  
	private String	red	;	//	超期已检
	private String	is_user_defined	;	//	自定义报告 1 是
	private String	file_name	;	//	自定义报告文件名
	private String	xsqts	;	//	
	private String	fk_hall_no	;	//	大厅报检订单号
	private String  report_confirm_id;		// 预存校核人ID
	private String  report_confirm_name;	// 预存校核人姓名
	private String  confirm_id;		// 实际校核人ID
	private String  confirm_name;	// 实际校核人姓名
	private Date    confirm_Date;	// 实际校核时间
	private String  examine_id;		// 审批人ID
	private String  examine_name;	// 审批人姓名
	private Date    examine_Date;	// 审批时间
	private String  issue_id;	// 签发人ID
	private String  issue_name;	// 签发人姓名
	private Date    issue_Date;	// 签发时间
	private String  is_flow;	// 是否启动流程
	private String  flow_note_name;	//步骤名称
	private String  is_copy;  		//是否是复制报告
	private String  is_back;		// 是否退回报检
	private String  is_mobile;		// 是否移动检验，默认为0（0：否 1：是）
	private String  check_status;	// 检验状态，默认为0（0：正常检验 1：中止检验）
	private String  archives_status;// 档案恢复（1为恢复）
	private String  export_pdf;		// 报告pdf导出时间标志
	private String  is_self_sn;		// 是否自编号（0：否 1：是）
										// 如果是自编号，系统不自动生成报告编号，用户可修改；
										// 如果不是自编号，系统自动生成报告编号，用户不可修改；
	private String  pact_id;		// 合同ID
	private String  pact_sn;		// 合同编号
	
	private String device_qr_code;	// 设备二维码编号
	private String is_cur_error;	// 当前正在纠错中（0：否 1：是）
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getFk_inspection_id() {
		return fk_inspection_id;
	}
	public void setFk_inspection_id(String fk_inspection_id) {
		this.fk_inspection_id = fk_inspection_id;
	}
	public String getFk_tsjc_device_document_id() {
		return fk_tsjc_device_document_id;
	}
	public void setFk_tsjc_device_document_id(String fk_tsjc_device_document_id) {
		this.fk_tsjc_device_document_id = fk_tsjc_device_document_id;
	}
	public String getReport_type() {
		return report_type;
	}
	public void setReport_type(String report_type) {
		this.report_type = report_type;
	}
	public String getFk_flow_index_id() {
		return fk_flow_index_id;
	}
	public void setFk_flow_index_id(String fk_flow_index_id) {
		this.fk_flow_index_id = fk_flow_index_id;
	}
	public String getFlow_note_num() {
		return flow_note_num;
	}
	public void setFlow_note_num(String flow_note_num) {
		this.flow_note_num = flow_note_num;
	}
	public String getFlow_note_id() {
		return flow_note_id;
	}
	public void setFlow_note_id(String flow_note_id) {
		this.flow_note_id = flow_note_id;
	}
	public Double getAdvance_fees() {
		return advance_fees;
	}
	public void setAdvance_fees(Double advance_fees) {
		this.advance_fees = advance_fees;
	}
	public Date getAdvance_time() {
		return advance_time;
	}
	public void setAdvance_time(Date advance_time) {
		this.advance_time = advance_time;
	}
	public String getData_status() {
		return data_status;
	}
	public void setData_status(String data_status) {
		this.data_status = data_status;
	}
	public String getCheck_op() {
		return check_op;
	}
	public void setCheck_op(String check_op) {
		this.check_op = check_op;
	}
	public String getCheck_tel() {
		return check_tel;
	}
	public void setCheck_tel(String check_tel) {
		this.check_tel = check_tel;
	}
	public String getItem_op_id() {
		return item_op_id;
	}
	public void setItem_op_id(String item_op_id) {
		this.item_op_id = item_op_id;
	}
	public String getItem_op_name() {
		return item_op_name;
	}
	public void setItem_op_name(String item_op_name) {
		this.item_op_name = item_op_name;
	}
	public String getCheck_op_id() {
		return check_op_id;
	}
	public void setCheck_op_id(String check_op_id) {
		this.check_op_id = check_op_id;
	}
	public String getCheck_op_name() {
		return check_op_name;
	}
	public void setCheck_op_name(String check_op_name) {
		this.check_op_name = check_op_name;
	}
	public String getCheck_unit_id() {
		return check_unit_id;
	}
	public void setCheck_unit_id(String check_unit_id) {
		this.check_unit_id = check_unit_id;
	}
	public String getEnter_op_id() {
		return enter_op_id;
	}
	public void setEnter_op_id(String enter_op_id) {
		this.enter_op_id = enter_op_id;
	}
	public String getEnter_op_name() {
		return enter_op_name;
	}
	public void setEnter_op_name(String enter_op_name) {
		this.enter_op_name = enter_op_name;
	}
	public Date getEnter_time() {
		return enter_time;
	}
	public void setEnter_time(Date enter_time) {
		this.enter_time = enter_time;
	}
	public String getReport_item() {
		return report_item;
	}
	public void setReport_item(String report_item) {
		this.report_item = report_item;
	}
	public String getYsjl_item() {
		return ysjl_item;
	}
	public void setYsjl_item(String ysjl_item) {
		this.ysjl_item = ysjl_item;
	}
	public String getFee_status() {
		return fee_status;
	}
	public void setFee_status(String fee_status) {
		this.fee_status = fee_status;
	}
	public String getInspection_conclusion() {
		return inspection_conclusion;
	}
	public void setInspection_conclusion(String inspection_conclusion) {
		this.inspection_conclusion = inspection_conclusion;
	}
	public Date getLast_check_time() {
		return last_check_time;
	}
	public void setLast_check_time(Date last_check_time) {
		this.last_check_time = last_check_time;
	}
	public String getAdvance_remark() {
		return advance_remark;
	}
	public void setAdvance_remark(String advance_remark) {
		this.advance_remark = advance_remark;
	}
	public String getAdvance_type() {
		return advance_type;
	}
	public void setAdvance_type(String advance_type) {
		this.advance_type = advance_type;
	}
	public String getReport_sn() {
		return report_sn;
	}
	public void setReport_sn(String report_sn) {
		this.report_sn = report_sn;
	}
	public String getReport_com_name() {
		return report_com_name;
	}
	public void setReport_com_name(String report_com_name) {
		this.report_com_name = report_com_name;
	}
	public String getReport_com_address() {
		return report_com_address;
	}
	public void setReport_com_address(String report_com_address) {
		this.report_com_address = report_com_address;
	}
	public Double getReceivable() {
		return receivable;
	}
	public void setReceivable(Double receivable) {
		this.receivable = receivable;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	public String getAppoint_op_id() {
		return appoint_op_id;
	}
	public void setAppoint_op_id(String appoint_op_id) {
		this.appoint_op_id = appoint_op_id;
	}
	public String getIs_report_input() {
		return is_report_input;
	}
	public void setIs_report_input(String is_report_input) {
		this.is_report_input = is_report_input;
	}
	public String getApprresult() {
		return apprresult;
	}
	public void setApprresult(String apprresult) {
		this.apprresult = apprresult;
	}
	public String getIs_report_confirm() {
		return is_report_confirm;
	}
	public void setIs_report_confirm(String is_report_confirm) {
		this.is_report_confirm = is_report_confirm;
	}
	public String getReport_confirm_remark() {
		return report_confirm_remark;
	}
	public void setReport_confirm_remark(String report_confirm_remark) {
		this.report_confirm_remark = report_confirm_remark;
	}
	public Integer getWarning_deadline() {
		return warning_deadline;
	}
	public void setWarning_deadline(Integer warning_deadline) {
		this.warning_deadline = warning_deadline;
	}
	public String getIs_plan() {
		return is_plan;
	}
	public void setIs_plan(String is_plan) {
		this.is_plan = is_plan;
	}
	public String getInvoice_no() {
		return invoice_no;
	}
	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}
	public Date getInvoice_date() {
		return invoice_date;
	}
	public void setInvoice_date(Date invoice_date) {
		this.invoice_date = invoice_date;
	}
	public String getEnter_unit_id() {
		return enter_unit_id;
	}
	public void setEnter_unit_id(String enter_unit_id) {
		this.enter_unit_id = enter_unit_id;
	}
	public String getIs_borrow() {
		return is_borrow;
	}
	public void setIs_borrow(String is_borrow) {
		this.is_borrow = is_borrow;
	}
	public String getBorrow_sn() {
		return borrow_sn;
	}
	public void setBorrow_sn(String borrow_sn) {
		this.borrow_sn = borrow_sn;
	}
	public Date getBorrow_date() {
		return borrow_date;
	}
	public void setBorrow_date(Date borrow_date) {
		this.borrow_date = borrow_date;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public String getIs_recheck() {
		return is_recheck;
	}
	public void setIs_recheck(String is_recheck) {
		this.is_recheck = is_recheck;
	}
	public Date getPrint_time() {
		return print_time;
	}
	public void setPrint_time(Date print_time) {
		this.print_time = print_time;
	}
	public String getIs_print_tags() {
		return is_print_tags;
	}
	public void setIs_print_tags(String is_print_tags) {
		this.is_print_tags = is_print_tags;
	}
	public String getIs_xsqsy() {
		return is_xsqsy;
	}
	public void setIs_xsqsy(String is_xsqsy) {
		this.is_xsqsy = is_xsqsy;
	}
	public String getIs_zzsy() {
		return is_zzsy;
	}
	public void setIs_zzsy(String is_zzsy) {
		this.is_zzsy = is_zzsy;
	}
	public String getIs_return_print() {
		return is_return_print;
	}
	public void setIs_return_print(String is_return_print) {
		this.is_return_print = is_return_print;
	}
	public String getRed() {
		return red;
	}
	public void setRed(String red) {
		this.red = red;
	}
	public String getIs_user_defined() {
		return is_user_defined;
	}
	public void setIs_user_defined(String is_user_defined) {
		this.is_user_defined = is_user_defined;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getXsqts() {
		return xsqts;
	}
	public void setXsqts(String xsqts) {
		this.xsqts = xsqts;
	}
	public String getFk_hall_no() {
		return fk_hall_no;
	}
	public void setFk_hall_no(String fk_hall_no) {
		this.fk_hall_no = fk_hall_no;
	}
	public String getReport_confirm_id() {
		return report_confirm_id;
	}
	public void setReport_confirm_id(String report_confirm_id) {
		this.report_confirm_id = report_confirm_id;
	}
	public String getReport_confirm_name() {
		return report_confirm_name;
	}
	public void setReport_confirm_name(String report_confirm_name) {
		this.report_confirm_name = report_confirm_name;
	}
	public String getConfirm_id() {
		return confirm_id;
	}
	public void setConfirm_id(String confirm_id) {
		this.confirm_id = confirm_id;
	}
	public String getConfirm_name() {
		return confirm_name;
	}
	public void setConfirm_name(String confirm_name) {
		this.confirm_name = confirm_name;
	}
	public Date getConfirm_Date() {
		return confirm_Date;
	}
	public void setConfirm_Date(Date confirm_Date) {
		this.confirm_Date = confirm_Date;
	}
	public String getExamine_id() {
		return examine_id;
	}
	public void setExamine_id(String examine_id) {
		this.examine_id = examine_id;
	}
	public String getExamine_name() {
		return examine_name;
	}
	public void setExamine_name(String examine_name) {
		this.examine_name = examine_name;
	}
	public Date getExamine_Date() {
		return examine_Date;
	}
	public void setExamine_Date(Date examine_Date) {
		this.examine_Date = examine_Date;
	}
	public String getIssue_id() {
		return issue_id;
	}
	public void setIssue_id(String issue_id) {
		this.issue_id = issue_id;
	}
	public String getIssue_name() {
		return issue_name;
	}
	public void setIssue_name(String issue_name) {
		this.issue_name = issue_name;
	}
	public Date getIssue_Date() {
		return issue_Date;
	}
	public void setIssue_Date(Date issue_Date) {
		this.issue_Date = issue_Date;
	}
	public String getIs_flow() {
		return is_flow;
	}
	public void setIs_flow(String is_flow) {
		this.is_flow = is_flow;
	}
	public String getFlow_note_name() {
		return flow_note_name;
	}
	public void setFlow_note_name(String flow_note_name) {
		this.flow_note_name = flow_note_name;
	}
	public String getIs_copy() {
		return is_copy;
	}
	public void setIs_copy(String is_copy) {
		this.is_copy = is_copy;
	}
	public String getIs_back() {
		return is_back;
	}
	public void setIs_back(String is_back) {
		this.is_back = is_back;
	}
	public String getIs_mobile() {
		return is_mobile;
	}
	public void setIs_mobile(String is_mobile) {
		this.is_mobile = is_mobile;
	}
	public String getCheck_status() {
		return check_status;
	}
	public void setCheck_status(String check_status) {
		this.check_status = check_status;
	}
	public String getArchives_status() {
		return archives_status;
	}
	public void setArchives_status(String archives_status) {
		this.archives_status = archives_status;
	}
	public String getExport_pdf() {
		return export_pdf;
	}
	public void setExport_pdf(String export_pdf) {
		this.export_pdf = export_pdf;
	}
	public String getIs_self_sn() {
		return is_self_sn;
	}
	public void setIs_self_sn(String is_self_sn) {
		this.is_self_sn = is_self_sn;
	}
	public String getPact_id() {
		return pact_id;
	}
	public void setPact_id(String pact_id) {
		this.pact_id = pact_id;
	}
	public String getPact_sn() {
		return pact_sn;
	}
	public void setPact_sn(String pact_sn) {
		this.pact_sn = pact_sn;
	}
	public String getDevice_qr_code() {
		return device_qr_code;
	}
	public void setDevice_qr_code(String device_qr_code) {
		this.device_qr_code = device_qr_code;
	}
	public String getIs_cur_error() {
		return is_cur_error;
	}
	public void setIs_cur_error(String is_cur_error) {
		this.is_cur_error = is_cur_error;
	}
}
