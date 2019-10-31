package com.scts.task.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 检验和质量管理软件功能开发（修改）任务书
 * FunctionTaskInfo entity. 
 * @author GaoYa
 * @date 2016-11-22 上午11:26:00
 */
@Entity
@Table(name = "FUNCTION_TASK_INFO")
public class FunctionTaskInfo implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;					// ID
	private String sn;					// 任务书编号（格式为当前年份+“-”+序列号，例如2016-001）
	private String task_name;			// 任务名称
	private String task_requirement;	// 任务要求
	private String task_type;			// 任务类型（1：新功能 2：优化功能 3：数据维护 4：数据统计）
	private String task_category;		// 任务分类（系统功能模块）
	private String task_priority;		// 任务优先级（0：一般 1：重要 2：紧急）	
	private Date   expect_finish_date;	// 期望完成日期
	
	private String advance_user_id;		// 报告人ID
	private String advance_user_name;	// 报告人姓名
	private String advance_org_id;		// 报告部门ID
	private String advance_org_name;	// 报告部门名称
	private Date   advance_date;		// 报告日期
	
	private String create_org_id;		// 任务下达部门ID
	private String create_org_name;		// 任务下达部门名称
	private String create_user_id;		// 任务下达用户ID
	private String create_user_name;	// 任务下达用户姓名
	private Date   create_date;			// 任务下达日期
	private String issue_user_id;		// 任务签发用户ID
	private String issue_user_name;		// 任务签发用户姓名
	private Date   issue_date;			// 任务签发日期
	
	private String receive_org_id;		// 任务接收部门ID
	private String receive_org_name;	// 任务接收部门名称
	private String receive_user_id;		// 任务接收人ID
	private String receive_user_name;	// 任务接收人姓名
	private Date   receive_date;		// 任务接收日期
	
	private String test_result1;		// 一测结果（1：满意 2：存在问题或建议）
	private String test_remark1;		// 一测结果描述（具体存在的问题或建议描述）
	private String test_user_id1;		// 一测用户ID
	private String test_user_name1;		// 一测用户姓名
	private Date   test_date1;			// 一测日期
	
	private String test_result2;		// 二测结果（1：满意 2：存在问题或建议）
	private String test_remark2;		// 二测结果描述（具体存在的问题或建议描述）
	private String test_user_id2;		// 二测用户ID
	private String test_user_name2;		// 二测用户姓名
	private Date   test_date2;			// 二测日期
	
	private String test_result3;		// 三测结果（1：满意 2：存在问题或建议）
	private String test_remark3;		// 三测结果描述（具体存在的问题或建议描述）
	private String test_user_id3;		// 三测用户ID
	private String test_user_name3;		// 三测用户姓名
	private Date   test_date3;			// 三测日期

	private String zlb_desc;			// 质管部确认完成情况
	private String zlb_remark;			// 质管部备注
	private String zlb_user_id;			// 质管部经办人ID
	private String zlb_user_name;		// 质管部经办人姓名
	private Date   develop_end_date;	// 质管部确认完成日期
	
	private String remarks;				// 备注
	private String mdy_user_id;			// 最后修改人ID
	private String mdy_user_name;		// 最后修改人姓名
	private Date   mdy_date;			// 最后修改日期
	private String data_status;			// 数据状态（0：任务下达 1：任务已签发 2：任务已接收 3：任务开发中 
										// 4：任务开发完成 5：任务测试中 6：任务测试完成 7：任务确认完成 8：未提交 9：任务退回 99：已删除）



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

	public String getReceive_user_id() {
		return receive_user_id;
	}

	public void setReceive_user_id(String receive_user_id) {
		this.receive_user_id = receive_user_id;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getTask_name() {
		return task_name;
	}

	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}

	public String getTask_requirement() {
		return task_requirement;
	}

	public void setTask_requirement(String task_requirement) {
		this.task_requirement = task_requirement;
	}

	public Date getExpect_finish_date() {
		return expect_finish_date;
	}

	public void setExpect_finish_date(Date expect_finish_date) {
		this.expect_finish_date = expect_finish_date;
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

	public String getIssue_user_id() {
		return issue_user_id;
	}

	public void setIssue_user_id(String issue_user_id) {
		this.issue_user_id = issue_user_id;
	}

	public String getIssue_user_name() {
		return issue_user_name;
	}

	public void setIssue_user_name(String issue_user_name) {
		this.issue_user_name = issue_user_name;
	}

	public Date getIssue_date() {
		return issue_date;
	}

	public void setIssue_date(Date issue_date) {
		this.issue_date = issue_date;
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

	public String getTest_result1() {
		return test_result1;
	}

	public void setTest_result1(String test_result1) {
		this.test_result1 = test_result1;
	}

	public String getTest_remark1() {
		return test_remark1;
	}

	public void setTest_remark1(String test_remark1) {
		this.test_remark1 = test_remark1;
	}

	public String getTest_user_id1() {
		return test_user_id1;
	}

	public void setTest_user_id1(String test_user_id1) {
		this.test_user_id1 = test_user_id1;
	}

	public String getTest_user_name1() {
		return test_user_name1;
	}

	public void setTest_user_name1(String test_user_name1) {
		this.test_user_name1 = test_user_name1;
	}

	public Date getTest_date1() {
		return test_date1;
	}

	public void setTest_date1(Date test_date1) {
		this.test_date1 = test_date1;
	}

	public String getTest_result2() {
		return test_result2;
	}

	public void setTest_result2(String test_result2) {
		this.test_result2 = test_result2;
	}

	public String getTest_remark2() {
		return test_remark2;
	}

	public void setTest_remark2(String test_remark2) {
		this.test_remark2 = test_remark2;
	}

	public String getTest_user_id2() {
		return test_user_id2;
	}

	public void setTest_user_id2(String test_user_id2) {
		this.test_user_id2 = test_user_id2;
	}

	public String getTest_user_name2() {
		return test_user_name2;
	}

	public void setTest_user_name2(String test_user_name2) {
		this.test_user_name2 = test_user_name2;
	}

	public Date getTest_date2() {
		return test_date2;
	}

	public void setTest_date2(Date test_date2) {
		this.test_date2 = test_date2;
	}

	public String getTest_result3() {
		return test_result3;
	}

	public void setTest_result3(String test_result3) {
		this.test_result3 = test_result3;
	}

	public String getTest_remark3() {
		return test_remark3;
	}

	public void setTest_remark3(String test_remark3) {
		this.test_remark3 = test_remark3;
	}

	public String getTest_user_id3() {
		return test_user_id3;
	}

	public void setTest_user_id3(String test_user_id3) {
		this.test_user_id3 = test_user_id3;
	}

	public String getTest_user_name3() {
		return test_user_name3;
	}

	public void setTest_user_name3(String test_user_name3) {
		this.test_user_name3 = test_user_name3;
	}

	public Date getTest_date3() {
		return test_date3;
	}

	public void setTest_date3(Date test_date3) {
		this.test_date3 = test_date3;
	}

	public String getZlb_desc() {
		return zlb_desc;
	}

	public void setZlb_desc(String zlb_desc) {
		this.zlb_desc = zlb_desc;
	}

	public String getZlb_remark() {
		return zlb_remark;
	}

	public void setZlb_remark(String zlb_remark) {
		this.zlb_remark = zlb_remark;
	}

	public String getZlb_user_id() {
		return zlb_user_id;
	}

	public void setZlb_user_id(String zlb_user_id) {
		this.zlb_user_id = zlb_user_id;
	}

	public String getZlb_user_name() {
		return zlb_user_name;
	}

	public void setZlb_user_name(String zlb_user_name) {
		this.zlb_user_name = zlb_user_name;
	}

	public Date getDevelop_end_date() {
		return develop_end_date;
	}

	public void setDevelop_end_date(Date develop_end_date) {
		this.develop_end_date = develop_end_date;
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

	public String getCreate_org_id() {
		return create_org_id;
	}

	public void setCreate_org_id(String create_org_id) {
		this.create_org_id = create_org_id;
	}

	public String getCreate_org_name() {
		return create_org_name;
	}

	public void setCreate_org_name(String create_org_name) {
		this.create_org_name = create_org_name;
	}

	public String getReceive_org_id() {
		return receive_org_id;
	}

	public void setReceive_org_id(String receive_org_id) {
		this.receive_org_id = receive_org_id;
	}

	public String getReceive_org_name() {
		return receive_org_name;
	}

	public void setReceive_org_name(String receive_org_name) {
		this.receive_org_name = receive_org_name;
	}

	public String getTask_type() {
		return task_type;
	}

	public void setTask_type(String task_type) {
		this.task_type = task_type;
	}

	public String getTask_priority() {
		return task_priority;
	}

	public void setTask_priority(String task_priority) {
		this.task_priority = task_priority;
	}

	public String getTask_category() {
		return task_category;
	}

	public void setTask_category(String task_category) {
		this.task_category = task_category;
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

	public String getAdvance_org_id() {
		return advance_org_id;
	}

	public void setAdvance_org_id(String advance_org_id) {
		this.advance_org_id = advance_org_id;
	}

	public String getAdvance_org_name() {
		return advance_org_name;
	}

	public void setAdvance_org_name(String advance_org_name) {
		this.advance_org_name = advance_org_name;
	}

	public Date getAdvance_date() {
		return advance_date;
	}

	public void setAdvance_date(Date advance_date) {
		this.advance_date = advance_date;
	}
}
