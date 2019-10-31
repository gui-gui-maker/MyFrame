package com.lsts.humanresources.bean;

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
@Table(name = "TJY2_RL_INSPECTION_DECLARE")
@JsonIgnoreProperties(ignoreUnknown = true)
public class InspectionDeclare implements BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;//${columnb.remarks}

    private String empId;//申报人ID

    private String empName;//申报人姓名

    private String empSex;//性别

    private java.util.Date empBirthDate;//出生年月

    private String empEducation;//学历

    private java.util.Date intoWorkDate;//进院时间

    private String position;//工作岗位

    private String graduateSchool;//毕业院校

    private String empTitle;//职称

    private String workDepartment;//工作部门ID

    private String workDepartmentName;//工作部门

    private String postLevel;//申报岗位级别

    private String intoInspectionYears;//从事检验岗位时间

    private String certificate1;//持主检验员

    private java.util.Date certificate1Date;//初次取证时间

    private String certificate2;//持其他检验员

    private java.util.Date certificate2Date;//取证时间

    private String certificate3;//持其他检验员

    private java.util.Date certificate3Date;//取证时间

    private String reward;//年度考核获得院表彰情况

    private String directorOpinion;//部门主任审核意见

    private String directorId;//部门主任ID

    private String directorName;//部门主任姓名

    private java.util.Date directorDate;//部门主任审核日期

    private String qualityDirectorOpinion;//质量部主任审核意见

    private String qualityDirectorId;//质量部主任ID

    private String qualityDirectorName;//质量部主任姓名

    private java.util.Date qualityDirectorDate;//质量部主任审核日期

    private String scientificDirectorOpinion;//科管部主任审核意见

    private String scientificDirectorId;//科管部主任ID

    private String scientificDirectorName;//科管部主任姓名

    private java.util.Date scientificDirectorDate;//科管部主任审核日期
    
    private String status;//申请状态

    private String createId;//创建人ID

    private String createBy;//创建人

    private java.util.Date createDate;//创建时间

    private String lastModifyId;//最近修改人ID

    private String lastModifyBy;//最近修改人

    private java.util.Date lastModifyDate;//最近修改时间

    public void setId(String value){
        this.id = value;
    }
    public void setEmpId(String value){
        this.empId = value;
    }
    public void setEmpName(String value){
        this.empName = value;
    }
    public void setEmpSex(String value){
        this.empSex = value;
    }
    public void setEmpBirthDate(java.util.Date value){
        this.empBirthDate = value;
    }
    public void setEmpEducation(String value){
        this.empEducation = value;
    }
    public void setIntoWorkDate(java.util.Date value){
        this.intoWorkDate = value;
    }
    public void setPosition(String value){
        this.position = value;
    }
    public void setGraduateSchool(String value){
        this.graduateSchool = value;
    }
    public void setEmpTitle(String value){
        this.empTitle = value;
    }
    public void setWorkDepartment(String value){
        this.workDepartment = value;
    }
    public void setWorkDepartmentName(String value){
        this.workDepartmentName = value;
    }
    public void setPostLevel(String value){
        this.postLevel = value;
    }
    public void setIntoInspectionYears(String value){
        this.intoInspectionYears = value;
    }
    public void setCertificate1(String value){
        this.certificate1 = value;
    }
    public void setCertificate1Date(java.util.Date value){
        this.certificate1Date = value;
    }
    public void setCertificate2(String value){
        this.certificate2 = value;
    }
    public void setCertificate2Date(java.util.Date value){
        this.certificate2Date = value;
    }
    public void setCertificate3(String value){
        this.certificate3 = value;
    }
    public void setCertificate3Date(java.util.Date value){
        this.certificate3Date = value;
    }
    public void setReward(String value){
        this.reward = value;
    }
    public void setDirectorOpinion(String value){
        this.directorOpinion = value;
    }
    public void setDirectorId(String value){
        this.directorId = value;
    }
    public void setDirectorName(String value){
        this.directorName = value;
    }
    public void setDirectorDate(java.util.Date value){
        this.directorDate = value;
    }
    public void setQualityDirectorOpinion(String value){
        this.qualityDirectorOpinion = value;
    }
    public void setQualityDirectorId(String value){
        this.qualityDirectorId = value;
    }
    public void setQualityDirectorName(String value){
        this.qualityDirectorName = value;
    }
    public void setQualityDirectorDate(java.util.Date value){
        this.qualityDirectorDate = value;
    }
    public void setScientificDirectorOpinion(String value){
        this.scientificDirectorOpinion = value;
    }
    public void setScientificDirectorId(String value){
        this.scientificDirectorId = value;
    }
    public void setScientificDirectorName(String value){
        this.scientificDirectorName = value;
    }
    public void setScientificDirectorDate(java.util.Date value){
        this.scientificDirectorDate = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    public void setCreateId(String value){
        this.createId = value;
    }
    public void setCreateBy(String value){
        this.createBy = value;
    }
    public void setCreateDate(java.util.Date value){
        this.createDate = value;
    }
    public void setLastModifyId(String value){
        this.lastModifyId = value;
    }
    public void setLastModifyBy(String value){
        this.lastModifyBy = value;
    }
    public void setLastModifyDate(java.util.Date value){
        this.lastModifyDate = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="EMP_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getEmpId(){
        return this.empId;
    }
    @Column(name ="EMP_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=128)
    public String getEmpName(){
        return this.empName;
    }
    @Column(name ="EMP_SEX",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getEmpSex(){
        return this.empSex;
    }
    @Column(name ="EMP_BIRTH_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getEmpBirthDate(){
        return this.empBirthDate;
    }
    @Column(name ="EMP_EDUCATION",unique=false,nullable=true,insertable=true,updatable=true,length=128)
    public String getEmpEducation(){
        return this.empEducation;
    }
    @Column(name ="INTO_WORK_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getIntoWorkDate(){
        return this.intoWorkDate;
    }
    @Column(name ="POSITION",unique=false,nullable=true,insertable=true,updatable=true,length=128)
    public String getPosition(){
        return this.position;
    }
    @Column(name ="GRADUATE_SCHOOL",unique=false,nullable=true,insertable=true,updatable=true,length=128)
    public String getGraduateSchool(){
        return this.graduateSchool;
    }
    @Column(name ="EMP_TITLE",unique=false,nullable=true,insertable=true,updatable=true,length=128)
    public String getEmpTitle(){
        return this.empTitle;
    }
    @Column(name ="WORK_DEPARTMENT",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getWorkDepartment(){
        return this.workDepartment;
    }
    @Column(name ="WORK_DEPARTMENT_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=128)
    public String getWorkDepartmentName(){
        return this.workDepartmentName;
    }
    @Column(name ="POST_LEVEL",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getPostLevel(){
        return this.postLevel;
    }
    @Column(name ="INTO_INSPECTION_YEARS",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getIntoInspectionYears(){
        return this.intoInspectionYears;
    }
    @Column(name ="CERTIFICATE1",unique=false,nullable=true,insertable=true,updatable=true,length=128)
    public String getCertificate1(){
        return this.certificate1;
    }
    @Column(name ="CERTIFICATE1_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getCertificate1Date(){
        return this.certificate1Date;
    }
    @Column(name ="CERTIFICATE2",unique=false,nullable=true,insertable=true,updatable=true,length=128)
    public String getCertificate2(){
        return this.certificate2;
    }
    @Column(name ="CERTIFICATE2_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getCertificate2Date(){
        return this.certificate2Date;
    }
    @Column(name ="CERTIFICATE3",unique=false,nullable=true,insertable=true,updatable=true,length=128)
    public String getCertificate3(){
        return this.certificate3;
    }
    @Column(name ="CERTIFICATE3_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getCertificate3Date(){
        return this.certificate3Date;
    }
    @Column(name ="REWARD",unique=false,nullable=true,insertable=true,updatable=true,length=3000)
    public String getReward(){
        return this.reward;
    }
    @Column(name ="DIRECTOR_OPINION",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getDirectorOpinion(){
        return this.directorOpinion;
    }
    @Column(name ="DIRECTOR_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getDirectorId(){
        return this.directorId;
    }
    @Column(name ="DIRECTOR_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=128)
    public String getDirectorName(){
        return this.directorName;
    }
    @Column(name ="DIRECTOR_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getDirectorDate(){
        return this.directorDate;
    }
    @Column(name ="QUALITY_DIRECTOR_OPINION",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getQualityDirectorOpinion(){
        return this.qualityDirectorOpinion;
    }
    @Column(name ="QUALITY_DIRECTOR_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getQualityDirectorId(){
        return this.qualityDirectorId;
    }
    @Column(name ="QUALITY_DIRECTOR_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=128)
    public String getQualityDirectorName(){
        return this.qualityDirectorName;
    }
    @Column(name ="QUALITY_DIRECTOR_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getQualityDirectorDate(){
        return this.qualityDirectorDate;
    }
    @Column(name ="SCIENTIFIC_DIRECTOR_OPINION",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getScientificDirectorOpinion(){
        return this.scientificDirectorOpinion;
    }
    @Column(name ="SCIENTIFIC_DIRECTOR_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getScientificDirectorId(){
        return this.scientificDirectorId;
    }
    @Column(name ="SCIENTIFIC_DIRECTOR_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=128)
    public String getScientificDirectorName(){
        return this.scientificDirectorName;
    }
    @Column(name ="SCIENTIFIC_DIRECTOR_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getScientificDirectorDate(){
        return this.scientificDirectorDate;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="CREATE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getCreateId(){
        return this.createId;
    }
    @Column(name ="CREATE_BY",unique=false,nullable=true,insertable=true,updatable=true,length=128)
    public String getCreateBy(){
        return this.createBy;
    }
    @Column(name ="CREATE_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getCreateDate(){
        return this.createDate;
    }
    @Column(name ="LAST_MODIFY_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getLastModifyId(){
        return this.lastModifyId;
    }
    @Column(name ="LAST_MODIFY_BY",unique=false,nullable=true,insertable=true,updatable=true,length=128)
    public String getLastModifyBy(){
        return this.lastModifyBy;
    }
    @Column(name ="LAST_MODIFY_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getLastModifyDate(){
        return this.lastModifyDate;
    }


} 
