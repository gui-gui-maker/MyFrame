package com.lsts.equipment2.bean;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_EQUIPMENT_BOX_RECORD")
public class EquipmentBoxRecord implements BaseEntity{

    private String id;//主键

    private String motionType;//操作类型

    private String fkMotionId;//操作类型外键ID

    private String fkEquipmentboxId;//设备箱ID

    private String fkEquipmentId;//设备ID

    public void setId(String value){
        this.id = value;
    }
    public void setMotionType(String value){
        this.motionType = value;
    }
    public void setFkMotionId(String value){
        this.fkMotionId = value;
    }
    public void setFkEquipmentboxId(String value){
        this.fkEquipmentboxId = value;
    }
    public void setFkEquipmentId(String value){
        this.fkEquipmentId = value;
    }
    
    
    @Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=false,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="MOTION_TYPE",unique=true,nullable=true,insertable=true,updatable=true,length=20)
    public String getMotionType(){
        return this.motionType;
    }
    @Column(name ="FK_MOTION_ID",unique=true,nullable=true,insertable=true,updatable=true,length=32)
    public String getFkMotionId(){
        return this.fkMotionId;
    }
    @Column(name ="FK_EQUIPMENTBOX_ID",unique=true,nullable=true,insertable=true,updatable=true,length=32)
    public String getFkEquipmentboxId(){
        return this.fkEquipmentboxId;
    }
    @Column(name ="FK_EQUIPMENT_ID",unique=true,nullable=true,insertable=true,updatable=true,length=32)
    public String getFkEquipmentId(){
        return this.fkEquipmentId;
    }


} 
