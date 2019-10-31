package com.fwxm.recipients.bean;

import com.fwxm.material.bean.GoodsAndOrder;
import com.khnt.core.crud.bean.BaseEntity;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "TJY2_CH_RECIPIENTS")
public class Tjy2ChLq implements BaseEntity {

    private String id;
    private String createId;
    private String createName;
    private String createOrgId;
    private String createOrgName;
    private String createUnitId;
    private String createUnitName;
    private Date createTime;

    private String lqId; // 领取人id
    private String lqName; // 领取人名字
    private String lqOrgId; // 领取人部门
    private String lqOrgName; // 领取人
    private Date lqTime; // 领取时间
    private String ckdBH; //出库单编号
    private String ckdId; //出库单ID

    private String lqBh;//领取单编号
    private String blqbm; //被领取部门 也叫存货来源
    private String blqbmId; //被领取部门ID
    private String lqRemark; //领取用途
    private String lqwp; //领取物品的文字描述
    private String bmfzr; //部门负责人
    private String status; //是否出库或删除
    private List<GoodsAndOrder> goods;
    private Double lqzje; //领取总金额
    private String dataFrom;//0 pc端提交 1微信端提交

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


    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "lq_id")
    public String getLqId() {
        return lqId;
    }

    public void setLqId(String lqId) {
        this.lqId = lqId;
    }

    @Column(name = "lq_name")
    public String getLqName() {
        return lqName;
    }

    public void setLqName(String lqName) {
        this.lqName = lqName;
    }

    @Column(name = "lq_org_id")
    public String getLqOrgId() {
        return lqOrgId;
    }

    public void setLqOrgId(String lqOrgId) {
        this.lqOrgId = lqOrgId;
    }

    @Column(name = "lq_org_name")
    public String getLqOrgName() {
        return lqOrgName;
    }

    public void setLqOrgName(String lqOrgName) {
        this.lqOrgName = lqOrgName;
    }

    @Column(name = "lq_time")
    public Date getLqTime() {
        return lqTime;
    }

    public void setLqTime(Date lqTime) {
        this.lqTime = lqTime;
    }

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "fk_order_id")
    public List<GoodsAndOrder> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsAndOrder> goods) {
        this.goods = goods;
    }

    @Column(name = "LQ_BH")
    public String getLqBh() {
        return lqBh;
    }

    public void setLqBh(String lqBh) {
        this.lqBh = lqBh;
    }

    @Column(name = "blqbm")
    public String getBlqbm() {
        return blqbm;
    }

    public void setBlqbm(String blqbm) {
        this.blqbm = blqbm;
    }

    @Column(name = "blqbm_Id")
    public String getBlqbmId() {
        return blqbmId;
    }

    public void setBlqbmId(String blqbmId) {
        this.blqbmId = blqbmId;
    }

    @Column(name = "lq_remark")
    public String getLqRemark() {
        return lqRemark;
    }

    public void setLqRemark(String lqRemark) {
        this.lqRemark = lqRemark;
    }

    @Column(name = "bmfzr")
    public String getBmfzr() {
        return bmfzr;
    }

    public void setBmfzr(String bmfzr) {
        this.bmfzr = bmfzr;
    }

    @Column(name = "lqwp")
    public String getLqwp() {
        return lqwp;
    }

    public void setLqwp(String lqwp) {
        this.lqwp = lqwp;
    }

    @Column(name = "lqzje")
    public Double getLqzje() {
        return lqzje;
    }

    public void setLqzje(Double lqzje) {
        this.lqzje = lqzje;
    }

    @Column(name = "ckdbh")
    public String getCkdBH() {
        return ckdBH;
    }

    public void setCkdBH(String ckdBH) {
        this.ckdBH = ckdBH;
    }

    @Column(name = "ckdid")
    public String getCkdId() {
        return ckdId;
    }

    public void setCkdId(String ckdId) {
        this.ckdId = ckdId;
    }

    @Column(name = "data_from")
    public String getDataFrom() {
        return dataFrom;
    }

    public void setDataFrom(String dataFrom) {
        this.dataFrom = dataFrom;
    }
}
