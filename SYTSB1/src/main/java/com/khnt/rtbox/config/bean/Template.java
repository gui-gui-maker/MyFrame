package com.khnt.rtbox.config.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.khnt.core.crud.bean.BaseEntity;
import java.util.Date;

/*******************************************************************************
 * 
 * <p>
 *  
 * </p>
 * 
 * @ClassName Template
 * @JDK 1.7
 * @author ZMH
 * @date 2019-01-15 10:45:04
 */
@Entity
@Table(name = "RT_TEMPLATE")
public class Template implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private String id;//ID
	private String rtCode;//报表代码
	private String rtName;//报表名称
	private String status;//状态
	private String createdById;//创建人ID
	private String createdBy;//创建人
	private Date createdDate;//创建时间
	private String lastUpdById;//最后更新人ID
	private String lastUpdBy;//最后更新人
	private Date lastUpdDate;//最后时间
	private Integer version;//当前版本

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}
	
	@Column(name="RT_CODE")
	public String getRtCode(){
		return rtCode;
	}
		
	public void setRtCode(String rtCode){
		this.rtCode=rtCode;
	}

	@Column(name="RT_NAME")
	public String getRtName(){
		return rtName;
	}
		
	public void setRtName(String rtName){
		this.rtName=rtName;
	}

	@Column(name="STATUS")
	public String getStatus(){
		return status;
	}
		
	public void setStatus(String status){
		this.status=status;
	}

	@Column(name="CREATED_BY_ID")
	public String getCreatedById(){
		return createdById;
	}
		
	public void setCreatedById(String createdById){
		this.createdById=createdById;
	}

	@Column(name="CREATED_BY")
	public String getCreatedBy(){
		return createdBy;
	}
		
	public void setCreatedBy(String createdBy){
		this.createdBy=createdBy;
	}

	@Column(name="CREATED_DATE")
	public Date getCreatedDate(){
		return createdDate;
	}
		
	public void setCreatedDate(Date createdDate){
		this.createdDate=createdDate;
	}

	@Column(name="LAST_UPD_BY_ID")
	public String getLastUpdById(){
		return lastUpdById;
	}
		
	public void setLastUpdById(String lastUpdById){
		this.lastUpdById=lastUpdById;
	}

	@Column(name="LAST_UPD_BY")
	public String getLastUpdBy(){
		return lastUpdBy;
	}
		
	public void setLastUpdBy(String lastUpdBy){
		this.lastUpdBy=lastUpdBy;
	}

	@Column(name="LAST_UPD_DATE")
	public Date getLastUpdDate(){
		return lastUpdDate;
	}
		
	public void setLastUpdDate(Date lastUpdDate){
		this.lastUpdDate=lastUpdDate;
	}

	
	
	
	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "RT_TEMPLATE:ID="+id;

	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}