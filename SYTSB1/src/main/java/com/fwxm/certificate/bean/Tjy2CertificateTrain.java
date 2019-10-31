package com.fwxm.certificate.bean;

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
@Table(name = "TJY2_CERTIFICATE_TRAIN")
public class Tjy2CertificateTrain implements BaseEntity{

    private String id;//主键
    private String userName;//员工姓名

    private String userId;//职工ID

    private String trainUnit;//培训主办单位

    private String trainUnitId;//培训主办单位ID

    private String tratnFileNum;//培训文件文号

    private java.util.Date tratnTimeStart;//培训开始时间

    private java.util.Date tratnTimeEnd;//培训结束时间

    private String tratnSite;//培训地址

    private String tratnType;//培训类别 码表: TJY2_TRAIN_TYPE: (01:业务学习培训|02:取（换）检验资格证培训|03:其它培训)

    private java.util.Date createDate;//创建时间

    private String createMan;//创建人

    private String lastMan;//最后修改人

    private java.util.Date lastDate;//最后修改时间
    
    private String period;//学时
    
    private String trainCost;//培训费
    
    private String otherCost;//其他费用
    private String trainRemark;//培训内容
    private String trainNum;//培训编号
    
    

    @Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
    public void setUserId(String value){
        this.userId = value;
    }
    public void setTrainUnit(String value){
        this.trainUnit = value;
    }
    public void setTrainUnitId(String value){
        this.trainUnitId = value;
    }
    public void setTratnFileNum(String value){
        this.tratnFileNum = value;
    }
    public void setTratnTimeStart(java.util.Date value){
        this.tratnTimeStart = value;
    }
    public void setTratnTimeEnd(java.util.Date value){
        this.tratnTimeEnd = value;
    }
    public void setTratnSite(String value){
        this.tratnSite = value;
    }
    public void setTratnType(String value){
        this.tratnType = value;
    }
    public void setCreateDate(java.util.Date value){
        this.createDate = value;
    }
    public void setCreateMan(String value){
        this.createMan = value;
    }
    public void setLastMan(String value){
        this.lastMan = value;
    }
    public void setLastDate(java.util.Date value){
        this.lastDate = value;
    }
    @Column(name ="USER_ID")
	public String getUserId() {
		return userId;
	}
    @Column(name ="TRAIN_UNIT",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getTrainUnit(){
        return this.trainUnit;
    }
    @Column(name ="TRAIN_UNIT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getTrainUnitId(){
        return this.trainUnitId;
    }
    @Column(name ="TRATN_FILE_NUM",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getTratnFileNum(){
        return this.tratnFileNum;
    }
    @Column(name ="TRATN_TIME_START",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getTratnTimeStart(){
        return this.tratnTimeStart;
    }
    @Column(name ="TRATN_TIME_END",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getTratnTimeEnd(){
        return this.tratnTimeEnd;
    }
    @Column(name ="TRATN_SITE",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getTratnSite(){
        return this.tratnSite;
    }
    @Column(name ="TRATN_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=10)
    public String getTratnType(){
        return this.tratnType;
    }
    @Column(name ="CREATE_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getCreateDate(){
        return this.createDate;
    }
    @Column(name ="CREATE_MAN",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getCreateMan(){
        return this.createMan;
    }
    @Column(name ="LAST_MAN",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getLastMan(){
        return this.lastMan;
    }
    @Column(name ="LAST_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getLastDate(){
        return this.lastDate;
    }
    @Column(name ="user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name ="period")
	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}
	@Column(name ="train_cost")
	public String getTrainCost() {
		return trainCost;
	}

	public void setTrainCost(String trainCost) {
		this.trainCost = trainCost;
	}
	@Column(name ="other_cost")
	public String getOtherCost() {
		return otherCost;
	}

	public void setOtherCost(String otherCost) {
		this.otherCost = otherCost;
	}
	@Column(name ="train_remark")
	public String getTrainRemark() {
		return trainRemark;
	}

	public void setTrainRemark(String trainRemark) {
		this.trainRemark = trainRemark;
	}
	
	@Column(name ="train_num")
	public String getTrainNum() {
		return trainNum;
	}

	public void setTrainNum(String trainNum) {
		this.trainNum = trainNum;
	}


} 
