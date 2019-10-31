package com.lsts.equipment2.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_EQUIP_MAINTAIN")
public class EquipMaintain implements BaseEntity{
	 private String id;//${columnb.remarks}

	    private String fkDeviceId;//设备表id

	    private String deviceName;//设备名称

	    private String precision;//设备精度

	    private String specifications;//规格型号

	    private String deviceNum;//设备编号

	    private java.util.Date purchaseDate;//购进日期

	    private java.math.BigDecimal purchaseMoney;//设备原值

	    private java.math.BigDecimal maintainMoney;//预计维修金额

	    private String dispose;//处理结果

	    private String status;//状态(0 申请 1 验收)

	    private String numbers;//编号

	    private String equipmentMan;//设备管理员

	    private java.util.Date equipmentDate;//设备管理员签日期

	    private String directorMan;//主任

	    private java.util.Date directorDate;//主任签日期

	    private String webmasterMan;//站长

	    private java.util.Date webmasterDate;//站长签日期

	    private String leadershipMan;//院领导

	    private java.util.Date leadershipDate;//院领导签日期

	    private String agentMan;//经办人

	    private java.util.Date agentDate;//经办人签日期

	    private String failureAnalysis;//故障分析说明

	    private String checkoutOpinion;//检验室意见

	    private String securityOpinion;//保障部意见

	    private String maintenanceInstructions;//设备维修说明

	    private String acceptanceRecord;//验收记录

	    private String electricityExperiment;//通电实验

	    private String leadershipOpinion;//院领导意见

	    private String acceptanceConclusion;//验收结论
	    private String flowStatus;//流程状态
	    private String processSteps;//流程状态
	    private String applyDate;//
	    private String applyMan;//
	    private String dealMan;//
	    private String dealDate;//
	    
