package com.fwxm.ret.bean;

import com.fwxm.material.bean.GoodsAndOrder;
import com.khnt.core.crud.bean.BaseEntity;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 存货退还
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "TJY2_CH_RETURN")
public class Tjy2ChReturn implements BaseEntity {
    private String id;
    private String createId;
    private String createName;
    private String createOrgId;
    private String createOrgName;
    private String createUnitId;
    private String createUnitName;
    private Date createTime;

    private String trId; // 退还人id
    private String trName; // 退还人名字
    private String trOrgId; // 退还人部门
    private String trOrgName; // 退还人
    private Date trTime; // 退还时间

    private String trBh;//退还单编号
    private String trRemark; //退还说明
    private String status; //是否出库或删除
    private String lqBh; //退还依据的领取单编号
    private String lqId; //退还依据的领取单Id
    private String ckBh; //退还依据的出库单编号
    private String ckId; //退还依据的出库单Id；
    private List<GoodsAndOrder> goods; //退还的明细

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

    @Column(name = "tr_Id")
    public String getTrId() {
        return trId;
    }

    public void setTrId(String trId) {
        this.trId = trId;
    }

    @Column(name = "tr_Name")
    public String getTrName() {
        return trName;
    }

    public void setTrName(String trName) {
        this.trName = trName;
    }

    @Column(name = "tr_Org_Id")
    public String getTrOrgId() {
        return trOrgId;
    }

    public void setTrOrgId(String trOrgId) {
        this.trOrgId = trOrgId;
    }

    @Column(name = "tr_Org_Name")
    public String getTrOrgName() {
        return trOrgName;
    }

    public void setTrOrgName(String trOrgName) {
        this.trOrgName = trOrgName;
    }

    @Column(name = "tr_Time")
    public Date getTrTime() {
        return trTime;
    }

    public void setTrTime(Date trTime) {
        this.trTime = trTime;
    }

    @Column(name = "tr_Bh")
    public String getTrBh() {
        return trBh;
    }

    public void setTrBh(String trBh) {
        this.trBh = trBh;
    }

    @Column(name = "tr_Remark")
    public String getTrRemark() {
        return trRemark;
    }

    public void setTrRemark(String trRemark) {
        this.trRemark = trRemark;
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

    @Column(name = "lq_Bh")
    public String getLqBh() {
        return lqBh;
    }

    public void setLqBh(String lqBh) {
        this.lqBh = lqBh;
    }

    @Column(name = "lq_Id")
    public String getLqId() {
        return lqId;
    }

    public void setLqId(String lqId) {
        this.lqId = lqId;
    }

    @Column(name = "ck_Bh")
    public String getCkBh() {
        return ckBh;
    }

    public void setCkBh(String ckBh) {
        this.ckBh = ckBh;
    }

    @Column(name = "ck_Id")
    public String getCkId() {
        return ckId;
    }

    public void setCkId(String ckId) {
        this.ckId = ckId;
    }
}