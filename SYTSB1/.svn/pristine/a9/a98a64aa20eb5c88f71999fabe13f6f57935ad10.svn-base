package com.lsts.device.bean;

import java.util.Date;

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

/*******************************************************************************
 * 
 * 客运索道
 * 
 * CablewayPara entity. 
 * @author GaoYa
 * @date 2014-09-09 11:02:00
 */

@Entity
@JsonIgnoreProperties(ignoreUnknown = true, value = { "deviceDocument" })
@Table(name = "BASE_DEVICE_CABLEWAY_PARA")
public class CablewayPara implements BaseEntity {

	private static final long serialVersionUID = 1L;
	private String id;
	
	/********基本情况********/
	private String legal_name;			// 法定代表人
	private Date first_install_date;	// 首次安装竣工日期
	private Date remake_date; 		// 改造竣工日期
	private Date repair_date; 		// 重大维修竣工日期
	
	/********主要部件制造单位********/
	private String zybj_zzdw_1; 	// 驱动迂回装置
	private String zybj_zzdw_2; 	// 托压索轮组
	private String zybj_zzdw_3; 	// 抱索器
	private String zybj_zzdw_4; 	// 运载工具
	private String zybj_zzdw_5; 	// 运载索
	private String zybj_zzdw_6; 	// 承载索
	private String zybj_zzdw_7; 	// 牵引索（平衡索）
	private String zybj_zzdw_8; 	// 减速机
	private String zybj_zzdw_9; 	// 支架及鞍座
	private String zybj_zzdw_10; 	// 电气设备
	private String remark; 			// 备注
	
	/********基本技术参数********/
	private String jbjscs_1;		// 客运索道型式
	private String jbjscs_2; 		// 平距
	private String jbjscs_3; 		// 斜长
	private String jbjscs_4; 		// 高差
	private String jbjscs_5; 		// 运量
	private String jbjscs_6; 		// 速度（本次检验）
	private String jbjscs_7; 		// 支架数目
	private String jbjscs_8; 		// 主电机型式和功率
	private String jbjscs_9; 		// 张紧油压（重锤重量）
	private String jbjscs_10; 		// 运载索（牵引、平衡索）
	private String jbjscs_11; 		// 承载索
	private String jbjscs_12; 		// 运载工具数量和类型（本次检验）
	private String jbjscs_13; 		// 索距
	

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DeviceDocument deviceDocument;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_tsjc_device_document_id")
	public DeviceDocument getDeviceDocument() {
		return deviceDocument;
	}

	public void setDeviceDocument(DeviceDocument deviceDocument) {
		this.deviceDocument = deviceDocument;
	}

	public String getLegal_name() {
		return legal_name;
	}

	public void setLegal_name(String legal_name) {
		this.legal_name = legal_name;
	}

	public Date getFirst_install_date() {
		return first_install_date;
	}

	public void setFirst_install_date(Date first_install_date) {
		this.first_install_date = first_install_date;
	}

	public Date getRemake_date() {
		return remake_date;
	}

	public void setRemake_date(Date remake_date) {
		this.remake_date = remake_date;
	}

	public Date getRepair_date() {
		return repair_date;
	}

	public void setRepair_date(Date repair_date) {
		this.repair_date = repair_date;
	}

	public String getZybj_zzdw_1() {
		return zybj_zzdw_1;
	}

	public void setZybj_zzdw_1(String zybj_zzdw_1) {
		this.zybj_zzdw_1 = zybj_zzdw_1;
	}

	public String getZybj_zzdw_2() {
		return zybj_zzdw_2;
	}

	public void setZybj_zzdw_2(String zybj_zzdw_2) {
		this.zybj_zzdw_2 = zybj_zzdw_2;
	}

	public String getZybj_zzdw_3() {
		return zybj_zzdw_3;
	}

	public void setZybj_zzdw_3(String zybj_zzdw_3) {
		this.zybj_zzdw_3 = zybj_zzdw_3;
	}

	public String getZybj_zzdw_4() {
		return zybj_zzdw_4;
	}