		@Id
	   	@GeneratedValue(generator = "system-uuid")
	   	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	   	public String getId() {
	   		return id;
	   	}
	   	public void setId(String id) {
	   		this.id = id;
	   	}
	    public void setFkDeviceId(String value){
	        this.fkDeviceId = value;
	    }
	    public void setDeviceName(String value){
	        this.deviceName = value;
	    }
	    public void setPrecision(String value){
	        this.precision = value;
	    }
	    public void setSpecifications(String value){
	        this.specifications = value;
	    }
	    public void setDeviceNum(String value){
	        this.deviceNum = value;
	    }
	    public void setPurchaseDate(java.util.Date value){
	        this.purchaseDate = value;
	    }
	    public void setPurchaseMoney(java.math.BigDecimal value){
	        this.purchaseMoney = value;
	    }
	    public void setMaintainMoney(java.math.BigDecimal value){
	        this.maintainMoney = value;
	    }
	    public void setDispose(String value){
	        this.dispose = value;
	    }
	    public void setStatus(String value){
	        this.status = value;
	    }
	    public void setNumbers(String value){
	        this.numbers = value;
	    }
	    public void setEquipmentMan(String value){
	        this.equipmentMan = value;
	    }
	    public void setEquipmentDate(java.util.Date value){
	        this.equipmentDate = value;
	    }
	    public void setDirectorMan(String value){
	        this.directorMan = value;
	    }
	    public void setDirectorDate(java.util.Date value){
	        this.directorDate = value;
	    }
	    public void setWebmasterMan(String value){
	        this.webmasterMan = value;
	    }
	    public void setWebmasterDate(java.util.Date value){
	        this.webmasterDate = value;
	    }
	    public void setLeadershipMan(String value){
	        this.leadershipMan = value;
	    }
	    public void setLeadershipDate(java.util.Date value){
	        this.leadershipDate = value;
	    }
	    public void setAgentMan(String value){
	        this.agentMan = value;
	    }
	    public void setAgentDate(java.util.Date value){
	        this.agentDate = value;
	    }
	    public void setFailureAnalysis(String value){
	        this.failureAnalysis = value;
	    }
	    public void setCheckoutOpinion(String value){
	        this.checkoutOpinion = value;
	    }
	    public void setSecurityOpinion(String value){
	        this.securityOpinion = value;
	    }
	    public void setMaintenanceInstructions(String value){
	        this.maintenanceInstructions = value;
	    }
	    public void setAcceptanceRecord(String value){
	        this.acceptanceRecord = value;
	    }
	    public void setElectricityExperiment(String value){
	        this.electricityExperiment = value;
	    }
	    public void setLeadershipOpinion(String value){
	        this.leadershipOpinion = value;
	    }
	    public void setAcceptanceConclusion(String value){
	        this.acceptanceConclusion = value;
	    }
	    @Column(name ="FK_DEVICE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
	    public String getFkDeviceId(){
	        return this.fkDeviceId;
	    }
	    @Column(name ="DEVICE_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=32)
	    public String getDeviceName(){
	        return this.deviceName;
	    }
	    @Column(name ="PRECISION",unique=false,nullable=true,insertable=true,updatable=true,length=32)
	    public String getPrecision(){
	        return this.precision;
	    }
	    @Column(name ="SPECIFICATIONS",unique=false,nullable=true,insertable=true,updatable=true,length=32)
	    public String getSpecifications(){
	        return this.specifications;
	    }
	    @Column(name ="DEVICE_NUM",unique=false,nullable=true,insertable=true,updatable=true,length=32)
	    public String getDeviceNum(){
	        return this.deviceNum;
	    }
	    @Column(name ="PURCHASE_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	    public java.util.Date getPurchaseDate(){
	        return this.purchaseDate;
	    }
	    @Column(name ="PURCHASE_MONEY",unique=false,nullable=true,insertable=true,updatable=true,length=22)
	    public java.math.BigDecimal getPurchaseMoney(){
	        return this.purchaseMoney;
	    }
	    @Column(name ="MAINTAIN_MONEY",unique=false,nullable=true,insertable=true,updatable=true,length=22)
	    public java.math.BigDecimal getMaintainMoney(){
	        return this.maintainMoney;
	    }
	    @Column(name ="DISPOSE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
	    public String getDispose(){
	        return this.dispose;
	    }
	    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=10)
	    public String getStatus(){
	        return this.status;
	    }
	    @Column(name ="NUMBERS",unique=false,nullable=true,insertable=true,updatable=true,length=32)
	    public String getNumbers(){
	        return this.numbers;
	    }
	    @Column(name ="EQUIPMENT_MAN",unique=false,nullable=true,insertable=true,updatable=true,length=32)
	    public String getEquipmentMan(){
	        return this.equipmentMan;
	    }
	    @Column(name ="EQUIPMENT_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	    public java.util.Date getEquipmentDate(){
	        return this.equipmentDate;
	    }
	    @Column(name ="DIRECTOR_MAN",unique=false,nullable=true,insertable=true,updatable=true,length=32)
	    public String getDirectorMan(){
	        return this.directorMan;
	    }
	    @Column(name ="DIRECTOR_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	    public java.util.Date getDirectorDate(){
	        return this.directorDate;
	    }
	    @Column(name ="WEBMASTER_MAN",unique=false,nullable=true,insertable=true,updatable=true,length=32)
	    public String getWebmasterMan(){
	        return this.webmasterMan;
	    }
	    @Column(name ="WEBMASTER_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	    public java.util.Date getWebmasterDate(){
	        return this.webmasterDate;
	    }
	    @Column(name ="LEADERSHIP_MAN",unique=false,nullable=true,insertable=true,updatable=true,length=32)
	    public String getLeadershipMan(){
	        return this.leadershipMan;
	    }
	    @Column(name ="LEADERSHIP_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	    public java.util.Date getLeadershipDate(){
	        return this.leadershipDate;
	    }
	    @Column(name ="AGENT_MAN",unique=false,nullable=true,insertable=true,updatable=true,length=32)
	    public String getAgentMan(){
	        return this.agentMan;
	    }
	    @Column(name ="AGENT_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	    public java.util.Date getAgentDate(){
	        return this.agentDate;
	    }
	    @Column(name ="FAILURE_ANALYSIS",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
	    public String getFailureAnalysis(){
	        return this.failureAnalysis;
	    }
	    @Column(name ="CHECKOUT_OPINION",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
	    public String getCheckoutOpinion(){
	        return this.checkoutOpinion;
	    }
	    @Column(name ="SECURITY_OPINION",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
	    public String getSecurityOpinion(){
	        return this.securityOpinion;
	    }
	    @Column(name ="MAINTENANCE_INSTRUCTIONS",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
	    public String getMaintenanceInstructions(){
	        return this.maintenanceInstructions;
	    }
	    @Column(name ="ACCEPTANCE_RECORD",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
	    public String getAcceptanceRecord(){
	        return this.acceptanceRecord;
	    }
	    @Column(name ="ELECTRICITY_EXPERIMENT",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
	    public String getElectricityExperiment(){
	        return this.electricityExperiment;
	    }
	    @Column(name ="LEADERSHIP_OPINION",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
	    public String getLeadershipOpinion(){
	        return this.leadershipOpinion;
	    }
	    @Column(name ="ACCEPTANCE_CONCLUSION",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
	    public String getAcceptanceConclusion(){
	        return this.acceptanceConclusion;
	    }
	    @Column(name ="FLOW_STATUS")
		public String getFlowStatus() {
			return flowStatus;
		}
		public void setFlowStatus(String flowStatus) {
			this.flowStatus = flowStatus;
		}
		  @Column(name ="PROCESS_STEPS")
		public String getProcessSteps() {
			return processSteps;
		}
		public void setProcessSteps(String processSteps) {
			this.processSteps = processSteps;
		}
		 @Column(name ="APPLY_DATE")
		public String getApplyDate() {
			return applyDate;
		}
		public void setApplyDate(String applyDate) {
			this.applyDate = applyDate;
		}
		 @Column(name ="APPLY_MAN")
		public String getApplyMan() {
			return applyMan;
		}
		public void setApplyMan(String applyMan) {
			this.applyMan = applyMan;
		}
		 @Column(name ="DEAL_MAN")
		public String getDealMan() {
			return dealMan;
		}
		public void setDealMan(String dealMan) {
			this.dealMan = dealMan;
		}
		 @Column(name ="DEAL_DATE")
		public String getDealDate() {
			return dealDate;
		}
		public void setDealDate(String dealDate) {
			this.dealDate = dealDate;
		}
	   
		



} 
