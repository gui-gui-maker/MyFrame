package com.scts.maintenance.bean;

import java.util.Date;
import java.util.List;

import com.khnt.pub.fileupload.bean.Attachment;


/**
 * 系统运维台账数据传输对象
 * MaintenanceInfoDTO. 
 * @author GaoYa
 * @date 2015-09-09 上午09:15:00
 * @update 2017-08-03 上午09:15:00
 */

public class MaintenanceInfoDTO{

	private String id;					// ID
	private String sn;					// 业务流水号
	private String info_type;			// 类型（1：新功能 2：升级功能 3：数据维护 4：数据统计 5：优化功能）
	private String priority;			// 优先级（数据字典：BASE_PRIORITY 0：一般 1：重要 2：紧急）
	private String func_name;			// 功能模块
	private String pro_desc;			// 内容描述
	
	private String advance_user_id;		// 报告人ID
	private String advance_user_name;	// 报告人姓名
	private String org_id;				// 业务部门ID（多个部门，以逗号分隔）
	private String org_name;			// 业务部门名称（多个部门，以逗号分隔）
	private Date   advance_date;		// 报告日期
	
	private String create_user_id;		// 记录人ID
	private String create_user_name;	// 记录人姓名
	private Date   create_date;			// 记录日期
	private String write_user_id;		// 填表人ID
	private String write_user_name;		// 填表人姓名
	private Date   write_date;			// 填表日期
	
	/* 
	 * 标记论证后，将向论证人发送微信提醒
	 * 论证结论为处理的，发送：xxx功能已通过论证，进入开发修改环节，预计xxx完成。感谢你对院信息化工作的支持。（信息中心）
	 * 论证结论为延期的，发送：xxx功能已通过论证，因xxx原因延迟开发。感谢你对院信息化工作的支持。（信息中心）
	 * */
	private String prove_user_id;		// 论证人ID（多个论证人以逗号分隔）
	private String prove_user_name;		// 论证人姓名（多个论证人以逗号分隔）
	private String prove_date;			// 论证日期
	private String prove_type;			// 论证结论（0：处理 1：延期）
	private String prove_remark;		// 论证备注（延期时填写）
	private Date   expect_finish_date;	// 预计完成日期
	private String receive_user_id;		// 论证记录人ID
	private String receive_user_name;	// 论证记录人姓名
	private Date   receive_date;		// 论证记录日期
	
	private String develop_user_name;	// 开发员姓名
	private Date   develop_start_date;	// 开始开发日期
	private String develop_desc;		// 开发完成情况描述
	private Date   develop_end_date;	// 完成开发日期
	
	private String test_user_id;		// 测试人ID
	private String test_user_name;		// 测试人姓名
	private Date   test_date;			// 测试日期
	
	private String publish_user_id;		// 发布人ID
	private String publish_user_name;	// 发布人姓名
	private Date   publish_date;		// 发布日期（已发布的日志将公示于系统首页）
	
	private String remarks;				// 备注
	
	private String fk_func_task_id;		// 软件功能任务书ID
	private String func_task_name;		// 软件功能任务书名称
	private String func_task_sn;		// 软件功能任务书编号
	
	private String confirm_user_id;		// 确认人ID
	private String confirm_user_name;	// 确认人姓名
	private Date   confirm_date;		// 确认日期
	
	private String mdy_user_id;			// 最后修改人ID
	private String mdy_user_name;		// 最后修改人姓名
	private Date   mdy_date;			// 最后修改日期
	
	private String data_status;			// 数据状态（数据字典MAINTENANCE_DATA_STATUS，0：未受理 1：已受理 2：已论证 3：已完成 4：已发布  5：已确认 99：已删除）
	private String send_msg_type;		// 反馈方式（1：微信 2：短信 3：微信和短信 0：不反馈）
	
	private String mobile;				// 手机号码
	private String content;				// 发送内容
	
