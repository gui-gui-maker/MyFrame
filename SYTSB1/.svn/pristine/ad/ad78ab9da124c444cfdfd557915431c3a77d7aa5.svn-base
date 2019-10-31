package com.lsts.finance.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_FCP")
public class Tjy2Fcp implements BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String sysId;//对应sys_userid

    private String sysName;//对应sys_name

    private java.math.BigDecimal averageF;//费用报销费用

    private java.math.BigDecimal averageC;//差旅费人均费

    private java.math.BigDecimal averageP;//培训费人均费

    private java.math.BigDecimal total;//总金额

    private String id;//${columnb.remarks}

    public void setSysId(String value){
        this.sysId = value;
    }
    public void setSysName(String value){
        this.sysName = value;
    }
    public void setAverageF(java.math.BigDecimal value){
        this.averageF = value;
    }
    public void setAverageC(java.math.BigDecimal value){
        this.averageC = value;
    }
    public void setAverageP(java.math.BigDecimal value){
        this.averageP = value;
    }
    public void setTotal(java.math.BigDecimal value){
        this.total = value;
    }
    public void setId(String value){
        this.id = value;
    }
    @Column(name ="SYS_ID",unique=false,nullable=false,insertable=true,updatable=true,length=32)
    public String getSysId(){
        return this.sysId;
    }
    @Column(name ="SYS_NAME",unique=false,nullable=false,insertable=true,updatable=true,length=40)
    public String getSysName(){
        return this.sysName;
    }
    @Column(name ="AVERAGE_F",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getAverageF(){
        return this.averageF;
    }
    @Column(name ="AVERAGE_C",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getAverageC(){
        return this.averageC;
    }
    @Column(name ="AVERAGE_P",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getAverageP(){
        return this.averageP;
    }
    @Column(name ="TOTAL",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getTotal(){
        return this.total;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }


} 
