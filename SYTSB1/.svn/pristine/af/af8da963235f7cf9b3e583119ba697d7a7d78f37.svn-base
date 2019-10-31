package com.lsts.finance.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_SCIENTIFIC_CWBZ")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ScientificCwbz implements BaseEntity{
	
	private String id;
	private String fk_researcj_id;//项目ID

	private String fk_clfbxd_id;//报销单ID
	private String fy_type;//费用项目
	private String bxry;//报销人员
	private String bxbh;//报销编号
	private String unit;//报销部门
	private Date bxrq;//报销日期
	private BigDecimal bxje;//报销金额
	private String status;//状态
	
	

	public String getFk_researcj_id() {
		return fk_researcj_id;
	}

	public String getFk_clfbxd_id() {
		return fk_clfbxd_id;
	}

	public String getBxry() {
		return bxry;
	}

	public String getBxbh() {
		return bxbh;
	}

	public String getUnit() {
		return unit;
	}

	public Date getBxrq() {
		return bxrq;
	}

	public BigDecimal getBxje() {
		return bxje;
	}

	public String getStatus() {
		return status;
	}

	public void setFk_researcj_id(String fk_researcj_id) {
		this.fk_researcj_id = fk_researcj_id;
	}

	public void setFk_clfbxd_id(String fk_clfbxd_id) {
		this.fk_clfbxd_id = fk_clfbxd_id;
	}

	public void setBxry(String bxry) {
		this.bxry = bxry;
	}

	public void setBxbh(String bxbh) {
		this.bxbh = bxbh;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public void setBxrq(Date bxrq) {
		this.bxrq = bxrq;
	}

	public void setBxje(BigDecimal bxje) {
		this.bxje = bxje;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFy_type() {
		return fy_type;
	}

	public void setFy_type(String fy_type) {
		this.fy_type = fy_type;
	}

	@Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    
    public void setId(String value){
        this.id = value;
    }
    
} 