	private String task_attach_id;		// 功能说明附件ID
	private String task_attach_name;	// 功能说明附件文件名称
	private String update_attach_id;	// 更新说明附件ID
	private String update_attach_name;	// 更新说明附件文件名称
	private List<Attachment> attachment_list;
	//private String atta_file_paths;		// 功能说明附件文件路径名称

	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}
	
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
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

	public String getData_status() {
		return data_status;
	}

	public void setData_status(String data_status) {
		this.data_status = data_status;
	}

	public String getInfo_type() {
		return info_type;
	}

	public void setInfo_type(String info_type) {
		this.info_type = info_type;
	}

	public String getFunc_name() {
		return func_name;
	}

	public void setFunc_name(String func_name) {
		this.func_name = func_name;
	}

	public String getPro_desc() {
		return pro_desc;
	}

	public void setPro_desc(String pro_desc) {
		this.pro_desc = pro_desc;
	}

	public String getAdvance_user_id() {
		return advance_user_id;
	}

	public void setAdvance_user_id(String advance_user_id) {
		this.advance_user_id = advance_user_id;
	}

	public String getAdvance_user_name() {
		return advance_user_name;
	}

	public void setAdvance_user_name(String advance_user_name) {
		this.advance_user_name = advance_user_name;
	}

	public Date getAdvance_date() {
		return advance_date;
	}

	public void setAdvance_date(Date advance_date) {
		this.advance_date = advance_date;
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

	public String getReceive_user_name() {
		return receive_user_name;
	}

	public void setReceive_user_name(String receive_user_name) {
		this.receive_user_name = receive_user_name;
	}

	public Date getReceive_date() {
		return receive_date;
	}

	public void setReceive_date(Date receive_date) {
		this.receive_date = receive_date;
	}

	public String getDevelop_user_name() {
		return develop_user_name;
	}

	public void setDevelop_user_name(String develop_user_name) {
		this.develop_user_name = develop_user_name;
	}

	public Date getDevelop_start_date() {
		return develop_start_date;
	}

	public void setDevelop_start_date(Date develop_start_date) {
		this.develop_start_date = develop_start_date;
	}

	public Date getDevelop_end_date() {
		return develop_end_date;
	}

	public void setDevelop_end_date(Date develop_end_date) {
		this.develop_end_date = develop_end_date;
	}

	public String getPublish_user_id() {
		return publish_user_id;
	}

	public void setPublish_user_id(String publish_user_id) {
		this.publish_user_id = publish_user_id;
	}

	public String getPublish_user_name() {
		return publish_user_name;
	}

	public void setPublish_user_name(String publish_user_name) {
		this.publish_user_name = publish_user_name;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDevelop_desc() {
		return develop_desc;
	}

	public void setDevelop_desc(String develop_desc) {
		this.develop_desc = develop_desc;
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

	public String getSend_msg_type() {
		return send_msg_type;
	}

	public void setSend_msg_type(String send_msg_type) {
		this.send_msg_type = send_msg_type;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getReceive_user_id() {
		return receive_user_id;
	}

	public void setReceive_user_id(String receive_user_id) {
		this.receive_user_id = receive_user_id;
	}

	public String getTest_user_id() {
		return test_user_id;
	}

	public void setTest_user_id(String test_user_id) {
		this.test_user_id = test_user_id;
	}

	public String getTest_user_name() {
		return test_user_name;
	}

	public void setTest_user_name(String test_user_name) {
		this.test_user_name = test_user_name;
	}

	public Date getTest_date() {
		return test_date;
	}

	public void setTest_date(Date test_date) {
		this.test_date = test_date;
	}

	public String getFk_func_task_id() {
		return fk_func_task_id;
	}

	public void setFk_func_task_id(String fk_func_task_id) {
		this.fk_func_task_id = fk_func_task_id;
	}

	public String getFunc_task_name() {
		return func_task_name;
	}

	public void setFunc_task_name(String func_task_name) {
		this.func_task_name = func_task_name;
	}

	public String getFunc_task_sn() {
		return func_task_sn;
	}

	public void setFunc_task_sn(String func_task_sn) {
		this.func_task_sn = func_task_sn;
	}

	public String getProve_user_id() {
		return prove_user_id;
	}

	public void setProve_user_id(String prove_user_id) {
		this.prove_user_id = prove_user_id;
	}

	public String getProve_user_name() {
		return prove_user_name;
	}

	public void setProve_user_name(String prove_user_name) {
		this.prove_user_name = prove_user_name;
	}

	public String getProve_date() {
		return prove_date;
	}

	public void setProve_date(String prove_date) {
		this.prove_date = prove_date;
	}

	public String getProve_type() {
		return prove_type;
	}

	public void setProve_type(String prove_type) {
		this.prove_type = prove_type;
	}

	public String getProve_remark() {
		return prove_remark;
	}

	public void setProve_remark(String prove_remark) {
		this.prove_remark = prove_remark;
	}

	public Date getExpect_finish_date() {
		return expect_finish_date;
	}

	public void setExpect_finish_date(Date expect_finish_date) {
		this.expect_finish_date = expect_finish_date;
	}

	public String getConfirm_user_id() {
		return confirm_user_id;
	}

	public void setConfirm_user_id(String confirm_user_id) {
		this.confirm_user_id = confirm_user_id;
	}

	public String getConfirm_user_name() {
		return confirm_user_name;
	}

	public void setConfirm_user_name(String confirm_user_name) {
		this.confirm_user_name = confirm_user_name;
	}

	public Date getConfirm_date() {
		return confirm_date;
	}

	public void setConfirm_date(Date confirm_date) {
		this.confirm_date = confirm_date;
	}

	public String getTask_attach_id() {
		return task_attach_id;
	}

	public void setTask_attach_id(String task_attach_id) {
		this.task_attach_id = task_attach_id;
	}

	public String getUpdate_attach_id() {
		return update_attach_id;
	}

	public void setUpdate_attach_id(String update_attach_id) {
		this.update_attach_id = update_attach_id;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getPublish_date() {
		return publish_date;
	}

	public void setPublish_date(Date publish_date) {
		this.publish_date = publish_date;
	}

	public String getWrite_user_id() {
		return write_user_id;
	}

	public void setWrite_user_id(String write_user_id) {
		this.write_user_id = write_user_id;
	}

	public String getWrite_user_name() {
		return write_user_name;
	}

	public void setWrite_user_name(String write_user_name) {
		this.write_user_name = write_user_name;
	}

	public Date getWrite_date() {
		return write_date;
	}

	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}

	public String getTask_attach_name() {
		return task_attach_name;
	}

	public void setTask_attach_name(String task_attach_name) {
		this.task_attach_name = task_attach_name;
	}

	public String getUpdate_attach_name() {
		return update_attach_name;
	}

	public void setUpdate_attach_name(String update_attach_name) {
		this.update_attach_name = update_attach_name;
	}

	public List<Attachment> getAttachment_list() {
		return attachment_list;
	}

	public void setAttachment_list(List<Attachment> attachment_list) {
		this.attachment_list = attachment_list;
	}

	/*public String getAtta_file_paths() {
		return atta_file_paths;
	}

	public void setAtta_file_paths(String atta_file_paths) {
		this.atta_file_paths = atta_file_paths;
	}	*/	
	
}
