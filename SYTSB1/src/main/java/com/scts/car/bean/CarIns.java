package com.scts.car.bean;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_CAR_INS")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarIns implements BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;//主键id

    private String fkCarId;//车辆id
    
    private String fkInsDetailId;//保险详情id

    

    private String dataStatus;//数据状态

    public void setId(String value){
        this.id = value;
    }
    public void setFkCarId(String value){
        this.fkCarId = value;
    }
    public void setFkInsDetailId(String value){
        this.fkInsDetailId = value;
    }
    public void setDataStatus(String value){
        this.dataStatus = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="FK_CAR_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFkCarId(){
        return this.fkCarId;
    }
    @Column(name ="FK_INS_DETAIL_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFkInsDetailId(){
        return this.fkInsDetailId;
    }
    @Column(name ="DATA_STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getDataStatus(){
        return this.dataStatus;
    }


} 
