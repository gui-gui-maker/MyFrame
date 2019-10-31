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

/**
 * 客运索道参数
 * 
 * @author liming 2014-5-19
 * 
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true, value = { "deviceDocument" })
@Table(name = "BASE_DEVICE_KYSD_PARA")
public class RopeWayPara implements BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3065496326960307406L;
	private String id;
	private String p90001001;
	private String p90002001;
	private String p90002002;
	private String p90002003;
	private String cp_by1;
	private String cp_by2;
	private String cp_by3;
	private String cp_by4;
	private String cp_by5;
	private String cp_by6;
	private String cp_by7;
	private String cp_by8;
	private String cp_by9;
	private String cp_by10;
	private String cbz;
	private String ccdbz;
	private String ccjrbm;
	private String ccjrmc;
	private String ccjrq;
	private String cgxrq;
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
	@JoinColumn(name = "FK_TZSB_DEVICE_DOCUMENT_ID")
	public DeviceDocument getDeviceDocument() {
		return deviceDocument;
	}

	public void setDeviceDocument(DeviceDocument deviceDocument) {
		this.deviceDocument = deviceDocument;
	}
	public String getP90001001() {
		return p90001001;
	}

	public void setP90001001(String p90001001) {
		this.p90001001 = p90001001;
	}

	public String getP90002001() {
		return p90002001;
	}

	public void setP90002001(String p90002001) {
		this.p90002001 = p90002001;
	}

	public String getP90002002() {
		return p90002002;
	}

	public void setP90002002(String p90002002) {
		this.p90002002 = p90002002;
	}

	public String getP90002003() {
		return p90002003;
	}

	public void setP90002003(String p90002003) {
		this.p90002003 = p90002003;
	}

	public String getCp_by1() {
		return cp_by1;
	}

	public void setCp_by1(String cp_by1) {
		this.cp_by1 = cp_by1;
	}

	public String getCp_by2() {
		return cp_by2;
	}

	public void setCp_by2(String cp_by2) {
		this.cp_by2 = cp_by2;
	}

	public String getCp_by3() {
		return cp_by3;
	}

	public void setCp_by3(String cp_by3) {
		this.cp_by3 = cp_by3;
	}

	public String getCp_by4() {
		return cp_by4;
	}

	public void setCp_by4(String cp_by4) {
		this.cp_by4 = cp_by4;
	}

	public String getCp_by5() {
		return cp_by5;
	}

	public void setCp_by5(String cp_by5) {
		this.cp_by5 = cp_by5;
	}

	public String getCp_by6() {
		return cp_by6;
	}

	public void setCp_by6(String cp_by6) {
		this.cp_by6 = cp_by6;
	}

	public String getCp_by7() {
		return cp_by7;
	}

	public void setCp_by7(String cp_by7) {
		this.cp_by7 = cp_by7;
	}

	public String getCp_by8() {
		return cp_by8;
	}

	public void setCp_by8(String cp_by8) {
		this.cp_by8 = cp_by8;
	}

	public String getCp_by9() {
		return cp_by9;
	}

	public void setCp_by9(String cp_by9) {
		this.cp_by9 = cp_by9;
	}

	public String getCp_by10() {
		return cp_by10;
	}

	public void setCp_by10(String cp_by10) {
		this.cp_by10 = cp_by10;
	}

	public String getCbz() {
		return cbz;
	}

	public void setCbz(String cbz) {
		this.cbz = cbz;
	}

	public String getCcdbz() {
		return ccdbz;
	}

	public void setCcdbz(String ccdbz) {
		this.ccdbz = ccdbz;
	}

	public String getCcjrbm() {
		return ccjrbm;
	}

	public void setCcjrbm(String ccjrbm) {
		this.ccjrbm = ccjrbm;
	}

	public String getCcjrmc() {
		return ccjrmc;
	}

	public void setCcjrmc(String ccjrmc) {
		this.ccjrmc = ccjrmc;
	}

	public String getCcjrq() {
		return ccjrq;
	}

	public void setCcjrq(String ccjrq) {
		this.ccjrq = ccjrq;
	}

	public String getCgxrq() {
		return cgxrq;
	}

	public void setCgxrq(String cgxrq) {
		this.cgxrq = cgxrq;
	}

}
