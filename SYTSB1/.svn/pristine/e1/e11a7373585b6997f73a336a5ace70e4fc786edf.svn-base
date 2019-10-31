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
 * @ClassName QualityFilesUpdate
 * @JDK 1.7
 * @author CODER_V3.0
 * @date 2017-05-15 20:47:21
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "TJY2_QUALITY_FILES_UPDATE")
public class QualityManagerFilesUpdate implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private String id;//id
	private String updateNo;//申请单编号
	private String fileNumOld;//更新前文件编号
	private String fileNumNew;//更新后文件编号
	private String fileTypeOld;//更新前文件类别
	private String fileTypeNew;//更新后文件类别
	private String fkQfilesOldId;//被更新体系文件ID
	private String fileNameOld;//文件名称
	private String file_id_old;//被更新附件ID
	private String fkQfilesNewId;//更新后新体系文件ID
	private String fileNameNew;//更新后新体系文件名称
	private String file_id_new;//更新后新体系文件附件Id
	private String updateType;//更新类型 1-体系文件更新 9-体系文件作废
	private String needsUpdate;//需修改的内容
	private String updateOcntent;//修改的内容
	private String updateReasons;//修改理由
	private Date functionDateOld;//旧件开始执行日期
	private Date functionDate;//新文件开始执行日期
	private String remarks;//备注
	private String registrant;//申请人ID
	private String registrantName;//申请人
	private Date registrantTime;//申请时间
	private String auditManId;//审核人ID
	private String auditManName;//审核人姓名
	private Date auditTime;//审核时间
	private String auditDesc;//审核意见
	private String signManId;//批准人ID
	private String signManName;//批准人姓名
	private Date signTime;//批准时间
	private String signDesc;//签发意见
	private String handle_id;//处理人
	private String handle_name;//处理人姓名
	private String handle_status;//处理状态 1审核 2签发 3不通过 4通过
	

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

	@Column(name="FILE_TYPE_OLD")
	public String getFileTypeOld() {
		return fileTypeOld;
	}

	public void setFileTypeOld(String fileTypeOld) {
		this.fileTypeOld = fileTypeOld;
	}

	@Column(name="FILE_TYPE_NEW")
	public String getFileTypeNew() {
		return fileTypeNew;
	}

	public void setFileTypeNew(String fileTypeNew) {
		this.fileTypeNew = fileTypeNew;
	}

	@Column(name="FILE_NUM_OLD")
	public String getFileNumOld() {
		return fileNumOld;
	}

	public void setFileNumOld(String fileNumOld) {
		this.fileNumOld = fileNumOld;
	}

	@Column(name="FILE_NUM_NEW")
	public String getFileNumNew() {
		return fileNumNew;
	}

	public void setFileNumNew(String fileNumNew) {
		this.fileNumNew = fileNumNew;
	}

	@Column(name="UPDATE_NO")
	public String getUpdateNo(){
		return updateNo;
	}
		
	public void setUpdateNo(String updateNo){
		this.updateNo=updateNo;
	}

	@Column(name="FK_QFILES_OLD_ID")
	public String getFkQfilesOldId(){
		return fkQfilesOldId;
	}
		
	public void setFkQfilesOldId(String fkQfilesOldId){
		this.fkQfilesOldId=fkQfilesOldId;
	}

	@Column(name="FILE_NAME_OLD")
	public String getFileNameOld(){
		return fileNameOld;
	}
		
	public void setFileNameOld(String fileNameOld){
		this.fileNameOld=fileNameOld;
	}

	@Column(name="FK_QFILES_NEW_ID")
	public String getFkQfilesNewId(){
		return fkQfilesNewId;
	}
		
	public void setFkQfilesNewId(String fkQfilesNewId){
		this.fkQfilesNewId=fkQfilesNewId;
	}

	@Column(name="FILE_NAME_NEW")
	public String getFileNameNew(){
		return fileNameNew;
	}
		
	public void setFileNameNew(String fileNameNew){
		this.fileNameNew=fileNameNew;
	}

	@Column(name="UPDATE_TYPE")
	public String getUpdateType(){
		return updateType;
	}
		
	public void setUpdateType(String updateType){
		this.updateType=updateType;
	}

	@Column(name="NEEDS_UPDATE")
	public String getNeedsUpdate(){
		return needsUpdate;
	}
		
	public void setNeedsUpdate(String needsUpdate){
		this.needsUpdate=needsUpdate;
	}

	@Column(name="UPDATE_OCNTENT")
	public String getUpdateOcntent(){
		return updateOcntent;
	}
		
	public void setUpdateOcntent(String updateOcntent){
		this.updateOcntent=updateOcntent;
	}

	@Column(name="UPDATE_REASONS")
	public String getUpdateReasons(){
		return updateReasons;
	}
		
	public void setUpdateReasons(String updateReasons){
		this.updateReasons=updateReasons;
	}

	@Column(name="FUNCTION_DATE")
	public Date getFunctionDate(){
		return functionDate;
	}
		
	public void setFunctionDate(Date functionDate){
		this.functionDate=functionDate;
	}
	
	@Column(name="FUNCTION_DATE_OLD")
	public Date getFunctionDateOld() {
		return functionDateOld;
	}

	public void setFunctionDateOld(Date functionDateOld) {
		this.functionDateOld = functionDateOld;
	}

	@Column(name="REMARKS")
	public String getRemarks(){
		return remarks;
	}
		
	public void setRemarks(String remarks){
		this.remarks=remarks;
	}

	@Column(name="REGISTRANT")
	public String getRegistrant(){
		return registrant;
	}
		
	public void setRegistrant(String registrant){
		this.registrant=registrant;
	}

	@Column(name="REGISTRANT_NAME")
	public String getRegistrantName(){
		return registrantName;
	}
		
	public void setRegistrantName(String registrantName){
		this.registrantName=registrantName;
	}

	@Column(name="REGISTRANT_TIME")
	public Date getRegistrantTime(){
		return registrantTime;
	}
		
	public void setRegistrantTime(Date registrantTime){
		this.registrantTime=registrantTime;
	}

	@Column(name="AUDIT_MAN_ID")
	public String getAuditManId(){
		return auditManId;
	}
		
	public void setAuditManId(String auditManId){
		this.auditManId=auditManId;
	}

	@Column(name="AUDIT_MAN_NAME")
	public String getAuditManName(){
		return auditManName;
	}
		
	public void setAuditManName(String auditManName){
		this.auditManName=auditManName;
	}

	@Column(name="AUDIT_TIME")
	public Date getAuditTime(){
		return auditTime;
	}
		
	public void setAuditTime(Date auditTime){
		this.auditTime=auditTime;
	}

	@Column(name="AUDIT_DESC")
	public String getAuditDesc(){
		return auditDesc;
	}
		
	public void setAuditDesc(String auditDesc){
		this.auditDesc=auditDesc;
	}

	@Column(name="SIGN_MAN_ID")
	public String getSignManId(){
		return signManId;
	}
		
	public void setSignManId(String signManId){
		this.signManId=signManId;
	}

	@Column(name="SIGN_MAN_NAME")
	public String getSignManName(){
		return signManName;
	}
		
	public void setSignManName(String signManName){
		this.signManName=signManName;
	}

	@Column(name="SIGN_TIME")
	public Date getSignTime(){
		return signTime;
	}
		
	public void setSignTime(Date signTime){
		this.signTime=signTime;
	}

	@Column(name="SIGN_DESC")
	public String getSignDesc(){
		return signDesc;
	}
		
	public void setSignDesc(String signDesc){
		this.signDesc=signDesc;
	}

	
	
	
	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "TZSB_QUALITY_FILES_UPDATE:ID="+id;

	}

	public String getHandle_name() {
		return handle_name;
	}

	public void setHandle_name(String handle_name) {
		this.handle_name = handle_name;
	}

	public String getHandle_id() {
		return handle_id;
	}

	public void setHandle_id(String handle_id) {
		this.handle_id = handle_id;
	}

	public String getHandle_status() {
		return handle_status;
	}

	public void setHandle_status(String handle_status) {
		this.handle_status = handle_status;
	}

	public String getFile_id_old() {
		return file_id_old;
	}

	public void setFile_id_old(String file_id_old) {
		this.file_id_old = file_id_old;
	}

	public String getFile_id_new() {
		return file_id_new;
	}

	public void setFile_id_new(String file_id_new) {
		this.file_id_new = file_id_new;
	}
	
}
