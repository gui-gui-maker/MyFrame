package com.fwxm.scientific.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * <p>
 *  
 * </p>
 * 
 * @ClassName InstrumentDeviceInfo
 * @JDK 1.7
 * @author CODER_V3.0
 * @date 2018-01-18 15:20:10
 */
@Entity
@Table(name = "TJY2_INSTRUMENT_DEVICE_INFO")
@JsonIgnoreProperties({"instrumentDevice"})
public class InstrumentDeviceInfo implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private String id;//
	private String deviceName;//设备名称
	private String sum;//需求数量
	private String deviceModel;//设备型号
	private String deviceManufacturer;//设备厂家
	private String money;//预计单价
	private String reason;//购买理由
	private String channel;//了解渠道
	private String parameter;//主要功能及参数
	private String remark;//备注
	private String sbyt_xy;//设备用途及将产生的效益
	private String xq_man;//需求提出人
	
	//private String instrumentDeviceId;//仪器设备需求id
	private InstrumentDevice instrumentDevice;
	
	@ManyToOne
	@JoinColumn(name = "instrument_device_id")
	public InstrumentDevice getInstrumentDevice() {
		return instrumentDevice;
	}
	public void setInstrumentDevice(InstrumentDevice instrumentDevice) {
		this.instrumentDevice = instrumentDevice;
	}
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	@Column(name="DEVICE_NAME")
	public String getDeviceName(){
		return deviceName;
	}
		
	public void setDeviceName(String deviceName){
		this.deviceName=deviceName;
	}

	@Column(name="SUM")
	public String getSum(){
		return sum;
	}
		
	public void setSum(String sum){
		this.sum=sum;
	}

	@Column(name="DEVICE_MODEL")
	public String getDeviceModel(){
		return deviceModel;
	}
		
	public void setDeviceModel(String deviceModel){
		this.deviceModel=deviceModel;
	}

	@Column(name="DEVICE_MANUFACTURER")
	public String getDeviceManufacturer(){
		return deviceManufacturer;
	}
		
	public void setDeviceManufacturer(String deviceManufacturer){
		this.deviceManufacturer=deviceManufacturer;
	}

	@Column(name="MONEY")
	public String getMoney(){
		return money;
	}
		
	public void setMoney(String money){
		this.money=money;
	}

	@Column(name="REASON")
	public String getReason(){
		return reason;
	}
		
	public void setReason(String reason){
		this.reason=reason;
	}

	@Column(name="CHANNEL")
	public String getChannel(){
		return channel;
	}
		
	public void setChannel(String channel){
		this.channel=channel;
	}

	@Column(name="PARAMETER")
	public String getParameter(){
		return parameter;
	}
		
	public void setParameter(String parameter){
		this.parameter=parameter;
	}

	@Column(name="REMARK")
	public String getRemark(){
		return remark;
	}
		
	public void setRemark(String remark){
		this.remark=remark;
	}

	
	
	
	public String getSbyt_xy() {
		return sbyt_xy;
	}
	public void setSbyt_xy(String sbyt_xy) {
		this.sbyt_xy = sbyt_xy;
	}
	public String getXq_man() {
		return xq_man;
	}
	public void setXq_man(String xq_man) {
		this.xq_man = xq_man;
	}
	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "TJY2_INSTRUMENT_DEVICE_INFO:ID="+id;

	}
}
