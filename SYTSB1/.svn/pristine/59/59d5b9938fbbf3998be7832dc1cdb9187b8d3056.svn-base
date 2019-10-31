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
 * 厂内机动车参数
 * 
 * @author 肖慈边 2014-1-21
 * 
 */

@Entity
@JsonIgnoreProperties(ignoreUnknown = true, value = { "deviceDocument" })
@Table(name = "BASE_DEVICE_ENGINE_PARA")
public class EnginePara implements BaseEntity {
	
		private static final long serialVersionUID = 1L;
		private String	id	;	//	id
//		private String	fk_tsjc_device_document_id	;	//	设备基本信息ID
		private String	p_pzhm	;	//	牌照号码
		private String	p_fdjxh	;	//	发动机编号
		private String	p_djccbh	;	//	出厂(底盘)编号
		private String	p_edzzl	;	//	额定载重量[kg]
		private String	p_yxsd	;	//	运行速度[km/h]
		private String	p_dlfs	;	//	动力方式
		private String	p_jssdy	;	//	驾驶室定员[人]
		private String	p_rylx	;	//	燃油类型
		private String	p_cpxh	;	//	厂牌型号
		private String	p_cllx	;	//	车辆类型
		private String	cp_by1	;	//	空车重量[kg]
		private String	cp_by2	;	//	颜色
		
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
		
//		public String getFk_tsjc_device_document_id() {
//			return fk_tsjc_device_document_id;
//		}
//		public void setFk_tsjc_device_document_id(String fk_tsjc_device_document_id) {
//			this.fk_tsjc_device_document_id = fk_tsjc_device_document_id;
//		}
		public String getP_pzhm() {
			return p_pzhm;
		}
		public void setP_pzhm(String p_pzhm) {
			this.p_pzhm = p_pzhm;
		}
		public String getP_fdjxh() {
			return p_fdjxh;
		}
		public void setP_fdjxh(String p_fdjxh) {
			this.p_fdjxh = p_fdjxh;
		}
		public String getP_djccbh() {
			return p_djccbh;
		}
		public void setP_djccbh(String p_djccbh) {
			this.p_djccbh = p_djccbh;
		}
		public String getP_edzzl() {
			return p_edzzl;
		}
		public void setP_edzzl(String p_edzzl) {
			this.p_edzzl = p_edzzl;
		}
		public String getP_yxsd() {
			return p_yxsd;
		}
		public void setP_yxsd(String p_yxsd) {
			this.p_yxsd = p_yxsd;
		}
		public String getP_dlfs() {
			return p_dlfs;
		}
		public void setP_dlfs(String p_dlfs) {
			this.p_dlfs = p_dlfs;
		}
		public String getP_jssdy() {
			return p_jssdy;
		}
		public void setP_jssdy(String p_jssdy) {
			this.p_jssdy = p_jssdy;
		}
		public String getP_rylx() {
			return p_rylx;
		}
		public void setP_rylx(String p_rylx) {
			this.p_rylx = p_rylx;
		}
		public String getP_cpxh() {
			return p_cpxh;
		}
		public void setP_cpxh(String p_cpxh) {
			this.p_cpxh = p_cpxh;
		}
		public String getP_cllx() {
			return p_cllx;
		}
		public void setP_cllx(String p_cllx) {
			this.p_cllx = p_cllx;
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


}
