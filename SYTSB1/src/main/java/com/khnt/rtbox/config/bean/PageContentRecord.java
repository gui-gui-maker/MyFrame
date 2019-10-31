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
 * @ClassName PageContentRecord
 * @JDK 1.7
 * @author zmh
 * @date 2019-01-14 19:22:34
 */
@Entity
@Table(name = "RT_PAGE_CONTENT_RECORD")
public class PageContentRecord implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private String id;//ID
	private String rtCode;//报表代码
	private String rtName;//报表名称
	private PageContent pageContent;
	private String pageType;//页码类别(tpl模版页，page1页面1，page2页面2)
	private String content;//模版内容
	private Integer version;//当前版本
	private String createdById;//创建人ID
	private String createdBy;//创建人
	private Date createdDate;//创建时间
	private String lastUpdById;//最后更新人ID
	private String lastUpdBy;//最后更新人
	private Date lastUpdDate;//最后时间
	private Integer bigVersion;//大版本（存磁盘时使用）
	

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_RT_PAGE_CONTENT_ID")
	public PageContent getPageContent() {
		return this.pageContent;
	}

	public void setPageContent(PageContent pageContent) {
		this.pageContent = pageContent;
	}

	
	@Column(name="PAGE_TYPE")
	public String getPageType(){
		return pageType;
	}
		
	public void setPageType(String pageType){
		this.pageType=pageType;
	}

	@Column(name="CONTENT")
	public String getContent(){
		return content;
	}
		
	public void setContent(String content){
		this.content=content;
	}

	@Column(name="VERSION")
	public Integer getVersion(){
		return version;
	}
		
	public void setVersion(Integer version){
		this.version=version;
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
		return "RT_PAGE_CONTENT_RECORD:ID="+id;

	}

	@Column(name="BIG_VERSION")
	public Integer getBigVersion() {
		return bigVersion;
	}

	public void setBigVersion(Integer bigVersion) {
		this.bigVersion = bigVersion;
	}
}
