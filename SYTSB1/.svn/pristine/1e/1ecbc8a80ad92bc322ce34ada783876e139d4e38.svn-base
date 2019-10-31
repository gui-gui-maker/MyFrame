package com.khnt.oa.car.service;


import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.oa.car.bean.CarInfo;
import com.khnt.oa.car.bean.CarRecord;
import com.khnt.oa.car.dao.CarInfoDao;
import com.khnt.utils.DateUtil;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*******************************************************************************
 * <p>
 * 
 * </p>
 * 
 * @ClassName CarInfoManager
 * @JDK 1.5
 * @author 
 * @date 
 */
@Service("carInfoManager")
public class CarInfoManager extends EntityManageImpl<CarInfo, CarInfoDao> {
	@Autowired
	CarInfoDao carInfoDao;
	
	@Autowired
	CarRecordManager carRecordManager;
	
	
	public HashMap<String, Object> saveCarInfo(CarInfo carInfo){
		String carCode=carInfo.getCarNum();
		String sql="select 1 from oa_car_info t where t.car_num='"+carCode+"' and t.id<>'"+carInfo.getId()+"'";
		List<?> list= (List<?>) carInfoDao.createSQLQuery(sql).list();
		if(list.size()>0){
			return JsonWrapper.failureWrapperMsg("车牌号已经存在，请填写正确的车牌号");
		}
		carInfoDao.save(carInfo);
		return JsonWrapper.successWrapper(carInfo);
	}
	
	
	public boolean saveUsedState(String id,String type){
		try{
			CarInfo a=this.get(id);
			a.setCarStateDate(new Date());
		    a.setCarState(type);
		    carInfoDao.save(a);
		    //应该做一个记录
		    CarRecord carRecord = new CarRecord();
		    carRecord.setCarId(a.getId());
		    carRecord.setReason(type);
		    carRecord.setRegDate(new Date());
		    carRecord.setRegWeek(DateUtil.getWeek(new Date()));
		    carRecordManager.save(carRecord);
		    return true;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
	}
	
	/**
	 * 根据车辆类别查询查询基本信息
	 * @param type
	 * @return
	 */
	public List<Map<String, Object>> queryCarType(String type){
		/*String sql="select * from OA_CAR_INFO where type=? and STATE='0' ";
		SQLQuery  query=((SQLQuery) carInfoDao.createSQLQuery(sql, type)).addEntity(CarInfo.class);
		List<CarInfo> list = query.list(); 
		return list ;*/
		List<Map<String, Object>> list=carInfoDao.queryCarType(type);
		List<Map<String, Object>> listCar=new ArrayList<Map<String,Object>>();
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				Map<String, Object> map=new HashMap<String, Object>();
				String types=list.get(i).get("types")==null?"":list.get(i).get("types").toString();
				String carBrand=list.get(i).get("CAR_BRAND").toString();
				String carNum=list.get(i).get("CAR_NUM").toString();
				String carType=list.get(i).get("CAR_TYPE")==null?"":list.get(i).get("CAR_TYPE").toString();
				String engineNo=list.get(i).get("ENGINE_NO")==null?"":list.get(i).get("ENGINE_NO").toString();
				String frameNo=list.get(i).get("FRAME_NO")==null?"":list.get(i).get("FRAME_NO").toString();
				String carDisplacement=list.get(i).get("CAR_DISPLACEMENT")==null?"":list.get(i).get("CAR_DISPLACEMENT").toString();
				String loadNumber=list.get(i).get("LOAD_NUMBER").toString();
				String address=list.get(i).get("ADDRESS")==null?"":list.get(i).get("ADDRESS").toString();
				String color=list.get(i).get("COLOR")==null?"":list.get(i).get("COLOR").toString();
				String buyDate=list.get(i).get("BUY_DATE")==null?"":list.get(i).get("BUY_DATE").toString();
				String managerRoomName=list.get(i).get("MANAGER_ROOM_NAME").toString();
				map.put("carBrand",carBrand);map.put("carNum",carNum);map.put("carType",carType);
				map.put("engineNo",engineNo);map.put("frameNo",frameNo );map.put("carDisplacement", carDisplacement);
				map.put("loadNumber", loadNumber);map.put("address", address);map.put("color", color);
				map.put("buyDate", buyDate);map.put("managerRoomName",managerRoomName);
				listCar.add(map);
			}
		}
		return listCar;
	}
}
