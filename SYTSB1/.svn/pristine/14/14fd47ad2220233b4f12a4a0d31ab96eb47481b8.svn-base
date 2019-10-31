package com.lsts.finance.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 银行转账历史使用记录
 * CwBankHistory entity. 
 * @author GaoYa
 * @date 2015-11-16 14:35:00
 */
@Entity
@Table(name = "TJY2_CW_BANK_HISTORY")
public class CwBankHistory implements BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;				// id
	private String fk_cw_bank_id;	// 银行转账记录id
    private BigDecimal last_remainMoney;	// 上次剩余金额
    private BigDecimal last_usedMoney;		// 上次冲账金额（上次使用金额）
    private String last_mdy_by;		// 最后修改人id
    private String last_mdy_name;	// 最后修改人姓名
    private Date   last_mdy_date;	// 最后修改时间
    private String remrk;			// 备注

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

	public BigDecimal getLast_remainMoney() {
		return last_remainMoney;
	}

	public void setLast_remainMoney(BigDecimal last_remainMoney) {
		this.last_remainMoney = last_remainMoney;
	}

	public BigDecimal getLast_usedMoney() {
		return last_usedMoney;
	}

	public void setLast_usedMoney(BigDecimal last_usedMoney) {
		this.last_usedMoney = last_usedMoney;
	}

	public String getLast_mdy_by() {
		return last_mdy_by;
	}

	public void setLast_mdy_by(String last_mdy_by) {
		this.last_mdy_by = last_mdy_by;
	}

	public String getLast_mdy_name() {
		return last_mdy_name;
	}

	public void setLast_mdy_name(String last_mdy_name) {
		this.last_mdy_name = last_mdy_name;
	}

	public Date getLast_mdy_date() {
		return last_mdy_date;
	}

	public void setLast_mdy_date(Date last_mdy_date) {
		this.last_mdy_date = last_mdy_date;
	}

	public String getRemrk() {
		return remrk;
	}
	
    public void setRemrk(String value){
        this.remrk = value;
    }

	public String getFk_cw_bank_id() {
		return fk_cw_bank_id;
	}

	public void setFk_cw_bank_id(String fk_cw_bank_id) {
		this.fk_cw_bank_id = fk_cw_bank_id;
	}
    
} 
