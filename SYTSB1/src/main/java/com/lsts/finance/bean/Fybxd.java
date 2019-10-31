package com.lsts.finance.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_FYBXD")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Fybxd implements BaseEntity{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String abolishName;//作废处理人
	private String abolish;//作废状态

	private java.util.Date abolishTime; //作废时间
	
	private String identifier;//编号
	
	private String handleName;//处理人
	
	private String handleId;//处理人id
	
	private java.util.Date handleTime;//处理时间
	
	private String handleOPinion;//处理意见

    private String id;//id
    
    private String unitId;
    
    private String departmentId;//部门ID

    private java.util.Date bsDate;//报销日期
    
    private String accessory;//附件 张

    private String unit;//单位

    private String department;//部门

    private String unitLeader;//单位审核

    private String branchedPassage;//分管审核

    private String DepartmentManager;//部门审核

    private String peopleConcerned;//报销人

    private String wordFigure;//金额大写
    
    private String projectItemId1;//车牌ID1/科研ID1

    private String projectItemId2;

    private String projectItemId3;

    private String projectItemId4;
    
    private String projectItemName1;//车牌1/科研1

    private String projectItemName2;

    private String projectItemName3;

    private String projectItemName4;

    private String costItem1;//费用项目一

    private String costItem2;//费用项目二

    private String costItem3;//费用项目三

    private String costItem4;//费用项目四
    
    private String subItem1;//经济类型一

    private String subItem2;//${columnb.remarks}

    private String subItem3;//${columnb.remarks}

    private String subItem4;//${columnb.remarks}

    private String class1;//类别

    private String class2;//${columnb.remarks}

    private String class3;//${columnb.remarks}

    private String class4;//${columnb.remarks}

    private java.math.BigDecimal money1;//金额

    private java.math.BigDecimal money2;//${columnb.remarks}

    private java.math.BigDecimal money3;//${columnb.remarks}

    private java.math.BigDecimal money4;//${columnb.remarks}

    private java.math.BigDecimal total;//金额合计

    private String user;//登录用户

    private String userId;//登录用户id

    private String userDate;//当前登录日期

    private String status = "REGISTER";//状态
    
    private String fybxType;//费用报销类型,0:普通；1：车辆；2：科研 3：存货入库；4：存货出库
    
    private String bxQrId;//处理人ID报销确认
    private String bxQrName;//处理人报销确认
    private java.util.Date bxQrTime;//处理时间报销确认
    public void setId(String value){
        this.id = value;
    }
    public void setHandleTime(java.util.Date value){
        this.handleTime = value;
    }
    public void setAbolishTime(java.util.Date value){
        this.abolishTime = value;
    }
    public void setBsDate(java.util.Date value){
        this.bsDate = value;
    }
    public void setAccessory(String value){
        this.accessory = value;
    }
    public void setHandleName(String value){
        this.handleName = value;
    }
    public void setHandleId(String value){
        this.handleId = value;
    }
    public void setAbolish(String value){
        this.abolish = value;
    }
    public void setIdentifier(String value){
        this.identifier = value;
    }
    public void setHandleOPinion(String value){
        this.handleOPinion = value;
    }
    public void setUnit(String value){
        this.unit = value;
    }
    public void setDepartment(String value){
        this.department = value;
    }
    public void setDepartmentId(String value){
        this.departmentId = value;
    }
    public void setUnitId(String value){
        this.unitId = value;
    }
    public void setUnitLeader(String value){
        this.unitLeader = value;
    }
    public void setBranchedPassage(String value){
        this.branchedPassage = value;
    }
    public void setDepartmentManager(String value){
        this.DepartmentManager = value;
    }
    public void setPeopleConcerned(String value){
        this.peopleConcerned = value;
    }
    public void setWordFigure(String value){
        this.wordFigure = value;
    }
    public void setProjectItemId1(String value){
        this.projectItemId1 = value;
    }
    public void setProjectItemId2(String value){
        this.projectItemId2 = value;
    }
    public void setProjectItemId3(String value){
        this.projectItemId3 = value;
    }
    public void setProjectItemId4(String value){
        this.projectItemId4 = value;
    }
    public void setProjectItemName1(String value){
        this.projectItemName1 = value;
    }
    public void setProjectItemName2(String value){
        this.projectItemName2 = value;
    }
    public void setProjectItemName3(String value){
        this.projectItemName3 = value;
    }
    public void setProjectItemName4(String value){
        this.projectItemName4 = value;
    }
    public void setCostItem1(String value){
        this.costItem1 = value;
    }
    public void setCostItem2(String value){
        this.costItem2 = value;
    }
    public void setCostItem3(String value){
        this.costItem3 = value;
    }
    public void setCostItem4(String value){
        this.costItem4 = value;
    }
    public void setSubItem1(String value){
        this.subItem1 = value;
    }
    public void setSubItem2(String value){
        this.subItem2 = value;
    }
    public void setSubItem3(String value){
        this.subItem3 = value;
    }
    public void setSubItem4(String value){
        this.subItem4 = value;
    }
    public void setClass1(String value){
        this.class1 = value;
    }
    public void setClass2(String value){
        this.class2 = value;
    }
    public void setClass3(String value){
        this.class3 = value;
    }
    public void setClass4(String value){
        this.class4 = value;
    }
    public void setMoney1(java.math.BigDecimal value){
        this.money1 = value;
    }
    public void setMoney2(java.math.BigDecimal value){
        this.money2 = value;
    }
    public void setMoney3(java.math.BigDecimal value){
        this.money3 = value;
    }
    public void setMoney4(java.math.BigDecimal value){
        this.money4 = value;
    }
    public void setTotal(java.math.BigDecimal value){
        this.total = value;
    }
    public void setUser(String value){
        this.user = value;
    }
    public void setUserId(String value){
        this.userId = value;
    }
    public void setUserDate(String value){
        this.userDate = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    public void setFybxType(String value){
        this.fybxType = value;
    }
    public void setBxQrId(String value){
        this.bxQrId = value;
    }
    public void setBxQrName(String value){
        this.bxQrName = value;
    }
    public void setBxQrTime(java.util.Date value){
        this.bxQrTime = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true)
    public String getId(){
        return this.id;
    }
    @Column(name ="BS_DATE",unique=false,nullable=true,insertable=true,updatable=true)
    public java.util.Date getBsDate(){
        return this.bsDate;
    }
    @Column(name ="HANDLE_TIME",unique=false,nullable=true,insertable=true,updatable=true)
    public java.util.Date getHandleTime(){
        return this.handleTime;
    }
    @Column(name ="ABOLISH_TIME",unique=false,nullable=true,insertable=true,updatable=true)
    public java.util.Date getAbolishTime(){
        return this.abolishTime;
    }
    @Column(name ="ACCESSORY",unique=false,nullable=true,insertable=true,updatable=true)
    public String getAccessory(){
        return this.accessory;
    }
    @Column(name ="UNIT",unique=false,nullable=true,insertable=true,updatable=true)
    public String getUnit(){
        return this.unit;
    }
    @Column(name ="DEPARTMENT",unique=false,nullable=true,insertable=true,updatable=true)
    public String getDepartment(){
        return this.department;
    }
    @Column(name ="UNIT_LEADER",unique=false,nullable=true,insertable=true,updatable=true)
    public String getUnitLeader(){
        return this.unitLeader;
    }
    @Column(name ="BRANCHED_PASSAGE",unique=false,nullable=true,insertable=true,updatable=true)
    public String getBranchedPassage(){
        return this.branchedPassage;
    }
    @Column(name ="DEPARTMENT_MANAGER",unique=false,nullable=true,insertable=true,updatable=true)
    public String getDepartmentManager(){
        return this.DepartmentManager;
    }
    @Column(name ="PEOPLE_CONCERNED",unique=false,nullable=true,insertable=true,updatable=true)
    public String getPeopleConcerned(){
        return this.peopleConcerned;
    }
    @Column(name ="WORD_FIGURE",unique=false,nullable=true,insertable=true,updatable=true)
    public String getWordFigure(){
        return this.wordFigure;
    }
    @Column(name ="PROJECT_ITEM_ID1",unique=false,nullable=true,insertable=true,updatable=true)
    public String getProjectItemId1(){
        return this.projectItemId1;
    }
    @Column(name ="PROJECT_ITEM_ID2",unique=false,nullable=true,insertable=true,updatable=true)
    public String getProjectItemId2(){
        return this.projectItemId2;
    }
    @Column(name ="PROJECT_ITEM_ID3",unique=false,nullable=true,insertable=true,updatable=true)
    public String getProjectItemId3(){
        return this.projectItemId3;
    }
    @Column(name ="PROJECT_ITEM_ID4",unique=false,nullable=true,insertable=true,updatable=true)
    public String getProjectItemId4(){
        return this.projectItemId4;
    }
    @Column(name ="PROJECT_ITEM_NAME1",unique=false,nullable=true,insertable=true,updatable=true)
    public String getProjectItemName1(){
        return this.projectItemName1;
    }
    @Column(name ="PROJECT_ITEM_NAME2",unique=false,nullable=true,insertable=true,updatable=true)
    public String getProjectItemName2(){
        return this.projectItemName2;
    }
    @Column(name ="PROJECT_ITEM_NAME3",unique=false,nullable=true,insertable=true,updatable=true)
    public String getProjectItemName3(){
        return this.projectItemName3;
    }
    @Column(name ="PROJECT_ITEM_NAME4",unique=false,nullable=true,insertable=true,updatable=true)
    public String getProjectItemName4(){
        return this.projectItemName4;
    }
    @Column(name ="COST_ITEM1",unique=false,nullable=true,insertable=true,updatable=true)
    public String getCostItem1(){
        return this.costItem1;
    }
    @Column(name ="COST_ITEM2",unique=false,nullable=true,insertable=true,updatable=true)
    public String getCostItem2(){
        return this.costItem2;
    }
    @Column(name ="COST_ITEM3",unique=false,nullable=true,insertable=true,updatable=true)
    public String getCostItem3(){
        return this.costItem3;
    }
    @Column(name ="COST_ITEM4",unique=false,nullable=true,insertable=true,updatable=true)
    public String getCostItem4(){
        return this.costItem4;
    }
    @Column(name ="SUB_ITEM1",unique=false,nullable=true,insertable=true,updatable=true)
    public String getSubItem1(){
        return this.subItem1;
    }
    @Column(name ="SUB_ITEM2",unique=false,nullable=true,insertable=true,updatable=true)
    public String getSubItem2(){
        return this.subItem2;
    }
    @Column(name ="SUB_ITEM3",unique=false,nullable=true,insertable=true,updatable=true)
    public String getSubItem3(){
        return this.subItem3;
    }
    @Column(name ="SUB_ITEM4",unique=false,nullable=true,insertable=true,updatable=true)
    public String getSubItem4(){
        return this.subItem4;
    }
    @Column(name ="CLASS1",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClass1(){
        return this.class1;
    }
    @Column(name ="CLASS2",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClass2(){
        return this.class2;
    }
    @Column(name ="CLASS3",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClass3(){
        return this.class3;
    }
    @Column(name ="CLASS4",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClass4(){
        return this.class4;
    }
    @Column(name ="MONEY1",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getMoney1(){
        return this.money1;
    }
    @Column(name ="MONEY2",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getMoney2(){
        return this.money2;
    }
    @Column(name ="MONEY3",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getMoney3(){
        return this.money3;
    }
    @Column(name ="MONEY4",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getMoney4(){
        return this.money4;
    }
    @Column(name ="TOTAL",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getTotal(){
        return this.total;
    }
    @Column(name ="USER_",unique=false,nullable=true,insertable=true,updatable=true)
    public String getUser(){
        return this.user;
    }
    @Column(name ="USER_ID",unique=false,nullable=true,insertable=true,updatable=true)
    public String getUserId(){
        return this.userId;
    }
    @Column(name ="USER_DATE",unique=false,nullable=true,insertable=true,updatable=true)
    public String getUserDate(){
        return this.userDate;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="UNIT_ID",unique=false,nullable=true,insertable=true,updatable=true)
    public String getUnitId(){
            return this.unitId;
    }
    @Column(name ="DEPARTMENT_ID",unique=false,nullable=true,insertable=true,updatable=true)
    public String getDepartmentId(){
        return this.departmentId;
    }
    
    @Column(name ="HANDLE_ID",unique=false,nullable=true,insertable=true,updatable=true)
    public String getHandleId(){
        return this.handleId;
    }
    @Column(name ="HANDLE_NAME",unique=false,nullable=true,insertable=true,updatable=true)
    public String getHandleName(){
        return this.handleName;
    }
    @Column(name ="HANDLE_OPINION",unique=false,nullable=true,insertable=true,updatable=true)
    public String getHandleOPinion(){
        return this.handleOPinion;
    }
    @Column(name ="IDENTIFIER",unique=false,nullable=true,insertable=true,updatable=true)
    public String getIdentifier(){
        return this.identifier;
    }
    @Column(name ="ABOLISH",unique=false,nullable=true,insertable=true,updatable=true)
    public String getAbolish(){
        return this.abolish;
    }
    @Column(name ="ABOLISH_NAME",unique=false,nullable=true,insertable=true,updatable=true)
    public String getAbolishName(){
        return this.abolishName;
    }
    @Column(name ="FYBX_TYPE",unique=false,nullable=true,insertable=true,updatable=true)
    public String getFybxType(){
        return this.fybxType;
    }
    @Column(name ="BX_QR_TIME",unique=false,nullable=true,insertable=true,updatable=true)
    public java.util.Date getBxQrTime(){
        return this.bxQrTime;
    }
    @Column(name ="BX_QR_ID",unique=false,nullable=true,insertable=true,updatable=true)
    public String getBxQrId(){
        return this.bxQrId;
    }
    @Column(name ="BX_QR_NAME",unique=false,nullable=true,insertable=true,updatable=true)
    public String getBxQrName(){
        return this.bxQrName;
    }
    public void setAbolishName(String value){
        this.abolishName = value;
    }


} 