	public void setZybj_zzdw_4(String zybj_zzdw_4) {
		this.zybj_zzdw_4 = zybj_zzdw_4;
	}

	public String getZybj_zzdw_5() {
		return zybj_zzdw_5;
	}

	public void setZybj_zzdw_5(String zybj_zzdw_5) {
		this.zybj_zzdw_5 = zybj_zzdw_5;
	}

	public String getZybj_zzdw_6() {
		return zybj_zzdw_6;
	}

	public void setZybj_zzdw_6(String zybj_zzdw_6) {
		this.zybj_zzdw_6 = zybj_zzdw_6;
	}

	public String getZybj_zzdw_7() {
		return zybj_zzdw_7;
	}

	public void setZybj_zzdw_7(String zybj_zzdw_7) {
		this.zybj_zzdw_7 = zybj_zzdw_7;
	}

	public String getZybj_zzdw_8() {
		return zybj_zzdw_8;
	}

	public void setZybj_zzdw_8(String zybj_zzdw_8) {
		this.zybj_zzdw_8 = zybj_zzdw_8;
	}

	public String getZybj_zzdw_9() {
		return zybj_zzdw_9;
	}

	public void setZybj_zzdw_9(String zybj_zzdw_9) {
		this.zybj_zzdw_9 = zybj_zzdw_9;
	}

	public String getZybj_zzdw_10() {
		return zybj_zzdw_10;
	}

	public void setZybj_zzdw_10(String zybj_zzdw_10) {
		this.zybj_zzdw_10 = zybj_zzdw_10;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getJbjscs_1() {
		return jbjscs_1;
	}

	public void setJbjscs_1(String jbjscs_1) {
		this.jbjscs_1 = jbjscs_1;
	}

	public String getJbjscs_2() {
		return jbjscs_2;
	}

	public void setJbjscs_2(String jbjscs_2) {
		this.jbjscs_2 = jbjscs_2;
	}

	public String getJbjscs_3() {
		return jbjscs_3;
	}

	public void setJbjscs_3(String jbjscs_3) {
		this.jbjscs_3 = jbjscs_3;
	}

	public String getJbjscs_4() {
		return jbjscs_4;
	}

	public void setJbjscs_4(String jbjscs_4) {
		this.jbjscs_4 = jbjscs_4;
	}

	public String getJbjscs_5() {
		return jbjscs_5;
	}

	public void setJbjscs_5(String jbjscs_5) {
		this.jbjscs_5 = jbjscs_5;
	}

	public String getJbjscs_6() {
		return jbjscs_6;
	}

	public void setJbjscs_6(String jbjscs_6) {
		this.jbjscs_6 = jbjscs_6;
	}

	public String getJbjscs_7() {
		return jbjscs_7;
	}

	public void setJbjscs_7(String jbjscs_7) {
		this.jbjscs_7 = jbjscs_7;
	}

	public String getJbjscs_8() {
		return jbjscs_8;
	}

	public void setJbjscs_8(String jbjscs_8) {
		this.jbjscs_8 = jbjscs_8;
	}

	public String getJbjscs_9() {
		return jbjscs_9;
	}

	public void setJbjscs_9(String jbjscs_9) {
		this.jbjscs_9 = jbjscs_9;
	}

	public String getJbjscs_10() {
		return jbjscs_10;
	}

	public void setJbjscs_10(String jbjscs_10) {
		this.jbjscs_10 = jbjscs_10;
	}

	public String getJbjscs_11() {
		return jbjscs_11;
	}

	public void setJbjscs_11(String jbjscs_11) {
		this.jbjscs_11 = jbjscs_11;
	}

	public String getJbjscs_12() {
		return jbjscs_12;
	}

	public void setJbjscs_12(String jbjscs_12) {
		this.jbjscs_12 = jbjscs_12;
	}

	public String getJbjscs_13() {
		return jbjscs_13;
	}

	public void setJbjscs_13(String jbjscs_13) {
		this.jbjscs_13 = jbjscs_13;
	}

	
	
}
