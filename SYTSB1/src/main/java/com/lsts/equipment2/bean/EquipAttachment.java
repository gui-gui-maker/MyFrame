package com.lsts.equipment2.bean;

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
@Table(name = "TJY2_EQUIPMENT_ATTACHMENT")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquipAttachment implements BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;//${columnb.remarks}

    private String fkEquipmentId;//设备外键

    private String attachmentName;//附件名称

    private String attachmentSn;//出厂编号

    private String attachmentModel;//规格型号

    private String attachmentNum;//数量

    private String manufacturers;//制造厂家

    private String status;//附件状态

    private String configuring;//配置箱号

    private String remark;//备注

    private java.util.Date createDate;//创建时间

    private String createId;//创建人ID

    private String createBy;//创建人

    private java.util.Date lastModifyDate;//修改时间

    private String lastModifyId;//修改人ID

    private String lastModifyBy;//修改人

    public void setId(String value){
        this.id = value;
    }
    public void setFkEquipmentId(String value){
        this.fkEquipmentId = value;
    }
    public void setAttachmentName(String value){
        this.attachmentName = value;
    }
    public void setAttachmentSn(String value){
        this.attachmentSn = value;
    }
    public void setAttachmentModel(String value){
        this.attachmentModel = value;
    }
    public void setAttachmentNum(String value){
        this.attachmentNum = value;
    }
    public void setManufacturers(String value){
        this.manufacturers = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    public void setConfiguring(String value){
        this.configuring = value;
    }
    public void setRemark(String value){
        this.remark = value;
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
    @Column(name ="FK_EQUIPMENT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFkEquipmentId(){
        return this.fkEquipmentId;
    }
    @Column(name ="ATTACHMENT_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getAttachmentName(){
        return this.attachmentName;
    }
    @Column(name ="ATTACHMENT_SN",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getAttachmentSn(){
        return this.attachmentSn;
    }
    @Column(name ="ATTACHMENT_MODEL",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getAttachmentModel(){
        return this.attachmentModel;
    }
    @Column(name ="ATTACHMENT_NUM",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getAttachmentNum(){
        return this.attachmentNum;
    }
    @Column(name ="MANUFACTURERS",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getManufacturers(){
        return this.manufacturers;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="CONFIGURING",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getConfiguring(){
        return this.configuring;
    }
    @Column(name ="REMARK",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getRemark(){
        return this.remark;
    }
    @Column(name ="CREATE_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getCreateDate(){
        return this.createDate;
    }
    @Column(name ="CREATE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getCreateId(){
        return this.createId;
    }
    @Column(name ="CREATE_BY",unique=false,nullable=true,insertable=true,updatable=true,length=32)
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
    @Column(name ="LAST_MODIFY_BY",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getLastModifyBy(){
        return this.lastModifyBy;
    }


} 
