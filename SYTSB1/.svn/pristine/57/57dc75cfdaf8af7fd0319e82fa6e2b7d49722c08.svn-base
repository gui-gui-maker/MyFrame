package com.fwxm.library.bean;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import com.khnt.core.crud.bean.BaseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*******************************************************************************
 * 
 * <p>
 *  
 * </p>
 * 
 * @ClassName Receive
 * @JDK 1.7
 * @author CODER_V3.0
 * @date 2018-10-23 10:57:26
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "LIB_RECEIVE")
public class Receive implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private String id;//
	private String applier;//申请人
	private Date applyTime;//申请时间
	private String receiveOrg;//领取部门（id）
	private String receiveOrgLeader;//部门负责人（id）
	private String applyReason;//申请理由
	private String approveBy;//审批人（id）
	private String approveSuggestion;//审批意见
	private String approveResult;//审批结果（0`不同意`1`同意）
	private Date approveTime;
	private String status;//0`未提交`1`提交`2`科研技术部审核通过`00`科研技术部审核不同意`99`全部领取`55`部分领取
	
	Set<Book> books = new HashSet<Book>();

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	
	public String getApplier() {
		return applier;
	}

	public void setApplier(String applier) {
		this.applier = applier;
	}
	
	@Transient
	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	@Column(name="RECEIVE_ORG")
	public String getReceiveOrg(){
		return receiveOrg;
	}
		
	public void setReceiveOrg(String receiveOrg){
		this.receiveOrg=receiveOrg;
	}

	@Column(name="RECEIVE_ORG_LEADER")
	public String getReceiveOrgLeader(){
		return receiveOrgLeader;
	}
		
	public void setReceiveOrgLeader(String receiveOrgLeader){
		this.receiveOrgLeader=receiveOrgLeader;
	}

	@Column(name="APPLY_REASON")
	public String getApplyReason(){
		return applyReason;
	}
		
	public void setApplyReason(String applyReason){
		this.applyReason=applyReason;
	}

	@Column(name="APPLY_TIME")
	public Date getApplyTime(){
		return applyTime;
	}
		
	public void setApplyTime(Date applyTime){
		this.applyTime=applyTime;
	}

	@Column(name="APPROVE_BY")
	public String getApproveBy(){
		return approveBy;
	}
		
	public void setApproveBy(String approveBy){
		this.approveBy=approveBy;
	}

	@Column(name="APPROVE_SUGGESTION")
	public String getApproveSuggestion(){
		return approveSuggestion;
	}
		
	public void setApproveSuggestion(String approveSuggestion){
		this.approveSuggestion=approveSuggestion;
	}

	@Column(name="APPROVE_RESULT")
	public String getApproveResult(){
		return approveResult;
	}
		
	public void setApproveResult(String approveResult){
		this.approveResult=approveResult;
	}

	
	@Column(name="STATUS")
	public String getStatus(){
		return status;
	}
		
	public void setStatus(String status){
		this.status=status;
	}

	@Column(name="approve_time")
	public Date getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "LIB_RECEIVE:ID="+id;

	}
}
