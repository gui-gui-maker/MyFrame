package com.fwxm.scientific.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fwxm.scientific.bean.TjDTO;
import com.fwxm.scientific.dao.TjDao;





/**
 *财务统计manager
 *
 * @author dxf
 *
 * @date 2015年10月16日
 */
@Service("tjManager")
public class TjManager {
    @Autowired
    TjDao tjDao;
    
    // 获取财务报账统计数据
 	public List<TjDTO> getKyData(String startDate,
 			String endDate, String org_id,String userName) {
 		return tjDao.getKyData(startDate, endDate, org_id, userName);
 	}
 // 获取财务报账统计数据
  	public List<Object> getFybx(List<String> lists) {
  		return tjDao.getFybx(lists);
  	}
 // 获取项目名称
   	public List<String> getFybxlb() {
   		return tjDao.getFybxlb();
   	}
 // 获取持证数据（部门）
  	public List<TjDTO> getCzData(String startDate,
  			String endDate, String org_id,String unit) {
  		return tjDao.getCzData(startDate, endDate, org_id, unit);
  	}
   
}
