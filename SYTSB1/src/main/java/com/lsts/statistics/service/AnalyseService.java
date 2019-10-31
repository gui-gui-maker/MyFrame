package com.lsts.statistics.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.pub.codetable.bean.CodeTableValue;
import com.khnt.rbac.impl.bean.Area;
import com.khnt.rbac.impl.dao.EmployeeDao;
import com.khnt.utils.StringUtil;
import com.lsts.employee.bean.EmployeeCert;
import com.lsts.employee.dao.EmployeeCertDao;
import com.lsts.humanresources.bean.EmployeeBase;
import com.lsts.humanresources.dao.EmployeeBaseDao;
import com.lsts.statistics.bean.DeviceCountDTO;
import com.lsts.statistics.dao.AnalyseDao;

/**
 * 统计分析
 * 
 * @author liming
 * @Date 2014-6-6
 */
@Service("analyseService")
public class AnalyseService {
	@Autowired
	private AnalyseDao analyseDao;
	
	private Logger logger = Logger.getLogger(this.getClass());
	/**
	 * 按地区和设备类型统计
	 * 
	 * @param mainCode
	 *            主类代码
	 * @param areaCode
	 *            区划代码
	 * @param startDate
	 *            下次检验日期始
	 * @param endDate
	 *            下次检验日期止
	 * @return
	 */
	public List<Object> deviceCountByArea(Date startDate, Date endDate) {
		List<Object> mapResule = new ArrayList<Object>();
		try {
			List<Area> listarea = analyseDao.vAreaList();
			List<CodeTableValue> cvlist = analyseDao.deviceSortMainCode();
			for (int i = 0; i < listarea.size(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				map.put(listarea.get(i).getCode(), listarea.get(i).getText());
				map.put("areaCode", listarea.get(i).getCode());
				map.put("areaName", listarea.get(i).getText());
				int total = 0;

				for (int j = 0; j < cvlist.size(); j++) {
					String device_type = cvlist.get(j).getValue().substring(0,1);
					String count = analyseDao
							.deviceCountByArea(device_type, listarea.get(
									i).getCode(), startDate, endDate);
					map.put(device_type, count);
					total += Integer.parseInt(count);
					if ("3".equals(device_type)) {
						String count1 = analyseDao.deviceUncheckCountByArea(
								device_type, listarea.get(i).getCode()); // 未报检数量
						map.put("32", count1);
					}
				}
				map.put("total", String.valueOf(total));
				mapResule.add(map);
			} 
		}catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.toString());
		}
		return mapResule;
	}

	// 各检验部门检验业务统计
	public List<DeviceCountDTO> queryCount(String startDate, String endDate,
			String device_type, String org_id) {
		return analyseDao.queryCount(startDate, endDate, device_type, org_id);
	}

	// 各检验部门检验业务统计表导出
	public List<DeviceCountDTO> exportCount(String startDate, String endDate,
			String device_type) {
		return analyseDao.exportCount(startDate, endDate, device_type);
	}

	// 按设备类别（电梯）统计各检验部门已打印的定检、监检报告数量
	public List<DeviceCountDTO> devicePrintedCount(String startDate,
			String endDate) {
		return analyseDao.devicePrintedCount(startDate, endDate);
	}

	// 各部门已打印的电梯定检、监检报告统计表导出
	public List<DeviceCountDTO> exportPrintedCount(String startDate,
			String endDate) {
		return analyseDao.exportPrintedCount(startDate, endDate);
	}
	
	// 业务服务部综合统计
	public List<DeviceCountDTO> all_count(String startDate,
				String endDate) {
		return analyseDao.all_count(startDate, endDate);
	}

	// 各部门人员业务统计表
	public List<DeviceCountDTO> deviceCountByUser(String startDate,
			String endDate, String org_id) {
		return analyseDao.deviceCountByUser(startDate, endDate, org_id);
	}

	// 各部门人员业务统计表导出
	public List<DeviceCountDTO> exportCountByUser(String startDate,
			String endDate, String org_id) {
		return analyseDao.exportCountByUser(startDate, endDate, org_id);
	}
	// 机电六部已打印报告统计表导出
	public List<DeviceCountDTO> exportPrintedCount_JD6(String equipmentVariety,String startDate,
			String endDate) {
		List<DeviceCountDTO> list=null;
		if(equipmentVariety.equals("")||equipmentVariety==null){
			equipmentVariety="4";
		}
		if(equipmentVariety.substring(0,1).equals("4")){//起重机械
			list=analyseDao.exportPrintedCount_JD6qz(startDate, endDate);
		}else if(equipmentVariety.substring(0,1).equals("5")){//场（厂）内专用机动车辆
			list=analyseDao.exportPrintedCount_JD6cn(startDate, endDate);
		}else if(equipmentVariety.substring(0,1).equals("6")){//大型游乐设施
			list=analyseDao.exportPrintedCount_JD6yl(startDate, endDate);
		}else if(equipmentVariety.substring(0,1).equals("9")){//客运索道
			list=analyseDao.exportPrintedCount_JD6kysd(startDate, endDate);
		}else{
			list=analyseDao.exportPrintedCount_JD6qz(startDate, endDate);
		}
		return list;
	}

}
