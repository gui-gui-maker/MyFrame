package com.lsts.qualitymanage.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import com.khnt.core.crud.bean.BaseEntity;
import java.util.Date;

/*******************************************************************************
 * 
 * <p>
 *  
 * </p>
 * 
 * @ClassName QualityFiles
 * @JDK 1.7
 * @author CODER_V3.0
 * @date 2017-05-15 20:47:21
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "TJY2_QUALITY_MANAGER_FILES")
public class QualityManagerFiles implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private String id;//
	private String fileNum;//文件编号
	private String fileName;//文件名字
	private String fileType;//质量体系类别
	private String authority;//权限
	private String departmentId;//部门id
	private String department;//部门
	private String registrant;//登记人
	private String registrantId;//登记人id
	private Date implementDate;//实施日期
	private String filePath;//保存在硬盘上的路径
	private Date uploadTime;//上传时间
	private String status;//文件的状态 0-未发布 1-发布 2-修订中 5-已归档（被替换） 9 - 作废
	private String delManId;//作废人ID
	private String delManName;//作废人名字
	private Date delDate;//作废时间
	private Date archiveDate;//归档时间 - 申请修改通过后，本条记录状态变成归档，并记录归档时间
	private String fileId;//附件文件id
	
	private String deviceType;
	private String reportId;
	private String reportName;
	
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
	public String getId(){
		return id;
	}
		
	public void setId(String id){
		this.id=id;
	}

	@Column(name="FILE_ID")
	public String getFileId(){
		return fileId;
	}
		
	public void setFileId(String fileId){
		this.fileId=fileId;
	}

	@Column(name="FILE_NAME")
	public String getFileName(){
		return fileName;
	}
		
	public void setFileName(String fileName){
		this.fileName=fileName;
	}

	@Column(name="FILE_TYPE")
	public String getFileType(){
		return fileType;
	}
		
	public void setFileType(String fileType){
		this.fileType=fileType;
	}

	@Column(name="AUTHORITY")
	public String getAuthority(){
		return authority;
	}
		
	public void setAuthority(String authority){
		this.authority=authority;
	}

	@Column(name="DEPARTMENT_ID")
	public String getDepartmentId(){
		return departmentId;
	}
		
	public void setDepartmentId(String departmentId){
		this.departmentId=departmentId;
	}

	@Column(name="DEPARTMENT")
	public String getDepartment(){
		return department;
	}
		
	public void setDepartment(String department){
		this.department=department;
	}

	@Column(name="REGISTRANT")
	public String getRegistrant(){
		return registrant;
	}
		
	public void setRegistrant(String registrant){
		this.registrant=registrant;
	}

	@Column(name="REGISTRANT_ID")
	public String getRegistrantId(){
		return registrantId;
	}
		
	public void setRegistrantId(String registrantId){
		this.registrantId=registrantId;
	}

	@Column(name="IMPLEMENT_DATE")
	public Date getImplementDate(){
		return implementDate;
	}
		
	public void setImplementDate(Date implementDate){
		this.implementDate=implementDate;
	}

	@Column(name="FILE_PATH")
	public String getFilePath(){
		return filePath;
	}
		
	public void setFilePath(String filePath){
		this.filePath=filePath;
	}

	@Column(name="UPLOAD_TIME")
	public Date getUploadTime(){
		return uploadTime;
	}
		
	public void setUploadTime(Date uploadTime){
		this.uploadTime=uploadTime;
	}

	@Column(name="STATUS")
	public String getStatus(){
		return status;
	}
		
	public void setStatus(String status){
		this.status=status;
	}

	@Column(name="DEL_MAN_ID")
	public String getDelManId(){
		return delManId;
	}
		
	public void setDelManId(String delManId){
		this.delManId=delManId;
	}

	@Column(name="DEL_MAN_NAME")
	public String getDelManName(){
		return delManName;
	}
		
	public void setDelManName(String delManName){
		this.delManName=delManName;
	}

	@Column(name="DEL_DATE")
	public Date getDelDate(){
		return delDate;
	}
		
	public void setDelDate(Date delDate){
		this.delDate=delDate;
	}

	@Column(name="ARCHIVE_DATE")
	public Date getArchiveDate(){
		return archiveDate;
	}
		
	public void setArchiveDate(Date archiveDate){
		this.archiveDate=archiveDate;
	}

	@Column(name="REPORT_ID")
	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	@Column(name="REPORT_NAME")
	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	
	@Column(name="device_type")
	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "TZSB_QUALITY_FILES:ID="+id;

	}
	@Column(name="FILE_NUM")
	public String getFileNum() {
		return fileNum;
	}

	public void setFileNum(String fileNum) {
		this.fileNum = fileNum;
	}
}
