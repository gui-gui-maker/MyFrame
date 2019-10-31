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
 * @ClassName Repair
 * @JDK 1.5
 * @author
 * @date 2
 */
@Entity
@Table(name = "OA_CAR_REPAIR")
public class Repair implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;// 编号
	//private String carId;// 汽车编号
	private CarInfo car;//
	private String driver;// 驾驶员
	private Date repairTime;// 维修时间
	private Float repairMoney;// 维修金额（元）
	private String repairFactory;// 维修厂家
	private String repairRoom;// 管理处室
	private String repairRoomCode;// 管理处室编号
	private String type;// 类型，维修、保养
	private Date nextDate;// 下次保养时间；
	private String remark;
	private String maintenanceKM;
	private String nextMaintenanceKM;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "DRIVER")
	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	@Column(name = "REPAIR_TIME")
	public Date getRepairTime() {
		return repairTime;
	}

	public void setRepairTime(Date repairTime) {
		this.repairTime = repairTime;
	}

	@Column(name = "REPAIR_MONEY")
	public Float getRepairMoney() {
		return repairMoney;
	}

	public void setRepairMoney(Float repairMoney) {
		this.repairMoney = repairMoney;
	}

	@Column(name = "REPAIR_FACTORY")
	public String getRepairFactory() {
		return repairFactory;
	}

	public void setRepairFactory(String repairFactory) {
		this.repairFactory = repairFactory;
	}

	@Column(name = "REPAIR_ROOM")
	public String getRepairRoom() {
		return repairRoom;
	}

	public void setRepairRoom(String repairRoom) {
		this.repairRoom = repairRoom;
	}

	@Column(name = "REPAIR_ROOM_CODE")
	public String getRepairRoomCode() {
		return repairRoomCode;
	}

	public void setRepairRoomCode(String repairRoomCode) {
		this.repairRoomCode = repairRoomCode;
	}

	@Column(name = "TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "NEXT_MAINTENANCE")
	public Date getNextDate() {
		return nextDate;
	}

	public void setNextDate(Date nextDate) {
		this.nextDate = nextDate;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "MAINTENANCE_KM")
	public String getMaintenanceKM() {
		return maintenanceKM;
	}

	public void setMaintenanceKM(String maintenanceKM) {
		this.maintenanceKM = maintenanceKM;
	}
	@Column(name = "NEXT_MAINTENANCE_KM")
	public String getNextMaintenanceKM() {
		return nextMaintenanceKM;
	}

	public void setNextMaintenanceKM(String nextMaintenanceKM) {
		this.nextMaintenanceKM = nextMaintenanceKM;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CAR_ID")
	public CarInfo getCar() {
		return car;
	}

	public void setCar(CarInfo car) {
		this.car = car;
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
