package com.lsts.finance.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_CW_BANK_CHARDE_RE")
public class CwBankChardeRe implements BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;//id

    private String fkBankDetailId;//银行转账明细ID

    private String fkInspectionInfoId;//收费业务ID

    public void setId(String value){
        this.id = value;
    }
    public void setFkBankDetailId(String value){
        this.fkBankDetailId = value;
    }
    public void setFkInspectionInfoId(String value){
        this.fkInspectionInfoId = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="FK_BANK_DETAIL_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFkBankDetailId(){
        return this.fkBankDetailId;
    }
    @Column(name ="FK_INSPECTION_INFO_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFkInspectionInfoId(){
        return this.fkInspectionInfoId;
    }


} 
