package com.lsts.gis.quality.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsts.gis.inspect.bean.DeviceTjDTO;
import com.lsts.gis.inspect.dao.DeviceTjDao;
import com.lsts.gis.quality.bean.QualityTjDTO;
import com.lsts.gis.quality.dao.QualityTjDao;





/**
 *统计manager
 *
 * @author zpl
 * 
 *
 * @date 
 */
@Service("qualityTjManager")
public class QualityTjManager {
    @Autowired QualityTjDao qualityTjDao;
    
    public Map<String, Object> initCount(){
    	return qualityTjDao.initCount();
    }
    public List<QualityTjDTO> initRwsList(String file_name){
    	return qualityTjDao.initRwsList(file_name);
    }
    public List<QualityTjDTO> initNkdqList(String file_name){
    	return qualityTjDao.initNkdqList(file_name);
    }
    public List<QualityTjDTO> initBhgList(String file_name){
    	return qualityTjDao.initBhgList(file_name);
    }
    public List<QualityTjDTO> initRjrwsList(String file_name){
    	return qualityTjDao.initRjrwsList(file_name);
    }
    public List<QualityTjDTO> initJcbgList(String file_name){
    	return qualityTjDao.initJcbgList(file_name);
    }
    public List<QualityTjDTO> getByReportNo(String report_sn){
    	return qualityTjDao.getByReportNo(report_sn);
    }
    public List<QualityTjDTO> getByReportNoYSJL(String report_sn){
    	return qualityTjDao.getByReportNoYSJL(report_sn);
    }
    public List<QualityTjDTO> getByReportNoBhg(String report_sn){
    	return qualityTjDao.getByReportNoBhg(report_sn);
    }
    
}
