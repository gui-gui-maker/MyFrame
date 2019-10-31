package com.lsts.gis.inspect.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsts.gis.inspect.bean.DeviceTjDTO;
import com.lsts.gis.inspect.dao.DeviceTjDao;
import com.lsts.gis.quality.bean.QualityTjDTO;





/**
 *统计manager
 *
 * @author 
 *
 * @date 
 */
@Service("deviceTjManager")
public class DeviceTjManager {
    @Autowired DeviceTjDao deviceTjDao;
    
    public List<DeviceTjDTO> getQnMoney(String unit_id){
    	return deviceTjDao.getQnMoney(unit_id);
    }
    public List<DeviceTjDTO> getYfMoney(String unit_id){
    	return deviceTjDao.getYfMoney(unit_id);
    }
    public List<DeviceTjDTO> getSnYfMoney(String unit_id){
    	return deviceTjDao.getSnYfMoney(unit_id);
    }
    public List<DeviceTjDTO> getMtMoney(String unit_id){
    	return deviceTjDao.getMtMoney(unit_id);
    }
    public List<DeviceTjDTO> getNdMoney(String unit_id){
    	return deviceTjDao.getNdMoney(unit_id);
    }
    public List<DeviceTjDTO> getZjTj(String unit_id){
    	return deviceTjDao.getZjTj(unit_id);
    }
    public List<DeviceTjDTO> getZjTjJd(){
    	return deviceTjDao.getZjTjJd();
    }
    public List<DeviceTjDTO> getZjTjCy(){
    	return deviceTjDao.getZjTjCy();
    }
    public List<DeviceTjDTO> getZjTjWs(){
    	return deviceTjDao.getZjTjWs();
    }
    public List<DeviceTjDTO> getJcTj(String unit_id){
    	return deviceTjDao.getJcTj(unit_id);
    }
    public List<DeviceTjDTO> getBmBg(String unit_id){
    	return deviceTjDao.getBmBg(unit_id);
    }
    public List<DeviceTjDTO> getBmFy(String unit_id){
    	return deviceTjDao.getBmFy(unit_id);
    }
    public Map<String, Object> initCount(String unit_id){
    	return deviceTjDao.initCount(unit_id);
    }
    public List<DeviceTjDTO> getYfMoneyXX(String unit_id,String dates){
    	return deviceTjDao.getYfMoneyXX(unit_id,dates);
    }
    public List<DeviceTjDTO> getDateMtMoney(String unit_id,String sdate,String edate){
    	return deviceTjDao.getDateMtMoney(unit_id,sdate,edate);
    }
    public List<QualityTjDTO> getByUnitIdXl(String unit_id){
    	return deviceTjDao.getByUnitIdXl(unit_id);
    }
    public List<DeviceTjDTO> statisticsFee(String unit_id){
    	return deviceTjDao.statisticsFee(unit_id);
    }
	public List<DeviceTjDTO> statisticsFeeQn(String unit_id){
		return deviceTjDao.statisticsFeeQn(unit_id);
	}
	 public List<DeviceTjDTO> getYfMoneyZc(String unit_id,String dates){
	    	return deviceTjDao.getYfMoneyZc(unit_id,dates);
	    }
	 public List<DeviceTjDTO> getMtMoneyZc(String unit_id){
	    	return deviceTjDao.getMtMoneyZc(unit_id);
	    }
	 public List<DeviceTjDTO> getBmQxj(String unit_id){
	    	return deviceTjDao.getBmQxj(unit_id);
	    }
	 public List<DeviceTjDTO> getKytj(){
	    	return deviceTjDao.getKytj();
	    }
	 public List<DeviceTjDTO> getRyzc(){
	    	return deviceTjDao.getRyzc();
	    }
	 
}
