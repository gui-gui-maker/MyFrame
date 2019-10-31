package com.lsts.office.bean;




import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;
import com.lsts.office.service.WeightyTaskManager;

@Entity
@Table(name = "TJY2_YWHBSGZ")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ywhbsgz implements BaseEntity{

	private String responsiblePerson;//责任人
	
	private String responsiblePersonid;//责任人id

	private Set<YwhbsgzFk> ywhbsgzFks;
	
	private String id;//${columnb.remarks}

    private java.util.Date startTime;//任务开始时间

    private java.util.Date endTim;//任务结束时间

    private String department;//部门
    
    private String departmentId;//部门ID

    private String missionContent;//任务内容

    private String performance;//完成情况

    private String unfinishedReason;//未完成原因

    private String status=WeightyTaskManager.BG_RWZT_WXF;//状态

    private String createrId;//创建人id
    
    private String creater;//创建人
    
    private java.util.Date createrTime;//创建人时间
    
    

    public void setResponsiblePersonid(String value){
        this.responsiblePersonid = value;
    }
    public void setId(String value){
        this.id = value;
    }
    public void setResponsiblePerson(String value){
        this.responsiblePerson = value;
    }
    public void setStartTime(java.util.Date value){
        this.startTime = value;
    }
    public void setEndTim(java.util.Date value){
        this.endTim = value;
    }
    public void setDepartment(String value){
        this.department = value;
    }
    public void setMissionContent(String value){
        this.missionContent = value;
    }
    public void setPerformance(String value){
        this.performance = value;
    }
    public void setUnfinishedReason(String value){
        this.unfinishedReason = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    public void setCreaterId(String value){
        this.createrId = value;
    }
    public void setCreater(String value){
        this.creater = value;
    }
    public void setCreaterTime(java.util.Date value){
        this.createrTime = value;
    }
    public void setDepartmentId(String value){
        this.departmentId = value;
    }

    
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID")
    public String getId(){
        return this.id;
    }
    @Column(name ="START_TIME")
    public java.util.Date getStartTime(){
        return this.startTime;
    }
    @Column(name ="END_TIM")
    public java.util.Date getEndTim(){
        return this.endTim;
    }
    @Column(name ="DEPARTMENT")
    public String getDepartment(){
        return this.department;
    }
    @Column(name ="MISSION_CONTENT")
    public String getMissionContent(){
        return this.missionContent;
    }
    @Column(name ="PERFORMANCE")
    public String getPerformance(){
        return this.performance;
    }
    @Column(name ="UNFINISHED_REASON")
    public String getUnfinishedReason(){
        return this.unfinishedReason;
    }
    @Column(name ="STATUS")
    public String getStatus(){
        return this.status;
    }
    @Column(name ="CREATER_ID")
    public String getCreaterId(){
        return this.createrId;
    }
    @Column(name ="CREATER")
    public String getCreater(){
        return this.creater;
    }
    @Column(name ="CREATER_TIME")
    public java.util.Date getCreaterTime(){
        return this.createrTime;
    }
    @Column(name ="RESPONSIBLE_PERSON")
    public String getResponsiblePerson(){
        return this.responsiblePerson;
    }
    @Column(name ="RESPONSIBLE_PERSONID")
    public String getResponsiblePersonid(){
        return this.responsiblePersonid;
    }
    
    @Column(name ="DEPARTMENT_ID")
    public String getDepartmentId(){
        return this.departmentId;
    }
    
    public void setYwhbsgzFks(Set<YwhbsgzFk> ywhbsgzFks) {
		this.ywhbsgzFks = ywhbsgzFks;
	}
    
    @OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.LAZY,mappedBy="ywhbsgz")
	public Set<YwhbsgzFk> getYwhbsgzFks() {
		return ywhbsgzFks;
	}

	


} 
