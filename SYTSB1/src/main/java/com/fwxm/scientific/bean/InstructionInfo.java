package com.fwxm.scientific.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @ClassName InstructionInfo
 * @JDK 1.7
 * @author CODER_V3.0
 * @date 2018-01-10 13:42:45
 */
@Entity
@Table(name = "TJY2_INSTRUCTION_INFO")
@JsonIgnoreProperties({"instruction"})
public class InstructionInfo implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private String id;//
	private String tjy2InstructionId;//指导书主键id
	private String projectNameId;//项目id
	private String projectName;//项目名称
	private String type;//制订/修订
	private String reason;//制修订理由
	private String requirements;//要求
	private String endDate;//完成时限
	private String forwardBm;//提出部门
	private String forwardBmId;//提出部门id
	private String forwardMan;//提出人id
	private String forwardManId;//提出人id
	private Instruction instruction;
	

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "tjy2_Instruction_Id")
	public Instruction getInstruction() {
		return instruction;
	}
	public void setInstruction(Instruction instruction) {
		this.instruction = instruction;
	}
	/*@Column(name="tjy2_Instruction_Id")
	public String getTjy2InstructionId() {
		return tjy2InstructionId;
	}
	public void setTjy2InstructionId(String tjy2InstructionId) {
		this.tjy2InstructionId = tjy2InstructionId;
	}*/
	
	
	@Column(name="PROJECT_NAME_ID")
	public String getProjectNameId() {
		return projectNameId;
	}
	public void setProjectNameId(String projectNameId) {
		this.projectNameId = projectNameId;
	}
	
	@Column(name="PROJECT_NAME")
	public String getProjectName(){
		return projectName;
	}
	public void setProjectName(String projectName){
		this.projectName=projectName;
	}

	@Column(name="TYPE")
	public String getType(){
		return type;
	}
		
	public void setType(String type){
		this.type=type;
	}

	@Column(name="REASON")
	public String getReason(){
		return reason;
	}
		
	public void setReason(String reason){
		this.reason=reason;
	}

	@Column(name="REQUIREMENTS")
	public String getRequirements(){
		return requirements;
	}
		
	public void setRequirements(String requirements){
		this.requirements=requirements;
	}

	@Column(name="END_DATE")
	public String getEndDate(){
		return endDate;
	}
		
	public void setEndDate(String endDate){
		this.endDate=endDate;
	}
	@Column(name="FORWARD_BM")
	public String getForwardBm() {
		return forwardBm;
	}

	public void setForwardBm(String forwardBm) {
		this.forwardBm = forwardBm;
	}
	@Column(name="FORWARD_BM_ID")
	public String getForwardBmId() {
		return forwardBmId;
	}

	public void setForwardBmId(String forwardBmId) {
		this.forwardBmId = forwardBmId;
	}
	@Column(name="FORWARD_MAN")
	public String getForwardMan() {
		return forwardMan;
	}

	public void setForwardMan(String forwardMan) {
		this.forwardMan = forwardMan;
	}
	@Column(name="FORWARD_MAN_ID")
	public String getForwardManId() {
		return forwardManId;
	}

	public void setForwardManId(String forwardManId) {
		this.forwardManId = forwardManId;
	}
	
	

	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "TJY2_INSTRUCTION_INFO:ID="+id;

	}
}
