package com.lsts.qualitymanage.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_QUALITY_SGCJJYBG")
@JsonIgnoreProperties(ignoreUnknown = true)
public class QualitySgcjjybg implements BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;//${columnb.remarks}

    private String registrant;//登记人

    private String registrantId;//登记人id

    private java.util.Date registrantDate;//登记人时间

    private String identifier;//编号

    private String status;//状态

    private String userName;//用户名称

    private java.util.Date bjwtsj;//报检/委托时间

    private String reason;//手工出具报告/证书的原因

    private String num;//序号

    private String equipmentName;//设备名称

    private String njts;//拟检台数

    private String equipmentType;//设备种类

    private String equipmentVariety;//设备品种

    private String equipmentCategory;//检验类别

    private String fwbjhbg;//服务部计划的报告/证书编号

    private String remarks;//备注

    private String num2;//${columnb.remarks}

    private String equipmentName2;//${columnb.remarks}

    private String njts2;//${columnb.remarks}

    private String equipmentType2;//${columnb.remarks}

    private String equipmentVariety2;//${columnb.remarks}

    private String equipmentCategory2;//${columnb.remarks}

    private String fwbjhbg2;//${columnb.remarks}

    private String remarks2;//${columnb.remarks}

    private String num3;//${columnb.remarks}

    private String equipmentName3;//${columnb.remarks}

    private String njts3;//${columnb.remarks}

    private String equipmentType3;//${columnb.remarks}

    private String equipmentVariety3;//${columnb.remarks}

    private String equipmentCategory3;//${columnb.remarks}

    private String fwbjhbg3;//${columnb.remarks}

    private String remarks3;//${columnb.remarks}

    private String num4;//${columnb.remarks}

    private String equipmentName4;//${columnb.remarks}

    private String njts4;//${columnb.remarks}

    private String equipmentType4;//${columnb.remarks}

    private String equipmentVariety4;//${columnb.remarks}

    private String equipmentCategory4;//${columnb.remarks}

    private String fwbjhbg4;//${columnb.remarks}

    private String remarks4;//${columnb.remarks}

    private String num5;//${columnb.remarks}

    private String equipmentName5;//${columnb.remarks}

    private String njts5;//${columnb.remarks}

    private String equipmentType5;//${columnb.remarks}

    private String equipmentVariety5;//${columnb.remarks}

    private String equipmentCategory5;//${columnb.remarks}

    private String fwbjhbg5;//${columnb.remarks}

    private String remarks5;//${columnb.remarks}

    private String num6;//${columnb.remarks}

    private String equipmentName6;//${columnb.remarks}

    private String njts6;//${columnb.remarks}

    private String equipmentType6;//${columnb.remarks}

    private String equipmentVariety6;//${columnb.remarks}

    private String equipmentCategory6;//${columnb.remarks}

    private String fwbjhbg6;//${columnb.remarks}

    private String remarks6;//${columnb.remarks}

    private String num7;//${columnb.remarks}

    private String equipmentName7;//${columnb.remarks}

    private String njts7;//${columnb.remarks}

    private String equipmentType7;//${columnb.remarks}

    private String equipmentVariety7;//${columnb.remarks}

    private String equipmentCategory7;//${columnb.remarks}

    private String fwbjhbg7;//${columnb.remarks}

    private String remarks7;//${columnb.remarks}

    private String department;//申请部门

    private String departmentId;//申请部门id

    private String applyName;//申请人

    private String applyManId;//申请人id

    private java.util.Date applyTime;//申请日期

    private String departmentMan;//部门负责人

    private java.util.Date departmentManDate;//日期

    private String jyrjfzr;//检验软件责任人确认

    private java.util.Date jyrjfzrDate;//检验软件责任人确认时间

    private String ywfwbjbr;//业务服务部经办人签字

    private java.util.Date ywfwbjbrDate;//业务服务部经办人签字时间

    private String departmentYj;//部门意见

    private String jyrjfzrYj;//检验软件责任人意见

    private String ywfwbjbrYj;//业务服务部经办人意见
    
    private String statusa;//0是法定1是委托
    
    private String contractNumber;//合同编号
    private String contractNumber2;
    private String contractNumber3;
    private String contractNumber4;
    private String contractNumber5;
    private String contractNumber6;
	private String contractNumber7;
    
    private String zlshman;//质量监督管理部审核人
    private java.util.Date zlshtime;//质量监督管理部审核时间
    private String zlshyj;//质量监督管理部意见
    
    private String rwdId1;//任务单id
    private String rwdId2;//任务单id
    private String rwdId3;//任务单id
    private String rwdId4;//任务单id
    private String rwdId5;//任务单id
    private String rwdId6;//任务单id
    private String rwdId7;//任务单id
    private String rwdSn1;//任务单name
    private String rwdSn2;//任务单name
    private String rwdSn3;//任务单name
    private String rwdSn4;//任务单name
    private String rwdSn5;//任务单name
    private String rwdSn6;//任务单name
    private String rwdSn7;//任务单name
    
    
    @Column(name = "RWD_ID1")
	public String getRwdId1() {
		return rwdId1;
	}
	public void setRwdId1(String rwdId1) {
		this.rwdId1 = rwdId1;
	}
	@Column(name = "RWD_ID2")
	public String getRwdId2() {
		return rwdId2;
	}
	public void setRwdId2(String rwdId2) {
		this.rwdId2 = rwdId2;
	}
	@Column(name = "RWD_ID3")
	public String getRwdId3() {
		return rwdId3;
	}
	public void setRwdId3(String rwdId3) {
		this.rwdId3 = rwdId3;
	}
	@Column(name = "RWD_ID4")
	public String getRwdId4() {
		return rwdId4;
	}
	public void setRwdId4(String rwdId4) {
		this.rwdId4 = rwdId4;
	}
	@Column(name = "RWD_ID5")
	public String getRwdId5() {
		return rwdId5;
	}
	public void setRwdId5(String rwdId5) {
		this.rwdId5 = rwdId5;
	}
	@Column(name = "RWD_ID6")
	public String getRwdId6() {
		return rwdId6;
	}
	public void setRwdId6(String rwdId6) {
		this.rwdId6 = rwdId6;
	}
	@Column(name = "RWD_ID7")
	public String getRwdId7() {
		return rwdId7;
	}
	public void setRwdId7(String rwdId7) {
		this.rwdId7 = rwdId7;
	}
	@Column(name = "RWD_SN1")
	public String getRwdSn1() {
		return rwdSn1;
	}
	public void setRwdSn1(String rwdSn1) {
		this.rwdSn1 = rwdSn1;
	}
	@Column(name = "RWD_SN2")
	public String getRwdSn2() {
		return rwdSn2;
	}
	public void setRwdSn2(String rwdSn2) {
		this.rwdSn2 = rwdSn2;
	}
	@Column(name = "RWD_SN3")
	public String getRwdSn3() {
		return rwdSn3;
	}
	public void setRwdSn3(String rwdSn3) {
		this.rwdSn3 = rwdSn3;
	}
	@Column(name = "RWD_SN4")
	public String getRwdSn4() {
		return rwdSn4;
	}
	public void setRwdSn4(String rwdSn4) {
		this.rwdSn4 = rwdSn4;
	}
	@Column(name = "RWD_SN5")
	public String getRwdSn5() {
		return rwdSn5;
	}
	public void setRwdSn5(String rwdSn5) {
		this.rwdSn5 = rwdSn5;
	}
	@Column(name = "RWD_SN6")
	public String getRwdSn6() {
		return rwdSn6;
	}
	public void setRwdSn6(String rwdSn6) {
		this.rwdSn6 = rwdSn6;
	}
	@Column(name = "RWD_SN7")
	public String getRwdSn7() {
		return rwdSn7;
	}
	public void setRwdSn7(String rwdSn7) {
		this.rwdSn7 = rwdSn7;
	}
	public void setZlshman(String zlshman) {
		this.zlshman = zlshman;
	}
	public void setZlshtime(java.util.Date zlshtime) {
		this.zlshtime = zlshtime;
	}
	public void setZlshyj(String zlshyj) {
		this.zlshyj = zlshyj;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	public void setContractNumber2(String contractNumber2) {
		this.contractNumber2 = contractNumber2;
	}
	public void setContractNumber3(String contractNumber3) {
		this.contractNumber3 = contractNumber3;
	}
	public void setContractNumber4(String contractNumber4) {
		this.contractNumber4 = contractNumber4;
	}
	public void setContractNumber5(String contractNumber5) {
		this.contractNumber5 = contractNumber5;
	}
	public void setContractNumber6(String contractNumber6) {
		this.contractNumber6 = contractNumber6;
	}
	public void setContractNumber7(String contractNumber7) {
		this.contractNumber7 = contractNumber7;
	}
	public void setStatusa(String statusa) {
		this.statusa = statusa;
	}
	public void setId(String value){
        this.id = value;
    }
    public void setRegistrant(String value){
        this.registrant = value;
    }
    public void setRegistrantId(String value){
        this.registrantId = value;
    }
    public void setRegistrantDate(java.util.Date value){
        this.registrantDate = value;
    }
    public void setIdentifier(String value){
        this.identifier = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    public void setUserName(String value){
        this.userName = value;
    }
    public void setBjwtsj(java.util.Date value){
        this.bjwtsj = value;
    }
    public void setReason(String value){
        this.reason = value;
    }
    public void setNum(String value){
        this.num = value;
    }
    public void setEquipmentName(String value){
        this.equipmentName = value;
    }
    public void setNjts(String value){
        this.njts = value;
    }
    public void setEquipmentType(String value){
        this.equipmentType = value;
    }
    public void setEquipmentVariety(String value){
        this.equipmentVariety = value;
    }
    public void setEquipmentCategory(String value){
        this.equipmentCategory = value;
    }
    public void setFwbjhbg(String value){
        this.fwbjhbg = value;
    }
    public void setRemarks(String value){
        this.remarks = value;
    }
    public void setNum2(String value){
        this.num2 = value;
    }
    public void setEquipmentName2(String value){
        this.equipmentName2 = value;
    }
    public void setNjts2(String value){
        this.njts2 = value;
    }
    public void setEquipmentType2(String value){
        this.equipmentType2 = value;
    }
    public void setEquipmentVariety2(String value){
        this.equipmentVariety2 = value;
    }
    public void setEquipmentCategory2(String value){
        this.equipmentCategory2 = value;
    }
    public void setFwbjhbg2(String value){
        this.fwbjhbg2 = value;
    }
    public void setRemarks2(String value){
        this.remarks2 = value;
    }
    public void setNum3(String value){
        this.num3 = value;
    }
    public void setEquipmentName3(String value){
        this.equipmentName3 = value;
    }
    public void setNjts3(String value){
        this.njts3 = value;
    }
    public void setEquipmentType3(String value){
        this.equipmentType3 = value;
    }
    public void setEquipmentVariety3(String value){
        this.equipmentVariety3 = value;
    }
    public void setEquipmentCategory3(String value){
        this.equipmentCategory3 = value;
    }
    public void setFwbjhbg3(String value){
        this.fwbjhbg3 = value;
    }
    public void setRemarks3(String value){
        this.remarks3 = value;
    }
    public void setNum4(String value){
        this.num4 = value;
    }
    public void setEquipmentName4(String value){
        this.equipmentName4 = value;
    }
    public void setNjts4(String value){
        this.njts4 = value;
    }
    public void setEquipmentType4(String value){
        this.equipmentType4 = value;
    }
    public void setEquipmentVariety4(String value){
        this.equipmentVariety4 = value;
    }
    public void setEquipmentCategory4(String value){
        this.equipmentCategory4 = value;
    }
    public void setFwbjhbg4(String value){
        this.fwbjhbg4 = value;
    }
    public void setRemarks4(String value){
        this.remarks4 = value;
    }
    public void setNum5(String value){
        this.num5 = value;
    }
    public void setEquipmentName5(String value){
        this.equipmentName5 = value;
    }
    public void setNjts5(String value){
        this.njts5 = value;
    }
    public void setEquipmentType5(String value){
        this.equipmentType5 = value;
    }
    public void setEquipmentVariety5(String value){
        this.equipmentVariety5 = value;
    }
    public void setEquipmentCategory5(String value){
        this.equipmentCategory5 = value;
    }
    public void setFwbjhbg5(String value){
        this.fwbjhbg5 = value;
    }
    public void setRemarks5(String value){
        this.remarks5 = value;
    }
    public void setNum6(String value){
        this.num6 = value;
    }
    public void setEquipmentName6(String value){
        this.equipmentName6 = value;
    }
    public void setNjts6(String value){
        this.njts6 = value;
    }
    public void setEquipmentType6(String value){
        this.equipmentType6 = value;
    }
    public void setEquipmentVariety6(String value){
        this.equipmentVariety6 = value;
    }
    public void setEquipmentCategory6(String value){
        this.equipmentCategory6 = value;
    }
    public void setFwbjhbg6(String value){
        this.fwbjhbg6 = value;
    }
    public void setRemarks6(String value){
        this.remarks6 = value;
    }
    public void setNum7(String value){
        this.num7 = value;
    }
    public void setEquipmentName7(String value){
        this.equipmentName7 = value;
    }
    public void setNjts7(String value){
        this.njts7 = value;
    }
    public void setEquipmentType7(String value){
        this.equipmentType7 = value;
    }
    public void setEquipmentVariety7(String value){
        this.equipmentVariety7 = value;
    }
    public void setEquipmentCategory7(String value){
        this.equipmentCategory7 = value;
    }
    public void setFwbjhbg7(String value){
        this.fwbjhbg7 = value;
    }
    public void setRemarks7(String value){
        this.remarks7 = value;
    }
    public void setDepartment(String value){
        this.department = value;
    }
    public void setDepartmentId(String value){
        this.departmentId = value;
    }
    public void setApplyName(String value){
        this.applyName = value;
    }
    public void setApplyManId(String value){
        this.applyManId = value;
    }
    public void setApplyTime(java.util.Date value){
        this.applyTime = value;
    }
    public void setDepartmentMan(String value){
        this.departmentMan = value;
    }
    public void setDepartmentManDate(java.util.Date value){
        this.departmentManDate = value;
    }
    public void setJyrjfzr(String value){
        this.jyrjfzr = value;
    }
    public void setJyrjfzrDate(java.util.Date value){
        this.jyrjfzrDate = value;
    }
    public void setYwfwbjbr(String value){
        this.ywfwbjbr = value;
    }
    public void setYwfwbjbrDate(java.util.Date value){
        this.ywfwbjbrDate = value;
    }
    public void setDepartmentYj(String value){
        this.departmentYj = value;
    }
    public void setJyrjfzrYj(String value){
        this.jyrjfzrYj = value;
    }
    public void setYwfwbjbrYj(String value){
        this.ywfwbjbrYj = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="REGISTRANT",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getRegistrant(){
        return this.registrant;
    }
    @Column(name ="REGISTRANT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getRegistrantId(){
        return this.registrantId;
    }
    @Column(name ="REGISTRANT_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getRegistrantDate(){
        return this.registrantDate;
    }
    @Column(name ="IDENTIFIER",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getIdentifier(){
        return this.identifier;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="USER_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getUserName(){
        return this.userName;
    }
    @Column(name ="BJWTSJ",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getBjwtsj(){
        return this.bjwtsj;
    }
    @Column(name ="REASON",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getReason(){
        return this.reason;
    }
    @Column(name ="NUM",unique=false,nullable=true,insertable=true,updatable=true,length=10)
    public String getNum(){
        return this.num;
    }
    @Column(name ="EQUIPMENT_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentName(){
        return this.equipmentName;
    }
    @Column(name ="NJTS",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getNjts(){
        return this.njts;
    }
    @Column(name ="EQUIPMENT_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentType(){
        return this.equipmentType;
    }
    @Column(name ="EQUIPMENT_VARIETY",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentVariety(){
        return this.equipmentVariety;
    }
    @Column(name ="EQUIPMENT_CATEGORY",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentCategory(){
        return this.equipmentCategory;
    }
    @Column(name ="FWBJHBG",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getFwbjhbg(){
        return this.fwbjhbg;
    }
    @Column(name ="REMARKS",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getRemarks(){
        return this.remarks;
    }
    @Column(name ="NUM2",unique=false,nullable=true,insertable=true,updatable=true,length=10)
    public String getNum2(){
        return this.num2;
    }
    @Column(name ="EQUIPMENT_NAME2",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentName2(){
        return this.equipmentName2;
    }
    @Column(name ="NJTS2",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getNjts2(){
        return this.njts2;
    }
    @Column(name ="EQUIPMENT_TYPE2",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentType2(){
        return this.equipmentType2;
    }
    @Column(name ="EQUIPMENT_VARIETY2",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentVariety2(){
        return this.equipmentVariety2;
    }
    @Column(name ="EQUIPMENT_CATEGORY2",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentCategory2(){
        return this.equipmentCategory2;
    }
    @Column(name ="FWBJHBG2",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getFwbjhbg2(){
        return this.fwbjhbg2;
    }
    @Column(name ="REMARKS2",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getRemarks2(){
        return this.remarks2;
    }
    @Column(name ="NUM3",unique=false,nullable=true,insertable=true,updatable=true,length=10)
    public String getNum3(){
        return this.num3;
    }
    @Column(name ="EQUIPMENT_NAME3",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentName3(){
        return this.equipmentName3;
    }
    @Column(name ="NJTS3",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getNjts3(){
        return this.njts3;
    }
    @Column(name ="EQUIPMENT_TYPE3",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentType3(){
        return this.equipmentType3;
    }
    @Column(name ="EQUIPMENT_VARIETY3",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentVariety3(){
        return this.equipmentVariety3;
    }
    @Column(name ="EQUIPMENT_CATEGORY3",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentCategory3(){
        return this.equipmentCategory3;
    }
    @Column(name ="FWBJHBG3",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getFwbjhbg3(){
        return this.fwbjhbg3;
    }
    @Column(name ="REMARKS3",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getRemarks3(){
        return this.remarks3;
    }
    @Column(name ="NUM4",unique=false,nullable=true,insertable=true,updatable=true,length=10)
    public String getNum4(){
        return this.num4;
    }
    @Column(name ="EQUIPMENT_NAME4",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentName4(){
        return this.equipmentName4;
    }
    @Column(name ="NJTS4",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getNjts4(){
        return this.njts4;
    }
    @Column(name ="EQUIPMENT_TYPE4",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentType4(){
        return this.equipmentType4;
    }
    @Column(name ="EQUIPMENT_VARIETY4",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentVariety4(){
        return this.equipmentVariety4;
    }
    @Column(name ="EQUIPMENT_CATEGORY4",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentCategory4(){
        return this.equipmentCategory4;
    }
    @Column(name ="FWBJHBG4",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getFwbjhbg4(){
        return this.fwbjhbg4;
    }
    @Column(name ="REMARKS4",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getRemarks4(){
        return this.remarks4;
    }
    @Column(name ="NUM5",unique=false,nullable=true,insertable=true,updatable=true,length=10)
    public String getNum5(){
        return this.num5;
    }
    @Column(name ="EQUIPMENT_NAME5",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentName5(){
        return this.equipmentName5;
    }
    @Column(name ="NJTS5",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getNjts5(){
        return this.njts5;
    }
    @Column(name ="EQUIPMENT_TYPE5",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentType5(){
        return this.equipmentType5;
    }
    @Column(name ="EQUIPMENT_VARIETY5",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentVariety5(){
        return this.equipmentVariety5;
    }
    @Column(name ="EQUIPMENT_CATEGORY5",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentCategory5(){
        return this.equipmentCategory5;
    }
    @Column(name ="FWBJHBG5",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getFwbjhbg5(){
        return this.fwbjhbg5;
    }
    @Column(name ="REMARKS5",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getRemarks5(){
        return this.remarks5;
    }
    @Column(name ="NUM6",unique=false,nullable=true,insertable=true,updatable=true,length=10)
    public String getNum6(){
        return this.num6;
    }
    @Column(name ="EQUIPMENT_NAME6",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentName6(){
        return this.equipmentName6;
    }
    @Column(name ="NJTS6",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getNjts6(){
        return this.njts6;
    }
    @Column(name ="EQUIPMENT_TYPE6",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentType6(){
        return this.equipmentType6;
    }
    @Column(name ="EQUIPMENT_VARIETY6",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentVariety6(){
        return this.equipmentVariety6;
    }
    @Column(name ="EQUIPMENT_CATEGORY6",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentCategory6(){
        return this.equipmentCategory6;
    }
    @Column(name ="FWBJHBG6",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getFwbjhbg6(){
        return this.fwbjhbg6;
    }
    @Column(name ="REMARKS6",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getRemarks6(){
        return this.remarks6;
    }
    @Column(name ="NUM7",unique=false,nullable=true,insertable=true,updatable=true,length=10)
    public String getNum7(){
        return this.num7;
    }
    @Column(name ="EQUIPMENT_NAME7",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentName7(){
        return this.equipmentName7;
    }
    @Column(name ="NJTS7",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getNjts7(){
        return this.njts7;
    }
    @Column(name ="EQUIPMENT_TYPE7",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentType7(){
        return this.equipmentType7;
    }
    @Column(name ="EQUIPMENT_VARIETY7",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentVariety7(){
        return this.equipmentVariety7;
    }
    @Column(name ="EQUIPMENT_CATEGORY7",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getEquipmentCategory7(){
        return this.equipmentCategory7;
    }
    @Column(name ="FWBJHBG7",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getFwbjhbg7(){
        return this.fwbjhbg7;
    }
    @Column(name ="REMARKS7",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getRemarks7(){
        return this.remarks7;
    }
    @Column(name ="DEPARTMENT",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getDepartment(){
        return this.department;
    }
    @Column(name ="DEPARTMENT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getDepartmentId(){
        return this.departmentId;
    }
    @Column(name ="APPLY_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getApplyName(){
        return this.applyName;
    }
    @Column(name ="APPLY_MAN_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getApplyManId(){
        return this.applyManId;
    }
    @Column(name ="APPLY_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getApplyTime(){
        return this.applyTime;
    }
    @Column(name ="DEPARTMENT_MAN",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getDepartmentMan(){
        return this.departmentMan;
    }
    @Column(name ="DEPARTMENT_MAN_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getDepartmentManDate(){
        return this.departmentManDate;
    }
    @Column(name ="JYRJFZR",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getJyrjfzr(){
        return this.jyrjfzr;
    }
    @Column(name ="JYRJFZR_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getJyrjfzrDate(){
        return this.jyrjfzrDate;
    }
    @Column(name ="YWFWBJBR",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getYwfwbjbr(){
        return this.ywfwbjbr;
    }
    @Column(name ="YWFWBJBR_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getYwfwbjbrDate(){
        return this.ywfwbjbrDate;
    }
    @Column(name ="DEPARTMENT_YJ",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getDepartmentYj(){
        return this.departmentYj;
    }
    @Column(name ="JYRJFZR_YJ",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getJyrjfzrYj(){
        return this.jyrjfzrYj;
    }
    @Column(name ="YWFWBJBR_YJ",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getYwfwbjbrYj(){
        return this.ywfwbjbrYj;
    }
    @Column(name ="STATUSA",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getStatusa() {
		return statusa;
	}
    @Column(name ="CONTRACT_NUMBER",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getContractNumber() {
		return contractNumber;
	}
    @Column(name ="CONTRACT_NUMBER2",unique=false,nullable=true,insertable=true,updatable=true,length=200)
	public String getContractNumber2() {
		return contractNumber2;
	}
    @Column(name ="CONTRACT_NUMBER3",unique=false,nullable=true,insertable=true,updatable=true,length=200)
	public String getContractNumber3() {
		return contractNumber3;
	}
    @Column(name ="CONTRACT_NUMBER4",unique=false,nullable=true,insertable=true,updatable=true,length=200)
	public String getContractNumber4() {
		return contractNumber4;
	}
    @Column(name ="CONTRACT_NUMBER5",unique=false,nullable=true,insertable=true,updatable=true,length=200)
	public String getContractNumber5() {
		return contractNumber5;
	}
    @Column(name ="CONTRACT_NUMBER6",unique=false,nullable=true,insertable=true,updatable=true,length=200)
	public String getContractNumber6() {
		return contractNumber6;
	}
    @Column(name ="CONTRACT_NUMBER7",unique=false,nullable=true,insertable=true,updatable=true,length=200)
	public String getContractNumber7() {
		return contractNumber7;
	}
    @Column(name ="ZLSHMAN",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getZlshman() {
		return zlshman;
	}
    @Column(name ="ZLSHTIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getZlshtime() {
    	return zlshtime;
    }
    @Column(name ="ZLSHYJ",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getZlshyj() {
    	return zlshyj;
    }
} 
