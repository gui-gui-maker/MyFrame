package com.fwxm.certificate.bean;

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
@Table(name = "TJY2_CERTIFICATE_TRAIN_SUB")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Tjy2CertificateTrainSub implements BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;//${columnb.remarks}

    private String fkCertificateTrainId;//主表外键ID

    private String fkUserId;//人员外键ID

    public void setId(String value){
        this.id = value;
    }
    public void setFkCertificateTrainId(String value){
        this.fkCertificateTrainId = value;
    }
    public void setFkUserId(String value){
        this.fkUserId = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="FK_CERTIFICATE_TRAIN_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFkCertificateTrainId(){
        return this.fkCertificateTrainId;
    }
    @Column(name ="FK_USER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFkUserId(){
        return this.fkUserId;
    }


} 
