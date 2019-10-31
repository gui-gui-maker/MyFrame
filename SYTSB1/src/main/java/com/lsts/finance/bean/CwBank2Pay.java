package com.lsts.finance.bean;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 银行转账与收费业务关联信息表
 * CwBank2Pay entity. 
 * @author GaoYa
 * @date 2015-11-18 10:15:00
 */
@Entity
@Table(name = "TJY2_CW_BANK2PAY")
public class CwBank2Pay implements BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;				// id
	private String fk_cw_bank_id;	// 银行转账记录id
    private String fk_inspection_pay_id;	// 收费业务id
    private BigDecimal cur_used_money;		// 本次收费金额
    private String pay_remark;			// 备注

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

	public String getFk_inspection_pay_id() {
		return fk_inspection_pay_id;
	}

	public void setFk_inspection_pay_id(String fk_inspection_pay_id) {
		this.fk_inspection_pay_id = fk_inspection_pay_id;
	}

	public BigDecimal getCur_used_money() {
		return cur_used_money;
	}

	public void setCur_used_money(BigDecimal cur_used_money) {
		this.cur_used_money = cur_used_money;
	}
	
	public String getFk_cw_bank_id() {
		return fk_cw_bank_id;
	}

	public void setFk_cw_bank_id(String fk_cw_bank_id) {
		this.fk_cw_bank_id = fk_cw_bank_id;
	}

	public String getPay_remark() {
		return pay_remark;
	}

	public void setPay_remark(String pay_remark) {
		this.pay_remark = pay_remark;
	}

} 
