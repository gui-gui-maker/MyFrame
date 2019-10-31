package com.fwxm.scientific.bean;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

import java.util.Date;
import java.util.List;

/*******************************************************************************
 * 
 * <p>
 *  
 * </p>
 * 
 * @ClassName Instruction
 * @JDK 1.7
 * @author CODER_V3.0
 * @date 2018-01-10 13:42:45
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "TJY2_INSTRUCTION")
public class Instruction implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private String id;//
	private String instructionNumber;//??????????
	private Date createDate;//????????
	private String createMan;//??????
	private Date auditDate;//????????
	private String auditMan;//??????
	private Date signDate;//????????
	private String signMan;//??????
	private String createId;//??????id
	private String auditId;//??????id
	private String signId;//??????id
	private String status;//???? 0???? 9????
	private List<InstructionInfo> instructionInfo; 
	private String auditOpinion;
	private String signOpinion;
	private String isReturn;//是否退回；0：否；1：是
	
	@Column(name="IS_RETURN")
	public String getIsReturn() {
		return isReturn;
	}
	public void setIsReturn(String isReturn) {
		this.isReturn = isReturn;
	}
	
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="instruction")
	    public List<InstructionInfo> getInstructionInfo() {
			return instructionInfo;
		}
		public void setInstructionInfo(List<InstructionInfo> instructionInfo) {
			this.instructionInfo = instructionInfo;
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

	@Column(name="INSTRUCTION_NUMBER")
	public String getInstructionNumber(){
		return instructionNumber;
	}
		
	public void setInstructionNumber(String instructionNumber){
		this.instructionNumber=instructionNumber;
	}

	@Column(name="CREATE_DATE")
	public Date getCreateDate(){
		return createDate;
	}
		
	public void setCreateDate(Date createDate){
		this.createDate=createDate;
	}
	
	@Column(name="CREATE_MAN")
	public String getCreateMan(){
		return createMan;
	}
		
	public void setCreateMan(String createMan){
		this.createMan=createMan;
	}

	@Column(name="AUDIT_DATE")
	public Date getAuditDate(){
		return auditDate;
	}
		
	public void setAuditDate(Date auditDate){
		this.auditDate=auditDate;
	}

	@Column(name="AUDIT_MAN")
	public String getAuditMan(){
		return auditMan;
	}
		
	public void setAuditMan(String auditMan){
		this.auditMan=auditMan;
	}

	@Column(name="SIGN_DATE")
	public Date getSignDate(){
		return signDate;
	}
		
	public void setSignDate(Date signDate){
		this.signDate=signDate;
	}

	@Column(name="SIGN_MAN")
	public String getSignMan(){
		return signMan;
	}
		
	public void setSignMan(String signMan){
		this.signMan=signMan;
	}

	@Column(name="CREATE_ID")
	public String getCreateId(){
		return createId;
	}
		
	public void setCreateId(String createId){
		this.createId=createId;
	}

	@Column(name="AUDIT_ID")
	public String getAuditId(){
		return auditId;
	}
		
	public void setAuditId(String auditId){
		this.auditId=auditId;
	}

	@Column(name="SIGN_ID")
	public String getSignId(){
		return signId;
	}
		
	public void setSignId(String signId){
		this.signId=signId;
	}

	@Column(name="STATUS")
	public String getStatus(){
		return status;
	}
		
	public void setStatus(String status){
		this.status=status;
	}

	
	
	@Column(name="AUDIT_OPINION")
	public String getAuditOpinion() {
		return auditOpinion;
	}
	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}
	@Column(name="SIGN_OPINION")
	public String getSignOpinion() {
		return signOpinion;
	}
	public void setSignOpinion(String signOpinion) {
		this.signOpinion = signOpinion;
	}
	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "TJY2_INSTRUCTION:ID="+id;

	}
}
