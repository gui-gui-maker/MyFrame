package com.fwxm.supplies.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.fwxm.material.bean.GoodsAndOrder;
import com.khnt.core.crud.bean.BaseEntity;

/**
 * 物资入库单
 * @author Administrator
 *
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "TJY2_CH_WAREHOUSING")
public class Warehousing implements BaseEntity {
	private static final long serialVersionUID = 1L;
	private String id;
	private String shdw;//收货单位
	private String dz;//地址
	private String lxrmc;//联系人名称
	private String lxrmc_id;//联系人名称id
	private String lxrbm;//联系人部门
	private String lxrbm_id;//联系人部门id
	private String dh;//电话
	private String jbr;//经办人
//	private Integer sl;//数量
	private Date ysb_time;//验收表时间
	private String ysbbg;//验收表编号
	private String bmfzr;//部门负责人
	private String ys;//验收人
	private String cg;//采购人
	private String kg;//库管人
	private Date create_time;//
	private String create_user_id;
	private String create_user_name;
	private String create_org_name;
	private String create_org_id;
	private List<GoodsAndOrder> goodsAndOrder;
	private String state;//状态：99删除;1保存；2提交可用
    private String warehousing_num;//系统生成的人眼可识别的入库单号
	private String fybxd_id;//费用报销单id
	private String bz_zt;//报销状态：0或null报销中；1已报销；2报销作废
	 

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	@Column(name="FK_FYBXD_ID")
	public String getFybxd_id() {
		return fybxd_id;
	}
	public void setFybxd_id(String fybxd_id) {
		this.fybxd_id = fybxd_id;
	}
	@Column(name="SHDW")
	public String getShdw() {
		return shdw;
	}
	public void setShdw(String shdw) {
		this.shdw = shdw;
	}
	@Column(name="DZ")
	public String getDz() {
		return dz;
	}
	public void setDz(String dz) {
		this.dz = dz;
	}

//	@Column(name="SL")
//	public Integer getSl() {
//		return sl;
//	}

	@Column(name="DH")
	public String getDh() {
		return dh;
	}
	public void setDh(String dh) {
		this.dh = dh;
	}
	@Column(name="JBR")
	public String getJbr() {
		return jbr;
	}
	public void setJbr(String jbr) {
		this.jbr = jbr;
	}
//	public void setSl(Integer sl) {
//		this.sl = sl;
//	}
	@Column(name="YSB_TIME")
	public Date getYsb_time() {
		return ysb_time;
	}
	public void setYsb_time(Date ysb_time) {
		this.ysb_time = ysb_time;
	}
	@Column(name="YSBBH")
	public String getYsbbg() {
		return ysbbg;
	}
	public void setYsbbg(String ysbbg) {
		this.ysbbg = ysbbg;
	}
	@Column(name="BMFZR")
	public String getBmfzr() {
		return bmfzr;
	}
	public void setBmfzr(String bmfzr) {
		this.bmfzr = bmfzr;
	}
	@Column(name="YS")
	public String getYs() {
		return ys;
	}
	public void setYs(String ys) {
		this.ys = ys;
	}
	@Column(name="CG")
	public String getCg() {
		return cg;
	}
	public void setCg(String cg) {
		this.cg = cg;
	}
	@Column(name="KG")
	public String getKg() {
		return kg;
	}
	public void setKg(String kg) {
		this.kg = kg;
	}
	@Column(name="CREATE_TIME")
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	@Column(name="CREATE_USER_ID")
	public String getCreate_user_id() {
		return create_user_id;
	}
	public void setCreate_user_id(String create_user_id) {
		this.create_user_id = create_user_id;
	}
	@Column(name="CREATE_USER_NAME")
	public String getCreate_user_name() {
		return create_user_name;
	}
	public void setCreate_user_name(String create_user_name) {
		this.create_user_name = create_user_name;
	}
	@Column(name="LXRMC")
	public String getLxrmc() {
		return lxrmc;
	}
	public void setLxrmc(String lxrmc) {
		this.lxrmc = lxrmc;
	}
	@Column(name="LXRMC_ID")
	public String getLxrmc_id() {
		return lxrmc_id;
	}
	public void setLxrmc_id(String lxrmc_id) {
		this.lxrmc_id = lxrmc_id;
	}
	@Column(name="LXRBM")
	public String getLxrbm() {
		return lxrbm;
	}
	public void setLxrbm(String lxrbm) {
		this.lxrbm = lxrbm;
	}
	@Column(name="LXRBM_ID")
	public String getLxrbm_id() {
		return lxrbm_id;
	}
	public void setLxrbm_id(String lxrbm_id) {
		this.lxrbm_id = lxrbm_id;
	}
	@Column(name="CREATE_ORG_NAME")
	public String getCreate_org_name() {
		return create_org_name;
	}
	public void setCreate_org_name(String create_org_name) {
		this.create_org_name = create_org_name;
	}
	@Column(name="CREATE_ORG_ID")
	public String getCreate_org_id() {
		return create_org_id;
	}
	public void setCreate_org_id(String create_org_id) {
		this.create_org_id = create_org_id;
	}
	

	@OneToMany(cascade={CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_order_id",insertable = false, updatable = false)
	public List<GoodsAndOrder> getGoodsAndOrder() {
		return goodsAndOrder;
	}
	public void setGoodsAndOrder(List<GoodsAndOrder> goodsAndOrder) {
		this.goodsAndOrder = goodsAndOrder;
	}

	@Column(name="STATE")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}



    @Column(name = "WAREHOUSING_NUM")
    public String getWarehousing_num() {
        return warehousing_num;
    }

    public void setWarehousing_num(String warehousing_num) {
        this.warehousing_num = warehousing_num;
    }
    @Column(name = "BZ_ZT")
	public String getBz_zt() {
		return bz_zt;
	}
	public void setBz_zt(String bz_zt) {
		this.bz_zt = bz_zt;
	}
    
	
}
