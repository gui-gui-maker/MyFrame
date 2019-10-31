package com.khnt.oa.car.bean;

import com.khnt.core.crud.bean.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/*******************************************************************************
 * 
 * <p>
 *  
 * </p>
 * 
 * @ClassName Apply
 * @JDK 1.5
 * @author 
 * @date 2
 */
@Entity
@Table(name = "OA_CAR_APPLY")
public class Apply implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;//编号
	private String applitorCode;//申请人编号
	private String applitorName;//申请人
	private Date applitorTime;//申请时间
	private String applyRoom;//申请处室
	private String applyRoomCode;//处室编号
	private CarInfo car;//
	private String usedCarReason;//用车事由
	private String destination;//目的地
	private Date startTime;//出车时间
	private Date endTime;//返回时间
	private String destinationMan;//批准人
	private String destinationManId;//批准人编号
	private Date destinationTime;//批准时间
	private String state;//状态
	private String startKm;//起始公里数
	private String endKm;//结算公里数
	private String allKm;//实际里程
	private String gasolineV;//汽油量（升）
	private String oilPrice;//汽油单价
	private String gasolineMoney;//汽油金额（元）
	private String oilConsumption100;//百公里油耗
	private String sendCarMan;//派车人
	private String sendCarManCode;//派车人编号
	private Date sendCarTime;//派车时间
	private String driver;
	private String editor;//处理人
	private String editorCode;//处理人编号

	
	private String backCarType;
	
	private Date backCarTypeDate;
	private Date confirmTime;//确认归还时间
	private String confirmor ;//确认人
	private String confirmorCode;//确认人编号
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	@Column(name="APPLITOR_CODE")
	public String getApplitorCode(){
		return applitorCode;
	}

	public void setApplitorCode(String applitorCode){
		this.applitorCode=applitorCode;
	}

	@Column(name="APPLITOR_NAME")
	public String getApplitorName(){
		return applitorName;
	}

	public void setApplitorName(String applitorName){
		this.applitorName=applitorName;
	}

	@Column(name="APPLITOR_TIME")
	public Date getApplitorTime(){
		return applitorTime;
	}

	public void setApplitorTime(Date applitorTime){
		this.applitorTime=applitorTime;
	}

	@Column(name="APPLY_ROOM")
	public String getApplyRoom(){
		return applyRoom;
	}

	public void setApplyRoom(String applyRoom){
		this.applyRoom=applyRoom;
	}

	@Column(name="APPLY_ROOM_CODE")
	public String getApplyRoomCode(){
		return applyRoomCode;
	}

	public void setApplyRoomCode(String applyRoomCode){
		this.applyRoomCode=applyRoomCode;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CAR_ID")
	public CarInfo getCar() {
		return car;
	}

	public void setCar(CarInfo car) {
		this.car = car;
	}

	@Column(name="USED_CAR_REASON")
	public String getUsedCarReason(){
		return usedCarReason;
	}

	public void setUsedCarReason(String usedCarReason){
		this.usedCarReason=usedCarReason;
	}

	@Column(name="DESTINATION")
	public String getDestination(){
		return destination;
	}

	public void setDestination(String destination){
		this.destination=destination;
	}

	@Column(name="START_TIME")
	public Date getStartTime(){
		return startTime;
	}

	public void setStartTime(Date startTime){
		this.startTime=startTime;
	}

	@Column(name="END_TIME")
	public Date getEndTime(){
		return endTime;
	}

	public void setEndTime(Date endTime){
		this.endTime=endTime;
	}

	@Column(name="DESTINATION_MAN")
	public String getDestinationMan(){
		return destinationMan;
	}

	public void setDestinationMan(String destinationMan){
		this.destinationMan=destinationMan;
	}

	@Column(name="STATE")
	public String getState(){
		return state;
	}

	public void setState(String state){
		this.state=state;
	}

	@Column(name="START_KM")
	public String getStartKm(){
		return startKm;
	}

	public void setStartKm(String startKm){
		this.startKm=startKm;
	}

	@Column(name="END_KM")
	public String getEndKm(){
		return endKm;
	}

	public void setEndKm(String endKm){
		this.endKm=endKm;
	}

	@Column(name="ALL_KM")
	public String getAllKm(){
		return allKm;
	}

	public void setAllKm(String allKm){
		this.allKm=allKm;
	}

	@Column(name="GASOLINE_V")
	public String getGasolineV(){
		return gasolineV;
	}

	public void setGasolineV(String gasolineV){
		this.gasolineV=gasolineV;
	}

	@Column(name="GASOLINE_MONEY")
	public String getGasolineMoney(){
		return gasolineMoney;
	}

	public void setGasolineMoney(String gasolineMoney){
		this.gasolineMoney=gasolineMoney;
	}

	@Column(name="OIL_CONSUMPTION_100")
	public String getOilConsumption100(){
		return oilConsumption100;
	}

	public void setOilConsumption100(String oilConsumption100){
		this.oilConsumption100=oilConsumption100;
	}

	@Column(name="SEND_CAR_MAN")
	public String getSendCarMan(){
		return sendCarMan;
	}

	public void setSendCarMan(String sendCarMan){
		this.sendCarMan=sendCarMan;
	}

	@Column(name="SEND_CAR_MAN_CODE")
	public String getSendCarManCode(){
		return sendCarManCode;
	}

	public void setSendCarManCode(String sendCarManCode){
		this.sendCarManCode=sendCarManCode;
	}

	@Column(name="SEND_CAR_TIME")
	public Date getSendCarTime(){
		return sendCarTime;
	}

	public void setSendCarTime(Date sendCarTime){
		this.sendCarTime=sendCarTime;
	}
	
	
	@Column(name="DRIVER")

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}
	
	
	
	@Column(name="EDITOR")
	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}
	@Column(name="EDITOR_CODE")
	public String getEditorCode() {
		return editorCode;
	}

	public void setEditorCode(String editorCode) {
		this.editorCode = editorCode;
	}
	
	@Column(name="DESTINATION_TIME")

	public Date getDestinationTime() {
		return destinationTime;
	}

	public void setDestinationTime(Date destinationTime) {
		this.destinationTime = destinationTime;
	}
	
	@Column(name="DESTINATION_MAN_ID")

	public String getDestinationManId() {
		return destinationManId;
	}

	public void setDestinationManId(String destinationManId) {
		this.destinationManId = destinationManId;
	}
	
	@Column(name="OIL_PRICE")

	public String getOilPrice() {
		return oilPrice;
	}

	public void setOilPrice(String oilPrice) {
		this.oilPrice = oilPrice;
	}
	
	@Column(name="BACK_CAR_TYPE")

	public String getBackCarType() {
		return backCarType;
	}

	public void setBackCarType(String backCarType) {
		this.backCarType = backCarType;
	}
	@Column(name="BACK_CAR_TYPE_DATE")
	
	public Date getBackCarTypeDate() {
		return backCarTypeDate;
	}

	public void setBackCarTypeDate(Date backCarTypeDate) {
		this.backCarTypeDate = backCarTypeDate;
	}
	@Column(name="CONFIRM_TIME")
	
	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}
	@Column(name="CONFIRMOR")
	public String getConfirmor(){
		return confirmor;
	}

	public void setConfirmor(String confirmor){
		this.confirmor=confirmor;
	}
	@Column(name="CONFIRMOR_CODE")
	public String getConfirmorCode(){
		return confirmorCode;
	}

	public void setConfirmorCode(String confirmorCode){
		this.confirmorCode=confirmorCode;
	}
	
	

	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "未说明";

	}
}
