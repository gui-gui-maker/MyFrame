package com.lsts.finance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsts.finance.bean.CwFytjDTO;
import com.lsts.finance.dao.CwFytjDao;


/**
 *财务统计manager
 *
 * @author dxf
 *
 * @date 2015年10月16日
 */
@Service("cwFytjManager")
public class CwFytjManager {
    @Autowired
    CwFytjDao cwFytjDao;
    
    // 获取财务报账统计数据
 	public List<CwFytjDTO> getCwData(String startDate,
 			String endDate, String org_id,String userName) {
 		return cwFytjDao.getCwData(startDate, endDate, org_id, userName);
 	}
 // 获取财务报账统计数据（部门）
  	public List<CwFytjDTO> getCwBmData(String startDate,
  			String endDate, String org_id,String unit) {
  		return cwFytjDao.getCwBmData(startDate, endDate, org_id, unit);
  	}
   
}
