package com.fwxm.material.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "TJY2_CH_GOODSANDORDER")
public class GoodsAndOrder implements BaseEntity {
    private static final long serialVersionUID = 1L;

    private String id;
    private String fk_order_id; //相关单据ID
    private Goods goods; //查询
    private String fk_goods_id;//更新
    private Float sl; //数量(填单时候数量)
    private Float sjlqsl;//实际领取数量

    //type是：CK，crk_type是 LQ ，表示领取单出库
    //type是：CK，crk_type是 TH ，表示退货单出库
    //type是：RK，crk_type是 CG ，表示采购单入库
    //type是：RK，crk_type是 TR ，表示退还单入库
    //type是：LQ ，表示领取单
    //type是：TH ，表示领取单出库
    //type是：TR ，表示退还单
    private String type; // CK 出库单 RK 入库单 LQ 领取单 TH 退货单 TR退还单
    private String crk_type; //LQ TH CG TR
    private Date create_time;
    private String status; //99 逻辑删除 ；1（存货入库）保存；2（存货入库）提交可用
    private String fph;//发票号
    private String bz;

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Column(name = "create_time")
    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "FK_ORDER_ID")
    public String getFk_order_id() {
        return fk_order_id;
    }

    public void setFk_order_id(String fk_order_id) {
        this.fk_order_id = fk_order_id;
    }

    @Column(name = "SL")
    public Float getSl() {
        return sl;
    }

    public void setSl(Float sl) {
        this.sl = sl;
    }

    @OneToOne(cascade =CascadeType.REFRESH, orphanRemoval = false)
    @JoinColumn(name = "FK_GOODS_ID",insertable = false, updatable = false)
    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Column(name = "FK_GOODS_ID")
    public String getFk_goods_id() {
        return fk_goods_id;
    }

    public void setFk_goods_id(String fk_goods_id) {
        this.fk_goods_id = fk_goods_id;
    }

    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "CRK_TYPE")
    public String getCrk_type() {
        return crk_type;
    }

    public void setCrk_type(String crk_type) {
        this.crk_type = crk_type;
    }

    @Column(name = "bz")
    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }
    @Column(name = "FPH")
	public String getFph() {
		return fph;
	}

	public void setFph(String fph) {
		this.fph = fph;
	}
	@Column(name="SJLQSL")
	public Float getSjlqsl() {
		return sjlqsl;
	}

	public void setSjlqsl(Float sjlqsl) {
		this.sjlqsl = sjlqsl;
	}
	
    
}
