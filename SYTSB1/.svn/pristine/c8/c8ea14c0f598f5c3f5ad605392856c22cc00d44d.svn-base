package com.lsts.inspection.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.inspection.bean.SysAutoIssueLog;
import com.lsts.inspection.dao.SysAutoIssueLogDao;

/**
 * 系统自动随机分配报告签发日志表业务逻辑对象
 * @ClassName SysAutoIssueLogService
 * @JDK 1.7
 * @author GaoYa
 * @date 2017-06-28 上午10:44:00
 */
@Service("sysAutoIssueLogService")
public class SysAutoIssueLogService extends EntityManageImpl<SysAutoIssueLog, SysAutoIssueLogDao> {
	
	@Autowired
	private SysAutoIssueLogDao sysAutoIssueLogDao;
	
	/**
	 * 根据使用单位名称、检验部门ID查询已分配人员信息
	 * @param report_com_name -- 使用单位名称
	 * @param check_unit_id -- 检验部门ID
	 * @param device_type -- 设备类别
	 * @param start_date -- 开始时间
	 * @param end_date -- 结束时间
	 * 
	 * @return 
	 * @author GaoYa
	 * @date 2017-06-28 上午10:45:00
	 */
	public List<SysAutoIssueLog> getInfos(String report_com_name, String check_unit_id, String device_type, String start_date,
			String end_date) {
		return sysAutoIssueLogDao.getInfos(report_com_name, check_unit_id, device_type, start_date, end_date);
	}
	
	/**
	 * 根据分配目标对象ID查询已分配累计总数
	 * @param emp_id -- 分配目标对象ID
	 * @param check_unit_id -- 检验部门ID
	 * @param device_type -- 设备类别
	 * 
	 * @return 
	 * @author GaoYa
	 * @date 2017-06-28 上午11:15:00
	 */
	@SuppressWarnings("unchecked")
	public int getCount(String emp_id, String check_unit_id, String device_type) {
		return sysAutoIssueLogDao.getCount(emp_id, check_unit_id, device_type);
	}
	
	/**
	 * 根据报告业务ID查询已分配技能信息
	 * @param business_id -- 报告业务ID
	 * 
	 * @return 
	 * @author GaoYa
	 * @date 2017-07-18 下午16:24:00
	 */
	@SuppressWarnings("unchecked")
	public SysAutoIssueLog getIssueLog(String business_id) {
		return sysAutoIssueLogDao.getIssueLog(business_id);
	}
}
