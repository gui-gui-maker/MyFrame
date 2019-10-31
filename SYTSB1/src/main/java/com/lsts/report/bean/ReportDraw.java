package com.lsts.report.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;
import com.lsts.inspection.bean.InspectionInfo;
import com.scts.payment.bean.InspectionInfoDTO;

/**
 * 报告领取记录
 * ReportDraw entity. 
 * @author GaoYa
 * @date 2014-03-04 15:17:00
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "tzsb_report_draw")
public class ReportDraw implements BaseEntity{

	
	private static final long serialVersionUID = 1L;
	
	private String id;	//	ID
	private String pulldown_op;		// 领取人
	private String idcard;			// 领取人身份证号
	private Date   pulldown_time;	// 领取时间
	private String linkmode;	// 联系方式
	private String job_unit;	// 工作单位
	private String remark;		// 备注
	private String borrow_sn;	// 预借书流水号
	private String report_sn;	// 报告书编号
	private String mdy_user_id;		// 最后修改人ID
	private String mdy_user_name;	// 最后修改人姓名
	private Date   mdy_date;		// 最后修改日期
	private String data_status;		// 数据状态（0：正常  99：已删除）
	private String draw_sn; //批量领取编号
	
	private String evaluate;//评价
	private String sign_file;//签名文件（path）
	
	private List<InspectionInfoDTO>  inspectionInfoDTOList;
	

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public InspectionInfo inspectionInfo;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_inspection_info_id")
	public InspectionInfo getInspectionInfo() {
		return inspectionInfo;
	}
	public void setInspectionInfo(InspectionInfo inspectionInfo) {
		this.inspectionInfo = inspectionInfo;
	}
	public String getPulldown_op() {
		return pulldown_op;
	}
	public void setPulldown_op(String pulldown_op) {
		this.pulldown_op = pulldown_op;
	}
	
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getLinkmode() {
		return linkmode;
	}
	public void setLinkmode(String linkmode) {
		this.linkmode = linkmode;
	}
	public String getJob_unit() {
		return job_unit;
	}
	public void setJob_unit(String job_unit) {
		this.job_unit = job_unit;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getBorrow_sn() {
		return borrow_sn;
	}
	public void setBorrow_sn(String borrow_sn) {
		this.borrow_sn = borrow_sn;
	}	
	public Date getPulldown_time() {
		return pulldown_time;
	}
	public void setPulldown_time(Date pulldown_time) {
		this.pulldown_time = pulldown_time;
	}
	public String getReport_sn() {
		return report_sn;
	}
	public void setReport_sn(String report_sn) {
		this.report_sn = report_sn;
	}
	
	@SuppressWarnings("unchecked")
	public Map to_Map() {
        Map map = new HashMap();
        map.put("id", id);
        map.put("fk_inspection_info_id", inspectionInfo.getId());
        map.put("pulldown_op", pulldown_op);
        map.put("pulldown_time", pulldown_time);
        return map;
    }
	
	@Transient
	public List<InspectionInfoDTO> getInspectionInfoDTOList() {
		return inspectionInfoDTOList;
	}
	public void setInspectionInfoDTOList(
			List<InspectionInfoDTO> inspectionInfoDTOList) {
		this.inspectionInfoDTOList = inspectionInfoDTOList;
	}
	
	public String getData_status() {
		return data_status;
	}
	public void setData_status(String data_status) {
		this.data_status = data_status;
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
	public String getSign_file() {
		return sign_file;
	}
	public void setSign_file(String sign_file) {
		this.sign_file = sign_file;
	}
	public String getEvaluate() {
		return evaluate;
	}
	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	public String getDraw_sn() {
		return draw_sn;
	}
	public void setDraw_sn(String draw_sn) {
		this.draw_sn = draw_sn;
	}
	
	
	
	
} 
