package com.lsts.report.bean;

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
@Table(name = "TJY2_REPORT_YJSZ")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportYjsz implements BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;//id

//    private java.util.Date startTime;//开始时间

//    private java.util.Date endTime;//结束时间

    private String flow;//流程环节开始

    private String tianshu;//天数

    private String state;//状态
    
    private String duanxinPush;//短息推送

    private String weixinPush;//微信推送
    
    private String flows;//流程环节结束

    
	
	public void setFlows(String flows) {
		this.flows = flows;
	}
	public void setDuanxinPush(String duanxinPush) {
		this.duanxinPush = duanxinPush;
	}
	public void setWeixinPush(String weixinPush) {
		this.weixinPush = weixinPush;
	}
	public void setId(String value){
        this.id = value;
    }
//    public void setStartTime(java.util.Date value){
//        this.startTime = value;
//    }
    public void setFlow(String value){
        this.flow = value;
    }
    public void setTianshu(String value){
        this.tianshu = value;
    }
    public void setState(String value){
        this.state = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
//    @Column(name ="START_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
//    public java.util.Date getStartTime(){
//        return this.startTime;
//    }
    @Column(name ="FLOW",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getFlow(){
        return this.flow;
    }
    @Column(name ="TIANSHU",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getTianshu(){
        return this.tianshu;
    }
    @Column(name ="STATE",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getState(){
        return this.state;
    }
    @Column(name ="DUANXIN_PUSH",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getDuanxinPush() {
		return duanxinPush;
	}
	
    @Column(name ="WEIXIN_PUSH",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getWeixinPush() {
		return weixinPush;
	}
    @Column(name ="FLOWS",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getFlows() {
		return flows;
	}
} 
