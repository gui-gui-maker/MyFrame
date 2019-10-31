package com.lsts.equipment2.bean;

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

@Entity
@Table(name = "TJY2_EQUIP_DOCIMASY_FKS")
@JsonIgnoreProperties(ignoreUnknown=true,value={"docimasyFk"})
public class DocimasyFks implements BaseEntity{



	private  String  id;//${columnb.remarks}
    
    private  DocimasyFk   docimasyFk;

    private  String  result;//检验结果
    
    private String  equipmentId;//检定设备id

    private  String  status;//检定状态
    
    private  String  remark;//备注
    

    private  java.util.Date   practicalTime;//检定日期
    

    private  java.util.Date   nextTime;//计划日期
    

    public void setId(String value){
        this.id = value;
    }
    
    public void setResult(String value){
        this.result = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    public void setRemark(String value){
        this.remark = value;
    }
    public void setPracticalTime(java.util.Date value){
        this.practicalTime = value;
    }
    public void setNextTime(java.util.Date value){
        this.nextTime = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    
    public void setEquipmentId(String value){
        this.equipmentId = value;
    }
   
    @Column(name ="RESULT",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getResult(){
        return this.result;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=40)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="REMARK",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getRemark(){
        return this.remark;
    }
    @Column(name ="PRACTICAL_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getPracticalTime(){
        return this.practicalTime;
    }
    @Column(name ="NEXT_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getNextTime(){
        return this.nextTime;
    }
    @Column(name ="EQUIPMENT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getEquipmentId(){
        return this.equipmentId;
    }

  @ManyToOne
 	@JoinColumn(name="FK_ID",nullable=false)
 	public DocimasyFk getDocimasyFk() {
 		return docimasyFk;
 	}

  public void setDocimasyFk(DocimasyFk docimasyFk) {
		this.docimasyFk = docimasyFk;
	}

} 
