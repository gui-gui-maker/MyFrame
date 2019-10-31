package com.lsts.device.bean;

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
 * 起重参数
 * 
 * @author 肖慈边 2014-1-20
 * 
 */

@Entity
@JsonIgnoreProperties(ignoreUnknown = true, value = { "deviceDocument" })
@Table(name = "BASE_DEVICE_CRANE_PARA")
public class CranePara implements BaseEntity {
	
	private static final long serialVersionUID = 1L;
	private String	id	;	//	id
//	private String	fk_tsjc_device_document_id	;	//	设备基本信息ID
	private String	p40001001	;	//	工作级别
	private String	p40001002	;	//	工作环境
	private String	p40001003	;	//	门架型式
	private String	p40001004	;	//	取物装置
	private String	p40001005	;	//	司机室型式
	private String	p40001006	;	//	操纵形式
	private String	p40001007	;	//	导电方式
	private String	p40001008	;	//	旋转支承装置型式
	private String	p40001009	;	//	旋转驱动装置型式
	private String	p40001010	;	//	变幅驱动型式
	private String	p40001011	;	//	臂架系统型式
	private String	p40001012	;	//	出入库方式
	private String	p40001013	;	//	升降驱动方式
	private String	p40001014	;	//	横行驱动方式
	private String	p40001015	;	//	设计规范
	private String	p40001016	;	//	制造规范
	private String	p40001017	;	//	设备新旧状况
	private String	p40001018	;	//	监检形式
	private String	p40001019	;	//	变幅方式
	private String	p40001020	;	//	变幅平衡方式
	private String	p40002001	;	//	轨矩[m]
	private String	p40002002	;	//	幅度[度]
	private String	p40002003	;	//	起升速度[m/min]
	private String	p40002003_fg	;	//	P40002003_FG
	private String	p40002004	;	//	载荷[t],起重量
	private String	p40002004_fg	;	//	P40002004_FG
	private String	p40002005	;	//	提升力矩
	private String	p40002006	;	//	提升高度[m]
	private String	p40002006_fg	;	//	提升高度(副钩)[m]
	private String	p40002007	;	//	工作半径[m]
	private String	p_cz	;	//	层站[层数]
	private String	p_qdxs	;	//	驱动方式
	private String	p_bfsd	;	//	变幅速度
	private String	p_hzsd	;	//	回转速度
	private String	p_xssd	;	//	控制屏出行驶速度厂编号
	private String	p_kd	;	//	跨度
	private String	p_drgzxc	;	//	吊笼工作行程
	private String	p_tsszj	;	//	提升绳直径
	private String	p_zydgd	;	//	自由端高度
	private String	p_acqxh	;	//	安全器型号
	private String	p_acqbh	;	//	安全器编号
	private String	p_acqxzsd	;	//	安全器协作速度
	private String	p_yxsd_dc	;	//	运行速度(大车)
	private String	p_yxsd_xc	;	//	运行速度(小车)
	private String	p_gdcd	;	//	轨道长度
	private String	p_cws	;	//	车位数
	private String  p_stclcc;	//  适停车辆尺寸
	
	
	 
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
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
	
	
//	public String getFk_tsjc_device_document_id() {
//		return fk_tsjc_device_document_id;
//	}
//	public void setFk_tsjc_device_document_id(String fk_tsjc_device_document_id) {
//		this.fk_tsjc_device_document_id = fk_tsjc_device_document_id;
//	}
	public String getP40001001() {
		return p40001001;
	}
	public void setP40001001(String p40001001) {
		this.p40001001 = p40001001;
	}
	public String getP40001002() {
		return p40001002;
	}
	public void setP40001002(String p40001002) {
		this.p40001002 = p40001002;
	}
	public String getP40001003() {
		return p40001003;
	}
	public void setP40001003(String p40001003) {
		this.p40001003 = p40001003;
	}
	public String getP40001004() {
		return p40001004;
	}
	public void setP40001004(String p40001004) {
		this.p40001004 = p40001004;
	}
	public String getP40001005() {
		return p40001005;
	}
	public void setP40001005(String p40001005) {
		this.p40001005 = p40001005;
	}
	public String getP40001006() {
		return p40001006;
	}
	public void setP40001006(String p40001006) {
		this.p40001006 = p40001006;
	}
	public String getP40001007() {
		return p40001007;
	}
	public void setP40001007(String p40001007) {
		this.p40001007 = p40001007;
	}
	public String getP40001008() {
		return p40001008;
	}
	public void setP40001008(String p40001008) {
		this.p40001008 = p40001008;
	}
	public String getP40001009() {
		return p40001009;
	}
	public void setP40001009(String p40001009) {
		this.p40001009 = p40001009;
	}
	public String getP40001010() {
		return p40001010;
	}
	public void setP40001010(String p40001010) {
		this.p40001010 = p40001010;
	}
	public String getP40001011() {
		return p40001011;
	}
	public void setP40001011(String p40001011) {
		this.p40001011 = p40001011;
	}
	public String getP40001012() {
		return p40001012;
	}
	public void setP40001012(String p40001012) {
		this.p40001012 = p40001012;
	}
	public String getP40001013() {
		return p40001013;
	}
	public void setP40001013(String p40001013) {
		this.p40001013 = p40001013;
	}
	public String getP40001014() {
		return p40001014;
	}
	public void setP40001014(String p40001014) {
		this.p40001014 = p40001014;
	}
	public String getP40001015() {
		return p40001015;
	}
	public void setP40001015(String p40001015) {
		this.p40001015 = p40001015;
	}
	public String getP40001016() {
		return p40001016;
	}
	public void setP40001016(String p40001016) {
		this.p40001016 = p40001016;
	}
	public String getP40001017() {
		return p40001017;
	}
	public void setP40001017(String p40001017) {
		this.p40001017 = p40001017;
	}
	public String getP40001018() {
		return p40001018;
	}
	public void setP40001018(String p40001018) {
		this.p40001018 = p40001018;
	}
	public String getP40001019() {
		return p40001019;
	}
	public void setP40001019(String p40001019) {
		this.p40001019 = p40001019;
	}
	public String getP40001020() {
		return p40001020;
	}
	public void setP40001020(String p40001020) {
		this.p40001020 = p40001020;
	}
	public String getP40002001() {
		return p40002001;
	}
	public void setP40002001(String p40002001) {
		this.p40002001 = p40002001;
	}
	public String getP40002002() {
		return p40002002;
	}
	public void setP40002002(String p40002002) {
		this.p40002002 = p40002002;
	}
	public String getP40002003() {
		return p40002003;
	}
	public void setP40002003(String p40002003) {
		this.p40002003 = p40002003;
	}
	public String getP40002003_fg() {
		return p40002003_fg;
	}
	public void setP40002003_fg(String p40002003_fg) {
		this.p40002003_fg = p40002003_fg;
	}
	public String getP40002004() {
		return p40002004;
	}
	public void setP40002004(String p40002004) {
		this.p40002004 = p40002004;
	}
	public String getP40002004_fg() {
		return p40002004_fg;
	}
	public void setP40002004_fg(String p40002004_fg) {
		this.p40002004_fg = p40002004_fg;
	}
	public String getP40002005() {
		return p40002005;
	}
	public void setP40002005(String p40002005) {
		this.p40002005 = p40002005;
	}
	public String getP40002006() {
		return p40002006;
	}
	public void setP40002006(String p40002006) {
		this.p40002006 = p40002006;
	}
	public String getP40002006_fg() {
		return p40002006_fg;
	}
	public void setP40002006_fg(String p40002006_fg) {
		this.p40002006_fg = p40002006_fg;
	}
	public String getP40002007() {
		return p40002007;
	}
	public void setP40002007(String p40002007) {
		this.p40002007 = p40002007;
	}
	public String getP_cz() {
		return p_cz;
	}
	public void setP_cz(String p_cz) {
		this.p_cz = p_cz;
	}
	public String getP_qdxs() {
		return p_qdxs;
	}
	public void setP_qdxs(String p_qdxs) {
		this.p_qdxs = p_qdxs;
	}
	public String getP_bfsd() {
		return p_bfsd;
	}
	public void setP_bfsd(String p_bfsd) {
		this.p_bfsd = p_bfsd;
	}
	public String getP_hzsd() {
		return p_hzsd;
	}
	public void setP_hzsd(String p_hzsd) {
		this.p_hzsd = p_hzsd;
	}
	public String getP_xssd() {
		return p_xssd;
	}
	public void setP_xssd(String p_xssd) {
		this.p_xssd = p_xssd;
	}
	public String getP_kd() {
		return p_kd;
	}
	public void setP_kd(String p_kd) {
		this.p_kd = p_kd;
	}
	public String getP_drgzxc() {
		return p_drgzxc;
	}
	public void setP_drgzxc(String p_drgzxc) {
		this.p_drgzxc = p_drgzxc;
	}
	public String getP_tsszj() {
		return p_tsszj;
	}
	public void setP_tsszj(String p_tsszj) {
		this.p_tsszj = p_tsszj;
	}
	public String getP_zydgd() {
		return p_zydgd;
	}
	public void setP_zydgd(String p_zydgd) {
		this.p_zydgd = p_zydgd;
	}
	public String getP_acqxh() {
		return p_acqxh;
	}
	public void setP_acqxh(String p_acqxh) {
		this.p_acqxh = p_acqxh;
	}
	public String getP_acqbh() {
		return p_acqbh;
	}
	public void setP_acqbh(String p_acqbh) {
		this.p_acqbh = p_acqbh;
	}
	public String getP_acqxzsd() {
		return p_acqxzsd;
	}
	public void setP_acqxzsd(String p_acqxzsd) {
		this.p_acqxzsd = p_acqxzsd;
	}
	public String getP_yxsd_dc() {
		return p_yxsd_dc;
	}
	public void setP_yxsd_dc(String p_yxsd_dc) {
		this.p_yxsd_dc = p_yxsd_dc;
	}
	public String getP_yxsd_xc() {
		return p_yxsd_xc;
	}
	public void setP_yxsd_xc(String p_yxsd_xc) {
		this.p_yxsd_xc = p_yxsd_xc;
	}
	public String getP_gdcd() {
		return p_gdcd;
	}
	public void setP_gdcd(String p_gdcd) {
		this.p_gdcd = p_gdcd;
	}
	public String getP_cws() {
		return p_cws;
	}
	public void setP_cws(String p_cws) {
		this.p_cws = p_cws;
	}
	public String getP_stclcc() {
		return p_stclcc;
	}
	public void setP_stclcc(String p_stclcc) {
		this.p_stclcc = p_stclcc;
	}

}
