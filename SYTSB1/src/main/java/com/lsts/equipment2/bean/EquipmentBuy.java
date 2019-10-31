package com.lsts.equipment2.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 设备购买申请
 * EquipmentBuy entity. 
 * @author GaoYa
 * @date 2014-02-17 17:25:00
 */
@Entity
@Table(name = "TJY2_EQUIP_APPLY")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquipmentBuy implements BaseEntity{

	private static final long serialVersionUID = 1L;
	private String id;//ID

    private String applyId;//申请人ID

    private String applyName;//申请人姓名

    private String applyUnitId;//申请部门ID

    private String applyUnitName;//申请部门名称

    private java.util.Date applyDate;//申请时间

    private String applyStatus;//申请状态

    private String jhlzjy;//计划论证意见

    private String jhlzId;//计划论证审核人ID

    private String jhlzName;//计划论证审核人姓名

    private java.util.Date jhlzDate;//计划论证审核时间

    private String sqbmyj;//申请部门负责人意见

    private String sqbmshId;//申请部门审核人ID

    private String sqbmshName;//申请部门审核人姓名

    private java.util.Date sqbmshDate;//申请部门审核时间

    private String znbmyj;//职能部门负责人意见

    private String znbmshId;//职能部门审核人ID

    private String znbmshName;//职能部门审核人姓名

    private java.util.Date znbmshDate;//职能部门审核时间

    private java.util.Date createDate;//创建时间

    private String createId;//创建人ID

    private String createBy;//创建人

    private java.util.Date lastModifyDate;//最后更新时间

    private String lastModifyId;//最后更新人ID

    private String lastModifyBy;//最后更新人

	private List<EquipmentApplyList> equipmentApplyList;//申请设备列表

	public void setId(String value){
        this.id = value;
    }
    public void setApplyId(String value){
        this.applyId = value;
    }
    public void setApplyName(String value){
        this.applyName = value;
    }
    public void setApplyUnitId(String value){
        this.applyUnitId = value;
    }
    public void setApplyUnitName(String value){
        this.applyUnitName = value;
    }
    public void setApplyDate(java.util.Date value){
        this.applyDate = value;
    }
    public void setApplyStatus(String value){
        this.applyStatus = value;
    }
    public void setJhlzjy(String value){
        this.jhlzjy = value;
    }
    public void setJhlzId(String value){
        this.jhlzId = value;
    }
    public void setJhlzName(String value){
        this.jhlzName = value;
    }
    public void setJhlzDate(java.util.Date value){
        this.jhlzDate = value;
    }
    public void setSqbmyj(String value){
        this.sqbmyj = value;
    }
    public void setSqbmshId(String value){
        this.sqbmshId = value;
    }
    public void setSqbmshName(String value){
        this.sqbmshName = value;
    }
    public void setSqbmshDate(java.util.Date value){
        this.sqbmshDate = value;
    }
    public void setZnbmyj(String value){
        this.znbmyj = value;
    }
    public void setZnbmshId(String value){
        this.znbmshId = value;
    }
    public void setZnbmshName(String value){
        this.znbmshName = value;
    }
    public void setZnbmshDate(java.util.Date value){
        this.znbmshDate = value;
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
    @Column(name ="ID",unique=false,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="APPLY_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getApplyId(){
        return this.applyId;
    }
    @Column(name ="APPLY_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getApplyName(){
        return this.applyName;
    }
    @Column(name ="APPLY_UNIT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getApplyUnitId(){
        return this.applyUnitId;
    }
    @Column(name ="APPLY_UNIT_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getApplyUnitName(){
        return this.applyUnitName;
    }
    @Column(name ="APPLY_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getApplyDate(){
        return this.applyDate;
    }
    @Column(name ="APPLY_STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getApplyStatus(){
        return this.applyStatus;
    }
    @Column(name ="JHLZJY",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getJhlzjy(){
        return this.jhlzjy;
    }
    @Column(name ="JHLZ_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getJhlzId(){
        return this.jhlzId;
    }
    @Column(name ="JHLZ_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getJhlzName(){
        return this.jhlzName;
    }
    @Column(name ="JHLZ_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getJhlzDate(){
        return this.jhlzDate;
    }
    @Column(name ="SQBMYJ",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getSqbmyj(){
        return this.sqbmyj;
    }
    @Column(name ="SQBMSH_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getSqbmshId(){
        return this.sqbmshId;
    }
    @Column(name ="SQBMSH_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getSqbmshName(){
        return this.sqbmshName;
    }
    @Column(name ="SQBMSH_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getSqbmshDate(){
        return this.sqbmshDate;
    }
    @Column(name ="ZNBMYJ",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getZnbmyj(){
        return this.znbmyj;
    }
    @Column(name ="ZNBMSH_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getZnbmshId(){
        return this.znbmshId;
    }
    @Column(name ="ZNBMSH_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getZnbmshName(){
        return this.znbmshName;
    }
    @Column(name ="ZNBMSH_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getZnbmshDate(){
        return this.znbmshDate;
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
    
	@Transient
	public List<EquipmentApplyList> getEquipmentApplyList() {
		return equipmentApplyList;
	}
	public void setEquipmentApplyList(List<EquipmentApplyList> equipmentApplyList) {
		this.equipmentApplyList = equipmentApplyList;
	}
	
}
