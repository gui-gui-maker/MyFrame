package com.lsts.office.bean;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import com.khnt.core.crud.bean.BaseEntity;
import com.lsts.humanresources.bean.EmployeeBase;

@Entity
@Table(name = "TJY2_OFFICE_MESSAGE")
@JsonIgnoreProperties(ignoreUnknown=true)
public class OfficeMessage implements BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;//${columnb.remarks}

    private String sendId;//发送对象ID

    private String sendName;//发送对象姓名

    private String sendNumber;//发送号码

    private String isTemporaryTel;//是否临时号码

    private String sendDimension;//发送内容

    private String sendType;//发送方式

    private String sendStyle;//发送类型

    private String sendStatus;//发送状态，“0”表示未发送，“1”表示已发送

    private java.util.Date sendTime;//发送时间

    private String sendById;//发送人ID

    private String sendBy;//发送人姓名

    private java.util.Date createDate;//创建时间

    private String createId;//创建人ID

    private String createBy;//创建人

    private java.util.Date lastModifyDate;//最后更新时间

    private String lastModifyId;//最后更新人ID

    private String lastModifyBy;//最后更新人
    
    private List<EmployeeBase> employee;

    public void setId(String value){
        this.id = value;
    }
    public void setSendId(String value){
        this.sendId = value;
    }
    public void setSendName(String value){
        this.sendName = value;
    }
    public void setSendNumber(String value){
        this.sendNumber = value;
    }
    public void setIsTemporaryTel(String value){
        this.isTemporaryTel = value;
    }
    public void setSendDimension(String value){
        this.sendDimension = value;
    }
    public void setSendType(String value){
        this.sendType = value;
    }
    public void setSendStyle(String value){
        this.sendStyle = value;
    }
    public void setSendStatus(String value){
        this.sendStatus = value;
    }
    public void setSendTime(java.util.Date value){
        this.sendTime = value;
    }
    public void setSendById(String value){
        this.sendById = value;
    }
    public void setSendBy(String value){
        this.sendBy = value;
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
    @Column(name ="SEND_ID",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getSendId(){
        return this.sendId;
    }
    @Column(name ="SEND_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getSendName(){
        return this.sendName;
    }
    @Column(name ="SEND_NUMBER",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getSendNumber(){
        return this.sendNumber;
    }
    @Column(name ="IS_TEMPORARY_TEL",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getIsTemporaryTel(){
        return this.isTemporaryTel;
    }
    @Column(name ="SEND_DIMENSION",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getSendDimension(){
        return this.sendDimension;
    }
    @Column(name ="SEND_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getSendType(){
        return this.sendType;
    }
    @Column(name ="SEND_STYLE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getSendStyle(){
        return this.sendStyle;
    }
    @Column(name ="SEND_STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getSendStatus(){
        return this.sendStatus;
    }
    @Column(name ="SEND_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getSendTime(){
        return this.sendTime;
    }
    @Column(name ="SEND_BY_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getSendById(){
        return this.sendById;
    }
    @Column(name ="SEND_BY",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getSendBy(){
        return this.sendBy;
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
    public List<EmployeeBase> getEmployee() {
		return employee;
	}
	public void setEmployee(List<EmployeeBase> employee) {
		this.employee = employee;
	}


} 
