package com.fwxm.outstorage.bean;

import com.fwxm.material.bean.GoodsAndOrder;
import com.khnt.core.crud.bean.BaseEntity;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 出库单
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "TJY2_CH_OUT")
public class Tjy2ChCk implements BaseEntity {
    private String id;
    private String createId;
    private String createName;
    private String createOrgId;
    private String createOrgName;
    private String createUnitId;
    private String createUnitName;
    private Date createTime;

    private Date lqSqsj; //领取申请时间（Transient属性）
    private String ckdbh; // 出库单编号
    private String lqbm; // 领取部门
    private String lqr; // 领取人
    private Date lqsj; // 领取时间（出库时间）
    private String ckr; // 出库人
    private String lybm; // 领用部门
    private Date lysj; // 领用时间
    private String bmfzr; // 部门负责人
    private String kg;// 库管人

    private String ckyjdh; // 出库依据单号
    private String ckyjtype; // 出库依据类型 TH 退货出库 LQ 出库
    private String status; //
    private String bxStatus;// 报销状态0未报销,1报销中,2已报销,3不可报销
    private String bxId;// 报销id
    private List<GoodsAndOrder> goods;

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

    @Column(name = "CREATE_USER_ID")
    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    @Column(name = "CREATE_USER_NAME")
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

    @Column(name = "ckdbh")
    public String getCkdbh() {
        return ckdbh;
    }

    public void setCkdbh(String ckdbh) {
        this.ckdbh = ckdbh;
    }

    @Column(name = "lqbm")
    public String getLqbm() {
        return lqbm;
    }

    public void setLqbm(String lqbm) {
        this.lqbm = lqbm;
    }

    @Column(name = "lqr")
    public String getLqr() {
        return lqr;
    }

    public void setLqr(String lqr) {
        this.lqr = lqr;
    }

    @Column(name = "lqsj")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getLqsj() {
        return lqsj;
    }

    public void setLqsj(Date lqsj) {
        this.lqsj = lqsj;
    }

    @Column(name = "ckr")
    public String getCkr() {
        return ckr;
    }

    public void setCkr(String ckr) {
        this.ckr = ckr;
    }

    @Column(name = "lybm")
    public String getLybm() {
        return lybm;
    }

    public void setLybm(String lybm) {
        this.lybm = lybm;
    }

    @Column(name = "lysj")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getLysj() {
        return lysj;
    }

    public void setLysj(Date lysj) {
        this.lysj = lysj;
    }

    @Column(name = "bmfzr")
    public String getBmfzr() {
        return bmfzr;
    }

    public void setBmfzr(String bmfzr) {
        this.bmfzr = bmfzr;
    }

    @Column(name = "kg")
    public String getKg() {
        return kg;
    }

    public void setKg(String kg) {
        this.kg = kg;
    }

    @Column(name = "ckyjdh")
    public String getCkyjdh() {
        return ckyjdh;
    }

    public void setCkyjdh(String ckyjdh) {
        this.ckyjdh = ckyjdh;
    }

    @Column(name = "ckyjtype")
    public String getCkyjtype() {
        return ckyjtype;
    }

    public void setCkyjtype(String ckyjtype) {
        this.ckyjtype = ckyjtype;
    }

    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "fk_order_id")
    public List<GoodsAndOrder> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsAndOrder> goods) {
        this.goods = goods;
    }

    @Column(name = "BX_STATUS")
    public String getBxStatus() {
        return bxStatus;
    }

    public void setBxStatus(String bxStatus) {
        this.bxStatus = bxStatus;
    }
    @Column(name = "BX_ID")
    public String getBxId() {
        return bxId;
    }

    public void setBxId(String bxId) {
        this.bxId = bxId;
    }

    @Transient
    public Date getLqSqsj() {
        return lqSqsj;
    }

    public void setLqSqsj(Date lqSqsj) {
        this.lqSqsj = lqSqsj;
    }
}
