package com.fwxm.material.bean;

import com.khnt.core.crud.bean.BaseEntity;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Table(name = "TJY2_CH_GOODS_TYPE")
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoodsType implements BaseEntity {
    private static final long serialVersionUID = 1L;
    private String id;
    private String lx_bh;//类型编号
    private String lx_name;//类型名称
    private String bz;//备注
    private String fz;//阀值
    private String createId;
    private String createName;
    private String createOrgId;
    private String createOrgName;
    private String createUnitId;
    private String createUnitName;
    private Date createTime;
    private String state;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID")
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "CREATE_ID")
    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    @Column(name = "CREATE_NAME")
    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    @Column(name = "CREATE_ORG_ID")
    public String getCreateOrgId() {
        return createOrgId;
    }

    public void setCreateOrgId(String createOrgId) {
        this.createOrgId = createOrgId;
    }

    @Column(name = "CREATE_ORG_NAME")
    public String getCreateOrgName() {
        return createOrgName;
    }

    public void setCreateOrgName(String createOrgName) {
        this.createOrgName = createOrgName;
    }

    @Column(name = "CREATE_UNIT_ID")
    public String getCreateUnitId() {
        return createUnitId;
    }

    public void setCreateUnitId(String createUnitId) {
        this.createUnitId = createUnitId;
    }

    @Column(name = "CREATE_UNIT_NAME")
    public String getCreateUnitName() {
        return createUnitName;
    }

    public void setCreateUnitName(String createUnitName) {
        this.createUnitName = createUnitName;
    }

    @Column(name = "CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "LX_BH")
	public String getLx_bh() {
		return lx_bh;
	}

	public void setLx_bh(String lx_bh) {
		this.lx_bh = lx_bh;
	}

    @Column(name = "LX_NAME")
	public String getLx_name() {
		return lx_name;
	}

	public void setLx_name(String lx_name) {
		this.lx_name = lx_name;
	}

    @Column(name = "BZ")
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

    @Column(name = "FZ")
	public String getFz() {
		return fz;
	}

	public void setFz(String fz) {
		this.fz = fz;
	}

    @Column(name = "STATE")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
    
}
