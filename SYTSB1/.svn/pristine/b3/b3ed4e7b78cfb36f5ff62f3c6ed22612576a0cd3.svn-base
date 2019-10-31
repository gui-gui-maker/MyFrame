package com.lsts.equipment2.bean;

import java.util.Date;
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

/** 
 * @author 作者 QuincyXu
 * @JDK 1.7
 * @version 创建时间：2016年5月16日15:57:26
 * 固定资产
 */
@Entity
@Table(name = "TJY2_EQUIP_PPE")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquipPpe implements BaseEntity{

	
	private static final long serialVersionUID = 1L;
    private String id;//ID

    private String cardNo;//卡片编号

    private String selfNo;//自编号

    private String assetName;//资产名称

    private String spaciModel;//规格型号

    private String sn;//序列号

    private String unit;//计量单位

    private String assetValue;//价值
    
    private String originalValue;//原值

    private String placeLocation;//放置地点

    private String userDepartmentId;//使用部门ID

    private String userDepartment;//使用部门

    private String userId;//使用人ID

    private String userName;//使用人

    private java.util.Date productDate;//出厂日期

    private java.util.Date releaseDate;//发放日期

    private String assetStatus;//资产状态
    
    private String inventory;//盘点状态
    
    private Date inventoryDate;//盘点时间
    
	private String inventoryId;//盘点人ID
	
	private String inventoryName;//盘点人姓名
    
    private String remark;//备注
    
    private String serviceLife;//最低使用年限
    
    private String loanStatus;//借用状态（0表示未借用，1表示已借用）
    
    private String owner;//归属
    
    private String barcode;//条形码
    
    private Date instockDate;//盘点时间

    private java.util.Date createDate;//创建时间

    private String createId;//创建人ID

    private String createBy;//创建人

    private java.util.Date lastModifyDate;//最后更新时间

    private String lastModifyId;//最后更新人ID

    private String lastModifyBy;//最后更新人

    public void setId(String value){
        this.id = value;
    }
    public void setCardNo(String value){
        this.cardNo = value;
    }
    public void setSelfNo(String value){
        this.selfNo = value;
    }
    public void setAssetName(String value){
        this.assetName = value;
    }
    public void setSpaciModel(String value){
        this.spaciModel = value;
    }
    public void setSn(String value){
        this.sn = value;
    }
    public void setUnit(String value){
        this.unit = value;
    }
    public void setAssetValue(String value){
        this.assetValue = value;
    }
    public void setOriginalValue(String value){
        this.originalValue = value;
    }
    public void setPlaceLocation(String value){
        this.placeLocation = value;
    }
    public void setUserDepartmentId(String value){
        this.userDepartmentId = value;
    }
    public void setUserDepartment(String value){
        this.userDepartment = value;
    }
    public void setUserId(String value){
        this.userId = value;
    }
    public void setUserName(String value){
        this.userName = value;
    }
    public void setProductDate(java.util.Date value){
        this.productDate = value;
    }
    public void setReleaseDate(java.util.Date value){
        this.releaseDate = value;
    }
    public void setAssetStatus(String value){
        this.assetStatus = value;
    }
    public void setInventory(String value){
        this.inventory = value;
    }
    public void setInventoryDate(Date value){
        this.inventoryDate = value;
    }
    public void setInventoryId(String value){
        this.inventoryId = value;
    }
    public void setInventoryName(String value){
        this.inventoryName = value;
    }
    public void setRemark(String value){
        this.remark = value;
    }
    public void setServiceLife(String value){
        this.serviceLife = value;
    }
    public void setLoanStatus(String value){
        this.loanStatus = value;
    }
    public void setOwner(String value){
        this.owner = value;
    }
    public void setBarcode(String value){
        this.barcode = value;
    }
    public void setInstockDate(Date value){
        this.instockDate = value;
    }
    public void setCreateDate(java.util.Date value){
        this.createDate = value;
    }
    public void setCreateId(String value){
        this.createId = value;
    }
    public void setCreateBy(String value){
        this.createBy = value;
    }
    public void setLastModifyDate(java.util.Date value){
        this.lastModifyDate = value;
    }
    public void setLastModifyId(String value){
        this.lastModifyId = value;
    }
    public void setLastModifyBy(String value){
        this.lastModifyBy = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="CARD_NO",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getCardNo(){
        return this.cardNo;
    }
    @Column(name ="SELF_NO",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getSelfNo(){
        return this.selfNo;
    }
    @Column(name ="ASSET_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getAssetName(){
        return this.assetName;
    }
    @Column(name ="SPACI_MODEL",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getSpaciModel(){
        return this.spaciModel;
    }
    @Column(name ="SN",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getSn(){
        return this.sn;
    }
    @Column(name ="UNIT",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getUnit(){
        return this.unit;
    }
    @Column(name ="ASSET_VALUE",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getAssetValue(){
        return this.assetValue;
    }
    @Column(name ="ORIGINAL_VALUE",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getOriginalValue(){
        return this.originalValue;
    }
    @Column(name ="PLACE_LOCATION",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getPlaceLocation(){
        return this.placeLocation;
    }
    @Column(name ="USER_DEPARTMENT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getUserDepartmentId(){
        return this.userDepartmentId;
    }
    @Column(name ="USER_DEPARTMENT",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getUserDepartment(){
        return this.userDepartment;
    }
    @Column(name ="USER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getUserId(){
        return this.userId;
    }
    @Column(name ="USER_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getUserName(){
        return this.userName;
    }
    @Column(name ="PRODUCT_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getProductDate(){
        return this.productDate;
    }
    @Column(name ="RELEASE_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getReleaseDate(){
        return this.releaseDate;
    }
    @Column(name ="ASSET_STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getAssetStatus(){
        return this.assetStatus;
    }
    @Column(name ="INVENTORY",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getInventory(){
        return this.inventory;
    }
    @Column(name ="INVENTORY_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getInventoryDate(){
        return this.inventoryDate;
    }
    @Column(name ="INVENTORY_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getInventoryId(){
        return this.inventoryId;
    }
    @Column(name ="INVENTORY_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getInventoryName(){
        return this.inventoryName;
    }
    @Column(name ="REMARK",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getRemark(){
        return this.remark;
    }
    @Column(name ="SERVICE_LIFE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getServiceLife(){
        return this.serviceLife;
    }
    @Column(name ="LOAN_STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getLoanStatus(){
        return this.loanStatus;
    }
    @Column(name ="OWNER",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getOwner(){
        return this.owner;
    }
    @Column(name ="BARCODE",unique=false,nullable=true,insertable=true,updatable=true,length=13)
    public String getBarcode(){
        return this.barcode;
    }
    @Column(name ="INSTOCK_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getInstockDate(){
        return this.instockDate;
    }
    @Column(name ="CREATE_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getCreateDate(){
        return this.createDate;
    }
    @Column(name ="CREATE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getCreateId(){
        return this.createId;
    }
    @Column(name ="CREATE_BY",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getCreateBy(){
        return this.createBy;
    }
    @Column(name ="LAST_MODIFY_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getLastModifyDate(){
        return this.lastModifyDate;
    }
    @Column(name ="LAST_MODIFY_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getLastModifyId(){
        return this.lastModifyId;
    }
    @Column(name ="LAST_MODIFY_BY",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getLastModifyBy(){
        return this.lastModifyBy;
    }


} 
