package com.fwxm.material.bean;

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
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "TJY2_CH_SUPPLIER")
public class Supplier  implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String gysmc;//供应商名称
	private String gysdz;//供应商地址
	private String frdb;//法人代表
	private String tel;//手机/固定电话
	private String khyh;//开户银行
	private String zzjgdm;//注资机构代码
	private String swdjzbm;//税务登记证编码
	private Date zcrq;//注册日期
	private Date yyzzyxrq;//营业执照有效日期
//	private String qyxz;//企业性质
	private String lxr;//联系人
	private String lxrdh;//联系人电话
	private String lxrgddh;//固定电话
	private String yx;//邮箱
	private String zycpjfw;//主要产品及服务
	private String cz;//传真
	private String yb;//邮编
	private String wz;//网址
	private String status;//状态
	private String createUserId;//创建人id
	private String createUserName;//创建人
    private String createOrgId;
    private String createOrgName;
    private String createUnitId;
    private String createUnitName;
	private Date createDate;//创建时间
	private String bz;//备注
	
	
	
	
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	@Column(name="GYSMC")
	public String getGysmc() {
		return gysmc;
	}

	public void setGysmc(String gysmc) {
		this.gysmc = gysmc;
	}

	@Column(name="GYSDZ")
	public String getGysdz() {
		return gysdz;
	}

	public void setGysdz(String gysdz) {
		this.gysdz = gysdz;
	}

	@Column(name="LXR")
	public String getLxr() {
		return lxr;
	}

	public void setLxr(String lxr) {
		this.lxr = lxr;
	}

	@Column(name="LXRDH")
	public String getLxrdh() {
		return lxrdh;
	}

	public void setLxrdh(String lxrdh) {
		this.lxrdh = lxrdh;
	}

	@Column(name="CZ")
	public String getCz() {
		return cz;
	}

	public void setCz(String cz) {
		this.cz = cz;
	}
	
	@Column(name="YB")
	public String getYb() {
		return yb;
	}

	public void setYb(String yb) {
		this.yb = yb;
	}

	@Column(name="WZ")
	public String getWz() {
		return wz;
	}

	public void setWz(String wz) {
		this.wz = wz;
	}

	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name="CREATE_USER_ID")
	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	@Column(name="CREATE_USER_NAME")
	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	@Column(name="CARETE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(name="BZ")
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	@Column(name="FRDB")
	public String getFrdb() {
		return frdb;
	}

	public void setFrdb(String frdb) {
		this.frdb = frdb;
	}
	@Column(name="TEL")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	@Column(name="KHYH")
	public String getKhyh() {
		return khyh;
	}

	public void setKhyh(String khyh) {
		this.khyh = khyh;
	}
	@Column(name="ZZJGDM")
	public String getZzjgdm() {
		return zzjgdm;
	}

	public void setZzjgdm(String zzjgdm) {
		this.zzjgdm = zzjgdm;
	}
	@Column(name="SWDJZBM")
	public String getSwdjzbm() {
		return swdjzbm;
	}

	public void setSwdjzbm(String swdjzbm) {
		this.swdjzbm = swdjzbm;
	}
	@Column(name="ZCRQ")
	public Date getZcrq() {
		return zcrq;
	}

	public void setZcrq(Date zcrq) {
		this.zcrq = zcrq;
	}
	@Column(name="YYZZYXRQ")
	public Date getYyzzyxrq() {
		return yyzzyxrq;
	}

	public void setYyzzyxrq(Date yyzzyxrq) {
		this.yyzzyxrq = yyzzyxrq;
	}
//	@Column(name="QYXZ")
//	public String getQyxz() {
//		return qyxz;
//	}
//
//	public void setQyxz(String qyxz) {
//		this.qyxz = qyxz;
//	}
	@Column(name="LXRGDDH")
	public String getLxrgddh() {
		return lxrgddh;
	}

	public void setLxrgddh(String lxrgddh) {
		this.lxrgddh = lxrgddh;
	}
	@Column(name="YX")
	public String getYx() {
		return yx;
	}

	public void setYx(String yx) {
		this.yx = yx;
	}
	@Column(name="ZYCPJFW")
	public String getZycpjfw() {
		return zycpjfw;
	}

	public void setZycpjfw(String zycpjfw) {
		this.zycpjfw = zycpjfw;
	}
	
	
	
}
