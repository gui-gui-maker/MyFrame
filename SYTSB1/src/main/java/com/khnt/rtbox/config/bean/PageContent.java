package com.khnt.rtbox.config.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.khnt.core.crud.bean.BaseEntity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
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
 * @ClassName PageContent
 * @JDK 1.7
 * @author zmh
 * @date 2019-01-14 19:22:34
 */
@Entity
@Table(name = "RT_PAGE_CONTENT")
public class PageContent implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private String id;//ID
	private RtPage rtPage;
	private String rtCode;//报表代码
	private String rtName;//报表名称
	private String pageType;//页码类别(tpl模版页，page1页面1，page2页面2，...)
	private String content;//模版内容
	private String status;//状态
	private String createdById;//创建人ID
	private String createdBy;//创建人
	private Date createdDate;//创建时间
	private String lastUpdById;//最后更新人ID
	private String lastUpdBy;//最后更新人
	private Date lastUpdDate;//最后时间
	private Integer version;//版本号（最大版本号）
	
	private Set<PageContentRecord> pageContentRecords;

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
		
	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pageContent")
	public Set<PageContentRecord> getPageContentRecords() {
		return this.pageContentRecords;
	}

	public void setPageContentRecords(Set<PageContentRecord> pageContentRecords) {
		this.pageContentRecords = pageContentRecords;
	}
	
	
	
	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "RT_PAGE_CONTENT:ID="+id;

	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
