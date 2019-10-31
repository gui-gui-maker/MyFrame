package com.khnt.rtbox.config.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.khnt.core.crud.bean.BaseEntity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import java.util.Date;

/*******************************************************************************
 * 
 * <p>
 *  
 * </p>
 * 
 * @ClassName PageDirVersion
 * @JDK 1.7
 * @author CODER_V3.0
 * @date 2019-01-17 10:14:06
 */
@Entity
@Table(name = "RT_PAGE_DIR_VERSION")
public class PageDirVersion implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private String id;//ID
	private RtPage rtPage;
	private String rtCode;//报表代码
	private String rtName;//报表名称
	private Integer bigVersion;//模版版本号
	private String rtDirJson;//目录数据
	private Integer version;//版本号
	private String status;//状态
	private String createdById;//创建人ID
	private String createdBy;//创建人
	private Date createdDate;//创建时间
	private String lastUpdById;//最后更新人ID
	private String lastUpdBy;//最后更新人
	private Date lastUpdDate;//最后时间

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_RT_PAGE_ID")
	public RtPage getRtPage() {
		return this.rtPage;
	}

	public void setRtPage(RtPage rtPage) {
		this.rtPage = rtPage;
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

	@Column(name="BIG_VERSION")
	public Integer getBigVersion(){
		return bigVersion;
	}
		
	public void setBigVersion(Integer bigVersion){
		this.bigVersion=bigVersion;
	}

	@Column(name="RT_DIR_JSON")
	public String getRtDirJson(){
		return rtDirJson;
	}
		
	public void setRtDirJson(String rtDirJson){
		this.rtDirJson=rtDirJson;
	}

	@Column(name="VERSION")
	public Integer getVersion(){
		return version;
	}
		
	public void setVersion(Integer version){
		this.version=version;
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
		return "RT_PAGE_DIR_VERSION:ID="+id;

	}
}
