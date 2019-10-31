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
@Table(name = "TJY2_QUALITY_LIAISON")
@JsonIgnoreProperties(ignoreUnknown=true)
public class QualityLiaison implements BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;//${columnb.remarks}

    private String fileNumber;//文件编号

    private java.util.Date implementDate;//实施日期

    private String identifier;//编号

    private String comName;//受检单位名称

    private String item1;//输入框一

    private String item2;//输入框二

    private java.util.Date item3;//输入框三

    private String content;//问题和意见

    private String result;//处理结果

    private String inspectorId;//监检员ID

    private String inspector;//监检员

    private java.util.Date inspectorDate;//日期

    private String type;//类型

    private String checkUserId;//审核人ID

    private String checkUserName;//审核人姓名

    private String checkContent;//审核意见

    private java.util.Date checkDate;//审核时间

    private String checkStatus;//审核状态
    
    private String printStatus;//打印状态

    private String createId;//创建人ID

    private String createBy;//创建人

    private java.util.Date createDate;//创建时间

    private String lastModifyId;//最后更新人ID

    private String lastModifyBy;//最后更新人

    private java.util.Date lastModifyDate;//最后更新时间

    public void setId(String value){
        this.id = value;
    }
    public void setFileNumber(String value){
        this.fileNumber = value;
    }
    public void setImplementDate(java.util.Date value){
        this.implementDate = value;
    }
    public void setIdentifier(String value){
        this.identifier = value;
    }
    public void setComName(String value){
        this.comName = value;
    }
    public void setItem1(String value){
        this.item1 = value;
    }
    public void setItem2(String value){
        this.item2 = value;
    }
    public void setItem3(java.util.Date value){
        this.item3 = value;
    }
    public void setContent(String value){
        this.content = value;
    }
    public void setResult(String value){
        this.result = value;
    }
    public void setInspectorId(String value){
        this.inspectorId = value;
    }
    public void setInspector(String value){
        this.inspector = value;
    }
    public void setInspectorDate(java.util.Date value){
        this.inspectorDate = value;
    }
    public void setType(String value){
        this.type = value;
    }
    public void setCheckUserId(String value){
        this.checkUserId = value;
    }
    public void setCheckUserName(String value){
        this.checkUserName = value;
    }
    public void setCheckContent(String value){
        this.checkContent = value;
    }
    public void setCheckDate(java.util.Date value){
        this.checkDate = value;
    }
    public void setCheckStatus(String value){
        this.checkStatus = value;
    }
    public void setPrintStatus(String value){
        this.printStatus = value;
    }
    public void setCreateId(String value){
        this.createId = value;
    }
    public void setCreateBy(String value){
        this.createBy = value;
    }
    public void setCreateDate(java.util.Date value){
        this.createDate = value;
    }
    public void setLastModifyId(String value){
        this.lastModifyId = value;
    }
    public void setLastModifyBy(String value){
        this.lastModifyBy = value;
    }
    public void setLastModifyDate(java.util.Date value){
        this.lastModifyDate = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="FILE_NUMBER",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getFileNumber(){
        return this.fileNumber;
    }
    @Column(name ="IMPLEMENT_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getImplementDate(){
        return this.implementDate;
    }
    @Column(name ="IDENTIFIER",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getIdentifier(){
        return this.identifier;
    }
    @Column(name ="COM_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getComName(){
        return this.comName;
    }
    @Column(name ="ITEM1",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getItem1(){
        return this.item1;
    }
    @Column(name ="ITEM2",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getItem2(){
        return this.item2;
    }
    @Column(name ="ITEM3",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getItem3(){
        return this.item3;
    }
    @Column(name ="CONTENT",unique=false,nullable=true,insertable=true,updatable=true,length=3000)
    public String getContent(){
        return this.content;
    }
    @Column(name ="RESULT",unique=false,nullable=true,insertable=true,updatable=true,length=3000)
    public String getResult(){
        return this.result;
    }
    @Column(name ="INSPECTOR_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getInspectorId(){
        return this.inspectorId;
    }
    @Column(name ="INSPECTOR",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getInspector(){
        return this.inspector;
    }
    @Column(name ="INSPECTOR_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getInspectorDate(){
        return this.inspectorDate;
    }
    @Column(name ="TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getType(){
        return this.type;
    }
    @Column(name ="CHECK_USER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getCheckUserId(){
        return this.checkUserId;
    }
    @Column(name ="CHECK_USER_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getCheckUserName(){
        return this.checkUserName;
    }
    @Column(name ="CHECK_CONTENT",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getCheckContent(){
        return this.checkContent;
    }
    @Column(name ="CHECK_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getCheckDate(){
        return this.checkDate;
    }
    @Column(name ="CHECK_STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getCheckStatus(){
        return this.checkStatus;
    }
    @Column(name ="PRINT_STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getPrintStatus(){
        return this.printStatus;
    }
    @Column(name ="CREATE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getCreateId(){
        return this.createId;
    }
    @Column(name ="CREATE_BY",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getCreateBy(){
        return this.createBy;
    }
    @Column(name ="CREATE_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getCreateDate(){
        return this.createDate;
    }
    @Column(name ="LAST_MODIFY_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getLastModifyId(){
        return this.lastModifyId;
    }
    @Column(name ="LAST_MODIFY_BY",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getLastModifyBy(){
        return this.lastModifyBy;
    }
    @Column(name ="LAST_MODIFY_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getLastModifyDate(){
        return this.lastModifyDate;
    }


} 
