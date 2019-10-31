package com.lsts.office.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;


/**
 * 重大任务部门子表
 * @author lenovo
 *
 */
@Entity
@Table(name = "TJY2_BG_DEP")
@JsonIgnoreProperties(ignoreUnknown=true,value={"weightyTask"})
public class WeightyDep implements BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;//主键
    
//    private String pkDepId;
    
    private String phDepName;//部门
    
    private String depDutyName; //部门负责人
   
    private String phDepId;//部门ID


    private WeightyTask weightyTask;
    
    
    public void setId(String value){
        this.id = value;
    }
    
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    
    
//    @Column(name = "PK_DEP_ID")
//	public String getPkDepId() {
//		return pkDepId;
//	}
//	public void setPkDepId(String pkDepId) {
//		this.pkDepId = pkDepId;
//	}
	@Column(name = "PH_DEP_NAME")
	public String getPhDepName() {
		return phDepName;
	}

	public void setPhDepName(String phDepName) {
		this.phDepName = phDepName;
	}
	@Column(name = "DEP_DUTY_NAME")
	public String getDepDutyName() {
		return depDutyName;
	}

	public void setDepDutyName(String depDutyName) {
		this.depDutyName = depDutyName;
	}
   
	@Column(name="PH_DEP_ID")
    public String getPhDepId() {
		return phDepId;
	}

	public void setPhDepId(String phDepId) {
		this.phDepId = phDepId;
	}

	@ManyToOne
   	@JoinColumn(name="PK_DEP_ID")
	public WeightyTask getWeightyTask() {
		return weightyTask;
	}
	public void setWeightyTask(WeightyTask value) {
		this.weightyTask = value;
	}



} 
