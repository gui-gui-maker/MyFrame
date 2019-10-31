package com.fwxm.material.bean;

import com.khnt.core.crud.bean.BaseEntity;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "TJY2_CH_GOODS")
public class Goods implements BaseEntity {
    private static final long serialVersionUID = 1L;
    private String id;
    private String fk_warehousing_id; //32位uuid入库单号
    private String warehousing_num;//系统生成的人眼可识别的入库单号

    private String wpmc;//物品名称
    private String ggjxh;//规格及型号
    private String dw;//单位
    private Double je;//单价
    private Double se;//税额
    private Float sl;//物资数量(库存数量)
    private Float cssl;//物资初始数量
    private Double zje;//总金额
    private String gysmc;//供应商名称
    private Supplier supplier;//供应商
    private Date create_time;//创建时间
    private String create_user_id;//创建人id
    private String create_user_name;//创建人名称
    private String create_org_id; //创建部门id
    private String create_org_name; //创建部门名称
    private String create_unit_id; //创建单位Id
    private String create_unit_name; //创建单位名称
    private String sybm;//使用部门
    private String sybm_id; //使用部门id
    private String ysrqz;//验收人签字
    private String bz;//备注
    private Date rk_time;//入库时间
    private String wplx;//物品类型名称
    private GoodsType goodstype;//物品类型id
    private String state;//状态：99删除;1（存货入库）保存；2（存货入库）提交可用



    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "WPMC")
    public String getWpmc() {
        return wpmc;
    }

    public void setWpmc(String wpmc) {
        this.wpmc = wpmc;
    }

    @Column(name = "GGJXH")
    public String getGgjxh() {
        return ggjxh;
    }

    public void setGgjxh(String ggjxh) {
        this.ggjxh = ggjxh;
    }

    @Column(name = "DW")
    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw;
    }

    @Column(name = "JE")
    public Double getJe() {
        return je;
    }

    public void setJe(Double je) {
        this.je = je;
    }

    @Column(name = "SE")
    public Double getSe() {
        return se;
    }

    public void setSe(Double se) {
        this.se = se;
    }

    @Column(name = "ZJE")
    public Double getZje() {
        return je  * sl;
    }

    public void setZje(Double zje) {
        this.zje = zje;
    }

    @Column(name = "GYSMC")
    public String getGysmc() {
        return gysmc;
    }

    public void setGysmc(String gysmc) {
        this.gysmc = gysmc;
    }

    @ManyToOne
    @JoinColumn(name = "FK_SUPPLIER_ID")
    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Column(name = "CREATE_TIME")
    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    @Column(name = "CREATE_USER_ID")
    public String getCreate_user_id() {
        return create_user_id;
    }

    public void setCreate_user_id(String create_user_id) {
        this.create_user_id = create_user_id;
    }

    @Column(name = "CREATE_USER_NAME")
    public String getCreate_user_name() {
        return create_user_name;
    }

    public void setCreate_user_name(String create_user_name) {
        this.create_user_name = create_user_name;
    }

    @Column(name = "FK_WAREHOUSING_ID")
    public String getFk_warehousing_id() {
        return fk_warehousing_id;
    }

    public void setFk_warehousing_id(String fk_warehousing_id) {
        this.fk_warehousing_id = fk_warehousing_id;
    }

    @Column(name = "SYBM")
    public String getSybm() {
        return sybm;
    }

    public void setSybm(String sybm) {
        this.sybm = sybm;
    }

    @Column(name = "YSRQZ")
    public String getYsrqz() {
        return ysrqz;
    }

    public void setYsrqz(String ysrqz) {
        this.ysrqz = ysrqz;
    }

    @Column(name = "BZ")
    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    @Column(name = "RK_TIME")
    public Date getRk_time() {
        return rk_time;
    }

    public void setRk_time(Date rk_time) {
        this.rk_time = rk_time;
    }

    @Column(name = "WPLX")
    public String getWplx() {
        return wplx;
    }

    public void setWplx(String wplx) {
        this.wplx = wplx;
    }

    @ManyToOne
    @JoinColumn(name = "FK_GOODSTYPE_ID")
    public GoodsType getGoodstype() {
        return goodstype;
    }

    public void setGoodstype(GoodsType goodstype) {
        this.goodstype = goodstype;
    }


    @Column(name = "WAREHOUSING_NUM")
    public String getWarehousing_num() {
        return warehousing_num;
    }

    public void setWarehousing_num(String warehousing_num) {
        this.warehousing_num = warehousing_num;
    }

    @Column(name = "SL")
    public Float getSl() {
        return sl;
    }

    public void setSl(Float sl) {
        this.sl = sl;
    }

    @Column(name = "SYBM_ID")
    public String getSybm_id() {
        return sybm_id;
    }

    public void setSybm_id(String sybm_id) {
        this.sybm_id = sybm_id;
    }

    @Column(name = "STATE")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

    @Column(name = "create_org_id")
    public String getCreate_org_id() {
        return create_org_id;
    }

    public void setCreate_org_id(String create_org_id) {
        this.create_org_id = create_org_id;
    }

    @Column(name = "create_org_name")
    public String getCreate_org_name() {
        return create_org_name;
    }

    public void setCreate_org_name(String create_org_name) {
        this.create_org_name = create_org_name;
    }

    @Column(name = "create_unit_id")
    public String getCreate_unit_id() {
        return create_unit_id;
    }

    public void setCreate_unit_id(String create_unit_id) {
        this.create_unit_id = create_unit_id;
    }

    @Column(name = "create_unit_name")
    public String getCreate_unit_name() {
        return create_unit_name;
    }

    public void setCreate_unit_name(String create_unit_name) {
        this.create_unit_name = create_unit_name;
    }

    @Column(name = "CSSl")
	public Float getCssl() {
		return cssl;
	}

	public void setCssl(Float cssl) {
		this.cssl = cssl;
	}
    
    
}
