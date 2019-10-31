package com.lsts.qualitymanage.bean;

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
@Table(name = "TJY2_QUALITY_UPDATE_FILE")
@JsonIgnoreProperties(ignoreUnknown = true)
public class QualityUpdateFile implements BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;//id

    private String fileName;//文件名称

    private String fileId;//文件编号

    private String needsUpdate;//需修改的内容

    private String updateOcntent;//修改的内容

    private String updateReasons;//修改理由

    private java.util.Date materialDate;//执行日期

    private String status="0";//状态

    private String registrant;//登记人

    private java.util.Date registrantTime;//登记时间

    private String identifier;//申请单编号

    private String projectNumber;//项目编号

    private String department;//负责部门

    private String projectLeader;//项目负责人

    private String fileProperties;//文件性质(方案,报告,技术文件,质量管理文件,其它 )

    private String testCase;//检验案例(有，无)

    private String identifierC;//传递单编号

    private String projectsName;//项目名称
   
    private String fileIdCdd;//传递单文件编号
    
    private String registrantId;//登记人ID
    
    private String remarks;//备注
    
    private java.util.Date passTime;//审核通过的日期

    private String qualityXybzFileId;//质量体系文件的id
   
    private String departmentId;//负责部门id

    private String projectLeaderId;//项目负责人id
    //申请单审核
    private String current_man;//编制人
    private java.util.Date current_mandate;//编制人日期
    private String sh_man;//审核人
    private java.util.Date sh_mandate;//审核人日期
    private String pz_man;//批准人
    private java.util.Date pz_mandate;//批准人日期
    //传递单审核
    private String cddYj;//传递单审核意见
    private String cddYjMan;//传递单审核意见人
    private java.util.Date cddYjDate;//传递单审核意见时间
    private String cddYj2;//传递单审核复审意见
    private String cddYjMan2;//传递单审核复审意见人
    private java.util.Date cddYjDate2;//传递单审核复审意见时间
    private java.util.Date cddJsdate;//传递单审批接受日期
    private java.util.Date cddFhdate;//传递单审批返还日期
    private String cddSpyj;//传递单审批意见
    private String cddSpyjMan;//传递单审批意见人
    private java.util.Date cddSpyjDate;//传递单审批意见时间
    private String cddSpyj2;//传递单审批复审意见
    private String cddSpyjMan2;//传递单审批复审意见人
    private java.util.Date cddSpyjDate2;//传递单审批复审意见时间
    private java.util.Date cddJsdate2;//传递单接受意见接受日期
    private java.util.Date cddCddate;//传递单接受意见存档日期
    private String cddJsyj;//传递单接受意见
    private String cddJsbm;//传递单接受意见部门
    private String cddJsjb;//传递单接受意见经办
    private java.util.Date cddJsdate3;//传递单接受意见时间

    //private String filePath;//文件path

	public void setCddJsdate2(java.util.Date cddJsdate2) {
		this.cddJsdate2 = cddJsdate2;
	}
	public void setCddCddate(java.util.Date cddCddate) {
		this.cddCddate = cddCddate;
	}
	public void setCddJsyj(String cddJsyj) {
		this.cddJsyj = cddJsyj;
	}
	public void setCddJsbm(String cddJsbm) {
		this.cddJsbm = cddJsbm;
	}
	public void setCddJsjb(String cddJsjb) {
		this.cddJsjb = cddJsjb;
	}
	public void setCddJsdate3(java.util.Date cddJsdate3) {
		this.cddJsdate3 = cddJsdate3;
	}
	public void setCddSpyjMan(String cddSpyjMan) {
		this.cddSpyjMan = cddSpyjMan;
	}
	public void setCddSpyj(String cddSpyj) {
		this.cddSpyj = cddSpyj;
	}
	public void setCddSpyjDate(java.util.Date cddSpyjDate) {
		this.cddSpyjDate = cddSpyjDate;
	}
	public void setCddSpyj2(String cddSpyj2) {
		this.cddSpyj2 = cddSpyj2;
	}
	public void setCddSpyjMan2(String cddSpyjMan2) {
		this.cddSpyjMan2 = cddSpyjMan2;
	}
	public void setCddSpyjDate2(java.util.Date cddSpyjDate2) {
		this.cddSpyjDate2 = cddSpyjDate2;
	}
	public void setCddJsdate(java.util.Date cddJsdate) {
		this.cddJsdate = cddJsdate;
	}
	public void setCddFhdate(java.util.Date cddFhdate) {
		this.cddFhdate = cddFhdate;
	}
	public void setCddYj(String cddYj) {
		this.cddYj = cddYj;
	}
	public void setCddYjMan(String cddYjMan) {
		this.cddYjMan = cddYjMan;
	}
	public void setCddYjDate(java.util.Date cddYjDate) {
		this.cddYjDate = cddYjDate;
	}
	public void setCddYj2(String cddYj2) {
		this.cddYj2 = cddYj2;
	}
	public void setCddYjMan2(String cddYjMan2) {
		this.cddYjMan2 = cddYjMan2;
	}
	public void setCddYjDate2(java.util.Date cddYjDate2) {
		this.cddYjDate2 = cddYjDate2;
	}
	public void setCurrent_man(String current_man) {
		this.current_man = current_man;
	}
	public void setCurrent_mandate(java.util.Date current_mandate) {
		this.current_mandate = current_mandate;
	}
	public void setSh_man(String sh_man) {
		this.sh_man = sh_man;
	}
	public void setSh_mandate(java.util.Date sh_mandate) {
		this.sh_mandate = sh_mandate;
	}
	public void setPz_man(String pz_man) {
		this.pz_man = pz_man;
	}
	public void setPz_mandate(java.util.Date pz_mandate) {
		this.pz_mandate = pz_mandate;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public void setProjectLeaderId(String projectLeaderId) {
		this.projectLeaderId = projectLeaderId;
	}
	public void setQualityXybzFileId(String value){
        this.qualityXybzFileId = value;
    }
	public void setPassTime(java.util.Date passTime) {
		this.passTime = passTime;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public void setRegistrantId(String registrantId) {
		this.registrantId = registrantId;
	}
	public void setFileIdCdd(String fileIdCdd) {
		this.fileIdCdd = fileIdCdd;
	}
	public void setProjectsName(String projectsName) {
		this.projectsName = projectsName;
	}
	public void setId(String value){
        this.id = value;
    }
    public void setFileName(String value){
        this.fileName = value;
    }
    public void setFileId(String value){
        this.fileId = value;
    }
    public void setNeedsUpdate(String value){
        this.needsUpdate = value;
    }
    public void setUpdateOcntent(String value){
        this.updateOcntent = value;
    }
    public void setUpdateReasons(String value){
        this.updateReasons = value;
    }
    public void setMaterialDate(java.util.Date value){
        this.materialDate = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    public void setRegistrant(String value){
        this.registrant = value;
    }
    public void setRegistrantTime(java.util.Date value){
        this.registrantTime = value;
    }
    public void setIdentifier(String value){
        this.identifier = value;
    }
    public void setProjectNumber(String value){
        this.projectNumber = value;
    }
    public void setDepartment(String value){
        this.department = value;
    }
    public void setProjectLeader(String value){
        this.projectLeader = value;
    }
    public void setFileProperties(String value){
        this.fileProperties = value;
    }
    public void setTestCase(String value){
        this.testCase = value;
    }
    public void setIdentifierC(String value){
        this.identifierC = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="FILE_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getFileName(){
        return this.fileName;
    }
    @Column(name ="FILE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFileId(){
        return this.fileId;
    }
    @Column(name ="NEEDS_UPDATE",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getNeedsUpdate(){
        return this.needsUpdate;
    }
    @Column(name ="UPDATE_OCNTENT",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getUpdateOcntent(){
        return this.updateOcntent;
    }
    @Column(name ="UPDATE_REASONS",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getUpdateReasons(){
        return this.updateReasons;
    }
    @Column(name ="MATERIAL_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getMaterialDate(){
        return this.materialDate;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="REGISTRANT",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getRegistrant(){
        return this.registrant;
    }
    @Column(name ="REGISTRANT_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getRegistrantTime(){
        return this.registrantTime;
    }
    @Column(name ="IDENTIFIER",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getIdentifier(){
        return this.identifier;
    }
    @Column(name ="PROJECT_NUMBER",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getProjectNumber(){
        return this.projectNumber;
    }
    @Column(name ="DEPARTMENT",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getDepartment(){
        return this.department;
    }
    @Column(name ="PROJECT_LEADER",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getProjectLeader(){
        return this.projectLeader;
    }
    @Column(name ="FILE_PROPERTIES",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getFileProperties(){
        return this.fileProperties;
    }
    @Column(name ="TEST_CASE",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getTestCase(){
        return this.testCase;
    }
    @Column(name ="IDENTIFIER_C",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getIdentifierC(){
        return this.identifierC;
    }
    @Column(name ="PROJECTS_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=400)
   	public String getProjectsName() {
   		return projectsName;
   	}
    @Column(name ="FILE_ID_CDD",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFileIdCdd() {
		return fileIdCdd;
	}
    @Column(name ="REGISTRANT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getRegistrantId() {
		return registrantId;
	}
    @Column(name ="REMARKS",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getRemarks() {
		return remarks;
	}
    @Column(name ="PASS_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getPassTime() {
		return passTime;
	}
    @Column(name ="QUALITY_XYBZ_FILE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getQualityXybzFileId(){
        return this.qualityXybzFileId;
    }
    @Column(name ="DEPARTMENT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getDepartmentId() {
		return departmentId;
	}
    @Column(name ="PROJECT_LEADER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
	public String getProjectLeaderId() {
		return projectLeaderId;
	}
    @Column(name ="CURRENT_MAN",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getCurrent_man() {
		return current_man;
	}
    @Column(name ="CURRENT_MANDATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	public java.util.Date getCurrent_mandate() {
		return current_mandate;
	}
    @Column(name ="SH_MAN",unique=false,nullable=true,insertable=true,updatable=true,length=200)
	public String getSh_man() {
		return sh_man;
	}
    @Column(name ="SH_MANDATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	public java.util.Date getSh_mandate() {
		return sh_mandate;
	}
    @Column(name ="PZ_MAN",unique=false,nullable=true,insertable=true,updatable=true,length=200)
	public String getPz_man() {
		return pz_man;
	}
    @Column(name ="PZ_MANDATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	public java.util.Date getPz_mandate() {
		return pz_mandate;
	}
    @Column(name ="CDD_YJ",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getCddYj() {
		return cddYj;
	}
    @Column(name ="CDD_YJ_MAN",unique=false,nullable=true,insertable=true,updatable=true,length=200)
	public String getCddYjMan() {
		return cddYjMan;
	}
    @Column(name ="CDD_YJ_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	public java.util.Date getCddYjDate() {
		return cddYjDate;
	}
    @Column(name ="CDD_YJ2",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
	public String getCddYj2() {
		return cddYj2;
	}
    @Column(name ="CDD_YJ_MAN2",unique=false,nullable=true,insertable=true,updatable=true,length=200)
	public String getCddYjMan2() {
		return cddYjMan2;
	}
    @Column(name ="CDD_YJ_DATE2",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	public java.util.Date getCddYjDate2() {
		return cddYjDate2;
	}
    @Column(name ="CDD_JSDATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getCddJsdate() {
		return cddJsdate;
	}
    @Column(name ="CDD_FHDATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	public java.util.Date getCddFhdate() {
		return cddFhdate;
	}
    @Column(name ="CDD_SPYJ",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
	public String getCddSpyj() {
		return cddSpyj;
	}
    @Column(name ="CDD_SPYJ_MAN",unique=false,nullable=true,insertable=true,updatable=true,length=200)
	public String getCddSpyjMan() {
		return cddSpyjMan;
	}
    @Column(name ="CDD_SPYJ_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	public java.util.Date getCddSpyjDate() {
		return cddSpyjDate;
	}
    @Column(name ="CDD_SPYJ2",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
	public String getCddSpyj2() {
		return cddSpyj2;
	}
    @Column(name ="CDD_SPYJ_MAN2",unique=false,nullable=true,insertable=true,updatable=true,length=200)
	public String getCddSpyjMan2() {
		return cddSpyjMan2;
	}
    @Column(name ="CDD_SPYJ_DATE2",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	public java.util.Date getCddSpyjDate2() {
		return cddSpyjDate2;
	}
    @Column(name ="CDD_JSDATE2",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	public java.util.Date getCddJsdate2() {
		return cddJsdate2;
	}
    @Column(name ="CDD_CDDATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	public java.util.Date getCddCddate() {
		return cddCddate;
	}
    @Column(name ="CDD_JSYJ",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
	public String getCddJsyj() {
		return cddJsyj;
	}
    @Column(name ="CDD_JSBM",unique=false,nullable=true,insertable=true,updatable=true,length=200)
	public String getCddJsbm() {
		return cddJsbm;
	}
    @Column(name ="CDD_JSJB",unique=false,nullable=true,insertable=true,updatable=true,length=200)
	public String getCddJsjb() {
		return cddJsjb;
	}
    @Column(name ="CDD_JSDATE3",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	public java.util.Date getCddJsdate3() {
		return cddJsdate3;
	}
} 
