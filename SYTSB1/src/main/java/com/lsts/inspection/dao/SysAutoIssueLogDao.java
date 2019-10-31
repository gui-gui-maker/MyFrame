package com.lsts.inspection.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.constant.Constant;
import com.lsts.inspection.bean.SysAutoIssueLog;



/**
 * 系统自动随机分配报告签发日志表数据处理对象
 * @ClassName SysAutoIssueDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2017-06-28 上午10:25:00
 */
@Repository("sysAutoIssueDao")
public class SysAutoIssueLogDao extends EntityDaoImpl<SysAutoIssueLog> {

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
	 * @date 2017-06-27 上午11:02:00
	 */
	@SuppressWarnings("unchecked")
	public List<SysAutoIssueLog> getInfos(String report_com_name, String check_unit_id, String device_type,
			String start_date, String end_date) {
		List<SysAutoIssueLog> list = new ArrayList<SysAutoIssueLog>();
		String hql = "from SysAutoIssueLog t where t.report_com_name=? and t.check_unit_id=? and t.device_type=? and t.op_action = '"
				+ Constant.SYS_OP_ACTION_AUTO_ISSUE + "' and t.op_time >= to_date(substr('" + start_date
				+ "',0,10),'yyyy-MM-dd') and t.op_time <= to_date('" + end_date + "','yyyy-MM-dd HH24:mi:ss')";
		list = this.createQuery(hql, report_com_name, check_unit_id, device_type).list();
		return list;
	}
	
	/**
	 * 根据分配目标对象ID查询已分配累计总数
	 * @param user_id -- 分配目标对象ID
	 * @param check_unit_id -- 可签部门ID
	 * @param device_type -- 设备类别
	 * 
	 * @return 
	 * @author GaoYa
	 * @date 2017-06-28 上午11:15:00
	 */
	public int getCount(String user_id, String check_unit_id, String device_type) {
		String sql = "select count(distinct t.business_id) as issue_count from sys_auto_issue_log t "
				+ "where t.to_user_id=? and t.device_type=? and t.op_action = '"
				+ Constant.SYS_OP_ACTION_AUTO_ISSUE + "' ";
		if(StringUtil.isNotEmpty(check_unit_id) && !"1".equals(check_unit_id)){
			sql += " and t.check_unit_id='"+check_unit_id+"'";
		}
		List list = this.createSQLQuery(sql, user_id, device_type).list();
		if (!list.isEmpty()) {
			return list.get(0) != null ? Integer.parseInt(list.get(0) + "") : 0;
		}
		return 0;
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
		List<SysAutoIssueLog> list = new ArrayList<SysAutoIssueLog>();
		SysAutoIssueLog issueLog = null;
		String hql = "from SysAutoIssueLog t where t.business_id=? order by t.op_time desc";
		list = this.createQuery(hql, business_id).list();
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return issueLog;
	}
}
