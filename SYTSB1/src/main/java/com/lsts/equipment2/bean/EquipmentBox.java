package com.lsts.equipment2.bean;

import java.util.List;

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

@Entity
@Table(name = "TJY2_EQUIPMENT_BOX")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EquipmentBox implements BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;//ID

    private String boxDepId;//部门ID

    private String boxDepName;//部门名称

    private String boxNumber;//设备箱编号

    private java.util.Date boxTime;//装箱时间

    private String registerId;//登记人ID

    private String registerName;//登记人

    private java.util.Date registerTime;//登记时间

    private String fkTemplateId;//模板ID

    private String remark;//备注
    
    private String templateName; //模板名称
    
    private String boxPicId;//设备箱照片
    
    private String boxinoutStatus;//设备箱出入库状态 01：表示已入库 02：表示已出库
    
    private String boxLoanStatus;//设备箱借用/领用状态 码表:TJY2_EQUIP_BOX
    
    private List<Equipment> baseEquipment2s;
    
    private String card_no;	// 卡片编号
    
    private java.util.Date lastCheckDate;//最近一次下次检定日期
    
    public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

    public void setId(String value){
        this.id = value;
    }
    public void setBoxDepId(String value){
        this.boxDepId = value;
    }
    public void setBoxDepName(String value){
        this.boxDepName = value;
    }
    public void setBoxNumber(String value){
        this.boxNumber = value;
    }
    public void setBoxTime(java.util.Date value){
        this.boxTime = value;
    }
    public void setRegisterId(String value){
        this.registerId = value;
    }
    public void setRegisterName(String value){
        this.registerName = value;
    }
    public void setRegisterTime(java.util.Date value){
        this.registerTime = value;
    }
   
    public void setRemark(String value){
        this.remark = value;
    }
    public void setLastCheckDate(java.util.Date value){
        this.lastCheckDate = value;
    }

    
    
	public void setBoxinoutStatus(String boxinoutStatus) {
		this.boxinoutStatus = boxinoutStatus;
	}
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=false,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="BOX_DEP_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getBoxDepId(){
        return this.boxDepId;
    }
    @Column(name ="BOX_DEP_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getBoxDepName(){
        return this.boxDepName;
    }
    @Column(name ="BOX_NUMBER",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getBoxNumber(){
        return this.boxNumber;
    }
    @Column(name ="BOX_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getBoxTime(){
        return this.boxTime;
    }
    @Column(name ="REGISTER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getRegisterId(){
        return this.registerId;
    }
    @Column(name ="REGISTER_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getRegisterName(){
        return this.registerName;
    }
    @Column(name ="REGISTER_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getRegisterTime(){
        return this.registerTime;
    }
   
    @Column(name ="REMARK",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getRemark(){
        return this.remark;
    }
    
    @Column(name ="BOX_INOUTSTATUS",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getBoxinoutStatus(){
        return this.boxinoutStatus;
    }
    
    @Column(name="TEMPLATE_NAME")
    public String getTemplateName() {
		return this.templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	
	@Column(name="FK_TEMPLATE_ID")
	public String getFkTemplateId() {
		return fkTemplateId;
	}
	public void setFkTemplateId(String fkTemplateId) {
		this.fkTemplateId = fkTemplateId;
	}

	@Column(name="BOX_PIC_ID")
	public String getBoxPicId() {
		return boxPicId;
	}
	public void setBoxPicId(String boxPicId) {
		this.boxPicId = boxPicId;
	}
	
	
	@Column(name="BOX_LOANSTATUS")
	public String getBoxLoanStatus() {
		return boxLoanStatus;
	}
	public void setBoxLoanStatus(String boxLoanStatus) {
		this.boxLoanStatus = boxLoanStatus;
	}
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.LAZY,mappedBy="equipmentBox")
	public List<Equipment> getBaseEquipment2s() {
		return baseEquipment2s;
	}
	public void setBaseEquipment2s(List<Equipment> baseEquipment2s) {
		this.baseEquipment2s = baseEquipment2s;
	}
	
	@Column(name ="CARD_NO",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getCard_no(){
        return this.card_no;
    }
	@Column(name ="LAST_CHECK_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getLastCheckDate(){
        return this.lastCheckDate;
    }
 
	

	
	
	
	
	
	
} 
