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
@Table(name = "TJY2_EQUIPMENT_CONTRACT")
public class EquipmentContract implements BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;//主键

    private String contractType;//合同类型

    private java.util.Date contractStartDate;//合同开始日期

    private java.util.Date contractStopDate;//合同终止日期

    private String signedMan;//合同签订人

    private String documentId;//合同文档ID

    private byte documentDoc[];;//合同文档内容

    private String documentName;//合同文档名字

    private java.util.Date createDate;//创建时间

    private String fkEquipApplyId;//申请单基本信息ID

    public void setId(String value){
        this.id = value;
    }
    public void setContractType(String value){
        this.contractType = value;
    }
    public void setContractStartDate(java.util.Date value){
        this.contractStartDate = value;
    }
    public void setContractStopDate(java.util.Date value){
        this.contractStopDate = value;
    }
    public void setSignedMan(String value){
        this.signedMan = value;
    }
    public void setDocumentId(String value){
        this.documentId = value;
    }
    public void setDocumentDoc(byte[] value){
        this.documentDoc = value;
    }
    public void setDocumentName(String value){
        this.documentName = value;
    }
    public void setCreateDate(java.util.Date value){
        this.createDate = value;
    }
    public void setFkEquipApplyId(String value){
        this.fkEquipApplyId = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="CONTRACT_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getContractType(){
        return this.contractType;
    }
    @Column(name ="CONTRACT_START_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getContractStartDate(){
        return this.contractStartDate;
    }
    @Column(name ="CONTRACT_STOP_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getContractStopDate(){
        return this.contractStopDate;
    }
    @Column(name ="SIGNED_MAN",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getSignedMan(){
        return this.signedMan;
    }
    @Column(name ="DOCUMENT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getDocumentId(){
        return this.documentId;
    }
    @Column(name ="DOCUMENT_DOC",unique=false,nullable=true,insertable=true,updatable=true)
    public byte[] getDocumentDoc(){
        return this.documentDoc;
    }
    @Column(name ="DOCUMENT_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getDocumentName(){
        return this.documentName;
    }
    @Column(name ="CREATE_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getCreateDate(){
        return this.createDate;
    }
    @Column(name ="FK_EQUIP_APPLY_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFkEquipApplyId(){
        return this.fkEquipApplyId;
    }


} 
