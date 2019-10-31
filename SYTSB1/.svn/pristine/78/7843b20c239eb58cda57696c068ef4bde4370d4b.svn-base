package com.khnt.rtbox.config.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import com.khnt.core.crud.bean.BaseEntity;
import java.util.Date;


@Entity
@Table(name = "RT_PAGE")
@JsonIgnoreProperties({"template"})
public class RtPage implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;
	private String rtCode;// 报表代码
	private String rtName;// 报表名称
	private String isPage;// 是否分页
	private String rtPageCode;// 页面代码
	private String relTableCode;// 关联表代码
	private String relColCode;// 关联字段代码(默认:FK_REPORT_ID，FK_INSPECTION_INFO_ID)
	private String linkCss;// 引入CSS(全路径以逗号隔开)
	private String linkJs;// 引入JS(全路径以逗号隔开)
	private String savePath;// 保存路径(包括类名方法名)
	private String detailPath;// 详情路径(包括类名方法名)
	private String pagePath;// 页面存放路径
	private String remark;// 备注
	private Date createTime;// 创建时间
	private String status;// 状态
	private String templeteXmlFilePath;// XML模板存放地址
	private String templeteDocFilePath;// DOC模板输出地址
	private String outPutJspFilePath;// JSP模板输出地址
	private String outPutDocDirPath;// DOC导出输出文件夹
	private String deviceType;//设备类别
	private String reportType;//报表类别
	private Integer firstNum;//起始序号
	private String modelType;//模板类型 （0 或者null报告 1原始记录）
	
	// ZMH add 20190115 rtPage 做为模版版本管理
	private String createdById;//创建人ID
	private String createdBy;//创建人
	private Date createdDate;//创建时间
	private String lastUpdById;//最后更新人ID
	private String lastUpdBy;//最后更新人
	private Date lastUpdDate;//最后时间
	private Integer version;//当前版本
	private String rtDirJson;//目录数据
	private Template template;//模版

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "RT_CODE")
	public String getRtCode() {
		return rtCode;
	}

	public void setRtCode(String rtCode) {
		this.rtCode = rtCode;
	}

	@Column(name = "RT_NAME")
	public String getRtName() {
		return rtName;
	}

	public void setRtName(String rtName) {
		this.rtName = rtName;
	}

	@Column(name = "IS_PAGE")
	public String getIsPage() {
		return isPage;
	}

	public void setIsPage(String isPage) {
		this.isPage = isPage;
	}

	@Column(name = "RT_PAGE_CODE")
	public String getRtPageCode() {
		return rtPageCode;
	}

	public void setRtPageCode(String rtPageCode) {
		this.rtPageCode = rtPageCode;
	}

	@Column(name = "REL_TABLE_CODE")
	public String getRelTableCode() {
		return relTableCode;
	}

	public void setRelTableCode(String relTableCode) {
		this.relTableCode = relTableCode;
	}

	@Column(name = "REL_COL_CODE")
	public String getRelColCode() {
		return relColCode;
	}

	public void setRelColCode(String relColCode) {
		this.relColCode = relColCode;
	}

	@Column(name = "LINK_CSS")
	public String getLinkCss() {
		return linkCss;
	}

	public void setLinkCss(String linkCss) {
		this.linkCss = linkCss;
	}

	@Column(name = "LINK_JS")
	public String getLinkJs() {
		return linkJs;
	}

	public void setLinkJs(String linkJs) {
		this.linkJs = linkJs;
	}

	@Column(name = "SAVE_PATH")
	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	@Column(name = "DETAIL_PATH")
	public String getDetailPath() {
		return detailPath;
	}

	public void setDetailPath(String detailPath) {
		this.detailPath = detailPath;
	}

	@Column(name = "PAGE_PATH")
	public String getPagePath() {
		return pagePath;
	}

	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "TEMPLETE_XML_FILE_PATH")
	public String getTempleteXmlFilePath() {
		return templeteXmlFilePath;
	}

	public void setTempleteXmlFilePath(String templeteXmlFilePath) {
		this.templeteXmlFilePath = templeteXmlFilePath;
	}

	@Column(name = "TEMPLETE_DOC_FILE_PATH")
	public String getTempleteDocFilePath() {
		return templeteDocFilePath;
	}

	public void setTempleteDocFilePath(String templeteDocFilePath) {
		this.templeteDocFilePath = templeteDocFilePath;
	}

	@Column(name = "OUT_PUT_DOC_DIR_PATH")
	public String getOutPutDocDirPath() {
		return outPutDocDirPath;
	}

	public void setOutPutDocDirPath(String outPutDocDirPath) {
		this.outPutDocDirPath = outPutDocDirPath;
	}

	@Column(name = "OUT_PUT_JSP_FILE_PATH")
	public String getOutPutJspFilePath() {
		return outPutJspFilePath;
	}

	public void setOutPutJspFilePath(String outPutJspFilePath) {
		this.outPutJspFilePath = outPutJspFilePath;
	}
	
	
	@Column(name = "DEVICE_TYPE")
	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	@Column(name = "REPORT_TYPE")
	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	@Column(name="FIRST_NUM")
	public Integer getFirstNum() {
		return firstNum;
	}

	public void setFirstNum(Integer firstNum) {
		this.firstNum = firstNum;
	}
	
	@Column(name="MODEL_TYPE")
	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
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

	@Column(name="VERSION")
	public Integer getVersion(){
		return version;
	}
		
	public void setVersion(Integer version){
		this.version=version;
	}
	
	@Column(name="RT_DIR_JSON")
	public String getRtDirJson(){
		return rtDirJson;
	}
		
	public void setRtDirJson(String rtDirJson){
	this.rtDirJson=rtDirJson;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_RT_TEMPLATE_ID")
	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}
	
	public String toString() {
		return "RT_PAGE:ID=" + id;
	}
}
