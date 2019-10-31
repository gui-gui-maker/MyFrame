package com.khnt.bpm.ext.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 流程环节审核、处理结果。
 */
@Entity
@Table(name = "FLOW_OPINION")
public class Opinion implements BaseEntity {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;// 意见名称
	private String serviceId;// 业务ID
	private String activityId;// 任务环节
	private String workitem;// 工作项
	private String result;// 结果是否通过
	private String opinion;// 意见
	private String remark;// 备注
	private String signerName;// 意见签写人
	private String signerId;// 意见签写人ID
	private Date signDate;// 意见签写时间
	private String lock;// 是否锁定，在未锁定时，用户可随时修改，一旦锁定后不能修改，只能重新填写。在流程审批中，环节一提交，意见就被锁定。

	private String signature;// 电子签名信息
	private String seal;// 电子印章信息
	private String opinionSignature;// 手写意见电子签名信息

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "SERVICE_ID")
	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	@Column(name = "WORKITEM")
	public String getWorkitem() {
		return workitem;
	}

	public void setWorkitem(String workitem) {
		this.workitem = workitem;
	}

	@Column(name = "ACTIVITY_ID")
	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	@Column(name = "OPINION")
	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "SIGN_DATE")
	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	@Column(name = "SIGNER_NAME")
	public String getSignerName() {
		return signerName;
	}

	public void setSignerName(String signerName) {
		this.signerName = signerName;
	}

	@Column(name = "SIGNER_ID")
	public String getSignerId() {
		return signerId;
	}

	public void setSignerId(String signerId) {
		this.signerId = signerId;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "RESULT")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Column(name = "lock_status")
	public String getLock() {
		return lock;
	}

	public void setLock(String lock) {
		this.lock = lock;
	}

	@Column(name = "signature")
	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Column(name = "seal")
	public String getSeal() {
		return seal;
	}

	public void setSeal(String seal) {
		this.seal = seal;
	}

	@Column(name = "opinion_signature")
	public String getOpinionSignature() {
		return opinionSignature;
	}

	public void setOpinionSignature(String opinionSignature) {
		this.opinionSignature = opinionSignature;
	}

	public String toString() {
		return "工作流处理环节意见，环节[" + this.activityId + "]，签署人[" + this.signerName + "]，意见内容:[" + this.opinion + "]";
	}
}
