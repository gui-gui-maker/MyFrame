package com.fwxm.scientific.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

import java.util.Date;

/*******************************************************************************
 * 
 * <p>
 *  
 * </p>
 * 
 * @ClassName InstructionRw
 * @JDK 1.7
 * @author CODER_V3.0
 * @date 2018-01-11 15:37:16
 */
@Entity
@Table(name = "TJY2_INSTRUCTION_RW")
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstructionRw implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private String id;//
	private String projectName;//项目名称
	private String tjy2InstructionInfoId;//指导书副表id
	private String type;//制订/修订
	private String requirements;//要求
	private String reviewMan;//标准审查人员
	private String auditMan;//审核人员
	private String reviewId;//标准审查人员id
	private String auditId;//审核人员id
	private String developContent;//制定内容
	private String commitmentsType;//协调专家支持或购买服务
	private String budgetMoney;//项目经费预算
	private Date projectStartDate;//项目开始时间
	private Date projectEndDate;//项目结束时间
	private Date projectAcceptanceDate;//项目验收时间
	private String projectHead;//项目负责人
	private String projectHeadId;//项目负责人
	private String projectCyMan;//项目参与人员
	private String projectCyManId;//项目参与人员id
	private String status;//状态
	private String rwNumber;//任务书编号
	private Date limit_start_date;//填写开始日期
	private Date limit_end_date;//填写结束日期
	private Date audit_date;//审核时间
	private Date review_date;//审查时间
	private String sign_id;//批准人id
	private String sign_man;//批准人
	private Date sign_date;//批准时间
	private String zr_id;//主任批准id
	private String zr_man;//主任批准
	private Date zr_date;//主任批准时间
	private String audit_opinion;//审核意见
	private String review_opinion;//审查意见
	private String sign_opinion;//批准意见
	private String zr_opinion;//主任批准意见
	private String kj_id;//科技委审核id
	private String kj_man;//科技委审核人
	private Date kj_date;//科技委审核时间
	private String kj_opinion;//科技委审核意见
	private String fzr_id;//副主任批准id
	private String fzr_man;//副主任批准
	private Date fzr_date;//副主任批准时间
	private String fzr_opinion;//副主任批准意见
	
	
	private String psh_id;//评审会id
	private String psh_man;//评审会人
	private String psh_opinion;//评审会意见
	private Date psh_date;//评审会时间
	private String isReturn;//是否退回；0：否；1：是
	
	
	@Column(name="PSH_DATE")
	public Date getPsh_date() {
		return psh_date;
	}
	public void setPsh_date(Date psh_date) {
		this.psh_date = psh_date;
	}
	@Column(name="PSH_ID")
	public String getPsh_id() {
		return psh_id;
	}
	public void setPsh_id(String psh_id) {
		this.psh_id = psh_id;
	}
	@Column(name="PSH_MAN")
	public String getPsh_man() {
		return psh_man;
	}
	public void setPsh_man(String psh_man) {
		this.psh_man = psh_man;
	}
	@Column(name="PSH_OPINION")
	public String getPsh_opinion() {
		return psh_opinion;
	}
	public void setPsh_opinion(String psh_opinion) {
		this.psh_opinion = psh_opinion;
	}
	@Column(name="IS_RETURN")
	public String getIsReturn() {
		return isReturn;
	}
	public void setIsReturn(String isReturn) {
		this.isReturn = isReturn;
	}
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	

	@Column(name="PROJECT_NAME")
	public String getProjectName(){
		return projectName;
	}
		
	public void setProjectName(String projectName){
		this.projectName=projectName;
	}

	@Column(name="TJY2_INSTRUCTION_INFO_ID")
	public String getTjy2InstructionInfoId(){
		return tjy2InstructionInfoId;
	}
		
	public void setTjy2InstructionInfoId(String tjy2InstructionInfoId){
		this.tjy2InstructionInfoId=tjy2InstructionInfoId;
	}

	@Column(name="TYPE")
	public String getType(){
		return type;
	}
		
	public void setType(String type){
		this.type=type;
	}

	@Column(name="REQUIREMENTS")
	public String getRequirements(){
		return requirements;
	}
		
	public void setRequirements(String requirements){
		this.requirements=requirements;
	}

	@Column(name="REVIEW_MAN")
	public String getReviewMan(){
		return reviewMan;
	}
		
	public void setReviewMan(String reviewMan){
		this.reviewMan=reviewMan;
	}

	@Column(name="AUDIT_MAN")
	public String getAuditMan(){
		return auditMan;
	}
		
	public void setAuditMan(String auditMan){
		this.auditMan=auditMan;
	}

	@Column(name="REVIEW_ID")
	public String getReviewId(){
		return reviewId;
	}
		
	public void setReviewId(String reviewId){
		this.reviewId=reviewId;
	}

	@Column(name="AUDIT_ID")
	public String getAuditId(){
		return auditId;
	}
		
	public void setAuditId(String auditId){
		this.auditId=auditId;
	}

	@Column(name="DEVELOP_CONTENT")
	public String getDevelopContent(){
		return developContent;
	}
		
	public void setDevelopContent(String developContent){
		this.developContent=developContent;
	}

	@Column(name="COMMITMENTS_TYPE")
	public String getCommitmentsType(){
		return commitmentsType;
	}
		
	public void setCommitmentsType(String commitmentsType){
		this.commitmentsType=commitmentsType;
	}

	@Column(name="BUDGET_MONEY")
	public String getBudgetMoney(){
		return budgetMoney;
	}
		
	public void setBudgetMoney(String budgetMoney){
		this.budgetMoney=budgetMoney;
	}

	@Column(name="PROJECT_START_DATE")
	public Date getProjectStartDate(){
		return projectStartDate;
	}
		
	public void setProjectStartDate(Date projectStartDate){
		this.projectStartDate=projectStartDate;
	}

	@Column(name="PROJECT_END_DATE")
	public Date getProjectEndDate(){
		return projectEndDate;
	}
		
	public void setProjectEndDate(Date projectEndDate){
		this.projectEndDate=projectEndDate;
	}

	@Column(name="PROJECT_ACCEPTANCE_DATE")
	public Date getProjectAcceptanceDate(){
		return projectAcceptanceDate;
	}
		
	public void setProjectAcceptanceDate(Date projectAcceptanceDate){
		this.projectAcceptanceDate=projectAcceptanceDate;
	}

	@Column(name="PROJECT_HEAD")
	public String getProjectHead(){
		return projectHead;
	}
		
	public void setProjectHead(String projectHead){
		this.projectHead=projectHead;
	}

	
	
	@Column(name="PROJECT_HEAD_ID")
	public String getProjectHeadId() {
		return projectHeadId;
	}

	public void setProjectHeadId(String projectHeadId) {
		this.projectHeadId = projectHeadId;
	}
	
	@Column(name="PROJECT_CY_MAN")
	public String getProjectCyMan() {
		return projectCyMan;
	}

	public void setProjectCyMan(String projectCyMan) {
		this.projectCyMan = projectCyMan;
	}
	@Column(name="PROJECT_CY_MAN_ID")
	public String getProjectCyManId() {
		return projectCyManId;
	}

	public void setProjectCyManId(String projectCyManId) {
		this.projectCyManId = projectCyManId;
	}
	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="RW_NUMBER")
	public String getRwNumber() {
		return rwNumber;
	}
	public void setRwNumber(String rwNumber) {
		this.rwNumber = rwNumber;
	}
	
	public Date getLimit_start_date() {
		return limit_start_date;
	}
	public void setLimit_start_date(Date limit_start_date) {
		this.limit_start_date = limit_start_date;
	}
	public Date getLimit_end_date() {
		return limit_end_date;
	}
	public void setLimit_end_date(Date limit_end_date) {
		this.limit_end_date = limit_end_date;
	}
	
	public Date getAudit_date() {
		return audit_date;
	}
	public void setAudit_date(Date audit_date) {
		this.audit_date = audit_date;
	}
	public Date getReview_date() {
		return review_date;
	}
	public void setReview_date(Date review_date) {
		this.review_date = review_date;
	}
	public String getSign_id() {
		return sign_id;
	}
	public void setSign_id(String sign_id) {
		this.sign_id = sign_id;
	}
	public String getSign_man() {
		return sign_man;
	}
	public void setSign_man(String sign_man) {
		this.sign_man = sign_man;
	}
	public Date getSign_date() {
		return sign_date;
	}
	public void setSign_date(Date sign_date) {
		this.sign_date = sign_date;
	}
	public String getZr_id() {
		return zr_id;
	}
	public void setZr_id(String zr_id) {
		this.zr_id = zr_id;
	}
	public String getZr_man() {
		return zr_man;
	}
	public void setZr_man(String zr_man) {
		this.zr_man = zr_man;
	}
	public Date getZr_date() {
		return zr_date;
	}
	public void setZr_date(Date zr_date) {
		this.zr_date = zr_date;
	}
	
	public String getAudit_opinion() {
		return audit_opinion;
	}
	public void setAudit_opinion(String audit_opinion) {
		this.audit_opinion = audit_opinion;
	}
	public String getReview_opinion() {
		return review_opinion;
	}
	public void setReview_opinion(String review_opinion) {
		this.review_opinion = review_opinion;
	}
	public String getSign_opinion() {
		return sign_opinion;
	}
	public void setSign_opinion(String sign_opinion) {
		this.sign_opinion = sign_opinion;
	}
	public String getZr_opinion() {
		return zr_opinion;
	}
	public void setZr_opinion(String zr_opinion) {
		this.zr_opinion = zr_opinion;
	}
	
	public String getKj_id() {
		return kj_id;
	}
	public void setKj_id(String kj_id) {
		this.kj_id = kj_id;
	}
	public String getKj_man() {
		return kj_man;
	}
	public void setKj_man(String kj_man) {
		this.kj_man = kj_man;
	}
	public Date getKj_date() {
		return kj_date;
	}
	public void setKj_date(Date kj_date) {
		this.kj_date = kj_date;
	}
	public String getKj_opinion() {
		return kj_opinion;
	}
	public void setKj_opinion(String kj_opinion) {
		this.kj_opinion = kj_opinion;
	}
	public String getFzr_id() {
		return fzr_id;
	}
	public void setFzr_id(String fzr_id) {
		this.fzr_id = fzr_id;
	}
	public String getFzr_man() {
		return fzr_man;
	}
	public void setFzr_man(String fzr_man) {
		this.fzr_man = fzr_man;
	}
	public Date getFzr_date() {
		return fzr_date;
	}
	public void setFzr_date(Date fzr_date) {
		this.fzr_date = fzr_date;
	}
	public String getFzr_opinion() {
		return fzr_opinion;
	}
	public void setFzr_opinion(String fzr_opinion) {
		this.fzr_opinion = fzr_opinion;
	}
	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "TJY2_INSTRUCTION_RW:ID="+id;
 
	}
}
