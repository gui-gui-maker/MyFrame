package com.lsts.inspection.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import com.khnt.core.crud.bean.BaseEntity;

/**
 * 报告领取消息推送fkInspectionInfoId和报告书编号保存
 * @author XXZX
 *
 */
@Entity
@Table(name = "REPORT_DRAW_SIGN")
public class ReportDrawSign implements BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
    private String id;//流水号
    
    private String invoiceNo;//发票号

    private String fkInspectionInfoId;//报检业务数据表id

    private String reportSn;//报告书编号
    
    private String formatReportSn;
    
    private String pulldown_op;
    
    private String pulldown_tel;

    private java.util.Date cretatdDate;//创建时间

    private String cretatdBy;//创建人

    private String isDel;//删除状态
    
    private String signFile;//签名图片保存路径
    private String evaluate;//评价（0：非常满意，1：满意，2：不满意）
    private String status;//推送状态，判断是否重推(1:表示重推)
    private String signRelativeFile;//签名图片保存相对路径
    
    private Integer imgVersion=0;

    public void setId(String value){
        this.id = value;
    }
    public void setFkInspectionInfoId(String value){
        this.fkInspectionInfoId = value;
    }
    public void setReportSn(String value){
        this.reportSn = value;
    }
    public void setCretatdDate(java.util.Date value){
        this.cretatdDate = value;
    }
    public void setCretatdBy(String value){
        this.cretatdBy = value;
    }
    public void setIsDel(String value){
        this.isDel = value;
    }
    
    @Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
    public String getId(){
        return this.id;
    }
    @Column(name ="FK_INSPECTION_INFO_ID")
    public String getFkInspectionInfoId(){
        return this.fkInspectionInfoId;
    }
    @Column(name ="REPORT_SN")
    public String getReportSn(){
        return this.reportSn;
    }
    @Column(name ="CRETATD_DATE")
    public java.util.Date getCretatdDate(){
        return this.cretatdDate;
    }
    @Column(name ="CRETATD_BY")
    public String getCretatdBy(){
        return this.cretatdBy;
    }
    @Column(name ="IS_DEL")
    public String getIsDel(){
        return this.isDel;
    }
    
    @Column(name ="SIGN_FILE")
	public String getSignFile() {
		return signFile;
	}
	public void setSignFile(String signFile) {
		this.signFile = signFile;
	}
	
	 @Column(name ="EVALUATE")
	public String getEvaluate() {
		return evaluate;
	}
	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	
	@Column(name ="STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name ="SIGN_RELATIVE_FILE")
	public String getSignRelativeFile() {
		return signRelativeFile;
	}
	public void setSignRelativeFile(String signRelativeFile) {
		this.signRelativeFile = signRelativeFile;
	}
	@Column(name ="invoice_no")
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	
	public String getPulldown_op() {
		return pulldown_op;
	}
	public void setPulldown_op(String pulldown_op) {
		this.pulldown_op = pulldown_op;
	}
	
	public String getPulldown_tel() {
		return pulldown_tel;
	}
	public void setPulldown_tel(String pulldown_tel) {
		this.pulldown_tel = pulldown_tel;
	}
	@Column(name ="img_version")
	public Integer getImgVersion() {
		return imgVersion;
	}
	public void setImgVersion(Integer imgVersion) {
		this.imgVersion = imgVersion;
	}
	@Column(name ="format_report_sn")
	public String getFormatReportSn() {
		return formatReportSn;
	}
	public void setFormatReportSn(String formatReportSn) {
		this.formatReportSn = formatReportSn;
	}
	
	
} 
