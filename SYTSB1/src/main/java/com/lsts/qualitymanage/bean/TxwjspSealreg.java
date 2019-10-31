package com.lsts.qualitymanage.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_QUALITY_TXWJSP_SEALREG")
@JsonIgnoreProperties(ignoreUnknown=true)
public class TxwjspSealreg implements BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;//${columnb.remarks}
    
	private String identifier;//编号
	
    private String matters;//办理事项

    private String orgName;//单位名称

    private String orgid;//单位ID

    private String chapterName;//用章名称

    private String deliveryHead;//送件部门负责人签字
    
    private java.util.Date deliveryHeadDate;//送件部门负责人签字日期

    private String givePerson;//送件人签字
    
    private java.util.Date givePersonDate;//送件人签字日期

	private String serviceHead;//服务部负责人签字
    
    private java.util.Date serviceHeadDate;//服务部负责人签字日期

    private String opertionalManagement;//业务部门分管领导签字
    
    private java.util.Date opertionalManagementDate;//业务部门分管领导签字日期

    private String sealer;//盖章人签字

    private java.util.Date sealDate;//盖章日期

    private String sealScore;//盖章分数

    private String creater;//创建人

    private java.util.Date createrDate;//创建时间

    private String createrid;//创建人ID

	private String status;// 1`未提交`2`待审核`3`审核中`4`审核通过`5`审核失败
	
    private String uploadIds;

    public void setId(String value){
        this.id = value;
    }
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
    public void setMatters(String value){
        this.matters = value;
    }
    public void setOrgName(String value){
        this.orgName = value;
    }
    public void setOrgid(String value){
        this.orgid = value;
    }
    public void setChapterName(String value){
        this.chapterName = value;
    }
    public void setDeliveryHead(String value){
        this.deliveryHead = value;
    }
    public void setGivePerson(String value){
        this.givePerson = value;
    }
    public void setServiceHead(String value){
        this.serviceHead = value;
    }
    public void setOpertionalManagement(String value){
        this.opertionalManagement = value;
    }
    public void setSealer(String value){
        this.sealer = value;
    }
    public void setSealDate(java.util.Date value){
        this.sealDate = value;
    }
    public void setSealScore(String value){
        this.sealScore = value;
    }
    public void setCreater(String value){
        this.creater = value;
    }
    public void setCreaterDate(java.util.Date value){
        this.createrDate = value;
    }
    public void setCreaterid(String value){
        this.createrid = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    
    public void setDeliveryHeadDate(java.util.Date deliveryHeadDate) {
    	this.deliveryHeadDate = deliveryHeadDate;
    }
    public void setServiceHeadDate(java.util.Date serviceHeadDate) {
    	this.serviceHeadDate = serviceHeadDate;
    }
    public void setOpertionalManagementDate(java.util.Date opertionalManagementDate) {
    	this.opertionalManagementDate = opertionalManagementDate;
    }
    public void setGivePersonDate(java.util.Date givePersonDate) {
		this.givePersonDate = givePersonDate;
	}
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="IDENTIFIER",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getIdentifier() {
		return identifier;
	}
    @Column(name ="MATTERS",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getMatters(){
        return this.matters;
    }
    @Column(name ="DEPARTMENT",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getOrgName(){
        return this.orgName;
    }
    @Column(name ="ORGID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getOrgid(){
        return this.orgid;
    }
    @Column(name ="CHAPTER_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getChapterName(){
        return this.chapterName;
    }
    @Column(name ="DELIVERY_HEAD",unique=false,nullable=true,insertable=true,updatable=true,length=40)
    public String getDeliveryHead(){
        return this.deliveryHead;
    }
    @Column(name ="GIVE_PERSON",unique=false,nullable=true,insertable=true,updatable=true,length=40)
    public String getGivePerson(){
        return this.givePerson;
    }
    @Column(name ="SERVICE_HEAD",unique=false,nullable=true,insertable=true,updatable=true,length=40)
    public String getServiceHead(){
        return this.serviceHead;
    }
    @Column(name ="OPERTIONAL_MANAGEMENT",unique=false,nullable=true,insertable=true,updatable=true,length=40)
    public String getOpertionalManagement(){
        return this.opertionalManagement;
    }
    @Column(name ="SEALER",unique=false,nullable=true,insertable=true,updatable=true,length=40)
    public String getSealer(){
        return this.sealer;
    }
    @Column(name ="SEAL_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getSealDate(){
        return this.sealDate;
    }
    @Column(name ="SEAL_SCORE",unique=false,nullable=true,insertable=true,updatable=true,length=40)
    public String getSealScore(){
        return this.sealScore;
    }
    @Column(name ="APPLY_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=40)
    public String getCreater(){
        return this.creater;
    }
    @Column(name ="APPLY_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getCreaterDate(){
        return this.createrDate;
    }
    @Column(name ="CREATERID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getCreaterid(){
        return this.createrid;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=40)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="OPERTIONALMANAGEMENT_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getOpertionalManagementDate() {
    	return opertionalManagementDate;
    }
    @Column(name ="DELIVERYHEAD_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getDeliveryHeadDate() {
    	return deliveryHeadDate;
    }
    @Column(name ="SERVICEHEAD_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getServiceHeadDate() {
    	return this.serviceHeadDate;
    }
    @Column(name ="GIVEPERSON_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getGivePersonDate() {
    	return this.givePersonDate;
    }
 
	@Transient
	public String getUploadIds() {
		return uploadIds;
	}

	public void setUploadIds(String uploadIds) {
		this.uploadIds = uploadIds;
	}
	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "未说明";

	}
} 
